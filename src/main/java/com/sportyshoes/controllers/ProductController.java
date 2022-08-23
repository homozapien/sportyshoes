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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sportyshoes.model.beans.Product;
import com.sportyshoes.model.beans.ProductBrand;
import com.sportyshoes.model.beans.ProductUsage;
import com.sportyshoes.model.services.ProductService;

@Controller
@RequestMapping("/sportyshoes")
public class ProductController 
{
	@Autowired
	ProductService productService;
	
	private List<ProductBrand> pdrBrandList;
	private List<ProductUsage> prdUsageList;
	private List<Product> productList;
	
	@RequestMapping(value = "showProductView")
	public String showProductCreate(Model model) 
	{
		 this.refreshDashboardData();		
		 model.addAttribute("pdrBrandList", this.pdrBrandList);
		 model.addAttribute("prdUsageList", this.prdUsageList);
				
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
	
	   String usageid = request.getParameter("prdusage");
	   String brandid = request.getParameter("prdbrand");
	   
	   Product product = new Product();
	   
	   product.setPrdid(prdid);
	   product.setPrdname(prdname);
	   product.setPrdprice(prdprice);
	   
	   ProductBrand productBrand = this.pdrBrandList
			                           .stream()
			                           .filter(element -> brandid.equalsIgnoreCase(element.getBrand_id()))
			                           .findAny()
			                           .orElse(null);
	   
	  product.setProductBrand(productBrand);
	  
	  ProductUsage productUsage = this.prdUsageList
								  .stream()
					              .filter(element -> usageid.equalsIgnoreCase(element.getUsage_id()))
					              .findAny()
					              .orElse(null);
	  
	   product.setProductUsage(productUsage);
	   
	   product.setPrdlogo(bytes);
	   
	   String result = productService.createProduct(product);  
	   modelMap.addAttribute("msg", result);
	   modelMap.addAttribute("pdrBrandList", this.pdrBrandList);
	   modelMap.addAttribute("prdUsageList", this.prdUsageList);
	   return "product";
	}
	
	@RequestMapping(value = "/refreshDashboard",method = RequestMethod.GET)
	public String getAllDashBoardData(ModelMap modelMap)
	{
		this.refreshDashboardData();
		modelMap.addAttribute("productList",  this.productList);
		modelMap.addAttribute("pdrBrandList", this.pdrBrandList);
		modelMap.addAttribute("prdUsageList", this.prdUsageList);
		return "dashboard";
		
	}
	
	private void refreshDashboardData() //helper method
	{
		this.productList   = productService.getAllProducts();
		this.pdrBrandList  = productService.getAllProductBrand();
		this.prdUsageList  = productService.getAllProductUsageTypes();
		
	}
	

}
