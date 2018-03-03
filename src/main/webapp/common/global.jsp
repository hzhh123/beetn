<%--标签 --%>
<%@ page trimDirectiveWhitespaces="true" isELIgnored="false" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}" />
<c:set var="path" value="${ctxPath}" />
<c:set var="staticPath" value="${ctxPath}" />
<script type="text/javascript">
    var basePath = "${staticPath }";
    window.UEDITOR_HOME_URL = "${staticPath }/static/ueditor/";
    window.UEDITOR_SERVER_URL = "${staticPath }/ueditor";
</script>