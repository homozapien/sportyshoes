package com.sportyshoes.model.beans;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="purchaseorder")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrder 
{

	@Id
   // @GeneratedValue(strategy = GenerationType.AUTO)
	private int orderid;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order") 
	private Set<PurchaseItem> item;
	
	
	 @OneToOne(cascade = CascadeType.ALL, optional=false) 
	 @JoinColumn(name = "orderedby", referencedColumnName = "emailid" ) 
	 private Customer customer; 
	
	
	@Basic
    private java.sql.Date orderDate; 
    
    private double orderTotalPrice;
    
    

}
