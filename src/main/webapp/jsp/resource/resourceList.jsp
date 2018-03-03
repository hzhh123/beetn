<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/16
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/global.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>主页</title>

    <meta name="description" content="overview &amp; stats" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    <%@include file="/common/head.jsp"%>
    <link rel="stylesheet" href="${staticPath}/static/plugin/treegrid/css/jquery.treegrid.css">
    <style type="text/css">
     .ace-nav{
            height: 45px!important;
        }
        .sidebar-shortcuts .btn{
            padding: 6px!important;
        }
        .breadcrumb{
            padding-top: 6px;
        }
        .main-content{
            min-height: calc(100% - 45px)!important;
        }
        .laytable-cell-checkbox{
            padding-top: 5px;
        }
        @media(max-width: 768px) {
            .layui-layer-iframe{
                width:100%!important;
                height:100%!important;
            }
        }
    </style>
</head>

<body class="no-skin">
<!-- #section:basics/navbar.layout -->
<jsp:include page="/common/navbar.jsp"/>

<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">
    <script type="text/javascript">
        try{ace.settings.check('main-container' , 'fixed')}catch(e){}
    </script>

    <!-- #section:basics/sidebar -->
    <div id="sidebar" class="sidebar                  responsive sidebar-fixed  "  data-scroll-to-active="true" data-include-shortcuts="true" data-smooth-scroll="150">
        <script type="text/javascrH-ui.adminipt">
					try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
				</script>
        <div class="sidebar-shortcuts" id="sidebar-shortcuts" >
            <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
                <button class="btn btn-success">
                    <i class="ace-icon fa fa-signal"></i>
                </button>

                <button class="btn btn-info">
                    <i class="ace-icon fa fa-pencil"></i>
                </button>

                <!-- #section:basics/sidebar.layout.shortcuts -->
                <button class="btn btn-warning">
                    <i class="ace-icon fa fa-users"></i>
                </button>

                <button class="btn btn-danger">
                    <i class="ace-icon fa fa-cogs"></i>
                </button>

                <!-- /section:basics/sidebar.layout.shortcuts -->
            </div>

            <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
                <span class="btn btn-success"></span>

                <span class="btn btn-info"></span>

                <span class="btn btn-warning"></span>

                <span class="btn btn-danger"></span>
            </div>
        </div><!-- /.sidebar-shortcuts -->
        <%--侧边菜单--%>
        <jsp:include page="/common/sidebar.jsp"/>

        <!-- #section:basics/sidebar.layout.minimize -->
        <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
            <i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
        </div>

        <!-- /section:basics/sidebar.layout.minimize -->
        <script type="text/javascript">
            try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
        </script>
    </div>

    <!-- /section:basics/sidebar -->
    <div class="main-content">
        <div class="main-content-inner">
            <!-- #section:basics/content.breadcrumbs -->
            <div class="breadcrumbs breadcrumbs-fixed" id="breadcrumbs">
                <script type="text/javascript">
                    try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                </script>

                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="/index">主页</a>
                    </li>
                    <li class="active">资源管理</li>
                </ul><!-- /.breadcrumb -->

                <!-- #section:basics/content.searchbox -->
                <div class="nav-search" id="nav-search">
                    <form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
									<i class="ace-icon fa fa-search nav-search-icon"></i>
								</span>
                    </form>
                </div><!-- /.nav-search -->

                <!-- /section:basics/content.searchbox -->
            </div>

            <!-- /section:basics/content.breadcrumbs -->
            <div class="page-content">
                <!-- #section:settings.box -->
                <div class="ace-settings-container" id="ace-settings-container">
                    <div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
                        <i class="ace-icon fa fa-cog bigger-130"></i>
                    </div>

                    <div class="ace-settings-box clearfix" id="ace-settings-box">
                        <div class="pull-left width-50">
                            <!-- #section:settings.skins -->
                            <div class="ace-settings-item">
                                <div class="pull-left">
                                    <select id="skin-colorpicker" class="hide">
                                        <option data-skin="no-skin" value="#438EB9">#438EB9</option>
                                        <option data-skin="skin-1" value="#222A2D">#222A2D</option>
                                        <option data-skin="skin-2" value="#C6487E">#C6487E</option>
                                        <option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
                                    </select>
                                </div>
                                <span>&nbsp; Choose Skin</span>
                            </div>

                            <!-- /section:settings.skins -->

                            <!-- #section:settings.navbar -->
                            <div class="ace-settings-item">
                                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-navbar" />
                                <label class="lbl" for="ace-settings-navbar"> Fixed Navbar</label>
                            </div>

                            <!-- /section:settings.navbar -->

                            <!-- #section:settings.sidebar -->
                            <div class="ace-settings-item">
                                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-sidebar" />
                                <label class="lbl" for="ace-settings-sidebar"> Fixed Sidebar</label>
                            </div>

                            <!-- /section:settings.sidebar -->

                            <!-- #section:settings.breadcrumbs -->
                            <div class="ace-settings-item">
                                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-breadcrumbs" />
                                <label class="lbl" for="ace-settings-breadcrumbs"> Fixed Breadcrumbs</label>
                            </div>

                            <!-- /section:settings.breadcrumbs -->

                            <!-- #section:settings.rtl -->
                            <div class="ace-settings-item">
                                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl" />
                                <label class="lbl" for="ace-settings-rtl"> Right To Left (rtl)</label>
                            </div>

                            <!-- /section:settings.rtl -->

                            <!-- #section:settings.container -->
                            <div class="ace-settings-item">
                                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-add-container" />
                                <label class="lbl" for="ace-settings-add-container">
                                    Inside
                                    <b>.container</b>
                                </label>
                            </div>

                            <!-- /section:settings.container -->
                        </div><!-- /.pull-left -->

                        <div class="pull-left width-50">
                            <!-- #section:basics/sidebar.options -->
                            <div class="ace-settings-item">
                                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-hover" />
                                <label class="lbl" for="ace-settings-hover"> Submenu on Hover</label>
                            </div>

                            <div class="ace-settings-item">
                                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-compact" />
                                <label class="lbl" for="ace-settings-compact"> Compact Sidebar</label>
                            </div>

                            <div class="ace-settings-item">
                                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-highlight" />
                                <label class="lbl" for="ace-settings-highlight"> Alt. Active Item</label>
                            </div>

                            <!-- /section:basics/sidebar.options -->
                        </div><!-- /.pull-left -->
                    </div><!-- /.ace-settings-box -->
                </div><!-- /.ace-settings-container -->

                <!-- /section:settings.box -->
                 <div class="page-header">
                    <h1>
                       资源管理
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                           资源列表
                        </small>
                    </h1>
                </div><!-- /.page-header -->

                 <div class="row">
                    <div class="col-xs-12">
                    	<div class="btn-group pull-right">
                    		 <button class="btn btn-white btn-primary" onclick="add()"><i class="fa fa-plus-circle bigger-110 blue"></i> 添加</button>
                    		   <button class="btn btn-success btn-white btn-refresh"><i class="fa fa-refresh  bigger-110 green"></i> 刷新</button>
                    	</div>
                    	       <table class="table tree-2 table-bordered table-striped table-condensed" id="info">
                            <thead>
                              <tr>
                                  <th>名称</th>
                                  <th class="hidden-480">url</th>
                                  <th class="hidden-480">描述</th>
                                  <th class="hidden-480">图标</th>
                                  <th class="hidden-480">状态</th>
                                  <th class="hidden-480">类型</th>
                                  <th class="hidden-480">顺序</th>
                              </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                        <!-- PAGE CONTENT ENDS -->
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->


    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
    </a>
