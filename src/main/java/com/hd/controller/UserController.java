package com.hd.controller;

import com.hd.controller.base.BaseController;
import com.hd.entity.Organization;
import com.hd.entity.Role;
import com.hd.entity.User;
import com.hd.service.OrganizationService;
import com.hd.service.RoleService;
import com.hd.service.UserService;
import com.hd.util.GetPinyin;
import com.hd.util.JsonUtil;
import com.hd.util.PageInfo;
import com.hd.util.StringUtils;
import com.hd.util.report.excel.EasyExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequestMapping("user")
@Controller
public class UserController extends BaseController{
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private OrganizationService organizationService;


    @RequestMapping("index")
    public String index(){
        return "jsp/user/userList";
    }
    //通过用户名或汉语拼音首字母查询
    @ResponseBody
    @RequestMapping("searchUser")
    public List<User>find(String name){
        List<User>users=new ArrayList<User>();
        if(!name.trim().equals("")) {
            //判断是否是汉字
            if (GetPinyin.isHanzi(name)) {
                users = userService.findUserByName(name);
            } else {
                users = userService.findUserByHeadPinyin(name);
            }
        }
        return users;
    }

    /**
     * 用户列表
     * @param user
     * @param page
     * @param limit
     * @return
     */
    @ResponseBody
    @RequestMapping("dataGrid")
    public Object dataGrid(User user,Integer page,Integer limit) {
        PageInfo info=userService.findDataGrid(page,limit,user);
        return info;
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("delete")
    public Object delete(Long id,@Value("#{APP_PROPERTIES['account.user.delete.syntoactiviti']}") Boolean synToActiviti)throws Exception{
        userService.doDelete(id,synToActiviti);
        return renderSuccess("删除成功！");
    }

    /**
     * 批量删除用户
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteBatch")
    public Object deleteBatch(String ids,@Value("#{APP_PROPERTIES['account.user.delete.syntoactiviti']}") Boolean synToActiviti)throws Exception{
        userService.doDeleteBatch(ids,synToActiviti);
        return renderSuccess("删除成功！");
    }

    /**
     * 添加页面
     * @return
     */
    @RequestMapping("addPage")
    public String addPage(Model model){
        List<Role>roles=roleService.findAllByStatus(1);
        model.addAttribute("roles",roles);
        return "jsp/user/userAdd";
    }

    /**
     * 添加
     * @param user
     * @return
     */
    @RequestMapping(value="/add",method=RequestMethod.POST)
    @ResponseBody
    public Object add(User user, @Value("#{APP_PROPERTIES['account.user.add.syntoactiviti']}") Boolean synToActiviti)throws Exception {
        String msg="";
        boolean b=true;
        if(StringUtils.isNotBlank(user.getUsername())) {
            b=userService.valid(user.getUsername());
            if(!b) {
                msg+="【用户名被占用！】";
            }
        }
        if(b) {
            //userService.save(user);
            String roleIdListStr=user.getRoleIdList();
            List<Long>roleIdList=new ArrayList<Long>();
            if(StringUtils.isNotBlank(roleIdListStr)){
                String ridS[]=roleIdListStr.split(",");
                for(String rid:ridS){
                    roleIdList.add(Long.valueOf(rid));
                }
            }
            userService.doAdd(user,roleIdList,synToActiviti);
            userService.saveUserRole(user.getRoleIdList(),user.getId());
            return renderSuccess("【添加成功！】");
        }else{
            return renderError(msg);
        }

    }

    /**
     *编辑页面
     */
    @RequestMapping("editPage")
    public String editPage(Long id, Model model){
        User user=userService.getUser(id);
        if(user.getOrgid()!=null){
            Organization organization=organizationService.get(user.getOrgid());
            user.setOrgName(organization.getName());
        }
        model.addAttribute("user",user);
        List<Role>checkRoles=userService.getRoles(id);
        model.addAttribute("checkRoles",checkRoles);
        List<Role>roles=roleService.findAllByStatus(1);
        model.addAttribute("roles",roles);
        return "jsp/user/userEdit";
    }

    @ResponseBody
    @RequestMapping("edit")
    public Object edit(User user){
        User vo=userService.getUser(user.getId());
        String msg="";
        boolean b=true;
        if(!vo.getUsername().equals(user.getUsername())) {
            b=userService.valid(user.getUsername());
            if(!b) {
                msg+="【用户名被占用！】";
            }
        }
        if(b) {
            userService.update(user);
            userService.saveUserRole(user.getRoleIdList(),user.getId());
            return renderSuccess("【修改成功！】");
        }else{
            return renderError(msg);
        }
    }

    /**
     *编辑页面
     */
    @RequestMapping("viewPage")
    public String viewPage(Long id, Model model){
        User user=userService.getUser(id);
        if(user.getOrgid()!=null){
            Organization organization=organizationService.get(user.getOrgid());
            user.setOrgName(organization.getName());
        }
        model.addAttribute("user",user);
        List<Role>checkRoles=userService.getRoles(id);
        model.addAttribute("checkRoles",checkRoles);
        List<Role>roles=roleService.findAllByStatus(1);
        model.addAttribute("roles",roles);
        return "jsp/user/userView";
    }

    /**
     * 校验用户名
     * @param username
     * @return
     */
    @ResponseBody
    @RequestMapping("valid")
    public Object valid(String username){
        String msg="";
        boolean b=true;
        if(StringUtils.isNotBlank(username)) {
            //校验项目名称
            b=userService.valid(username);
            if(b) {
                msg="用户名可用！";
            }else {
                msg="用户名称被占用！";
            }
        }
        if(b) {
            return renderSuccess(msg);
        }else {
            return renderError(msg);
        }
    }
    /**
     * 导入页面
     * @return
     */
    @RequestMapping("importPage")
    public String excelImportPage(){
        return "jsp/excel/import";
    }

    /**
     * 导入excel
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "import",method = RequestMethod.POST)
    public Object excelImport(HttpServletRequest request,HttpServletResponse response)throws Exception{
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    String originalFilename = file.getOriginalFilename();
                    String path = request.getServletContext().getRealPath("/upload/excel/");//上传路径
                    File basePath = new File(path);
                    if (!basePath.exists()) {// 如果文件夹不存在，自动创建
                        basePath.mkdirs();
                    }
                    File newFile=new File(basePath,originalFilename);
                    file.transferTo(newFile);
                    System.out.println(newFile.getPath());
                    //读取excel
                    EasyExcel fastExcel = new EasyExcel(newFile.getPath());
                    fastExcel.setSheetName("Sheet1");
                    List<User> users = fastExcel.parse(User.class);
                    if(null != users && !users.isEmpty()) {
                        for (User user:users){
                            if(!StringUtils.isAllFieldNull(user)){
                                userService.save(user);
                                System.out.println(JsonUtil.toJson(user));
                            }
                        }
                    } else {
                        System.out.println("没有结果");
                    }
                    fastExcel.close();
                    newFile.delete();//删除文件
                }
            }
        }

        return renderSuccess("导入成功！");
    }

    /**
     * 同步所有用户到activiti表
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/syncUserToActiviti",method = RequestMethod.POST)
    @ResponseBody
    public Object syncUserToActiviti() throws Exception {
        String msg="";
        boolean b=true;
        try {
            this.userService.synAllUserAndRoleToActiviti();
          msg="同步用户信息成功！";
        } catch (Exception e) {
            msg="同步用户信息失败！";
            b=false;
        }
        if(b){
            return renderSuccess(msg);
        }else{
            return renderError(msg);
        }
    }
}
