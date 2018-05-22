<!DOCTYPE html>
<html>
<head>
<title>TODO</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<link rel="stylesheet" href="../../../../static/css/bootstrap.min.css">
<link rel="stylesheet" href="../../../../static/css/bootstrapValidator.min.css">
<link rel="stylesheet" href="../../../../static/css/font-awesome.min.css">
<link rel="stylesheet" href="../../../../static/css/fieldsetstyle.css"> 
<link rel="stylesheet" href="../../../../static/css/test/style.css" >
<link rel="stylesheet" href="../../../../static/plugins/jqgrid/ui.jqgrid-bootstrap.css">


<script src="../../../../static/libs/jquery.min.js"></script>
<script src="../../../../static/plugins/layer/layer.js"></script>
<script src="../../../../static/plugins/laydate/laydate.js"></script>
<script src="../../../../static/libs/bootstrap.min.js"></script>
<script src="../../../../static/libs/bootstrapValidator.min.js"></script>
<script src="../../../../static/plugins/jqgrid/grid.locale-cn.js"></script>
<script src="../../../../static/plugins/jqgrid/jquery.jqGrid.min.js"></script>
<script src="../../../../static/js/common.js"></script>
<script src="../../../../static/libs/vue.min.js"></script>



</head>
<body >
<div id="rrapp" v-cloak>
	<div v-show="showList">
		<div class="panel panel-default">
            <div class="panel-heading">查询条件</div>
            <div class="panel-body">
                <form id="formSearch" class="form-horizontal" >
	                <div class="col-sm-12" style="margin-top: 10px;">	                	
	                	<#list queryHtmllist as item> 
						<div class="col-sm-3">
							<div class="input-group">
							    <div class="input-group-btn">
							        <label  class="btn btn-white">${item.label}</label>
							    </div>
							    <input type="text" class="form-control"  v-model="q.${item.name}">
							</div>                            
						</div>
						</#list>
						<div class="col-lg-2 col-sm-3">
								<div class="col-lg-12 col-sm-12">
									<a class="btn btn-info" @click="query" style="margin-right: 20px;"><i class="fa fa-search"></i>查询</a>
									<a class="btn btn-info" @click="reset" style="margin-right: 20px;"><i class="fa fa-refresh"></i>重置</a>
								</div>
							</div>
						</div>
              		 </div>
                </form>
			</div>
        </div> 
		<div style=" margin-bottom: 20px;">
			<a  class="btn btn-info" style="margin-right: 20px;" @click="add"><i class="fa fa-plus"></i>&nbsp;新增</a>
			<a  class="btn btn-info" style="margin-right: 20px;" @click="update"><i class="fa fa-pencil-square-o"></i>&nbsp;修改</a>
			<a  class="btn btn-info" style="margin-right: 20px;" @click="del"><i class="fa fa-trash-o"></i>&nbsp;删除</a>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">列表</div>
	        <div class="panel-body">
			    <table id="jqGrid"></table>
			    <div id="jqGridPager"></div>
			</div>
		</div>
    </div>
    
     <div v-show="!showList" class="panel panel-default">
		<div class="panel-heading">{{title}}</div>
		<form class="form-horizontal" id="busiForm"  style="margin-top: 20px;" onkeydown="if(event.keyCode==13){return false;}">
			<div class="form-group">
			   	<#list infoHtmllist as item> 
				<lable class="col-sm-1 control-label">${item.label}</lable>
				<div class="col-sm-3">
					<input type="text" class="form-control" id="${item.name}" name="${item.name}" v-model="model.${item.name}">                          
				</div>
				</#list>
			</div>
			<div class="form-group">
					<div class="col-sm-2 control-label"></div>
					<input type="button" class="btn btn-primary" @click="saveOrUpdate"value="保存" />&nbsp;&nbsp; 
					<input type="button" class="btn btn-warning" @click="reload" value="返回" />
			</div>
		</form>
	</div>
</div>
<script src="../../../../static/js/modules/busi/${jsName}"></script>
</body>
</html>