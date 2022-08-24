<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="mybootstrap.jsp"%>
</head>
<body>
	
			<div class="container">
				<div class="jumbotron">

					<h4>Sportyshoes Customer Signup</h4>
					<div class="card">
						<div class="card-body">
							<form action="createProfile" method="post">

								<div class="row mb-3">

									<label for="emailid" class="col-sm-2 col-form-label">Email Address</label>
										
									<div class="col-sm-10">
										<input type="email" class="form-control" name="emailid"
											placeholder="Enter unique email address" required />
									</div>
								</div>

								<div class="row mb-3">

									<label for="firstname" class="col-sm-2 col-form-label">Firstname</label>
									<div class="col-sm-10">
										<input type="text" class="form-control input-sm"
											name="firstname" aria-label="default input"
											placeholder="Enter firstname" required />
									</div>
								</div>

								<div class="row mb-3">

									<label for="lastname" class="col-sm-2 col-form-label">Lastname</label>
									<div class="col-sm-10">
										<input type="text" class="form-control input-sm"
											name="lastname" aria-label="default input"
											placeholder="Enter lastname" required />
									</div>
								</div>

								<div class="row mb-3">

									<label for="password" class="col-sm-2 col-form-label">Password</label>
										

									<div class="col-sm-10">
										<input type="password" class="form-control input-sm"
											name="password" aria-label="default input"
											placeholder="Enter password" required />
									</div>
								</div>

								<hr>

								<label class="col-sm-2 col-form-label"></label>

								<button type="submit" class="btn btn-primary">Submit</button>
								<input class="btn btn-secondary" type="reset" value="Reset">

							</form>
						</div>
					</div>
				</div>
			</div>
</body>

</html>