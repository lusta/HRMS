$(document).ready(function(){
   
	var emailViewModel = function(){
	
	self = this;
	self.email = ko.observable();	
	var resetPassword = {
			
			email:self.email,
	};

	self.resetPass = function(){
		$.ajax({
			type:'POST',
			data:ko.toJSON(resetPassword),
			url:'http://localhost:8080/HRMS/hr/EmployeeService/reset',
			dataType:'json',
			contentType:'application/json;charset=utf-8',
			success:function(data){
				alert("an email has been sent to you " );
			},
			error:function(XHR,status,er){
				alert("an error happened ");
			}
			
		});
	};
};
   
	  ko.applyBindings(new emailViewModel()); 
});
