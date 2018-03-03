package com.hd.controller.bpmn;


import com.hd.controller.base.BaseController;
import com.hd.entity.activiti.TaskVO;
import com.hd.util.PageInfo;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 任务Controller层
 *
 * @author user
 */
@Controller
@RequestMapping("/task")
public class TaskController extends BaseController{

    @Resource
    private TaskService taskService;

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private FormService formService;

    @Resource
    private HistoryService historyService;

    /**
     * 根据用户id分页查询任务
     */
    @ResponseBody
    @RequestMapping("/list")
    public Object list(Integer page,Integer limit,String name,String userId){
        if (name== null) {
            name = "";
        }
        PageInfo info = new PageInfo(page,limit);
        List<Task> list = taskService.createTaskQuery() // 创建任务查询
                .taskCandidateUser(userId) // 根据用户id查询
                .taskNameLike("%" + name + "%") // 根据任务名称查询
                .listPage(page,limit); // 返回带分页的结果集合
        List<TaskVO> taskList = new ArrayList<TaskVO>();
        for (Task t : list) {
            TaskVO TaskVO = new TaskVO();
            TaskVO.setId(t.getId());
            TaskVO.setName(t.getName());
            TaskVO.setCreateTime(t.getCreateTime());
            taskList.add(TaskVO);
        }
        long total = taskService.createTaskQuery().taskCandidateUser(userId).taskNameLike("%" + name + "%").count(); // 获取总记录数
        info.setCount(total);
        info.setData(taskList);
        return info;
    }

    /**
     * 流程实例走完的历史任务
     *
     */
    @ResponseBody
    @RequestMapping("/finishedList")
    public Object finishedList(Integer page, Integer limit, String name, String userId) throws Exception {
        if (name == null) {
            name = "";
        }
        PageInfo info=new PageInfo(page,limit);
        List<HistoricTaskInstance> htiList = historyService.createHistoricTaskInstanceQuery() // 创建历史任务实例查询
                .taskCandidateUser(userId) // 根据角色查询
                .taskNameLike("%" + name + "%") // 根据任务名称查询
                .list();
        List<TaskVO> taskList = new ArrayList<TaskVO>();
        for (HistoricTaskInstance hti : htiList) {
            if ((taskService.createTaskQuery().processInstanceId(hti.getProcessInstanceId()).singleResult() == null)) {
                TaskVO TaskVO = new TaskVO();
                TaskVO.setId(hti.getId());
                TaskVO.setName(hti.getName());
                TaskVO.setCreateTime(hti.getCreateTime());
                TaskVO.setEndTime(hti.getEndTime());
                taskList.add(TaskVO);
            }
        }
        int total=taskList.size();
        if (taskList.size() > ((page-1)*limit + limit)) {
            taskList = taskList.subList((page-1)*limit, ((page-1)*limit + limit));
        }
        info.setData(taskList);
        info.setCount(total);
        return info;
    }

    /**
     * 流程实例未走完的历史任务
     *
     */
    @ResponseBody
    @RequestMapping("/unFinishedList")
    public Object unFinishedList(Integer page, Integer limit, String name, String userId) {
        if (name == null) {
            name = "";
        }
       PageInfo info=new PageInfo(page,limit);
        List<HistoricTaskInstance> htiList = historyService.createHistoricTaskInstanceQuery() // 创建历史任务实例查询
                .taskCandidateUser(userId) // 根据角色查询
                .taskNameLike("%" + name + "%") // 根据任务名称查询
                .list();
        List<TaskVO> taskList = new ArrayList<TaskVO>();
        for (HistoricTaskInstance hti : htiList) {
            if ((taskService.createTaskQuery().processInstanceId(hti.getProcessInstanceId()).singleResult() != null)
                    && (taskService.createTaskQuery().taskCandidateUser(userId).taskId(hti.getId()).list().size() == 0)) {
                TaskVO TaskVO = new TaskVO();
                TaskVO.setId(hti.getId());
                TaskVO.setName(hti.getName());
                TaskVO.setCreateTime(hti.getCreateTime());
                TaskVO.setEndTime(hti.getEndTime());
                taskList.add(TaskVO);
            }
        }
        int total=taskList.size();
        if (taskList.size() > ((page-1)*limit + limit)) {
            taskList = taskList.subList((page-1)*limit, ((page-1)*limit + limit));
        }
        info.setCount(total);
        info.setData(taskList);
        return info;
    }

