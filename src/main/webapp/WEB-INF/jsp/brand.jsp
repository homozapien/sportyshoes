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
					<h2>Brand Registration</h2>
				</div>
				<div class="card">
					<div class="card-body">

						<form action="createbrand" method="post">

							<div class="row mb-3">

								<label for="brandid" class="col-sm-2 col-form-label"> Brand Id</label>

								<div class="col-sm-10">
									<input type="text" class="form-control" name="brandid"
										placeholder="Enter a unique product brand id" required />
								</div>
							</div>

							<div class="row mb-3">

								<label for="brandname" class="col-sm-2 col-form-label">
									Brand Name</label>

								<div class="col-sm-10">
									<input type="text" class="form-control" name="brandname"
										placeholder="Enter a product brand name" required />
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