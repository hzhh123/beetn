$(document).ready(function () {
    $('#logout').click(function(){
        layer.confirm('确定要退出系统吗？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            $.ajax({
                url:basePath+'/logout',
                type:'post',
                dataType:'json',
                success:function(result){
                    if(result.success){
                        window.location.href=basePath+"/jsp/login.jsp";
                    }else {
                        layer.msg(result.msg);
                    }
                }
            });
        }, function(){
            layer.close();
        });
    });


    $('.nav.nav-list a').each(function () {
        if ($(this).attr('href') == '/index') {
            $(this).parent().addClass('active');
        }
    });
})
