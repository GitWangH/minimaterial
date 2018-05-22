$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/user/list',
        datatype: "json",
        colModel: [			
			{ label: '用户ID', name: 'userId', index: "userId", width: 45, key: true},
			{ label: '用户类型', name: 'userType', width: 80, formatter: function(value, options, row){
				return value === 0 ? 
					'系统用户' : 
					'供应商用户';
			}},
			{ label: '所属供应商', name: 'supplierName', width: 75 },
			{ label: '用户名', name: 'username', width: 75 },
			{ label: '所属部门', name: 'deptName', width: 75 },
			{ label: '职位', name: 'positionName', index: 'position', width: 75 },
			{ label: '身份证号', name: 'identityAccount', width: 90 },
			{ label: '邮箱', name: 'email', width: 90 },
			{ label: '手机号', name: 'mobile', width: 100 },
			{ label: '状态', name: 'status', width: 80, formatter: function(value, options, row){
				return value === 0 ? 
					'<span class="label label-danger">禁用</span>' : 
					'<span class="label label-success">正常</span>';
			}},
			{ label: '创建时间', name: 'createTime', index: "createTime", width: 80}
        ],
		viewrecords: true,
		/*height:'80%',*/
		height:'auto',
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25,
        autowidth:true,
        multiselect: true,
        sortname: 'createTime',  
        sortorder: 'desc',  
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.content",
            page: "page.currentPage",
            total: "page.pageCount",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var setting = {
	    data: {
	        simpleData: {
	            enable: true,
	            idKey: "deptId",
	            pIdKey: "parentId",
	            rootPId: -1
	        },
	        key: {
	            url:"nourl"
	        }
	    }
	};
var ztree;
	

/*;!function(){  
    layer.config({//加载扩展模块  
        extend: 'extend/layer.ext.js'  
    });  
    layer.ready(function(){   
    });  
}();  
  
function ityzl_SHOW_LOAD_LAYER(){  
    return layer.msg('努力中...', {icon: 16,shade: [0.5, '#f5f5f5'],scrollbar: false,offset: '200px', time:100000}) ;  
}  
function ityzl_CLOSE_LOAD_LAYER(index){  
    layer.close(index);  
}  
function ityzl_SHOW_TIP_LAYER(){  
    layer.msg('恭喜您，加载完成！',{time: 1000,offset: '50px'});  
} */ 



