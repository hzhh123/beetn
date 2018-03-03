/**
 * Created by Administrator on 2017/5/17.
 */
/*左侧菜单点击*/
$("#main-nav").on('click', 'li a', function(e) {
    //防止有链接跳转
    e.preventDefault();
    var $this = $(this);
    if($this.attr('href')!=''&&$this.attr('href')!='#'&&$this.attr('href')!='javascript;'&&$this.attr('href')!='javascript:void(0)'&&$this.attr('href')!=null){
        addIframe($this);
        $('#main-nav li').removeClass('active-item');
        $this.parents('.level-1').parent().addClass('active-item');
        $this.parent().addClass('active-item');
    }
});

/*添加iframe*/
var m=1;
function addIframe(cur){
    var $this = cur;
    var h = $this.attr("href"),
        label = $this.find("span").text()||$this.text(),
        isHas = false;
    if (h == "" || $.trim(h).length == 0) {
        return false;
    }

    var fullWidth = $(window.top.document).width();

    if(fullWidth >= 750){
        $(window.top.document).find(".layout-side").show();

    }else{
        $(window.top.document).find(".layout-side").hide();
    }
    $(window.top.document).find(".content-tab").each(function() {
        if ($(this).data("id") == h) {
            if (!$(this).hasClass("active")) {
                $(this).addClass("active").siblings(".content-tab").removeClass("active");
                addTab(this);
            }
            isHas = true;
        }
    });
    if(isHas){
        $(window.top.document).find(".body-iframe").each(function() {
            if ($(this).data("id") == h) {
                $(this).show().siblings(".body-iframe").hide();
            }
        });
    }
    if (!isHas) {
        var tab = "<a href='javascript:;' class='content-tab active' data-id='"+h+"'>"+ label +" <i class='fa fa-times-circle' style='position: relative;top:13px'></i></a>";
        $(window.top.document).find(".content-tab").removeClass("active");
        $(window.top.document).find(".tab-nav-content").append(tab);
        var iframe = "<iframe class='body-iframe' name='iframe"+ m +"' width='100%' height='100%' src='"+ h +"' frameborder='0' data-id='"+ h +"' seamless></iframe>";
        $(window.top.document).find(".layout-main-body").find("iframe.body-iframe").hide().parents(".layout-main-body").append(iframe);
        addTab( $(window.top.document).find(".content-tab.active"));
    }
    m++;
    return false;
}


/*添加tab*/
function addTab(cur) {
    var prev_all = tabWidth($(cur).prevAll()),
        next_all = tabWidth($(cur).nextAll());
    var other_width =tabWidth( $(window.top.document).find(".layout-main-tab").children().not(".tab-nav"));
    var navWidth =  $(window.top.document).find(".layout-main-tab").outerWidth(true)-other_width;//可视宽度
    var hidewidth = 0;
    if ( $(window.top.document).find(".tab-nav-content").width() < navWidth) {
        hidewidth = 0
    } else {
        if (next_all <= (navWidth - $(cur).outerWidth(true) - $(cur).next().outerWidth(true))) {
            if ((navWidth - $(cur).next().outerWidth(true)) > next_all) {
                hidewidth = prev_all;
                var m = cur;
                while ((hidewidth - $(m).outerWidth()) > ( $(window.top.document).find(".tab-nav-content").outerWidth() - navWidth)) {
                    hidewidth -= $(m).prev().outerWidth();
                    m = $(m).prev()
                }
            }
        } else {
            if (prev_all > (navWidth - $(cur).outerWidth(true) - $(cur).prev().outerWidth(true))) {
                hidewidth = prev_all - $(cur).prev().outerWidth(true)
            }
        }
    }
    $(".tab-nav-content").animate({
            marginLeft: 0 - hidewidth + "px"
        },
        "fast")
}

/*获取宽度*/
function tabWidth(tabarr) {
    var allwidth = 0;
    $(tabarr).each(function() {
        allwidth += $(this).outerWidth(true)
    });
    return allwidth;
}

/*左按钮事件*/
$(".btn-left").on("click", leftBtnFun);
/*右按钮事件*/
$(".btn-right").on("click", rightBtnFun);
/*选项卡切换事件*/
$(".tab-nav-content").on("click", ".content-tab", navChange);
/*选项卡关闭事件*/
$(".tab-nav-content").on("click", ".content-tab i", closePage);
/*选项卡双击关闭事件*/
$(".tab-nav-content").on("dblclick", ".content-tab", closePage);


/*左按钮方法*/
function leftBtnFun() {
    var ml = Math.abs(parseInt($(".tab-nav-content").css("margin-left")));
    var other_width = tabWidth($(".layout-main-tab").children().not(".tab-nav"));
    var navWidth = $(".layout-main-tab").outerWidth(true)-other_width;//可视宽度
    var hidewidth = 0;
    if ($(".tab-nav-content").width() < navWidth) {
        return false
    } else {
        var tabIndex = $(".content-tab:first");
        var n = 0;
        while ((n + $(tabIndex).outerWidth(true)) <= ml) {
            n += $(tabIndex).outerWidth(true);
            tabIndex = $(tabIndex).next();
        }
        n = 0;
        if (tabWidth($(tabIndex).prevAll()) > navWidth) {
            while ((n + $(tabIndex).outerWidth(true)) < (navWidth) && tabIndex.length > 0) {
                n += $(tabIndex).outerWidth(true);
                tabIndex = $(tabIndex).prev();
            }
            hidewidth = tabWidth($(tabIndex).prevAll());
        }
    }
    $(".tab-nav-content").animate({
            marginLeft: 0 - hidewidth + "px"
        },
        "fast");
}

