<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/global.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>用户部门树(复选)</title>

    <meta name="description" content="overview &amp; stats" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    <link  rel="shortcut icon" href="${path}/static/aceadmin/assets/favicon/favicon.ico">
    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="${path}/static/aceadmin/assets/css/bootstrap.css" />
    <link rel="stylesheet" href="${path}/static/aceadmin/assets/css/font-awesome.css" />
    <link rel="stylesheet" href="${path}/static/plugin/bootstrap-ztree3/css/bootstrapStyle/bootstrapStyle.css" type="text/css">
    <link rel="stylesheet" href="${staticPath}/static/aceadmin/assets/js/layui/css/layui.css">
    <style>
        .search-user,.search-user:hover,.search-user:visited,.search-user:link,.search-user:active{
            text-decoration: none;
        }
    </style>
</head>

<body>
<div class="layui-container-fluid">
    <div class="layui-col-md7" style="border-right:1px solid #ccc;height: calc(100% - 55px);overflow: hidden">
    <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
        <ul class="layui-tab-title">
            <li class="layui-this">按部门</li>
            <li>按职位</li>
        </ul>
        <div class="layui-tab-content" style="heigth:100%">
            <div class="layui-tab-item layui-show"><ul id="treeDemo" class="ztree" style="overflow: auto;height:100%"></ul></div>
            <div class="layui-tab-item"></div>
        </div>
    </div>
    </div>
    <div class="layui-col-md5">
        <form class="layui-form" style="margin-top: 5px;border-bottom: thin solid #ccc;padding: 0;height: 46px;">
            <div class="layui-form-item">
                <label class="layui-form-label" style="width: 100px;">快捷查询</label>
                <div class="layui-input-block" style="width: calc(100% - 130px)">
                    <input type="text" name="name" id="name" autocomplete="off" placeholder="请输入姓名或姓名首字母查询" class="layui-input">
                </div>
            </div>
        </form>
        <div style="height: calc(100% - 100px);overflow: hidden">
            <div style="height: 200px;overflow: auto;border-bottom: 1px solid #ccc;padding: 15px;">
                <ul id="searchResultUser">

                </ul>
            </div>
            <div style="height: 100%;overflow: auto;padding: 15px;">
                <ul id="selectUser">
                </ul>
            </div>
        </div>
    </div>
</div>
<div style="text-align: right;position: fixed;bottom: 5px;width: 100%;right: 5px;">
    <hr>
    <button class="layui-btn" id="save">保存</button>
    <button class="layui-btn layui-btn-primary" id="clear">清除</button>
</div>
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

