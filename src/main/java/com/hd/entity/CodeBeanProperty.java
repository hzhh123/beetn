package com.hd.entity;

import javax.persistence.*;

/**
 * 保存代码生成表字段信息
 */
@Entity
@Table(name = "code_bean_property")
public class CodeBeanProperty {
    /**
     * id编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 属性,eg:<code>name</code>
     */
    private String field;
    /**
     * field字段对应的java类的字段
     */
    private String javaField;
    /**
     * 类型,eg:<code>varchar</code>,<code>datetime</code>,<code>double</code>,<code>longtext</code>
     */
    private String type;
    /**
     * field字段对应的java类型
     */
    private String javaType;
    /**
     * 长度，如果没有规定长度的，则默认为255
     */
    private Integer length = 255;
    /**
     * 十进位，默认为0，针对<code>decimal</code>
     */
    private Integer decase;
    /**
     * 是否为空，只有两个选择:YES,NO,eg:<code>YES</code>
     */
    private String isNull;
    /**
     * 是否为主键，如果为主键则， key = <code>PRI</code>，如果唯一，则 key = <code>UNI</code>
     * ，如果为外键，则 key = <code>MUL</code>
     */
    private String priKey;
    /**
     * 属性的默认值
     */
    private String defaultValue;
    /**
     * 其他选项，如一个表的id的增长方式为：auto_increment
     */
    private String extra;
    /**
     * 列的注释
     */
    private String comment;
    /**
     * 是否是临时对象
     */
    private String isTransient;
    /**
     * 是否查询
     */
    private String isQuery;
    /**
     * 页面显示方式
     */
    private String showType;
    /**
     * 表名
     */
    private String tableName;
    /**
     * 校验
     */
    private String valid;
    @Transient
    private String methodFieldName;//get、set方法时字段名首字母大写

    private String description;//表描述

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getJavaField() {
        return javaField;
    }

    public void setJavaField(String javaField) {
        this.javaField = javaField;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getDecase() {
        return decase;
    }

    public void setDecase(Integer decase) {
        this.decase = decase;
    }

    public String getIsNull() {
        return isNull;
    }

    public void setIsNull(String isNull) {
        this.isNull = isNull;
    }

    public String getPriKey() {
        return priKey;
    }

    public void setPriKey(String priKey) {
        this.priKey = priKey;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getIsTransient() {
        return isTransient;
    }

    public void setIsTransient(String isTransient) {
        this.isTransient = isTransient;
    }

    public String getIsQuery() {
        return isQuery;
    }

    public void setIsQuery(String isQuery) {
        this.isQuery = isQuery;
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getMethodFieldName() {
        return methodFieldName;
    }

    public void setMethodFieldName(String methodFieldName) {
        this.methodFieldName = methodFieldName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
