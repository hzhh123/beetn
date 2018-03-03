<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
 <meta name="description" content="overview &amp; stats" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>选择直接上级</title>
<%@include file="/common/global.jsp" %>
 <link href="${staticPath }/static/plugin/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${staticPath}/static/plugin/jstree/dist/themes/default/style.css">
     <link rel="stylesheet" href="${staticPath}/static/aceadmin/assets/js/layui/css/layui.css">
     <style type="text/css">
     	 .laytable-cell-checkbox{
            padding-top: 5px;
        }
     </style>
</head>
<body style="overflow: hidden;">
<div >
<blockquote class="layui-elem-quote layui-text layui-quote-nm">
  <span style="color:red">*</span>只能选择一条记录
</blockquote>
  <form class="layui-form"  id="addForm" style="margin:10px;">
        <div class="layui-form-item" >
         <div class="layui-input-inline">
            <label class="layui-form-label">姓名 </label>
            <div class="layui-input-block">
                <input type="text" name="user.name" id="name" autocomplete="off" placeholder="请输入查询姓名" style="width: 150px;" class="layui-input">
            </div>
            </div>
            <div class="layui-input-inline">
            <label class="layui-form-label">部门 </label>
            <div class="layui-input-block">
                <input type="text" name="user.organizationId" id="org" autocomplete="off" placeholder="请选择查询部门" style="width: 150px;" class="layui-input">
            </div>
            </div>
        </div>
        <div class="layui-form-item" >
         <div class="layui-input-inline">
            <label class="layui-form-label">手机号码 </label>
            <div class="layui-input-block">
                <input type="text" name="user.phone" id="phone" autocomplete="off" placeholder="请输入手机号码" style="width: 150px;"  class="layui-input">
            </div>
            </div>
            <div class="layui-input-inline">
            <div class="layui-input-block" style="width: 100%;">
               <button type="button" class="layui-btn layui-btn-normal" id="search">查询</button> <button class="layui-btn" id="ok">确定</button>
            </div>
        </div>
        </div>
</form>
<table  class="layui-table"  lay-data="{id:'dataGrid',height:'full-180', url:'${ctxPath}/user/dataGrid',loading:true,page:true,limits: [30,60,90,150,300,500,1000]}" lay-filter="test">
                                    <thead>
                                    <tr>
                                        <th lay-data="{checkbox:true, fixed: true}"></th>
                                        <th lay-data="{field:'id', width:80, sort: true}">ID</th>
                                        <th lay-data="{field:'username', width:80}">用户名</th>
                                        <th lay-data="{field:'name', width:80}">姓名</th>
                                         <th lay-data="{field:'sex', width:80,templet:'#tpl1'}">性别</th>
                                         <th lay-data="{field:'orgName', width:100}">所属部门</th> 
                                          <th lay-data="{field:'projectName', width:100}">所属项目</th>
                                          <th lay-data="{field:'leaderName', width:100}">直接上级</th>
                                         <th lay-data="{field:'phone', width:177}">电话号码</th>
                                         <th lay-data="{field:'enterDay', width:120}">进场日期</th>
                                          <th lay-data="{field:'status', width:80,templet:'#tpl2'}">状态</th>
                                    </tr>
                                    </thead>
                                </table>
                                 <script type="text/html" id="tpl1">
                                    {{#  if(d.sex==1){ }}
                                    男
                                    {{#  } else { }}
                                    女
                                    {{#  } }}
                                </script>
                                <script type="text/html" id="tpl2">
                                    {{#  if(d.status==1){ }}
                                    <%--<span class="layui-badge layui-bg-green">有效</span>--%>
                                    <span class="label label-success arrowed-in" style="position: relative;z-index: 0">有效</span>
                                    {{#  } else { }}
                                    <%--<span class="layui-badge layui-bg-gray">无效</span>--%>
                                    <span class="label label-inverse arrowed-in" style="position: relative;z-index: 0">无效</span>
                                    {{#  } }}
                                </script>
                                <script type="text/html" id="tpl3">
                                    {{ datetime2Str(d.createTime)}}
                                </script>
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
<script src="${ctx}/static/plugin/jstree/dist/jstree.min.js"></script>
<script src="${ctxPath}/static/aceadmin/assets/js/layui/layui.all.js"></script>
<script src="${path}/static/js/util.js"></script>
<script type="text/javascript">
layui.use(['table'], function() {
    var table = layui.table;
		//监听单选
		table.on('checkbox(test)', function(obj){
		  var checked=obj.checked;
		  var data=obj.data; //选中行的相关数据
		  var type=obj.type; //如果触发的是全选，则为：all，如果触发的是单选，则为：one
		  if(checked){
			  if(type!='one'){
				  $.layerMsg('只能选中一条记录！','warning');
			  } 
		  }
		});
		$('#search').click(function(){
			var name=$('#name').val();
			var org=$('#org').val();
			var phone=$('#phone').val();
			console.log(phone)
			 table.reload('dataGrid', {
                 where:{
                	 name: $('#name').val()
                	,organizationId: $('#org').val()
                	,phone:$('#phone').val()
                 }
             });
		})
        $('#close').click(function(){
        	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        	parent.layer.close(index); //再执行关闭 
        })
        $('#ok').click(function(){
        	 var checkStatus = table.checkStatus('dataGrid')
             ,data = checkStatus.data;
        	if(data.length>1){
        		 $.layerMsg('只能选中一条记录！','warning');
        	}else if(data.length==1){
        		console.log(data[0]);
	        	parent.$('#leader').val(data[0].id); 
	        	parent.$('#leaderName').val(data[0].name); 
	        	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	        	parent.layer.close(index); //再执行关闭 
        	}else{
        		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
            	parent.layer.close(index); //再执行关闭 
        	}
        })
})
</script>
</body>
</html>