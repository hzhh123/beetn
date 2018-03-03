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
    <title>项目修改</title>

    <meta name="description" content="overview &amp; stats" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    <%@include file="/common/head.jsp"%>
    	<link rel="stylesheet" href="${path}/static/aceadmin/assets/css/select2.css" />
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
                    <li class="active">项目管理</li>
                </ul><!-- /.breadcrumb -->

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
                       项目管理
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                           项目修改
                        </small>
                    </h1>
                </div><!-- /.page-header -->

                 <div class="row">
                    <div class="col-xs-12">
                          <div class="widget-box">
									<div class="widget-header widget-header-blue widget-header-flat">
										<h4 class="widget-title lighter">修改项目</h4>
									</div>

									<div class="widget-body">
										<div class="widget-main">
											<!-- #section:plugins/fuelux.wizard -->
											<div id="fuelux-wizard-container">
												<div>
													<!-- #section:plugins/fuelux.wizard.steps -->
													<ul class="steps">
														<li data-step="1" class="active">
															<span class="step">1</span>
															<span class="title">基本信息</span>
														</li>

														<li data-step="2">
															<span class="step">2</span>
															<span class="title">附加信息</span>
														</li>
													</ul>

													<!-- /section:plugins/fuelux.wizard.steps -->
												</div>

												<hr />

												<!-- #section:plugins/fuelux.wizard.container -->
												<div class="step-content pos-rel">
													<div class="step-pane active" data-step="1">
														<form class="form-horizontal" id="validation-form" method="post">
														<input type="hidden" name="id" value="${project.id}" id="id">
															<div class="form-group">
																<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">项目名称</label>
																<div class="col-xs-12 col-sm-9">
																	<div class="clearfix">
																		<input type="text" name="name" id="name" value="${project.name}" class="col-xs-12 col-sm-6" placeholder="请输入项目名称"/>
																	</div>
																</div>
															</div>

															<div class="space-2"></div>

															<div class="form-group">
																<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="projectNo">项目编号</label>

																<div class="col-xs-12 col-sm-9">
																	<div class="clearfix">
																		<input type="text" name="projectNo" id="projectNo" value="${project.projectNo}" placeholder="请输入项目编号" class="col-xs-12 col-sm-6" />
																	</div>
																</div>
															</div>
															
															<div class="space-2"></div>
															<div class="form-group">
																<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="constructionUnits">建设单位</label>

																<div class="col-xs-12 col-sm-9">
																	<div class="clearfix">
																		<input type="text" name="constructionUnits" id="constructionUnits" value="${project.constructionUnits}" placeholder="请输入" class="col-xs-12 col-sm-6" />
																	</div>
																</div>
															</div>
															
															<div class="space-2"></div>
															<div class="form-group">
																<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="designUtils">设计单位</label>

																<div class="col-xs-12 col-sm-9">
																	<div class="clearfix">
																		<input type="text" name="designUtils" id="designUtils" placeholder="请输入" value="${project.designUtils}" class="col-xs-12 col-sm-6" />
																	</div>
																</div>
															</div>
															
															<div class="space-2"></div>
															<div class="form-group">
																<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="totalUtils">总包单位</label>

																<div class="col-xs-12 col-sm-9">
																	<div class="clearfix">
																		<input type="text" name="totalUtils" id="totalUtils" placeholder="请输入" value="${project.totalUtils}" class="col-xs-12 col-sm-6" />
																	</div>
																</div>
															</div>
															
															<div class="space-2"></div>
															<div class="form-group">
																<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="supervisingUnit">监理单位</label>

																<div class="col-xs-12 col-sm-9">
																	<div class="clearfix">
																		<input type="text" name="supervisingUnit" id="supervisingUnit" placeholder="请输入" value="${project.supervisingUnit}" class="col-xs-12 col-sm-6" />
																	</div>
																</div>
															</div>
															
															<div class="space-2"></div>
															<div class="form-group">
																<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="explorationUnit">勘察单位</label>

																<div class="col-xs-12 col-sm-9">
																	<div class="clearfix">
																		<input type="text" name="explorationUnit" id="explorationUnit" placeholder="请输入" value="${project.explorationUnit}" class="col-xs-12 col-sm-6" />
																	</div>
																</div>
															</div>
															
															<div class="space-2"></div>
															<div class="form-group">
																<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="address">工程地址</label>

																<div class="col-xs-12 col-sm-9">
																	<div class="clearfix">
																		<input type="text" name="address" id="address" placeholder="请输入" value="${project.address}" class="col-xs-12 col-sm-6" />
																	</div>
																</div>
															</div>
															
															<div class="space-2"></div>
															<div class="form-group">
																<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="days">合同工期</label>

																<div class="col-xs-12 col-sm-9">
																	<div class="clearfix">
																		<input type="text" name="days" id="days" placeholder="请输入" value="${project.days}" class="col-xs-12 col-sm-6" />
																	</div>
																</div>
															</div>
															
															<div class="space-2"></div>
															<div class="form-group">
																<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="scale">建筑规模</label>

																<div class="col-xs-12 col-sm-9">
																	<div class="clearfix">
																		<input type="text" name="scale" id="scale" placeholder="请输入" value="${project.scale}" class="col-xs-12 col-sm-6" />
																	</div>
																</div>
															</div>
														</form>
													</div>

													<div class="step-pane" data-step="2">
													</div>
												</div>

												<!-- /section:plugins/fuelux.wizard.container -->
											</div>

											<hr />
											<div class="wizard-actions">
												<!-- #section:plugins/fuelux.wizard.buttons -->
												<button class="btn btn-prev">
													<i class="ace-icon fa fa-arrow-left"></i>
													上一步
												</button>

												<button class="btn btn-success btn-next" data-last="完成">
													下一步
													<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
												</button>

												<!-- /section:plugins/fuelux.wizard.buttons -->
											</div>

											<!-- /section:plugins/fuelux.wizard -->
										</div><!-- /.widget-main -->
									</div><!-- /.widget-body -->
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
		<script src="${path}/static/aceadmin/assets/js/bootstrap.js"></script>

		<!-- page specific plugin scripts -->
		<script src="${path}/static/aceadmin/assets/js/fuelux/fuelux.wizard.js"></script>
		<script src="${path}/static/aceadmin/assets/js/jquery.validate.js"></script>
		<script src="${path}/static/aceadmin/assets/js/additional-methods.js"></script>
		<script src="${path}/static/aceadmin/assets/js/bootbox.js"></script>
		<script src="${path}/static/aceadmin/assets/js/jquery.maskedinput.js"></script>
		<script src="${path}/static/aceadmin/assets/js/select2.js"></script>

		<!-- ace scripts -->
		<script src="${path}/static/aceadmin/assets/js/ace/elements.scroller.js"></script>
		<script src="${path}/static/aceadmin/assets/js/ace/elements.colorpicker.js"></script>
		<script src="${path}/static/aceadmin/assets/js/ace/elements.fileinput.js"></script>
		<script src="${path}/static/aceadmin/assets/js/ace/elements.typeahead.js"></script>
		<script src="${path}/static/aceadmin/assets/js/ace/elements.wysiwyg.js"></script>
		<script src="${path}/static/aceadmin/assets/js/ace/elements.spinner.js"></script>
		<script src="${path}/static/aceadmin/assets/js/ace/elements.treeview.js"></script>
		<script src="${path}/static/aceadmin/assets/js/ace/elements.wizard.js"></script>
		<script src="${path}/static/aceadmin/assets/js/ace/elements.aside.js"></script>
		<script src="${path}/static/aceadmin/assets/js/ace/ace.js"></script>
		<script src="${path}/static/aceadmin/assets/js/ace/ace.ajax-content.js"></script>
		<script src="${path}/static/aceadmin/assets/js/ace/ace.touch-drag.js"></script>
		<script src="${path}/static/aceadmin/assets/js/ace/ace.sidebar.js"></script>
		<script src="${path}/static/aceadmin/assets/js/ace/ace.sidebar-scroll-1.js"></script>
		<script src="${path}/static/aceadmin/assets/js/ace/ace.submenu-hover.js"></script>
		<script src="${path}/static/aceadmin/assets/js/ace/ace.widget-box.js"></script>
		<script src="${path}/static/aceadmin/assets/js/ace/ace.settings.js"></script>
		<script src="${path}/static/aceadmin/assets/js/ace/ace.settings-rtl.js"></script>
		<script src="${path}/static/aceadmin/assets/js/ace/ace.settings-skin.js"></script>
		<script src="${path}/static/aceadmin/assets/js/ace/ace.widget-on-reload.js"></script>
		<script src="${path}/static/aceadmin/assets/js/ace/ace.searchbox-autocomplete.js"></script>
		<script src="${ctxPath}/static/aceadmin/assets/js/layui/layui.all.js"></script>
		<script src="${ctxPath}/static/js/util.js"></script>
