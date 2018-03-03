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
    <link rel="stylesheet" href="${path}/static/aceadmin/assets/css/font-awesome.css" />
    <link rel="stylesheet" href="${staticPath}/static/aceadmin/assets/js/layui/css/layui.css">
    <link rel="stylesheet" href="${staticPath}/static/plugin/select2/css/select2.min.css">
    <link rel="stylesheet" href="${staticPath}/static/css/default.css">
    <style type="text/css">


    </style>
</head>
<body >
<div class="layui-container-fluid">
 <div class="layui-row" >
 <%--<div class="layui-row" style="height:calc(100% - 55px);overflow:auto;">--%>
    <form class="layui-form"  id="addForm" style="margin:10px;">
    	<input type="hidden" name="id" value="${user.id}">
        <div class="layui-form-item" >
            <label class="layui-form-label">用户名<span style="color: red;">*</span> </label>
            <div class="layui-input-block">
                <input type="text" name="username" id="username" value="${user.username }" lay-verify="required"  autocomplete="off" placeholder="请输入用户名" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item" style="display:none">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-block">
                <input type="password" name="password" value="${user.password}"  autocomplete="off" placeholder="请输入密码" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">姓名<span style="color: red;">*</span></label>
            <div class="layui-input-block">
                <input type="text" name="name" value="${user.name }" autocomplete="off" placeholder="请输入姓名" class="layui-input" id="name" >
            </div>
        </div>
        
       <%-- <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
             <c:if test="${user.sex==1}">
             	<input type="radio" name="sex" value="1" title="男" checked="">
                <input type="radio" name="sex" value="0" title="女">
             </c:if>
             <c:if test="${user.sex==0}">
             	<input type="radio" name="sex" value="1" title="男">
                <input type="radio" name="sex" value="0" title="女" checked="">
             </c:if>
                
            </div>
        </div>--%>
       <%-- <div class="layui-form-item">
                <label class="layui-form-label">职务/工种</label>
                <div class="layui-input-block">
                    <input type="text" name="workType" id="workType" placeholder="请输入" value="${user.workType}"   autocomplete="off" class="layui-input">
                </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-block">
          		<c:if test="${user.status==1}">
                  <input type="checkbox" name="status" value="1" lay-skin="switch" lay-text="有效|无效" checked>
          		</c:if>
          		<c:if test="${user.status!=1}">
                  <input type="checkbox" name="status" value="1" lay-skin="switch" lay-text="有效|无效">
          		</c:if>
            </div>
        </div>
        <div class="layui-form-item">
                <label class="layui-form-label">直接上级</label>
                <div class="layui-input-block">
                    <input type="text" name="leaderName" id="leaderName" value="${user.leaderName}" placeholder="选择直接上级"   autocomplete="off" class="layui-input">
                    <input type="hidden" name="leader" id="leader" value="${user.leader}">
                </div>
        </div>
        <div class="layui-form-item">
                <label class="layui-form-label">所属项目</label>
                <div class="layui-input-block">
                    <input type="text" name="projectName" id="projectName" value="${project.name}" placeholder="选择所属项目"   autocomplete="off" class="layui-input">
                    <input type="hidden" name="pjId" id="pjId" value="${project.id}">
                </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">年龄</label>
                <div class="layui-input-inline">
                    <input type="number" name="age" lay-verify="age"  autocomplete="off" class="layui-input" value="${user.age}">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">手机<span style="color: red;">*</span></label>
            <div class="layui-input-block">
                <input type="tel" name="phone" lay-verify="required"  autocomplete="off" placeholder="请输入手机号码" class="layui-input" value="${user.phone}">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">民族</label>
            <div class="layui-input-block">
                <input type="text" name="national"   autocomplete="off" placeholder="请输入民族" class="layui-input"  value="${user.national}">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">文化程度</label>
            <div class="layui-input-block">
                <input type="text" name="education"   autocomplete="off" placeholder="请输入" class="layui-input"  value="${user.education}">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">进场日期</label>
            <div class="layui-input-block">
                <input type="text" name="enterDay" id="enterDay"  autocomplete="off" placeholder="yyyy-MM-dd" class="layui-input"  value="${user.birthday}">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">出生日期</label>
            <div class="layui-input-block">
                <input type="text" name="birthday" id="birthday"  autocomplete="off" placeholder="yyyy-MM-dd" class="layui-input"  value="${user.enterDay}">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">身份证号码</label>
            <div class="layui-input-block">
                <input type="text" name="idNum"  autocomplete="off" placeholder="请输入身份证号码" class="layui-input"  value="${user.idNum}">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">用户类型</label>
            <div class="layui-input-block">
                  <select name="userType">
                        <option value="0" <c:if test="${user.userType==0}">selected</c:if>>管理员</option>
                        <option value="1" <c:if test="${user.userType==1}">selected</c:if>>用户</option>
                    </select>
            </div>
        </div>--%>
        <div class="layui-form-item">
            <label class="layui-form-label">部门<span style="color: red;">*</span></label>
            <div class="layui-input-block">
                  <input type="text" name="orgName" value="${user.orgName}"  id="orgName"   autocomplete="off" placeholder="请选择部门" class="layui-input">
                  <input type="hidden" name="orgid" id="orgid" value="${user.orgid}">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">角色</label>
            <div class="layui-input-block">
                <c:forEach var="role" items="${roles}">
                   <c:if test="${checkRoles.size()>0}">
                        <c:set var="status" value="0"/>
                        <c:forEach var="checkRole" items="${checkRoles}">
                            <c:if test="${checkRole.id==role.id}">
                                <c:set var="status" value="1"/>
                            </c:if>
                        </c:forEach>
                        <input type="checkbox" name="roleId"  title="${role.description}" value="${role.id}" <c:if test="${status==1}">checked</c:if> >
                    </c:if>
                    <c:if test="${checkRoles.size()==0}">
                        <input type="checkbox" name="roleId"  title="${role.description}" value="${role.id}" >
                    </c:if>
                </c:forEach>
            </div>
        </div>
         <%--<div class="layui-form-item" style="position: fixed;bottom:0px;width:100%;">--%>
    <%--<hr>--%>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="login">立即提交</button>
                
            </div>
    </div>
    </form>
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

