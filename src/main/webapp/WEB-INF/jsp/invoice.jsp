<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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

			<div class="alert alert-success">${ msg }</div>

		</div>
	</c:if>
	
	<c:set var="custUser" value="customer" />
	<c:set var="user" value="${sessionScope.loggedInUser}" />

	<c:choose>
		<c:when test="${not empty user && user.getTypeOfUser().equalsIgnoreCase(custUser)}">
			
	      <div class="container">
				<div class="jumbotron">
					<h2>SportyShoes Payment Invoice</h2>
				</div>
				<br/>
				
				<c:choose>
					<c:when test="${not empty itemList && not empty orderid}">
				
				       <div class="card">
							<div class="card-body">
				
				      <table class="table table-striped">
										<caption align="top"> Invoice#  <span style="color: red"> ${orderid} </span> for Ordered Items!  
										  &nbsp; Recepient: <b><span style="color: blue">${user.emailid}</span></b>
										</caption>
										<thead>
											<tr>
												<th scope="col">Logo</th>
												<th scope="col">Name</th>
												<th scope="col">Brand</th>
												<th scope="col">Usage</th>
												<th scope="col">Quantity</th>
												<th scope="col">Sub-Total</th>
											</tr>
										</thead>

										<c:forEach items="${itemList}" var="item">
											<tr>
												<td><img alt="img" src="data:image/jpeg;base64,${item.getProduct().base64PrdLogo}" width="60" height="60" /></td>
												<td>${item.getProduct().prdname}</td>
												<td>${item.getProduct().getProductBrand().getBrand_name()}</td>
												<td>${item.getProduct().getProductUsage().getUsageType()}</td>
												<td>${item.quantity}</td>
												<td>${item.subTotalPrice}</td>
											</tr>
										</c:forEach>
										<tr>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>Total Price: </td>
												<td>${totalPrice}</td>
											</tr>
										
									</table>
				    </div>
				  </div>
				
				   </c:when>
					<c:otherwise>
						<br>
						<div class="alert alert-info">Ordered Item List is empty</div>
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