package com.sportyshoes.model.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity(name = "product_type")
@Data
public class ProductType 
{
	@Id
	private String id;
	private String usageType; //e.g. Athletics, Soccer, BasketBall, Hockey
	
	@OneToOne(mappedBy = "productType")
	private Product product;

}
