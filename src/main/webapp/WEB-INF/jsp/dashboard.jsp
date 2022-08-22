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
														<td>${product.prdusage}</td>
														<td>${product.prdbrand}</td>
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

		</c:when>
		<c:otherwise>
			<%
	          response.sendRedirect("index.jsp");
         %>
		</c:otherwise>
	</c:choose>

</body>
</html>