package com.hd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hd.dao.BaseDao;
import com.hd.entity.Icon;
import com.hd.service.IconService;
import com.hd.util.PageInfo;

/**
*@author hzhh123
*@time 2018年1月18日上午11:55:19 
**/
@Service("iconService")
public class IconServiceImpl implements IconService {
	@Autowired
	private BaseDao<Icon,Long>iconDao;

	@Override
	public void save(Icon icon) {
		iconDao.save(icon);
		
	}

	@Override
	public PageInfo findDataGrid(Integer page, Integer limit, String sourcetype) {
		PageInfo info=new PageInfo(page,limit);
		List<Icon>icons=new ArrayList<Icon>();
		String hql="from Icon where sourcetype='"+sourcetype+"'";
		icons=iconDao.findPage(hql, page, limit);
		info.setData(icons);
		int count=iconDao.find(hql).size();
		info.setCount(count);
		return info;
	}

}
