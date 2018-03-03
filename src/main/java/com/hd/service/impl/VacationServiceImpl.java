package com.hd.service.impl;

import com.hd.dao.BaseDao;
import com.hd.entity.Vacation;
import com.hd.service.BaseService;
import com.hd.service.VacationService;
import com.hd.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class VacationServiceImpl implements VacationService {
	
	@Autowired 
	private BaseService<Vacation,Long> baseService;
	@Autowired
	private BaseDao<Vacation,Long>vacationDao;
	
	@Override
	public Serializable doAdd(Vacation vacation) throws Exception {
		return this.baseService.add(vacation);
	}

	@Override
	public void doUpdate(Vacation vacation) throws Exception {
		this.baseService.update(vacation);
	}

	@Override
	public void doDelete(Vacation vacation) throws Exception {
		this.baseService.delete(vacation);
	}

	@Override
	public List<Vacation> toList(Long userId,PageInfo info) throws Exception {
		List<Vacation> list = this.baseService.findByPage("Vacation", new String[]{"userId"}, new String[]{userId.toString()},info);
		return list;
	}

	@Override
	public Vacation findById(Long id) throws Exception {
		return this.baseService.getUnique("Vacation", new String[]{"id"}, new String[]{id.toString()});
	}

	@Override
	public List<Vacation> findByStatus(Long userId, String status, PageInfo info) throws Exception {
		List<Vacation> list = this.baseService.findByPage("Vacation", new String[]{"userId","status"}, new String[]{userId.toString(), status}, info);
		return list;
	}

	@Override
	public void findDataGrid(PageInfo info) {
		String hql="from Vacation where 1=1 ";
		List<Object>params=new ArrayList<Object>();
		Map<String,Object>condition=info.getCondition();
		if(condition.get("userId")!=null){
			hql+=" userId=?";
			params.add(condition.get("userId"));
		}
		List<Vacation>vacations=vacationDao.findPage(hql,info.getPage(),info.getLimit(),params);
		info.setData(vacations);
		int totals=vacationDao.findByList(hql,params).size();
		info.setCount(totals);
	}
}
