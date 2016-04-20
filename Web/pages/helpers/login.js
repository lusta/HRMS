$(document).ready(function(){

	function ValidateEmail(email) {
	    var expr = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	    return expr.test(email);
	};
	
	var loginViewModel = function(){
	
	self = this;
	self.email = ko.observable();
	self.password = ko.observable();
	
	var login = {
			
			email:	self.email,
			password:self.password,
	};
	
	self.LoginUser = function(){
		
		$("#demo").live("click", function () {
			var status = false;
		    if (!ValidateEmail($("#email").val())) {
		        alert("Invalid email address.");
		        return;
		    }	   
		$.ajax({
			type:'POST',
			data:ko.toJSON(login),
			url:'http://localhost:8080/HRMS/hr/EmployeeService/loginUser',
			dataType:'json',
			contentType:'application/json;charset=utf-8',
			success:function(data){
				if(data.toString()=='Admin'){
					window.location="pages/Admin.html";
				}
				else if(data.toString()=='HR'){
					window.location="pages/Hresource.html";
				}
				else if(data.toString()=='Manager'){
					window.location="pages/Manager.html";
				}
				else if(data.toString()=='Employee'){
					window.location="pages/employee.html";
				}
				else{
					alert(data);
					$('#username').html(data);
				}
			},
			error:function(XHR,status,er){
				alert(status);
				
			}
			
		});
	};

};
	  ko.applyBindings(new loginViewModel()); 
});
