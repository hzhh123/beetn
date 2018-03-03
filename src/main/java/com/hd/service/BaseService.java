/**
 * Project Name:SpringOA
 * File Name:IBaseService.java
 * Package Name:com.zml.oa.service
 * Date:2014-11-9下午5:37:16
 *
 */
package com.hd.service;

import com.hd.util.PageInfo;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: IBaseService
 * @Description:IBaseService
 * @author: zml
 * @date: 2014-11-9 下午5:37:16
 *
 */
public interface BaseService<T, PK extends Serializable> {

	 public List<T> getAllList(String tableSimpleName) throws Exception;
	 
	 public T getUnique(String tableSimpleName, String[] columns, String[] values) throws Exception;
	 
	 public List<T> findByWhere(String tableSimpleName, String[] columns, String[] values) throws Exception;
	 
	 public List<T> getCount(String tableSimpleName) throws Exception;
	 
	 public Serializable add(T bean) throws Exception;
	 
	 public void saveOrUpdate(T bean) throws Exception;

	 public void delete(T bean) throws Exception;
	 
	 public void update(T bean) throws Exception;
	 
	 public T getBean(final Class<T> obj, PK id) throws Exception;
	 public List<T> findByPage(String tableSimpleName,String[] columns,String[] values, PageInfo info) throws Exception;




}
