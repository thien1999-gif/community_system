<%@page import="com.online.shop.dto.Village"%>
<%@page import="com.online.shop.dto.Ward"%>
<%@page import="com.online.shop.dto.District"%>
<%@page import="com.online.shop.dto.Province"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>DASHBOARD</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
	
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
		
		<style type="text/css">
		input {
	width: 100%;
}  small{
	color : red;
}
		</style>	
</head>

<body class="text-monospace text-nowrap">
	<header>
		<div class="d-xl-flex flex-shrink-1 flex-fill">
			<img
				class="d-xl-flex justify-content-xl-center align-items-xl-center"
				src="<c:url value="/traineeAssets/img/banner.jpg"/>"
				style="width: 100%; height: 480px;">
		</div>
		<div>
			<nav class="navbar navbar-light navbar-expand-md navigation-clean"
				style="padding: 5px; background-color: rgb(255, 255, 255);">
				<div class="container">
					<a class="navbar-brand" href="#" style="color: #213d30;"> <img
						src="<c:url value="/traineeAssets/img/logo.png"/>"
						style="width: 150px; height: 100px;">
					</a>
					<button class="navbar-toggler" data-toggle="collapse"
						data-target="#navcol-1">
						<span class="sr-only">Toggle navigation</span><span
							class="navbar-toggler-icon"> </span>
					</button>
					<div class="collapse navbar-collapse" id="navcol-1">
						<ul class="nav navbar-nav mr-auto">
							<li class="nav-item" role="presentation"><a
								class="nav-link active shadow" href="trainee-Home">DASHBOARD
							</a></li>
							<li class="nav-item" role="presentation"><a
								class="nav-link shadow" href="newPost">New Post</a></li>
						</ul>
						<ul class="nav navbar-nav ml-auto">
							<li class="nav-item dropdown show"><a
								class="dropdown-toggle nav-link shadow" data-toggle="dropdown"
								aria-expanded="true" href="#" style="padding: 10px;">${traineeProfile.fullName}
									<img src="<c:url value="${traineeProfile.avatar}"/>"
									style="height: 30px; padding: 0; margin-left: 5px; width: 30px;">
							</a>
								<div class="dropdown-menu" role="menu">
									<a class="dropdown-item" role="presentation" href="viewProfile">PROFILE
										<i class="fa fa-address-book-o float-right" id="logouticon"
										style="margin: 0px; width: 19px; height: 19px;"> </i>
									</a> <a class="dropdown-item" role="presentation"
										href="<c:url value="/guest/logout"/>">LOGOUT <i
										class="fa fa-user-times float-right" id="logouticon"
										style="width: 19px; height: 19px; margin: 0px;"> </i>
									</a>
								</div></li>
						</ul>
					</div>
				</div>
			</nav>
		</div>
	</header>
	<div class="container">
		<div>
			<nav
				class="navbar navbar-light navbar-expand-md justify-content-center"
				style="padding: 5px; background-color: rgb(255, 255, 255);">
				<ul class="nav navbar-nav ">
					<li class="nav-item" role="presentation"><a
						class="nav-link active shadow" href="myCourse">MY COURSE</a></li>
					<li class="nav-item" role="presentation"><a
						class="nav-link active shadow" href="manageClass">RATING
							TRAINER</a></li>
					<li class="nav-item" role="presentation"><a
						class="nav-link shadow" href="viewTrainerRequests">TRAINER
							REQUEST </a></li>
					<li class="nav-item" role="presentation"><a
						class="nav-link shadow" href="createPost">CREATE POST</a></li>
				</ul>
			</nav>
		</div>
		<!-- Data Form -->
		<h1 style="text-align: center">My Profile</h1>
		<form:form id="viewProfile" action="" commandName="traineeProfile" method="POST"
			enctype="multipart/form-data">
			<div class="row justify-content-md-center">
				<div class="col-lg-auto col-md-auto col-sm-auto">
					<div style="text-align: center;">

						<img id="uploadImg"
							src="<c:url value="${traineeProfile.avatar}"/>"
							style="width: 100px; height: 100px;">
					</div>
					<div style="text-align: center; margin-top: 5px;">
						<form:input path="profileImage" type="file"
							onchange="document.getElementById('uploadImg').src = window.URL.createObjectURL(this.files[0])" />
					</div>
					<div
						class="table-responsive table-bordered bg-light border rounded border-dark shadow"
						style="margin: 10px 0px; white-space: initial;">
						<table class="table table-striped table-bordered table-sm">
							<thead>
							</thead>
							<tbody>
								<tr>
									<td>Email</td>
									<td><form:input path="email" disabled="true" /></td>
								</tr>
								<tr>
									<td>Name</td>
									<td><form:input id="fullname"  required="true" path="fullName" />
									<small id="fullname_error_message" class="form-text text-muted"> </small></td>
									
								</tr>

								<tr>
									<td>Gender</td>
									<td><form:select class="form-control" path="gender">
											<optgroup label="Choose one subject">
												<option value="1"
													${traineeProfile.gender == 1? 'selected':'' }>Male</option>
												<option value="2"
													${traineeProfile.gender == 2? 'selected':'' }>FeMale</option>
											</optgroup>
										</form:select></td>
								</tr>
								<tr>
									<td>Day of Birth</td>
									<td><form:input id="dateOfBirth" type="date" path="dayOfBirth" /><small
							id="date_error_message" class="form-text text-muted"></small></td>
								</tr>
								<tr>
									<td>Address</td>
									<td>
								<form:input disabled="true" path="address"/>					
	
							<%@ include file="../includes/address.jsp" %> 
									</td>
								</tr>
								<tr>
									<td>Facebook</td>
									<td><form:input path="facebook" /></td>

								</tr>
								<tr>
									<td>Zalo</td>
									<td><form:input path="zalo" /></td>

								</tr>

								<tr>
									<td>Phone</td>
									<td><form:input required="true" path="phone" />
									<small
							id="phone_error_message" class="form-text text-muted"></small>
									</td>
								</tr>
							</tbody>
						</table>
									 <input  type="hidden" id="villages" name="numberAddress" value="null" /> 
									 <input  type="hidden" id="wards"  name="wards" value="null" />
									 <input  type="hidden" id="districts" name="district"  value="null" />
									 <input  type="hidden" id="citys" name="city" value="null" />	
									<input type="hidden" name = "addressHidden" value = "${traineeProfile.address}" />
						<form:input path="avatar" type="hidden"/>
						<button type="submit" class="btn btn-primary">Update</button>
					</div>
				</div>
			</div>

		</form:form>
		<!-- Paging -->
	</div>
	<div class="footer-clean">
		<footer>
			<div class="container">
				<div class="row justify-content-center">
					<div class="col-sm-4 col-md-3 item">
						<h3>Services</h3>
						<ul>
							<li><a href="#">Web design</a></li>
							<li><a href="#">Development</a></li>
							<li><a href="#">Hosting</a></li>
						</ul>
					</div>
					<div class="col-sm-4 col-md-3 item">
						<h3>About</h3>
						<ul>
							<li><a href="#">Company</a></li>
							<li><a href="#">Team</a></li>
							<li><a href="#">Legacy</a></li>
						</ul>
					</div>
					<div class="col-sm-4 col-md-3 item">
						<h3>Careers</h3>
						<ul>
							<li><a href="#">Job openings</a></li>
							<li><a href="#">Employee success</a></li>
							<li><a href="#">Benefits</a></li>
						</ul>
					</div>
				</div>
			</div>
		</footer>
	</div>
	
		<script
		src="<c:url value="/traineeAssets/js/CheckValidTraineeProfile.js" />"></script>
	

	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
		integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
		crossorigin="anonymous"></script>
</body>

</html>