package com.hd.util;

import java.util.List;

/**
*@author hzhh123
*@time 2018年1月18日下午1:41:15 
*layui分页
**/
public class Page {
	 private Long count;
	    private List list;
	    private Integer curr;
	    private Integer limit;

	    public Long getCount() {
	        return count;
	    }

	    public void setCount(Long count) {
	        this.count = count;
	    }

	    public List getList() {
	        return list;
	    }

	    public void setList(List list) {
	        this.list = list;
	    }

	    public Integer getCurr() {
	        return curr;
	    }

	    public void setCurr(Integer curr) {
	        this.curr = curr;
	    }

	    public Integer getLimit() {
	        return limit;
	    }

	    public void setLimit(Integer limit) {
	        this.limit = limit;
	    }

	    public Page(Integer curr, Integer limit) {
	        this.curr = curr;
	        this.limit = limit;
	    }

	    public Page() {
	    }
}
