package com.sportyshoes.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoes.model.beans.PurchaseOrder;
import com.sportyshoes.model.repository.PurchaseOrderRepository;

@Service
public class PurchaseOrderService 
{
	@Autowired
	PurchaseOrderRepository purchaseOrderRepository;
	
	public boolean createPurchaseOrder(PurchaseOrder po) 
	{
		if(purchaseOrderRepository.createPurchaseOrder(po)>0) 
		{
				return true;
		}
		else 
		{
				return false;
		}
    } 
	
	
	public List<PurchaseOrder> getAllPurchaseOrders()
	{
		return purchaseOrderRepository.getAllPurchaseOrders();
	}
	
	
}
