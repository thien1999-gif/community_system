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
	<main class="page hire-me-page">
		<div class="stylecss" style="height: 70px; margin-top: 0px;">
			<p class="loop">
				<strong>You have 9 request and 10 request</strong>
			</p>
		</div>
		<section class="portfolio-block hire-me">

			<div class="container" style="margin: 50px auto; text-align: center;">
				<div class="heading">
					<h2>TEACHER &nbsp;UPDATE PROFILE</h2>
				</div>
				<div style="margin-top: 0px; margin-bottom: 8px;">
					<img src="<c:url value="${trainerNew.avatar}" />"
						style="margin: 50px auto; min-width: 200px; min-height: 200px; width: 100px; height: 100px;">
				</div>
				<div style="margin-top: 0px; margin-bottom: 8px; margin-left: 550x;"></div>
				<form:form
					style="margin-bottom: 0px; margin-top: 0px; text-align: left;"
					method="POST" commandName="trainer">
					<div class="form-group">
						<label for="email">Email</label><input readonly class="form-control"
							name="idEmail" value="${idEmail}" type="email" />
						<form:input path="email" type="hidden" />
					</div>
					<div class="form-group">
						<label for="subject" 
							${trainerNew.fullName != trainerOld.fullName ? 'style="color:red"':''}>Full
							name</label>
						<form:input readonly="true" class="form-control" path="fullName" type="text" />

					</div>
					<div class="form-group">
						<label for="subject"
							${trainerNew.gender != trainerOld.gender ? 'style="color:red"':''}>Gender</label>
							<input class="form-control" readonly name="" value="${trainer.gender == 1 ? 'Male':'Female' }" >
						<form:input type="hidden" class="form-control" path="gender" />
					</div>
					<div class="form-group">
						<label for="subject"
							${trainerNew.address != trainerOld.address ? 'style="color:red"':''}>Address</label>
						<form:input readonly="true" class="form-control" path="address" type="text" />
						<form:input readonly="true" class="form-control" path="codeAddress" type="hidden" />
					</div>
					<div class="form-group">
						<label for="message"
							${trainerNew.dayOfBirth != trainerOld.dayOfBirth ? 'style="color:red"':''}>Date
							of birth</label>
						<form:input readonly="true" path="dayOfBirth" class="form-control" type="date" />
					</div>
					<div class="form-group">
						<label for="message"
							${trainerNew.graduationYear != trainerOld.graduationYear ? 'style="color:red"':''}>Granduation
							year</label>
						<form:input readonly="true" path="graduationYear" class="form-control" type="text" />
					</div>
					<div class="form-group">
						<label for="message"
							${trainerNew.salaryPerHour != trainerOld.salaryPerHour ? 'style="color:red"':''}>Salary
							per hour</label>
						<form:input readonly="true" path="salaryPerHour" class="form-control" type="text" />
					</div>
					<div class="form-group">
						<label for="message"
							${trainerNew.experience != trainerOld.experience ? 'style="color:red"':''}>Experience</label>
						<form:input readonly="true" path="experience" class="form-control" type="text" />
					</div>
					<div class="form-group">
						<label for="message"
							${trainerNew.facebook != trainerOld.facebook ? 'style="color:red"':''}>Facebook</label>
						<form:input readonly="true" class="form-control" path="facebook" type="text" />
					</div>
					<div class="form-group">
						<label for="message"
							${trainerNew.zalo != trainerOld.zalo ? 'style="color:red"':''}>Zalo</label>
						<form:input readonly="true" class="form-control" path="zalo" type="text" />
					</div>					
					<div class="form-group">
						<label for="message"
							${trainerNew.phone != trainerOld.phone ? 'style="color:red"':''}>Phone</label>
						<form:input readonly="true" class="form-control" path="phone" type="tel" />
					</div>
					
					<form:input path="description" type="hidden"/>
					<form:input path="idCard" type="hidden"/>
					<form:input path="resumeLink" type="hidden"/>
					<form:input path="latitude" type="hidden"/>
					<form:input path="longitude" type="hidden"/>
					<form:input path="verify" type="hidden"/>
					
					
					<form:input type="hidden" path="avatar"
						value="${trainerNew.avatar}" />
					<div class="btn-group" role="group">
						<button class="btn btn-primary" type="submit"
							style="background-color: rgb(31, 239, 13);">
							<i class="typcn typcn-tick-outline">Accept</i>
						</button>
						<a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/DenyUpdateRequest?id=${trainerNew.email}"
							style="background-color: rgb(239, 13, 13);">Deny</a>
						<a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/ListTeacherRequestUpdate"
							style="background-color: rgb(239, 13, 13);">Cancel</a>

					</div>
				</form:form>
			</div>
		</section>
	</main>
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
</body>

</html>