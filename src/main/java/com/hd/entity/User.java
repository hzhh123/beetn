package com.hd.entity;

import com.hd.util.report.excel.ExcelField;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * The persistent class for the user database table.
 * 用户表
 */
@Entity
@Table(name="sys_user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	/**
	 * 登陆名
	 */
	@ExcelField("用户名")
	private String username;
	/**
	 * 登陆密码
	 */
	@ExcelField("密码")
	private String password;
	/**
	 * 姓名
	 */
	@ExcelField("姓名")
	private String name;
	private String headPinyin;
	/**
	 * 状态
	 */
	private Integer status;

	/**
	 * 所属组织
	 */
	private Long orgid;
	@Transient
	private String orgName;

	private Long positionid;//职位id
	@Transient
	private String positionName;//职位名称


	/**
	 * 创建时间
	 */
	@Column(name = "create_time",nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	@Transient
	private String roleNameList;//角色名称集合
	@Transient
	private String roleIdList;//角色id集合
	@Transient
	private List<Role>roleList;
	@Transient
	private String orgNameList;//部门名称集合
	@Transient
	private String positionNameList;//职位名称结合

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getOrgid() {
		return orgid;
	}

	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Long getPositionid() {
		return positionid;
	}

	public void setPositionid(Long positionid) {
		this.positionid = positionid;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRoleNameList() {
		return roleNameList;
	}

	public void setRoleNameList(String roleNameList) {
		this.roleNameList = roleNameList;
	}

	public String getOrgNameList() {
		return orgNameList;
	}

	public void setOrgNameList(String orgNameList) {
		this.orgNameList = orgNameList;
	}

	public String getPositionNameList() {
		return positionNameList;
	}

	public void setPositionNameList(String positionNameList) {
		this.positionNameList = positionNameList;
	}

	public String getHeadPinyin() {
		return headPinyin;
	}

	public void setHeadPinyin(String headPinyin) {
		this.headPinyin = headPinyin;
	}

	public String getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(String roleIdList) {
		this.roleIdList = roleIdList;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
}
	
	
