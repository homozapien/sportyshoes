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

	<c:choose>
		<c:when
			test="${not empty sessionScope.loggedInUser && sessionScope.loggedInUser.equalsIgnoreCase(adminUser)}">

			<div class="container">
				<div class="jumbotron">
					<h2>Products List</h2>
				</div>

				<c:choose>
					<c:when test="${not empty productList}">

						<table class="table table-striped">
							<thead>
								<tr>
									<th scope="col">Id</th>
									<th scope="col">Name</th>
									<th scope="col">Usage</th>
									<th scope="col">Brand</th>
									<th scope="col">Unit Price</th>
								    <th scope="col">Logo</th> 
								</tr>
							</thead>
							
								<c:forEach items="${productList}" var="product">
													<tr>
														<td>${product.prdid}</td>
														<td>${product.prdname}</td>
														<td>${product.getProductUsage().getUsageType()}</td>
														<td>${product.getProductBrand().getBrand_name()}</td>
														<td>${product.prdprice}</td>
                                                        <td><img alt="img" src="data:image/jpeg;base64,${product.base64PrdLogo}" width="80" height="80"/></td>
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
			<hr/>
			
			<div class="container">
				<div class="jumbotron">
					<h2>All available Brand List</h2>
				</div>

				<c:choose>
					<c:when test="${not empty pdrBrandList}">

						<table class="table table-striped">
							<thead>
								<tr>
									<th scope="col">Id</th>
									<th scope="col">Brand Name</th>
								</tr>
							</thead>
							
								<c:forEach items="${pdrBrandList}" var="brand">
													<tr>
														<td>${brand.brand_id}</td>
														<td>${brand.brand_name}</td>
													</tr>

												</c:forEach>

						</table>

					</c:when>
					<c:otherwise>
						<br>
						<div class="alert alert-info">Brand List is empty</div>
					</c:otherwise>
				</c:choose>

			</div>
			
			<hr/>
			
			<div class="container">
				<div class="jumbotron">
					<h2>All available Usage List</h2>
				</div>

				<c:choose>
					<c:when test="${not empty prdUsageList}">

						<table class="table table-striped">
							<thead>
								<tr>
									<th scope="col">Id</th>
									<th scope="col">Usage Type</th>
									
								</tr>
							</thead>
							
								<c:forEach items="${prdUsageList}" var="usageType">
													<tr>
														<td>${usageType.usage_id}</td>
														<td>${usageType.usageType}</td>
														
													</tr>

												</c:forEach>

						</table>

					</c:when>
					<c:otherwise>
						<br>
						<div class="alert alert-info">Usage List is empty</div>
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