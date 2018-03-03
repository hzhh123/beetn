<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/26
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/global.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title></title>
    <meta name="description" content="overview &amp; stats" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    <link  rel="shortcut icon" href="${path}/static/aceadmin/assets/favicon/favicon.ico">
    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="${path}/static/aceadmin/assets/css/bootstrap.css" />

    <!-- page specific plugin styles -->

    <!-- text fonts -->
    <link rel="stylesheet" href="${path}/static/aceadmin/assets/css/ace-fonts.css" />

    <!-- ace styles -->
    <link rel="stylesheet" href="${path}/static/aceadmin/assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="${path}/static/aceadmin/assets/css/ace-part2.css" class="ace-main-stylesheet" />
    <![endif]-->

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="${path}/static/aceadmin/assets/css/ace-ie.css" />
    <![endif]-->
    <link rel="stylesheet" href="${staticPath}/static/css/aceadmin-style.css">
    <!-- inline styles related to this page -->

    <!-- ace settings handler -->
    <script src="${path}/static/aceadmin/assets/js/ace-extra.js"></script>

    <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

    <!--[if lte IE 8]>
    <script src="${path}/static/aceadmin/assets/js/html5shiv.js"></script>
    <script src="${path}/static/aceadmin/assets/js/respond.js"></script>
    <![endif]-->
    <link rel="stylesheet" href="${path}/static/plugin/font-awesome-4.7.0/css/font-awesome.min.css" />
    <link rel="stylesheet" href="${staticPath}/static/aceadmin/assets/js/layui/css/layui.css">
    <style type="text/css">
        .dropdown.dropdown-colorpicker>.dropdown-toggle{
            position: relative;
            float: right;
            top:-28px!important;
        }
        .dropdown-menu.dropdown-caret.dropdown-menu-right{
            top: 20px!important;
        }
        .site-doc-icon{margin-bottom: 50px; font-size: 0;}
        .site-doc-icon li{display: inline-block; vertical-align: middle; width: 127px; line-height: 25px; padding: 20px 0; margin-right: -1px; margin-bottom: -1px; border: 1px solid #e2e2e2; font-size: 14px; text-align: center; color: #666; transition: all .3s; -webkit-transition: all .3s;}
        .site-doc-icon li .layui-icon{display: inline-block; font-size: 36px;}
        .site-doc-icon li .fontclass{display: none;}
        .site-doc-icon li .name{color: #c2c2c2;}
        .site-doc-icon li:hover{background-color: #f2f2f2; color: #000;}
        .layui-container{
            padding: 0 0px 0 10px!important;
        }
    </style>
</head>
<body style="background:#fff;">
<div class="layui-container-fluid">
    <div style="overflow: auto;height: calc(100% - 50px);";>
    <form class="layui-form layui-form-pane" style="margin-top: 15px;" id="addForm">
        <div class="layui-form-item">
            <label class="layui-form-label">颜色类 </label>
            <div class="layui-input-block">
                <input type="text" name="clazz" id="clazz" class="layui-input" style="width: 80%" value="blue">
                <select id="simple-colorpicker-1" class="hide" lay-ignore>
                    <option selected="" data-class="blue" value="#307ECC">#307ECC</option>
                    <option data-class="blue2" value="#5090C1">#5090C1</option>
                    <option data-class="blue3" value="#6379AA">#6379AA</option>
                    <option data-class="green" value="#82AF6F">#82AF6F</option>
                    <option data-class="green2" value="#2E8965">#2E8965</option>
                    <option data-class="green3" value="#5FBC47">#5FBC47</option>
                    <option data-class="red" value="#E2755F">#E2755F</option>
                    <option data-class="red2" value="#E04141">#E04141</option>
                    <option data-class="red3" value="#D15B47">#D15B47</option>
                    <option data-class="orange" value="#FFC657">#FFC657</option>
                    <option data-class="purple" value="#7E6EB0">#7E6EB0</option>
                    <option data-class="pink" value="#CE6F9E">#CE6F9E</option>
                    <option data-class="dark" value="#404040">#404040</option>
                    <option data-class="grey" value="#848484">#848484</option>
                    <option data-class="default" value="#EEE">#EEE</option>
                </select>
            </div>
        </div>
    </form>
        <div class="tabbable" style="margin-right: 15px;">
            <ul class="nav nav-tabs" id="myTab">
                <li class="active">
                    <a data-toggle="tab" href="#fontawesome">
                        <i class="green ace-icon fa fa-home bigger-120"></i>
                        <span>fontawesome</span>
                    </a>
                </li>

                <li>
                    <a data-toggle="tab" href="#glyphicon">
                        <span>glyphicon</span>
                    </a>
                </li>
            </ul>

            <div class="tab-content">
                <div id="fontawesome" class="tab-pane fade in active">
                    <ul class="site-doc-icon" style="clear: both;width: 100%;" id="fontawesome_content">

                    </ul>
                        <%--<button class="btn-group" id="content"></button>--%>
                    <div id="fontawesome_paged"></div>
                    <!--内容模板-->
                    <%--<script id="contentTpl" type="text/html">--%>
                        <%--{{#  layui.each(d.list, function(index, item){ }}--%>
                        <%--<button class="btn btn-primary btn-sm"><i class="{{item.classname}}"></i></button>--%>
                        <%--{{#  }); }}--%>
                    <%--</script>--%>
                    <script id="contentTpl" type="text/html">
                        {{#  layui.each(d.list, function(index, item){ }}
                        <li>
                            <i class="{{item.classname}} fa-2x color"></i>
                            <div class="name">{{item.classname}}</div>
                        </li>
                        {{#  }); }}
                    </script>
                </div>

                <div id="glyphicon" class="tab-pane fade">
                    <ul class="site-doc-icon" style="clear: both;width: 100%;" id="glyphicon_content">

                    </ul>
                    <div id="glyphicon_paged"></div>
                </div>

            </div>
        </div>

        <!-- /section:elements.tab -->
    </div><!-- /.col -->
</div>
<div style="text-align: center;position: fixed;bottom: 5px;width: 100%;">
    <hr>
    <button class="layui-btn" id="save">保存</button>
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
<script src="${path}/static/aceadmin/assets/js/bootstrap.js"></script>

<!-- page specific plugin scripts -->

<!--[if lte IE 8]>
<script src="${path}/static/aceadmin/assets/js/excanvas.js"></script>
<![endif]-->
<script src="${path}/static/aceadmin/assets/js/jquery-ui.custom.js"></script>
<script src="${path}/static/aceadmin/assets/js/jquery.ui.touch-punch.js"></script>
<script src="${path}/static/aceadmin/assets/js/jquery.easypiechart.js"></script>
<script src="${path}/static/aceadmin/assets/js/jquery.sparkline.js"></script>
<script src="${path}/static/aceadmin/assets/js/flot/jquery.flot.js"></script>
<script src="${path}/static/aceadmin/assets/js/flot/jquery.flot.pie.js"></script>
<script src="${path}/static/aceadmin/assets/js/flot/jquery.flot.resize.js"></script>

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
<script src="${path}/static/aceadmin/assets/js/layui/layui.js"></script>
<script src="${path}/static/plugin/select2/js/select2.min.js"></script>
<script src="${path}/static/js/util.js"></script>
<script type="text/javascript">
    var pagine;
    var laytpl;
    layui.use(['form','laypage','laytpl'], function() {
        var form = layui.form;
        pagine=layui.laypage;
        laytpl=layui.laytpl;
        init(basePath+'/icon/page',1,100,"fontawesome");
        $('#simple-colorpicker-1').ace_colorpicker({pull_right:true}).on('change', function(){
            var color_class = $(this).find('option:selected').data('class');
            $('#clazz').val(color_class);
        });
        $('.site-doc-icon').on('click','li',function () {
                var s=""+$('.site-doc-icon li').find('.name.selected').attr('class');
                var t=s.replace(/name/g,"");
                var i=t.replace(/selected/g,"");
                var g=i.trim();
           if(g!='undefined'){
               $('.site-doc-icon li').find('.name').removeClass(g).removeClass('selected');
               $('.site-doc-icon li').find('.color').removeClass(g).removeClass('selected');
           }
           $(this).find('.name').addClass($('#clazz').val()).addClass('selected');
            $(this).find('.color').addClass($('#clazz').val()).addClass('selected');
        })

        $('#save').click(function () {
            var c1=$('#clazz').val();
            var c2=$('.site-doc-icon li').find('.name.selected').text();
            var c3=c2+" "+c1;
            parent.$('#icon').val(c3);
            //假设这是iframe页
            var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
            parent.layer.close(index); //再执行关闭
        })
        $('#myTab li').click(function () {
            var text=$(this).find('span').text().trim();
            init(basePath+'/icon/page',1,100,text);
        })
    });

    //layui分页
    function init(url,curr,limit,sourcetype) {
        var curr=curr||1;
        var limit=limit||100;
       var  params={
            curr:curr,
            limit:limit,
           sourcetype:sourcetype
        }
        $.ajax({
            url:url,
            //data:{"curr":curr,"limit":limit},
            data:params,
            type:'post',
            dataType:'json',
            success:function (result) {
                var getTpl = contentTpl.innerHTML
                    ,view = document.getElementById(sourcetype+'_content');
                laytpl(getTpl).render(result, function(html){
                    view.innerHTML = html;
                });
                pagine.render({
                    elem: sourcetype+'_paged',
                    limits:[100, 150, 200, 250, 300],
                    limit:result.limit,
                    curr:result.curr
                    ,layout:['count','page','skip','limit']
                    ,count: result.count //数据总数，从服务端得到
                    ,jump: function(obj, first){
                        //obj包含了当前分页的所有参数，比如：
                        // console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                        //console.log(obj.limit); //得到每页显示的条数
                        //首次不执行
                        if(!first){
                            init(url,obj.curr,obj.limit,sourcetype);
                        }
                    }
                });
            }
        })
    }
</script>
</body>
</html>
