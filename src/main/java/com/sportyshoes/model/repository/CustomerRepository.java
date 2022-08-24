package com.sportyshoes.model.repository;



import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sportyshoes.model.beans.Customer;


@Repository
public class CustomerRepository {

	@Autowired
	EntityManager manager;

	@Transactional
	public int createProfile(Customer customer) {
		try 
		{
			manager.persist(customer);
			return 1;
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}
	}
	
	
}
