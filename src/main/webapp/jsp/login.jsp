<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/15
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/global.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>登陆页面</title>

    <meta name="description" content="用户登陆" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    <link  rel="shortcut icon" href="${path}/static/aceadmin/assets/favicon/favicon.ico">
    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="${ctxPath}/static/aceadmin/assets/css/bootstrap.css" />
    <link rel="stylesheet" href="${ctxPath}/static/aceadmin/assets/css/font-awesome.css" />

    <!-- text fonts -->
    <link rel="stylesheet" href="${ctxPath}/static/aceadmin/assets/css/ace-fonts.css" />

    <!-- ace styles -->
    <link rel="stylesheet" href="${ctxPath}/static/aceadmin/assets/css/ace.css" />

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="${ctxPath}/static/aceadmin/assets/css/ace-part2.css" />
    <![endif]-->
    <link rel="stylesheet" href="${ctxPath}/static/aceadmin/assets/css/ace-rtl.css" />

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="${ctxPath}/static/aceadmin/assets/css/ace-ie.css" />
    <![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

    <!--[if lt IE 9]>
    <script src="${ctxPath}/static/aceadmin/assets/js/html5shiv.js"></script>
    <script src="${ctxPath}/static/aceadmin/assets/js/respond.js"></script>
    <![endif]-->
    <style type="text/css">
        @media(max-width: 768px) {
           .login-container{
               margin-top: 50px;
           }

        }
    </style>
</head>

<body class="login-layout light-login">
<div class="main-container">
    <div class="main-content">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <div class="login-container">
                    <div class="center">
                        <h1>
                            <i class="ace-icon fa fa-leaf green"></i>
                            <span class="red">Ace</span>
                            <span class="white" id="id-text2">Beetn</span>
                        </h1>
                        <h4 class="blue" id="id-company-text">&copy; Application</h4>
                    </div>

                    <div class="space-6"></div>

                    <div class="position-relative">
                        <div id="login-box" class="login-box visible widget-box no-border">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h4 class="header blue lighter bigger">
                                        <i class="ace-icon fa fa-coffee green"></i>
                                        请输入你的登陆信息
                                    </h4>

                                    <div class="space-6"></div>
                                  <form action="${ctxPath}/login" method="post" id="login">
                                    <fieldset>
                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															 <input type="text" class="form-control" id="username" name="username" placeholder="用户名">
															<i class="ace-icon fa fa-user"></i>
														</span>
                                            </label>

                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															  <input type="password" class="form-control" id="password" placeholder="密码" name="password">
															<i class="ace-icon fa fa-lock"></i>
														</span>
                                            </label>

                                            <div class="space"></div>

                                            <div class="clearfix">

                                                <button type="button" class="width-35 pull-right btn btn-sm btn-primary" id="loginBtn">
                                                    <i class="ace-icon fa fa-key"></i>
                                                    <span class="bigger-110">登陆</span>
                                                </button>
                                            </div>

                                            <div class="space-4"></div>
                                        </fieldset>
                                    </form>
                                </div><!-- /.widget-main -->

                              <!--   <div class="toolbar clearfix" style="height:30px;">
                                </div -->
                            </div><!-- /.widget-body -->
                        </div><!-- /.login-box -->



                    <div class="navbar-fixed-top align-right" id="bgsetter">
                        <br />
                        &nbsp;
                        <a id="btn-login-dark" href="#">黑色</a>
                        &nbsp;
                        <span class="blue">/</span>
                        &nbsp;
                        <a id="btn-login-blur" href="#">蓝色</a>
                        &nbsp;
                        <span class="blue">/</span>
                        &nbsp;
                        <a id="btn-login-light" href="#">明亮</a>
                        &nbsp; &nbsp; &nbsp;
                    </div>
                </div>
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div><!-- /.main-content -->
</div><!-- /.main-container -->

<!-- basic scripts -->

<!--[if !IE]> -->
<script type="text/javascript">
    window.jQuery || document.write("<script src='${ctxPath}/static/aceadmin/assets/js/jquery.js'>"+"<"+"/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='${ctxPath}/static/aceadmin/assets/js/jquery1x.js'>"+"<"+"/script>");
</script>
<![endif]-->
<script type="text/javascript">
    if('ontouchstart' in document.documentElement) document.write("<script src='${ctxPath}/static/aceadmin/assets/js/jquery.mobile.custom.js'>"+"<"+"/script>");
</script>
<script src="${staticPath }/static/plugin/layer/layer.js"></script>
<!-- inline scripts related to this page -->
<script type="text/javascript">
    jQuery(function($) {
        $(document).on('click', '.toolbar a[data-target]', function(e) {
            e.preventDefault();
            var target = $(this).data('target');
            $('.widget-box.visible').removeClass('visible');//hide others
            $(target).addClass('visible');//show target
        });
    });



    //you don't need this, just used for changing background
    jQuery(function($) {
        $('#btn-login-dark').on('click', function(e) {
            $('body').attr('class', 'login-layout');
            $('#id-text2').attr('class', 'white');
            $('#id-company-text').attr('class', 'blue');

            e.preventDefault();
        });
        $('#btn-login-light').on('click', function(e) {
            $('body').attr('class', 'login-layout light-login');
            $('#id-text2').attr('class', 'grey');
            $('#id-company-text').attr('class', 'blue');

            e.preventDefault();
        });
        $('#btn-login-blur').on('click', function(e) {
            $('body').attr('class', 'login-layout blur-login');
            $('#id-text2').attr('class', 'white');
            $('#id-company-text').attr('class', 'light-blue');

            e.preventDefault();
        });
        $('#loginBtn').bind('click',login);
        $(document).keypress(function(event){
            if(event.keyCode==13){
                event.returnValue=false;
                event.cancel = true;
                login();
            }
        });
    });
    function login(){
    	 $.ajax({
 			url:$('#login').attr('action'),
 			data:$('#login').serialize(),
 			type:'post',
 			dataType:'json',
 			success:function(data){
 				if(data.success){
 				    layer.msg("登陆成功！");
 					window.location.href="/index";
 				}else{
 				    layer.msg("用户名或密码不对！");
 	 			}
 			}
    	 });
    }
</script>
</body>
</html>
