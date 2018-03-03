<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/12/21
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<script type="text/javascript">
    if('ontouchstart' in document.documentElement) document.write("<script src='${path}/static/aceadmin/assets/js/jquery.mobile.custom.js'>"+"<"+"/script>");
</script>
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
<script src="${path}/static/aceadmin/assets/js/layer/layer.js"></script>
<script src="${ctxPath}/static/aceadmin/assets/js/layui/layui.all.js"></script>
<script src="${path}/static/js/common.js"></script>
<script src="${ctxPath}/static/js/util.js"></script>
<script src="${ctxPath}/static/aceadmin/assets/js/select2.js"></script>
<script src="${ctx}/static/plugin/jstree/dist/jstree.min.js"></script>
<script src="${path}/static/plugin/scroll/jquery.nicescroll.min.js"></script>
<!-- the following scripts are used in demo only for onpage help and you don't need them -->
<link rel="stylesheet" href="${path}/static/aceadmin/assets/css/ace.onpage-help.css" />
<link rel="stylesheet" href="${path}/static/aceadmin/docs/assets/js/themes/sunburst.css" />

<script type="text/javascript"> ace.vars['base'] = '${path}/static/aceadmin'; </script>
<script src="${path}/static/aceadmin/assets/js/ace/elements.onpage-help.js"></script>
<script src="${path}/static/aceadmin/assets/js/ace/ace.onpage-help.js"></script>
<script src="${path}/static/aceadmin/docs/assets/js/rainbow.js"></script>
<script src="${path}/static/aceadmin/docs/assets/js/language/generic.js"></script>
<script src="${path}/static/aceadmin/docs/assets/js/language/html.js"></script>
<script src="${path}/static/aceadmin/docs/assets/js/language/css.js"></script>
<script src="${path}/static/aceadmin/docs/assets/js/language/javascript.js"></script>