var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			username: null,
			mobile :null
		},
		showList: true,
		title:null,
		roleList:{},
		positionOptions: [],
		user:{
			userType:0,
			status:1,
			supplierId:'',
			deptId:null,
            deptName:null,
            position:'',
			roleIdList:[]
		},
		supplierList:[]
	},
	created: function() {
		this.getOptions();
	},
	methods: {
		query: function () {
			vm.reload();
		},
		reset: function() {
			vm.q.username = '';
			vm.q.mobile = '';
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.roleList = {};
			vm.user = {deptName:null, deptId:null, status:1,userType:0,position:'', supplierId:'',roleIdList:[]};
			
			//获取角色信息
			this.getRoleList();
			this.getOptions();
			vm.supplierList =  [],
			vm.getDept();
		},
		getOptions: function(){ 
			//获取职位
			$.get(baseURL + "sys/dict/getByCategoryCode/" + "POSITION", function(r){							
				vm.positionOptions = r.dictList;
			});
			
			//获取产品名称
			$.get(baseURL + "busi/supplier/getSupplierList", function(r){							
				vm.supplierList = r.supplierList;
			});
		},
		getDept: function(){
            //加载部门树
            $.get(baseURL + "sys/dept/list", function(r){
                ztree = $.fn.zTree.init($("#deptTree"), setting, r);
                var node = ztree.getNodeByParam("deptId", vm.user.deptId);
                if(node != null){
                    ztree.selectNode(node);

                    vm.user.deptName = node.name;
				}
            })
        },
		update: function () {
			var userId = getSelectedRow();
			if(userId == null){
				return ;
			}
			
			vm.showList = false;
            vm.title = "修改";
			
			vm.getUser(userId);
			//获取角色信息
			this.getRoleList();
			//this.getOptions();
		},
		del: function () {
			var userIds = getSelectedRows();
			if(userIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sys/user/delete",
                    contentType: "application/json",
				    data: JSON.stringify(userIds),
				    success: function(r){
						if(r.success){
							alert('操作成功', function(){
                                vm.reload();
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		saveOrUpdate: function () {
            if(vm.validator()){
                return ;
            }
            var i  ;
			var url = vm.user.userId == null ? "sys/user/save" : "sys/user/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
                beforeSend: function () {  
//                	debugger;
	                i = ityzl_SHOW_LOAD_LAYER();  
	            }, 
			    data: JSON.stringify(vm.user),
			    success: function(r){
			    	ityzl_CLOSE_LOAD_LAYER(i);  
		             ityzl_SHOW_TIP_LAYER();
		        	  if(r.success){
		        		  vm.reload();
						}else{
							alert(r.msg);
						}
		          },
		          error: function() {
		        	  ityzl_CLOSE_LOAD_LAYER(i);  
		        	  alert(data.msg);
		          }
			});
		},
		getUser: function(userId){
			$.get(baseURL + "sys/user/info/"+userId, function(r){
				vm.user = r.user;
				vm.getDept();
				
			});
		},
		getRoleList: function(){
			$.get(baseURL + "sys/role/select", function(r){
				vm.roleList = r.list;
			});
		},
		 deptTree: function(){
	            layer.open({
	                type: 1,
	                offset: '50px',
	                skin: 'layui-layer-molv',
	                title: "选择部门",
	                area: ['300px', '450px'],
	                shade: 0,
	                shadeClose: false,
	                content: jQuery("#deptLayer"),
	                btn: ['确定', '取消'],
	                btn1: function (index) {
	                    var node = ztree.getSelectedNodes();
	                    //选择上级部门
	                    vm.user.deptId = node[0].deptId;
	                    vm.user.deptName = node[0].name;
	                    layer.close(index);
	                }
	            });
	        },
		reload: function () {
			vm.showList = true;
			//重置表单校验
			$("#userForm").bootstrapValidator('resetForm') 
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				mtype: 'POST', 
				datatype: "json",
                postData:{
                	      'username': vm.q.username,
                	       'mobile':  vm.q.mobile
                	     },
                page:page
            }).trigger("reloadGrid");
		},
        validator: function () {
        	var form = $('#userForm');
        	var bv = form.data('bootstrapValidator');
            bv.validate();
            if (!bv.isValid()) {
            	return true;
            }
          /* if(isBlank(vm.user.deptId)){
                alert("请选择部门");
                return true;
            }*/

            if(isBlank(vm.user.roleIdList)){
                alert("请选择角色");
                return true;
            }
        }
	}
});
$(function () {
	$('#userForm').bootstrapValidator({
        message: '输入值不合法',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            username: {
                message: '用户名不合法',
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    },
                    /*stringLength: {
                        min: 3,
                        max: 30,
                        message: '请输入2到30个字符'
                    },*/
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\. \u4e00-\u9fa5 ]+$/,
                        message: '用户名只能由字母、数字、点、下划线和汉字组成 '
                    }
                }
            }
            , email: {
                validators: {
                    /*notEmpty: {
                        message: 'email不能为空'
                    },*/
                    emailAddress: {
                        message: '请输入正确的邮件地址如：123@qq.com'
                    }
                }
            },mobile: {
                validators: {
                    notEmpty: {
                        message: '手机号不能为空'
                    },
                    regexp: {
                        regexp: "^([0-9]{11})?$",
                        message: '手机号码格式错误'
                    }
                }
            }, deptName: {
                validators: {
                    notEmpty: {
                        message: '部门不能为空'
                    }
                }
            },identityAccount: {
                validators: {
                	stringLength: {
                        min: 15,
                        max: 18,
                        message: '请输入15到18个字符'
                    }
                }
            },position: {
        		validators: {
                    notEmpty: {
                        message: '客户职位不能为空！'
                    }
                }
        	},
        }
    });
});

//验证用户名不能重复
$("#username").focusout(function() {
	var id = $("#userId").val();
	var name = $("#username").val();
	if(name != null && name != ''){
		checkName(id, name);
	}
});

function checkName(id, name){
	$.ajax({
		url: baseURL + 'sys/user/checkUserName',
	          type : "post",
	          dataType : 'JSON',
	          data : {
	        	  name:name,
	        	  id:id
	          },
	          success : function(result) {
      		  //已经存在该名字提示用户
      		  if(!(result.success)){
      			  alert(result.msg);
      		  }
	      }
	 });
}
