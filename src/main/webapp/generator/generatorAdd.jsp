<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/26
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/global.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title></title>
    <meta name="description" content="overview &amp; stats" />
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    <link rel="stylesheet"
          href="${path}/static/aceadmin/assets/css/font-awesome.css" />
    <link rel="stylesheet"
          href="${staticPath}/static/aceadmin/assets/js/layui/css/layui.css">
    <link rel="stylesheet"
          href="${staticPath}/static/plugin/select2/css/select2.min.css">
    <link rel="stylesheet" href="${staticPath}/static/css/default.css">
    <style type="text/css">
        .layui-layer.layui-layer-iframe {
            overflow: hidden;
            width: 100%;
        }
    </style>
</head>
<body>
<div class="layui-container-fluid" style="margin: 15px 10px;">
    <form class="layui-form layui-form-pane" id="addForm">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">表名<span style="color: red;">*</span>
                </label>
                <div class="layui-input-inline">
                    <input type="text" name="name"
                           lay-verify="required" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">描述</label>
                <div class="layui-input-inline">
                    <input type="text" name="description" id="description"
                           placeholder="请输入。。"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <button class="layui-btn layui-btn-normal" id="add">添加一行</button>
                    <button class="layui-btn" id="save">保存</button>
                </div>
            </div>
        </div>
    </form>
    <form class="layui-form" id="form2">
        <table class="layui-table" lay-even="" id="table">
            <colgroup>
                <col width="100">
                <col width="100">
                <col width="100">
                <col width="100">
                <col width="50">
                <col width="50">
                <col width="50">
                <col width="150">
                <col width="80">
                <col width="100">
                <col width="100">
                <col width="100">
                <col width="150">
                <col>
            </colgroup>
            <thead>
            <tr>
                <th>列名<span style="color:red">*</span></th>
                <th>数据类型<span style="color:red">*</span></th>
                <th>长度</th>
                <th>默认值</th>
                <th>主键</th>
                <th>非空</th>
                <th>自增</th>
                <th>注释<span style="color:red">*</span></th>
                <th>临时对象</th>
                <th>页面显示方式<span style="color:red">*</span></th>
                <th>是否是查询参数</th>
                <th>校验</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><input type="text" name="field" class="layui-input"
                           placeholder="请输入列名"></td>
                <td><select name="type" lay-filter="aihao">
                    <option value=""></option>
                    <option value="int">int</option>
                    <option value="varchar">varchar</option>
                    <option value="text">text</option>
                    <option value="bigint">bigint</option>
                    <option value="tinyint">tinyint</option>
                    <option value="date">date</option>
                    <option value="datetime">datetime</option>
                </select></td>
                <td><input type="text" name="length" class="layui-input">
                </td>
                <td><input type="text" name="defaultValue"
                           class="layui-input"></td>
                <td>
                    <input type="checkbox" name="key" value="1" lay-skin="primary" title="" lay-filter="key">
                </td>
                <td>
                    <input type="checkbox" name="isNull" value="1" lay-skin="primary" title="" class="isNull">
                </td>
                <td>
                    <input type="checkbox" name="extra" value="1" lay-skin="primary" title="" >
                </td>
                <td><input type="text" name="comment" class="layui-input"></td>
                <td>
                    <input type="checkbox" name="isTransient" value="1" lay-skin="primary" title="" >
                </td>
                <td><select name="showType" >
                    <option value="hidden">隐藏域</option>
                    <option value="text" selected>文本</option>
                    <option value="date">日期</option>
                    <option value="datetime">日期时间</option>
                    <option value="textarea">大文本</option>
                    <option value="radio">单选</option>
                    <option value="checkbox">多选</option>
                    <option value="numbere">数字</option>
                    <option value="email">邮箱</option>
                    <option value="tel">电话</option>
                    <option value="select">下拉选择</option>
                    <option value="select2">select2</option>
                </select></td>
                <td>
                    <input type="checkbox" name="isQuery" value="1" lay-skin="primary" title="" >
                </td>
                <td>
                    <select name="valid">
                        <option value="" selected></option>
                        <option value="required:true">必填</option>
                    </select>
                </td>
                <td>
                    <button class="layui-btn layui-btn-danger del layui-btn-mini">删除</button>
                    <button class="layui-btn layui-btn-normal up layui-btn-mini" >上移</button>
                    <button class="layui-btn layui-btn-warm down layui-btn-mini" >下移</button>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
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

