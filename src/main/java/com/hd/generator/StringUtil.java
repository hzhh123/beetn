package com.hd.generator;
/**
*@author hzhh123
*@time 2018年1月22日下午2:25:07 
**/
public class StringUtil {
	/**
	 * 字符串处理
	 * 
	 * @param columnName
	 *            需要被处理的字符串
	 * @return <li>
	 *         <code>columnName = "expert_user_admin"; <br>return "expertUserAdmin";</code>
	 *         </li> <li><code>columnName = "expert"; <br>return "expert";</code>
	 *         </li>
	 */
	public static String handleColumnName(String columnName) {
		if (columnName.contains("_")) {
			String[] array = columnName.split("_");
			String temp = "";
			for (String str : array) {
				temp = temp + str.substring(0, 1).toUpperCase() + str.substring(1);
			}
			temp=temp.substring(0, 1).toLowerCase()+temp.substring(1,temp.length());
			return temp;
		} else {
			return columnName;
		}
	}
	public static String handleTableName(String tableName) {
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
	
	public static String getType(String fieldType) {
		String type="";
		if(fieldType.equals("int")||fieldType.equals("tinyint")){
			type ="Integer";
		}
		if(fieldType.equals("bigint")){
			type ="Long";
		}
		if(fieldType.equals("varchar")||fieldType.equals("text")){
			type ="String";
		}
		if(fieldType.equals("date")||fieldType.equals("datetime")){
			type ="Date";
		}
		return type;
	}
	/**
	 * 获取类型
	 * @param type 如：<code>varchar(20)</code>,<code>datetime</code>,<code>double</code>,<code>longtext</code>
	 * @return 结果：<br><li>type = "varchar(20)", return type = "varchar";</li>
	 * 		   <li>type = "datetime", return type = "datetime";</li>
	 */
	public static String getFieldType(String type){
		if(type.endsWith(")")){
			type = type.substring(0, type.indexOf("("));
		}
		return type;
	}

	/**
	 * 获取类型的长度,默认为255
	 * @param type 如：<code>varchar(20)</code>,<code>decimal(19,2)</code>,<code>datetime</code>,<code>double</code>,<code>longtext</code>
	 * @return 结果：<br><li>type = "varchar(20)", return "20";</li>
	 * 		   <li>type = "datetime", return type = "255";</li>
	 * 		   <li>type = "decimal(19,2)", return type = "19";</li>
	 */
	public static String getValueByType(String type) {
		if (type.endsWith(")")) {
			type = type.substring(type.indexOf("(") + 1, type.length() - 1);
			if(type.contains(",")){
				type = type.substring(0,type.indexOf(","));
			}
			return type;
		} else {
			return "255";
		}
	}
	public static void main(String[] args){
		String str="sys_log_yu";
		String str1="createtime";
		System.out.println(handleColumnName(str1));
		System.out.println(handleTableName(str1));
	}
}
