var vm = new Vue({
	el : '#app',
	data : {
		list :[],
	},
	created : function() {
		this.getBidSupplier();
	},
	methods : {
		getBidSupplier : function (){
			var url =baseURL+"busi/bid/bidShow" ;
			$.ajax({
		          url: url,
		          type: "GET",
		          dataType: "json",
		          success: function(r) {
		        	 vm.list = r.list;
		          },
		          error: function() {
		        	  alert(r.msg);
		          }
		     });
		},
	},
})