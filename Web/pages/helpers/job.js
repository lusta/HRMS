function jobViewModel(){
	var self = this;
	self.jobTitle = ko.observable("");
	self.jobLevel = ko.observable("");
	self.jobLocation = ko.observable("");
	self.jobCategory = ko.observable("");
	self.noOfPositions = ko.observable("");
	self.jobType = ko.observable("");
	self.jobDescription = ko.observable("");
	self.probitionPeriod = ko.observable("");
	self.startDate = ko.observable("");
	self.endDate = ko.observable("");
	
	var jobObject = 
	   {
			jobTitle: self.jobTitle,
			jobLevel:self.jobLevel,
			jobLocation:self.jobLocation,
			jobCategory:self.jobCategory,
			noOfPositions:self.noOfPositions,
			jobDescription:self.jobDescription,
			probitionPeriod:self.probitionPeriod,
			startDate:self.startDate,		
		};
		
	self.saveJob = function(){
	   
	    $.ajax({
	    	    data: ko.toJSON(jobObject),
	            type:'POST',
	            url:'http://localhost:8080/HRMS/hr/JobService/saveJob',
	            contentType: 'application/json',
	            dataType:"json",
	         success: function(data){
	            console.log(data);
	            alert(data.address);
	            alert("Record Added Successfully");
	            console.log(data);
	            },
	         error: function (data, status)
	         {
	             alert("Failed" + status);
	         }
	    });
	  };
		
	};
	
ko.applyBindings(new jobViewModel());

