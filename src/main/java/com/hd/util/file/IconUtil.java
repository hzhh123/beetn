package com.hd.util.file;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/7/25.
 */
public class IconUtil {
    public static final String GLYPHICON_PATH="/src/main/webapp/static/aceadmin/assets/css/bootstrap.css";
    public static final String FONTAWESOME_PATH="/src/main/webapp/static/plugin/font-awesome-4.7.0/css/font-awesome.css";
   public static List<String> matchList(String path,String prefix) {
       List<String>list=new ArrayList<String>();
       String basePath=System.getProperty("user.dir");
       String readPath=basePath+path;
       try {
           String content = FileUtils.readFileToString(new File(readPath));
           String regex=prefix+".*:before";
           Pattern pattern = Pattern.compile(regex);
           Matcher matcher = pattern.matcher(content);
           while (matcher.find()){
               list.add(matcher.group().replace(":before",""));
           }
           return list;
       }catch (IOException e){
           e.printStackTrace();
       }
       return null;
   }
   public static void main(String[] args){
        String readPath=FONTAWESOME_PATH;
		List<String>slist= IconUtil.matchList(readPath,"fa");
		for(String s:slist){
            System.out.println(s);
        }
   }
}
