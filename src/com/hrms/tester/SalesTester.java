package com.hrms.tester;

import java.util.Date;

import com.hrms.daoImpl.SaleCommissionImpl;
import com.hrms.model.SaleCommission;

public class SalesTester {

	public static void main(String[] args) {

		
		SaleCommission sale = new SaleCommission();
		SaleCommissionImpl imp = new SaleCommissionImpl();
		sale.setCommissionId(2l);
		sale.setCommissionPercentage(90);
		sale.setSalesAmount(56);
		sale.setSalesCount(457);
		sale.setDate("23/09/2014");
		
		//long id = imp.saveSaleCommission(sale);
		boolean update = imp.updateSaleCommission(sale.getCommissionId(), sale.getCommissionPercentage(), sale.getSalesAmount(), sale.getSalesCount(), sale.getDate());
		if(update){
			System.out.println("done...");
		}
	}

}
