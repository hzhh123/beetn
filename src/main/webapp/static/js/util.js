function date2Str(date){
	 if(isNull(date)){
	   	 return "";
	 }
	 var y = date.getFullYear();
	 if(y > 1985 && y < 1992) {
		 //夏令时的处理,确保不会少一个小时
		 date = new Date(date.getTime() + 3600*1000);
	 }
	 y = date.getFullYear();
	 var m = date.getMonth() + 1;
	 if(m<10) m = "0" + m;
	 var d = date.getDate();
	 if(d<10) d = "0" + d;
	 var redate = y + "-" + m + "-" + d;
	 return redate;
}


function datetime2Str(dt){
	 if(dt==null){
	   	 return "";
	 }
	 var datetime=new Date(dt);
	 var y = datetime.getFullYear();
	 var m = datetime.getMonth() + 1;  
	 if(m < 10) m = "0" + m;
	 var d = datetime.getDate();  
	 if(d < 10) d = "0" + d;
	 var h = datetime.getHours();
	 if(h < 10) h = "0" + h;
	 var min = datetime.getMinutes();
	 if(min < 10) min = "0" + min;
	 var s=datetime.getSeconds();
	 if(s<10)s="0"+s;
	 var redatetime = y + "-" + m + "-" + d + " " + h + ":" + min+":"+s; 
	 return redatetime;
};
/**
 * 创建模态窗。
 * @param {Object} options
 */
$.layerOpen = function (options) {
    var defaults = {
        id: "default",
        title: '系统窗口',
        type: 2,
        //skin: 'layui-layer-molv',
        width: "auto",
        height: "auto",
        maxmin: false,
        content: '',
        shade: 0.3,
        btn: ['确认', '取消'],
        btnclass: ['btn btn-primary', 'btn btn-danger'],
        yes: null
    };
    var options = $.extend(defaults, options);
    top.layer.open({
        id: options.id,
        type: options.type,
        scrollbar: false,
        //skin: options.skin,
        shade: options.shade,
        shadeClose: true,
        maxmin: options.maxmin,
        title: options.title,
        fix: false,
        area: [options.width, options.height],
        content: options.content,
        btn: options.btn,
        btnclass: options.btnclass,
        yes: function (index, layero) {
            if (options.yes && $.isFunction(options.yes)) {
                var iframebody = top.layer.getChildFrame('body', index);
                var iframeWin = layero.find('iframe')[0].contentWindow;
                options.yes(iframebody, iframeWin, index);
            }
        },
        cancel: function () {
            return true;
        }
    });

}

/**
 * 关闭模态窗。
 */
$.layerClose = function () {
    var index = top.layer.getFrameIndex(window.name);
    top.layer.close(index);
}

/**
 * 创建询问框。
 * @param {Object} options
 */
$.layerConfirm = function (options) {
    var defaults = {
        title: '提示',
        //skin: 'layui-layer-molv',
        content: "",
        icon: 3,
        shade: 0.3,
        anim: 4,
        btn: ['确认', '取消'],
        btnclass: ['btn btn-primary', 'btn btn-danger'],
        callback: null
    };
    var options = $.extend(defaults, options);
    top.layer.confirm(options.content, {
        title: options.title,
        icon: options.icon,
        btn: options.btn,
        btnclass: options.btnclass,
        //skin: options.skin,
        anim: options.anim
    }, function () {
        if (options.callback && $.isFunction(options.callback)) {
            options.callback();
        }
    }, function () {
        return true;
    });
}
/**
 * 创建一个信息弹窗。
 * @param {String} content
 * @param {String} type
 */
$.layerMsg = function (content, type, callback) {
    // debugger;
    if (type != undefined) {
        var icon = "";
        if (type == 'warning' || type == 0) {
            icon = 0;
        }
        if (type == 'success' || type == 1) {
            icon = 1;
        }
        if (type == 'error' || type == 2) {
            icon = 2;
        }
        if (type == 'info' || type == 6) {
            icon = 6;
        }
        top.layer.msg(content, { icon: icon, time: 2000 }, function () {
            if (callback && $.isFunction(callback)) {
                callback();
            }
        });
    } else {
        top.layer.msg(content, function () {
            if (callback && $.isFunction(callback)) {
                callback();
            }
        });
    }
}

/**
 * 绑定Select选项。
 * @param {Object} options
 */
$.fn.bindSelect = function (options) {
    var defaults = {
        id: "id",
        text: "text",
        search: true,
        //multiple: false,
        title: "请选择",
        url: "",
        param: [],
        change: null,
		type:'get'
    };
    var options = $.extend(defaults, options);
    var $element = $(this);
    if (options.url != "") {
        $.ajax({
            url: options.url,
            data: options.param,
            type: options.type,
            dataType: "json",
            async: false,
            success: function (data) {
                $.each(data, function (i) {
                    $element.append($("<option></option>").val(data[i][options.id]).html(data[i][options.text]));
                });
                $element.select2({
                    placeholder: options.title,
                    //multiple: options.multiple,
                    minimumResultsForSearch: options.search == true ? 0 : -1
                });
                $element.on("change", function (e) {
                    if (options.change != null) {
                        options.change(data[$(this).find("option:selected").index()]);
                    }
                    $("#select2-" + $element.attr('id') + "-container").html($(this).find("option:selected").text().replace(/　　/g, ''));
                });
            }
        });
    } else {
        $element.select2({
            minimumResultsForSearch: -1
        });
    }
}

/**
 * 提交表单。
 * @param {Object} options
 */
$.formSubmit = function (options) {
    var defaults = {
        url: "",
        data: {},
        type: "post",
        async: false,
        callback: null,
        close: true,
        showMsg: true
    };
    var options = $.extend(defaults, options);
    $.ajax({
        url: options.url,
        data: options.data,
        type: options.type,
        async: options.async,
        dataType: "json",
        success: function (data) {
        	//data=$.parseJSON(data);
        
            if (options.showMsg) {
            	console.log(data.success)
            	if(data.success){
                    if (options.close) {
                        $.layerClose();
                    }
                    $.layerMsg(data.msg, "success");

				}else{
                    $.layerMsg(data.msg, "warning");
				}
                if (options.callback && $.isFunction(options.callback)) {
                    options.callback();
                }
            }
        },
        error: function (xhr, status, error) {
            $.layerMsg(error, "error");
        },
        beforeSend: function () {

        },
        complete: function () {

        }
    })
}


/**
 * 获取URL指定参数值。
 * @param {String} name
 */
$.getQueryString = function (name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}
/**
 * 是否为空或空字符串
 */
function isNull(obj) {
    if(typeof(obj) == "number") return false;
    if(typeof(obj) == "undefined" || obj == null || !obj || ( typeof(obj) == "string" && obj.trim() == "" )) {
        return true;
    } else {
        return false;
    }
}