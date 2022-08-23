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

	<c:if test="${not empty msg }">

		<div class="container">

			<div class="alert alert-danger">${ msg }</div>

		</div>
	</c:if>

	<c:choose>
		<c:when
			test="${not empty sessionScope.loggedInUser && sessionScope.loggedInUser.equalsIgnoreCase(adminUser)}">

			<div class="container">
				<div class="jumbotron">
					<h2>Product Usage Type Registration</h2>
				</div>
				<div class="card">
					<div class="card-body">

						<form action="createusagetype" method="post">

							<div class="row mb-3">

								<label for="usageTypeId" class="col-sm-2 col-form-label"> Usage Type Id</label>

								<div class="col-sm-10">
									<input type="text" class="form-control" name="usageTypeId"
										placeholder="Enter a unique product usage type id e.g. Soccer" required />
								</div>
							</div>

							<div class="row mb-3">

								<label for="usagetypename" class="col-sm-2 col-form-label">
									Usage Type Name</label>

								<div class="col-sm-10">
									<input type="text" class="form-control" name="usagetypename"
										placeholder="Enter a product usage type name e.g. Soccer" required />
								</div>
							</div>

							
							<hr />
						

							<label class="col-sm-2 col-form-label"></label>

							<button type="submit" class="btn btn-primary">Submit</button>
							<input class="btn btn-secondary" type="reset" value="Reset">

						</form>
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