/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: jetty/9.3.10.v20160621
 * Generated at: 2018-03-02 09:27:37 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.common;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class navbar_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

final java.lang.String _jspx_method = request.getMethod();
if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
return;
}

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<div id=\"navbar\" class=\"navbar navbar-default navbar-fixed-top\">\r\n");
      out.write("    <script type=\"text/javascript\">\r\n");
      out.write("        try{ace.settings.check('navbar' , 'fixed')}catch(e){}\r\n");
      out.write("    </script>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"navbar-container\" id=\"navbar-container\">\r\n");
      out.write("        <!-- #section:basics/sidebar.mobile.toggle -->\r\n");
      out.write("        <button type=\"button\" class=\"navbar-toggle menu-toggler pull-left\" id=\"menu-toggler\" data-target=\"#sidebar\">\r\n");
      out.write("            <span class=\"sr-only\">Toggle sidebar</span>\r\n");
      out.write("\r\n");
      out.write("            <span class=\"icon-bar\"></span>\r\n");
      out.write("\r\n");
      out.write("            <span class=\"icon-bar\"></span>\r\n");
      out.write("\r\n");
      out.write("            <span class=\"icon-bar\"></span>\r\n");
      out.write("        </button>\r\n");
      out.write("\r\n");
      out.write("        <!-- /section:basics/sidebar.mobile.toggle -->\r\n");
      out.write("        <div class=\"navbar-header pull-left\">\r\n");
      out.write("            <!-- #section:basics/navbar.layout.brand -->\r\n");
      out.write("            <a href=\"#\" class=\"navbar-brand\">\r\n");
      out.write("                <small>\r\n");
      out.write("                    <i class=\"fa fa-leaf\"></i>\r\n");
      out.write("                   Beetn\r\n");
      out.write("                </small>\r\n");
      out.write("            </a>\r\n");
      out.write("\r\n");
      out.write("            <!-- /section:basics/navbar.layout.brand -->\r\n");
      out.write("\r\n");
      out.write("            <!-- #section:basics/navbar.toggle -->\r\n");
      out.write("\r\n");
      out.write("            <!-- /section:basics/navbar.toggle -->\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <!-- #section:basics/navbar.dropdown -->\r\n");
      out.write("        <div class=\"navbar-buttons navbar-header pull-right\" role=\"navigation\">\r\n");
      out.write("            <ul class=\"nav ace-nav\">\r\n");
      out.write("               <!--   <li class=\"grey\">\r\n");
      out.write("                    <a data-toggle=\"dropdown\" class=\"dropdown-toggle\" href=\"#\">\r\n");
      out.write("                        <i class=\"ace-icon fa fa-tasks\"></i>\r\n");
      out.write("                        <span class=\"badge badge-grey\">4</span>\r\n");
      out.write("                    </a>\r\n");
      out.write("\r\n");
      out.write("                    <ul class=\"dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close\">\r\n");
      out.write("                        <li class=\"dropdown-header\">\r\n");
      out.write("                            <i class=\"ace-icon fa fa-check\"></i>\r\n");
      out.write("                            4 Tasks to complete\r\n");
      out.write("                        </li>\r\n");
      out.write("\r\n");
      out.write("                        <li class=\"dropdown-content\">\r\n");
      out.write("                            <ul class=\"dropdown-menu dropdown-navbar\">\r\n");
      out.write("                                <li>\r\n");
      out.write("                                    <a href=\"#\">\r\n");
      out.write("                                        <div class=\"clearfix\">\r\n");
      out.write("                                            <span class=\"pull-left\">Software Update</span>\r\n");
      out.write("                                            <span class=\"pull-right\">65%</span>\r\n");
      out.write("                                        </div>\r\n");
      out.write("\r\n");
      out.write("                                        <div class=\"progress progress-mini\">\r\n");
      out.write("                                            <div style=\"width:65%\" class=\"progress-bar\"></div>\r\n");
      out.write("                                        </div>\r\n");
      out.write("                                    </a>\r\n");
      out.write("                                </li>\r\n");
      out.write("\r\n");
      out.write("                                <li>\r\n");
      out.write("                                    <a href=\"#\">\r\n");
      out.write("                                        <div class=\"clearfix\">\r\n");
      out.write("                                            <span class=\"pull-left\">Hardware Upgrade</span>\r\n");
      out.write("                                            <span class=\"pull-right\">35%</span>\r\n");
      out.write("                                        </div>\r\n");
      out.write("\r\n");
      out.write("                                        <div class=\"progress progress-mini\">\r\n");
      out.write("                                            <div style=\"width:35%\" class=\"progress-bar progress-bar-danger\"></div>\r\n");
      out.write("                                        </div>\r\n");
      out.write("                                    </a>\r\n");
      out.write("                                </li>\r\n");
      out.write("\r\n");
      out.write("                                <li>\r\n");
      out.write("                                    <a href=\"#\">\r\n");
      out.write("                                        <div class=\"clearfix\">\r\n");
      out.write("                                            <span class=\"pull-left\">Unit Testing</span>\r\n");
      out.write("                                            <span class=\"pull-right\">15%</span>\r\n");
      out.write("                                        </div>\r\n");
      out.write("\r\n");
      out.write("                                        <div class=\"progress progress-mini\">\r\n");
      out.write("                                            <div style=\"width:15%\" class=\"progress-bar progress-bar-warning\"></div>\r\n");
      out.write("                                        </div>\r\n");
      out.write("                                    </a>\r\n");
      out.write("                                </li>\r\n");
      out.write("\r\n");
      out.write("                                <li>\r\n");
      out.write("                                    <a href=\"#\">\r\n");
      out.write("                                        <div class=\"clearfix\">\r\n");
      out.write("                                            <span class=\"pull-left\">Bug Fixes</span>\r\n");
      out.write("                                            <span class=\"pull-right\">90%</span>\r\n");
      out.write("                                        </div>\r\n");
      out.write("\r\n");
      out.write("                                        <div class=\"progress progress-mini progress-striped active\">\r\n");
      out.write("                                            <div style=\"width:90%\" class=\"progress-bar progress-bar-success\"></div>\r\n");
      out.write("                                        </div>\r\n");
      out.write("                                    </a>\r\n");
      out.write("                                </li>\r\n");
      out.write("                            </ul>\r\n");
      out.write("                        </li>\r\n");
      out.write("\r\n");
      out.write("                        <li class=\"dropdown-footer\">\r\n");
      out.write("                            <a href=\"#\">\r\n");
      out.write("                                See tasks with details\r\n");
      out.write("                                <i class=\"ace-icon fa fa-arrow-right\"></i>\r\n");
      out.write("                            </a>\r\n");
      out.write("                        </li>\r\n");
      out.write("                    </ul>\r\n");
      out.write("                </li>\r\n");
      out.write("\r\n");
      out.write("                <li class=\"purple\">\r\n");
      out.write("                    <a data-toggle=\"dropdown\" class=\"dropdown-toggle\" href=\"#\">\r\n");
      out.write("                        <i class=\"ace-icon fa fa-bell icon-animated-bell\"></i>\r\n");
      out.write("                        <span class=\"badge badge-important\">8</span>\r\n");
      out.write("                    </a>\r\n");
      out.write("\r\n");
      out.write("                    <ul class=\"dropdown-menu-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close\">\r\n");
      out.write("                        <li class=\"dropdown-header\">\r\n");
      out.write("                            <i class=\"ace-icon fa fa-exclamation-triangle\"></i>\r\n");
      out.write("                            8 Notifications\r\n");
      out.write("                        </li>\r\n");
      out.write("\r\n");
      out.write("                        <li class=\"dropdown-content\">\r\n");
      out.write("                            <ul class=\"dropdown-menu dropdown-navbar navbar-pink\">\r\n");
      out.write("                                <li>\r\n");
      out.write("                                    <a href=\"#\">\r\n");
      out.write("                                        <div class=\"clearfix\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"pull-left\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<i class=\"btn btn-xs no-hover btn-pink fa fa-comment\"></i>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tNew Comments\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t</span>\r\n");
      out.write("                                            <span class=\"pull-right badge badge-info\">+12</span>\r\n");
      out.write("                                        </div>\r\n");
      out.write("                                    </a>\r\n");
      out.write("                                </li>\r\n");
      out.write("\r\n");
      out.write("                                <li>\r\n");
      out.write("                                    <a href=\"#\">\r\n");
      out.write("                                        <i class=\"btn btn-xs btn-primary fa fa-user\"></i>\r\n");
      out.write("                                        Bob just signed up as an editor ...\r\n");
      out.write("                                    </a>\r\n");
      out.write("                                </li>\r\n");
      out.write("\r\n");
      out.write("                                <li>\r\n");
      out.write("                                    <a href=\"#\">\r\n");
      out.write("                                        <div class=\"clearfix\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"pull-left\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<i class=\"btn btn-xs no-hover btn-success fa fa-shopping-cart\"></i>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tNew Orders\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t</span>\r\n");
      out.write("                                            <span class=\"pull-right badge badge-success\">+8</span>\r\n");
      out.write("                                        </div>\r\n");
      out.write("                                    </a>\r\n");
      out.write("                                </li>\r\n");
      out.write("\r\n");
      out.write("                                <li>\r\n");
      out.write("                                    <a href=\"#\">\r\n");
      out.write("                                        <div class=\"clearfix\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"pull-left\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<i class=\"btn btn-xs no-hover btn-info fa fa-twitter\"></i>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tFollowers\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t</span>\r\n");
      out.write("                                            <span class=\"pull-right badge badge-info\">+11</span>\r\n");
      out.write("                                        </div>\r\n");
      out.write("                                    </a>\r\n");
      out.write("                                </li>\r\n");
      out.write("                            </ul>\r\n");
      out.write("                        </li>\r\n");
      out.write("\r\n");
      out.write("                        <li class=\"dropdown-footer\">\r\n");
      out.write("                            <a href=\"#\">\r\n");
      out.write("                                See all notifications\r\n");
      out.write("                                <i class=\"ace-icon fa fa-arrow-right\"></i>\r\n");
      out.write("                            </a>\r\n");
      out.write("                        </li>\r\n");
      out.write("                    </ul>\r\n");
      out.write("                </li>\r\n");
      out.write("\r\n");
      out.write("                <li class=\"green\">\r\n");
      out.write("                    <a data-toggle=\"dropdown\" class=\"dropdown-toggle\" href=\"#\">\r\n");
      out.write("                        <i class=\"ace-icon fa fa-envelope icon-animated-vertical\"></i>\r\n");
      out.write("                        <span class=\"badge badge-success\">5</span>\r\n");
      out.write("                    </a>\r\n");
      out.write("\r\n");
      out.write("                    <ul class=\"dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close\">\r\n");
      out.write("                        <li class=\"dropdown-header\">\r\n");
      out.write("                            <i class=\"ace-icon fa fa-envelope-o\"></i>\r\n");
      out.write("                            5 Messages\r\n");
      out.write("                        </li>\r\n");
      out.write("\r\n");
      out.write("                        <li class=\"dropdown-content\">\r\n");
      out.write("                            <ul class=\"dropdown-menu dropdown-navbar\">\r\n");
      out.write("                                <li>\r\n");
      out.write("                                    <a href=\"#\" class=\"clearfix\">\r\n");
      out.write("                                        <img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${path}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/static/aceadmin/assets/avatars/avatar.png\" class=\"msg-photo\" alt=\"Alex's Avatar\" />\r\n");
      out.write("                                        <span class=\"msg-body\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"msg-title\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"blue\">Alex:</span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tCiao sociis natoque penatibus et auctor ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${path}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/static/aceadmin.\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t</span>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"msg-time\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<i class=\"ace-icon fa fa-clock-o\"></i>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span>a moment ago</span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t</span>\r\n");
      out.write("                                    </a>\r\n");
      out.write("                                </li>\r\n");
      out.write("\r\n");
      out.write("                                <li>\r\n");
      out.write("                                    <a href=\"#\" class=\"clearfix\">\r\n");
      out.write("                                        <img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${path}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/static/aceadmin/assets/avatars/avatar3.png\" class=\"msg-photo\" alt=\"Susan's Avatar\" />\r\n");
      out.write("                                        <span class=\"msg-body\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"msg-title\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"blue\">Susan:</span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tVestibulum id ligula porta felis euismod ...\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t</span>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"msg-time\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<i class=\"ace-icon fa fa-clock-o\"></i>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span>20 minutes ago</span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t</span>\r\n");
      out.write("                                    </a>\r\n");
      out.write("                                </li>\r\n");
      out.write("\r\n");
      out.write("                                <li>\r\n");
      out.write("                                    <a href=\"#\" class=\"clearfix\">\r\n");
      out.write("                                        <img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${path}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/static/aceadmin/assets/avatars/avatar4.png\" class=\"msg-photo\" alt=\"Bob's Avatar\" />\r\n");
      out.write("                                        <span class=\"msg-body\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"msg-title\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"blue\">Bob:</span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tNullam quis risus eget urna mollis ornare ...\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t</span>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"msg-time\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<i class=\"ace-icon fa fa-clock-o\"></i>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span>3:15 pm</span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t</span>\r\n");
      out.write("                                    </a>\r\n");
      out.write("                                </li>\r\n");
      out.write("\r\n");
      out.write("                                <li>\r\n");
      out.write("                                    <a href=\"#\" class=\"clearfix\">\r\n");
      out.write("                                        <img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${path}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/static/aceadmin/assets/avatars/avatar2.png\" class=\"msg-photo\" alt=\"Kate's Avatar\" />\r\n");
      out.write("                                        <span class=\"msg-body\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"msg-title\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"blue\">Kate:</span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tCiao sociis natoque eget urna mollis ornare ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${path}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/static/aceadmin.\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t</span>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"msg-time\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<i class=\"ace-icon fa fa-clock-o\"></i>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span>1:33 pm</span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t</span>\r\n");
      out.write("                                    </a>\r\n");
      out.write("                                </li>\r\n");
      out.write("\r\n");
      out.write("                                <li>\r\n");
      out.write("                                    <a href=\"#\" class=\"clearfix\">\r\n");
      out.write("                                        <img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${path}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/static/aceadmin/assets/avatars/avatar5.png\" class=\"msg-photo\" alt=\"Fred's Avatar\" />\r\n");
      out.write("                                        <span class=\"msg-body\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"msg-title\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"blue\">Fred:</span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tVestibulum id penatibus et auctor  ...\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t</span>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"msg-time\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<i class=\"ace-icon fa fa-clock-o\"></i>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span>10:09 am</span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t</span>\r\n");
      out.write("                                    </a>\r\n");
      out.write("                                </li>\r\n");
      out.write("                            </ul>\r\n");
      out.write("                        </li>\r\n");
      out.write("\r\n");
      out.write("                        <li class=\"dropdown-footer\">\r\n");
      out.write("                            <a href=\"inbox.html\">\r\n");
      out.write("                                See all messages\r\n");
      out.write("                                <i class=\"ace-icon fa fa-arrow-right\"></i>\r\n");
      out.write("                            </a>\r\n");
      out.write("                        </li>\r\n");
      out.write("                    </ul>\r\n");
      out.write("                </li> -->\r\n");
      out.write("                <!-- #section:basics/navbar.user_menu -->\r\n");
      out.write("                <li class=\"light-blue\">\r\n");
      out.write("                    <a data-toggle=\"dropdown\" href=\"#\" class=\"dropdown-toggle\">\r\n");
      out.write("                        <img class=\"nav-user-photo\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${path}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/static/aceadmin/assets/avatars/user.jpg\" alt=\"Jason's Photo\" />\r\n");
      out.write("                        <span class=\"user-info\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<small>欢迎你,</small>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sessionScope.user.name}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t</span>\r\n");
      out.write("\r\n");
      out.write("                        <i class=\"ace-icon fa fa-caret-down\"></i>\r\n");
      out.write("                    </a>\r\n");
      out.write("\r\n");
      out.write("                    <ul class=\"user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close\">\r\n");
      out.write("                       <!--  <li>\r\n");
      out.write("                            <a href=\"#\">\r\n");
      out.write("                                <i class=\"ace-icon fa fa-cog\"></i>\r\n");
      out.write("                                Settings\r\n");
      out.write("                            </a>\r\n");
      out.write("                        </li>\r\n");
      out.write(" -->\r\n");
      out.write("                        <li>\r\n");
      out.write("                            <a href=\"profile.html\">\r\n");
      out.write("                                <i class=\"ace-icon fa fa-user\"></i>\r\n");
      out.write("                                                        个人基本信息\r\n");
      out.write("                            </a>\r\n");
      out.write("                        </li>\r\n");
      out.write("                        <li class=\"divider\"></li>\r\n");
      out.write("\r\n");
      out.write("                        <li>\r\n");
      out.write("                            <a href=\"javascript:void(0);\" id=\"logout\">\r\n");
      out.write("                                <i class=\"ace-icon fa fa-power-off\"></i>\r\n");
      out.write("                                退出\r\n");
      out.write("                            </a>\r\n");
      out.write("                        </li>\r\n");
      out.write("                    </ul>\r\n");
      out.write("                </li>\r\n");
      out.write("\r\n");
      out.write("                <!-- /section:basics/navbar.user_menu -->\r\n");
      out.write("            </ul>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <!-- /section:basics/navbar.dropdown -->\r\n");
      out.write("    </div><!-- /.navbar-container -->\r\n");
      out.write("</div>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
