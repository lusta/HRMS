package com.hrms.tester;

import com.hrms.daoImpl.EmployeeDaoImpl;
import com.hrms.model.Employee;

public class LoginTester {

	public static void main(String[] args) {
		
		Employee emp = new Employee();
		EmployeeDaoImpl empd = new EmployeeDaoImpl();
		
		emp.setEmail("thabiso@gmail.com");
		emp.setPassword("12345");
		
		String email = emp.getEmail();
		String password = emp.getPassword();
		
		String login = empd.AuthanticateUser(email, password);
		if(login!=null){
			System.out.println("you in.."+login);
		}else{
			System.out.println("wrong details");
		}

	}

}
