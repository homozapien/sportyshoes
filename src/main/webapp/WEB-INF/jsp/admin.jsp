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
					<h2>SportyShoes Administrative Operations</h2>
				</div>
				<div class="card">
					<div class="card-body">
						<table class="table table-striped">
							<thead>
								<tr>
									<th scope="col">Name</th>
									<th scope="col">Admin Operation Description</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><a href="/sportyshoes/changePassword">Change
											Password</a></td>
									<td>Password Management</td>
								</tr>

								<tr>
									<td><a href="/sportyshoes/createproduct">Product</a></td>
									<td>Setup Product Items</td>
								</tr>

								<tr>
									<td><a href="/sportyshoes/createbrand"> Brand </a></td>
									<td>Setup Product Brand</td>
								</tr>

								<tr>
									<td><a href="/sportyshoes/createusagetype">Usage Type</a></td>
									<td>Setup Product Usage</td>
								</tr>

								<tr>
									<td><a href=otheradmintasks>Admin Task</a></td>
									<td>Additional Admin Task</td>
								</tr>

								<tr>
									<td><a href=refreshDashboard>Reporting Dashboard</a></td>
									<td>Reporting Dashboard</td>
								</tr>
							<tbody>
						</table>
					</div>
				</div>
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