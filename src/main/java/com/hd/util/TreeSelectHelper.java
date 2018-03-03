package com.hd.util;

import java.util.ArrayList;
import java.util.List;

public class TreeSelectHelper {
    public static String ToTreeSelectJson(List<TreeSelect> data)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(ToTreeSelectJson(data, "0", ""));
        sb.append("]");
        return sb.toString();
    }
    private static String ToTreeSelectJson(List<TreeSelect> data, String parentId, String blank)
    {
        StringBuilder sb = new StringBuilder();
        List<TreeSelect> childList = findAll(data,parentId);

        String tabline = "";
        if (parentId != "0")
        {
            tabline = "　　";
        }
        if (childList.size() > 0)
        {
            tabline = tabline + blank;
        }
        for (TreeSelect entity: childList)
        {
            entity.setText(tabline + entity.getText());
            String strJson = JsonUtil.toJson(entity);
            sb.append(strJson);
            sb.append(ToTreeSelectJson(data, entity.getId(), tabline));
        }
        return sb.toString().replace("}{", "},{");
    }

    private static List<TreeSelect>findAll(List<TreeSelect> data, String parentId){
        List<TreeSelect>treeSelects=new ArrayList<TreeSelect>();
        for(TreeSelect treeSelect:data){
            if(treeSelect.getParentId().equals(parentId)){
                treeSelects.add(treeSelect);
            }
        }
        return treeSelects;
    }
}
