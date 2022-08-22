package com.sportyshoes.model.beans;



import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder=true)
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
    
    @Transient
    private String base64PrdLogo;
	
}
