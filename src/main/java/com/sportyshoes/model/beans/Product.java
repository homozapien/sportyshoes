package com.sportyshoes.model.beans;



import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
	private String prdid; //unique item number for a product (e.g BarCode) ; can be tied to qtt during purchase
	
	private String prdname; //Product name
	
	 //e.g. Athletics, Soccer, BasketBall, Hockey
	private String prdusage;

	//e.g. Nike, Adidas, Finish Line
	private String prdbrand;
	
    @Lob
    private byte[] prdlogo;
    

    private double prdprice;
	
}
