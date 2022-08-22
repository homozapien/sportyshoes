package com.sportyshoes.model.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sportyshoes.model.beans.Product;

@Repository
public class ProductRepository {

	@Autowired
	EntityManager manager;

	@Transactional
	public int createProduct(Product product) {
		try {

			manager.persist(product);
			return 1;
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}
	}
	
	public List<Product> getAllProducts() 
	{
		Query qry = manager.createQuery("select prd from Product prd");  
		List<Product> productList = qry.getResultList();
		return productList;
	}
	
}