/*右按钮方法*/
function rightBtnFun() {
    var ml = Math.abs(parseInt($(".tab-nav-content").css("margin-left")));
    var other_width = tabWidth($(".layout-main-tab").children().not(".tab-nav"));
    var navWidth = $(".layout-main-tab").outerWidth(true)-other_width;//可视宽度
    var hidewidth = 0;
    if ($(".tab-nav-content").width() < navWidth) {
        return false
    } else {
        var tabIndex = $(".content-tab:first");
        var n = 0;
        while ((n + $(tabIndex).outerWidth(true)) <= ml) {
            n += $(tabIndex).outerWidth(true);
            tabIndex = $(tabIndex).next();
        }
        n = 0;
        while ((n + $(tabIndex).outerWidth(true)) < (navWidth) && tabIndex.length > 0) {
            n += $(tabIndex).outerWidth(true);
            tabIndex = $(tabIndex).next()
        }
        hidewidth = tabWidth($(tabIndex).prevAll());
        if (hidewidth > 0) {
            $(".tab-nav-content").animate({
                    marginLeft: 0 - hidewidth + "px"
                },
                "fast");
        }
    }
}

/*选项卡切换方法*/
function navChange() {
    if (!$(this).hasClass("active")) {
        var k = $(this).data("id");
        $(".body-iframe").each(function() {
            if ($(this).data("id") == k) {
                $(this).show().siblings(".body-iframe").hide();
                return false
            }
        });
        $(this).addClass("active").siblings(".content-tab").removeClass("active");
        addTab(this);
    }
}

/*选项卡关闭方法*/
function closePage() {
    var url = $(this).parents(".content-tab").data("id");
    var cur_width = $(this).parents(".content-tab").width();
    if ($(this).parents(".content-tab").hasClass("active")) {
        if ($(this).parents(".content-tab").next(".content-tab").size()) {
            var next_url = $(this).parents(".content-tab").next(".content-tab:eq(0)").data("id");
            $(this).parents(".content-tab").next(".content-tab:eq(0)").addClass("active");
            $(".body-iframe").each(function() {
                if ($(this).data("id") == next_url) {
                    $(this).show().siblings(".body-iframe").hide();
                    return false
                }
            });
            var n = parseInt($(".tab-nav-content").css("margin-left"));
            if (n < 0) {
                $(".tab-nav-content").animate({
                        marginLeft: (n + cur_width) + "px"
                    },
                    "fast")
            }
            $(this).parents(".content-tab").remove();
            $(".body-iframe").each(function() {
                if ($(this).data("id") == url) {
                    $(this).remove();
                    return false
                }
            })
        }
        if ($(this).parents(".content-tab").prev(".content-tab").size()) {
            var prev_url = $(this).parents(".content-tab").prev(".content-tab:last").data("id");
            $(this).parents(".content-tab").prev(".content-tab:last").addClass("active");
            $(".body-iframe").each(function() {
                if ($(this).data("id") == prev_url) {
                    $(this).show().siblings(".body-iframe").hide();
                    return false
                }
            });
            $(this).parents(".content-tab").remove();
            $(".body-iframe").each(function() {
                if ($(this).data("id") == url) {
                    $(this).remove();
                    return false
                }
            })
        }
    } else {
        $(this).parents(".content-tab").remove();
        $(".body-iframe").each(function() {
            if ($(this).data("id") == url) {
                $(this).remove();
                return false
            }
        });
        addTab($(".content-tab.active"))
    }
    return false
}

//点击iframe页面元素关闭tab
function closeIframePage() {
    var _this=$(window.top.document).find(".content-tab");
    _this.each(function () {
        if($(this).hasClass('active')){
            $(this).prev().addClass('active');
            $(this).remove();
        }
    })
    $(window.top.document).find(".body-iframe").each(function () {
        if(!($(this).css('display')=='none')){
            $(this).prev().show();
            $(this).remove();
        }
    })
}

//刷新iframe
function refreshIframePage() {
    $(window.top.document).find(".body-iframe").each(function () {
        if(!($(this).css('display')=='none')){
            var name=$(this)[0].name;
            var src=$(this)[0].src;
            var iframe = "<iframe class='body-iframe' name='"+ name+"' width='100%' height='100%' src='"+ src +"' frameborder='0' data-id='"+ src+"' seamless></iframe>";
            $(window.top.document).find(".layout-main-body").find("iframe.body-iframe").hide().parents(".layout-main-body").append(iframe);
            $(this).remove();
        }
    })
}

//关闭所有的tab
function closeAllPage() {
    var _this=$(window.top.document).find(".content-tab").not(':first-child');
    _this.each(function () {
        $(window.top.document).find(".content-tab:first-child").addClass('active');
        $(this).remove();
    })
    $(window.top.document).find(".body-iframe").not(':first-child').each(function () {
        $(window.top.document).find(".body-iframe:first-child").css('display','block');
            $(this).remove();
    })
}

