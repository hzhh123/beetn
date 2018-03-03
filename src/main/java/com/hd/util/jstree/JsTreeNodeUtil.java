package com.hd.util.jstree;

import java.util.ArrayList;
import java.util.List;


public class JsTreeNodeUtil {
	public static List<JsTreeNode> tree(List<JsTreeNode>nodes, String id) {
		//递归转化为树形
		 List<JsTreeNode> treeNodes=new ArrayList<JsTreeNode>();
	        for(JsTreeNode treeNode : nodes) {
	            JsTreeNode node=new JsTreeNode();
	            node.setId(treeNode.getId());
	            node.setText(treeNode.getText());
	            node.setA_attr(treeNode.getA_attr());
	            node.setIcon(treeNode.getIcon());
	            node.setLi_attr(treeNode.getLi_attr());
	            node.setParent(treeNode.getParent());
	            node.setState(treeNode.getState());
	            if(id.equals(treeNode.getParent())){
	                node.setChildren(tree(nodes, node.getId()));
	                treeNodes.add(node);
	            }

	        }
	        return treeNodes;
	}


}
