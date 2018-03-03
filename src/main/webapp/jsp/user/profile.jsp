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
                    <li class="active">控制台</li>
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
                       用户管理
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                           用户列表
                        </small>
                    </h1>
                </div><!-- /.page-header -->

                 <div class="row">
                    <div class="col-xs-12">
                            <div class="col-sm-3">
                                <div class="widget-box widget-color-green2">
                                    <div class="widget-header">
                                        <h4 class="widget-title lighter smaller">
                                            <i class="ace-icon fa fa-sitemap blue"></i>
                                            部门
                                        </h4>
                                    </div>

                                    <div class="widget-body">
                                        <div class="widget-main padding-8" style="height: 65%;overflow: auto">
                                                <div id="deptTree"></div>
                                        </div>
                                    </div>
                                </div><!--dget-box -->
                            </div><!-- /.col -->
                        <div class="col-sm-9">
                            <div class="input-group col-sm-4 pull-left">
                                <input type="text" class="form-control search-query" placeholder="输入你的姓名" name="name" id="name"/>
                                <span class="input-group-btn">
								<button type="button" class="btn btn-purple btn-sm" id="search">
								<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
								搜索
								</button>
								</span>
                            </div>

                            <div class="btn-group pull-right">
                                <%--<shiro:hasPermission name="/user/addPage">--%>
                                <button class="btn btn-white btn-primary" onclick="add()"><i class="fa fa-plus-circle bigger-110 blue"></i> 添加</button>
                                <%--</shiro:hasPermission>--%>
                                <shiro:hasPermission name="/user/deleteBatch">
                                <button class="btn btn-danger btn-white" data-type="getCheckData" id="dels"><i class="fa fa-trash  bigger-110 pink"></i> 删除</button>
                                </shiro:hasPermission>
                                <button class="btn btn-success btn-white btn-refresh"><i class="fa fa-refresh  bigger-110 green"></i> 刷新</button>
                                <button class="btn btn-warning btn-white"><i class="fa fa-file-excel-o  bigger-110 orange"></i> 导出</button>
                            </div>
                            <div class="layui-row">
                                <table  class="layui-table" lay-data="{id:'dataGrid',height:'full-250', url:'${ctxPath}/user/dataGrid',loading:true,page:true,limits: [30,60,90,150,300,500,1000]}" lay-filter="test">
                                    <thead>
                                    <tr>
                                        <th lay-data="{checkbox:true, fixed: true}"></th>
                                        <th lay-data="{field:'id', width:80, sort: true}">ID</th>
                                        <th lay-data="{field:'username', width:80}">用户名</th>
                                        <th lay-data="{field:'name', width:80}">姓名</th>
                                        <th lay-data="{field:'roleList', width:100}">角色</th>
                                        <th lay-data="{field:'sex', width:80,templet:'#tpl1'}">性别</th>
                                        <th lay-data="{field:'age', width:80,sort: true}">年龄</th>
                                        <th lay-data="{field:'organizationName', width:80}">所属部门</th>
                                        <th lay-data="{field:'status', width:80,templet:'#tpl2'}">状态</th>
                                        <th lay-data="{field:'phone', width:177}">电话号码</th>
                                        <th lay-data="{field:'userType', width:80}">用户类型</th>
                                        <th lay-data="{field:'createTime', width:135, sort: true,templet:'#tpl3'}">创建时间</th>
                                        <th lay-data="{fixed: 'right', width:120, align:'center', toolbar: '#barDemo'}">操作</th>
                                    </tr>
                                    </thead>
                                </table>
                                <script type="text/html" id="barDemo">
                                    <div class="btn-group">
                                        <button class="btn btn-white btn-success btn-xs"  title="查看" lay-event="detail"><i class="fa fa-search"></i></button>
                                        <%--<shiro:hasPermission name="/user/editPage">--%>
                                        <button class="btn btn-white btn-primary btn-xs" title="编辑" lay-event="edit"><i class="fa fa-edit"></i></button>
                                        <%--</shiro:hasPermission>--%>
                                        <shiro:hasPermission name="/user/delete">
                                        <button class="btn btn-white btn-danger btn-xs" title="删除" lay-event="del"><i class="fa fa-trash-o"></i></button>
                                        </shiro:hasPermission>
                                    </div>
                                </script>
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
                                    <span class="label label-inverse arrowed-in">无效</span>
                                    {{#  } }}
                                </script>
                                <script type="text/html" id="tpl3">
                                    {{ datetime2Str(d.createTime)}}
                                </script>
                            </div>
                        </div><!--/.col-->
                        </div>
                        <!-- PAGE CONTENT ENDS -->
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->

    <div class="footer">
        <div class="footer-inner">
            <!-- #section:basics/footer -->
            <div class="footer-content">
						<span class="bigger-120">
							<span class="blue bolder">Ace</span>
							Application &copy; 2013-2014
						</span>

                &nbsp; &nbsp;
                <span class="action-buttons">
							<a href="#">
								<i class="ace-icon fa fa-twitter-square light-blue bigger-150"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-facebook-square text-primary bigger-150"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-rss-square orange bigger-150"></i>
							</a>
						</span>
            </div>

            <!-- /section:basics/footer -->
        </div>
    </div>

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
    </a>
</div><!-- /.main-container -->

<%@include file="/common/foot.jsp"%>
<!-- inline scripts related to this page -->
<script type="text/javascript">
    jQuery(function($) {
        $('.easy-pie-chart.percentage').each(function(){
            var $box = $(this).closest('.infobox');
            var barColor = $(this).data('color') || (!$box.hasClass('infobox-dark') ? $box.css('color') : 'rgba(255,255,255,0.95)');
            var trackColor = barColor == 'rgba(255,255,255,0.95)' ? 'rgba(255,255,255,0.25)' : '#E2E2E2';
            var size = parseInt($(this).data('size')) || 50;
            $(this).easyPieChart({
                barColor: barColor,
                trackColor: trackColor,
                scaleColor: false,
                lineCap: 'butt',
                lineWidth: parseInt(size/10),
                animate: ace.vars['old_ie'] ? false : 1000,
                size: size
            });
        })

        $('.sparkline').each(function(){
            var $box = $(this).closest('.infobox');
            var barColor = !$box.hasClass('infobox-dark') ? $box.css('color') : '#FFF';
            $(this).sparkline('html',
                {
                    tagValuesAttribute:'data-values',
                    type: 'bar',
                    barColor: barColor ,
                    chartRangeMin:$(this).data('min') || 0
                });
        });


        //flot chart resize plugin, somehow manipulates default browser resize event to optimize it!
        //but sometimes it brings up errors with normal resize event handlers
        $.resize.throttleWindow = false;

        var placeholder = $('#piechart-placeholder').css({'width':'90%' , 'min-height':'150px'});
        var data = [
            { label: "social networks",  data: 38.7, color: "#68BC31"},
            { label: "search engines",  data: 24.5, color: "#2091CF"},
            { label: "ad campaigns",  data: 8.2, color: "#AF4E96"},
            { label: "direct traffic",  data: 18.6, color: "#DA5430"},
            { label: "other",  data: 10, color: "#FEE074"}
        ]
        function drawPieChart(placeholder, data, position) {
            $.plot(placeholder, data, {
                series: {
                    pie: {
                        show: true,
                        tilt:0.8,
                        highlight: {
                            opacity: 0.25
                        },
                        stroke: {
                            color: '#fff',
                            width: 2
                        },
                        startAngle: 2
                    }
                },
                legend: {
                    show: true,
                    position: position || "ne",
                    labelBoxBorderColor: null,
                    margin:[-30,15]
                }
                ,
                grid: {
                    hoverable: true,
                    clickable: true
                }
            })
        }
        drawPieChart(placeholder, data);

        /**
         we saved the drawing function and the data to redraw with different position later when switching to RTL mode dynamically
         so that's not needed actually.
         */
        placeholder.data('chart', data);
        placeholder.data('draw', drawPieChart);


        //pie chart tooltip example
        var $tooltip = $("<div class='tooltip top in'><div class='tooltip-inner'></div></div>").hide().appendTo('body');
        var previousPoint = null;

        placeholder.on('plothover', function (event, pos, item) {
            if(item) {
                if (previousPoint != item.seriesIndex) {
                    previousPoint = item.seriesIndex;
                    var tip = item.series['label'] + " : " + item.series['percent']+'%';
                    $tooltip.show().children(0).text(tip);
                }
                $tooltip.css({top:pos.pageY + 10, left:pos.pageX + 10});
            } else {
                $tooltip.hide();
                previousPoint = null;
            }

        });

        /////////////////////////////////////
        $(document).one('ajaxloadstart.page', function(e) {
            $tooltip.remove();
        });




        var d1 = [];
        for (var i = 0; i < Math.PI * 2; i += 0.5) {
            d1.push([i, Math.sin(i)]);
        }

        var d2 = [];
        for (var i = 0; i < Math.PI * 2; i += 0.5) {
            d2.push([i, Math.cos(i)]);
        }

        var d3 = [];
        for (var i = 0; i < Math.PI * 2; i += 0.2) {
            d3.push([i, Math.tan(i)]);
        }


        var sales_charts = $('#sales-charts').css({'width':'100%' , 'height':'220px'});
        $.plot("#sales-charts", [
            { label: "Domains", data: d1 },
            { label: "Hosting", data: d2 },
            { label: "Services", data: d3 }
        ], {
            hoverable: true,
            shadowSize: 0,
            series: {
                lines: { show: true },
                points: { show: true }
            },
            xaxis: {
                tickLength: 0
            },
            yaxis: {
                ticks: 10,
                min: -2,
                max: 2,
                tickDecimals: 3
            },
            grid: {
                backgroundColor: { colors: [ "#fff", "#fff" ] },
                borderWidth: 1,
                borderColor:'#555'
            }
        });


        $('#recent-box [data-rel="tooltip"]').tooltip({placement: tooltip_placement});
        function tooltip_placement(context, source) {
            var $source = $(source);
            var $parent = $source.closest('.tab-content')
            var off1 = $parent.offset();
            var w1 = $parent.width();

            var off2 = $source.offset();
            //var w2 = $source.width();

            if( parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2) ) return 'right';
            return 'left';
        }


        $('.dialogs,.comments').ace_scroll({
            size: 300
        });


        //Android's default browser somehow is confused when tapping on label which will lead to dragging the task
        //so disable dragging when clicking on label
        var agent = navigator.userAgent.toLowerCase();
        if(ace.vars['touch'] && ace.vars['android']) {
            $('#tasks').on('touchstart', function(e){
                var li = $(e.target).closest('#tasks li');
                if(li.length == 0)return;
                var label = li.find('label.inline').get(0);
                if(label == e.target || $.contains(label, e.target)) e.stopImmediatePropagation() ;
            });
        }

        $('#tasks').sortable({
                opacity:0.8,
                revert:true,
                forceHelperSize:true,
                placeholder: 'draggable-placeholder',
                forcePlaceholderSize:true,
                tolerance:'pointer',
                stop: function( event, ui ) {
                    //just for Chrome!!!! so that dropdowns on items don't appear below other items after being moved
                    $(ui.item).css('z-index', 'auto');
                }
            }
        );
        $('#tasks').disableSelection();
        $('#tasks input:checkbox').removeAttr('checked').on('click', function(){
            if(this.checked) $(this).closest('li').addClass('selected');
            else $(this).closest('li').removeClass('selected');
        });


        //show the dropdowns on top or bottom depending on window height and menu position
        $('#task-tab .dropdown-hover').on('mouseenter', function(e) {
            var offset = $(this).offset();

            var $w = $(window)
            if (offset.top > $w.scrollTop() + $w.innerHeight() - 100)
                $(this).addClass('dropup');
            else $(this).removeClass('dropup');
        });



    })
</script>



</body>
</html>

