package com.hd.controller;

import com.hd.controller.base.BaseController;
import com.hd.entity.Resource;
import com.hd.service.ResourceService;
import com.hd.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
*@author hzhh123
*@time 2018年1月17日下午9:59:42 
**/
@RequestMapping("resource")
@Controller
public class ResourceController extends BaseController {
	@Autowired
	private ResourceService resourceService;
	@RequestMapping("addPage")
	public String addPage() {
		return "jsp/resource/resourceAdd";
	}
	@RequestMapping("index")
	public String index(){
		return "jsp/resource/resourceList";
	}
	// 添加
	@ResponseBody
	@RequestMapping("add")
	public Object add(Resource resource) {
		resourceService.save(resource);
		return renderSuccess("添加成功！");
	}
	@RequestMapping("editPage")
	public String editPage(Long id, Model model) {
		Resource r = resourceService.get(id);
		model.addAttribute("resource", r);
		return "jsp/resource/resourceEdit";
	}

	@RequestMapping("viewPage")
	public String viewPage(Long id, Model model) {
		Resource r = resourceService.get(id);
		model.addAttribute("resource", r);
		return "jsp/resource/resourceView";
	}
	@ResponseBody
	@RequestMapping("get")
	public Object get(Long id) {
		Resource r = resourceService.get(id);
		return r;
	}
	// 更新
	@ResponseBody
	@RequestMapping("update")
	public Object update(Resource resource) {
		if (resource.getId() != null) {
			resourceService.update(resource);
		}
		return renderSuccess("编辑成功！");
	}

	// 删除
	@ResponseBody
	@RequestMapping("delete")
	public Object delete(Long id) {
		resourceService.delete(id);
		return renderSuccess("删除成功！");
	}
	@ResponseBody
	@RequestMapping("getTreeGrid")
	public String getTreeGrid() {
		String result=resourceService.getTreeGrid();
		return result;
	}
	@RequestMapping("jstree")
	public void jstree(HttpServletResponse response) throws IOException {
	     response.setCharacterEncoding("utf8");
	     response.setContentType("text/html;charset=UTF-8");  
	     PrintWriter out=response.getWriter();
	     out.println(JsonUtil.toJson(resourceService.selectJstree()));
	     out.close();
	}

}
