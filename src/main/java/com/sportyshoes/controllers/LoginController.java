package com.sportyshoes.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.sportyshoes.model.beans.UserMgmt;
import com.sportyshoes.model.services.LoginService;


@Controller
@RequestMapping("/sportyshoes")
public class LoginController 
{
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value = "/")
	public String backToLogin() 
	{   
			return "login";					
	}
	
	@RequestMapping(value = "checkUser",method = RequestMethod.POST)
	public String checkUserDetails(HttpServletRequest req, Model model) 
	{   
		String email      = req.getParameter("email");
		String password   = req.getParameter("password");
		String typeOfUser = req.getParameter("typeOfUser");
		
		String returnView = "login";
		
		UserMgmt user = new UserMgmt();
		user.setEmailId(email);
		user.setPassword(password);
		user.setTypeOfUser(typeOfUser);
				
		if(loginService.checkLoggedOnUser(user)) 
		{
			
			returnView = typeOfUser.equalsIgnoreCase("Admin") ? "admin" : "customer";
		
			req.getSession().setAttribute("loggedInUser", returnView);
		}
		else
		{
			model.addAttribute("msg", "Logon Failure,Try Again");
		}
		return returnView;
	}
	
	
}
