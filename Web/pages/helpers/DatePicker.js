 $(document).ready(function () {
        $("#tbStartDate").datepicker({        
            showOn: 'button',
            buttonImageOnly: true,
            buttonImage: '/HRMS/pages/images/cal.gif',
            buttonText: 'Click here (date)',
            onSelect: function (dateText, inst) {
                 var $endDate = $('#tbStartDate').datepicker('getDate');
                 $endDate.setDate($endDate.getDate() + 1);
                 $('#tbEndDate').datepicker('setDate', $endDate).datepicker("option", 'minDate', $endDate);
            },
            onClose: function (dateText, inst) {
                //$("#StartDate").val($("#tbStartDate").val());
            }
        });

        $("#tbEndDate").datepicker({
            dateFormat: 'dd-mm-yy',
            showOn: 'button',
            buttonImageOnly: true,
            buttonImage: '/HRMS/pages/images/cal.gif',
            buttonText: 'Click here (date)',
            onClose: function (dateText, inst) {
                //$("#EndDate").val($("#tbEndDate").val());
            }
        });

            var $endDate = $('#tbStartDate').datepicker('getDate');
            $endDate.setDate($endDate.getDate() + 1);
            $('#tbEndDate').datepicker('setDate', $endDate).datepicker("option", 'minDate', $endDate);
    });