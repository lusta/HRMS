package com.hrms.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.hrms.bDhelper.DBhelper;
import com.hrms.dao.SaleCommissionDao;
import com.hrms.model.Department;
import com.hrms.model.Employee;
import com.hrms.model.SaleCommission;


public class SaleCommissionImpl implements SaleCommissionDao
{
	EntityManagerFactory emf = null;
	EntityManager em = null;
	@Override
	public Long saveSaleCommission(SaleCommission saleCommission) {		
		emf=DBhelper.provideEntity();
		em=emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(saleCommission);
		em.getTransaction().commit();
		em.close();	
		return saleCommission.getCommissionId();
	}
	@Override	
	public boolean DeleteSales(Long commissionId)
	{
		emf = DBhelper.provideEntity();
		em = emf.createEntityManager();	
		em.getTransaction( ).begin( );
		SaleCommission sales = em.find( SaleCommission.class,commissionId);
	    em.remove(sales);
	    em.getTransaction( ).commit();
	    em.close( );
	    return true;
	}	
	@Override
	public SaleCommission getSaleCommission(Long saleCommissionId) {
		emf = DBhelper.provideEntity();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		SaleCommission sales = em.find(SaleCommission.class,saleCommissionId);
		return sales;
	}
	@Override
	public List<SaleCommission> getSaleCommissionList() {
		emf = DBhelper.provideEntity();
		em = emf.createEntityManager();
		TypedQuery<SaleCommission> query = em.createQuery("from SaleCommission sales ",SaleCommission.class);
		List<SaleCommission> results = query.getResultList();
		return results;
	}
	@Override
	public boolean updateSaleCommission(Long commissionId,double commissionPercentage, double salesAmount, int salesCount,String date) {
		boolean status = false;
		try
		{			
		emf = DBhelper.provideEntity();
		em = emf.createEntityManager();
		em.getTransaction().begin();
   		Query query =  em.createQuery("Update SaleCommission sales Set sales.commissionPercentage = :Sales, sales.salesAmount = :Amount, sales.salesCount = :Count, sales.date = :Date where sales.commissionId = :commissionId");
   		query.setParameter("commissionId",commissionId);
   		query.setParameter("Sales",commissionPercentage);	
		query.setParameter("Amount",salesAmount);
	 	query.setParameter("Count", salesCount);
	 	query.setParameter("Date", date);
	 	int sales = query.executeUpdate();
	 	if(sales >= 1 ){
	 		status=true;
	 	}

	 	em.getTransaction( ).commit( );
	 	em.close(); 	
		}
		catch(Exception e){
			  e.printStackTrace();
		}
		   return status;
	}
	
}
