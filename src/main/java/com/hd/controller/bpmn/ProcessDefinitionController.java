package com.hd.controller.bpmn;

import com.hd.controller.base.BaseController;
import com.hd.entity.ProcessDefinitionEntity;
import com.hd.util.PageInfo;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 流程定义管理Controller
 * @author user
 *
 */
@Controller
@RequestMapping("/processDefinition")
public class ProcessDefinitionController extends BaseController{
    @Resource
    private RepositoryService repositoryService;

    @Resource
    private HistoryService historyService;

    /**
     * 流程定义查询列表
     * @param page
     * @param limit
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping("dataGrid")
   public Object dataGrid(Integer page,Integer limit,String name){
       if(name==null){
           name="";
       }
       PageInfo info=new PageInfo(page,limit);
       List<ProcessDefinition>processDefinitions=repositoryService.createProcessDefinitionQuery()
               .orderByProcessDefinitionId().desc()//根据流程定义id降序查询
               .processDefinitionNameLike(""+name+"%")// 根据流程定义名称模糊查询
               .listPage(page,limit);//返回带分页的查询结果结合
       long total=repositoryService.createProcessDefinitionQuery().processDefinitionNameLike("%"+name+"%").count(); // 获取总记录数
        List<ProcessDefinitionEntity>processDefinitionEntities=new ArrayList<ProcessDefinitionEntity>();
        for (ProcessDefinition processDefinition:processDefinitions){
            ProcessDefinitionEntity processDefinitionEntity=new ProcessDefinitionEntity();
            processDefinitionEntity.setId(processDefinition.getId());
            processDefinitionEntity.setName(processDefinition.getName());
            processDefinitionEntity.setDiagramResourceName(processDefinition.getDiagramResourceName());
            processDefinitionEntity.setRevision(processDefinition.getVersion());
        }
       info.setData(processDefinitions);
       info.setCount(total);
       return info;
   }

    /**
     * 查看流程图
     * @param deploymentId
     * @param diagramResourceName
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/showView")
    public String showView(String deploymentId,String diagramResourceName,HttpServletResponse response)throws Exception{
        InputStream inputStream=repositoryService.getResourceAsStream(deploymentId, diagramResourceName);
        OutputStream out=response.getOutputStream();
        for(int b=-1;(b=inputStream.read())!=-1;){
            out.write(b);
        }
        out.close();
        inputStream.close();
        return null;
    }

    /**
     * 查看流程图
     * @param
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/showViewByTaskId")
    public String showViewByTaskId(String taskId,HttpServletResponse response)throws Exception{
        HistoricTaskInstance hti=historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
        String processDefinitionId=hti.getProcessDefinitionId(); // 获取流程定义id
        ProcessDefinition pd=repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        InputStream inputStream=repositoryService.getResourceAsStream(pd.getDeploymentId(), pd.getDiagramResourceName());
        OutputStream out=response.getOutputStream();
        for(int b=-1;(b=inputStream.read())!=-1;){
            out.write(b);
        }
        out.close();
        inputStream.close();
        return null;
    }
}
