package com.sportyshoes.model.services;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoes.model.beans.Product;
import com.sportyshoes.model.repository.ProductRepository;

@Service
public class ProductService 
{
	@Autowired
	ProductRepository productRepository;
	
	public String createProduct(Product product) 
	{
		if(productRepository.createProduct(product)>0) 
		{
				return "New Product Item created successfully";
		}
		else 
		{
				return "Error during new product creation; record didn't insert";
		}
    } 
	
	public List<Product> getAllProducts()
	{
	//	List<Product> productList = productRepository.getAllProducts();
		
		List<Product> productList = productRepository.getAllProducts().stream()
		                                   .map(element -> {
		                                	   Product product =  element.toBuilder().build();
							        	       byte[] encodeBase64 = Base64.encodeBase64(element.getPrdlogo());
							                   String base64Encoded = new String(encodeBase64);
							                   product.setBase64PrdLogo(base64Encoded);
		        	                           return product;
		                               })
		                        .collect(Collectors.toList());
		
		
		return productList;
	}
	
}
