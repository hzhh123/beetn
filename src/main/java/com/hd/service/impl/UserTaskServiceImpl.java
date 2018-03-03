package com.hd.service.impl;

import com.hd.dao.JdbcDao;
import com.hd.entity.UserTask;
import com.hd.service.BaseService;
import com.hd.service.UserTaskService;
import com.hd.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;


@Service
public class UserTaskServiceImpl implements UserTaskService {

	@Autowired 
	private BaseService<UserTask,Long> baseService;
	
	@Autowired
	protected JdbcDao jdbcDao;
	
	@Override
	public Serializable doAdd(UserTask userTask) throws Exception {
		return baseService.add(userTask);
	}

	@Override
	public void doUpdate(UserTask userTask) throws Exception {
		this.baseService.update(userTask);
	}

	@Override
	public void doDelete(UserTask userTask) throws Exception {
		this.baseService.delete(userTask);
	}

	@Override
	public List<UserTask> toList(String procDefKey, PageInfo info) throws Exception {
		List<UserTask> list = this.baseService.findByPage("UserTask", new String[]{"procDefKey"}, new String[]{procDefKey},info);
		return list;
	}

	@Override
	public UserTask findById(Long id) throws Exception {
		return this.baseService.getBean(UserTask.class, id);
	}

	@Override
	public Integer deleteAll() throws Exception {
		String sql = "delete from T_USER_TASK";
		return this.jdbcDao.delete(sql, null);
	}

	@Override
	public List<UserTask> findByWhere(String procDefKey) throws Exception {
		return this.baseService.findByWhere("UserTask", new String[]{"procDefKey"}, new String[]{procDefKey});
	}

	@Override
	public List<UserTask> getAll() throws Exception {
		return this.baseService.getAllList("UserTask");
	}

}
