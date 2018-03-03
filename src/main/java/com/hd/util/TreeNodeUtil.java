package com.hd.util;
import java.util.ArrayList;
import java.util.List;

import com.hd.entity.vo.Menu;


public class TreeNodeUtil {
	public static List<TreeNode> tree(List<TreeNode>nodes,String id) {
		//递归转化为树形
		 List<TreeNode> treeNodes=new ArrayList<TreeNode>();
	        for(TreeNode treeNode : nodes) {
	            TreeNode node=new TreeNode();
	            node.setId(treeNode.getId());
	            node.setName(treeNode.getName());
	            node.setIsHeader(treeNode.getIsHeader());
	            node.setOrder(treeNode.getOrder());
	            node.setUrl(treeNode.getUrl());
	            node.setIcon(treeNode.getIcon());
	            node.setParentId(treeNode.getParentId());
	            node.setLevel(treeNode.getLevel());
	            node.setTarget(treeNode.getTarget());
	            if(id.equals(treeNode.getParentId())){
	                node.setChildMenus(tree(nodes, node.getId()));
	                treeNodes.add(node);
	            }
	             
	        }
	        return treeNodes;
	}	
	

	//转化为TreeNode节点
	public static TreeNode toNode(Menu menu){
		TreeNode node=new TreeNode();
		node.setId(menu.getId());
		node.setName(menu.getName());
		node.setParentId(menu.getParentId());
		node.setOrder(menu.getOrder());
		node.setIsHeader(menu.getIsHeader());
		node.setUrl(menu.getUrl());
		node.setIcon(menu.getIcon());
		node.setLevel(menu.getLevel());
		node.setTarget(menu.getTarget());
		return node;
	}
	public static List<TreeNode>toListNode(List<Menu>menus){
		List<TreeNode>nodes=new ArrayList<TreeNode>();
		for(Menu menu:menus){
			nodes.add(toNode(menu));
		}
		return nodes;
	}
}
