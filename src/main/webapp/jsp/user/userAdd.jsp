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
 <%--<div class="layui-row" style="height:calc(100% - 55px);overflow:auto;">--%>
 <div class="layui-row" style="overflow-y:auto;overflow-x: hidden">
    <form class="layui-form"  id="addForm" style="margin:10px;">
        <div class="layui-form-item" >
            <label class="layui-form-label">用户名<span style="color: red;">*</span> </label>
            <div class="layui-input-block">
                <input type="text" name="username" id="username" lay-verify="required"  autocomplete="off" placeholder="请输入用户名" class="layui-input">
            </div>
        </div>
         <div class="layui-form-item">
            <label class="layui-form-label">密码<span style="color: red;">*</span></label>
            <div class="layui-input-block">
                <input type="password" name="password" lay-verify="pass"  autocomplete="off" placeholder="请输入密码" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">姓名<span style="color: red;">*</span></label>
            <div class="layui-input-block">
                <input type="text" name="name" lay-verify="required"  autocomplete="off" placeholder="请输入姓名" class="layui-input" id="name" >
            </div>
        </div>
        
       <%-- <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <input type="radio" name="sex" value="1" title="男" checked="">
                <input type="radio" name="sex" value="0" title="女">
            </div>
        </div>
        <div class="layui-form-item">
                <label class="layui-form-label">职务/工种</label>
                <div class="layui-input-block">
                    <input type="text" name="workType" id="workType" placeholder="请输入"   autocomplete="off" class="layui-input">
                </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-block">
                <input type="checkbox" name="status" value="1" lay-skin="switch" lay-text="有效|无效" checked>
            </div>
        </div>
        <div class="layui-form-item">
                <label class="layui-form-label">直接上级</label>
                <div class="layui-input-block">
                    <input type="text" name="leaderName" id="leaderName" placeholder="选择直接上级"   autocomplete="off" class="layui-input">
                    <input type="hidden" name="leader" id="leader">
                </div>
        </div>
        <div class="layui-form-item">
                <label class="layui-form-label">所属项目</label>
                <div class="layui-input-block">
                    <input type="text" name="projectName" id="projectName" placeholder="选择所属项目"   autocomplete="off" class="layui-input">
                    <input type="hidden" name="pjId" id="pjId">
                </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">年龄</label>
                <div class="layui-input-inline">
                    <input type="number" name="age" lay-verify="age"  autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">手机<span style="color: red;">*</span></label>
            <div class="layui-input-block">
                <input type="tel" name="phone" lay-verify="required"  autocomplete="off" placeholder="请输入手机号码" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">民族</label>
            <div class="layui-input-block">
                <input type="text" name="national"   autocomplete="off" placeholder="请输入民族" class="layui-input"  >
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">文化程度</label>
            <div class="layui-input-block">
                <input type="text" name="education"   autocomplete="off" placeholder="请输入" class="layui-input"  >
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">进场日期</label>
            <div class="layui-input-block">
                <input type="text" name="enterDay" id="enterDay"  autocomplete="off" placeholder="yyyy-MM-dd" class="layui-input"  >
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">出生日期</label>
            <div class="layui-input-block">
                <input type="text" name="birthday" id="birthday"  autocomplete="off" placeholder="yyyy-MM-dd" class="layui-input"  >
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">身份证号码</label>
            <div class="layui-input-block">
                <input type="text" name="idNum"  autocomplete="off" placeholder="请输入身份证号码" class="layui-input"  >
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">用户类型</label>
            <div class="layui-input-block">
                <select name="userType">
                    <option value="0">管理员</option>
                    <option value="1" selected>用户</option>
                </select>
            </div>
        </div>--%>
        <div class="layui-form-item">
            <label class="layui-form-label">部门<span style="color: red;">*</span></label>
            <div class="layui-input-block">
                  <input type="text" name="orgName" id="orgName"   autocomplete="off" placeholder="请选择部门" class="layui-input">
                  <input type="hidden" name="orgid" id="orgid">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">角色</label>
            <div class="layui-input-block">
                <c:forEach var="role" items="${roles}">
                    <input type="checkbox" name="roleId"  title="${role.description}" value="${role.id}">
                </c:forEach>
            </div>
        </div>
         <div class="layui-form-item" >
         <%--<div class="layui-form-item" style="position: fixed;bottom:0px;width:100%;">--%>
    <%--<hr>--%>
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="login">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                
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
                url: basePath+"/user/add",
                data: form.field,
                dataType:'json',
                type:'post',
                success:function(data){
					//data=eval('('+data+')');
					if(data.success){
                        $.layerClose();
                        $.layerMsg(data.msg, "success");
						refresh();
					}else{
                        $.layerMsg(data.msg, "warning");
                    }
                },
                error:function(error){
                	$.layerMsg("添加出错", "error");
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
        $('#username').blur(function () {
            var username = $('#username').val();
            if (username && username.length > 0) {
                $.formSubmit({
                    url: basePath+'/user/valid',
                    data: { username: username },
                    close: false
                });
            }
            return false;
        });
        form.verify({
            pass: [
            /^[\S]{6,12}$/
            ,'密码必须6到12位，且不能出现空格'
            ],
            age:[
                /^([1]?\d{1,2})$/
                ,'年龄范围是0-100'
            ]
        })

    });

</script>
</body>
</html>
