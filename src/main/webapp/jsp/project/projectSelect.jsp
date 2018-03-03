<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
 <meta name="description" content="overview &amp; stats" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>选择所属项目</title>
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
            <label class="layui-form-label" >项目名称 </label>
            <div class="layui-input-block">
                <input type="text" name="name" id="name" autocomplete="off" placeholder="请输入项目名称" style="width: 150px;" class="layui-input">
            </div>
            </div>
            <div class="layui-input-inline">
            <label class="layui-form-label" style="margin-left:50px;">项目编号 </label>
            <div class="layui-input-block" style="margin-left:160px";>
                <input type="text" name="projectNo" id="projectNo" autocomplete="off" placeholder="请输入项目编号" style="width: 150px;" class="layui-input">
            </div>
            </div>
        </div>
        <div class="layui-form-item" >
            <div class="layui-input-inline">
            <div class="layui-input-block" style="width: 100%;">
               <button type="button" class="layui-btn layui-btn-normal" id="search">查询</button> <button class="layui-btn" id="ok">确定</button>
            </div>
        </div>
        </div>
</form>
<table  class="layui-table"  lay-data="{id:'dataGrid',height:'full-180', url:'${ctxPath}/project/dataGrid',loading:true,page:true,limits: [30,60,90,150,300,500,1000]}" lay-filter="test">
                                    <thead>
                                    <tr>
                                        <th lay-data="{checkbox:true, fixed: true}"></th>
                                        <th lay-data="{field:'id', width:80, sort: true}">ID</th>
                                        <th lay-data="{field:'name', width:280}">项目名称</th>
                                        <th lay-data="{field:'projectNo', width:180}">项目编号</th>
                                    </tr>
                                    </thead>
                                </table>
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
			var projectNo=$('#projectNo').val();
			 table.reload('dataGrid', {
                 where:{
                	 name: name
                	,projectNo:projectNo
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
	        	parent.$('#pjId').val(data[0].id); 
	        	parent.$('#projectName').val(data[0].name); 
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