var listUrl= baseURL + '${listUrl}';
var addUrl = baseURL + '${addUrl}';
var updateUrl = baseURL + '${updateUrl}';
var deleteUrl = baseURL + '${deleteUrl}';
var infoUrl = baseURL + '${infoUrl}';


$(function () {
    load();
});

function load(){
	$("#jqGrid").jqGrid({
    url: listUrl,
    datatype: "json",
    mtype: 'POST',
    colModel: [			
		{ label: 'id', name: 'id', index: "id", width: 100, key: true, hidden:true },
		<#list frontlist as item> 
		{ label: '${item.label}', name: '${item.name}',  sortable: false, width: 150 },
		</#list>
		
	viewrecords: true,
	shrinkToFit: false,
	height:'auto',
    rowNum: 10,
	rowList : [10,30,50,100],
    rownumbers: true, 
    autowidth:true,
    multiselect: true,
    sortorder: 'desc', 
    sortname: 'orderDate', 
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
    }
});}

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
		<#list querylist as query> 
			${query} : '',
		</#list>
		},
		showList:true,
		title:null,
		model: {
		<#list infolist as info> 
			${info.name} : '',
		</#list>
		},
	},
	created: function() {
		
	},
	methods: {
		query: function () {
			vm.reload();
		},
		reset: function() {
			<#list querylist as query> 
				vm.q.${query} = '',
			</#list>	
			vm.reload();
		},
		add : function (){
			vm.title = "新增";
			vm.showList = false;
			vm.model = {
				<#list infolist as info> 
					${info.name} : '',
				</#list>
			};
		},
		info: function(id){ 
			$.get(infoUrl+userId, function(r){
				vm.model = r.model;
			});
		},
		update: function () {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
			vm.info(id);
		},
		del: function (){
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: deleteUrl,
				    dataType: "json",
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
		saveOrUpdate: function () {
			var url = vm.model.id == null ? addUrl : updateUrl;
			$.ajax({
				type: "POST",
			    url:  url,
                contentType: "application/json",
			    data: JSON.stringify(vm.model),
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
			$("#busiForm").bootstrapValidator('resetForm') 
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{
				<#list querylist as query> 
					'${query}': vm.q.${query} ,
				</#list>	
                },
        	    datatype: "json",
     	        mtype: 'POST',
                page:1
            }).trigger("reloadGrid");
		},
	},
});

$(function () {
	debugger ;
	$('#busiForm').bootstrapValidator({
        message: '输入值不合法',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
              /*username: {
                message: '用户名不合法',
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    },
                    stringLength: {
                        min: 3,
                        max: 30,
                        message: '请输入2到30个字符'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\. \u4e00-\u9fa5 ]+$/,
                        message: '用户名只能由字母、数字、点、下划线和汉字组成 '
                    }
                }
            },*/
        }
    });
});

