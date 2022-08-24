package com.sportyshoes.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sportyshoes.model.beans.Customer;
import com.sportyshoes.model.beans.UserMgmt;
import com.sportyshoes.model.services.CustomerService;
import com.sportyshoes.model.services.LoginService;


@Controller
@RequestMapping("/sportyshoes")
public class LoginController 
{
	@Autowired
	private LoginService loginService;
	
	@Autowired
	CustomerService customerService;
	
	@RequestMapping(value = "/")
	public String backToLogin() 
	{   
			return "login";					
	}
	
	@RequestMapping(value = "signUp")
	public String displaySignUp() 
	{   
			return "signUp";					
	}
	
	@RequestMapping(value = "checkUser",method = RequestMethod.POST)
	public String checkUserDetails(HttpServletRequest req, Model model) 
	{   
		String email      = req.getParameter("email");
		String password   = req.getParameter("password");
		String typeOfUser = req.getParameter("typeOfUser");
		
		String returnView = "login";
		
		UserMgmt user = new UserMgmt();
		user.setEmailid(email);
		user.setPassword(password);
		user.setTypeOfUser(typeOfUser);
				
		if(loginService.checkLoggedOnUser(user)) 
		{
			
					if(typeOfUser.equalsIgnoreCase("Admin"))
					{
						returnView = "admin";
						req.getSession().setAttribute("loggedInUser", returnView);
					}
					else
					{
						returnView = "marketsquare";
						req.getSession().setAttribute("loggedInUser", "customer");
						
					}
					
					req.getSession().setAttribute("userId", email);
		}
		else
		{
			model.addAttribute("msg", "Logon Failure,Try Again");
		}
		return returnView;
	}
	
	
	@PostMapping(value = "createProfile")
	public String createCustomer(HttpServletRequest req, Model model) 
	{   
		String email      = req.getParameter("emailid");
		String password   = req.getParameter("password");
		String firstname  = req.getParameter("firstname");
		String lastname   = req.getParameter("lastname");		
		String typeOfUser = "customer";
		
		String returnView = "login";
		
		Customer customer = new Customer(email, password, typeOfUser, firstname, lastname);
		
		String result = customerService.createProfile(customer);
		
		System.out.println(result);
		
		model.addAttribute("msg", result);
		
		return returnView;
	}
	
}
