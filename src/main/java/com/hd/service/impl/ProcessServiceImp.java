package com.hd.service.impl;

import com.hd.entity.BaseVO;
import com.hd.entity.CommentVO;
import com.hd.entity.User;
import com.hd.entity.Vacation;
import com.hd.processtask.DeleteActiveTaskCmd;
import com.hd.processtask.RevokeTaskCmd;
import com.hd.processtask.StartActivityCmd;
import com.hd.service.ProcessService;
import com.hd.service.UserService;
import com.hd.service.VacationService;
import com.hd.service.WorkflowService;
import com.hd.util.PageInfo;
import org.activiti.bpmn.BpmnAutoLayout;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.*;
import org.activiti.engine.history.*;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.DelegationState;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流程相关Service
 * @author zml
 *
 */
@Service
public class ProcessServiceImp implements ProcessService {

	private static final Logger logger = LogManager.getLogger(ProcessServiceImp.class);
	
	@Autowired
	protected RuntimeService runtimeService;
	
    @Autowired
    protected IdentityService identityService;
    
    @Autowired
    protected TaskService taskService;
    
    @Autowired
    protected RepositoryService repositoryService;
    
    @Autowired
    protected HistoryService historyService;
    
	@Autowired
	protected UserService userService;
	
    @Autowired
    ProcessEngineFactoryBean processEngineFactory;

    @Autowired
    ProcessEngineConfiguration processEngineConfiguration;
    
    @Autowired
    protected WorkflowService workflowService;
    
	@Autowired
	private VacationService vacationService;

	@Autowired
	private ProcessEngine processEngine;
	
	
    /**
     * 查询代办任务
     * @param user
     * @param 
     * @return
     */
	@Override
    public List<BaseVO> findTodoTask(User user, PageInfo info){
		//taskCandidateOrAssigned查询某个人的待办任务，包含已签收、候选任务<候选人范围和候选组范围>
		TaskQuery taskQuery = this.taskService.createTaskQuery().taskCandidateOrAssigned(user.getId().toString());
		Integer totalSum = taskQuery.list().size();
		List<Task> tasks = taskQuery.orderByTaskCreateTime().desc().listPage(info.getPage(), info.getLimit());
		List<BaseVO> taskList = getBaseVOList(tasks);
		return taskList;
    } 

    /**
     * 读取已结束中的流程(admin查看)
     *
     * @return
     */
    @Override
    public List<BaseVO> findFinishedProcessInstances(PageInfo info) {
        HistoricProcessInstanceQuery historQuery = historyService.createHistoricProcessInstanceQuery().finished();
    	
        Integer totalSum = historQuery.list().size();
		List<HistoricProcessInstance> list = historQuery.orderByProcessInstanceEndTime().desc().listPage(info.getPage(), info.getLimit());
		List<BaseVO> processList = new ArrayList<BaseVO>();
		
		for (HistoricProcessInstance historicProcessInstance : list) {
			String processInstanceId = historicProcessInstance.getId();
			List<HistoricVariableInstance> listVar = this.historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).list();
			for(HistoricVariableInstance var : listVar){
				if("serializable".equals(var.getVariableTypeName()) && "entity".equals(var.getVariableName())){
					BaseVO base = (BaseVO) var.getValue();
					base.setHistoricProcessInstance(historicProcessInstance);
					base.setProcessDefinition(getProcessDefinition(historicProcessInstance.getProcessDefinitionId()));
					processList.add(base);
					break;
				}
			}
		}
		
