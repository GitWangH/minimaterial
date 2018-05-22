$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/category/list',
        datatype: "json",
        colModel: [			
			{ label: 'ID', name: 'id', width: 30, key: true, hidden:true },
			{ label: '类别名称', name: 'name', width: 60 },
			{ label: '类别编码', name: 'code', width: 100 },
			{ label: '创建时间', name: 'createTime', width: 100 },
			{ label: '备注', name: 'remark', width: 80 }
        ],
		viewrecords: true,
        height: '80%',
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25,
        autowidth:true,
        multiselect: true,
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



var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			name: null,
			code:null,
			xxx: null
		},
		showList: true,
		title: null,
		category: {},
		options: []
	},
	methods: {
		query: function () {
			vm.reload();
		},
		reset: function() {
			vm.q.name = '';
			vm.q.code = '';
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.category = {};
		},
		update: function () {
			var categoryId = getSelectedRow();
			if(categoryId == null){
				return ;
			}
			$.get(baseURL + "sys/category/info/"+categoryId, function(r){
                vm.showList = false;
                vm.title = "修改";
                vm.category = r.sysCategory;
            });
		},
		del: function () {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sys/category/delete",
                    contentType: "application/json",
				    data: JSON.stringify(ids),
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
		
		showDetail : function (){
			var categoryId = getSelectedRow();
			if(categoryId == null){
				return ;
			}
			location.href = baseURL + 'views/modules/sys/dictionary.html?categoryId='+categoryId;
		},
		saveOrUpdate: function () {
            if(vm.validator()){
				return ;
			}

			var url = vm.category.id == null ? "sys/category/save" : "sys/category/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.category),
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
		},
		reload: function () {
			var temp = vm.q.xxx;
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{
                	'name': vm.q.name,
                	'code':vm.q.code
                	},
                page:page
            }).trigger("reloadGrid");
		},
		validator: function () {
			if(isBlank(vm.category.name)){
				alert("分类名称不能为空");
				return true;
			}

            if(isBlank(vm.category.code)){
                alert("分类编码不能为空");
                return true;
            }
        }
	},
	created: function(){
		/*$.get(baseURL + "sys/dict/list2", function(r){
	    	debugger ;
	        vm.options = r.list;
	    });*/
	},
});

