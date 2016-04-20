/**
 * 
 */
var table=null;
$(document).ready(function(){
	loadTable();
});

function loadTable()
{
	$.ajax({
		dataType: "text",//force ajax return text in order to parse json latter.
		url: "http://localhost:8080/HRMS/hr/EmployeeService/ViewEmployees", 
		success: function(data){

			var jsonTbl = JSON.parse(data);
			table=$("#tbl_employee").dataTable({
	        "bJQueryUI": true,
	        "iDisplayLength": 1,
	        "bLengthChange": true,
	        "aaData": jsonTbl,
	        "sAjaxSource": "http://localhost:8080/HRMS/hr/EmployeeService/ViewEmployees",
	        "bServerSide": true,
	        "bSort": false,
	        "bVisible":false,
	        "sPaginationType": "full_numbers",
	        "aoColumns": [	                   	                      
	                      {"sTitle": "First name", "mDataProp": "firstName" , "sWidth": "100px"},	                    
	                      {"sTitle": "Last name", "mDataProp": "lastName" , "sWidth": "100px"},	
	                      {"sTitle": "Title", "mDataProp": "title" , "sWidth": "80px"},	              
	                      {"sTitle": "Email", "mDataProp": "email" , "sWidth": "100px"},	                     
	                      {"sTitle": "Contact Number", "mDataProp": "contactNumber" , "sWidth": "60px"},
	                      {"sTitle": "Institution", "mDataProp": "institution" , "sWidth": "80px"},   
	                      {"sTitle": "Qualification", "mDataProp": "qualification" , "sWidth": "50px"},
	                      {"sTitle": "Position", "mDataProp": "position" , "sWidth": "50px"},	                                     
	                      {"sTitle": "Actions", "mDataProp": "empNumber", "sWidth": "90px" },
	                      
	                  ],
	                  "aoColumnDefs": [{ "bVisible": false, "aTargets": [] }],
	                  "iDeferLoading": [jsonTbl.length, jsonTbl.length],
	                  "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull) { 
	                	  
	       	           $('td:eq(8)', nRow).html('<input type="submit"  value="Details" onclick="doUpdate('+iDisplayIndex+');" class="mws-button green small"> <input type="button" class="mws-button blue small" value="Report" onclick="doGenerateReport('+iDisplayIndex+');"/>');
	
	                  }
	       	});
	    }
	});	
}

function doDelete(empNumber){

	var empNumber = empNumber;
	
	var saveJson = { empNumber: empNumber};
	
	var successF = function (data) {
		alert("Succesfuly removed");
		table.fnDestroy();
		loadTable();
    };
    
    var errorF = function (data) {
    	
        alert("Delete error, contact system admin!!!");
    };
	 hrms.ajax.post("http://localhost:8080/HRMS/hr/EmployeeService/DeleteEmployees",saveJson, successF, errorF);
}

function doUpdate(index){

	
	var data=table.fnGetData(index);	
	$("#updatediv").css("display", "block");
	$("#tbfirstName").val(data["firstName"]);	
	$("#tblastName").val(data["lastName"]);
	$("#tbidNumber").val(data["identityNumber"]);
	$("#tbtitle").val(data["title"]);	
	$("#tbemail").val(data["email"]);		
	$("#tbinstitution").val(data["institution"]);
	$("#tbqualification").val(data["qualification"]);
	$("#tbposition").val(data["position"]);
	$("#tbcontactNumber").val(data["contactNumber"]);
	$("#tbempType").val(data["empType"]);
	$("input[id=hf_empNumber]").val(data["empNumber"]);	
		
}

function doCancel(){
	$("#updatediv").css("display", "none");
}
function update(){
	var empNumber =$("input[id=hf_empNumber]").val();
	
	//Get Object
	var FirstName = $("#tbfirstName");
	var LastName = $("#tblastName");
	var ID = $("#tbidNumber");
	var Title = $("#tbtitle");
	var Email = $("#tbemail");
	var Institution = $("#tbinstitution");
	var Qualification = $("#tbqualification");
	var Position = $("#tbposition");
	var ContactNumber = $("#tbcontactNumber");
	var EmpType = $("#tbempType");
	
    //Get values
	var firstName = FirstName.val();
	var lastName = LastName.val();
	var identityNumber = ID.val();
	var title = Title.val();	
	var email = Email.val();
	var institution = Institution.val();
	var qualification = Qualification.val();
	var position = Position.val();
	var contactNumber = ContactNumber.val();
	var empType = EmpType.val();
	
	var saveJson = { 			
						empNumber : empNumber, 
						firstName : firstName, 			
						lastName : lastName, 
						identityNumber : identityNumber, 
						title : title, 					
						email : email, 	
						institution : institution,
						qualification : qualification,
						position : position, 
						contactNumber : contactNumber, 
						empType : empType		
					};

	    var successF = function (data) {
	    	
	    	if(data){
	    		alert("Succesfully updated");
	    		table.fnDestroy();
	    		loadTable();
	    		doCancel();
	    		
			}
			else{
				alert("Not updated");	  		
			}
			
	    };

	    var errorF = function (data) {
	    	alert("Failed to update");
	    };
	    
	    hrms.ajax.post("http://localhost:8080/HRMS/hr/EmployeeService/UpdateEmployees", saveJson, successF, errorF);

}

function doGenerateReport(index){

	var data=table.fnGetData(index);
	$("#reportdiv").css("display", "block");
	$("#firstName").val(data["firstName"]);	
	$("#lastName").val(data["lastName"]);
	$("#idNumber").val(data["identityNumber"]);
	$("#title").val(data["title"]);	
		
}

function doCancelReport(){
	$("#reportdiv").css("display", "none");
}

function doGetReport(){
	
	//Get Object
	var FirstName = $("#firstName");
	var LastName = $("#lastName");
	var ID = $("#idNumber");
	var Title = $("#title");
	var ReportTitle = $("#reportTitle");
	var Description = $("#description");
	
    //Get values
	var firstName = FirstName.val();
	var lastName = LastName.val();
	var identityNumber = ID.val();
	var title = Title.val();	
	var reportTitle = ReportTitle.val();
	var description = Description.val();
	
	var saveJson = { 			
						firstName : firstName, 			
						lastName : lastName, 
						identityNumber : identityNumber, 
						title : title, 					
						reportTitle : reportTitle, 	
						description : description	
					};

	    var successF = function (data) {
	    	
	    	if(data){
	    		alert("Report succesfully created");
	    		doCancelReport();
	    		
			}
			else{
				alert("Not created");	  		
			}
	    };

	    var errorF = function (data) {
	    	alert("Failed to create, contact system admin");
	    };
	    
	    hrms.ajax.post("http://localhost:8080/HRMS/hr/ReportService/Report", saveJson, successF, errorF);
}

