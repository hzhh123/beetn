$(document).ready(function () {
    $.ajax({
        type:'get',
        dataType:'json',
        url:basePath+"/resource/shiroMenu",
        success:function (data) {
            initMenu(data,'#main-nav');
        }
    })
});

function initMenu(menu,parent) {
    for (var i = 0; i < menu.length; i++) {
        var item = menu[i];
        var str = "";
        if (item.childMenus.length==0) {
            str=' <li class=""> <a href="'+item.url+'"><i class="menu-icon fa fa-list-alt"></i><span class="menu-text"> '+item.name+'</span>' +
                '</a><b class="arrow"></b></li>';
            $(parent).append(str);
        } else {
            str=' <li class=""><a href="#" class="dropdown-toggle"><i class="menu-icon fa fa-tag"></i><span class="menu-text">'+item.name+'</span>'
            +'<b class="arrow fa fa-angle-down"></b></a><b class="arrow"></b>';
            str+=' <ul class="submenu"  id="menu-child-'+item.id+'"></ul></li>';
            $(parent).append(str);
            var childParent = $("#menu-child-" + item.id);
            initMenu(item.childMenus, childParent);
        }
    }
}