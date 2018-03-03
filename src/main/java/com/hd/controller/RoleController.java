package com.hd.controller;

import com.hd.controller.base.BaseController;
import com.hd.entity.Role;
import com.hd.service.RoleService;
import com.hd.util.JsonUtil;
import com.hd.util.PageInfo;
import com.hd.util.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RequestMapping("role")
@Controller
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;
    @RequestMapping("index")
    public String index(){
        return "jsp/role/roleList";
    }

    @RequestMapping("dataGrid")
    public void dataGrid(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer page = Integer.parseInt(request.getParameter("page"));
        Integer limit = Integer.parseInt(request.getParameter("limit"));
        PageInfo info = roleService.findDataGrid(page, limit);
        System.out.println(page+","+limit);
        System.out.println(JsonUtil.toJson(info));
        response.setCharacterEncoding("utf8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(JsonUtil.toJson(info));
        out.close();
    }
    @RequestMapping("addPage")
    public String addPage() {
        return "jsp/role/roleAdd";
    }

    // 添加用户
    @ResponseBody
    @RequestMapping("add")
    public Object add(Role role) {
        //roleService.save(role);
        roleService.doAdd(role);
        return renderSuccess("添加成功！");
    }
    @RequestMapping("editPage")
    public Object editPage(Long id, Model model) {
        Role r = roleService.get(id);
        model.addAttribute("role", r);
        return "jsp/role/roleEdit";
    }
    @RequestMapping("viewPage")
    public String viewPage(Long id, Model model) {
        Role r = roleService.get(id);
        model.addAttribute("role", r);
        return "jsp/role/roleView";
    }

    // 更新
    @ResponseBody
    @RequestMapping("update")
    public Object update(Role role) {
        if (role.getId() != null) {
            roleService.update(role);
        }
        return renderSuccess("编辑成功！");
    }

    // 删除
    @ResponseBody
    @RequestMapping("delete")
    public Object delete(Long id) {
        //roleService.delete(id);
        roleService.doDelete(id);
        return renderSuccess("删除成功！");
    }

    // 分配权限
    @ResponseBody
    @RequestMapping("permissionAddOrEdit")
    public Object permissionAddOrEdit(Long roleId,String resourceIds) {
        roleService.addOrEditPermission(roleId, resourceIds);
        return renderSuccess("授权成功！");
    }
    @ResponseBody
    @RequestMapping("viewPermission")
    public Object viewPermission(Long id) throws IOException {
        List<Tree> treeList = roleService.selectTreeByRoleId(id);
       return treeList;
    }
}
