package com.sportyshoes.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/sportyshoes")
public class LoginController 
{
  
	
	@RequestMapping(value = "checkUser",method = RequestMethod.POST)
	public String checkUserDetails(HttpServletRequest req) {   
		String email    = req.getParameter("email");
		String password = req.getParameter("password");
		if(email.equals("a@a.com") && password.equals("1234")) {
			return "admin";					// admin
		}else {
			return "failure";					// failure.jsp 
		}
	}
	
	
}
