<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Hire me - Brand</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Lato:300,400,700">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/css/pikaday.min.css">

<link rel="stylesheet"
	href="<c:url value="/adminAssets/bootstrap/css/bootstrap2.min.css" />">
<link rel="stylesheet"
	href="<c:url value="/adminAssets/fonts/font-awesome.min.css" />">
<link rel="stylesheet"
	href="<c:url value="/adminAssets/fonts/ionicons.min.css" />">
<link rel="stylesheet"
	href="<c:url value="/adminAassets/fonts/typicons.min.css" />">
<link rel="stylesheet"
	href="<c:url value="/adminAssets/css/Pretty-Table-1.css" />">
<link rel="stylesheet"
	href="<c:url value="/adminAssets/css/Pretty-Table.css" />">
<link rel="stylesheet"
	href="<c:url value="/adminAssets/css/Social-Icon-Circle.css" />">
<link rel="stylesheet"
	href="<c:url value="/adminAssets/css/Table-with-search-1-1.css" />">
<link rel="stylesheet"
	href="<c:url value="/adminAssets/css/Table-With-Search-1.css" />">
<link rel="stylesheet"
	href="<c:url value="/adminAssets/css/Table-With-Search.css" />">
<link rel="stylesheet"
	href="<c:url value="/adminAssets/css/style.css" />">
<link rel="stylesheet"
	href="<c:url value="/adminAssets/bootstrap/css/bootstrap.min.css" />">
<link rel="stylesheet"
	href="<c:url value="/adminAssets/fonts/fontawesome-all.min.css" />">
<link rel="stylesheet"
	href="<c:url value="/adminAssets/css/style.css" />">
	<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
</head>

<body>
	<nav class="navbar navbar-expand-lg portfolio-navbar">
		<div class="container">
			<a class="navbar-brand logo"
				href="${pageContext.request.contextPath}/admin/admin-Home"
				style="color: black;">Dashboard</a>
			<button class="navbar-toggler" data-toggle="collapse"
				data-target="#navbarNav" style="border-color: black;">
				<span class="sr-only">Toggle navigation</span> <i
					class="fas fa-bars"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="nav navbar-nav ml-auto">
					<li class="nav-item dropdown"><a
						class="dropdown-toggle nav-link" data-toggle="dropdown"
						aria-expanded="false" href="#" style="color: black;">Account</a>
						<div class="dropdown-menu" role="menu">
							<a class="dropdown-item" role="presentation"
								href="${pageContext.request.contextPath}/admin/ListTeacherRequest">Register
								request</a><a class="dropdown-item" role="presentation"
								href="${pageContext.request.contextPath}/admin/ListTeacherRequestUpdate">Update
								Info request</a> <a class="dropdown-item" role="presentation"
								href="${pageContext.request.contextPath}/admin/ListTrainee">Manage
								trainee's account</a> <a class="dropdown-item" role="presentation"
								href="${pageContext.request.contextPath}/admin/ListRequestVerifyPost">Create
								post request</a> <a class="dropdown-item" role="presentation"
								href="${pageContext.request.contextPath}/admin/ListTrainers">Manage
								trainer's account</a>
								<a class="dropdown-item" role="presentation"
								href="${pageContext.request.contextPath}/admin/BlackList">Black list
								</a>

						</div></li>
					<li class="nav-item dropdown"><a
						class="dropdown-toggle nav-link" data-toggle="dropdown"
						aria-expanded="false" href="#" style="color: black;">Course</a>
						<div class="dropdown-menu" role="menu">
							<a class="dropdown-item" role="presentation"
								href="${pageContext.request.contextPath}/admin/ListCourse">Course
								manager</a>
						</div></li>
					<li class="nav-item dropdown"><a
						class="dropdown-toggle nav-link" data-toggle="dropdown"
						aria-expanded="false" href="#" style="color: black;"> <img
							src="<c:url value="${adminProfile.avatar}" />"
							style="width: 30px; height: 30px;"> ${adminProfile.fullName}
					</a>
						<div class="dropdown-menu" role="menu">
							<a class="dropdown-item" role="presentation"
								href="${pageContext.request.contextPath}/admin/AdminInfo">Profile</a> <a class="dropdown-item"
								role="presentation" href="<c:url value="/guest/logout"/>">Sign
								out</a>
						</div></li>
				</ul>
			</div>
		</div>
	</nav>
	<main class="page hire-me-page">
		<section class="portfolio-block hire-me">
			<div class="stylecss" style="height: 70px; margin-top: -50px;">
				<p class="loop">You have 9 request and 10 request</p>
			</div>
			<div class="container" style="text-align: center;">

				<div class="heading" style="margin-top: 50px;">
					<h2>Information</h2>
				</div>

				<div style="margin-top: -50px;">
					<img src="<c:url value="${adminProfile.avatar}" />"
						style="margin: 50px auto; min-width: 200px; min-height: 200px; width: 200px;">
				</div>
				<form:form id="frmProfileAdmin" action="" style="margin: 50px auto; text-align: left;"
					method="POST" commandName="adminProfile"
					enctype="multipart/form-data">



					<div
						style="margin-top: 0px; margin-bottom: 5px; margin-left: 550x;">
						<form:input id="profileImage" class="btn btn-primary" type="file"
							path="profileImage" />

					</div>

					<div class="form-group">
						<label for="email">Email</label>
						<form:input readonly="true" class="form-control" path="email" type="text"
							id="email" />


					</div>

					<div class="form-group">
						<label for="message">Full name</label>
						<form:input required="true" class="form-control" path="fullName" type="tel" />
					</div>
					<div class="form-group">
						<label for="message">Date of birth</label>
						<form:input path="dayOfBirth" class="form-control" type="date" />
						<small id="date_error_message" class="form-text text-muted"> </small>
					</div>
					<br>
					<hr>
					<p style="color:red">Optional*</p>
					<div class="form-group">
						<label for="subject">Old Password</label><input
							class="form-control" name="oldPassword" type="password" />
						<c:if test="${ not empty errorPassword}">
							<span style="color: red;">${errorPassword}</span>
						</c:if>

					</div>
					<div class="form-group">
						<label for="subject">New Password</label>
						<input name="newPassword" id="password" class="form-control"  type="password" />
						<small id="password_error_message" class="form-text text-muted"> </small>
					</div>
					<div class="form-group">
						<label for="subject">Confirm Password</label><input id="confirm_password"
							class="form-control" type="password">
							<small id="confirm_password_error_message" class="form-text text-muted"> </small>
					</div>
					<button class="btn btn-primary btn-block" type="submit">Update</button>
				</form:form>
			</div>
		</section>
	</main>
</body>
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
<script src="<c:url value="/adminAssets/js/checkProfileAdmin.js" />"></script>



<script
	src="https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/pikaday.min.js"></script>

<script src="<c:url value="/adminAssets/js/jquery.min.js"/>"></script>
<script
	src="<c:url value="/adminAssets/bootstrap/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/adminAassets/js/Table-With-Search.js"/>"></script>
<script src="<c:url value="/adminAssets/js/theme.js"/>"></script>
<script src="<c:url value="/adminAssets/js/Table-with-search-1.js"/>"></script>
<script src="<c:url value="/adminAssets/js/Table-With-Search.js"/>"></script>

</html>