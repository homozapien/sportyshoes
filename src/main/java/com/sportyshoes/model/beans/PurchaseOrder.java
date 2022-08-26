package com.sportyshoes.model.beans;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name="purchaseorder", uniqueConstraints = { @UniqueConstraint(name = "OrderIdAndOrderBy", columnNames = { "orderid", "orderedby"})})
public class PurchaseOrder
{
	@Id
	private String orderid;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order") 
	private Set<PurchaseItem> item;
	
	
	 @ManyToOne(fetch = FetchType.LAZY, optional=false) 
	 @JoinColumn(name = "orderedby", referencedColumnName = "emailid" ) 
	 private Customer customer; 
	
	
	@Basic
    private java.sql.Date orderDate; 
    
    private double orderTotalPrice;
    
    private String paymentStatus;

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public Set<PurchaseItem> getItem() {
		return item;
	}

	public void setItem(Set<PurchaseItem> item) {
		this.item = item;
	}


	public java.sql.Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(java.sql.Date orderDate) {
		this.orderDate = orderDate;
	}

	public double getOrderTotalPrice() {
		return orderTotalPrice;
	}

	public void setOrderTotalPrice(double orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "PurchaseOrder [orderid=" + orderid + ", item=" + item + ", customer=" + customer + ", orderDate="
				+ orderDate + ", orderTotalPrice=" + orderTotalPrice + ", paymentStatus=" + paymentStatus + "]";
	}
    
    
	

}
