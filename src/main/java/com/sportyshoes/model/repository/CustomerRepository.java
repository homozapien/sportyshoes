package com.sportyshoes.model.repository;



import java.util.List;

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
	
	public Customer findCustomerProfileByRef(String emailid)
	{
		//return manager.find(Customer.class, emailid);
		return manager.getReference(Customer.class, emailid);
		
	}
	
	public List<Customer> getAllCustomerprofiles()
	{
		List<Customer> listofCustmers = manager.createQuery(" from Customer ").getResultList();  
		
		return listofCustmers;
	}
	
	
}
