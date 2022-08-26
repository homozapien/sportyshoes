package com.sportyshoes.model.beans;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="purchaseitem")
public class PurchaseItem extends Product
{  
	private int    quantity;
	private double subTotalPrice;	
	
	@ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name = "orderid", referencedColumnName = "orderid")
	private PurchaseOrder order; 

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getSubTotalPrice() {
		return subTotalPrice;
	}

	public void setSubTotalPrice(double subTotalPrice) {
		this.subTotalPrice = subTotalPrice;
	}

	public PurchaseOrder getOrder() {
		return order;
	}

	public void setOrder(PurchaseOrder order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "PurchaseItem [prdid = " + this.getPrdid() + " quantity=" + quantity + ", subTotalPrice=" + subTotalPrice + ", order=" + order + "]";
	} 
	
	
	
}
