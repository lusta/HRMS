package com.hrms.services;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.hrms.model.Leave;
import com.hrms.daoImpl.LeaveDaoImpl;

@Path(value="/LeaveService")
public class LeaveController {
LeaveDaoImpl LeaveDaoImpl=new LeaveDaoImpl();

@Path( value ="/SaveLeave")
@POST
@Consumes(MediaType.APPLICATION_JSON)
public Response SaveLeave(Leave leave){
	LeaveDaoImpl.saveLeave(leave);
	return Response.status(200).build();
	
}
@PUT
@Path(value="/UpdateLeave")
@Produces(MediaType.APPLICATION_JSON)
public Response updateLeave(Leave leave){
	LeaveDaoImpl.updateLeave(leave);
	return Response.status(200).build();
	
}
@Path(value="{leaveId}")
@GET
@Produces(MediaType.APPLICATION_JSON)
public String getLeave(@PathParam("leaveId")Long leaveId){
	Leave leave =LeaveDaoImpl.getLeave(leaveId);
	return leave.toString();
}

@Path(value="/ViewAllLeaves")
@GET
@Produces(MediaType.APPLICATION_JSON)
public List<Leave> viewAllLeaves()throws  JSONException{
	LeaveDaoImpl = new LeaveDaoImpl();
	return LeaveDaoImpl.getLeaveList();

}
@Path(value="/CalculateLeave")
@GET
@Produces(MediaType.APPLICATION_JSON)
public Response getNumberOfdays(Date startDate, Date EndDate){
	
	LeaveDaoImpl.calculateNumberOfDays(startDate, EndDate);
	
	return Response.status(200).build();
	
}
@Path(value="/ValidateLeave")
@GET
@Produces(MediaType.APPLICATION_JSON)
public Response ValidateLeave(int requestedDays,int actualDays){
	
	LeaveDaoImpl.validateLeaveDays(requestedDays, actualDays);
	
	return Response.status(200).build();
	
}
}
