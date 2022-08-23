package com.sportyshoes.model.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="product_usage")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder=true)
@DynamicInsert
public class ProductUsage 
{
	@Id
	@Column(columnDefinition = "varchar(255) default 'Athletics'")
	private String usage_id;
	
	@Column(columnDefinition = "varchar(255) default 'Athletics'")
	private String usageType; //e.g. Athletics, Soccer, BasketBall, Hockey

	@OneToOne(mappedBy = "productUsage")
	private Product product;

}