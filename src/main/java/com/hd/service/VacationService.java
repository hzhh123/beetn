package com.hd.service;


import com.hd.entity.Vacation;
import com.hd.util.PageInfo;

import java.io.Serializable;
import java.util.List;

public interface VacationService {

	public Serializable doAdd(Vacation vacation) throws Exception;
	
	public void doUpdate(Vacation vacation) throws Exception;
	
	public void doDelete(Vacation vacation) throws Exception;
	
	public List<Vacation> toList(Long userId,PageInfo info) throws Exception;
	
	public Vacation findById(Long id) throws Exception;
	
	public List<Vacation> findByStatus(Long userId, String status, PageInfo info) throws Exception;
	void findDataGrid(PageInfo info);
}
