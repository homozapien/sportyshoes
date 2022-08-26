package com.sportyshoes.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
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
public class AdminTaskController 
{
	@Autowired
	ProductService productService;
	
	private List<ProductBrand> pdrBrandList;
	private List<ProductUsage> prdUsageList;
	private List<Product> productList;
	
	@RequestMapping(value = "createproduct")
	public String showProductCreate(Model model) 
	{
		 this.refreshDashboardData();		
		 model.addAttribute("pdrBrandList", this.pdrBrandList);
		 model.addAttribute("prdUsageList", this.prdUsageList);
				
		return "product";
	}
	
	@RequestMapping(value = "createbrand")
	public String displayBrandView() 
	{			
		return "brand";
	}
	
	@RequestMapping(value = "createusagetype")
	public String displayUsageTypeView() 
	{			
		return "usage";
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
	
	
	@PostMapping(value = "createbrand")
	public String createBrand(ModelMap modelMap,  HttpServletRequest request) 
	{			
		    String id   = request.getParameter("brandid");
		    String name = request.getParameter("brandname");
		   
		    ProductBrand brand = new  ProductBrand();
		    brand.setBrand_id(id);
		    brand.setBrand_name(name);
		    
		    String result = productService.createBrand(brand);  
		    modelMap.addAttribute("msg", result);
		   
		   return "brand";
	}
	
	@PostMapping(value = "createusagetype")
	public String createUsageType(ModelMap modelMap,  HttpServletRequest request) 
	{	
		String id   = request.getParameter("usageTypeId");
		String name = request.getParameter("usagetypename");
		
		ProductUsage usage = new  ProductUsage();
		usage.setUsage_id(id);
		usage.setUsageType(name);
		
		String result = productService.createUsageTpe(usage);  
		modelMap.addAttribute("msg", result);
		return "usage";
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
	
	private void refreshDashboardData() 
	{
		this.productList   = productService.getAllProducts();
		this.pdrBrandList  = productService.getAllProductBrand();
		this.prdUsageList  = productService.getAllProductUsageTypes();
		
	}
	

}
