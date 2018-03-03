package com.hd.controller;

import com.hd.controller.base.BaseController;
import com.hd.entity.Organization;
import com.hd.service.OrganizationService;
import com.hd.util.JsonUtil;
import com.hd.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
*@author hzhh123
*@time 2018年1月15日下午5:17:06 
**/
@RequestMapping("organization")
@Controller
public class OrganizationController extends BaseController{
	@Autowired
	private OrganizationService organizationService;
	@RequestMapping("index")
	public String index(){
		return "jsp/org/organizationList";
	}
	@RequestMapping("jstree")
	public void jstree( HttpServletResponse response) throws IOException {
	     response.setCharacterEncoding("utf8");
	     response.setContentType("text/html;charset=UTF-8");  
	     PrintWriter out=response.getWriter();
	     out.println(JsonUtil.toJson(organizationService.selectJstree()));
	     out.close();
	}
	@RequestMapping("dataGrid")
	public void dataGrid( HttpServletRequest request, HttpServletResponse response) throws IOException {
		 Integer page=Integer.parseInt(request.getParameter("page"));
		 Integer limit=Integer.parseInt(request.getParameter("limit"));
		 String orgname=request.getParameter("name");
		 String id=request.getParameter("id");
	     PageInfo info=organizationService.findDataGrid(page, limit, orgname,id);
	     response.setCharacterEncoding("utf8");
	     response.setContentType("text/html;charset=UTF-8");  
	     PrintWriter out=response.getWriter();
	     out.print(JsonUtil.toJson(info));
	     out.close();
	}

	@RequestMapping("addPage")
	public String addPage() {
		return "jsp/org/organizationAdd";
	}
	
	//添加用户
	@ResponseBody
	@RequestMapping("add")
	public Object add(Organization organization) {
		organizationService.save(organization);
		return renderSuccess("添加成功！");
	}
	@RequestMapping("editPage")
	public String editPage(Long id,Model model) {
		Organization org=organizationService.get(id);
		if(org.getPid()!=null) {
			Organization vo=organizationService.get(org.getPid());
			org.setParentName(vo.getName());
		}
		model.addAttribute("organization", org);
		return "jsp/org/organizationEdit";
	}
	@RequestMapping("viewPage")
	public String viewPage(Long id,Model model) {
		Organization org=organizationService.get(id);
		if(org.getPid()!=null) {
			Organization vo=organizationService.get(org.getPid());
			org.setParentName(vo.getName());
		}
		model.addAttribute("organization", org);
		return "jsp/org/organizationView";
	}
	
	//更新
	@ResponseBody
	@RequestMapping("update")
	public Object update(Organization organization) {
		if(organization.getId()!=null) {
			organizationService.update(organization);
		}
		return renderSuccess("修改成功!");
	}
	//删除
	@ResponseBody
	@RequestMapping("delete")
	public Object delete(Long id) {
		organizationService.delete(id);
		return renderSuccess();
	}
	//批量删除
	@ResponseBody
	@RequestMapping("deleteBatch")
	public Object deleteBatch(String ids) {
		organizationService.deleteBatch(ids);
		return renderSuccess("删除成功！");
	}

	/**
	 * 用户-部门树
	 * @return
	 */
	@ResponseBody
	@RequestMapping("findOrgAndUserTree")
	public Object findOrgAndUserTree(){
		return organizationService.findUserAndOrgTree(null);
	}

}
