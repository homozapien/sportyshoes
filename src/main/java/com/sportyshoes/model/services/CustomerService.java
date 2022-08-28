package com.sportyshoes.model.services;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoes.model.beans.Customer;
import com.sportyshoes.model.repository.CustomerRepository;

@Service
public class CustomerService 
{
	@Autowired
	CustomerRepository custRepository;
	
	public String createProfile(Customer customer) 
	{
		if(custRepository.createProfile(customer) > 0)
		{
			return "New customer profile successfully created";
		}
		else
		{
			return "Exception while creating wew customer profile";
		}
    } 
	
	
	public Customer findCustomerProfileByRef(String emailid)
	{
		return custRepository.findCustomerProfileByRef(emailid);
		
	}
	
	
	public List<Customer> getAllCustomerProfiles()
	{
		return custRepository.getAllCustomerprofiles();
		
	}
	
	
	
}
