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
					<h2>SportyShoes Administrative Operations</h2>
				</div>

				<table class="table table-striped">
					<thead>
						<tr>
							<th scope="col">Name</th>
							<th scope="col">Admin Operation Description</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><a href="passwordmgmt.jsp">Change Password</a></td>
							<td>Password Management</td>
						</tr>

						<tr>
							<td><a href="/sportyshoes/showProductView">Product Setup</a></td>
							<td>Setup Product Items</td>
						</tr>

						<tr>
							<td><a href="/sportyshoes/getAllProducts">Reporting
									Dashboard</a></td>
							<td>Readonly Reporting Dashboard</td>
						</tr>
					<tbody>
				</table>
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