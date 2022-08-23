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
					<h2>Product Registration</h2>
				</div>
				<div class="card">
					<div class="card-body">

						<!--  -c:url var="createProduct_url" value="/sportyshoes/createProduct" /-->
						<form action="createProduct" method="post"
							enctype='multipart/form-data'>

							<div class="row mb-3">

								<label for="prdid" class="col-sm-2 col-form-label"> Id</label>

								<div class="col-sm-10">
									<input type="text" class="form-control" name="prdid"
										placeholder="Enter a unique product Id" required />
								</div>
							</div>

							<div class="row mb-3">

								<label for="prdname" class="col-sm-2 col-form-label">
									Name</label>

								<div class="col-sm-10">
									<input type="text" class="form-control" name="prdname"
										placeholder="Enter a product name" required />
								</div>
							</div>

							<div class="row mb-3">

								<label for="prdprice" class="col-sm-2 col-form-label">
									Unit Price</label>

								<div class="col-sm-10">
									<input type="number" class="form-control" name="prdprice" step="any" min=0
										placeholder="Enter a product unit price" required />
								</div>
							</div>
							<div class="row mb-3">

								<label for="prdlogo" class="col-sm-2 col-form-label">Upload
									Logo</label>

								<div class="col-sm-10">
									<input type="file" class="form-control" name="prdlogo"
										accept="image/png, image/gif, image/jpeg" required />

								</div>
							</div>
							
							 <div class="row mb-3">

								<label for="prdusage" class="col-sm-2 col-form-label">Usage Type</label>
								<div class="col-sm-10">
									<select name="prdusage">
                                                 <c:forEach items="${prdUsageList}" var="usageType">
                                                       <option value="${usageType.usage_id}">${usageType.usageType}</option>
                                                 </c:forEach>
                                     </select>

								</div>
							</div>
							
							
                         <div class="row mb-3">

								<label for="prdbrand" class="col-sm-2 col-form-label">Brand</label>
								<div class="col-sm-10">
									<select name="prdbrand">
                                                 <c:forEach items="${pdrBrandList}" var="brand">
                                                       <option value="${brand.brand_id}">${brand.brand_name}</option>
                                                 </c:forEach>
                                     </select>

								</div>
							</div>
							
							
							<hr />
						<!--  	<fieldset class="row mb-3">

								<legend class="col-form-label col-sm-2 pt-0">Usage</legend>

								<div class="col-sm-10">

									<div class="form-check">
										<label class="form-check-label"> 
										<input
											class="form-check-input" type="radio" name="prdusage"
											id="soccerUsageType" value="Soccer"> Soccer
										</label>
									</div>
									<div class="form-check">
										<label class="form-check-label"> <input
											class="form-check-input" type="radio" name="prdusage"
											id="athleticsUsageType" value="Athletics"> Athletics
										</label>
									</div>
									<div class="form-check">
										<label class="form-check-label"> <input
											class="form-check-input" type="radio" name="prdusage"
											id="skatingUsageType" value="Skating"> Skating
										</label>
									</div>
									<div class="form-check">
										<label class="form-check-label"> <input
											class="form-check-input" type="radio" name="prdusage"
											id="tennisUsageType" value="Tennis"> Tennis
										</label>
									</div>
								</div>
							</fieldset>
							<hr />
							<fieldset class="row mb-3">

								<legend class="col-form-label col-sm-2 pt-0">Brand</legend>

								<div class="col-sm-10">

									<div class="form-check">
										<label class="form-check-label"> <input
											class="form-check-input" type="radio" name="prdbrand"
											id="nikeBrand" value="Nike"> Nike
										</label>
									</div>
									<div class="form-check">
										<label class="form-check-label"> <input
											class="form-check-input" type="radio" name="prdbrand"
											id="adidasBrand" value="Adidas"> Adidas
										</label>
									</div>
									<div class="form-check">
										<label class="form-check-label"> <input
											class="form-check-input" type="radio" name="prdbrand"
											id="genericBrand" value="Generic"> Generic
										</label>
									</div>

								</div>
							</fieldset>

							<hr> -->

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