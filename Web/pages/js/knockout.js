var viewModel = function()
{
    var self = this;
    self.fname = ko.observable();
    //self.mname = ko.observable();
    self.lname = ko.observable();
    
    self.title = ko.observable();
    self.id_number = ko.observable();
    //self.date = ko.observable();    
    
    //self.optionProvince = ["Northern Cape","Eastern cape","Free State","Western Cape","Limpopo","North West","Kwazulu-Natal","Mpumalanga","Gauteng"],
    //self.province = ko.observable();    
    //self.gender = ko.observable();    
    //self.race = ko.observable();
    //self.optionRace = ["African","Asian", "Coloured", "White"], 
    
    //self.optionCountry = ["South Africa","India", "United States", "Nigeria"],
    //self.country = ko.observable();    
    self.email = ko.observable(); 
    //self.address = ko.observable();
    
    //self.code = ko.observable();
    self.cnumber = ko.observable();
    //self.onumber = ko.observable();
    
    self.institution = ko.observable();
    self.optionInstituion = ["Cape Peninsula University of Technology","University of Johannesburg","University of Limpopo","University of Venda","University of South Africa", "Walter Sisulu university", "University of Pretoria"],
    
    self.selectedOptionValue = ko.observable(),    
    self.optionValues = ["Diploma","B-Tech", "Degree", "Honours"],
    
    self.position = ko.observable();    
    self.optionPosition = ["Training","Java Develper", "Web developer"],    

    self.letter = ko.observable();    
       
    var User = {
    		firstName: self.fname,
    
    		lastName: self.lname,
    		identityNumber: self.id_number,

    		title: self.title,
    		email: self.email,  		
    		contactNumber: self.cnumber,    		
    		institution: self.institution,
    		qualification: self.selectedOptionValue,    		
    		position: self.position,  		
            coverLetter: self.letter,
     
                                 
            }
   
    self.saveApplicant = function(model , event)
    {
        $.ajax({
        	type: "POST",
            url: "http://localhost:8080/HRMS/hr/ApplicantService/addApplicant",           
            data: ko.toJSON(User),
            contentType: 'application/json',
            dataType: "json",
            success: function(data)
            {
                alert("Added Successfully!")
            },

            error: function(http,status)
            {
                alert("Failed to save :");
            }
       })
    }

}

$(document).ready(function()
		{
		    var model = new viewModel();		   
		    ko.applyBindings(model);
		    
});
