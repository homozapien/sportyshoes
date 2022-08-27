package com.sportyshoes.controllers;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sportyshoes.model.beans.Customer;
import com.sportyshoes.model.beans.Product;
import com.sportyshoes.model.beans.PurchaseItem;
import com.sportyshoes.model.beans.PurchaseOrder;
import com.sportyshoes.model.beans.UserMgmt;
import com.sportyshoes.model.services.CustomerService;
import com.sportyshoes.model.services.PurchaseOrderService;
import com.sportyshoes.utils.Helper;
import com.sportyshoes.utils.PaymentGatewayService;



@Controller
@RequestMapping("/sportyshoes")
public class ShoppingController {
	
    private int cartItemCount = 0;
    private Map<String, Integer> cartItemMap = new HashMap<>();
    
    @Autowired
    CustomerService customerService;
    
    @Autowired
    PurchaseOrderService poService;
   

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
		cartItemCount = 0;
		req.getSession().removeAttribute("cartItemList");
		req.getSession().removeAttribute("totalPrice");
		
		if(!this.cartItemMap.isEmpty())
		{
		   this.cartItemMap.forEach((key, value) -> {
			   
			       Product product = productMap.get(key);
			       PurchaseItem purchaseItem = new PurchaseItem();
			       purchaseItem.setProduct(product);
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
		
		UserMgmt user = (UserMgmt)req.getSession().getAttribute("loggedInUser");
		String  emailId = user.getEmailid();
		
		List<PurchaseItem> listOfItems = (List<PurchaseItem>)req.getSession().getAttribute("cartItemList");
		
		Set<PurchaseItem> setOfItems = new HashSet<>(listOfItems);
		
		PurchaseOrder po = new PurchaseOrder();
		
		po.setOrderid(Helper.generateRandomString());
		po.setItem(setOfItems);
		po.setOrderTotalPrice(totalPrice);
		
		Customer customer = customerService.findCustomerProfileByRef(emailId);
		
	    po.setCustomer(customer);	    
	    po.setOrderDate(java.sql.Date.valueOf(LocalDate.now()));
	    
	    String paymentStatus = PaymentGatewayService.performDummyPayment(customer.getCreditCard(), totalPrice, customer.getFirstname(), customer.getLastname(), "123", "01/99");
	    
	    po.setPaymentStatus(paymentStatus);
	    
	    po.getItem().forEach(item -> {
	                 item.setOrderItemId(po.getOrderid() + item.getProduct().getPrdid());
	    	         item.setOrder(po); 
	                 }
	    		);
	    
	    String msg = "";
	    String view = "";
	   if(poService.createPurchaseOrder(po))
	   {
		   msg = "New Purchase Order  created successfully";
		    totalPrice = 0.0;
			req.getSession().removeAttribute("cartItemList");
			req.getSession().removeAttribute("totalPrice");
			this.cartItemMap.clear();
			view = "success";
	   }
	   else
	   {
		   msg = "Error during purchase order creation; record didn't insert"; 
		   view  = "failure";
	   }
	   
	   model.addAttribute("msg", msg);
	   return view;
		
	}

	

}
