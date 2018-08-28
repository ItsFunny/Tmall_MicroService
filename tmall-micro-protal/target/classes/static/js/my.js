  $(function () {  
// jQuery.get("http://localhost:8091/rest/product/show/all",function(data,status){
// alert(data);
// });
	 $.ajax({  
//		url: "http://localhost:8091/rest/product/show/all",
		 url: "http://localhost:8093/test.html",
		type: "GET",  
		dataType: "json",  
        cache: false,  
		sucess: function(data){
			alert(data);
		},
		error: function(){
			alert("error");
		}
	 });
  });
