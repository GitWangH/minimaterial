//jqGrid的配置信息
$.jgrid.defaults.width = 800;
$.jgrid.defaults.responsive = true;
$.jgrid.defaults.styleUI = 'Bootstrap';

//工具集合Tools
window.T = {};

// 获取请求参数
// 使用示例
// location.href = http://localhost/index.html?id=123
// T.p('id') --> 123;
var url = function(name) {
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r!=null)return  unescape(r[2]); return null;
};
T.p = url;

var baseURL = "/MiniMaterial/";

//登录token
var token = localStorage.getItem("token");
/*if(token == 'null'){
    parent.location.href = baseURL + 'login.html';
}*/
$(document).ajaxComplete(function(event, response, settings) {  
    var sessionTimeout = response.getResponseHeader("SessionTimeout");  
    if(sessionTimeout != null && typeof sessionTimeout != "undefined" && sessionTimeout.length > 0){  
    	 parent.location.href = baseURL + 'login.html';
    }  
});  

//jquery全局配置
$.ajaxSetup({
	dataType: "json",
	cache: false,
    headers: {
        "token": token
    },
    xhrFields: {
	    withCredentials: true
    },
    complete: function(xhr) {
    }
});

//jqgrid全局配置
$.extend($.jgrid.defaults, {
    ajaxGridOptions : {
        headers: {
            "token": token
        }
    }
});

/**jgrid 列表加载网络异常处理   数据加载延迟闪退*/
/*$.extend($.jgrid.defaults, {  
    loadError: function(xhr,status,error){  
    	debugger ;
    	 parent.location.href = baseURL + 'login.html';
      },  
});  
*/
/**jgrid 列表加载数据异常处理*/
$.extend($.jgrid.defaults, {  
	loadComplete: function(data){  
    	if(!data.success){
    		alert(data.msg);
    	}
      },  
}); 


var user = JSON.parse(localStorage.getItem("user")) ;
//权限管理员判断
function IsAdmin() {
	debugger ;
	var userId = user.userId;
  if (userId == '1') {
      return true;
  } else {
      return false;
  }
}


//权限判断
function hasPermission(permission) {
    if (window.parent.permissions.indexOf(permission) > -1) {
        return true;
    } else {
        return false;
    }
}

//重写alert
window.alert = function(msg, callback){
	parent.layer.alert(msg, function(index){
		parent.layer.close(index);
		if(typeof(callback) === "function"){
			callback("ok");
		}
	});
}

//重写confirm式样框
window.confirm = function(msg, callback){
	parent.layer.confirm(msg, {btn: ['确定','取消']},
	function(){//确定事件
		if(typeof(callback) === "function"){
			callback("ok");
		}
	});
}

//选择一条记录
function getSelectedRow() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if(!rowKey){
    	alert("请选择一条记录");
    	return ;
    }
    
    var selectedIDs = grid.getGridParam("selarrrow");
    if(selectedIDs.length > 1){
    	alert("只能选择一条记录");
    	return ;
    }
    
    return selectedIDs[0];
}

//选择一条记录
function getSelectedRowData() {
	var id = getSelectedRow();
	if(id == null){
		return ;
	}
	var select=$('#jqGrid').jqGrid('getGridParam','selrow');
	 //如果想获取选择的行的数据，只要传入rowId即可，如下：
	return $('#jqGrid').jqGrid('getRowData',select)
}



//选择多条记录
function getSelectedRows() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if(!rowKey){
    	alert("请选择一条记录");
    	return ;
    }
    
    return grid.getGridParam("selarrrow");
}

//判断是否为空
function isBlank(value) {
    return !value || !/\S/.test(value)
}


/**
 * 
 * 获取页面传递的参数
 * **/
function  getUrlVars(){  
    var vars = [], hash;  
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');  
    for(var i = 0; i < hashes.length; i++)  
    {  
      hash = hashes[i].split('=');  
      vars.push(hash[0]);  
      vars[hash[0]] = hash[1];  
    }  
    return vars;  
  }

/**
 * 
 * 根据参数名获取页面传递的参数值
 * **/
 function getUrlVar(name){  
    return getUrlVars()[name];  
  } 
 
 /**
  * 日期格式化
  */
 Date.prototype.Format = function (fmt) {
	    var o = {
	        "M+": this.getMonth() + 1, //月份 
	        "d+": this.getDate(), //日 
	        "h+": this.getHours(), //小时 
	        "m+": this.getMinutes(), //分 
	        "s+": this.getSeconds(), //秒 
	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
	        "S": this.getMilliseconds() //毫秒 
	    };
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    for (var k in o)
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	    return fmt;
	}
 
 ;!function(){  
	    layer.config({//加载扩展模块  
	        extend: 'extend/layer.ext.js'  
	    });  
	    layer.ready(function(){   
	    });  
	}();  
	  
	function ityzl_SHOW_LOAD_LAYER(){  
	    return layer.msg('执行中...', {icon: 16,shade: [0.5, '#f5f5f5'],scrollbar: false,offset: '200px', time:100000}) ;  
	}  
	function ityzl_CLOSE_LOAD_LAYER(index){  
	    layer.close(index);  
	}  
	function ityzl_SHOW_TIP_LAYER(){  
	    layer.msg('执行完成！',{time: 1000,offset: '50px'});  
	} 