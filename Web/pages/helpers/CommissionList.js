var table=null;
$(document).ready(function(){
	loadData();
});

function loadData(){
	$.ajax({
		dataType: "text",//force ajax return text in order to parse json latter.
		url: "http://localhost:8080/HRMS/hr/SaleCommission/ViewAllSalesCommssion", 
		success: function(data){
						//custome parse Json						
		var jsonTbl = JSON.parse(data);
		 table = $("#commission").dataTable({
		 "bJQueryUI": true,
		 "iDisplayLength": 1,
		 "bLengthChange": true,
		 "aaData": jsonTbl,
	     "sAjaxSource": "http://localhost:8080/HRMS/hr/SaleCommission/ViewAllSalesCommssion",
		 "bServerSide": true,
		 "bSort": false,
		 "bVisible":true,
		 "bProcessing": false,
		 "sPaginationType": "full_numbers",
		 "aoColumns": [
					   {"sTitle": "Commission ID", "mDataProp": "commissionId", "sWidth": "100px" },
					   {"sTitle": "commissionPercentage", "mDataProp": "commissionPercentage" , "sWidth": "200px"},
					   {"sTitle": "salesAmount", "mDataProp": "salesAmount", "sWidth": "100px" },
					   {"sTitle": "salesCount", "mDataProp": "salesCount", "sWidth": "200px" },                     
					   {"sTitle": "Date", "mDataProp": "date", "sWidth": "200px" },
					   {"sTitle": "Actions", "mDataProp": "", "sWidth": "200px" },
					  ],
			"aoColumnDefs": [{ "bVisible": false, "aTargets": [] }],
			"iDeferLoading": [2, 2],
			"fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {     	           
			 $('td:eq(5)', nRow).html('<input type="submit" value="update" onclick="doUpdate('+iDisplayIndex+');" class="mws-button green">&nbsp; <input type="button" class="mws-button red" value="Delete" onclick="doDelete('+aData["commissionId"]+');"/><br/>');
			 }
		   });      	  	
		}
	 });
}

function doDelete(commissionId){
	
	var commissionId = commissionId;	
	var saveJson = { commissionId: commissionId};
	
	var successF = function (data) {
		alert("Succesfuly removed");
		table.fnDestroy();
		loadData();
    };
    
    var errorF = function (data) {
    	
        alert("Delete error!!!");
    };
	 hrms.ajax.post("http://localhost:8080/HRMS/hr/SaleCommission/deleteSales",saveJson, successF, errorF);
   }

    function doUpdate(index){

	var data=table.fnGetData(index);
	$("#updatediv").css("display", "block","top");
	$("#commissionId").val(data["commissionId"]);	
	$("#commissionPercentage").val(data["commissionPercentage"]);	
	$("#salesAmount").val(data["salesAmount"]);
	$("#salesCount").val(data["salesCount"]);
	$("#date").val(data["date"]);	
	}

    function doCancel()
    {
	$("#updatediv").css("display", "none");
    }
    function update(){
	//var commissionId =$("input[id=hf_commissionId]").val();
	
	//Get Object
	var commissionId = $("#commissionId");
	var commissionPercentage = $("#commissionPercentage");
	var salesAmount = $("#salesAmount");
	var salesCount = $("#salesCount");
	var date = $("#date");
		
    //Get values
	var commissionId = commissionId.val();
	var commissionPercentage = commissionPercentage.val();
	var salesAmount = salesAmount.val();
	var salesCount = salesCount.val();
	var date = date.val();	
	
	var saveJson = { 		
			        commissionId : commissionId,
			        commissionPercentage : commissionPercentage, 
			        salesAmount : salesAmount, 			
			        salesCount : salesCount, 
			        date : date, 
					};

	    var successF = function (data) {	    	
	    	if(data)
	    	{
	    		alert("Succesfully updated");
	    		table.fnDestroy();
	    		loadData();
			}
			else
			{
				alert("Not updated");	  		
			}
			
	    };

	    var errorF = function (data) 
	    {
	    	alert("Failed to update");
	    };
	    
	    hrms.ajax.post("http://localhost:8080/HRMS/hr/SaleCommission/updateCommission", saveJson, successF, errorF);
}

