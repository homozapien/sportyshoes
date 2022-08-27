package com.sportyshoes.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sportyshoes.model.beans.Customer;
import com.sportyshoes.model.beans.Product;
import com.sportyshoes.model.beans.UserMgmt;
import com.sportyshoes.model.services.CustomerService;
import com.sportyshoes.model.services.LoginService;
import com.sportyshoes.model.services.ProductService;

@Controller
@RequestMapping("/sportyshoes")
public class LoginController {
	
	@Autowired
	private LoginService loginService;

	@Autowired
	CustomerService customerService;
	
	@Autowired
	ProductService productService;

	@RequestMapping(value = "/")
	public String backToLogin() {
		return "login";
	}

	@RequestMapping(value = "signUp")
	public String displaySignUp() {
		return "signUp";
	}
	
	@RequestMapping(value = "changePassword")
	public String changePassword() {
		return "passwordmgmt";
	}

	@RequestMapping(value = "checkUser", method = RequestMethod.POST)
	public String checkUserDetails(HttpServletRequest req, Model model) {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String typeOfUser = req.getParameter("typeOfUser");

		String returnView = "login";

		UserMgmt user = new UserMgmt();
		user.setEmailid(email);
		user.setPassword(password);
		user.setTypeOfUser(typeOfUser);
		
		if (loginService.checkLoggedOnUser(user)) {

			if (typeOfUser.equalsIgnoreCase("Admin")) {
				returnView = "admin";
			} 
			else
			{
				List<Product> productList = productService.getAllProducts(); 
				if(!productList.isEmpty())
				{
					req.getSession().setAttribute("productList", productList);
					returnView = "marketsquare";
				}
				else
				{
					returnView = "closedmarketsquare";
				}
			}

			req.getSession().setAttribute("loggedInUser", user);
		} 
		else {
			model.addAttribute("msg", "Logon Failure,Try Again");
		}
		return returnView;
	}

	@PostMapping(value = "createProfile")
	public String createCustomer(HttpServletRequest req, Model model) {
		String email = req.getParameter("emailid");
		String password = req.getParameter("password");
		String firstname = req.getParameter("firstname");
		String lastname = req.getParameter("lastname");
		String creditCard =  req.getParameter("creditCard");
		String typeOfUser = "customer";

		String returnView = "login";

		Customer customer = new Customer(email, password, typeOfUser, firstname, lastname, creditCard);

		String result = customerService.createProfile(customer);

		model.addAttribute("msg", result);

		return returnView;
	}
	
	
	@PostMapping(value = "passwordmgmt")
	public String changePassword(HttpServletRequest req, Model model) 
	{
		String email          = req.getParameter("emailid");
		String currentPassword = req.getParameter("currentpassword");
		String newpassword    = req.getParameter("newpassword");
		

		UserMgmt userProfile = (UserMgmt)req.getSession().getAttribute("loggedInUser");
		System.out.println(userProfile);
		
		if(userProfile.getPassword().equals(currentPassword))
		{
			userProfile.setPassword(newpassword);
			
			String result = loginService.changeLoggedOnUserPassword(userProfile);
			
			model.addAttribute("msg", result);
			return "login";
		}
		else
		{
			model.addAttribute("msg", "Invalid current password provided, try again ");
			return "passwordmgmt";
					
		}
		
		
	}

}
