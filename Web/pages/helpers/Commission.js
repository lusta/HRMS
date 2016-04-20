$(document).ready(function(){
	var salesViewModel = function(){
	
	self = this;
	self.commissionPercentage = ko.observable();
	self.salesAmount = ko.observable();
	self.salesCount = ko.observable();
	self.date = ko.observable();
	self.commissionId = ko.observable();
	
	var salesObject = {
			
			commissionPercentage:self.commissionPercentage,
			salesAmount:self.salesAmount,
			salesCount:self.salesCount,
			date : self.date,
	};
	self.saveCommission = function(){
		$.ajax({
			type:'POST',
			data:ko.toJSON(salesObject),
			url:'http://localhost:8080/HRMS/hr/SaleCommission/SaveCommission',
			dataType:'json',
			contentType:'application/json;charset=utf-8',
			success:function(data){
				alert("sales is added " );
			},
			error:function(XHR,status,er){
				alert("Failed to save sales...");
			}
			
		  });
	    };
		self.commissionId("");
		self.commissionPercentage("");
		self.salesAmount("");
		self.salesCount("");
		self.date("");
	};
	 ko.applyBindings(new salesViewModel()); 
});
