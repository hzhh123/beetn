/**
 * Project Name:SpringOA
 * File Name:BaseServiceImpl.java
 * Package Name:com.zml.oa.service.impl
 * Date:2014-11-9下午5:42:11
 *
 */
package com.hd.service.impl;

import com.hd.dao.BaseDao;
import com.hd.service.BaseService;
import com.hd.util.PageInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @ClassName: BaseServiceImpl
 * @Description: BaseServiceImpl
 *
 */
@Service
public class BaseServiceImpl<T, PK extends Serializable> implements BaseService<T, PK> {
	private static final Logger logger = LogManager.getLogger(BaseServiceImpl.class.getName());
	
    @Autowired  
    private BaseDao<T,PK> baseDao;

	public BaseDao<T, PK> getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao<T, PK> baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<T> getAllList(String tableSimpleName) throws Exception{
		StringBuffer sff = new StringBuffer();  
        sff.append("select a from ").append(tableSimpleName).append(" a ");  
        List<T> list = this.baseDao.createQuery(sff.toString());  
        if( list.size()>0 ){
      	   return list;
         }else{
      	   return Collections.emptyList();
         }
	}

	@Override
	public T getUnique(String tableSimpleName, String[] columns, String[] values) throws Exception{
		StringBuffer sb = new StringBuffer();  
        sb.append("select a from ").append(tableSimpleName).append( " a where ");  
        if(columns.length==values.length){  
            for(int i = 0; i < columns.length; i++){  
                sb.append("a.").append(columns[i]).append("='").append(values[i]).append("'");  
                if(i < columns.length-1){  
                    sb.append(" and ");  
                }  
           }  
           T entity = this.baseDao.unique(sb.toString());  
           return entity; 
        }else{  
           logger.error("columns.length != values.length");
           return null;  
        } 
	}
	
	@Override
	public List<T> findByWhere(String tableSimpleName, String[] columns, String[] values) throws Exception{
		StringBuffer sb = new StringBuffer();  
        sb.append("select a from ").append(tableSimpleName).append( " a where ");  
        if(columns.length==values.length){  
            for(int i = 0; i < columns.length; i++){  
                sb.append("a.").append(columns[i]).append("='").append(values[i]).append("'");  
                if(i < columns.length-1){  
                    sb.append(" and ");  
                }  
           }  
           List<T> list = this.baseDao.createQuery(sb.toString());  
//         最好用JDK提供的Collections.emptyList()来返回一个空的集合对象 而不是 null
//         Collections.EMPTY_LIST 是返回一个空集合对象，emptyList()是对EMPTY_LIST做了一个泛型支持，具体看源码
//         Collections.EMPTY_LIST的返回值是一个不可变的空List，不能进行例如add的各种操作！
           if( list.size()>0 ){
        	   return list;
           }else{
        	   return Collections.emptyList();
           }
        }else{  
        	return Collections.emptyList();  
        } 
	}

	@Override
	public List<T> getCount(String tableSimpleName) throws Exception{
    	String hql = "select count(*) from " + tableSimpleName;
    	List<T> list = this.baseDao.createQuery(hql);
    	return list;
	}

	@Override
	public Serializable add(T bean) throws Exception{
		return this.baseDao.save(bean);
	}

	@Override
	public void saveOrUpdate(T bean) throws Exception{
		this.baseDao.saveOrUpdate(bean);
	}

	@Override
	public void delete(T bean) throws Exception{
		this.baseDao.delete(bean);
	}

	@Override
	public void update(T bean) throws Exception{
		this.baseDao.update(bean);
	}

	@Override
	public T getBean(Class<T> obj, PK id) throws Exception{
		return this.baseDao.get(obj, id);
	}

	@Override
	public List<T> findByPage(String tableSimpleName, String[] columns, String[] values, PageInfo info) throws Exception {
		int totalSum = 0;
		List<T> list = new ArrayList<T>();
		if(columns.length <= 0 && values.length <= 0){
			list = getAllList(tableSimpleName);
		}else{
			list = findByWhere(tableSimpleName, columns, values);
		}
		if(list!=null&&list.size()>0){
			totalSum = list.size();
		}
		//int[] pageParams = PaginationThreadUtils.setPage(totalSum);

		StringBuffer sb = new StringBuffer();
		sb.append("select a from ").append(tableSimpleName).append( " a where ");
		if(columns.length==values.length){
			for(int i = 0; i < columns.length; i++){
				sb.append("a.").append(columns[i]).append("='").append(values[i]).append("'");
				if(i < columns.length-1){
					sb.append(" and ");
				}
			}
			String hql = sb.toString();
			if(hql.endsWith("where ")){
				hql = hql.substring(0, hql.length()-6);
			}
			logger.info("findByPage: HQL: "+hql);
			list = this.baseDao.findPage(hql, info.getPage(), info.getLimit());
			if( list.size()>0 ){
				return list;
			}else{
				return Collections.emptyList();
			}
		}else{
			logger.info("findByPage: columns.length != values.length");
			return Collections.emptyList();
		}
	}


}