        return processList;
    }
	
    /**
     * 各个审批人员查看自己完成的任务
     * @param 
     * @return
     * @throws Exception
     */
	@Override
	public List<BaseVO> findFinishedTaskInstances(User user, PageInfo info) throws Exception {
		HistoricTaskInstanceQuery historQuery = historyService.createHistoricTaskInstanceQuery().taskAssignee(user.getId().toString()).finished();
		Integer totalSum = historQuery.list().size();
    	List<HistoricTaskInstance> list = historQuery.orderByHistoricTaskInstanceEndTime().desc().listPage(info.getPage(), info.getLimit());
    	List<BaseVO> taskList = new ArrayList<BaseVO>();
    	
    	for(HistoricTaskInstance historicTaskInstance : list){
    		String processInstanceId = historicTaskInstance.getProcessInstanceId();
    		List<HistoricVariableInstance> listVar = this.historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).list();
			for(HistoricVariableInstance var : listVar){
				if("serializable".equals(var.getVariableTypeName()) && "entity".equals(var.getVariableName())){
					BaseVO base = (BaseVO) var.getValue();
					base.setHistoricTaskInstance(historicTaskInstance);
					base.setProcessDefinition(getProcessDefinition(historicTaskInstance.getProcessDefinitionId()));
					taskList.add(base);
					break;
				}
			}
    	}
		return taskList;
	}
    
    /**
     * 将Task集合转为BaseVO集合
     * @param tasks
     * @return
     */
    protected List<BaseVO> getBaseVOList(List<Task> tasks) {
    	List<BaseVO> taskList = new ArrayList<BaseVO>();
        for (Task task : tasks) {
        	String processInstanceId = task.getProcessInstanceId();
            ProcessInstance processInstance = this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).active().singleResult();
            if(processInstance==null){
            	//如果有挂起的流程则continue
            	continue;
            }
            //获取当前流程下的key为entity的variable
            BaseVO base = (BaseVO) this.runtimeService.getVariable(processInstance.getId(), "entity");
            base.setTask(task);
            base.setProcessInstance(processInstance);
            base.setProcessDefinition(getProcessDefinition(processInstance.getProcessDefinitionId()));
            taskList.add(base);
        }
    	return taskList;
    }
    
    /**
     * 查询流程定义对象
     *
     * @param processDefinitionId 流程定义ID
     * @return
     */
    protected ProcessDefinition getProcessDefinition(String processDefinitionId) {
        ProcessDefinition processDefinition = this.repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        logger.info(processDefinition.getVersion());
        return processDefinition;
    }
    
    /**
     * 签收任务
     * @param user
     * @param taskId
     */
	@Override
    public void doClaim(User user, String taskId){
    	this.identityService.setAuthenticatedUserId(user.getId().toString());
        this.taskService.claim(taskId, user.getId().toString());
    }
	
    /**
     * 委派任务
     */
	@Override
	public void doDelegateTask(String userId, String taskId) throws Exception {
		//API: If no owner is set on the task, the owner is set to the current assignee of the task.
		//OWNER_（委托人）：受理人委托其他人操作该TASK的时候，受理人就成了委托人OWNER_，其他人就成了受理人ASSIGNEE_
		//assignee容易理解，主要是owner字段容易误解，owner字段就是用于受理人委托别人操作的时候运用的字段
		this.taskService.delegateTask(taskId, userId);
	}
	
	/**
	 * 转办任务
	 */
	@Override
	public void doTransferTask(String userId, String taskId) throws Exception {
		Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
		if(task != null){
			String assign = task.getAssignee();
			this.taskService.setAssignee(taskId, userId);
			this.taskService.setOwner(taskId, assign);
		}else{
			throw new ActivitiObjectNotFoundException("任务不存在！", this.getClass());
		}
	}
	
	/**
	 * 获取评论
	 * @param processInstanceId
	 * @return
	 * @throws Exception
	 */
	@Override
    public List<CommentVO> getComments(String processInstanceId) throws Exception{
		// 查询一个任务所在流程的全部评论
		List<Comment> comments = this.taskService.getProcessInstanceComments(processInstanceId);
		List<CommentVO> commnetList = new ArrayList<CommentVO>();
		for(Comment comment : comments){
			User user = this.userService.getUser(Long.valueOf(comment.getUserId()));
			CommentVO vo = new CommentVO();
			vo.setContent(comment.getFullMessage());
			vo.setTime(comment.getTime());
			vo.setUserName(user.getName());
			commnetList.add(vo);
		}
    	return commnetList;
    }
    
    
    /**
     * 显示流程图,带流程跟踪
     * @param processInstanceId
     * @return
     */
    @Override
    public InputStream getDiagram(String processInstanceId){
    	ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
        List<String> activeActivityIds = runtimeService.getActiveActivityIds(processInstanceId);
        // 不使用spring请使用下面的两行代码
//    	ProcessEngineImpl defaultProcessEngine = (ProcessEngineImpl) ProcessEngines.getDefaultProcessEngine();
//    	Context.setProcessEngineConfiguration(defaultProcessEngine.getProcessEngineConfiguration());

        // 使用spring注入引擎请使用下面的这行代码
        processEngineConfiguration = processEngineFactory.getProcessEngineConfiguration();
        Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);

        //通过引擎生成png图片，并标记当前节点,并把当前节点用红色边框标记出来，弊端和直接部署流程文件生成的图片问题一样-乱码！。
        ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
        InputStream imageStream = diagramGenerator.generateDiagram(bpmnModel, "png", activeActivityIds);
    	return imageStream;
    }
    
    /**
     * 显示图片-通过流程ID，，不带流程跟踪(没有乱码问题)
     * @param resourceType
     * @param processInstanceId
     * @return
     */
    @Override
    public InputStream getDiagramByProInstanceId_noTrace(String resourceType, String processInstanceId){
    	
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processInstance.getProcessDefinitionId())
                .singleResult();

        String resourceName = "";
        if (resourceType.equals("png") || resourceType.equals("image")) {
            resourceName = processDefinition.getDiagramResourceName();
        } else if (resourceType.equals("xml")) {
            resourceName = processDefinition.getResourceName();
        }
        InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
        return resourceAsStream;
    }
    
    /**
     * 显示图片-通过部署ID，不带流程跟踪(没有乱码啊问题)
     * @param resourceType
     * @param processDefinitionId
     * @return
     * @throws Exception
     */
	@Override
	public InputStream getDiagramByProDefinitionId_noTrace(String resourceType,
			String processDefinitionId) throws Exception {
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        String resourceName = "";
        if (resourceType.equals("png") || resourceType.equals("image")) {
            resourceName = processDefinition.getDiagramResourceName();
        } else if (resourceType.equals("xml")) {
            resourceName = processDefinition.getResourceName();
        }
        InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
        return resourceAsStream;
	}

    /**
     * 查看正在运行的请假流程
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public List<BaseVO> listRuningVacation(User user, PageInfo info) throws Exception{
    	List<Vacation> listVacation = this.vacationService.findByStatus(user.getId(), BaseVO.PENDING, info);
		List<BaseVO> result = new ArrayList<BaseVO>();
		if(listVacation != null ){
			for (Vacation vac : listVacation) {
				if(vac.getProcessInstanceId() == null){
					continue;
				}
				// 查询流程实例
				ProcessInstance pi = this.runtimeService
						.createProcessInstanceQuery()
						.processInstanceId(vac.getProcessInstanceId())
						.singleResult();
				Task task = this.taskService.createTaskQuery().processInstanceId(vac.getProcessInstanceId()).singleResult();
				if (pi != null) {
					// 查询流程参数
					BaseVO base = (BaseVO) this.runtimeService.getVariable(pi.getId(), "entity");
					base.setTask(task);
		            base.setProcessInstance(pi);
		            base.setProcessDefinition(getProcessDefinition(pi.getProcessDefinitionId()));
					
					result.add(base);
				}
			}
		}
		return result;
    }
    




	@Override
	public String startVacation(Vacation vacation) throws Exception {
		// 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
        identityService.setAuthenticatedUserId(vacation.getUserId().toString());
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("entity", vacation);
        //由userTask自动分配审批权限
//        if(vacation.getDays() <= 3){
//        	variables.put("auditGroup", "manager");
//        }else{
//        	variables.put("auditGroup", "director");
//        }
        String businessKey = vacation.getBusinessKey();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("vacation", businessKey, variables);
        String processInstanceId = processInstance.getId();
        vacation.setProcessInstanceId(processInstanceId);
        this.vacationService.doUpdate(vacation);

        logger.info("processInstanceId: "+processInstanceId);
        //最后要设置null，就是这么做，还没研究为什么
        this.identityService.setAuthenticatedUserId(null);
        return processInstanceId;
	}



	/**
	 * 完成任务
	 */
	@Override
	public void complete(String taskId, String content, String userid, Map<String, Object> variables) throws Exception{
		Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
		// 根据任务查询流程实例
    	String processInstanceId = task.getProcessInstanceId();
    	ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		//评论人的id  一定要写，不然查看的时候会报错，没有用户
    	this.identityService.setAuthenticatedUserId(userid);
		// 添加评论--意见为空时，默认“同意”。
    	
    	if(content != null){
    		if(content == ""){
        		content = "同意";
        	}
    		this.taskService.addComment(taskId, pi.getId(), content);
    	}
    	
		// 完成委派任务
    	if(DelegationState.PENDING == task.getDelegationState()){
    		this.taskService.resolveTask(taskId, variables);
    		return;
    	}
    	//正常完成任务
		this.taskService.complete(taskId, variables);
	}

	@Override
	public List<ProcessInstance> listRuningProcess(PageInfo info) throws Exception {
		
		ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
		List<ProcessInstance> list = processInstanceQuery.orderByProcessInstanceId().desc().listPage(info.getPage(), info.getLimit());
		return list;
	}

	@Override
	public void activateProcessInstance(String processInstanceId)
			throws Exception {
		runtimeService.activateProcessInstanceById(processInstanceId);
	}

	@Override
	public void suspendProcessInstance(String processInstanceId)
			throws Exception {
		runtimeService.suspendProcessInstanceById(processInstanceId);
	}

	/**
	 * 撤回任务
	 */
	@Override
	public Integer revoke(String historyTaskId, String processInstanceId) throws Exception {
		Command<Integer> cmd = new RevokeTaskCmd(historyTaskId, processInstanceId, this.runtimeService, this.workflowService, this.historyService );
		Integer revokeFlag = this.processEngine.getManagementService().executeCommand(cmd);
		return revokeFlag;
	}

	/**
	 * 跳转（包括回退和向前）至指定活动节点
	 */
	@Override
	public void moveTo(String currentTaskId, String targetTaskDefinitionKey)
			throws Exception {
		TaskEntity taskEntity = (TaskEntity) this.taskService.createTaskQuery().taskId(currentTaskId).singleResult();
		moveTo(taskEntity, targetTaskDefinitionKey);
		
	}

	/**
	 * 跳转（包括回退和向前）至指定活动节点
	 */
	@Override
	public void moveTo(TaskEntity currentTaskEntity,
			String targetTaskDefinitionKey) throws Exception {
		ProcessDefinitionEntity pde = (ProcessDefinitionEntity) ((RepositoryServiceImpl)this.repositoryService).getDeployedProcessDefinition(currentTaskEntity.getProcessDefinitionId()); 
		ActivityImpl activity = (ActivityImpl) pde.findActivity(targetTaskDefinitionKey);

		moveTo(currentTaskEntity, activity);
	}
	
	private void moveTo(TaskEntity currentTaskEntity, ActivityImpl activity)
	{
		Command<Void> deleteCmd = new DeleteActiveTaskCmd(currentTaskEntity, "jump", true);
		Command<Void> StartCmd = new StartActivityCmd(currentTaskEntity.getExecutionId(), activity);
		this.processEngine.getManagementService().executeCommand(deleteCmd);
		this.processEngine.getManagementService().executeCommand(StartCmd);
	}

	@Override
	public void addProcessByDynamic() throws Exception {
		 /*this.repositoryService.deleteDeployment("5003", true);
		 this.repositoryService.deleteDeployment("5007", true);*/
		
		final String PROCESSID ="process_test_1";
		BpmnModel model = new BpmnModel();
		Process process = new Process();
		
		process.setId(PROCESSID);
		process.setName("动态流程测试");
		
		process.addFlowElement(createStartEvent());
		process.addFlowElement(createUserTask("userTask1", "用户任务1"));
		process.addFlowElement(createExclusiveGateway("gateway1"));
		process.addFlowElement(createUserTask("userTask2", "用户任务2"));
		process.addFlowElement(createExclusiveGateway("gateway2"));
		process.addFlowElement(createUserTask("userTask3", "用户任务3"));
		process.addFlowElement(createEndEvent());
		
		process.addFlowElement(createSequenceFlow("startEvent", "userTask1", "flow1", "", ""));
		process.addFlowElement(createSequenceFlow("userTask1", "gateway1", "flow2", "", ""));
		process.addFlowElement(createSequenceFlow("gateway1", "userTask2", "flow3", "同意", "${isPass}"));
		process.addFlowElement(createSequenceFlow("gateway1", "userTask3", "flow4", "不同意", "${!isPass}"));
		process.addFlowElement(createSequenceFlow("userTask2", "endEvent", "flow5", "", ""));
		process.addFlowElement(createSequenceFlow("userTask3", "gateway2", "flow6", "", ""));
		process.addFlowElement(createSequenceFlow("gateway2", "userTask1", "flow7", "同意", "${reApply}"));
		process.addFlowElement(createSequenceFlow("gateway2", "endEvent", "flow8", "结束", "${!reApply}"));
				
		model.addProcess(process);
		
		// 生成流程图片信息
		BpmnAutoLayout bpmnAutoLayout = new BpmnAutoLayout(model);
		bpmnAutoLayout.execute();
		
		// 部署流程
		Deployment deployment = this.repositoryService.createDeployment().addBpmnModel(PROCESSID+".bpmn", model).name("动态流程测试").deploy();
		
		/*// 启动流程
		ProcessInstance processInstance = this.runtimeService.startProcessInstanceByKey(PROCESSID); 
		
		// 导出流程图片
		InputStream processDiagram = this.repositoryService.getProcessDiagram(processInstance.getProcessDefinitionId());  
		FileUtils.copyInputStreamToFile(processDiagram, new File("D:/deployments/"+PROCESSID+".png"));  
		
		// 导出流程文件(BPMN xml)
		InputStream processBpmn = this.repositoryService.getResourceAsStream(deployment.getId(), PROCESSID+".bpmn");  
		FileUtils.copyInputStreamToFile(processBpmn,new File("D:/deployments/"+PROCESSID+".bpmn"));*/
		
	}

	/**
	 * 创建开始节点
	 * @return
	 */
	protected static StartEvent createStartEvent() {
		StartEvent startEvent = new StartEvent();
		startEvent.setId("startEvent");
		startEvent.setName("start");
		startEvent.setInitiator("startUserId");
		return startEvent;
	}
	
	/**
	 * 创建结束节点
	 * @return
	 */
	protected static EndEvent createEndEvent() {
		EndEvent endEvent = new EndEvent();
		endEvent.setId("endEvent");
		endEvent.setName("end");
		return endEvent;
	}
	
	/**
	 * 创建用户任务节点
	 * @param id
	 * @param name
	 * @return
	 */
	protected static UserTask createUserTask(String id, String name) {
		List<ActivitiListener> taskListeners = new ArrayList<ActivitiListener>();
		ActivitiListener listener = new ActivitiListener();
		listener.setId("");
		listener.setEvent("create");
		listener.setImplementationType("delegateExpression");
		listener.setImplementation("${userTaskListener}");
		taskListeners.add(listener);
		
		UserTask userTask = new UserTask();
		userTask.setId(id);
		userTask.setName(name);
		userTask.setTaskListeners(taskListeners);
		userTask.setDocumentation("");		// 说明
		return userTask;
	}
	
	/**
	 * 创建节点间的连线
	 * @param from
	 * @param to
	 * @param id
	 * @param name
	 * @param conditionExpression
	 * @return
	 */
	protected static SequenceFlow createSequenceFlow(String from, String to,String id,String name,String conditionExpression) {
		SequenceFlow flow = new SequenceFlow();
		flow.setId(id);
		flow.setName(name);
		flow.setSourceRef(from);
		flow.setTargetRef(to);
		if(StringUtils.isNotBlank(conditionExpression)) {
			flow.setConditionExpression(conditionExpression);
		}
		return flow;
	}
	
	/**
	 * 创建排他网关
	 * @param id
	 * @return
	 */
	protected static ExclusiveGateway createExclusiveGateway(String id) {
		ExclusiveGateway gateway = new ExclusiveGateway();
		gateway.setId(id);
		return gateway;
	}
}
