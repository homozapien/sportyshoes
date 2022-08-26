package com.sportyshoes.controllers;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sportyshoes.model.beans.Product;
import com.sportyshoes.model.beans.PurchaseItem;
import com.sportyshoes.model.services.ProductService;



@Controller
@RequestMapping("/sportyshoes")
public class ShoppingController {
	
    private int cartItemCount = 0;
    private Map<String, Integer> cartItemMap = new HashMap<>();
    
   

	@RequestMapping(value = "addtoCart", method = RequestMethod.POST)
	public String addSelectedItemToCart(HttpServletRequest req, Model model) 
	{
		String itemKey      =   req.getParameter("cartItemId");
		int quantity        =   Integer.valueOf (req.getParameter("cartItemQty"));
		
		if(cartItemMap.containsKey(itemKey))
		{
			int itemCount = cartItemMap.get(itemKey);
			cartItemMap.put(itemKey, itemCount + quantity);
		}
		else
		{
			cartItemMap.put(itemKey, quantity);	
		}
		
        cartItemCount += Integer.valueOf(quantity);
        
        String returnView = "closedmarketsquare";
        
        List<Product> productList = (List<Product>)req.getSession().getAttribute("productList");
        
		if(!productList.isEmpty())
		{
			model.addAttribute("cartItemCount", cartItemCount);
			
			returnView = "marketsquare";
		}
		return returnView;
	}
	
	private double totalPrice;
	
	@RequestMapping(value = "checkout", method = RequestMethod.GET)
	public String checkoutItemToCart(HttpServletRequest req, Model model) 
	{
		String returnView = "";
		List<PurchaseItem> listOfItems = new ArrayList<>();
		
		 List<Product> productList = (List<Product>)req.getSession().getAttribute("productList");
		
		Map<String, Product> productMap = productList.stream().collect(Collectors.toMap(Product::getPrdid, Function.identity()));
	      
		totalPrice = 0.0;
		req.getSession().removeAttribute("cartItemList");
		req.getSession().removeAttribute("totalPrice");
		
		if(!this.cartItemMap.isEmpty())
		{
		   this.cartItemMap.forEach((key, value) -> {
			   
			       Product product = productMap.get(key);
			       PurchaseItem purchaseItem = new PurchaseItem();
			       purchaseItem.setPrdid(product.getPrdid());
			       purchaseItem.setPrdname(product.getPrdname());
			       purchaseItem.setPrdlogo(product.getPrdlogo());
			       purchaseItem.setPrdprice(product.getPrdprice());
			       purchaseItem.setBase64PrdLogo(product.getBase64PrdLogo());
			       purchaseItem.setQuantity(value);
			       purchaseItem.setSubTotalPrice(value * product.getPrdprice());
			       
			       totalPrice += purchaseItem.getSubTotalPrice();
			       listOfItems.add(purchaseItem);
		 
		       }
		   );
		   
		   req.getSession().setAttribute("cartItemList",   listOfItems);
		   req.getSession().setAttribute("totalPrice", totalPrice);
		   returnView = "checkout";
		 }
		else
		{
			model.addAttribute("msg",   "Empty Shopping Cart; add items to cart before checkout");
			returnView = "marketsquare";
		}
		
		return returnView;
		
		
	}
	
	
	@RequestMapping(value = "makepayment", method = RequestMethod.GET)
	public String makePayment(HttpServletRequest req, Model model) 
	{
		
		String buyerId = (String)req.getSession().getAttribute("userId");
		
		
		
		return "";
	}

	

}
