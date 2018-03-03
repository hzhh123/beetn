package com.hd.service;

import com.hd.entity.Icon;
import com.hd.util.PageInfo;

/**
*@author hzhh123
*@time 2018年1月18日上午11:54:40 
**/

public interface IconService {
	void save(Icon icon);

	PageInfo findDataGrid(Integer page, Integer limit, String sourcetype);
}
