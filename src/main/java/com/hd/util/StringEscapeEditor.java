package com.hd.util;

import java.beans.PropertyEditorSupport;

import org.springframework.web.util.HtmlUtils;

public class StringEscapeEditor extends PropertyEditorSupport{

	@Override
	public String getAsText() {
		Object value=getValue();
		return value==null?"":value.toString();
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(text==null) {
			setValue(null);
		}else {
			//html转义
			setValue(HtmlUtils.htmlEscape(text));
		}
	}

	
}
