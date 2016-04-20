package com.hrms.services;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.hrms.daoImpl.EmployeeDaoImpl;
import com.hrms.daoImpl.ReportDaoImpl;
import com.hrms.model.Employee;
import com.hrms.model.Report;

@Path(value="/ReportService")
public class ReportService {

	@Path(value="/Report")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public boolean GenerateReport( Report report){
		boolean status = false;
		String firstName = report.getFirstName();
		String lastName = report.getLastName();
		String title = report.getTitle();
		String reportTitle = report.getReportTitle();
		String identityNumber = report.getIdentityNumber();
		String description = report.getDescription();
		
		ReportDaoImpl reportDao = new ReportDaoImpl();		
		
		try {
			status = reportDao.GenerateReport( firstName,  lastName,  identityNumber,
					 title,  reportTitle,  description);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return status;
		
	}
	
}
