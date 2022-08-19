package com.sportyshoes.model.beans;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product 
{	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO) //@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name = "contact_id")
	private String uuid; //unique item number for a product (e.g BarCode) ; can be tied to qtt during purchase
	
	private String name; //Product name
	
	@OneToOne(cascade = CascadeType.ALL)   
    @JoinColumn(name = "type_id", referencedColumnName = "id") //e.g. Athletics, Soccer, BasketBall, Hockey
	private ProductType productType;

	
	@OneToOne(cascade = CascadeType.ALL)   
    @JoinColumn(name = "brand_id", referencedColumnName = "id") //e.g. Nike, Adidas, Finish Line
	private ProductBrand productBrand;
	
	@ManyToOne  
    @JoinColumn(name = "grp_id", referencedColumnName = "id") //e.g. Female, Male, Unisex, Adult, Youth
	private ProductGroup productGroup;
	
	private int quantity;
    private double unitPrice;
	
}
