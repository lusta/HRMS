package com.hrms.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.hrms.daoImpl.JobDaoImpl;
import com.hrms.model.Job;


@Path("/JobService")
public class JobService {
	
	
	JobDaoImpl jobDaoImpl = null;
	
	@POST
	@Path("/saveJob")
	@Produces(MediaType.APPLICATION_JSON)
	public String saveJob(Job job)
	{
		jobDaoImpl = new JobDaoImpl();
		Long jobId = jobDaoImpl.saveJob(job);
		return jobId.toString();
	}
	@PUT
	@Path("/updateJob")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateJabo(Job job)
	{
		jobDaoImpl = new JobDaoImpl();
		Long jobId = jobDaoImpl.updateJob(job);
		
		return jobId.toString();
	}
	@GET
	@Path("{jobId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String getJob(@PathParam("jobId")Long jobId) throws JSONException
	{
		jobDaoImpl = new JobDaoImpl();
		Job job = jobDaoImpl.searchJob(jobId);
		JSONObject jobObect = new JSONObject();
		jobObect.put("", job);
		
		return jobObect.toString();
	}
	@GET
	@Path("/jobList")
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Job> jobList() throws JSONException
	{
		jobDaoImpl = new JobDaoImpl();
		return jobDaoImpl.jobList();
	}
}
