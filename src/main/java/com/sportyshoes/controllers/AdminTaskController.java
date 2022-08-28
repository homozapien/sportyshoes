package com.sportyshoes.controllers;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

import com.sportyshoes.model.beans.Customer;
import com.sportyshoes.model.beans.Product;
import com.sportyshoes.model.beans.ProductBrand;
import com.sportyshoes.model.beans.ProductUsage;
import com.sportyshoes.model.beans.PurchaseItem;
import com.sportyshoes.model.beans.PurchaseOrder;
import com.sportyshoes.model.services.CustomerService;
import com.sportyshoes.model.services.ProductService;
import com.sportyshoes.model.services.PurchaseOrderService;
import com.sportyshoes.utils.OrderHistoryDto;

@Controller
@RequestMapping("/sportyshoes")
public class AdminTaskController {
	@Autowired
	ProductService productService;

	@Autowired
	CustomerService customerService;

	@Autowired
	PurchaseOrderService purchaseOrderService;

	private List<ProductBrand> pdrBrandList;
	private List<ProductUsage> prdUsageList;
	private List<Product> productList;
	private List<Customer> customerList;
	private List<OrderHistoryDto> orderHistList;

	@RequestMapping(value = "createproduct")
	public String showProductCreate(Model model) {
		this.refreshDashboardData();
		model.addAttribute("pdrBrandList", this.pdrBrandList);
		model.addAttribute("prdUsageList", this.prdUsageList);

		return "product";
	}

	@RequestMapping(value = "createbrand")
	public String displayBrandView() {
		return "brand";
	}

	@RequestMapping(value = "createusagetype")
	public String displayUsageTypeView() {
		return "usage";
	}

