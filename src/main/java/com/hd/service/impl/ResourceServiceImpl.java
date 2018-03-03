package com.hd.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hd.dao.BaseDao;
import com.hd.entity.Resource;
import com.hd.entity.RoleResource;
import com.hd.service.ResourceService;
import com.hd.util.JsonUtil;
import com.hd.util.jstree.JsTreeNode;

/**
*@author hzhh123
*@time 2018年1月15日上午10:38:51 
**/
@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {
	 @Autowired
	 private BaseDao<Resource,Long> resourceDao;
	 @Autowired
	 private BaseDao<RoleResource,Long> roleResourceDao;
	@Override
	public void save(Resource resource) {
		resource.setCreateTime(new Date());
		resourceDao.save(resource);
	}

	@Override
	public void delete(Long id) {
		String hql="delete from RoleResource where resourceId=?";
		roleResourceDao.execute(hql, id);
		resourceDao.delete(Resource.class, id);
	}
/*
	@Override
	public void saveRoleResource(String resourceList, Long roleid) {
		String hql="from RoleResource where roleId=?";
		List<RoleResource> rrList=roleResourceDao.find(hql, roleid);
		List<Long>rList=new ArrayList<Long>();
		if(rrList.size()>0) {
			for(RoleResource rr:rrList) {
				rList.add(rr.getResourceId());
			}
		}
		if(!resourceList.equals("")) {
			if(!resourceList.equals(rList.toString())) {
				roleResourceDao.deleteBatch(rrList);
				String[]rsList=resourceList.split(",");
				for(String r:rsList) {
					RoleResource o=new RoleResource();
					o.setRoleId(roleid);
					o.setResourceId(Long.valueOf(r));
					roleResourceDao.save(o);
				}
			}
		}
		
	}*/

	@Override
	public Resource get(Long id) {
		Resource resource=resourceDao.get(Resource.class, id);
		if(resource.getPid()!=null&&resource.getPid()!=0) {
			Resource res=resourceDao.get(Resource.class, resource.getPid());
			resource.setParentName(res.getName());
		}
		return resource;
	}

	@Override
	public void update(Resource resource) {
		Resource vo=get(resource.getId());
		resource.setCreateTime(vo.getCreateTime());
		resourceDao.update(resource);
	}

	@Override
	public String getTreeGrid() {
		String hql="from Resource";
		List<Resource>resources=resourceDao.find(hql);
		for (Resource resource:resources){
			if(resource.getPid()==null) {
				//pid=null是根节点，88行会将pid=null修改为0
				resource.setPid(0L);
			}
		}
		return toTreeJson(resources);
	}
	public  String toTreeJson(List<Resource> data)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(treeJson(data, "0"));
		sb.append("]");
		return sb.toString();
	}
	private  String treeJson(List<Resource> data, String parentId)
	{
		StringBuilder sb = new StringBuilder();
		List<Resource> childList = findAll(data,parentId);
		for (Resource entity: childList)
		{
			String strJson = JsonUtil.toJson(entity);
			sb.append(strJson);
			sb.append(treeJson(data, entity.getId().toString()));
		}
		return sb.toString().replace("}{", "},{");
	}

	private  List<Resource>findAll(List<Resource> data, String  pid){
		List<Resource>resources=new ArrayList<Resource>();
		for(Resource resource:data){
			if(resource.getPid().toString().equals(pid)){
				resources.add(resource);
			}
		}
		return resources;
	}

	@Override
	public List<JsTreeNode> selectJstree() {
		String hql="from Resource";
		List<Resource>resources=resourceDao.find(hql);
		List<JsTreeNode>treeList=new ArrayList<JsTreeNode>();
		if(resources!=null&&resources.size()>0){
			for (Resource resource:resources){
				JsTreeNode node=new JsTreeNode();
				node.setId(resource.getId().toString());
				node.setIcon("fa fa-folder orange");
				if(resource.getPid()==null||resource.getPid()==0) {
					node.setParent("#");
				}else {
					node.setParent(resource.getPid().toString());
				}
				node.setText(resource.getName());
				treeList.add(node);
			}
		}
		return treeList;
	}

	
}
