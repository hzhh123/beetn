package com.hd.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.hd.util.*;
import org.activiti.engine.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hd.dao.BaseDao;
import com.hd.entity.Resource;
import com.hd.entity.Role;
import com.hd.entity.RoleResource;
import com.hd.entity.UserRole;
import com.hd.service.RoleService;

/**
*@author hzhh123
*@time 2018年1月15日上午10:38:09 
**/
@Service("roleService")
public class RoleServiceImpl implements RoleService {
	 @Autowired
	 private BaseDao<Role,Long> roleDao;
	 @Autowired
	 private BaseDao<UserRole,Long> userRoleDao;
	 @Autowired
	 private BaseDao<RoleResource,Long> roleResourceDao;
	 @Autowired
	 private BaseDao<Resource,Long> resourceDao;
	 @Autowired
	 private IdentityService identityService;
	@Override
	public void save(Role role) {
		roleDao.save(role);
	}

	@Override
	public void delete(Long id) {
		//删除用户-角色
		String hql1="delete from UserRole where roleId=?";
		userRoleDao.execute(hql1, id);
		//删除角色-资源
		String hql2="delete from RoleResource where roleId=?";
		roleResourceDao.execute(hql2, id);
		roleDao.delete(Role.class,id);
	}

	@Override
	public List<Role> findAllByStatus(Integer status) {
		List<Role>roles=new ArrayList<Role>();
		String hql="from Role where 1=1";
		if(status!=null) {
			hql+=" and status=?";
			roles=roleDao.find(hql, status);
		}else {
		 roles=roleDao.find(hql);
		}
		return roles;
	}

	@Override
	public PageInfo findDataGrid(Integer page, Integer limit) {
		PageInfo info=new PageInfo(page, limit);
		List<Role>roles=new ArrayList<Role>();
		String hql="from Role";
		roles=roleDao.findPage(hql, page, limit);
		info.setData(roles);
		int count=roleDao.find(hql).size();
		info.setCount(count);
		return info;
	}

	@Override
	public Role get(Long id) {
		return roleDao.get(Role.class, id);
	}

	@Override
	public void update(Role role) {
		roleDao.update(role);
	}

	@Override
	public void addOrEditPermission(Long roleId, String resourceIds) {
		List<Long>resourceIdList=selectResourceIdListByRoleId(roleId);
		if(resourceIdList.size()>0&&resourceIds.equals("")){
			//删除角色-资源
			String hql1="delete from RoleResource where roleId=?";
			roleResourceDao.execute(hql1, roleId);
		}
		if(resourceIdList.size()==0&&!resourceIds.equals("")){
			String resourceIdArr[]=resourceIds.split(",");
			for(String resourceId:resourceIdArr){
				RoleResource newRoleResource=new RoleResource();
				newRoleResource.setRoleId(roleId);
				newRoleResource.setResourceId(Long.valueOf(resourceId));
				roleResourceDao.save(newRoleResource);
			}
		}
		if(resourceIdList.size()>0&&!resourceIds.equals("")){
			if(! CollectionsUtil.compare(resourceIdList,StringUtils.str2ListLong(resourceIds))){
				//删除角色-资源
				String hql2="delete from RoleResource where roleId=?";
				roleResourceDao.execute(hql2, roleId);
				String resourceIdArr[]=resourceIds.split(",");
				for(String resourceId:resourceIdArr){
					RoleResource newRoleResource=new RoleResource();
					newRoleResource.setRoleId(roleId);
					newRoleResource.setResourceId(Long.valueOf(resourceId));
					roleResourceDao.save(newRoleResource);
				}
			}
		}
	}

	@Override
	public List<Resource> findResourceListByRoleId(Long roleId) {
		List<Resource>resources=new ArrayList<Resource>();
		String hql="from RoleResource where roleId=?";
		List<RoleResource>rrList=roleResourceDao.find(hql, roleId);
		if(rrList.size()>0) {
			for(RoleResource rr:rrList) {
				Resource vo=resourceDao.get(Resource.class,rr.getResourceId());
				resources.add(vo);
			}
		}
		return resources;
	}

	@Override
	public List<Long> selectResourceIdListByRoleId(Long roleId) {
		 List<Resource>resources=findResourceListByRoleId(roleId);
		 List<Long>resourceIds=new ArrayList<Long>();
		 if(resources.size()>0) {
			 for(Resource r:resources) {
				 resourceIds.add(r.getId());
			 }
		 }
		return resourceIds;
	}

	@Override
	public List<Tree> selectTreeByRoleId(Long roleId) {
		List<Long>resourceids=selectResourceIdListByRoleId(roleId);
		String hql="from Resource";
		List<Resource>resources=resourceDao.find(hql);
		List<Tree>treeList=new ArrayList<Tree>();
		for (Resource resource:resources){
			Tree tree=new Tree();
			tree.setId(resource.getId());
			tree.setpId(resource.getPid()==null?0:resource.getPid());
			tree.setName(resource.getName());
			boolean checked=false;
			if(resourceids.size()>0) {
				if (resourceids.contains(resource.getId())) {
					checked = true;
				}
			}
			tree.setChecked(checked);
			treeList.add(tree);
		}
		return treeList;
	}

	@Override
	public List<Role> findAll() {
		String hql="from Role";
		List<Role>roleList=roleDao.find(hql);
		return roleList;
	}

	@Override
	public void doAdd(Role role) {
		//保存角色
		save(role);
		//保存角色到工作流act_id_group表中
		String groupId = role.getId().toString();
		org.activiti.engine.identity.Group identity_group = identityService.newGroup(groupId);
		identity_group.setName(role.getName());
		identity_group.setType(role.getType());
		identityService.saveGroup(identity_group);
	}

	@Override
	public void doDelete(Long roleId) {
		delete(roleId);
		//删除工作流表中角色
		identityService.deleteGroup(roleId.toString());
	}
}