<%--<script src="${path}/static/aceadmin/assets/js/layer/layer.js"></script>--%>
<script src="${path}/static/aceadmin/assets/js/layui/layui.js"></script>
<script src="${path}/static/plugin/select2/js/select2.min.js"></script>
<script src="${path}/static/js/util.js"></script>
<script type="text/javascript">
    layui.use(['form'], function() {
        var form = layui.form;
        var $=layui.jquery;
        function refresh() {
            parent.$('.btn-refresh').click();
        }
        $('[name=name]').blur(function(){
            $.ajax({
                url:basePath+'/generator/valid/'+$(this).val(),
                type:'post',
                dataType:'json',
                success:function(result){
                    if(result.success){
                        $.layerMsg(result.msg, 'warning');
                    }
                }
            })
        });
        $('#add').click(function(){
            $('#table tbody').append('<tr>'
                +'<td><input type="text" name="field" class="layui-input"'
                +'placeholder="请输入列名"></td>'
                +'<td><select name="type" lay-filter="aihao">'
                +'<option value=""></option>'
                +'<option value="int">int</option>'
                +'<option value="varchar">varchar</option>'
                +'<option value="text">text</option>'
                +'<option value="bigint">bigint</option>'
                +'<option value="tinyint">tinyint</option>'
                +'<option value="date">date</option>'
                +'<option value="datetime">datetime</option>'
                +'</select></td>'
                +'<td><input type="text" name="length" class="layui-input">'
                +'</td>'
                +'<td><input type="text" name="defaultValue"'
                +'class="layui-input"></td>'
                +'<td> <input type="checkbox" name="key" value="1" lay-skin="primary" title="" lay-filter="key">'
                +'</td>'
                +'<td> <input type="checkbox" name="isNull" value="1" lay-skin="primary" title="" >'
                +'</td>'
                +'<td> <input type="checkbox" name="extra" value="1" lay-skin="primary" title="" >'
                +'</td>'
                +'<td><input type="text" name="comment" class="layui-input"></td>'
                +'<td><input type="checkbox" name="isTransient" value="1" lay-skin="primary" title="" ></td>'
                +'<td><select name="showType" >'
                +'<option value="hidden">隐藏域</option>'
                +'<option value="text" selected>文本</option>'
                +'<option value="textarea">大文本</option>'
                +'<option value="date">日期</option>'
                +'<option value="datetime">日期时间</option>'
                +'<option value="radio">单选</option>'
                +'<option value="checkbox">多选</option>'
                +'<option value="numbere">数字</option>'
                +'<option value="email">邮箱</option>'
                +'<option value="tel">电话</option>'
                +'<option value="select">下拉选择</option>'
                +'<option value="select2">select2</option>'
                +'</select></td>'
                +'<td><input type="checkbox" name="isQuery" value="1" lay-skin="primary" title="" ></td>'
                +'<td>'
                +'<select name="valid">'
                +'<option value="" selected></option>'
                +'<option value="required:true">必填</option>'
                +'</select>'
                +'</td>'
                +'<td>'
                +'<button class="layui-btn layui-btn-danger layui-btn-mini del">删除</button>'
                +'<button class="layui-btn layui-btn-normal layui-btn-mini up" >上移</button>'
                +'<button class="layui-btn layui-btn-warm down layui-btn-mini" >下移</button>'
                +'</td>'
                +'</tr>');
            form.render();
            return false;
        });

        //删除
        $('#table tbody').on('click','.del',function(){
            var $this=$(this);
            $this.closest('tr').remove();
            return false;
        })
        //上移
        $('#table tbody').on('click','.up',function(){
            var current = $(this).parent().parent(); //获取当前<tr>
            var prev = current.prev();  //获取当前<tr>前一个元素
            if (current.index() > 0) {
                current.insertBefore(prev); //插入到当前<tr>前一个元素前
            }
            return false;
        })
        //下移
        $('#table tbody').on('click','.down',function(){
            var current = $(this).parent().parent(); //获取当前<tr>
            var next = current.next(); //获取当前<tr>后面一个元素
            if (next) {
                current.insertAfter(next);  //插入到当前<tr>后面一个元素后面
            }
            return false;
        })

        $('#save').click(function(){
            var tableName=$('[name=name]').val();
            var description=$('#description').val();
            if(tableName==""){
                layer.msg('表名不能为空');
                $('[name=name]').focus();
            }else{
                var fields=new Array();
                var types=new Array();
                var lengths=new Array();
                var defaultValues=new Array();
                var keys=new Array();
                var isNulls=new Array();
                var extras=new Array();
                var comments=new Array();
                var showTypes=new Array();
                var isTransients=new Array();
                var isQuerys=new Array();
                var valids=new Array();
                $('[name=field]').each(function(){
                    fields.push($(this).val());
                })
                $('[name=type]').each(function(){
                    types.push($(this).val());
                })
                $('[name=length]').each(function(){
                    lengths.push($(this).val()==""?"0":$(this).val());
                })
                $('[name=defaultValue]').each(function(){
                    defaultValues.push($(this).val()==""?"0":$(this).val());
                })
                $('[name=key]').each(function(){
                    if($(this).is(':checked')){
                        keys.push("1");
                    }else{
                        keys.push("0");
                    }

                })
                $('[name=isNull]').each(function(){
                    if($(this).is(':checked')){
                        isNulls.push("1");
                    }else{
                        isNulls.push("0");
                    }
                })
                $('[name=extra]').each(function(){
                    console.log($(this).is(':checked'))
                    if($(this).is(':checked')){
                        extras.push("1");
                    }else{
                        extras.push("0");
                    }
                })
                $('[name=isTransient]').each(function(){
                    if($(this).is(':checked')){
                        isTransients.push("1");
                    }else{
                        isTransients.push("0");
                    }
                })
                $('[name=isQuery]').each(function(){
                    if($(this).is(':checked')){
                        isQuerys.push("1");
                    }else{
                        isQuerys.push("0");
                    }
                })
                $('[name=valid] option:selected').each(function(){
                    valids.push($(this).val()==""?"0":$(this).val());
                })
                $('[name=showType] option:selected').each(function(){
                    showTypes.push($(this).val());
                })
                $('[name=comment]').each(function(){
                    comments.push($(this).val());
                })

                var i=0,j=0,k=0,l=0,n=0;
                $.each(fields,function(index,item){
                    if(item==""){
                        i++;
                    }
                })
                $.each(keys,function(index,item){
                    console.log(item==0)
                    l+=item;
                })
                $.each(types,function(index,item){
                    if(item==""){
                        j++;
                    }
                })
                $.each(comments,function(index,item){
                    if(item==""){
                        k++;
                    }
                })
                $.each(showTypes,function(index,item){
                    if(item==""){
                        n++;
                    }
                })
                if(n>0){
                    msg+="【页面显示方式不能为空】";
                }
                var msg=""
                if(i>0){
                    msg+="【列名不能为空】";
                }
                if(j>0){
                    msg+=" 【列字段数据类型不能为空】";
                }
                if(k>0){
                    msg+=" 【列注释不能为空】";
                }
                if(l==0){
                    msg+="【表"+tableName+"没有设置主键】";
                }
                if(msg!=""){
                    layer.msg(msg);
                    return false;
                }
                var data={"tableName":tableName,"description":description,"fields":fields.join(","),"types":types.join(","),"lengths":lengths.join(","),"defaultValues":defaultValues.join(","),
                    "keys":keys.join(","),"isNulls":isNulls.join(","),"extras":extras.join(","),"comments":comments.join(","),"isQuerys":isQuerys.join(","),"isTransients":isTransients.join(","),
                    "showTypes":showTypes.join(","),"valids":valids.join(',')};
                console.log(data)
                $.ajax({
                    url:basePath+'/generator/addOrEdit',
                    type:'post',
                    data:data,
                    dataType:'json',
                    success:function(result){
                        result=eval('(result)');
                        $.layerClose();
                        refresh();
                        $.layerMsg('添加成功！','success');
                    },error:function(error){
                        $.layerMsg('添加出错！可能是sql语句语法不对！！','error');
                    }
                })
            }
            return false;
        })

        form.on('checkbox(key)', function(data){
            if($(this).is(':checked')){
                $(this).parent().next().find('[name=isNull]').attr('checked',true);
                $(this).parent().next().find('.layui-unselect.layui-form-checkbox').addClass('layui-form-checked');
            }else{
                $(this).parent().next().find('[name=isNull]').attr('checked',false);
                $(this).parent().next().find('.layui-unselect.layui-form-checkbox').removeClass('layui-form-checked');
            }
            form.render();
        });




    });

</script>
</body>
</html>
