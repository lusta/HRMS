package com.hrms.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.hrms.daoImpl.EmployeeDaoImpl;
import com.hrms.model.Employee;
import com.sun.jersey.api.client.ClientResponse.Status;


@Path(value="/EmployeeService")
public class EmployeeService {
	EmployeeDaoImpl empDaoImpl = null;
	@Path(value="/RegisterEmployees")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String RegisterEmployee(Employee employee)
	{
		String status = "User already exist";
		EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
		if(employeeDao.registerEmployee(employee)){
			status =  "success";
		}		
		return  new Gson().toJson(status);
	}
	
	@Path(value="/ViewEmployees")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> findAll() {
		empDaoImpl = new EmployeeDaoImpl();	
		return empDaoImpl.employeeList();	
		
	}
	
	@Path(value="/DeleteEmployees")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public boolean DeleteData(Employee employee)
	{
		Long empNumber = employee.getEmpNumber();
			
		empDaoImpl = new EmployeeDaoImpl();
		boolean status=false;
		try {
				status = empDaoImpl.DeleteEmployee( empNumber );
					
			} 
			catch (Exception e) {
				e.printStackTrace();
			}		
			 return status;	
	}
	
	@Path(value="/UpdateEmployees")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public boolean  UpdateEmployee(Employee employee){
		Long empNumber = employee.getEmpNumber();
		String firstName = employee.getFirstName();
		String lastName = employee.getLastName();
		String email = employee.getEmail();
		String identityNumber = employee.getIdentityNumber();
		String title = employee.getTitle();
		String institution = employee.getInstitution();
		String qualification = employee.getQualification();
		String position = employee.getPosition();
		String contactNumber = employee.getContactNumber();
		String empType = employee.getEmpType();
		
		boolean status = false;
		empDaoImpl = new EmployeeDaoImpl();
		try{
			status = empDaoImpl.updateEmployee(   empNumber, firstName,  lastName,  identityNumber,  title,  email,  contactNumber, institution,  qualification,  position,  empType );			 
		}
		catch(Exception e){
			e.printStackTrace();
		}	
		return status;
	}
	
	@Path(value="/email")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public boolean SendMail(Employee employee) {
		boolean status = false;
		String email = employee.getEmail();
		String message = "testting";
		EmployeeDaoImpl empDaoImpl = new EmployeeDaoImpl();
		
		try {
			status = empDaoImpl.SendMail(email,message);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return status;
		
	}
	
	@POST
	@Path("/employeeProfile")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean  EmployeeProfile(Employee employee){
		boolean status = false;
		String email = employee.getEmail();
		String firstName = employee.getFirstName();
		String lastName = employee.getLastName();
		
		empDaoImpl = new EmployeeDaoImpl();
		try {
				status = empDaoImpl.profile( firstName,  lastName,  email);
			
			} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return status;		
	}
	@POST
	@Path("/loginUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String  LoginUser(Employee employee , @Context HttpServletRequest req){
	 HttpSession session= req.getSession(true);
	 empDaoImpl = new EmployeeDaoImpl();		
	 String login=empDaoImpl.AuthanticateUser(employee.getEmail() , employee.getPassword());	
	 Gson son=new Gson();
	 String result=son.toJson(login);	
	 if(result!=null){
		 session.setAttribute("email", employee.getEmail()); 
	 }
	 return result;
	}
	@Path("/user")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getSession(@Context HttpServletRequest req){
		HttpSession session= req.getSession(true);
		Gson son=new Gson();
		String a = (String) session.getAttribute("email");	
		String result=son.toJson(a);
		return result;
	}
	@Path("/reset")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public boolean resetPass(Employee employee){
		String email = employee.getEmail();
		System.out.println(email);
		boolean result = false;
		empDaoImpl = new EmployeeDaoImpl();
		if(empDaoImpl.resetPassWord(email)){
			result = true;
		}
		return result;		
	}
	@Path("/resetPassword")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean rsetPassword(Employee employee){
		boolean status = false;
		empDaoImpl = new EmployeeDaoImpl();
		if(empDaoImpl.resetPass(employee.getEmail(), employee.getPassword())){
			status = true;
		}
		return status;
		
	}
	@Path("/profile")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Employee AccountProfile(@Context HttpServletRequest req){
		HttpSession session= req.getSession(true);
		empDaoImpl = new EmployeeDaoImpl();
		//Gson son=new Gson();
		String a = (String) session.getAttribute("email");							
		return empDaoImpl.AccountProfile(a);
	}
}

