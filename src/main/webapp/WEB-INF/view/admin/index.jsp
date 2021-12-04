<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Home - Brand</title>

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
</head>

<body>
	<div class="body">
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
									href="${pageContext.request.contextPath}/admin/ListRequestVerifyPost">Create
									post request</a> <a class="dropdown-item" role="presentation"
									href="${pageContext.request.contextPath}/admin/ListTrainee">Manage
									trainee's account</a> <a class="dropdown-item" role="presentation"
									href="${pageContext.request.contextPath}/admin/ListTrainers">Manage
									trainer's account</a> <a class="dropdown-item" role="presentation"
									href="${pageContext.request.contextPath}/admin/BlackList">Black
									list </a>
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
								src="<c:url value="${profileAdmin.avatar}" />"
								style="width: 30px; height: 30px;"> ${profileAdmin.fullName}
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
		<main class="page lanidng-page">
			<section class="portfolio-block block-intro">
				<div class="container">
					<div class="about-me">
						<p class="flash animated infinite"
							style="margin-bottom: -0px; margin-top: -61px; color: rgb(223, 22, 22);">
							<strong>HELLO ADMIN ${name}! WELCOME BACK</strong>
						</p>
					</div>
				</div>
				<div class="stylecss" style="height: 70px;">
					<p class="loop">

						<strong>${notifycation}</strong>
					</p>
				</div>
			</section>
		</main>
	</div>
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