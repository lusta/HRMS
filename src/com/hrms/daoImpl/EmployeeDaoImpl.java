package com.hrms.daoImpl;

import java.util.List;



import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;








import com.hrms.bDhelper.DBhelper;
import com.hrms.bDhelper.GMailClient;
import com.hrms.dao.EmployeeDoa;
import com.hrms.model.Employee;

public class EmployeeDaoImpl implements EmployeeDoa{
	
	EntityManagerFactory emf = null;
	EntityManager em = null; 
	
	 @Override
	 public boolean registerEmployee(Employee employee) 		
	   { 	
		 boolean status = false;
		 emf=DBhelper.provideEntity();
		 em=emf.createEntityManager();
		 em.getTransaction( ).begin( );	
		 if(!checkUser(employee.getEmail())){
			 em.persist(employee); 
			 em.getTransaction( ).commit( );
			 em.close( );
			 status = true;
		 }  					   				
		return status;
	   }
	 
	@Override
	 public List<Employee> employeeList() {
			emf = DBhelper.provideEntity();
			em = emf.createEntityManager();
		    TypedQuery<Employee> query = em.createQuery("from Employee", Employee.class);
			List<Employee> employee = query.getResultList();
			 
			 return employee;	

	}
		
	@Override
	public boolean updateEmployee( Long empNumber,String firstName, String lastName, String identityNumber, String title, String email, String contactNumber,
			String institution, String qualification, String position, String empType) 
	{
		boolean status = false;
		try{
			em.getTransaction( ).begin( );
		   	
	   		Query query =  em.createQuery("Update Employee employee Set employee.firstName = :Name, employee.identityNumber = :IdentityNumber, employee.lastName = :LastName, employee.title = :Title, employee.email = :Mail, employee.empType = :EmpType, employee.institution = :Institution, employee.qualification = :Qualification, employee.position = :Position where employee.empNumber = :EmpNumber");
	   		query.setParameter("EmpNumber",empNumber);
	   		query.setParameter("Name",firstName);	
			query.setParameter("IdentityNumber",identityNumber);
		 	query.setParameter("LastName", lastName);
		 	query.setParameter("Mail", email);
		 	query.setParameter("Title", title);
		 	query.setParameter("Institution", institution);
		 	query.setParameter("Qualification", qualification);
		 	query.setParameter("Position", position);
		 	query.setParameter("EmpType", empType);
		 	int employee = query.executeUpdate();
		 	if(employee >= 1 ){
		 		status=true;
		 	}

		 	em.getTransaction( ).commit( );
		 	em.close();
		 	em.close();
		 	
			}
			catch(Exception e){
				  e.printStackTrace();
			}
			   return status;
	   }
	@Override	
	public boolean DeleteEmployee( Long empNumber )
	{
		boolean status = false;
		try{
				emf = DBhelper.provideEntity();
				em = emf.createEntityManager();
				em.getTransaction( ).begin( );			   		
				Employee employee = em.find( Employee.class, empNumber );
				em.remove( employee );
				em.getTransaction( ).commit( );
				em.close( );
				em.close( );
			 }
			 catch(Exception e){
			   	e.printStackTrace();
			}
		return status;
	}	
	
