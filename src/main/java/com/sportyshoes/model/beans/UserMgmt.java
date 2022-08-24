package com.sportyshoes.model.beans;





import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "usermgmt")
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@Builder(toBuilder=true)
@Inheritance(strategy = InheritanceType.JOINED)
public class UserMgmt 
{
	
	@Id
	@ColumnDefault("'a@a.com'")
	private String emailid;  
	@ColumnDefault("'1'")
	private String password; 
	@ColumnDefault("'Admin'")
	private String typeOfUser;	
	
	
	
	
}
