// 判断时候在Iframe框架内,在则刷新父页面
if (self != top) {
    parent.location.reload(true);
    if (!!(window.attachEvent && !window.opera)) {
        document.execCommand("stop");
    } else {
        window.stop();
    }
}
$(document).ready(function () {
    $('#captcha').click(function () {
        var $this = $(this);
        var url = $this.data("src") + new Date().getTime();
        $this.attr("src", url);
    });
    $('#loginBtn').bind('click',login);
    $(document).keypress(function(event){
        if(event.keyCode==13){
            event.returnValue=false;
            event.cancel = true;
            login();
        }
    });
})

function login(){
    var data=$('#loginForm').serialize();
    var url=basePath+"/login";
    var headers={};
    headers['CSRFToken']=$("#csrftoken").val();
    $.ajax({
        type:'post',
        url:url,
        data:data,
        dataType: "json",
        headers:headers,
        success:function (result) {
            if(result.success){
                layer.msg("登陆成功！");
                window.location.href=basePath;
            }else{
                console.log(result.msg==null)
                layer.msg(result.msg);
                // 刷新验证码
                $("#captcha")[0].click();
            }
        }
    });
    return false;

}