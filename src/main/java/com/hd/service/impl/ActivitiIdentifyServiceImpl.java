package com.hd.service.impl;

import com.hd.dao.JdbcDao;
import com.hd.service.ActivitiBaseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ActivitiIdentifyServiceImpl implements ActivitiBaseService {

	private static final Logger logger = LogManager.getLogger(ActivitiIdentifyServiceImpl.class.getName());

	@Autowired
	protected JdbcDao jdbcDao;
	
	@Override
	public void deleteAllUser() {
		String sql = "delete from ACT_ID_USER";
		this.jdbcDao.delete(sql, null);
		logger.debug("deleted from activiti user.");
	}

	@Override
	public void deleteAllRole() {
		String sql = "delete from ACT_ID_GROUP";
		this.jdbcDao.delete(sql, null);
		logger.debug("deleted from activiti group.");
	}

	@Override
	public void deleteAllMemerShip() {
		String sql = "delete from ACT_ID_MEMBERSHIP";
		this.jdbcDao.delete(sql, null);
		logger.debug("deleted from activiti membership.");
	}

	@Override
	public void updateMembership(String userId, String groupId) throws Exception {
		String sql = "update ACT_ID_MEMBERSHIP set GROUP_ID_=:groupId where USER_ID_=:userId ";
		Map<String, Object> paramMap = new HashMap<String, Object>();  
	    paramMap.put("groupId", groupId);  
	    paramMap.put("userId", userId);  
		this.jdbcDao.saveOrUpdate(sql, paramMap);
		logger.debug("update ACT_ID_MEMBERSHIP.");
		
	}

}
