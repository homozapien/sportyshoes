package com.sportyshoes.utils;


import java.sql.Date;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderHistoryDto
{  
	private String prdid;
	private String orderBy;
	private String prdname;
	private String brand;
	private String usageType;
	private String orderId;
	private int    quantity;
	private double subTotalPrice;
	private Date orderDate;
		
}
