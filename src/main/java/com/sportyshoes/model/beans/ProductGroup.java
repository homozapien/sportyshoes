package com.sportyshoes.model.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
//@Data
//@Table(name="product_group")
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder(toBuilder=true)
//@DynamicInsert
public class ProductGroup 
{
	@Id
	@Column(columnDefinition = "varchar(255) default 'Unisex'")
	private String group_id;
	@Column(columnDefinition = "varchar(255) default 'Unisex'")
	private String group_name; //e.g. Females, Males, Unisex

	@OneToOne(mappedBy = "productGroup")
	private Product product;

}