</div><!-- /.main-container -->

<%@include file="/common/foot.jsp"%>
<script type="text/javascript" src="${ctxPath}/static/plugin/treegrid/js/jquery.treegrid.js"></script>
<script type="text/javascript" src="${ctxPath}/static/plugin/treegrid/js/jquery.treegrid.bootstrap3.js"></script>
<script type="text/javascript">
function add() {
    layer.open({
        type: 2,
        title: '<i class="fa fa-plus-circle  blue"></i>&nbsp;添加资源',
        shadeClose: false,
        shade: 0.8,
        maxmin: true,
        area: ['90%', '90%'],
        content: '${ctxPath}/resource/addPage' //iframe的url
    });
}
function edit(id) {
	 layer.open({
	        type: 2,
	        title: '<i class="fa fa-pencil  blue"></i>&nbsp;编辑资源',
	        shadeClose: false,
	        shade: 0.8,
	        maxmin: true,
	        area: ['90%', '90%'],
	        content: '${ctxPath}/resource/editPage?id='+id //iframe的url
	    });
}
function view(id) {
	 layer.open({
	        type: 2,
	        title: '<i class="fa fa-search  blue"></i>&nbsp;查看资源',
	        shadeClose: false,
	        shade: 0.8,
	        maxmin: true,
	        area: ['90%', '90%'],
	        content: '${ctxPath}/resource/viewPage?id='+id //iframe的url
	    });
}
function del(id,name){
	layer.confirm('真的删除【'+name+'】吗？', function(index){
        layer.close(index);
		 $.ajax({
	         url:'${ctxPath}/resource/delete',
	         data:{"id":id},
	         dataType:'json',
	         type:'post',
	         success:function(data){
	              layer.msg("删除成功！",{icon:1,time:1000});
	              dataGrid();
	         },
	         error: function(XmlHttpRequest, textStatus, errorThrown){
	             layer.msg('删除失败!',{icon:5,time:1000});
	         }
	     })
	});
}
function dataGrid(){
	 $.ajax({
	        url:basePath+'/resource/getTreeGrid',
	        dataType:'json',
	        success:function (data) {
	            data=eval('('+data+')');
	            var html="";
	            $('#info tbody').empty();
	            $.each(data,function(index,item){
	               if(item.pid==0){
	                    html+="<tr class='treegrid-"+item.id+"'>";
	                }else{
	                    html+="<tr class='treegrid-"+item.id+" treegrid-parent-"+item.pid+"'>";
	                }
	                html+="<td>"+item.name+"";
	                html+="<div class=\"pull-right pos-rel dropdown-hover\" hidden>"
	                    +"<button class=\"btn btn-minier bigger btn-primary\">"
	                    +"<i class=\"ace-icon fa fa-cog icon-only bigger-120\"></i></button>"
	                    +"<ul class=\"dropdown-menu dropdown-only-icon dropdown-yellow dropdown-caret dropdown-close dropdown-menu-right\" >"
	                    +"<li><a href=\"#\" class=\"tooltip-success\" data-rel=\"tooltip\" title=\"编辑\" onclick='edit("+item.id+")'><span class=\"green\">"
	                    +"<i class=\"ace-icon fa fa-pencil bigger-110\"></i></span></a></li>"
	                    +"<li><a href=\"#\" class=\"tooltip-success\" data-rel=\"tooltip\" title=\"查看\" onclick='view("+item.id+")'><span class=\"blue\">"
	                    +"<i class=\"ace-icon fa fa-search bigger-110\"></i></span></a></li>"
	                    +"<li><a href=\"#\" class=\"tooltip-error\" data-rel=\"tooltip\" title=\"删除\" onclick=\"del("+item.id+",'"+item.name+"')\"><span class=\"red\"><i class=\"ace-icon fa fa-trash-o bigger-110\"></i>"
	                    +"</span></a></li></ul></div>";

	                html+="</td>";
	                html+="<td class='hidden-480'>"+(item.url==null?"":item.url)+"</td>";
	                html+="<td class='hidden-480'>"+(item.description==null?'':item.description)+"</td>";
	                html+="<td class='hidden-480'>"+(item.icon==null?'':'<i class=\"'+item.icon+'\"')+"</td>";
	                html+="<td class='hidden-480'>"+(item.status==1?'<span class=\"label label-success arrowed-in\">有效</span>':'<span class="label label-inverse arrowed-in">无效</span>')+"</td>";
	                html+="<td class='hidden-480'>"+(item.resourceType==0?'菜单':'其他')+"</td>";
	                html+="<td class='hidden-480'>"+(item.seq==null?"":item.seq)+"</td>";
	                html+="</tr>"
	            });
	            $('#info tbody').append(html);
	            //提示
	            $('[data-rel="tooltip"]').tooltip({placement:tooltip_placement});
	            function tooltip_placement(context, source) {
	                var $source = $(source);
	                var $parent = $source.closest('td');
	                var off1 = $parent.offset();
	                var w1 = $parent.width();

	                var off2 = $source.offset();
	                //var w2 = $source.width();

	                if( parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2) ) return 'right';
	                return 'left';
	            }
	            $('#info').treegrid({
	                expanderExpandedClass: 'fa fa-minus-circle purple',
	                expanderCollapsedClass: 'fa fa-plus-circle purple'
	            });

	            $('#info tbody tr').hover(function () {
	               $(this).find('.pos-rel').show();
	            },function(){
	                $(this).find('.pos-rel').hide();
	            })
	        }
	    });
}
jQuery(function($) {
  $('.btn-refresh').click(function(){
	  dataGrid();
  });
  dataGrid();

	//设置菜单激活
    var href=basePath+"/resource/index";
    $('.nav.nav-list a').parent().removeClass('active');
    $('.nav.nav-list a').each(function () {
       if($(this).attr('href')==href){
    	   var level= $(this).data('level');
           $(this).parent().addClass('active');
           if(level==2){
               $(this).parent().parent().parent().addClass('active open');
           }
           if(level==3){
               $(this).parent().parent().parent().addClass('active open');
               $(this).parent().parent().parent().parent().parent().addClass('active open');
           }
           if(level==4){
               $(this).parent().parent().parent().addClass('active open');
               $(this).parent().parent().parent().parent().parent().addClass('active open');
               $(this).parent().parent().parent().parent().parent().parent().parent().addClass('active open');
           }

       }
    })
})
</script>
</body>
</html>

