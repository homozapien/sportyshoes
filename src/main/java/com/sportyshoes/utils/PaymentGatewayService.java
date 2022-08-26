package com.sportyshoes.utils;

import org.springframework.stereotype.Service;

@Service
public class PaymentGatewayService 
{
    public static String performDummyPayment(String creditCard, double amount, String firstname, String lastname, String cvv, String expirydate)
    {
    	//Implement call to Payment Service Provider
    	return "SUCCESS";
    }
}
