package com.hd.util;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @description：layui-table分页
 * @author：何志华
 * @date：2017-10-29
 * 返回json格式
 *
 *{
	"code": 0,
	"msg": "",
	"count": 1,
	"data": []
}*/
public class PageInfo {

    private final static int DEFAULT_LIMIT = 30; //默认显示的记录数 
    private int code=0;
    private String msg="";
    private long count; // 总记录 
    private List data; //显示的记录  
    @JsonIgnore
    private int page; // 当前页 
    @JsonIgnore
    private int limit; // 每页显示的记录数 
    @JsonIgnore
    private Map<String, Object> condition; //查询条件

    @JsonIgnore
    private String sort = "seq";// 排序字段
    @JsonIgnore
    private String order = "asc";// asc，desc mybatis Order 关键字

    public PageInfo() {}

    //构造方法
    public PageInfo(int page, int limit) {
        //计算当前页  
        if (page < 0) {
            this.page = 1;
        } else {
            //当前页
            this.page = page;
        }
        //记录每页显示的记录数  
        if (limit < 0) {
            this.limit = DEFAULT_LIMIT;
        } else {
            this.limit = limit;
        }
    }

    // 构造方法
    public PageInfo(int page, int limit, String sort, String order) {
        this(page, limit) ;
        // 排序字段，正序还是反序
        this.sort = sort;
        this.order = order;
    }

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public Map<String, Object> getCondition() {
		return condition;
	}

	public void setCondition(Map<String, Object> condition) {
		this.condition = condition;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
    
    
}
