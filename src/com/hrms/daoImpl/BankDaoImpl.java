package com.hrms.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import com.hrms.bDhelper.DBhelper;
import com.hrms.dao.BankDao;
import com.hrms.model.Bank;



public class BankDaoImpl implements BankDao{
	EntityManagerFactory emf = null;
	EntityManager em = null;
	@Override
	public Long saveBank(Bank bank) {
		emf=DBhelper.provideEntity();
		em=emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(bank);
		em.getTransaction().commit();
		em.close();	
		return bank.getBankId();
	}

	@Override
	public List<Bank> bankList() {
	    emf = DBhelper.provideEntity();
		em = emf.createEntityManager();
		TypedQuery<Bank> query = em.createQuery("from Bank bank ",Bank.class);
		List<Bank> results = query.getResultList();
		return results;
	}
	@Override
	public Bank getBank(Long bankId) {
		emf = DBhelper.provideEntity();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Bank bank = em.find(Bank.class,bankId);
		return bank;
	}

}
