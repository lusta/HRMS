package com.hrms.dao;

import java.util.List;

import com.hrms.model.SaleCommission;

public interface SaleCommissionDao {

	Long saveSaleCommission(SaleCommission saleCommission);
	SaleCommission getSaleCommission(Long saleCommissionId);
	boolean updateSaleCommission(Long commissionId,double commissionPercentage, double salesAmount, int salesCount, String date);
	List<SaleCommission> getSaleCommissionList();
	boolean DeleteSales(Long commissionId);

}
