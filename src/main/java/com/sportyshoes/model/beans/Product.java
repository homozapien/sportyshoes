package com.sportyshoes.model.beans;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
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
@Inheritance(strategy = InheritanceType.JOINED)
public class Product 
{	
	@Id   
	private String prdid; 
	
	private String prdname; 
	
    @Lob
    private byte[] prdlogo;
    

    private double prdprice;
    
    @Transient
    private String base64PrdLogo;
    
    @OneToOne   
    @JoinColumn(name = "usage_id", referencedColumnName = "usage_id") //e.g. Athletics, Soccer, BasketBall, Hockey
	private ProductUsage productUsage;

	@OneToOne   
    @JoinColumn(name = "brand_id", referencedColumnName = "brand_id") //e.g. Nike, Adidas, Finish Line
	private ProductBrand productBrand;
	
}
