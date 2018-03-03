package com.hd.controller.bpmn;

import com.hd.controller.base.BaseController;
import com.hd.entity.BaseVO;
import com.hd.entity.CommentVO;
import com.hd.entity.User;
import com.hd.entity.Vacation;
import com.hd.service.ProcessService;
import com.hd.service.VacationService;
import com.hd.util.PageInfo;
import com.hd.util.Result;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 业务流程：请假
 */
@RequestMapping("vacation")
@Controller
public class VacationController extends BaseController{
    @Autowired
    private VacationService vacationService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ProcessService processService;

    @RequestMapping("index")
    public String index(){
        return "jsp/vacation/vacationList";
    }

    /**
     * 所有请假列表
     * @param vacation
     * @param page
     * @param limit
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/dataGrid",method = RequestMethod.POST)
    public Object dataGrid(Vacation vacation,Integer page,Integer limit){
        PageInfo info=new PageInfo(page,limit);
        Map<String,Object>condition=new HashMap<String,Object>();
        if(vacation.getUserId()!=null){
            condition.put("userId",vacation.getUserId());
        }
        info.setCondition(condition);
        vacationService.findDataGrid(info);
        return info;
    }


    /**
     * 添加页
     * @param model
     * @return
     */
    @RequestMapping("/addPage")
    public String addPage(Model model){
        return "jsp/vacation/vacationAdd";
    }