	@RequestMapping(value = "/createProduct", method = RequestMethod.POST)
	public String createProduct(ModelMap modelMap, HttpServletRequest request) // @RequestParam("prdlogo") MultipartFile
																				// logoImg,
	{
		String prdid = request.getParameter("prdid");
		String prdname = request.getParameter("prdname");
		double prdprice = Double.valueOf(request.getParameter("prdprice"));
		MultipartFile multipart = ((MultipartHttpServletRequest) request).getFile("prdlogo");
		byte[] bytes = null;
		try {
			bytes = multipart.getBytes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String usageid = request.getParameter("prdusage");
		String brandid = request.getParameter("prdbrand");

		Product product = new Product();

		product.setPrdid(prdid);
		product.setPrdname(prdname);
		product.setPrdprice(prdprice);

		ProductBrand productBrand = this.pdrBrandList.stream()
				.filter(element -> brandid.equalsIgnoreCase(element.getBrand_id())).findAny().orElse(null);

		product.setProductBrand(productBrand);

		ProductUsage productUsage = this.prdUsageList.stream()
				.filter(element -> usageid.equalsIgnoreCase(element.getUsage_id())).findAny().orElse(null);

		product.setProductUsage(productUsage);

		product.setPrdlogo(bytes);

		String result = productService.createProduct(product);
		modelMap.addAttribute("msg", result);
		modelMap.addAttribute("pdrBrandList", this.pdrBrandList);
		modelMap.addAttribute("prdUsageList", this.prdUsageList);
		return "product";
	}

	@PostMapping(value = "createbrand")
	public String createBrand(ModelMap modelMap, HttpServletRequest request) {
		String id = request.getParameter("brandid");
		String name = request.getParameter("brandname");

		ProductBrand brand = new ProductBrand();
		brand.setBrand_id(id);
		brand.setBrand_name(name);

		String result = productService.createBrand(brand);
		modelMap.addAttribute("msg", result);

		return "brand";
	}

	@PostMapping(value = "createusagetype")
	public String createUsageType(ModelMap modelMap, HttpServletRequest request) {
		String id = request.getParameter("usageTypeId");
		String name = request.getParameter("usagetypename");

		ProductUsage usage = new ProductUsage();
		usage.setUsage_id(id);
		usage.setUsageType(name);

		String result = productService.createUsageTpe(usage);
		modelMap.addAttribute("msg", result);
		return "usage";
	}

	@RequestMapping(value = "/refreshDashboard", method = RequestMethod.GET)
	public String getAllDashBoardData(ModelMap modelMap) {
		this.refreshDashboardData();
		modelMap.addAttribute("productList", this.productList);
		modelMap.addAttribute("pdrBrandList", this.pdrBrandList);
		modelMap.addAttribute("prdUsageList", this.prdUsageList);
		return "dashboard";

	}

	@RequestMapping(value = "otheradmintasks", method = RequestMethod.GET)
	public String displayAddAdminView(ModelMap modelMap) {
		this.refreshOtherAdminData();

		modelMap.addAttribute("productList", this.productList);
		modelMap.addAttribute("customerList", this.customerList);
		modelMap.addAttribute("purchaseList", this.orderHistList);

		return "otheradmintasks";
	}

	@RequestMapping(value = "productfilter", method = RequestMethod.GET)
	public String doPerformProductFilter(HttpServletRequest request, ModelMap modelMap) {
		String filter = request.getParameter("productFilter");

		List<Product> filtredProductList = this.productList.stream()
				.filter(product -> product.getProductBrand().getBrand_name().equalsIgnoreCase(filter)
						|| product.getProductUsage().getUsageType().equalsIgnoreCase(filter))
				.collect(Collectors.toList());

		modelMap.addAttribute("productList", filtredProductList);
		modelMap.addAttribute("customerList", this.customerList);

		return "otheradmintasks";
	}

	@RequestMapping(value = "customerfilter", method = RequestMethod.GET)
	public String doPerformCustomerFilter(HttpServletRequest request, ModelMap modelMap) {
		String filter = request.getParameter("customerFilter");
		Comparator<Customer> comparator = Comparator.comparing(Customer::getFirstname)
				.thenComparing(Customer::getLastname);

		List<Customer> filtredCustomerList = this.customerList.stream()
				.filter(customer -> customer.getEmailid().equalsIgnoreCase(filter)
						|| customer.getFirstname().equalsIgnoreCase(filter)
						|| customer.getLastname().equalsIgnoreCase(filter))
				.sorted(comparator).collect(Collectors.toList());

		modelMap.addAttribute("productList", this.productList);
		modelMap.addAttribute("customerList", filtredCustomerList);

		return "otheradmintasks";
	}

	@RequestMapping(value = "purchasefilter", method = RequestMethod.GET)
	public String doPerformPurchaseFilter(HttpServletRequest request, ModelMap modelMap) {
		String categoryFilter = request.getParameter("productCat");
		String orderDateFilter = request.getParameter("orderDate");

		// Comparator<OrderHistoryDto> comparator =
		// Comparator.comparing(OrderHistoryDto::getUsageType)
		// .thenComparing(OrderHistoryDto::getOrderDate);

		if (!categoryFilter.isBlank() && !orderDateFilter.isBlank()) {
			Date orderDate = java.sql.Date.valueOf(orderDateFilter);

			List<OrderHistoryDto> filtredPurchaseList = this.orderHistList.stream()
					.filter(order -> order.getUsageType().equalsIgnoreCase(categoryFilter)
							&& (order.getOrderDate().compareTo(orderDate) == 0))
					// .sorted(comparator)
					.collect(Collectors.toList());

			modelMap.addAttribute("purchaseList", filtredPurchaseList);

		} else if (!categoryFilter.isBlank() && orderDateFilter.isBlank()) {

			List<OrderHistoryDto> filtredPurchaseList = this.orderHistList.stream()
					.filter(order -> order.getUsageType().equalsIgnoreCase(categoryFilter))
					// .sorted(comparator)
					.collect(Collectors.toList());

			modelMap.addAttribute("purchaseList", filtredPurchaseList);
		} else if (categoryFilter.isBlank() && !orderDateFilter.isBlank()) {
			Date orderDate = java.sql.Date.valueOf(orderDateFilter);

			List<OrderHistoryDto> filtredPurchaseList = this.orderHistList.stream()
					.filter(order -> (order.getOrderDate().compareTo(orderDate) == 0))
					// .sorted(comparator)
					.collect(Collectors.toList());

			modelMap.addAttribute("purchaseList", filtredPurchaseList);

		}

		modelMap.addAttribute("productList", this.productList);
		modelMap.addAttribute("customerList", this.customerList);
		
		return "otheradmintasks";
	}

	private void refreshDashboardData() {
		this.productList = productService.getAllProducts();
		this.pdrBrandList = productService.getAllProductBrand();
		this.prdUsageList = productService.getAllProductUsageTypes();
	}

	private void refreshOtherAdminData() {
		this.productList = productService.getAllProducts();
		this.customerList = customerService.getAllCustomerProfiles();
		List<PurchaseOrder> orderList = purchaseOrderService.getAllPurchaseOrders();
		this.orderHistList = new ArrayList<>();

		orderList.forEach(order -> {

			order.getItem().forEach(item -> {

				OrderHistoryDto orderDto = new OrderHistoryDto();

				orderDto.setPrdid(item.getProduct().getPrdid());
				orderDto.setPrdname(item.getProduct().getPrdname());
				orderDto.setBrand(item.getProduct().getProductBrand().getBrand_name());
				orderDto.setUsageType(item.getProduct().getProductUsage().getUsageType());
				orderDto.setOrderId(order.getOrderid());
				orderDto.setOrderBy(order.getCustomer().getEmailid());
				orderDto.setQuantity(item.getQuantity());
				orderDto.setSubTotalPrice(item.getSubTotalPrice());
				orderDto.setOrderDate(order.getOrderDate());
				this.orderHistList.add(orderDto);

			});

		});

	}

}
