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
    <!--   <button class="layui-btn" id="save" style="float: right;margin-bottom:5px;">保存</button> -->
    <form class="layui-form layui-form-pane" id="addForm">
        <div class="layui-form-item">
            <div class="layui-form-block">
                <textarea class="layui-textarea" id="code" style="height: calc(100% - 50px)">${code}</textarea>
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
        /*  var $=layui.jquery;
                 $.ajax({
                     url:basePath+'/autocode/autocode!readCodeFile',
                     type:'post',
                     success:function(result){
                      $('#code').text(result);
                     },error:function(error){
                         $.layerMsg('出错！','error');
                     }
                 }) */

        /* $('#save').click(function(){
            console.log($('#code').text())
        	$.ajax({
    			url:basePath+'/autocode/autocode!writeCodeFile',
    			data:{"code":$('#code').text()},
    			type:'post',
    			dataType:'json',
    			success:function(result){
    			 $.layerMsg('保存成功！','success');
    			 $.layerClose();
    			},error:function(error){
    				$.layerMsg('出错！','error');
    			}
    		})
         })		 */

    });

</script>
</body>
</html>
