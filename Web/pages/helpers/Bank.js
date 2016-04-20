$(document).ready(function(){
	var bankViewModel = function(){	
	    self = this;
	    self.accountNumber = ko.observable();
		self.bankName = ko.observable();
		self.branchName = ko.observable();
		self.accountType = ko.observable();
		self.branchCode = ko.observable();
	
 		var bankObject ={
 	 			
 			    accountNumber: self.accountNumber,
				bankName:self.bankName,
				branchName:self.branchName,
				accountType:self.accountType,
				branchCode:self.branchCode,
 		}
	self.saveBankDetails = function(){
		$.ajax({
			type:'POST',
			data:ko.toJSON(bankObject),
			url:'http://localhost:8080/HRMS/hr/BankService/saveBankDetails',
			dataType:'json',
			contentType:'application/json;charset=utf-8',
			success:function(data){
				alert("Bank is added " );
				self.accountNumber("");
				self.bankName("");
				self.branchName("");
				self.accountType("");
				self.branchCode("");
			},
			error:function(XHR,status,er){
				alert("Failed to save Bank details...");
			}
			
		  });
	    };

	};
	 ko.applyBindings(new bankViewModel()); 
});
