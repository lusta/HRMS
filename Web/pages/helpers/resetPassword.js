$(document).ready(function(){
   
	var resetViewModel = function(){
	
	self = this;
	self.email = ko.observable();
	self.password = ko.observable();
	self.pass = ko.observable();
	
	var resetPassword = {
			
			email:self.email,
			password:self.password,
	};
	self.savePassword = function(){
	    if(self.password!=self.pass){
	    	alert("passwords dont match " );
	    	return;
	    }
		$.ajax({
			type:'POST',
			data:ko.toJSON(resetPassword),
			url:'http://localhost:8080/HRMS/hr/EmployeeService/resetPass',
			dataType:'json',
			contentType:'application/json;charset=utf-8',
			success:function(data){
				alert("password reseted.. " );
			},
			error:function(XHR,status,er){
				alert("an error happened ");
			}
			
		});
	};
};
   
	  ko.applyBindings(new resetViewModel()); 
});
