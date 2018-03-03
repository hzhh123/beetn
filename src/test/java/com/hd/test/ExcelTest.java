package com.hd.test;


import com.hd.entity.User;
import com.hd.util.JsonUtil;
import com.hd.util.report.excel.EasyExcel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chunmeng.lu
 * Date: 2016-20-04 10:42
 */
public class ExcelTest {

	public static void main(String[] args) throws Exception {
		//testCreateExcel();
		//testParse();
		User user=new User();
		System.out.println(JsonUtil.toJson(user));
	}

	public static void testCreateExcel() throws Exception {
		EasyExcel fastExcel = new EasyExcel("/Users/lcm/Desktop/data.xlsx");
		List<MyTest> list = new ArrayList<MyTest>();
		for (int i = 0; i < 10; i++) {
			MyTest test = new MyTest();
			test.setName("张三" + i);
			test.setPhone("15321111111");
			test.setAddress("三里屯");
			test.setLat(135.11111);
			test.setType(1);
			test.setPrice(i * 1.1);
			test.setSize(i);
			test.setDateTime(new Date());
			test.setXx(i % 2 == 0);
			list.add(test);
		}
		fastExcel.createExcel(list);
		fastExcel.close();
	}

	public static void testParse() throws Exception {
		EasyExcel fastExcel = new EasyExcel("D:\\workspace\\idea2017\\work2\\beetn\\src\\main\\webapp\\upload\\excel\\测试.xls");
		fastExcel.setSheetName("Sheet1");
		List<User> tests = fastExcel.parse(User.class);
		if(null != tests && !tests.isEmpty()) {
			for(User myTest : tests) {
				System.out.println(JsonUtil.toJson(myTest));
			}
		} else {
			System.out.println("没有结果");
		}
		fastExcel.close();
	}
}
