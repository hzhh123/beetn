package com.hd.jdbc;

import com.hd.entity.CodeBeanProperty;
import com.hd.generator.LoadProperties;
import com.hd.generator.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {
    private static final Logger log = LoggerFactory.getLogger(DBUtil.class);
    // 载入配置
    // ==================================
    private static final String JDBC__PROPERTIES = "db.properties";
    private static LoadProperties loadProperties = new LoadProperties(JDBC__PROPERTIES);

    // JDBC RESOURCES
    // ==================================
    private static String DB_NAME = loadProperties.getValue("db_name");
    private static String SHOW_TABLES = "show tables";

    /**
     * 读取配置文件，初始化信息
     */
    public DBUtil() {
        loadProperties = loadProperties == null ? new LoadProperties(JDBC__PROPERTIES) : loadProperties;
    }

    /**
     * 获取数据库中所有的表名称
     *
     * @return 数据库中表名称的list
     * @throws Exception
     */
    public static List<String> showTables() throws Exception {
        List<String> list = new ArrayList<String>();
        Connection connection=JDBCTools.getConnection();
        PreparedStatement ps = connection.prepareStatement(SHOW_TABLES);
        ResultSet rs = ps.executeQuery();
        log.info("数据库:[" + DB_NAME + "]中的表如下：");
        while (rs.next()) {
            String tableName = rs.getString(1);
            log.info(tableName);
            list.add(tableName);
        }
       JDBCTools.releaseDB(rs,ps,connection);
        return list;
    }



    /**
     * 同步表
     * @param tableName
     * @throws Exception
     */
    public static void syncTable(String tableName)throws Exception{
        String sql="SHOW FULL COLUMNS FROM " +tableName;
        Connection connection=JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                CodeBeanProperty codeBeanProperty=new CodeBeanProperty();
                codeBeanProperty.setField(resultSet.getString(1));
                codeBeanProperty.setTableName(tableName);
                codeBeanProperty.setComment(resultSet.getString(9));
                codeBeanProperty.setPriKey(resultSet.getString(5));
                codeBeanProperty.setType(StringUtil.getFieldType(resultSet.getString(2)));
                codeBeanProperty.setLength(Integer.valueOf(StringUtil.getValueByType(resultSet.getString(2))));
                codeBeanProperty.setIsNull(resultSet.getString(4));
                codeBeanProperty.setDefaultValue(resultSet.getString(6));
                codeBeanProperty.setExtra(resultSet.getString(7));
                if(codeBeanProperty.getField().equals("id")){
                    codeBeanProperty.setShowType("hidden");
                }else if(codeBeanProperty.getType().equals("date")){
                    codeBeanProperty.setShowType("date");
                }else if(codeBeanProperty.getType().equals("datetime")){
                    codeBeanProperty.setShowType("datetime");
                }else{
                    codeBeanProperty.setShowType("text");
                }
                codeBeanProperty.setJavaField(StringUtil.handleColumnName(resultSet.getString(1)));
                String type=StringUtil.getFieldType(resultSet.getString(2));
                codeBeanProperty.setJavaType(StringUtil.getType(type));

                //插入
                DAO dao=new DAO();
                String insertSql="insert into code_bean_property(field,tableName,comment," +
                        "isNull,length,defaultValue,priKey,extra,showType,type,javaField,javaType)values(?,?,?,?,?,?,?,?,?,?,?,?)";
                dao.update(insertSql,codeBeanProperty.getField(),codeBeanProperty.getTableName(),codeBeanProperty.getComment(),
                        codeBeanProperty.getIsNull(),codeBeanProperty.getLength(),codeBeanProperty.getDefaultValue(),
                        codeBeanProperty.getPriKey(),codeBeanProperty.getExtra(),codeBeanProperty.getShowType(),codeBeanProperty.getType(),
                        codeBeanProperty.getJavaField(),codeBeanProperty.getJavaType());
            }
            DAO dao=new DAO();
            String insertSql="insert into code_generator(name,status)values(?,?)";
            dao.update(insertSql,tableName,0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCTools.releaseDB(resultSet, preparedStatement, connection);
        }
    }

    public static List<CodeBeanProperty>getColumnProperty(String tableName){
        String sql="select * from code_bean_property where tableName=?";
        Connection connection=JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<CodeBeanProperty>codeBeanProperties=new ArrayList<CodeBeanProperty>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,tableName);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                CodeBeanProperty codeBeanProperty=new CodeBeanProperty();
                codeBeanProperty.setField(resultSet.getString("field"));
                codeBeanProperty.setTableName(tableName);
                codeBeanProperty.setComment(resultSet.getString("comment"));
                codeBeanProperty.setPriKey(resultSet.getString("priKey"));
                codeBeanProperty.setType(resultSet.getString("type"));
                codeBeanProperty.setLength(Integer.valueOf(resultSet.getString("length")));
                codeBeanProperty.setIsNull(resultSet.getString("isNull"));
                codeBeanProperty.setDefaultValue(resultSet.getString("defaultValue"));
                codeBeanProperty.setExtra(resultSet.getString("extra"));
//                codeBeanProperty.setDecase(Integer.parseInt(resultSet.getString("decase")));
                codeBeanProperty.setJavaField(resultSet.getString("javaField"));
                codeBeanProperty.setJavaType(resultSet.getString("javaType"));
                codeBeanProperty.setIsQuery(resultSet.getString("isQuery"));
                codeBeanProperty.setIsTransient(resultSet.getString("isTransient"));
                codeBeanProperty.setValid(resultSet.getString("valid"));
                codeBeanProperty.setShowType(resultSet.getString("showType"));
                codeBeanProperty.setId(Long.valueOf(resultSet.getString("id")));
                codeBeanProperty.setDescription(resultSet.getString("description"));
                codeBeanProperty.setMethodFieldName(StringUtil.handleTableName(resultSet.getString("field")));
                codeBeanProperties.add(codeBeanProperty);
            }
            return  codeBeanProperties;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBCTools.releaseDB(resultSet, preparedStatement, connection);
        }
    }

    public static void deleteColumn(String tableName){
        String sql="delete from code_bean_property where tableName=?";
        DAO dao=new DAO();
        dao.update(sql,tableName);
    }
    public static void main(String[] args)throws Exception{
    }
}
