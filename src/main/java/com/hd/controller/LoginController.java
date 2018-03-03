package com.hd.controller;

import com.hd.controller.base.BaseController;
import com.hd.entity.User;
import com.hd.service.UserService;
import com.hd.util.JsonUtil;
import com.hd.util.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
*@author hzhh123
*@time 2018年1月23日下午5:12:45 
**/
@Controller
public class LoginController extends BaseController{
	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping("login")
	public Object login(HttpServletRequest request,User user){
		HttpSession session=request.getSession();
		User loginUser=userService.getUserByUsernameAndPassword(user);
		logger.info("登陆用户："+ JsonUtil.toJson(loginUser));
		if(loginUser!=null){
			session.setAttribute("user", loginUser);
			return renderSuccess("登陆成功！");//登陆成功
		} else {
			return renderError("登陆失败！用户名或密码不对！");
		}
	}
	@ResponseBody
	@RequestMapping("logout")
	public Object logout(HttpServletRequest request) {
		HttpSession session=request.getSession();
		session.removeAttribute("user");
		return renderSuccess();
	}
	@RequestMapping("index")
	public String index(HttpServletRequest request){
		HttpSession session=request.getSession();
		User sessionUser=(User)session.getAttribute("user");
		List<TreeNode> nodes =userService.getResourceByUserid(sessionUser.getId(), 0);
		System.out.println(JsonUtil.toJson(nodes));
		session.setAttribute("menus", nodes);
		return "jsp/index";
	}
}
