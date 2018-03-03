package com.hd.service;

import com.hd.entity.UserTask;
import com.hd.util.PageInfo;

import java.io.Serializable;
import java.util.List;


public interface UserTaskService {

	public Serializable doAdd(UserTask userTask) throws Exception;
	
	public void doUpdate(UserTask userTask) throws Exception;
	
	public void doDelete(UserTask userTask) throws Exception;
	
	public List<UserTask> toList(String procDefKey,PageInfo info) throws Exception;
	
	public Integer deleteAll() throws Exception; 
	
	public UserTask findById(Long id) throws Exception;
	
	public List<UserTask> findByWhere(String procDefKey) throws Exception;
	
	public List<UserTask> getAll() throws Exception;
	
}
