/*package com.hrms.tester;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import com.hrms.daoImpl.DepartmentDaoIpml;
import com.hrms.daoImpl.JobDaoImpl;
import com.hrms.model.Department;
import com.hrms.model.Job;

public class TestJob {
	
	Job job = new Job();
	JobDaoImpl impl = new JobDaoImpl();
	
	DepartmentDaoIpml depImpl = new DepartmentDaoIpml();
	@Test
	public void addjob() {
		
		Department department = depImpl.getDepartment(1L);
		assertNotEquals(department, 1L);
		
		assertNotEquals(grade, 2L);
		Date startDate = new Date();
		
		job.setDepartment(department);
		job.setJobCategory("Top Management");
		job.setJobDescription("Senior Programmer");
		job.setJobLevel("Proffessional");
		job.setJobLocation("Bloemfontein");
		job.setJobTitle("Senior Programmer");
		job.setJobType("Permanent");
		job.setNoOfPositions(2);
		job.setStartDate(startDate);
		job.setGrade(grade);
		
		Long id = impl.saveJob(job);
		assertEquals(id, id);
	}

}
*/