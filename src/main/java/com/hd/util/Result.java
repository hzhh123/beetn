package com.hd.util;

import java.io.Serializable;
/**
 * ajax 返回的结果集封装
 * @author 何志华
 *
 */
public class Result implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 662875886859640718L;
	public static final int SUCCESS=1;
	public static final int FAILURE=-1;
	private boolean success=true;
	private String msg="";
	private Object obj=null;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}

	public Result(boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}

	public Result() {
	}
}
