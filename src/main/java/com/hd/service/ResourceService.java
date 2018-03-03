package com.hd.service;

import java.util.List;

import com.hd.entity.Resource;
import com.hd.entity.User;
import com.hd.util.Tree;
import com.hd.util.jstree.JsTreeNode;

/**
 * @author hzhh123
 * @time 2018年1月15日上午10:30:53
 **/
public interface ResourceService {
	void save(Resource resource);

	void delete(Long id);

	// void saveRoleResource(String resourceList,Long roleid);
	Resource get(Long id);

	void update(Resource resource);

	String getTreeGrid();
	
	List<JsTreeNode> selectJstree();
	
}
