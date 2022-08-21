package com.sportyshoes.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoes.model.beans.Product;
import com.sportyshoes.model.repository.ProductRepository;

@Service
public class ProductService 
{
	@Autowired
	ProductRepository productRepository;
	
	public String createProduct(Product product) {
		if(productRepository.createProduct(product)>0) 
		{
				return "New Product Item created successfully";
		}else {
				return "Error during new product creation; record didn't insert";
		}
   } 
}
