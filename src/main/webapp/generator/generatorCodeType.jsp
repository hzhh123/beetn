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
    <style type="text/css">
        .layui-input-inline{
            height:20px;
        }
    </style>
</head>
<body>
<div class="layui-container-fluid" style="margin: 15px 10px;">
    <div class="layui-row" style="height:calc(100% - 55px);overflow:auto;">
        <input type="hidden" id="name" name="tableName" value="${tableName}">
        <form class="layui-form">
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <input type="checkbox" name="entity" value="1" title="实体（Entity）" checked="" lay-skin="primary">
                </div>
                <div class="layui-input-inline">
                    <input type="checkbox" name="service" value="1" title="业务层（Service）" checked="" lay-skin="primary">
                </div>
                <div class="layui-input-inline">
                    <input type="checkbox" name="controller" value="1" title="控制层（Controller）" checked="" lay-skin="primary">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <input type="checkbox" name="add" value="1" title="前端web层（Add）" checked="" lay-skin="primary">
                </div>
                <div class="layui-input-inline">
                    <input type="checkbox" name="edit" value="1" title="前端web层（Edit）" checked="" lay-skin="primary">
                </div>
                <div class="layui-input-inline">
                    <input type="checkbox" name="list" value="1" title="前端web层（List）" checked="" lay-skin="primary">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <input type="checkbox" name="view" value="1" title="前端web层（View）" checked="" lay-skin="primary">
                </div>
            </div>
            <div class="layui-form-item" style="position: fixed;bottom:0px;width:100%;">
                <hr>
                <div class="layui-input-block" style="text-align:center">
                    <button type="button" class="layui-btn layui-btn-primary" id="code">生成代码</button>
                </div>
            </div>
        </form>
    </div>
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

<script src="${path}/static/aceadmin/assets/js/layui/layui.js"></script>
<script src="${path}/static/js/util.js"></script>
<script type="text/javascript">
    layui.use(['form'], function() {
        var form = layui.form;
        var $=layui.jquery;
        function refresh() {
            parent.$('.btn-refresh').click();
        }

        $('#code').click(function(){
            var entity=0,service=0,controller=0,add=0,edit=0,list=0,view=0;
            var tableName=$('#name').val();
            if($('[name=entity]').is(':checked')){
                entity=1;
            }
            if($('[name=entity]').is(':checked')){
                entity=1;
            }
            if($('[name=service]').is(':checked')){
                service=1;
            }
            if($('[name=controller]').is(':checked')){
                controller=1;
            }

            if($('[name=add]').is(':checked')){
                add=1;
            }
            if($('[name=edit]').is(':checked')){
                edit=1;
            }
            if($('[name=list]').is(':checked')){
                list=1;
            }
            if($('[name=view]').is(':checked')){
                view=1;
            }
            var data={"tableName":tableName,"entity":entity,"service":service,"controller":controller,
                "add":add,"edit":edit,"list":list,"view":view};
            $.ajax({
                url:basePath+'/generator/generator',
                data:data,
                dataType:'json',
                type:'post',
                success:function(result){
                    $.layerMsg('生成代码成功！','success');
                    $.layerClose();
                    refresh();

                },error:function(error){
                    $.layerMsg('生成代码出错了！','error');
                }
            })
        });


    });

</script>
</body>
</html>
