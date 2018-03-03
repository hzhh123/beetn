package com.hd.service;

import java.util.List;

import com.hd.entity.Organization;
import com.hd.entity.User;
import com.hd.util.PageInfo;
import com.hd.util.jstree.JsTreeNode;
import com.hd.util.result.OrgUserTree;

/**
*@author hzhh123
*@time 2018年1月15日下午5:06:23 
**/
public interface OrganizationService {
	 void save(Organization organization);
	 void delete(Long id);
	 void deleteBatch(String ids);
	 void update(Organization organization);
	 List<JsTreeNode> selectJstree();
	 Organization get(Long id);
	 PageInfo findDataGrid(Integer page, Integer limit, String orgname, String id);
	 List<Organization>findAll();
	 List<Organization>getSubOranization(Long pid);
	 Organization getTopOrg();//查询总机构
	//通过organizationId查询用户
	List<User>getUserByOrganizationId(Long orgId);
	//部门-用户树
	List<OrgUserTree>findUserAndOrgByPId(Long pid);
	List<OrgUserTree>findUserAndOrgTree(Long pid);
}
