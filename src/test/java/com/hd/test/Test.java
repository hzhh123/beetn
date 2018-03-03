package com.hd.test;

import com.hd.entity.User;
import com.hd.service.OrganizationService;
import com.hd.service.UserService;
import com.hd.util.JsonUtil;
import com.hd.util.result.OrgUserTree;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.List;

/**
*@author hzhh123
*@time 2018年1月24日下午3:53:38 
**/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring.xml"})
public class Test {
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private UserService userService;
	@org.junit.Test
	public void test1() {
		List<OrgUserTree> orgUserTreeList=organizationService.findUserAndOrgByPId(null);
		System.out.println(JsonUtil.toJson(orgUserTreeList));
	}
	@org.junit.Test
	public void test2() {
		List<OrgUserTree> orgUserTreeList=organizationService.findUserAndOrgTree(null);
		System.out.println(JsonUtil.toJson(orgUserTreeList));
	}

	@org.junit.Test
	public void test3(){
		List<User>users=userService.findUserByName("李峰");
		System.out.println(users.size());
		users=userService.findUserByHeadPinyin("lf");
		System.out.println(users.size());
	}
	@org.junit.Test
	public void test4(){
		String str="1,,";
		String []s= str.split(",");
		System.out.println(s.length);
	}
	@org.junit.Test
	public void test5(){
		String applytime="2018-03-01 - 2018-04-03";
		String []s=applytime.split(" - ");
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(s[0]);
	}
}
