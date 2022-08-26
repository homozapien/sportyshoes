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
</body>
</html>