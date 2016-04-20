package com.hrms.dao;

import java.util.List;

import com.hrms.model.Employee;

public interface EmployeeDoa {
	
public boolean registerEmployee(Employee employee);

public boolean DeleteEmployee( Long empNumber );

public List<Employee> employeeList();

public boolean updateEmployee( Long empNumber,String firstName, String lastName, String identityNumber, String title, String email, String contactNumber,
		String institution, String qualification, String position, String empType ); 

public boolean SendMail(String email , String message);

public boolean profile(String firstName, String lastName, String email);

public String AuthanticateUser(String email, String password);

public String checkUserType(String email);

public String createSession(String Email);

public boolean resetPassWord(String Email);

public boolean resetPass(String Email , String password);

public boolean checkUser(String Email);

Employee AccountProfile(String email);
}