<script src="${path}/static/aceadmin/assets/js/layui/layui.js"></script>
<script src="${path}/static/js/util.js"></script>
<script type="text/javascript">
    layui.use(['form','laydate'], function() {
        var form = layui.form;
        var $=layui.jquery
        ,laydate = layui.laydate;
        function refresh() {
            parent.$('.btn-refresh').click();
        }
        laydate.render({
            elem: '#birthday'
          });
        laydate.render({
            elem: '#enterDay'
          });
        //监听提交
        form.on('submit(login)', function(form){
            var roleIds=[];
            $('[name=roleId]').each(function () {
                if($(this).next().hasClass('layui-form-checked')){
                    roleIds.push($(this).val());
                }
            }); 
           form.field.roleIdList=roleIds.join(',');
             $.ajax({
                url: basePath+"/user/edit",
                data: form.field,
                dataType:'json',
                type:'post',
                success:function(data){
                    if(data.success){
                        $.layerClose();
                        $.layerMsg(data.msg, "success");
                        refresh();
                    }else{
                        $.layerMsg(data.msg, "warning");
                    }
                },
                error:function(error){
                	$.layerMsg("修改出错", "error");
					$.layerClose();
                }
            }); 
            return false;
        });
        $('#orgName').focus(function(){
        	 layer.open({
        	        type: 2,
        	        title: '选择部门用户',
        	        shadeClose: true,
        	        shade: false,
        	        maxmin: true,
        	        area: ['60%', '80%'],
        	        content: '${ctxPath}/jsp/org/orgSelect.jsp', //iframe的url
        	  });
			this.blur();
         });
        $('#projectName').focus(function(){
        	 layer.open({
        	        type: 2,
        	        title: '选择所属项目',
        	        shadeClose: true,
        	        shade: false,
        	        maxmin: true,
        	        area: ['60%', '80%'],
        	        content: '${ctxPath}/jsp/project/projectSelect.jsp', //iframe的url
        	  });
			this.blur();
         });
        $('#leaderName').focus(function(){
        	 layer.open({
        	        type: 2,
        	        title: '选择直接上级',
        	        shadeClose: true,
        	        shade: false,
        	        maxmin: true,
        	        area: ['80%', '90%'],
        	        content: '${ctxPath}/jsp/user/userSelect.jsp', //iframe的url
        	  });
			this.blur();
         });
        $('#username').change(function () {
            var newUsername = $(this).val();
            var oldUsername="${user.username}";
            if (newUsername!=oldUsername) {
                $.formSubmit({
                    url: basePath+'/user/valid',
                    data: { username: newUsername },
                    close: false
                });
            }
            return false;
        });
        form.verify({
            age:[
                /^([1]?\d{1,2})$/
                ,'年龄范围是0-100'
            ]
        })

		
    });

</script>
</body>
</html>
