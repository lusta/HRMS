$(document).ready(function(){
	
	var editor;
	
	 $('#example').DataTable( {
		 dom: "Tfrtip",
		"bProcessing": true,
	    "bServerSide": true,
        "bProcessing": true,
        "bServerSide": true,
		ajax: "http://localhost:8080/HRSystem/hrs/payRollController/processPayments",
		columns: [
			{data: "lastName"},
			{data: "firstName"},
			{data: "Salary"},
			{data: "uifDeduction"},
			{data: "pensionDeduction"}
		],
		tableTools: {
			sRowSelect: "os",
			aButtons: [
		                { sExtends: "editor_create", editor: editor },
		                { sExtends: "editor_edit",   editor: editor },
		                { sExtends: "editor_remove", editor: editor }
		            ]
		}			
	});
});