<script type="text/javascript">
    jQuery(function($) {
    	
		var $validation = true;
		$('#fuelux-wizard-container')
		.ace_wizard({
			//step: 2 //optional argument. wizard will jump to step "2" at first
			//buttons: '.wizard-actions:eq(0)'
			
		})
		.on('actionclicked.fu.wizard' , function(e, info){
			//start first
			if(info.step == 1 && $validation) {
				if(!$('#validation-form').valid()){
					e.preventDefault();
				}else{
				//第一步发送项目信息保存
				var data={
						"project.id":$('#id').val(),
						"project.name":$('#name').val(),
						"project.projectNo":$('#projectNo').val(),
						"project.constructionUnits":$('#constructionUnits').val(),
						"project.designUtils":$('#designUtils').val(),
						"project.totalUtils":$('#totalUtils').val(),
						"project.supervisingUnit":$('#supervisingUnit').val(),
						"project.explorationUnit":$('#explorationUnit').val(),
						"project.address":$('#address').val(),
						"project.days":$('#days').val(),
						"project.scale":$('#scale').val()
				};
				$.ajax({
					async:false,
					url:basePath+'/project/update',
					data:data,
					dataType:'json',
					type:'post',
					success:function(data){
						data=eval('('+data+')');
						if(!data.success){
							$.layerMsg(data.msg,'warning');
							 e.preventDefault();
						}
					},error:function(error){
						layer.msg('修改出错了！');
						 e.preventDefault();
					}
				}) 
			}//end first
			}
			//第二步、第三步...可以模仿第一步
		})
		.on('finished.fu.wizard', function(e) {
			$.layerMsg("修改完成！",'success');
			window.location.href="${ctxPath}/jsp/project/projectList.jsp";
		}).on('stepclick.fu.wizard', function(e){
			e.preventDefault();//this will prevent clicking and selecting steps
		});
	
	
	
	
	
	
		$('#validation-form').validate({
			errorElement: 'div',
			errorClass: 'help-block',
			focusInvalid: false,
			ignore: "",
			rules: {
				name: {
					required: true,
				},
				projectNo:{
					required: true,
				},
				constructionUnits: {
					required: true,
				},
				designUtils: {
					required: true
				},
				totalUtils: {
					required: true
				},
				supervisingUnit: {
					required: true
				},
				explorationUnit: {
					required: true
				},
				address: {
					required: true,
				},
				days: {
					required: true,
				}
			},
	
			messages: {
				name: {
					required: "必填"
				},
				projectNo: {
					required: "必填"
				},
				constructionUnits: {
					required: "必填"
				},
				designUtils: {
					required: "必填"
				},
				totalUtils: {
					required: "必填"
				},
				supervisingUnit: {
					required: "必填"
				},
				explorationUnit: {
					required: "必填"
				},
				address: {
					required: "必填"
				},
				days: {
					required: "必填"
				},
			},
	
	
			 highlight: function (e) {
				$(e).closest('.form-group').removeClass('has-info').addClass('has-error');
			}, 
	
			success: function (e) {
				$(e).closest('.form-group').removeClass('has-error');//.addClass('has-info');
				$(e).remove();
			},
			submitHandler: function (form) {
			},
			invalidHandler: function (form) {
			}
		});
	
		
       
          	//设置菜单激活
         var href=basePath+"/project/managePage";
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

