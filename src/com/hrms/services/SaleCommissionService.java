package com.hrms.services;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

import com.hrms.daoImpl.SaleCommissionImpl;
import com.hrms.model.SaleCommission;

@Path("/SaleCommission")
public class SaleCommissionService {
	SaleCommissionImpl saleCommissionImpl=new SaleCommissionImpl();
	@POST
	@Path("/SaveCommission")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveCommission(SaleCommission saleCommission){
		saleCommissionImpl.saveSaleCommission(saleCommission);
		return Response.status(200).build();
		}
	@POST
	@Path("/updateCommission")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean updateCommission(SaleCommission saleCommission){
		boolean status = false;
        try{
    	    long commissionId  = saleCommission.getCommissionId();
    		double commissionPercentage = saleCommission.getCommissionPercentage();
    		double salesAmount = saleCommission.getSalesAmount();
    		int salesCount  = saleCommission.getSalesCount();
    		String date        = saleCommission.getDate();
    		if(saleCommissionImpl.updateSaleCommission(commissionId, commissionPercentage, salesAmount, salesCount, date)){
    			status = true;
    		}
           }
        catch(Exception ex){
        	ex.printStackTrace();
        }
		return status;
	}
	@GET
	@Path(value="{commissionId}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean getCommission(@PathParam("commissionId")Long commissionId){
		return saleCommissionImpl.DeleteSales(commissionId);
	}
	@Path("/deleteSales")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public boolean deleteCommission(SaleCommission comm){
		boolean status = false;
		long commissionId = comm.getCommissionId();
		if(saleCommissionImpl.DeleteSales(commissionId)){
			status = true;
		}
		return status;
		
	}
	@Path("/ViewAllSalesCommssion")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<SaleCommission> viewAllSalesCommission()throws  JSONException{
		
		return saleCommissionImpl.getSaleCommissionList();
	
	}
}
