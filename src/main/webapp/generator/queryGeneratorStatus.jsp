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
    <table class="layui-table" lay-even="" id="table">
        <colgroup>
            <col width="200">
            <col width="100">
            <col width="100">
            <col>
        </colgroup>
        <thead>
        <tr>
            <th>名称</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>实体（Entity）</td>
            <td> <span class="label label-success arrowed-in" style="position: relative;z-index: 0">${status==1?'已生成':'未生成'}</span></td>
            <td><button  class="layui-btn layui-btn-primary layui-btn-sm" onclick="search(1,${status},'${tableName}','entity')">查看</button></td>
        </tr>
        <tr>
            <td>业务层（Service）</td>
            <td> <span class="label label-success arrowed-in" style="position: relative;z-index: 0">${status==1?'已生成':'未生成'}</span></td>
            <td><button  class="layui-btn layui-btn-primary layui-btn-sm" onclick="search(1,${status},'${tableName}','service')">查看</button></td>
        </tr>
        <tr>
            <td>控制层（Controller）</td>
            <td> <span class="label label-success arrowed-in" style="position: relative;z-index: 0">${status==1?'已生成':'未生成'}</span></td>
            <td><button  class="layui-btn layui-btn-primary layui-btn-sm" onclick="search(1,${status},'${tableName}','controller')">查看</button></td>
        </tr>
        <tr>
            <td>前端web层（Add）</td>
            <td> <span class="label label-success arrowed-in" style="position: relative;z-index: 0">${status==1?'已生成':'未生成'}</span></td>
            <td><button  class="layui-btn layui-btn-primary layui-btn-sm" onclick="search(1,${status},'${tableName}','add')">查看</button></td>
        </tr>
        <tr>
            <td>前端web层（Edit）</td>
            <td> <span class="label label-success arrowed-in" style="position: relative;z-index: 0">${status==1?'已生成':'未生成'}</span></td>
            <td><button  class="layui-btn layui-btn-primary layui-btn-sm" onclick="search(1,${status},'${tableName}','edit')">查看</button></td>
        </tr>
        <tr>
            <td>前端web层（List）</td>
            <td> <span class="label label-success arrowed-in" style="position: relative;z-index: 0">${status==1?'已生成':'未生成'}</span></td>
            <td><button  class="layui-btn layui-btn-primary layui-btn-sm" onclick="search(1,${status},'${tableName}','list')">查看</button></td>
        </tr>
        <tr>
            <td>前端web层（View）</td>
            <td> <span class="label label-success arrowed-in" style="position: relative;z-index: 0">${status==1?'已生成':'未生成'}</span></td>
            <td><button  class="layui-btn layui-btn-primary layui-btn-sm" onclick="search(1,${status},'${tableName}','view')">查看</button></td>
        </tr>
        </tbody>
    </table>
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
    function search(id,status,tableName,type){
        if(status==1){
            top.layer.open({
                type: 2,
                title: '<i class="fa fa-plus-circle  blue"></i>&nbsp;查看表'+tableName+'对应的实体代码',
                shadeClose: true,
                shade: 0.4,
                maxmin: true,
                area: ['100%', '100%'],
                content: '${ctxPath}/generator/readCodeFile?tableName='+tableName+"&type="+type //iframe的url
            });
        }else{
            layer.msg('代码未生成！');
        }
    }
    layui.use(['form'], function() {
        var form = layui.form;
        var $=layui.jquery;
        function refresh() {
            parent.$('.btn-refresh').click();
        }

        /*$('#code').click(function(){
            var entity=0,service=0,controller=0,add=0,edit=0,list=0,view=0,action=0;
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
                    result=eval('('+result+')');
                    $.layerMsg('生成代码成功！','success');
                    $.layerClose();
                    refresh();

                },error:function(error){
                    $.layerMsg('生成代码出错了！','error');
                }
            })
        });
*/

    });

</script>
</body>
</html>
