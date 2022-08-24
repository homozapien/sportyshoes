package com.sportyshoes.model.beans;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "customer")
public class Customer extends UserMgmt
{
	
	private String firstname;
	private String lastname;
	
	@OneToOne(mappedBy = "customer", optional=true)
	private PurchaseOrder order; 

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String emailid, String password, String typeOfUser) {
		super(emailid, password, typeOfUser);
		// TODO Auto-generated constructor stub
	}

	public Customer(String emailid, String password, String typeOfUser, String firstname, String lastname) 
	{
		super(emailid, password, typeOfUser);
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public PurchaseOrder getOrder() {
		return order;
	}

	public void setOrder(PurchaseOrder order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "Customer [firstname=" + firstname + ", lastname=" + lastname + ", order=" + order + "]";
	}
	
	
	
    
}
