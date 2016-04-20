
$(document).ready(function(){
	 $.ajax({
         type: 'GET',
         url: "http://localhost:8080/HRMS/hr/EmployeeService/user",
         dataType: "json", // data type of response
         success:function(data){            				 
					$('#mws-username').html("Welcome : "+data);
			},
			error:function(XHR,status,er){
				//alert(status);
			}
     });
});