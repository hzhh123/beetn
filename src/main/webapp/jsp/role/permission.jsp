<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/global.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>分配权限</title>

    <meta name="description" content="overview &amp; stats" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    <link  rel="shortcut icon" href="${path}/static/aceadmin/assets/favicon/favicon.ico">
    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="${path}/static/aceadmin/assets/css/bootstrap.css" />
    <link rel="stylesheet" href="${path}/static/aceadmin/assets/css/font-awesome.css" />
    <link rel="stylesheet" href="${path}/static/plugin/bootstrap-ztree3/css/bootstrapStyle/bootstrapStyle.css" type="text/css">
    <link rel="stylesheet" href="${staticPath}/static/aceadmin/assets/js/layui/css/layui.css">
</head>

<body>

<ul id="treeDemo" class="ztree" style="overflow: auto;height: calc(100% - 50px);"></ul>
<div style="text-align: center;position: fixed;bottom: 5px;width: 100%;">
    <hr>
    <button class="layui-btn" id="save">保存</button>
</div>
<!--[if !IE]> -->
<script type="text/javascript">
    window.jQuery || document.write("<script src='${path}/static/aceadmin/assets/js/jquery.js'>"+"<"+"/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='${path}/static/aceadmin/assets/js/jquery1x.js'>"+"<"+"/script>");
</script>
<![endif]-->
<script type="text/javascript">
    if('ontouchstart' in document.documentElement) document.write("<script src='${path}/static/aceadmin/assets/js/jquery.mobile.custom.js'>"+"<"+"/script>");
</script>

<!-- ace scripts -->
<script src="${path}/static/aceadmin/assets/js/layer/layer.js"></script>
<script src="${path}/static/plugin/bootstrap-ztree3/js/jquery.ztree.all.min.js"></script>
<script src="${ctxPath}/static/aceadmin/assets/js/layui/layui.all.js"></script>
<script src="${ctxPath}/static/js/util.js"></script>
<!-- inline scripts related to this page -->
<script type="text/javascript">

    jQuery(function($) {
        var setting = {
            view: {
                selectedMulti: false
            },
            check: {
                enable: true
            },
            data: {
                simpleData: {
                    enable: true
                }
            }
        };
        //获取角色id
        var id=$.getQueryString("id");
        id=id.replace('#','');
        $.ajax({
            url:basePath+'/role/viewPermission',
            type:'get',
            data:{"id":id},
            dataType:'json',
            success:function(data){
                $.fn.zTree.init($("#treeDemo"), setting, data);
                var treeObj = $.fn.zTree.getZTreeObj("treeDemo"); 
                treeObj.expandAll(true);//默认展开
            }
        });

        //保存权限
        $('#save').click(function () {
            var resourceIds=[];
            var treeObj = $.fn.zTree.getZTreeObj("treeDemo"); //获取全部节点数据
            var nodes = treeObj.getCheckedNodes(true);
            for (var i=0, l=nodes.length; i < l; i++)  { //
                resourceIds.push(nodes[i].id);
            }
            $.ajax({
                url:basePath+'/role/permissionAddOrEdit',
                type:'post',
                data:{"roleId":id,"resourceIds":resourceIds.join(',')},
                dataType:'json',
                success:function(data){
                    $.layerClose();
                    $.layerMsg("授权成功！", "success");
                },error:function(error){
                	$.layerMsg("授权失败！", "error");
                }
            });
        });

    })
</script>

</body>
</html>

