package com.hrms.dao;

import java.util.Date;
import java.util.List;

import com.hrms.model.Leave;

public interface LeaveDao {

	Long saveLeave(Leave leave);	
	Leave getLeave(Long leaveId);
	Long updateLeave(Leave leave);
	List<Leave> getLeaveList();
	
	int calculateNumberOfDays(Date start, Date end);
	int validateLeaveDays(int requestedLeaveDays,int actualDaysLeft);
	String leaveStatus(int validatedLeaveDays);
	
}
