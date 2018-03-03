
package com.hd.generator;

import com.hd.jdbc.DBUtil;
import com.hd.entity.CodeBeanProperty;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 模板工具类，该类功能：<br>
 * <li>generateBean()生成java的Service</li>
 * <br>********************************************<br>
 * <code>bean.cfg.properties</code>为数据库的配置信息，默认位置是在项目src目录下面<br>
 */
public class GeneratorWebSelect {

	Logger log = LoggerFactory.getLogger(GeneratorWebSelect.class);

	// 载入bean配置
	// ===================================
	public static final String BEAN_CFG_PROPERTIES = "bean.cfg.properties";
	public static LoadProperties beanLoadProperties = new LoadProperties(BEAN_CFG_PROPERTIES);

	// BEAN PROPERTIES
	// ===================================
	Bean bean = new Bean();
	public static String BEAN_URL = beanLoadProperties.getValue("BEAN_WEB_URL");
	public static String BEAN_TARGET_PATH = beanLoadProperties.getValue("CODE_GENERATOR_BASE_PATH");
	public static String BEAN_PATH = BEAN_URL;

	// ANNOTATION
	// ====================================
	private static Annotation annotation = new Annotation();

	/**
	 * 读取配置文件，初始化信息
	 */
	public GeneratorWebSelect() {
		beanLoadProperties = beanLoadProperties == null ? new LoadProperties(BEAN_CFG_PROPERTIES) : beanLoadProperties;
		initAnnotation(beanLoadProperties);
	}

	/**
	 * 初始化注释信息
	 * 
	 * @param beanLoadProperties
	 *            加载配置
	 */
	protected void initAnnotation(LoadProperties beanLoadProperties) {
		annotation.setAuthorName(beanLoadProperties.getValue("ANNOTATION_AUTHOR_NAME"));
		annotation.setAuthorMail(beanLoadProperties.getValue("ANNOTATION_AUTHOR_MAIL"));
		annotation.setVersion(beanLoadProperties.getValue("ANNOTATION_VERSION"));
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		annotation.setDate(simpleDateFormat.format(new Date()));
	}

	/**
	 * 根据模板生成代码
	 * 
	 * @param fileVMPath
	 *            模板路径
	 * @param bean
	 *            目标bean
	 * @param annotation
	 *            注释
	 * @param
	 *
	 * @return
	 * @throws Exception
	 */
	public String createCode(String fileVMPath, Bean bean, Annotation annotation, List<CodeBeanProperty> codeBeanProperties) throws Exception {
		VelocityEngine velocityEngine = new VelocityEngine();
		velocityEngine.setProperty("input.encoding", "UTF-8");
		velocityEngine.setProperty("output.encoding", "UTF-8");
		velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		velocityEngine.init();
		Template template = velocityEngine.getTemplate(fileVMPath);
		VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("bean", bean);
		velocityContext.put("annotation", annotation);
		velocityContext.put("codeBeanProperties", codeBeanProperties);
		StringWriter stringWriter = new StringWriter();
		template.merge(velocityContext, stringWriter);
		return stringWriter.toString();
	}

	/**
	 * 生成java bean文件
	 * 
	 * @param tableName
	 *            表名称,这里会映射成bean的名称
	 * @param
	 *
	 * @throws Exception
	 */
	public void generateBean(String tableName, List<CodeBeanProperty> codeBeanProperties) throws Exception {
		if (codeBeanProperties != null) {
			tableName=handleTableNameFirstLower(tableName);
			String path =BEAN_TARGET_PATH + "/src/main/webapp/" + BEAN_PATH + "/"+handleTableNameFirstLower(tableName)+"/";
			File filePath = new File(path);
			if (!filePath.exists()) {
				filePath.mkdirs();
				log.info("创建路径[" + path + "]成功!");
			}
			String fileName = path +handleTableNameFirstLower(tableName) + "Select.jsp";
			File file = new File(fileName);
			FileWriter fw = new FileWriter(file);
			
			bean.setName(handleTableName(tableName));
			System.out.println(handleTableName(tableName));
			bean.setBeanUrl(BEAN_URL);
			bean.setLowerName(StringUtil.handleColumnName(tableName));
			fw.write(createCode(beanLoadProperties.getValue("BEAN_TEMPLATE_WEB_SELECT_URL"), bean, annotation, codeBeanProperties));
			System.out.println("代码生成成功！");
			System.out.println(file.getAbsolutePath());
			fw.flush();
			fw.close();
		}
	}

	protected  String handleTableNameFirstLower(String tableName) {
		if (tableName.contains("_")) {
			String[] array = tableName.split("_");
			String temp = "";
			for (String str : array) {
				temp = temp + str.substring(0, 1).toUpperCase() + str.substring(1);
			}
			temp=temp.substring(0, 1).toLowerCase()+temp.substring(1,temp.length());
			return temp;
		} else {
			return tableName;
		}
	}
	/**
	 * 字符串处理
	 * 
	 * @param tableName
	 *            需要被处理的字符串
	 * @return <li>
	 *         <code>tableName = "expert_user_admin"; <br>return "ExpertUserAdmin";</code>
	 *         </li> <li><code>tableName = "expert"; <br>return "Expert";</code>
	 *         </li>
	 */
	protected String handleTableName(String tableName) {
		if (tableName.contains("_")) {
			String[] array = tableName.split("_");
			String temp = "";
			for (String str : array) {
				temp = temp + str.substring(0, 1).toUpperCase() + str.substring(1);
			}
			return temp;
		} else {
			return tableName.substring(0, 1).toUpperCase() + tableName.substring(1);
		}
	}

	public static void main(String[] args) throws Exception{
		GeneratorWebSelect velocityUtil = new GeneratorWebSelect();
		String tableNames[]={"resource","user_role"};
		for(String tableName:tableNames){
			List<CodeBeanProperty>codeBeanProperties= DBUtil.getColumnProperty(tableName);
			velocityUtil.generateBean(tableName, codeBeanProperties);
		}
	}

}
