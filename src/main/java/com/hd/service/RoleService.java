package com.hd.service;

import java.util.List;

import com.hd.entity.Resource;
import com.hd.entity.Role;
import com.hd.util.PageInfo;
import com.hd.util.Tree;

/**
*@author hzhh123
*@time 2018年1月15日上午10:30:16 
**/
public interface RoleService {
	 void save(Role role);
	 void delete(Long id);
	 List<Role> findAllByStatus(Integer status);
	 List<Role>findAll();
	 PageInfo findDataGrid(Integer page, Integer limit);
	 Role get(Long id);
	void update(Role role);
	//分配权限
	void addOrEditPermission(Long roleId, String resourceIds);
	List<Resource>findResourceListByRoleId(Long roleId);
	List<Long>selectResourceIdListByRoleId(Long roleId);
	List<Tree> selectTreeByRoleId(Long roleId);

	/**
	 * 同步角色到工作流
	 * @param role
	 */
	void doAdd(Role role);

	/**
	 * 删除角色，同时删除工作流角色
	 * @param roleId
	 */
	void doDelete(Long roleId);
	 
}
