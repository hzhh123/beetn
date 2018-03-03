package com.hd.util.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author hzhh123
 * @time 2018年1月23日上午11:17:52
 **/
public class FileUtil {
	/*
	 * 功能：Java读取txt文件的内容 步骤：1：先获得文件句柄 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
	 * 3：读取到输入流后，需要读取生成字节流 4：一行一行的输出。readline()。 备注：需要考虑的是异常情况
	 * 
	 * @param filePath
	 */
	public static String readFile(String filePath) {
		String result = "";
		try {
			String encoding = "UTF-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					result += lineTxt + "\n";
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		return result;
	}
	//写入文件
	public static void writeFile(String path, String content) {
		try {
			File file = new File(path);
			if (!file.exists()) {
				file.createNewFile();
			}
			 FileWriter fw = new FileWriter(file.getAbsoluteFile());
			 BufferedWriter bw = new BufferedWriter(fw);
			 System.out.println(content);
			 System.out.println(file.getAbsoluteFile());
			 bw.write(content);
			 bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String argv[]) {
		String path = "D:\\workspace\\eclipse_work2\\autocode\\autocode\\src\\main\\java\\com\\hd\\entity\\Test.java";
		String result = readFile(path);
		System.out.println(result);
		
		//测试写入文件
		String content="11111111\n22,中国dddd";
		String path1="D:\\test.java";
		writeFile(path1,content);
		
	}

}
