package com.hd.service;


import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.hd.entity.Role;
import com.hd.entity.User;
import com.hd.util.PageInfo;
import com.hd.util.TreeNode;

/**
 * Created by Administrator on 2016/4/2.
 */
public interface UserService {
    //保存用户
    Serializable save(User user);
    //更新
    void update(User user);
    //通过id查询用户
    User getUser(Long id);
    User getUserById(Long id);
    //删除
    void delete(Long id);
    User getUserByUsernameAndPassword(User user);
    
    //添加用户-角色
    void saveUserRole(String roleIdList, Long userid);
    //获取菜单
    List<TreeNode>getResourceByUserid(Long userid, Integer resourceType);
    
    PageInfo findDataGrid(Integer page, Integer limit,User user);
	void deleteBatch(String ids);
	//获取用户关联角色
	List<Role> getRoles(Long id);
	List<User> findOfProject();

	List<User>findUserByName(String name);

	List<User>findUserByHeadPinyin(String pinyin);

	boolean valid(String username);

    /**
     * 添加用户并[同步其他数据库]
     * <ul>
     * <li>step 1: 保存系统用户，同时设置和部门的关系</li>
     * <li>step 2: 同步用户信息到activiti的identity.User，同时设置角色</li>
     * </ul>
     *
     * @param user              用户对象
     * @param roleIdList        角色ID集合
     * @param synToActiviti
     * 是否同步到Activiti数据库，通过配置文件方式设置，使用属性：account.user.add.syntoactiviti
     * @throws  Exception                       其他未知异常
     */
	public void doAdd(User user,List<Long>roleIdList,boolean synToActiviti) throws Exception;
    /**
     * 删除用户
     * @param userId        用户ID
     * @param synToActiviti
     * 是否同步到Activiti数据库，通过配置文件方式设置，使用属性：account.user.add.syntoactiviti
     * @throws Exception
     */
	public void doDelete(Long userId,boolean synToActiviti) throws Exception;
    /**
     * 批量删除用户
     * @param ids      用户IDs
     * @param synToActiviti
     * 是否同步到Activiti数据库，通过配置文件方式设置，使用属性：account.user.add.syntoactiviti
     * @throws Exception
     */
    public void doDeleteBatch(String ids,boolean synToActiviti) throws Exception;

    /**
     * 同步用户、角色数据到工作流
     * @throws Exception
     */
    public void synAllUserAndRoleToActiviti() throws Exception;
    /**
     * 删除工作流引擎Activiti的用户、角色以及关系
     * @throws Exception
     */
    public void deleteAllActivitiIdentifyData() throws Exception;



}
