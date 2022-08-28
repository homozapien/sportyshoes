package com.sportyshoes.model.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="purchaseitem")
public class PurchaseItem implements Serializable
{  
	private static final long serialVersionUID = 2561676627745387283L;

	@Id
	private String orderItemId;
	
	@OneToOne(fetch = FetchType.EAGER)  
    @JoinColumn(name = "prdid", referencedColumnName = "prdid") 
	private Product product;
	
	private int    quantity;
	private double subTotalPrice;	
	
	
	@ManyToOne
    @JoinColumn(name = "orderid", referencedColumnName = "orderid")
	private PurchaseOrder order;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

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

	public String getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
	}

	@Override
	public String toString() {
		return "PurchaseItem [orderItemId=" + orderItemId + ", product=" + product + ", quantity=" + quantity
				+ ", subTotalPrice=" + subTotalPrice + "]";
	}

	
	
	
	
	
	

	
}
