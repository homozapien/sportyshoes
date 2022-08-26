package com.sportyshoes.model.repository;


import javax.persistence.EntityManager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sportyshoes.model.beans.PurchaseOrder;

@Repository
public class PurchaseOrderRepository {

	@Autowired
	EntityManager manager;

	@Transactional
	public int createPurchaseOrder(PurchaseOrder po) {
		try 
		{
			manager.persist(po);
			return 1;
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}
	}
	
	
	
}
