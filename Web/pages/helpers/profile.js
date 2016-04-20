
$(document).ready(function(){
	 $.ajax({
         type: 'GET',
         url: "http://localhost:8080/HRMS/hr/EmployeeService/profile",
         dataType: "json", // data type of response
         success:function(data){        	 
        	       
					$('#mws-username').html(data.email);	
					$('#mws-firstname').html(data.firstName);
					$('#mws-lastname').html(data.lastName);					
					$('#mws-position').html(data.position);
					$('#mws-employeetype').html(data.empType);
					$('#mws-institution').html("Name of institution:"+data.institution);
					$('#mws-qualification').html("Name of qualification: "+data.qualification);
			},
			error:function(XHR,status,er){
				//alert(status);
			}
     });
});
