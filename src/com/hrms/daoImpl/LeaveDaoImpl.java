package com.hrms.daoImpl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import com.hrms.bDhelper.DBhelper;
import com.hrms.dao.LeaveDao;
import com.hrms.model.Leave;

public class LeaveDaoImpl implements LeaveDao
{

	EntityManagerFactory emf = null;
	EntityManager em = null;
		
	@Override
	public Long saveLeave(Leave leave) {
		
		emf=DBhelper.provideEntity();
		em=emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(leave);
		em.getTransaction().commit();
		em.close();	
	   return leave.getLeaveId();

	}

	@Override
	public Leave getLeave(Long leaveId) {

		emf = DBhelper.provideEntity();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Leave leave = em.find(Leave.class,leaveId);
		return leave;
	}

	@Override
	public Long updateLeave(Leave leave) {

		emf = DBhelper.provideEntity();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(leave);
		em.getTransaction().commit();
		em.close();		
		return leave.getLeaveId();
	}

	@Override
	public List<Leave> getLeaveList() {
	    emf = DBhelper.provideEntity();
		em = emf.createEntityManager();
		TypedQuery<Leave> query = em.createQuery("from Leave liv ",Leave.class);
		List<Leave> results = query.getResultList();
		return results;
	}


	@SuppressWarnings("static-access")
	@Override
	public int calculateNumberOfDays(Date start, Date end) {

		Calendar calendarStart = Calendar.getInstance();
		Calendar calendarEnd = Calendar.getInstance();
		
		calendarStart.setTime(start);
		calendarEnd.setTime(end);
		
		int days = 0;
		int differentMonthDays = 0;
		
		if(calendarEnd.get(calendarEnd.MONTH) > calendarStart.get(calendarStart.MONTH))
		{			
			differentMonthDays = calendarStart.getActualMaximum(calendarStart.DATE) - calendarStart.get(calendarStart.DATE);
			
			days = (calendarEnd.get(calendarEnd.DATE) + differentMonthDays);	
		}
		else if(calendarEnd.get(calendarEnd.MONTH) == calendarStart.get(calendarStart.MONTH))
		{
			days = (calendarEnd.get(calendarEnd.DATE) - calendarStart.get(calendarStart.DATE));			
		}
		
		if(((calendarStart.get(calendarStart.YEAR) == calendarEnd.get(calendarEnd.YEAR)) &&
			(calendarStart.get(calendarStart.MONTH) == calendarEnd.get(calendarEnd.MONTH)))	&&
			(calendarStart.get(calendarStart.DATE) == calendarEnd.get(calendarEnd.DATE)))
		{
			days = 1;
		}
		
		return days;
	}

	@Override
	public int validateLeaveDays(int requestedLeaveDays,int actualDaysLeft) {
	
		int leaveDays = 0;
		
		if(actualDaysLeft > 0)
		{
			leaveDays = actualDaysLeft - requestedLeaveDays;
		}
		else if(requestedLeaveDays > actualDaysLeft)
		{
			leaveDays = -1;
		}
		
		
		return leaveDays;
	}

	@Override
	public String leaveStatus(int validatedLeaveDays) {
		
		String leaveStatus = "";
		
		if(validatedLeaveDays == -1)
		{
			leaveStatus = "Denied - Verify leave days requested";
		}
		else if(validatedLeaveDays == 0)
		{
			leaveStatus = "No Leave days available";
		}
		else if(validatedLeaveDays > 0)
		{
			leaveStatus = "Approved ";
		}
		
		return leaveStatus;
	}

}