	@Override
	public boolean SendMail(String email , String message) {

   	 try {
   		   String Link = "<br><a href='http://localhost:8080/Urbian/login.jsp' onclick="+"> Click here to login with your new password</a>";
            String massage = "please follow the following link to reset your password this is your new pass word "
            		          +" please use it to login  here "+Link;
            String subject = "Activate Email";
            String fromAddress = "json.ajax2@gmail.com";

            GMailClient mailClient = new GMailClient();

                mailClient.postMail(new String[]{email}, subject, "Hi " + email + ", \n" + massage, fromAddress);

        } 
        catch (Exception e1) 
        {
            e1.printStackTrace();
        }
		
		return false;		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean profile(String firstName, String lastName, String email){
		boolean status = false;
		 try{
			 	emf = DBhelper.provideEntity();
				em = emf.createEntityManager();			
			 	Query query = em.createQuery("from Employee where email = :Mail");
			 	query.setParameter("Mail", email);
			 	
			 	List<Employee> employees = query.getResultList();
	 
			 	if(employees.size()>=1)
			 	{
			 		status=true;
			 	}
			 	
		 	}
	   		catch(Exception e){
	   			e.printStackTrace();
	   		}
		 return status;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String AuthanticateUser(String email, String password){
		String type = "wrong passsword/username combination";
		 try{
			 	emf = DBhelper.provideEntity();
				em = emf.createEntityManager();			
			 	Query query = em.createQuery("from Employee where email = :Mail and password = :Password");
			 	query.setParameter("Mail", email);
			 	query.setParameter("Password", password);			 	
			 	List<Employee> employees = query.getResultList();
			 	if(employees.size()>=1)
			 	{
			 		type = checkUserType(email);
			 	}
			 	
		 	}
	   		catch(Exception e){
	   			e.printStackTrace();
	   		}
		 return type;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String checkUserType(String email) {
		String usertype = null;
		//Employee em = null;
		try{
	 	Query query = em.createQuery("select emp.empType from Employee emp where email = :Mail");	
	 	query.setParameter("Mail", email);
	 	List<Employee> employee = query.getResultList();
	 	if(employee.size()>=1)
	 	{	 	
	 		usertype = employee.toString().replace("[", "").replace("]", "");
	 	}}
		catch(Exception e){
			e.printStackTrace();
		}
        return usertype;
	}
	@Override
	public String createSession(String Email) {
		
		return null;
	}
    
	@Override
	public boolean resetPassWord(String Email) {
		try {
			    String confirmation = this.GenerateConfirmation();
	   		    String Link = "<br><a href='http://localhost:8080/HRMS/pages/changePassword.html' onclick="+"> Click here to login with your new password</a>";
	            String massage = "please follow the following link to reset your password this is the confirmation code to use "
	            		          +confirmation+ " please use it to reset here  here "+Link;
	            String subject = "Activate Email";
	            String fromAddress = "urbiantest@gmail.com";
	            GMailClient mailClient = new GMailClient();
	            mailClient.postMail(new String[]{Email}, subject, "Hi " + Email + ", \n" + massage, fromAddress);
	        } 
	        catch (Exception e1) 
	        {
	            e1.printStackTrace();
	        }
			
			return false;		
	}		
	 //this method generates password that we will send to user
    public String GenerateConfirmation()
    {    	
    	final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    	final int RANDOM_STRING_LENGTH = 20;  
    	StringBuffer randStr = new StringBuffer();
    	        for(int i=0; i<RANDOM_STRING_LENGTH; i++)
    	        {
    	        	int number = (int) (Math.random()*CHAR_LIST.length());
    	        	char ch = CHAR_LIST.charAt(number);
    	        	randStr.append(ch);
    	        }
    	 return randStr.toString();
   }

	@Override
	public boolean resetPass(String Email, String password) 
	{
		boolean status = false;
		try{
			em.getTransaction( ).begin( );		   	
	   		Query query =  em.createQuery("Update Employee employee Set employee.password = :Password,  where employee.email = :Email");
	   		query.setParameter("Password",password);
	   		query.setParameter("Email",Email);	
		 	int employee = query.executeUpdate();
		 	if(employee >= 1 ){
		 		status=true;
		 	}
		 	em.getTransaction().commit( );
		 	em.close();
		 	em.close();
		 	
			}
			catch(Exception e){
				  e.printStackTrace();
			}
			   return status;
	   }

	@SuppressWarnings("unchecked")
	@Override
	public boolean checkUser(String Email) {
		boolean status = false;
		try{
	 	Query query = em.createQuery("select emp.email from Employee emp where email = :Mail");	
	 	query.setParameter("Mail", Email);
	 	List<Employee> employee = query.getResultList();
	 	if(employee.size()>=1)
	 	{	 	
	 		status = true;
	 	}}
		catch(Exception e){
			e.printStackTrace();
		}
		return status;
	}
	@Override
	public Employee AccountProfile(String email){		
		
		Employee employee = null;
		 try{
			 	emf = DBhelper.provideEntity();
				em = emf.createEntityManager();			
				   @SuppressWarnings("rawtypes")
				TypedQuery query = em.createQuery("from Employee emp where emp.email=:Mail", Employee.class);
				    query.setParameter("Mail", email);
					employee = (Employee) query.getSingleResult();
					  	}
	   		catch(Exception e){
	   			e.printStackTrace();
	   		}
		return employee;
		
		 
	}

}
