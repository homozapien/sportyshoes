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

	<c:set var="custUser" value="customer" />
	<c:set var="user" value="${sessionScope.loggedInUser}" />

	<c:choose>
		<c:when
			test="${not empty user && user.getTypeOfUser().equalsIgnoreCase(custUser)}">

			<div class="container">
				<div class="jumbotron">
					<h2>SportyShoes Checkout</h2>
				</div>
				<br />
				<c:choose>
					<c:when test="${not empty sessionScope.cartItemList}">

						<div class="card">
							<div class="card-body">


								<div class="mb-3">

									<form action="makepayment"  role="form">

										<table class="table table-striped">
											<caption align="top">Shopping Cart Item(s)</caption>
											<thead>
												<tr>
													<th scope="col">Logo</th>
													<th scope="col">Name</th>
													<th scope="col">Quantity</th>
													<th scope="col">Unit Price</th>
													<th scope="col">Sub-Total Price</th>
												</tr>
											</thead>

											<c:forEach items="${sessionScope.cartItemList}"
												var="cartItem">
												<tr>
													<td><img alt="img"
														src="data:image/jpeg;base64,${cartItem.getProduct().getBase64PrdLogo()}"
														width="60" height="60" /></td>
													<td>${cartItem.getProduct().getPrdname()}</td>
													<td>${cartItem.quantity}</td>
													<td>${cartItem.getProduct().getPrdprice()}</td>
													<td>${cartItem.subTotalPrice}</td>
												</tr>

											</c:forEach>
											<tr>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td><span style="color: red">Total Price: </span></td>
												<td><span style="color: red">${sessionScope.totalPrice}</span></td>
											</tr>
											<tr>
												<td colspan="5">&nbsp;</td>

											</tr>

											<tr>
												<td colspan="5"><button type="submit"
														class="btn btn-primary">Make Payment</button></td>

											</tr>

										</table>





									</form>



								</div>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<br>
						<div class="alert alert-info">Shopping Cart is empty</div>
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