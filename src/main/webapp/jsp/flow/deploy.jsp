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
    <title>流程部署</title>

    <meta name="description" content="overview &amp; stats" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    <%@include file="/common/head.jsp"%>
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
                    <li class="active">流程部署管理</li>
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
                        流程部署管理
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            流程部署列表
                        </small>
                    </h1>
                </div><!-- /.page-header -->

                <div class="row">
                    <div class="col-xs-12">
                        <!-- <div class="input-group col-sm-4 pull-left">
                            <input type="text" class="form-control search-query" placeholder="输入流程部署名称" name="name" id="name"/>
                            <span class="input-group-btn">
                            <button type="button" class="btn btn-purple btn-sm" id="search">
                            <span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
                            搜索
                            </button>
                            </span>
                        </div> -->

                        <div class="btn-group pull-right">
                            <button class="btn btn-white btn-primary" onclick="add()"><i class="fa fa-plus-circle bigger-110 blue"></i> 添加</button>
                            <button class="btn btn-success btn-white btn-refresh"><i class="fa fa-refresh  bigger-110 green"></i> 刷新</button>
                            <button class="btn btn-success btn-white btn-sync" onclick="sync"><i class="fa fa-snowflake-o  bigger-110 green"></i> 同步流程部署</button>
                        </div>
                        <div class="layui-row">
                            <table  class="layui-table" lay-data="{id:'dataGrid',height:'full-250', url:'${ctxPath}/role/dataGrid',loading:true,page:true,limits: [30,60,90,150,300,500,1000]}" lay-filter="test">
                                <thead>
                                <tr>
                                    <th lay-data="{field:'id', width:80, sort: true}">ID</th>
                                    <th lay-data="{field:'name', width:100}">流程部署名称</th>
                                    <th lay-data="{field:'description', width:180}">描述</th>
                                    <th lay-data="{field:'seq', width:80}">顺序</th>
                                    <th lay-data="{field:'status', width:100,templet:'#tpl1'}">状态</th>
                                    <th lay-data="{fixed: 'right', width:150, align:'center', toolbar: '#barDemo'}">操作</th>
                                </tr>
                                </thead>
                            </table>
                            <script type="text/html" id="barDemo">
                                <div class="btn-group">
                                    <button class="btn btn-white btn-warning btn-xs"  title="授权" lay-event="permission"><i class="fa fa-rss"></i></button>
                                    <button class="btn btn-white btn-success btn-xs"  title="查看" lay-event="detail"><i class="fa fa-search"></i></button>
                                    <button class="btn btn-white btn-primary btn-xs" title="编辑" lay-event="edit"><i class="fa fa-edit"></i></button>
                                    <button class="btn btn-white btn-danger btn-xs" title="删除" lay-event="del"><i class="fa fa-trash-o"></i></button>
                                </div>
                            </script>
                            <script type="text/html" id="tpl1">
                                {{#  if(d.status==1){ }}
                                <span class="label label-success arrowed-in" style="position: relative;z-index: 0">有效</span>
                                {{#  } else { }}
                                <span class="label label-inverse arrowed-in" style="position: relative;z-index: 0">无效</span>
                                {{#  } }}
                            </script>

                        </div>
                    </div>
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
<!-- inline scripts related to this page -->
<script type="text/javascript">
    function add() {
        layer.open({
            type: 2,
            title: '<i class="fa fa-plus-circle  blue"></i>&nbsp;添加流程部署',
            shadeClose: false,
            shade: 0.8,
            maxmin: true,
            area: ['90%', '90%'],
            content: '${ctxPath}/role/addPage' //iframe的url
        });
    }
    jQuery(function($) {

        function del(id){
            $.ajax({
                url:'${ctxPath}/role/delete',
                data:{"id":id},
                dataType:'json',
                type:'post',
                success:function(data){
                    layer.msg("删除成功！",{icon:1,time:1000});

                },
                error: function(XmlHttpRequest, textStatus, errorThrown){
                    layer.msg('删除失败!',{icon:5,time:1000});
                }
            })
        }
        layui.use('table', function(){
            var table = layui.table;
            //监听工具条
            table.on('tool(test)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                var layEvent = obj.event; //获得 lay-event 对应的值
                var tr = obj.tr; //获得当前行 tr 的DOM对象
                if(layEvent === 'detail'){ //查看
                    layer.open({
                        type: 2,
                        title: '<i class="fa fa-search-plus  blue"></i>&nbsp;查看流程部署信息',
                        shadeClose: true,
                        shade: 0.8,
                        maxmin: true,
                        area: ['50%', '90%'],
                        content:"${ctxPath}/role/viewPage?id="+data.id,
                    });
                } else if(layEvent === 'del'){ //删除
                    layer.confirm('真的删除'+data.name, function(index){
                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                        layer.close(index);
                        //向服务端发送删除指令
                        del(data.id);
                    });
                } else if(layEvent === 'edit'){ //编辑
                    layer.open({
                        type: 2,
                        title: '<i class="fa fa-edit  blue"></i>&nbsp;编辑流程部署信息',
                        shadeClose: false,
                        shade: 0.8,
                        maxmin: true,
                        area: ['50%', '90%'],
                        content:"${ctxPath}/role/editPage?id="+data.id,
                    });
                }else if(layEvent === 'permission'){ //授权
                    layer.open({
                        type: 2,
                        title: '<i class="fa fa-rss  blue"></i>&nbsp;分配权限',
                        shadeClose: false,
                        shade: 0.8,
                        maxmin: true,
                        area: ['50%', '90%'],
                        content:"${ctxPath}/jsp/role/permission.jsp?id="+data.id,
                    });
                }
            });

            $('.btn-refresh').on('click',function(){
                table.reload('dataGrid',{url:'${ctxPath}/role/dataGrid'});
            })

        });

        //设置菜单激活
        var href=basePath+"/role/index";
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

