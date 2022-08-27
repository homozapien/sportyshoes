<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="mybootstrap.jsp"%>
</head>
<body>

	<button class="btn btn-dark" onclick="history.back()">Go Back</button>
	<hr>

	<c:if test="${not empty msg }">

		<div class="container">

			<div class="alert alert-warning">${ msg }</div>

		</div>
	</c:if>
	
<c:set var="custUser" value="customer" />
	<c:set var="user" value="${sessionScope.loggedInUser}" />

	<c:choose>
		<c:when
			test="${not empty user && user.getTypeOfUser().equalsIgnoreCase(custUser)}">

			<div class="container">
				<div class="jumbotron">
					<h2>SportyShoes eMarket Square</h2>
				</div>
				<br />
				<c:choose>
					<c:when test="${not empty sessionScope.productList}">

						<div class="card">
							<div class="card-body">

								<div class="mb-3">
								<form action="checkout" id="checkoutForm" role="form" >
									<button type="submit" class="btn btn-primary" >Checkout</button>
									
									<a href="#" class="btn btn-info btn-lg"> <span
										class="glyphicon glyphicon-shopping-cart">${cartItemCount}
											Shopping Cart item(s) </span>
									</a>
                                   </form>
								</div>
								<div class="mb-3">
								
								<form action="addtoCart" method="post" id="cartForm" role="form" >
		
		                            <input type="hidden" id="cartItemId"      name="cartItemId">
		                            <input type="hidden" id="cartItemQty"     name="cartItemQty">
		
									<table class="table table-striped">
										<caption align="top">Item List for Sale</caption>
										<thead>
											<tr>
												<th scope="col">Logo</th>
												<th scope="col">Name</th>
												<th scope="col">Usage</th>
												<th scope="col">Brand</th>
												<th scope="col">Unit Price</th>
												<th scope="col">Quantity</th>
												<th scope="col">Action</th>
											</tr>
										</thead>

										<c:forEach items="${sessionScope.productList}" var="product">
											<tr>
												<td><img alt="img"
													src="data:image/jpeg;base64,${product.base64PrdLogo}"
													width="60" height="60" /></td>
												
												<td>${product.prdname}</td>
												<td>${product.getProductUsage().getUsageType()}</td>
												<td>${product.getProductBrand().getBrand_name()}</td>
												<td>${product.prdprice}</td>
												<td><input type="number" class="form-control input-sm" name="quantity" id="${product.prdid}qtt" value="1" min="1" max="10"
												size="2" required /></td>
												
													
												<td>
													<button type="submit" class="btn btn-primary"
													onclick="document.getElementById('cartItemId').value = '${product.prdid}';
													         document.getElementById('cartItemQty').value = document.getElementById('${product.prdid}qtt').value;
										                     document.getElementById('cartForm').submit();">
													Buy</button>
													</td>
											</tr>

										</c:forEach>

									</table>
									</form> 
									
								</div>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<br>
						<div class="alert alert-info">Product List is empty</div>
					</c:otherwise>
				</c:choose>

			</div>
		</c:when>
		<c:otherwise>
			<%
			response.sendRedirect("index.jsp");
			%>
		</c:otherwise>
	</c:choose>

</body>
</html>