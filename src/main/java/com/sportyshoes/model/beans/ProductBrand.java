package com.sportyshoes.model.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="product_brand")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder=true)
@DynamicInsert
public class ProductBrand 
{
	@Id
	 
	@Column(columnDefinition = "varchar(255) default 'Nike'")
	private String brand_id;
	
	@Column(columnDefinition = "varchar(255) default 'Nike'")
	private String brand_name; //e.g. Nike, Addisda, New Balance

	@OneToOne(mappedBy = "productBrand")
	private Product product;

}