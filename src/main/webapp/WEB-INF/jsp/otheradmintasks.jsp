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
	<c:set var="adminUser" value="admin" />
	<c:set var="user" value="${sessionScope.loggedInUser}" />

	<c:choose>
		<c:when
			test="${not empty user && user.getTypeOfUser().equalsIgnoreCase(adminUser)}">

			<div class="container">
				<div class="jumbotron">
					<h2>Products List</h2>
				</div>

				<div class="card">
					<div class="card-body">

							<form action="productfilter" method="get" role="form">
								
									<input type="text" name="productFilter" id="productFilter" size="50" required placeholder="Enter the product usage or brand to filter" />
								
								<button type="submit" class="btn btn-info">
									<span class="glyphicon glyphicon-search"></span> Search
								</button>
								<br>
							</form>

						<hr/>

						<c:choose>
							<c:when test="${not empty productList}">

								<table class="table table-striped">
									<thead>
										<tr>
											<th scope="col">Logo</th>
											<th scope="col">Id</th>
											<th scope="col">Name</th>
											<th scope="col">Usage</th>
											<th scope="col">Brand</th>
											<th scope="col">Unit Price</th>

										</tr>
									</thead>

									<c:forEach items="${productList}" var="product">
										<tr>
											<td><img alt="img"
												src="data:image/jpeg;base64,${product.base64PrdLogo}"
												width="80" height="80" /></td>
											<td>${product.prdid}</td>
											<td>${product.prdname}</td>
											<td>${product.getProductUsage().getUsageType()}</td>
											<td>${product.getProductBrand().getBrand_name()}</td>
											<td>${product.prdprice}</td>

										</tr>

									</c:forEach>

								</table>

							</c:when>
							<c:otherwise>
								<br>
								<div class="alert alert-info">Product List is empty</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
			<hr />

			<div class="container">
				<div class="jumbotron">
					<h2>Signed-Up Customers List</h2>
				</div>

				<div class="card">
					<div class="card-body">
							<form action="customerfilter" method="get" role="form">
								
									<input type="text" name="customerFilter" id="customerFilter" size="50" required placeholder="Enter the customer id or firstname or lastname to filter" />
								
								 <button type="submit" class="btn btn-info">
									<span class="glyphicon glyphicon-search"></span> Search
								</button>
								<br>
							</form>
						<hr />

						<c:choose>
							<c:when test="${not empty customerList}">

								<table class="table table-striped">
									<thead>
										<tr>
											<th scope="col">Customer Id</th>
											<th scope="col">First Name</th>
											<th scope="col">Last Name</th>
											<th scope="col">Payment Card#</th>
										</tr>
									</thead>

									<c:forEach items="${customerList}" var="customer">
										<tr>
											<td>${customer.emailid}</td>
											<td>${customer.firstname}</td>
											<td>${customer.lastname}</td>
											<td>${customer.creditCard}</td>
										</tr>

									</c:forEach>

								</table>

							</c:when>
							<c:otherwise>
								<br>
								<div class="alert alert-info">Customer List is empty</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
			<hr />
			<div class="container">
				<div class="jumbotron">
					<h2>Purchase History List</h2>
				</div>

				<div class="card">
					<div class="card-body">
							<form action="purchasefilter" method="get" role="form">
								
									<input type="text" name="productCat" id="productCat" size="50" placeholder="Enter the product category to filter" /><br/>
									<input type="date" id="orderDate" name="orderDate"  placeholder="Select order date to filter "/><br/><br/>
								
								 <button type="submit" class="btn btn-info">
									<span class="glyphicon glyphicon-search"></span> Search
								</button>
								<br>
							</form>
						<hr />

						<c:choose>
							<c:when test="${not empty purchaseList}">

								<table class="table table-striped">
									<thead>
										<tr>
										    <th scope="col">Order By</th>
											<th scope="col">Order Id</th>
											<th scope="col">Product Id</th>
											<th scope="col">Product Name</th>
											<th scope="col">Product Category</th>
											<th scope="col">Order Date</th>
										</tr>
									</thead>

									<c:forEach items="${purchaseList}" var="purchase">
										<tr>
											<td>${purchase.orderBy}</td>
											<td>${purchase.orderId}</td>
											<td>${purchase.prdid}</td>
											<td>${purchase.prdname}</td>
											<td>${purchase.usageType}</td>
											<td>${purchase.orderDate}</td>
										</tr>

									</c:forEach>

								</table>

							</c:when>
							<c:otherwise>
								<br>
								<div class="alert alert-info">Purchase Hstory List is empty</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
			<hr/>
		</c:when>
		<c:otherwise>
			<%
			response.sendRedirect("index.jsp");
			%>
		</c:otherwise>
	</c:choose>

</body>
</html>