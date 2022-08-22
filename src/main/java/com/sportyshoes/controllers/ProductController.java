package com.sportyshoes.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sportyshoes.model.beans.Product;
import com.sportyshoes.model.services.ProductService;

@Controller
@RequestMapping("/sportyshoes")
public class ProductController 
{
	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "showProductView")
	public String showProductCreate() 
	{
		return "product";
	}
	
	@RequestMapping(value = "/createProduct",method = RequestMethod.POST)
	public String createProduct(ModelMap modelMap,  HttpServletRequest request) //@RequestParam("prdlogo") MultipartFile logoImg,
	{
	   String prdid     = request.getParameter("prdid");
	   String prdname   = request.getParameter("prdname");
	   double prdprice  = Double.valueOf(request.getParameter("prdprice"));
	   MultipartFile multipart       = ((MultipartHttpServletRequest)request).getFile("prdlogo");
	   byte[] bytes = null;
	   try 
	   {
		bytes = multipart.getBytes();
	   } 
	   catch (IOException e) 
	   {
		// TODO Auto-generated catch block
		e.printStackTrace();
	   }
	
	   String prdusage = request.getParameter("prdusage");
	   String prdbrand = request.getParameter("prdbrand");
	   
	   Product product = new Product();
	   product.setPrdid(prdid);
	   product.setPrdname(prdname);
	   product.setPrdprice(prdprice);
	   product.setPrdbrand(prdbrand);
	   product.setPrdusage(prdusage);
	   product.setPrdlogo(bytes);
	   System.out.println(product);
	   
	   String result = productService.createProduct(product);  
	   modelMap.addAttribute("msg", result);
	   return "product";
	}
	
	@RequestMapping(value = "/getAllProducts",method = RequestMethod.GET)
	public String getAllProductItems(ModelMap modelMap)
	{
		List<Product> productList = productService.getAllProducts();
		modelMap.addAttribute("productList", productList);
		return "dashboard";
		
	}

}
