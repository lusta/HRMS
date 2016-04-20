package com.hrms.dao;

import java.util.List;

import com.hrms.model.Bank;

public interface BankDao {

	public Long saveBank(Bank bank);
	public List<Bank> bankList();
	public Bank getBank(Long bankId);
	
}
