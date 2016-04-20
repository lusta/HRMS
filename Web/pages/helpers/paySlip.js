$(document).ready(function() {
	 
			function payroll(data) {
				
				//var self = this;
  				   
  				  this.firstName = ko.observable(data.firstName);
  				  this.lastName = ko.observable(data.lastName);
  				  this.salary = ko.observable(data.salary);
  				  this.uif = ko.observable(data.uif);
  				  this.pension = ko.observable(data.pension);
  				  this.commissionAmount = ko.observable(data.commissionAmount);
  				  this.netSalary = ko.observable(data.netSalary);
  				  
  				this.totalDeduction = ko.computed(function(){
					 return this.uif()+this.pension();
					 
					},this);
					
		}
	function payrollViewModel(){
		
		self.payrollList = ko.observableArray([]);
		  
		$.getJSON('http://localhost:8080/HRSystem/hrs/payRollController/processPayments', function(allData) {
    			console.log(allData);
    			
                var mappedData= $.map(allData, function(data) 
                { 
                   return new payroll(data) 
                });
                
                self.payrollList(mappedData);
                console.log(self.payrollList());
            });
    	}

    		ko.applyBindings(new payrollViewModel());
    	});
   