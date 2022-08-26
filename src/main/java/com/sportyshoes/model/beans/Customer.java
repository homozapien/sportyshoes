package com.sportyshoes.model.beans;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "customer")

public class Customer extends UserMgmt
{
	
	private String firstname;
	private String lastname;
	private String creditCard;
	
  /* @OneToOne(mappedBy = "customer")
	private PurchaseOrder order; */
   
	@OneToMany(mappedBy = "customer") 
 	private Set<PurchaseOrder> setOfOrders; 
   
   

	public Customer() {
		super();
	}

	public Customer(String emailid, String password, String typeOfUser) {
		super(emailid, password, typeOfUser);
	}

	public Customer(String emailid, String password, String typeOfUser, String firstname, String lastname, String creditCard) 
	{
		super(emailid, password, typeOfUser);
		this.firstname = firstname;
		this.lastname = lastname;
		this.creditCard = creditCard;
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
	
	

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	

	public Set<PurchaseOrder> getSetOfOrders() {
		return setOfOrders;
	}

	public void setSetOfOrders(Set<PurchaseOrder> setOfOrders) {
		this.setOfOrders = setOfOrders;
	}

	@Override
	public String toString() {
		return "Customer [firstname=" + firstname + ", lastname=" + lastname + "]";
	}
	
	
	
    
}
