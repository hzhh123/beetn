package com.hd.service.impl;

import com.hd.dao.BaseDao;
import com.hd.entity.Organization;
import com.hd.entity.User;
import com.hd.service.OrganizationService;
import com.hd.util.PageInfo;
import com.hd.util.jstree.JsTreeNode;
import com.hd.util.result.OrgUserTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
*@author hzhh123
*@time 2018年1月15日下午5:07:19 
**/
@Service("organizationService")
public class OrganizationServiceImpl implements OrganizationService {
	@Autowired
	private BaseDao<Organization,Long>organizationDao;
	@Autowired
	private BaseDao<User,Long>userDao;
	@Override
	public void save(Organization organization) {
		organizationDao.save(organization);
	}
	@Override
	public void delete(Long id) {
		//设置用户机构部门为空
		String hql="update User set orgid=null where orgid=?";
		userDao.execute(hql, id);
		organizationDao.delete(Organization.class, id);
	}
	@Override
	public List<JsTreeNode> selectJstree() {
		String hql="from Organization";
		List<Organization>organizations=organizationDao.find(hql);
		List<JsTreeNode>treeList=new ArrayList<JsTreeNode>();
		if(organizations!=null){
			for (Organization organization:organizations){
				JsTreeNode node=new JsTreeNode();
				node.setId(organization.getId().toString());
				node.setIcon("fa fa-sitemap blue");
				node.setParent(organization.getPid()==null?"#":organization.getPid().toString());
				node.setText(organization.getName());
				treeList.add(node);
			}
		}
		return treeList;
	}
	@Override
	public Organization get(Long id) {
		return organizationDao.get(Organization.class, id);
	}
	@Override
	public void deleteBatch(String ids) {
		if(!ids.trim().equals("")) {
			List<Long>idList=new ArrayList<Long>();
			String[] ids_=ids.split(",");
			for(String id:ids_) {
				String hql="update User set orgid=null where orgid=?";
				userDao.execute(hql, Long.valueOf(id));
				idList.add(Long.valueOf(id));
			}
			organizationDao.deleteBatch2(Organization.class,idList);
		}
	}
	@Override
	public void update(Organization organization) {
		organizationDao.update(organization);
	}
	@Override
	public PageInfo findDataGrid(Integer page, Integer limit, String orgname,String id) {
		PageInfo info=new PageInfo(page,limit);
		List<Organization>organizations=new ArrayList<Organization>();
		String hql="from Organization where 1=1";
		if(orgname!=null&&!orgname.equals("")) {
			hql+=" and name like '%"+orgname+"%'";
		}
		if(id!=null&&!id.equals("")) {
			hql+=" and id="+Long.valueOf(id);
		}
		organizations=organizationDao.findPage(hql, page, limit);
		for(Organization organization:organizations) {
			if(organization.getPid()!=null) {
				Organization vo=get(organization.getPid());
				organization.setParentName(vo.getName());
			}
		}
		info.setData(organizations);
		int count=organizationDao.find(hql).size();
		info.setCount(count);
		return info;
	}
	@Override
	public List<Organization> findAll() {
		String hql="from Organization";
		return organizationDao.find(hql);
	}

	/**
	 * 查询子机构
	 */
	@Override
	public List<Organization> getSubOranization(Long pid) {
		if(pid!=null) {
			String hql = "from Organization where pid=?";
			return organizationDao.find(hql, pid);
		}else{
			String hql = "from Organization where pid is null";
			return organizationDao.find(hql);
		}
	}

	/**
	 * 查询总机构
	 * @param
	 * @return
	 */
	@Override
	public Organization getTopOrg() {
		String hql="from Organization where pid=?";
		return organizationDao.find(hql,null).get(0);
	}

	/**
	 * 通过部门id查询用户
	 * @param orgId
	 * @return
	 */
	@Override
	public List<User> getUserByOrganizationId(Long orgId) {
		String hql="from User where orgid=?";
		return userDao.find(hql,orgId);
	}

	/**
	 * 部门-用户树
	 * @param pid
	 * @return
	 */
	@Override
	public List<OrgUserTree>findUserAndOrgTree(Long pid){
		List<OrgUserTree>trees=new ArrayList<OrgUserTree>();
		List<OrgUserTree> childList = findUserAndOrgByPId(pid);
		for (OrgUserTree entity: childList)
		{
			trees.add(entity);
			if(entity.getType().equals("0")) {
				trees.addAll(findUserAndOrgTree(Long.valueOf(entity.getId())));
			}
		}
		return trees;
	}
	@Override
	public List<OrgUserTree>findUserAndOrgByPId(Long pid){
		List<OrgUserTree>trees=new ArrayList<OrgUserTree>();
		List<User>users=getUserByOrganizationId(pid);
		if(users.size()>0){
			List<OrgUserTree>treeUsers=new ArrayList<OrgUserTree>();
			for (User user:users){
				OrgUserTree tree=new OrgUserTree();
				tree.setName(user.getName());
				tree.setId("u"+user.getId());
				tree.setIcon("/static/img/user.png");
				tree.setpId(pid.toString());
				tree.setType("1");
				treeUsers.add(tree);
			}
			trees.addAll(treeUsers);
		}
		List<Organization>organizations=getSubOranization(pid);
		if(organizations.size()>0){
			List<OrgUserTree>treeOrgs=new ArrayList<OrgUserTree>();
			for (Organization organization:organizations){
				OrgUserTree tree=new OrgUserTree();
				tree.setName(organization.getName());
				tree.setId(organization.getId().toString());
				tree.setIcon("/static/img/sitemap.png");
				tree.setpId(pid==null?"0":pid.toString());
				tree.setType("0");
				treeOrgs.add(tree);
			}
			trees.addAll(treeOrgs);
		}
		return trees;
	}
}
