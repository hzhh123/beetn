package com.hd.util;

public class TreeSelect {
    private String id;
    private String text;
    private String parentId;
    private Object data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public TreeSelect(String id, String text) {
        this.id = id;
        this.text = text;
    }
    public TreeSelect(){
        super();
    }
}
