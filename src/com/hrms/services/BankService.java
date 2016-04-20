package com.hrms.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;

import com.hrms.daoImpl.BankDaoImpl;
import com.hrms.model.Bank;

@Path("/BankService")
public class BankService {

	BankDaoImpl bankDaoImpl = null;
	@GET
	@Path("/bankList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Bank> bankList() throws JSONException
	{
		bankDaoImpl = new BankDaoImpl();
        return bankDaoImpl.bankList();
	}
	
	@POST
	@Path("/saveBankDetails")
	@Produces(MediaType.APPLICATION_JSON)
	public String saveBankDetails(Bank bank)
	{
		bankDaoImpl= new BankDaoImpl();
		Long bankId = bankDaoImpl.saveBank(bank);
		return bankId.toString();
	}

}
