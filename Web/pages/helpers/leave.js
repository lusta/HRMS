$(document).ready(function(){
	
	var leaveViewModel = function(){
		
		var self = this;
		
		self.noOfDays = ko.observable();
		self.noOfTakenDays = ko.observable();
		self.noOfAvailableDays = ko.observable();
		self.departmentManagerApproval = ko.observable();
		self.hrManagerApprovalStatus = ko.observable();
		self.leaveStartDate = ko.observable();
		self.leaveEndDate = ko.observable();
		self.employee = ko.observable();
		
		var leaveObject ={
				noOfDays:self.noOfDays,
				noOfTakenDays:self.noOfTakenDays,
				noOfAvailableDays:self.noOfAvailableDays,
				departmentManagerApproval:self.departmentManagerApproval,
				hrManagerApprovalStatus:self.hrManagerApprovalStatus,
				leaveStartDate :self.leaveStartDate,
				leaveEndDate:self.leaveEndDate,
				employee:self.employee
		
		};
		self.allEmployees=ko.observableArray([]);
		$.getJSON('http://localhost:8080/HRMS/hr/employeeService/employeeList',				
				null, function(employeeData, status,xhr){			
			$.each(employeeData, function(index, value){
				self.allEmployees.push(value);
				console.log(value)
			});
			
		});
		
		
		self.save = function(){
			$.ajax({
				type:'POST',
				url:'http://localhost:8080/HRSystem/hrs/LeaveController/saveLeave',
				data:ko.toJSON(leaveObject),
				contentType:'application/json;charset=utf-8',
				dataType:'json',
				success:function(data){
					alert("Leave is being processed..")
				},
				error:function(data){
					alert("Failed to save leave request")
				}
			});
		}
	};
	ko.applyBindings(new leaveViewModel());
});
	

    