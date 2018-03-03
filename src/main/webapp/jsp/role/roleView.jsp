<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/26
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/global.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title></title>
    <meta name="description" content="overview &amp; stats" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    <link rel="stylesheet" href="${path}/static/aceadmin/assets/css/font-awesome.css" />
    <link rel="stylesheet" href="${staticPath}/static/aceadmin/assets/js/layui/css/layui.css">
    <link rel="stylesheet" href="${staticPath}/static/plugin/select2/css/select2.min.css">
    <link rel="stylesheet" href="${staticPath}/static/css/default.css">
    <style type="text/css">
        .layui-layer.layui-layer-iframe{
            overflow: hidden;
            width: 100%;
        }
    </style>
</head>
<body >
<div class="layui-container">
    <form class="layui-form layui-form-pane" style="margin-top: 15px;" id="addForm">
    	<input type="hidden" name="id" value="${role.id}">
        <div class="layui-form-item">
            <label class="layui-form-label">角色名称<span style="color: red;">*</span> </label>
            <div class="layui-input-block">
                <input type="text" name="name" value="${role.name}" id="name" lay-verify="required"  autocomplete="off" placeholder="请输入" class="layui-input" >
            </div>
        </div>
         <div class="layui-form-item" pane="">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-block">
            	<c:if test="${role.status==1}">
            	<input type="checkbox" name="status" value="1" lay-skin="switch" lay-text="有效|无效" checked>
            	</c:if>
            	<c:if test="${role.status!=1}">
            	<input type="checkbox" name="status" value="1" lay-skin="switch" lay-text="有效|无效" >
            	</c:if>
                
            </div>
        </div>
         <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">顺序</label>
                <div class="layui-input-inline">
                    <input type="number" name="seq" value="${role.seq}"  autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">描述</label>
            <div class="layui-input-block">
               <textarea placeholder="请输入内容" name="description" class="layui-textarea">${role.description}</textarea>
            </div>
        </div>
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
       

    });

</script>
</body>
</html>
