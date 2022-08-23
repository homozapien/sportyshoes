package com.sportyshoes.model.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sportyshoes.model.beans.Product;
import com.sportyshoes.model.beans.ProductBrand;
import com.sportyshoes.model.beans.ProductUsage;

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
	
	@Transactional
	public int createBrand(ProductBrand brand) {
		try 
		{
			manager.persist(brand);
			return 1;
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}
	}
	
	@Transactional
	public int createUsageTpe(ProductUsage usage) {
		try {

			manager.persist(usage);
			return 1;
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}
	}
	
	
	public List<Product> getAllProducts() 
	{
		Query qry = manager.createQuery("select prd from Product prd");  
		return qry.getResultList();
	}
	
	
	public List<ProductBrand> getAllProductBrand() 
	{
		Query qry = manager.createQuery("select brand from ProductBrand brand");  
		
		return qry.getResultList();
	}
	
	public List<ProductUsage> getAllProductUsageTypes() 
	{
		Query qry = manager.createQuery("select usage from ProductUsage usage");  
		return qry.getResultList();
	}
	
}
