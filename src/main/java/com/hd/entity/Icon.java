package com.hd.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
*@author hzhh123
*@time 2018年1月18日上午11:53:21
 * 图标表
**/
@Entity
@Table(name="sys_icon")
public class Icon {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	 /**
     * 图标显示名
     */
	private String displayname;
    /**
     * 类名
     */
	private String classname;
    /**
     * 类型
     */
	private String sourcetype;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDisplayname() {
		return displayname;
	}
	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getSourcetype() {
		return sourcetype;
	}
	public void setSourcetype(String sourcetype) {
		this.sourcetype = sourcetype;
	}
	

}