    /**
     * 查询当前流程图
     *
     * @param taskId
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/showCurrentView")
    public ModelAndView showCurrentView(String taskId, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        Task task = taskService.createTaskQuery() // 创建任务查询
                .taskId(taskId) // 根据任务id查询
                .singleResult();
        String processDefinitionId = task.getProcessDefinitionId(); // 获取流程定义id
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery() // 创建流程定义查询
                .processDefinitionId(processDefinitionId) // 根据流程定义id查询
                .singleResult();
        mav.addObject("deploymentId", processDefinition.getDeploymentId()); // 部署id
        mav.addObject("diagramResourceName", processDefinition.getDiagramResourceName()); // 图片资源文件名称

        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);
        String processInstanceId = task.getProcessInstanceId(); // 获取流程实例id
        ProcessInstance pi = runtimeService.createProcessInstanceQuery() // 根据流程实例id获取流程实例
                .processInstanceId(processInstanceId)
                .singleResult();
        ActivityImpl activityImpl = processDefinitionEntity.findActivity(pi.getActivityId()); // 根据活动id获取活动实例
        mav.addObject("x", activityImpl.getX()); // x坐标
        mav.addObject("y", activityImpl.getY()); // y坐标
        mav.addObject("width", activityImpl.getWidth()); // 宽度
        mav.addObject("height", activityImpl.getHeight()); // 高度
        mav.setViewName("page/currentView");
        return mav;
    }

    /**
     * 查询当前流程图 待办用到的
     *
     * @param taskId
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/showHisCurrentView")
    public ModelAndView showHisCurrentView(String taskId, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        HistoricTaskInstance hti = historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
        String processDefinitionId = hti.getProcessDefinitionId(); // 获取流程定义id
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery() // 创建流程定义查询
                .processDefinitionId(processDefinitionId) // 根据流程定义id查询
                .singleResult();
        mav.addObject("deploymentId", processDefinition.getDeploymentId()); // 部署id
        mav.addObject("diagramResourceName", processDefinition.getDiagramResourceName()); // 图片资源文件名称

        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);
        String processInstanceId = hti.getProcessInstanceId(); // 获取流程实例id
        ProcessInstance pi = runtimeService.createProcessInstanceQuery() // 根据流程实例id获取流程实例
                .processInstanceId(processInstanceId)
                .singleResult();
        ActivityImpl activityImpl = processDefinitionEntity.findActivity(pi.getActivityId()); // 根据活动id获取活动实例
        mav.addObject("x", activityImpl.getX()); // x坐标
        mav.addObject("y", activityImpl.getY()); // y坐标
        mav.addObject("width", activityImpl.getWidth()); // 宽度
        mav.addObject("height", activityImpl.getHeight()); // 高度
        mav.setViewName("page/currentView");
        return mav;
    }


    /**
     * 历史批注查询 根据任务id
     *
     * @param taskId
     * @return
     */
    @ResponseBody
    @RequestMapping("/listHistoryComment")
    public Object listHistoryComment(String taskId) {
        if (taskId == null) {
            return null;
        }
        HistoricTaskInstance hti = historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
        List<Comment> commentList = taskService.getProcessInstanceComments(hti.getProcessInstanceId());
        Collections.reverse(commentList); // 集合元素反转
        return commentList;
    }

    /**
     * 历史批注 通过流程实例id查询
     *
     * @param processInstanceId
     */
    @ResponseBody
    @RequestMapping("/listHistoryCommentWithProcessInstanceId")
    public Object listHistoryCommentWithProcessInstanceId(String processInstanceId) {
        if (processInstanceId == null) {
            return null;
        }
        List<Comment> commentList = taskService.getProcessInstanceComments(processInstanceId);
        Collections.reverse(commentList); // 集合元素反转
        return commentList;
    }

    /**
     * 根据任务id查询流程实例的具体执行过程
     *
     * @param taskId
     */
    @ResponseBody
    @RequestMapping("/listAction")
    public Object listAction(String taskId){
        HistoricTaskInstance hti = historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
        String processInstanceId = hti.getProcessInstanceId(); // 获取流程实例id
        List<HistoricActivityInstance> haiList = historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).list();
        return haiList;
    }
}
