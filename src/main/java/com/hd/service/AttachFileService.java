package com.hd.service;

import com.hd.entity.AttachFile;

/**
*@author hzhh123
*@time 2018年1月18日下午2:17:57 
**/
public interface AttachFileService {
	void save(AttachFile attachFile);
	void delete(Long id);
}
