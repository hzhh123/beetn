package com.hd.service.impl;

import cn.hutool.json.JSON;
import com.hd.dao.BaseDao;
import com.hd.entity.*;
import com.hd.entity.vo.Menu;
import com.hd.service.ActivitiBaseService;
import com.hd.service.BaseService;
import com.hd.service.RoleService;
import com.hd.service.UserService;
import com.hd.util.*;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.UserQuery;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Administrator on 2016/4/2.
 */
@Service("userService")
public class UserServiceImpl implements UserService{
	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class.getName());
    @Autowired
    private BaseDao<User,Long> userDao;
    @Autowired
    private BaseDao<UserRole,Long> userRoleDao;
    @Autowired
    private BaseDao<RoleResource,Long>roleResourceDao;
    @Autowired
    private BaseDao<Resource,Long>resourceDao;
    @Autowired
    private BaseDao<Organization,Long>organizationDao;
    @Autowired
    private BaseDao<Role,Long>roleDao;

    @Autowired
	private IdentityService identityService;
    @Autowired
	private RoleService roleService;
    @Autowired
	private BaseService<User,Long>baseService;

    @Autowired
	private ActivitiBaseService activitiBaseService;

    //保存用户
    public Serializable save(User user) {
    	user.setCreateTime(new Date());
    	//设置拼音首字母
		user.setHeadPinyin(GetPinyin.getPinyinHeadChar(user.getName()));
        return userDao.save(user);
    }

    public User getUser(Long id) {
        String hql="select u from User u where u.id=?";
        return userDao.get(hql,id);
    }

    public User getUserById(Long id){
        return userDao.get(User.class,id);
    }

    public void delete(Long id) {
        userDao.delete(User.class,id);
    }
    


	@Override
	public void update(User user) {
		User u=getUser(user.getId());
		user.setCreateTime(u.getCreateTime());
		//设置拼音首字母
		user.setHeadPinyin(GetPinyin.getPinyinHeadChar(user.getName()));
		userDao.update(user);
	}

	@Override
	public User getUserByUsernameAndPassword(User user) {
		// TODO Auto-generated method stub
		String hql="from User where username=? and password=?";
		return userDao.get(hql, user.getUsername(),user.getPassword());
	}

	@Override
	public void saveUserRole(String roleIdList, Long userid) {
		String hql="from UserRole where userId=?";
		List<UserRole> urList=userRoleDao.find(hql, userid);
		List<Long>rList=new ArrayList<Long>();
		if(urList.size()>0) {
			for(UserRole ur:urList) {
				rList.add(ur.getRoleId());
			}
		}
		if(!roleIdList.equals("")) {
			if(!roleIdList.equals(rList.toString())) {
				userRoleDao.deleteBatch(urList);
				String[]rsList=roleIdList.split(",");
				for(String r:rsList) {
					UserRole o=new UserRole();
					o.setRoleId(Long.valueOf(r));
					o.setUserId(userid);
					userRoleDao.save(o);
				}
			}
		}else{
			hql="delete from UserRole where userId=?";
			userRoleDao.execute(hql,userid);
		}
	}

	@Override
	public List<TreeNode> getResourceByUserid(Long userid,Integer resourceType) {
		List<TreeNode>nodes=new ArrayList<TreeNode>();
		List<Menu>menuList=new ArrayList<Menu>();
		String hql="from UserRole where userId=?";
		List<UserRole>urList=userRoleDao.find(hql,userid);
		List<RoleResource>rrList=new ArrayList<RoleResource>();
		Set<Resource>resSet=new HashSet<Resource>();
		if(urList.size()>0) {
			for(UserRole ur:urList) {
				hql="from RoleResource where roleId=?";
				rrList.addAll(roleResourceDao.find(hql, ur.getRoleId()));
			}
			if(rrList.size()>0) {
				for(RoleResource rr:rrList) {
					Resource res=new Resource();
					if(resourceType!=null) {
						hql="from Resource where id=? and resourceType=? order by seq";
						res=resourceDao.get(hql, rr.getResourceId(),resourceType);
					}else {
						res=resourceDao.get(Resource.class, rr.getResourceId());
					}
					resSet.add(res);
				}
			}
		}
		if(resSet.size()>0) {
			List<Resource>resources=new ArrayList<Resource>();
			for(Resource resource : resSet) {
				resources.add(resource);
			}
			ResourceComparator comp=new ResourceComparator();
			Collections.sort(resources,comp);
			for (Resource resource : resources) {
				Menu menu=new Menu();
				menu.setId(resource.getId().toString());
				menu.setIcon(resource.getIcon());
				menu.setUrl(resource.getUrl());
				menu.setIsHeader("");
				menu.setName(resource.getName());
				menu.setOrder(""+resource.getSeq());
				menu.setParentId(resource.getPid()==null?"0":resource.getPid().toString());
				menu.setLevel(resource.getLevel());
				menu.setTarget(resource.getTarget());
				menuList.add(menu);
			}
			nodes=TreeNodeUtil.toListNode(menuList);
			return TreeNodeUtil.tree(nodes, "0");
		}
		return null;
	}

	@Override
	public PageInfo findDataGrid(Integer page, Integer limit, User user) {
		PageInfo info=new PageInfo(page,limit);
		List<User>users=new ArrayList<User>();
		
		String hql="from User where 1=1";
		if(user.getName()!=null&&!user.getName().equals("")) {
			hql+=" and name like '%"+user.getName()+"%'";
		}
		if(user.getOrgid()!=null&&!user.getOrgid().equals("")) {
			  hql+=" and orgid="+user.getOrgid();
		}
		users=userDao.findPage(hql,page,limit);
			
			for(User vo:users) {
				//设置部门
				if(vo.getOrgid()!=null) {
					Organization org=organizationDao.get(Organization.class, vo.getOrgid());
					vo.setOrgName(org.getName());
				}
				//设置角色
				String hql1="from UserRole where userId=?";
				List<UserRole>urList=userRoleDao.find(hql1,vo.getId());
				String roleNameList="";
				if(urList.size()>0) {
					for(UserRole ur:urList) {
						Role role=roleDao.get(Role.class, ur.getRoleId());
						roleNameList+=role.getDescription()+",";
					}
					roleNameList=roleNameList.substring(0, roleNameList.length()-1);
					vo.setRoleNameList(roleNameList);
				}
			}
			info.setData(users);
			int count=userDao.find(hql).size();
			info.setCount(count);
		return info;
	}

	@Override
	public void deleteBatch(String ids) {
		if(!ids.trim().equals("")) {
			List<Long>idList=new ArrayList<Long>();
			String[] ids_=ids.split(",");
			for(String id:ids_) {
				idList.add(Long.valueOf(id));
			}
			userDao.deleteBatch2(User.class,idList);
		}
		
		
	}

	@Override
	public List<Role> getRoles(Long id) {
		String hql1="from UserRole where userId=?";
		List<UserRole>urList=userRoleDao.find(hql1,id);
		List<Role>roles=new ArrayList<Role>();
		if(urList.size()>0) {
			for(UserRole ur:urList) {
				Role role=roleDao.get(Role.class, ur.getRoleId());
				roles.add(role);
			}
		}
		return roles;
	}

	@Override
	public List<User> findOfProject() {
		String hql="from User where pjId is not null";
		return userDao.find(hql);
	}

	@Override
	public List<User> findUserByName(String name) {
    	List<User>users=new ArrayList<User>();
    	if(!name.equals("")){
    		String hql="from User where name like ?";
    		users=userDao.find(hql,"%".concat(name.concat("%")));
		}
		return users;
	}

	@Override
	public List<User> findUserByHeadPinyin(String pinyin) {
		List<User>users=new ArrayList<User>();
		if(!pinyin.equals("")){
			String hql="from User where headPinyin like ?";
			users=userDao.find(hql,"%".concat(pinyin.concat("%")));
		}
		return users;
	}

	@Override
	public boolean valid(String username) {
		boolean b=true;//true-可用，false-占用
		if(username!=null&&!username.trim().equals("")) {
			String hql="from User where username=?";
			//根据项目名称查询，有结果表示项目名称被占用
			List<User>users=userDao.find(hql,username);
			if(users.size()>0) {
				b=false;
			}
		}
		return b;
	}


	@Override
	public void doAdd(User user, List<Long> roleIdList, boolean synToActiviti) throws Exception {
    	//添加用户
		user.setCreateTime(new Date());
		Serializable userId=baseService.add(user);
		// 同步数据到Activiti Identify模块
		if(synToActiviti){
			UserQuery userQuery=identityService.createUserQuery();
			List<org.activiti.engine.identity.User>activitiUsers=userQuery.userId(userId.toString()).list();
			if (activitiUsers.size() == 1) {
				updateActivitiData(user, roleIdList, activitiUsers.get(0));
			} else if (activitiUsers.size() > 1) {
				String errorMsg = "发现重复用户：id=" + userId;
				logger.error(errorMsg);
				throw new RuntimeException(errorMsg);
			} else {
				newActivitiUser(user, roleIdList);
			}
		}
	}

	/**
	 * 添加工作流用户以及角色
	 * @param user      用户对象{@link User}
	 * @param roleIds   用户拥有的角色ID集合
	 */
	private void newActivitiUser(User user, List<Long> roleIds) {
		String userId = user.getId().toString();
		// 添加用户
		saveActivitiUser(user);
		// 添加membership
		addMembershipToIdentify(roleIds, userId);
	}
	/**
	 * 添加一个用户到Activiti {@link org.activiti.engine.identity.User}
	 * @param user  用户对象, {@link User}
	 */
	private void saveActivitiUser(User user) {
		String userId = user.getId().toString();
		org.activiti.engine.identity.User activitiUser = identityService.newUser(userId);
		cloneAndSaveActivitiUser(user, activitiUser);
		logger.debug("add activiti user: {}", ToStringBuilder.reflectionToString(activitiUser));
	}

	/**
	 * 添加Activiti Identify的用户-组关系
	 * @param roleIds   角色ID集合
	 * @param userId    用户ID
	 */
	private void addMembershipToIdentify(List<Long> roleIds, String userId) {
		if(roleIds.size()>0) {
			for (Long roleId : roleIds) {
				Role role = roleDao.get(Role.class, roleId);
				logger.debug("add role to activit: {}", role);
				identityService.createMembership(userId, role.getName());
			}
		}
	}

	/**
	 * 更新工作流用户以及角色
	 * @param user          用户对象{@link User}
	 * @param roleIds       用户拥有的角色ID集合
	 * @param activitiUser  Activiti引擎的用户对象，{@link org.activiti.engine.identity.User}
	 */
	private void updateActivitiData(User user, List<Long> roleIds, org.activiti.engine.identity.User activitiUser) {

		String userId = user.getId().toString();

		// 更新用户主体信息
		cloneAndSaveActivitiUser(user, activitiUser);

		// 删除用户的membership
		List<Group> activitiGroups = identityService.createGroupQuery().groupMember(userId).list();
		for (Group group : activitiGroups) {
			logger.debug("delete group from activit: {}", ToStringBuilder.reflectionToString(group));
			identityService.deleteMembership(userId, group.getId());
		}

		// 添加membership
		addMembershipToIdentify(roleIds, userId);
	}

	/**
	 * 使用系统用户对象属性设置到Activiti User对象中
	 * @param user          系统用户对象
	 * @param activitiUser  Activiti User
	 */
	private void cloneAndSaveActivitiUser(User user, org.activiti.engine.identity.User activitiUser) {
		activitiUser.setFirstName(user.getUsername());
		activitiUser.setLastName(org.apache.commons.lang.StringUtils.EMPTY);
		activitiUser.setPassword(org.apache.commons.lang.StringUtils.EMPTY);
		activitiUser.setEmail(org.apache.commons.lang.StringUtils.EMPTY);
		identityService.saveUser(activitiUser);
	}
	@Override
	public void doDelete(Long userId, boolean synToActiviti) throws Exception {
		User user=getUser(userId);
		if(user==null){
			throw new ServiceException("删除用户时，找不到ID为" + userId + "的用户");
		}
		/**
		 * 同步删除Activiti User Group
		 */
		if (synToActiviti) {
			// 同步删除Activiti User,会自动删除membership对应的信息
			identityService.deleteUser(userId.toString());
		}
		//删除本地用户
		this.delete(userId);
	}

	@Override
	public void synAllUserAndRoleToActiviti() throws Exception {
		// 清空工作流用户、角色以及关系
		deleteAllActivitiIdentifyData();

		// 复制角色数据
		synRoleToActiviti();

		// 复制用户以及关系数据
		synUserWithRoleToActiviti();
	}

	@Override
	public void deleteAllActivitiIdentifyData() throws Exception {
		this.activitiBaseService.deleteAllMemerShip();
		this.activitiBaseService.deleteAllRole();
		this.activitiBaseService.deleteAllUser();
		System.out.println("111111111111");
	}

	public List<Role>findAllRoleByuserId(Long userid){
		List<Role>roles=new ArrayList<Role>();
		String hql="from UserRole where userId=?";
		List<UserRole>userRoles=userRoleDao.find(hql,userid);
		if(userRoles.size()>0){
			for (UserRole ur:userRoles) {
				Role role = roleDao.get(Role.class, ur.getRoleId());
				roles.add(role);			}
		}
		return roles;
	}
	/**
	 * 复制用户以及关系数据
	 */
	private void synUserWithRoleToActiviti() {
		String hql="from User";
		List<User> allUser =userDao.find(hql);
		for (User user : allUser) {
			String userId = user.getId().toString();

			// 添加一个用户到Activiti
			saveActivitiUser(user);

			// 角色和用户的关系
			List<Role> roleList = findAllRoleByuserId(user.getId());
			if(roleList.size()>0){
				for (Role role : roleList) {
					identityService.createMembership(userId, role.getId().toString());
					logger.debug("add membership {user: {}, role: {}}", userId, role.getName());
				}
			}

		}
	}

	/**
	 * 同步所有角色数据到{@link Group}
	 * @throws Exception
	 */
	private void synRoleToActiviti() throws Exception {
		List<Role> allRoles = roleService.findAll();
		for (Role role : allRoles) {
			String groupId = role.getId().toString();
			org.activiti.engine.identity.Group identity_group = identityService.newGroup(groupId);
			identity_group.setName(role.getName());
			identity_group.setType(role.getType());
			identityService.saveGroup(identity_group);
		}
	}

	@Override
	public void doDeleteBatch(String ids, boolean synToActiviti) throws Exception {
		if(StringUtils.isNotBlank(ids)){
			String[] idArr=ids.split(",");
			for(String idS:idArr){
				doDelete(Long.valueOf(idS),synToActiviti);
			}
		}
	}
}
