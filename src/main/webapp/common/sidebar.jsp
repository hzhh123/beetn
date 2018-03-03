<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="nav nav-list">
    <li class="active">
        <a href="${ctxPath}/index">
            <i class="menu-icon fa fa-tachometer"></i>
            <span class="menu-text"> 主页 </span>
        </a>

        <b class="arrow"></b>
    </li>
    <c:forEach var="menuLV1" items="${sessionScope.menus}">
        <li class="">
            <c:choose>
        <c:when test="${menuLV1.childMenus.size()!=0}">
            <a href="${menuLV1.url}" class="dropdown-toggle" data-level="${menuLV1.level}" <c:if test="${menuLV1.target!=null&&!menuLV1.target.equals(\"\")}">target="_blank"</c:if>>
                </c:when>
                <c:otherwise>
                <a href="${menuLV1.url}" data-level="${menuLV1.level}" <c:if test="${menuLV1.target!=null&&!menuLV1.target.equals(\"\")}">target="_blank"</c:if>></c:otherwise></c:choose>
                    <i class="menu-icon ${menuLV1.icon}"></i>
                    <span class="menu-text"> ${menuLV1.name} </span>
                    <c:if test="${menuLV1.childMenus.size()!=0}">
                        <b class="arrow fa fa-angle-down"></b>
                    </c:if>
                </a>
                <b class="arrow"></b>
                <c:if test="${menuLV1.childMenus.size()!=0}">
                <ul class="submenu">
                    <c:forEach var="menuLV2" items="${menuLV1.childMenus}">
                        <li class="">
                            <c:choose>
                            <c:when test="${menuLV2.childMenus.size()!=0}">
                            <a href="${menuLV2.url}" class="dropdown-toggle" data-level="${menuLV2.level}" <c:if test="${menuLV2.target!=null&&!menuLV2.target.equals(\"\")}">target="_blank"</c:if>>
                                </c:when>
                                <c:otherwise>
                                <a href="${menuLV2.url}" data-level="${menuLV2.level}" <c:if test="${menuLV2.target!=null&&!menuLV2.target.equals(\"\")}">target="_blank"</c:if>>
                                    </c:otherwise></c:choose>
                                    <i class="menu-icon ${menuLV2.icon}"></i>
                                        ${menuLV2.name}
                                    <c:if test="${menuLV2.childMenus.size()!=0}">
                                        <b class="arrow fa fa-angle-down"></b>
                                    </c:if>
                                </a>

                                <b class="arrow"></b>
                                <c:if test="${menuLV2.childMenus.size()!=0}">
                                <ul class="submenu">
                                    <c:forEach var="menuLV3" items="${menuLV2.childMenus}">
                                        <li class="">
                                            <c:choose>
                                            <c:when test="${menuLV3.childMenus.size()!=0}">
                                            <a href="${menuLV3.url}" class="dropdown-toggle" data-level="${menuLV3.level}" <c:if test="${menuLV3.target!=null&&!menuLV3.target.equals(\"\")}">target="_blank"</c:if>>
                                                </c:when>
                                                <c:otherwise>
                                                <a href="${menuLV3.url}" data-level="${menuLV3.level}" <c:if test="${menuLV3.target!=null&&!menuLV3.target.equals(\"\")}">target="_blank"</c:if>>
                                                    </c:otherwise></c:choose>
                                                    <i class="menu-icon ${menuLV3.icon}"></i>
                                                        ${menuLV3.name}
                                                    <c:if test="${menuLV3.childMenus.size()!=0}">
                                                        <b class="arrow fa fa-angle-down"></b>
                                                    </c:if>
                                                </a>

                                                <b class="arrow"></b>
                                                <c:if test="${menuLV3.childMenus.size()!=0}">
                                                <ul class="submenu">
                                                    <c:forEach var="menuLV4" items="${menuLV3.childMenus}">
                                                        <li class="">
                                                            <c:choose>
                                                            <c:when test="${menuLV4.childMenus.size()!=0}">
                                                            <a href="${menuLV4.url}" class="dropdown-toggle" data-level="${menuLV4.level}" <c:if test="${menuLV4.target!=null&&!menuLV4.target.equals(\"\")}">target="_blank"</c:if>>
                                                                </c:when>
                                                                <c:otherwise>
                                                                <a href="${menuLV4.url}" data-level="${menuLV4.level}" <c:if test="${menuLV4.target!=null&&!menuLV4.target.equals(\"\")}">target="_blank"</c:if>>
                                                                    </c:otherwise></c:choose>
                                                                    <i class="menu-icon ${menuLV4.icon}"></i>
                                                                        ${menuLV4.name}
                                                                    <c:if test="${menuLV4.childMenus.size()!=0}">
                                                                        <b class="arrow fa fa-angle-down"></b>
                                                                    </c:if>
                                                                </a>

                                                                <b class="arrow"></b>

                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                                </c:if>
                                        </li>
                                    </c:forEach>
                                </ul>
                                </c:if>

                        </li>
                    </c:forEach>
                </ul>
                </c:if>
        </li>
    </c:forEach>
</ul>
<!-- /.nav-list -->