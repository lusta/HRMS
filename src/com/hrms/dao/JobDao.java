package com.hrms.dao;

import java.util.List;

import com.hrms.model.Job;

public interface JobDao {

	public Long saveJob(Job job);
	public Long updateJob(Job job);
	public Job searchJob(Long jobId);
	public List<Job> jobList();
}
