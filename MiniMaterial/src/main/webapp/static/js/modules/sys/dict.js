var categoryId = null ;
var PostData={year:2013,projectcode:"value"};

$(function () {
	categoryId = getUrlVar('categoryId');
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/dict/list?categoryId='+categoryId,
        datatype: "json",
       // mtype: 'POST', 
        colModel: [			
			{ label: 'ID', name: 'id', width: 30, key: true, hidden:true },
			{ label: '字典名称', name: 'name', width: 60 },
			{ label: '字典编码', name: 'code', width: 100 },
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
		showList: true,
		title: null,
		dictionary: {
			categoryId: null
		}
		
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.dictionary = {};
		},
		update: function () {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			
			$.get(baseURL + "sys/dict/info/"+id, function(r){
                vm.showList = false;
                vm.title = "修改";
                vm.dictionary = r.dictionary;
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
				    url: baseURL + "sys/dict/delete",
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
		back : function (){
			location.href = baseURL + 'views/modules/sys/category.html';
		},
		saveOrUpdate: function () {
            if(vm.validator()){
				return ;
			}

			var url = vm.dictionary.id == null ? "sys/dict/save" : "sys/dict/update";
			vm.dictionary.categoryId = categoryId;
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.dictionary),
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
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{
                	categoryId:categoryId
                	},
                page:page
            }).trigger("reloadGrid");
		},
		validator: function () {
			if(isBlank(vm.dictionary.name)){
				alert("分类名称不能为空");
				return true;
			}

            if(isBlank(vm.dictionary.code)){
                alert("分类编码不能为空");
                return true;
            }
        }
	}
});