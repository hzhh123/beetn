    <%@include file="global.jsp" %>
    <link href="${staticPath }/static/plugin/bootstrapV3/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${staticPath }/static/plugin/bootstrap-off-canvas/css/default.css">
    <link href="${staticPath }/static/plugin/bootstrap-off-canvas/src/bootstrap-off-canvas-nav.css" rel="stylesheet">
    <link href="${staticPath }/static/plugin/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="${staticPath }/static/plugin/metismenu-2.7.1/dist/metisMenu.css" rel="stylesheet">
    <link href="${staticPath }/static/plugin/metismenu-2.7.1/dist/mm-vertical1.css" rel="stylesheet">
    <link href="${staticPath }/static/css/style.css" rel="stylesheet">
    <!--[if IE]>
   <!-- <script src="http://libs.useso.com/js/html5shiv/3.7/html5shiv.min.js"></script>-->
    <![endif]-->
    <script src="${staticPath }/static/plugin/jquery.js"></script>
    <script src="${staticPath }/static/plugin/bootstrapV3/js/bootstrap.min.js"></script>
    <script src="${staticPath }/static/plugin/bootstrap-off-canvas/src/bootstrap-off-canvas-nav.js"></script>
    <script src="${staticPath }/static/plugin/scroll/jquery.nicescroll.min.js"></script>
    <script src="${staticPath }/static/plugin/scroll/scroll-demo.js"></script>
    <script src="${staticPath }/static/plugin/metismenu-2.7.1/dist/metisMenu.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $('#menu1').metisMenu();
            $("aside").niceScroll({
                cursorcolor:"#eee",
                cursoropacitymax:1,
                touchbehavior:false,
                cursorwidth:"6px",
                cursorborder:"0",
                cursorborderradius:"5px"
            });

        })
    </script>