<!-- ace scripts -->
<script src="${path}/static/aceadmin/assets/js/layer/layer.js"></script>
<script src="${path}/static/plugin/bootstrap-ztree3/js/jquery.ztree.all.min.js"></script>
<script src="${ctxPath}/static/aceadmin/assets/js/layui/layui.all.js"></script>
<script src="${ctxPath}/static/js/util.js"></script>
<!-- inline scripts related to this page -->
<script type="text/javascript">
    layui.use('element', function() {
        var $ = layui.jquery
            , element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
    });
    jQuery(function($) {
        var setting = {
            view: {
                selectedMulti: false
            },
            check: {
                enable: true,
            },
            data: {
                simpleData: {
                    enable: true
                }
            },callback: {
                onCheck: zTreeOnCheck,
            }
        };
        //递归取消节点选择
        function cancle(treeNode){
            if(treeNode.isParent){
                for (var i = 0; i < treeNode.children.length; i++) {
                    var id = treeNode.children[i].id;
                    if (id.indexOf('u') >= 0) {
                        id = id.replace('u', '');
                        $.each($('#selectUser').find('[name=userid]'),function () {
                            if(id==$(this).val()){
                                $(this).parent().remove();
                            }
                        });
                    }
                    cancle(treeNode.children[i]);
                }
            }
        }
        function zTreeOnCheck(event, treeId, treeNode) {
                var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
                 var nodes = treeObj.getChangeCheckedNodes();
                if(!treeNode.checked) {
                    if (treeNode.id.indexOf('u') >= 0) {
                        var id=treeNode.id.replace('u','');
                        $.each($('#selectUser').find('[name=userid]'),function () {
                           if(id==$(this).val()){
                               $(this).parent().remove();
                           }
                        });
                    }
                    cancle(treeNode);

                }else {
                    if (nodes.length > 0) {
                        $('#selectUser').empty();
                        for (var i = 0; i < nodes.length; i++) {
                            var id = nodes[i].id;
                            if (id.indexOf('u') >= 0) {
                                id = id.replace('u', '');
                                $('#selectUser').append(' <li><img src="/static/img/long-arrow-right.png" alt=""><input type="hidden" name="userid" value="' + id + '"><span>' + nodes[i].name + '</span></li>');
                            }
                        }
                    }
                }
        }


        $.ajax({
            url:basePath+'/organization/findOrgAndUserTree',
            type:'get',
            dataType:'json',
            success:function(data){
                $.fn.zTree.init($("#treeDemo"), setting, data);
                var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
                var nodes=treeObj.getNodes();
            }
        });

        //保存
        $('#save').click(function () {
            var treeObj = $.fn.zTree.getZTreeObj("treeDemo"); //获取全部节点数据
            var nodes = treeObj.getCheckedNodes(true);
            //var ids=new Array();
            if(nodes.length>0){
                for(var i=0;i<nodes.length;i++){
                    var id=nodes[i].id;
                    var name=nodes[i].name;
                    if(id.indexOf('u')>=0){
                        id=id.replace('u','');
                       // ids.push(id);

                    }
                }
            }
            var idArr=new Array();
            var names=new Array();
          if($('#selectUser').find('li').length>0){
                $('[name=userid]').each(function () {
                    var id=$(this).val();
                    var name=$(this).next().text();
                    names.push(name);
                    idArr.push(id);
                })
          }
          var idSelect=idArr.join(',')
          if(idSelect!=''){
              alert(idSelect)
              alert(names)
          }
            $.layerClose();
        });
        //清空所选
        $('#clear').click(function () {
            $('#selectUser').empty();
            var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
            var nodes = treeObj.getChangeCheckedNodes();
            if(nodes.length>0){
                for(var i=0;i<nodes.length;i++){
                    treeObj.checkNode(nodes[i], false, true);
                }
            }
        })

        /**
         * 输入字符查询
         */
        $('#name').keyup(function () {
            $.ajax({
                url:basePath+'/user/searchUser',
                data:{"name":$('#name').val()},
                dataType:'json',
                success:function(data){
                    $('#searchResultUser').empty();
                    if(data.length>0){
                        var html="";
                        $.each(data,function (index,item) {
                            html+="<li><input type=\"hidden\" name=\"searchUserid\" value=\""+item.id+"\"><a href='javascript:;' class='search-user'>"+item.name+"</a></li>";
                        });
                        $('#searchResultUser').append(html);
                    }
                }
            })
        })

        $('#searchResultUser').on('click','.search-user',function () {
            var name=$(this).text();
            var id=$(this).prev().val();
            var idArr=new Array();
            var names=new Array();
            if($('#selectUser').find('li').length>0){
                $('[name=userid]').each(function () {
                    var id=$(this).val();
                    var name=$(this).next().text();
                    names.push(name);
                    idArr.push(id);
                })
            }
            if(idArr.indexOf(id)<0) {
                $('#selectUser').append(' <li><img src="/static/img/long-arrow-right.png" alt=""><input type="hidden" name="userid" value="' + id + '"><span>' + name + '</span></li>');
                id="u"+id;
                var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
                var node = treeObj.getNodeByParam("id", id, null);
                treeObj.checkNode(node, true, true);
                console.log(node)
            }
        })
    })
</script>

</body>
</html>

