<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/26
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/global.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title></title>
    <meta name="description" content="overview &amp; stats" />
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    <link rel="stylesheet"
          href="${path}/static/aceadmin/assets/css/font-awesome.css" />
    <link rel="stylesheet"
          href="${staticPath}/static/aceadmin/assets/js/layui/css/layui.css">
    <link rel="stylesheet"
          href="${staticPath}/static/plugin/select2/css/select2.min.css">
    <link rel="stylesheet" href="${staticPath}/static/css/default.css">
    <style type="text/css">
        .layui-layer.layui-layer-iframe {
            overflow: hidden;
            width: 100%;
        }
    </style>
</head>
<body>
<div class="layui-container-fluid" style="margin: 15px 10px;">
    <form class="layui-form" id="form2">
        <table class="layui-table" lay-even="" id="table">
            <colgroup>
                <col width="100">
                <col width="150">
            </colgroup>
            <thead>
            <tr>
                <th>表名</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
    </form>
</div>

<!-- basic scripts -->
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

<%--<script src="${path}/static/aceadmin/assets/js/layer/layer.js"></script>--%>
<script src="${path}/static/aceadmin/assets/js/layui/layui.js"></script>
<script src="${path}/static/plugin/select2/js/select2.min.js"></script>
<script src="${path}/static/js/util.js"></script>
<script type="text/javascript">
    layui.use(['form'], function() {
        var form = layui.form;
        var $=layui.jquery;
        function refresh() {
            parent.$('.btn-refresh').click();
        }

        $.ajax({
            url:basePath+'/generator/showTables',
            dataType:'json',
            success:function (data) {
                if(data.success){
                    var html="";
                    if(data.obj!=null){
                        for(var i=0;i<data.obj.length;i++){
                            html+="<tr>";
                            html+="<td>"+data.obj[i]+"</td>";
                            html+="<td><button class='layui-btn-mini layui-btn sync'>同步</button></td>";
                            html+="</tr>";
                        }
                    }
                    $('#table tbody').append(html);
                }
            }
        })

        $('#table tbody').on('click','.sync',function () {
           var tableName=$(this).parent().prev().text();
            $.ajax({
                url:basePath+'/generator/syncTable',
                type:'post',
                data:{"tableName":tableName},
                dataType:'json',
                success:function (data) {
                       $.layerMsg(data.msg);
                       refresh();
                }
            })
            return false;
        })




    });

</script>
</body>
</html>
