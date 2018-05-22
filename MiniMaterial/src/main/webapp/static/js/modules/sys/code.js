$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + '/sys/generateCode/tablelist',
        datatype: "json",
        colModel: [			
			{ label: '表名称', name: 'tableName', width: 175 },
			{ label: '表空间', name: 'schem', width: 175 },
			{ label: '描述', name: 'desc', width: 375 },
        ],
        viewrecords: true,
    	shrinkToFit: true,
    	height:'auto',
        rowNum: -1,
    	rowList : [10,30,50,100],
        rownumbers: true, 
        autowidth:true,
        multiselect: true,//定义是否可以多选,可修改
        gridComplete:function(){
        }
    });
});
	
var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title:null,
	},
	methods: {
		query: function () {
			vm.reload();
		},
		reset: function() {
			vm.reload();
		},
		createModel: function(){
			var data= getSelectedRowData();
        	var table_name = data.tableName ;
        	var table_desc = data.desc ;
        	$.ajax({
				type: "get",
			    url: '/MaterialPurchasing/' + "sys/generateCode/createBean",
                contentType: "application/json",
			    data: {
			    	tableName:table_name,
			    	tableCommnet:table_desc
			    },
			    success: function(r){
			    	if(r.success){
						alert('创建成功', function(){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		
		createJs : function (){
			
			var data = getSelectedRowData();
			var name = data.tableName;
			
			var url= baseURL+"views/modules/sys/chooseCondition.html?name="+name;
		    //弹框层
		    layer.open({
		        scrollbar: false,
		        type: 2,
		        title : ["xxx" , true],
		        area: ['80%', '80%'], //宽高
		        content: [url,'no'],
		        shadeClose : false,
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
                page:page
            }).trigger("reloadGrid");
		},	
	}	
});
