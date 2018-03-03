package com.hd.controller;

import com.hd.controller.base.BaseController;
import com.hd.service.IconService;
import com.hd.util.Page;
import com.hd.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("icon")
@Controller
public class IconController extends BaseController{
    @Autowired
    private IconService iconService;
    @ResponseBody
    @RequestMapping("page")
    public Object page(Integer curr,Integer limit, String sourcetype) {
        PageInfo info=iconService.findDataGrid(curr,limit,sourcetype);
        Page p=new Page(curr,limit);
        p.setList(info.getData());
        p.setCount(info.getCount());
        return p;
    }
}
