<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/26
  Time: 22:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/global.jsp"%>
<html>
<head>
    <meta charset="utf-8" />
    <title>请假申请</title>
    <meta name="description" content="overview &amp; stats" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    <link rel="stylesheet" href="${path}/static/aceadmin/assets/js/layui/css/layui.css">
</head>
<body>
<div class="layui-container">
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>请假申请单</legend>
</fieldset>
    <blockquote class="layui-elem-quote layui-text layui-quote-nm" style="padding: 5px;">
        <span style="color: red">*</span>必填
    </blockquote>
<form class="layui-form layui-form-pane" action="${path}/vacation/add">
    <div class="layui-form-item">
            <label class="layui-form-label">请假人</label>
            <div class="layui-input-block">
                <input type="tel" name="userName"  autocomplete="off" class="layui-input" value="${sessionScope.user.name}" readonly="readonly">
                <input type="hidden" name="userId" value="${sessionScope.user.id}">
            </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">请假类型</label>
        <div class="layui-input-block">
            <select name="vacationType" lay-filter="aihao">
                <option value="0">带薪假</option>
                <option value="1">病假</option>
                <option value="2" selected="">事假</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
            <label class="layui-form-label">请假时间 <span style="color: red">*</span> </label>
            <div class="layui-input-block">
                <input type="text" name="applytime" id="applytime" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">请假天数 <span style="color: red">*</span></label>
        <div class="layui-input-block">
            <input type="text" name="days"  lay-verify="required" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">请假原因 <span style="color: red">*</span></label>
        <div class="layui-input-block">
            <textarea placeholder="请输入内容" class="layui-textarea" name="reason" lay-verify="required"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="button" class="layui-btn layui-btn-normal" lay-submit="" lay-filter="add">暂存</button>
            <button class="layui-btn" lay-submit="" lay-filter="commit">提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            <button type="button" class="layui-btn layui-btn-primary" onclick="back()">返回</button>
        </div>
    </div>
</form>
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
<!-- <![endif]-->
<script type="text/javascript" src="${path}/static/aceadmin/assets/js/layui/layui.js"></script>
<script type="text/javascript" src="${path}/static/js/util.js"></script>
<script type="text/javascript">
    function back() {
        window.opener=null;
        window.open('','_self');
        window.close();
    }
    layui.use(['form', 'layedit', 'laydate'], function() {
        var form = layui.form
            ,$ = layui.jquery
            ,laydate = layui.laydate;

        //日期时间范围
        laydate.render({
            elem: '#applytime'
            ,type: 'date'
            ,range: true
        });
        //监听提交
        form.on('submit(commit)', function(form){
            $.ajax({
                url: basePath+"/vacation/startApply",
                data: form.field,
                dataType:'json',
                type:'post',
                success:function(data){
                    if(data.success){
                        $.layerMsg(data.msg, "success");
                    }else{
                        $.layerMsg(data.msg, "warning");
                    }
                },
                error:function(error){
                    $.layerMsg("出错了", "error");
                }
            });
            return false;
        });

    });
</script>
</body>
</html>
