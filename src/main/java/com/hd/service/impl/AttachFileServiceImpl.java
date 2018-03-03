package com.hd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hd.dao.BaseDao;
import com.hd.entity.AttachFile;
import com.hd.service.AttachFileService;

/**
*@author hzhh123
*@time 2018年1月18日下午2:18:58 
**/
@Service("attachFileService")
public class AttachFileServiceImpl implements AttachFileService {
	@Autowired
	private BaseDao<AttachFile,Long>attachfileDao;
	@Override
	public void save(AttachFile attachFile) {
		attachfileDao.save(attachFile);
	}

	@Override
	public void delete(Long id) {
		attachfileDao.delete(AttachFile.class, id);

	}

}