    /**
     * 编辑页
     * @param id
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/editPage/{id}")
    public String editPage(@PathVariable("id") Long id,Model model)throws Exception{
        Vacation vacation = this.vacationService.findById(id);
        model.addAttribute("vacation", vacation);
        return "jsp/vacation/vacationEdit";
    }

    @ResponseBody
    @RequestMapping(value = "/startApply",method = RequestMethod.POST)
    public Object add(Vacation vacation,HttpServletRequest request)throws Exception{
        Result result=new Result();
        User user=(User)request.getSession().getAttribute("user");
        String[] applytime=vacation.getApplytime().split(" - ");
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        vacation.setBeginDate(dateFormat.parse(applytime[0]));
        vacation.setEndDate(dateFormat.parse(applytime[1]));
        vacation.setApplyDate(new Date());
        vacation.setUserId(user.getId());
        vacation.setUserName(user.getName());
        vacation.setTitle(user.getName()+" 的请假申请");
        vacation.setBusinessType(BaseVO.VACATION);
        vacationService.doAdd(vacation);
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("entity", vacation);
        String businessKey=vacation.getId().toString();
        try{
            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("vacation", businessKey, variables);
            String processInstanceId = processInstance.getId();
            vacation.setProcessInstanceId(processInstanceId);
            this.vacationService.doUpdate(vacation);
        } catch (ActivitiException e) {
            result.setSuccess(Boolean.FALSE);
            if (e.getMessage().indexOf("no processes deployed with key") != -1) {
                logger.warn("没有部署流程!", e);
                result.setMsg("没有部署流程，请联系系统管理员，在[流程定义]中部署相应流程文件！");
            } else {
                logger.error("启动请假流程失败：", e);
                result.setMsg("启动请假流程失败，系统内部错误！");
            }
            throw e;
        } catch (Exception e) {
            logger.error("启动请假流程失败：", e);
            result.setSuccess(Boolean.FALSE);
            result.setMsg("启动请假流程失败，系统内部错误！");
            throw e;
        }
        return result;
    }


    /**
     * 详细信息
     * @param id
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/details/{id}")
    public String details(@PathVariable("id") Long id, Model model) throws Exception{
        Vacation vacation = this.vacationService.findById(id);
        model.addAttribute("vacation", vacation);
        return "jsp/vacation/vacationDetails";
    }

    /**
     * 发起请假申请
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "startApply2",method = RequestMethod.POST)
    public Object startApply(Long id)throws Exception{
        Map<String,Object> variables=new HashMap<String,Object>();
        variables.put("entity", id);
        ProcessInstance pi=runtimeService.startProcessInstanceByKey("vacation",variables);
        //根据流程实例id查询任务
        Task task=taskService.createTaskQuery().processInstanceId(pi.getProcessInstanceId()).singleResult();
        //完成请假单申请提交任务
        taskService.complete(task.getId());
        Vacation vacation=vacationService.findById(id);
        vacation.setStatus(BaseVO.PENDING);//审批中
        vacationService.doUpdate(vacation);
        return renderSuccess("提交成功！");
    }

    /**
     * 审批请假流程
     * @param taskId
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/toApproval/{taskId}")
    public String toApproval(@PathVariable("taskId")String taskId,Model model)throws Exception{
        Task task=taskService.createTaskQuery().taskId(taskId).singleResult();
        //根据任务查询流程实例
        String processInstanceId=task.getProcessInstanceId();
        ProcessInstance processInstance=runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        Vacation vacation=(Vacation) this.runtimeService.getVariable(processInstanceId,"vacatonId");
        vacation.setTask(task);
        vacation.setProcessInstanceId(processInstanceId);
        List<CommentVO>commentList=processService.getComments(processInstanceId);
        String taskDefinitionKey = task.getTaskDefinitionKey();
        logger.info("taskDefinitionKey: "+taskDefinitionKey);
        String result = null;
        if("modifyApply".equals(taskDefinitionKey)){
            result = "jsp/vacation/vacationEdit";
        }else{
            result = "jsp/vacation/vacationAudit";
        }
        model.addAttribute("vacation", vacation);
        model.addAttribute("commentList", commentList);
        return result;
    }

    /**
     * 完成任务
     * @param id
     * @param content
     * @param completeFlag
     * @param taskId
     * @param redirectAttributes
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/complate/{taskId}")
    @ResponseBody
    public Object complate(
            @RequestParam("id") Long id,
            @RequestParam("content") String content,
            @RequestParam("completeFlag") Boolean completeFlag,
            @PathVariable("taskId") String taskId,
            RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception{
        Result result=new Result();
        User user=(User)request.getSession().getAttribute("user");
        try {
            Vacation vacation = this.vacationService.findById(id);
            Vacation baseVacation = (Vacation) this.runtimeService.getVariable(vacation.getProcessInstanceId(), "entity");
            Map<String, Object> variables = new HashMap<String, Object>();
            variables.put("isPass", completeFlag);
            if(!completeFlag){
                baseVacation.setTitle(baseVacation.getUserName()+" 的请假申请失败,需修改后重新提交！");
                vacation.setStatus(BaseVO.APPROVAL_FAILED);
                variables.put("entity", baseVacation);
            }
            // 完成任务
            this.processService.complete(taskId, content, user.getId().toString(), variables);

            if(completeFlag){
                //此处需要修改，不能根据人来判断审批是否结束。应该根据流程实例id(processInstanceId)来判定。
                //判断指定ID的实例是否存在，如果结果为空，则代表流程结束，实例已被删除(移到历史库中)
                ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().processInstanceId(vacation.getProcessInstanceId()).singleResult();
                if(pi!=null){
                    vacation.setStatus(BaseVO.APPROVAL_SUCCESS);
                }
            }

            this.vacationService.doUpdate(vacation);

            result.setSuccess(Boolean.TRUE);
            result.setMsg("任务办理完成！");
        } catch (ActivitiObjectNotFoundException e) {
            result.setSuccess(Boolean.FALSE);
            result.setMsg("此任务不存在，请联系管理员！");
            throw e;
        } catch (ActivitiException e) {
            result.setSuccess(Boolean.FALSE);
            result.setMsg("此任务正在协办，您不能办理此任务！");
            throw e;
        } catch (Exception e) {
            result.setSuccess(Boolean.FALSE);
            result.setMsg("任务办理失败，请联系管理员！");
            throw e;
        }
        return result;
    }


    /**
     * 调整请假申请
     * @param vacation
     * @param taskId
     * @param processInstanceId
     * @param reApply
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/modifyVacation/{taskId}", method = RequestMethod.POST)
    @ResponseBody
    public Object modifyVacation(
            @ModelAttribute("vacation") Vacation vacation,
            @PathVariable("taskId") String taskId,
            @RequestParam("processInstanceId") String processInstanceId,
            @RequestParam("reApply") Boolean reApply,
            HttpServletRequest request) throws Exception{

        User user = (User)request.getSession().getAttribute("user");
        Result result=new Result();

        Map<String, Object> variables = new HashMap<String, Object>();
        vacation.setUserId(user.getId());
        vacation.setUser_name(user.getName());
        vacation.setBusinessType(BaseVO.VACATION);
        vacation.setApplyDate(new Date());
        vacation.setBusinessKey(vacation.getId().toString());
        vacation.setProcessInstanceId(processInstanceId);
        if(reApply){
            //修改请假申请
            vacation.setTitle(user.getName()+" 的请假申请！");
            vacation.setStatus(BaseVO.PENDING);
            //由userTask自动分配审批权限
//	        if(vacation.getDays() <= 3){
//            	variables.put("auditGroup", "manager");
//            }else{
//            	variables.put("auditGroup", "director");
//            }
            result.setSuccess(Boolean.TRUE);
            result.setMsg("任务办理完成，请假申请已重新提交！");
        }else{
            vacation.setStatus(BaseVO.APPROVAL_FAILED);
            result.setSuccess(Boolean.TRUE);
            result.setMsg("任务办理完成，已经取消您的请假申请！");
        }
        try {
            this.vacationService.doUpdate(vacation);
            variables.put("entity", vacation);
            variables.put("reApply", reApply);
            this.processService.complete(taskId, "取消申请", user.getId().toString(), variables);

        } catch (ActivitiObjectNotFoundException e) {
            result.setSuccess(Boolean.FALSE);
            result.setMsg("此任务不存在，请联系管理员！");
            throw e;
        } catch (ActivitiException e) {
            result.setSuccess(Boolean.FALSE);
            result.setMsg("此任务正在协办，您不能办理此任务！");
            throw e;
        } catch (Exception e) {
            result.setSuccess(Boolean.FALSE);
            result.setMsg("任务办理失败，请联系管理员！");
            throw e;
        }
        return result;
    }

    /**
     * 通过任务Id获取请假单
     * @param taskId
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/getVacationByTaskId/{taskId}")
    public Object getVacationByTaskId(@PathVariable("taskId") String taskId)throws Exception{
        Long id=(Long)taskService.getVariable(taskId,"entity");
        Vacation vacation=vacationService.findById(id);
        return vacation;
    }

}
