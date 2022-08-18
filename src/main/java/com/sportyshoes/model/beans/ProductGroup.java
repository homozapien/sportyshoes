package com.sportyshoes.model.beans;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "product_group")
@Data
public class ProductGroup 
{
	@Id
	private String id;
	private String name; 
	private String generic;
	
	@OneToMany (mappedBy="productGroup")
	private Set<Product> setOfProducts;

}
