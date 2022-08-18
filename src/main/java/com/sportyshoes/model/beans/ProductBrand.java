package com.sportyshoes.model.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "product_brand")
@Data
public class ProductBrand 
{
	@Id
	private String id;
	private String name;
	
	@OneToOne(mappedBy = "productBrand")
	private Product product;

}
