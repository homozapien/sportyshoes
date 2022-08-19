package com.sportyshoes.model.beans;




import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;


import lombok.Data;


@Entity
@Table(name = "usermgmt")
@Data

public class UserMgmt implements Serializable
{
	private static final long serialVersionUID = 3756680645803624405L;
	@Id
	@ColumnDefault("'admin@sportyshoes.com'")
	private String emailId;  
	@ColumnDefault("'1234'")
	private String password; 
	@ColumnDefault("'Admin'")
	private String typeOfUser;	
	
	
}
