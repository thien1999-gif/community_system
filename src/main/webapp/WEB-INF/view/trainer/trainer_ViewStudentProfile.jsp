<%@page import="com.online.shop.util.SecurityUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>View trainee profile</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<META HTTP-EQUIV="Content-language" CONTENT="vi">
<link rel="stylesheet"
	href="<c:url value="/trainerAssets/bootstrap/css/bootstrap.min.css" />">
<link rel="stylesheet"
	href="<c:url value="/trainerAssets/fonts/font-awesome.min.css" />">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet"
	href="<c:url value="/trainerAssets/css/Data-Table-1.css" />">
<link rel="stylesheet"
	href="<c:url value="/trainerAssets/css/Data-Table.css" />">
<link rel="stylesheet"
	href="<c:url value="/trainerAssets/css/Footer-Basic.css" />">
<link rel="stylesheet"
	href="<c:url value="/trainerAssets/css/Footer-Clean.css" />">
<link rel="stylesheet"
	href="<c:url value="/trainerAssets/css/Footer-Dark.css" />">
<link rel="stylesheet"
	href="<c:url value="/trainerAssets/css/header-1.css" />">
<link rel="stylesheet"
	href="<c:url value="/trainerAssets/css/header-2.css" />">
<link rel="stylesheet"
	href="<c:url value="/trainerAssets/css/header.css" />">
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.15/css/dataTables.bootstrap.min.css">
<link rel="stylesheet"
	href="<c:url value="/trainerAssets/css/Pretty-Search-Form.css" />">
<link rel="stylesheet"
	href="<c:url value="/trainerAssets/css/MUSA_no-more-tables-1.css" />">
<link rel="stylesheet"
	href="<c:url value="/trainerAssets/css/MUSA_no-more-tables.css" />">
<link rel="stylesheet"
	href="<c:url value="/trainerAssets/css/Profile-Picture-With-Badge-1.css" />">
<link rel="stylesheet"
	href="<c:url value="/trainerAssets/css/Profile-Picture-With-Badge.css" />">
<link rel="stylesheet"
	href="<c:url value="/trainerAssets/css/styles.css" />">
<link rel="stylesheet"
	href="<c:url value="/trainerAssets/css/Table-With-Search-1.css" />">
<link rel="stylesheet"
	href="<c:url value="/trainerAssets/css/Table-With-Search.css" />">
<style type="text/css">
a.disable {
	pointer-events: none;
	cursor: default;
}
</style>
</head>
<body class="text-monospace text-nowrap">
	<header>
		<div class="d-xl-flex flex-shrink-1 flex-fill">
			<img
				class="d-xl-flex justify-content-xl-center align-items-xl-center"
				src="<c:url value="/trainerAssets/img/banner.jpg" />" width="100%">
		</div>
		<div>
			<nav class="navbar navbar-light navbar-expand-md navigation-clean"
				style="padding: 5px; background-color: rgb(255, 255, 255);">
				<div class="container">
					<a class="navbar-brand" href="#" style="color: #213d30;"><img
						src="<c:url value="/trainerAssets/img/logo.png" />"
						style="width: 150px; height: 100px;"></a>
					<button class="navbar-toggler" data-toggle="collapse"
						data-target="#navcol-1">
						<span class="sr-only">Toggle navigation</span><span
							class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navcol-1">
						<ul class="nav navbar-nav ml-auto">
							<li class="nav-item" role="presentation"><a
								class="nav-link active shadow ${trainer.verify ==0 ? 'disable':''}"
								href="<c:url value="/trainer/trainer-Home" />">Dashboard </a></li>
							<li class="nav-item" role="presentation"><a
								class="nav-link shadow ${trainer.verify ==0 ? 'disable':''}"
								href="<c:url value="/trainer/trainer-Manage-RequestCourse" />">Manage
									Course</a></li>
							<li class="nav-item dropdown"><a
								class="dropdown-toggle nav-link shadow" data-toggle="dropdown"
								aria-expanded="false" href="#" style="padding: 10px;">
									${trainer.fullName} <img
									src="<c:url value="${trainer.avatar}" />"
									style="height: 30px; padding: 0; margin-left: 5px; width: 30px;">
							</a>
								<div class="dropdown-menu" role="menu">
									<a class="dropdown-item ${trainer.verify ==0 ? 'disable':''}"
										role="presentation"
										href="${pageContext.request.contextPath}/trainer/viewProfile">
										Profile <i class="fa fa-address-book-o float-right"
										id="logouticon"
										style="margin: 0px; width: 19px; height: 19px;"></i>
									</a> <a class="dropdown-item" role="presentation"
										href="<c:url value="/guest/logout" />"> Logout <i
										class="fa fa-user-times float-right" id="logouticon"
										style="width: 19px; height: 19px; margin: 0px;"></i>
									</a>

								</div></li>
						</ul>
					</div>
				</div>
			</nav>
		</div>
	</header>
	<div class="container">
		<c:if test="${trainer.verify == 0}">
			<h1 style="color: red;">Please wait admin verify!!!</h1>
		</c:if>
		<c:set var="dto" value="${traineeProfile}" />
		<div
			class="table-responsive table-bordered bg-light border rounded border-dark shadow-lg"
			style="margin: 5px 0px;">
			<table class="table table-striped table-bordered table-sm">
				<thead>
					<tr>
						<th><img src="<c:url value="${dto.avatar}"/>"
							style="width: 50px; height: 50px;"></th>
						<th style="height: 50px; margin: 10px 0px;"><span
							class="float-left"> ${dto.fullName} </span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Day Of Birth</td>
						<td class="d-block">${dto.dayOfBirth}</td>
					</tr>
					<tr>
						<td>Gender</td>
						<td>${dto.gender ==1?'Male':'Female'}</td>
					</tr>
					<tr>
						<td>Email</td>
						<td>${dto.email}</td>
					</tr>
					<tr>
						<td>Zalo</td>
						<td>${dto.zalo}</td>
					</tr>
					<tr>
						<td>Facebook</td>
						<td>${dto.facebook}</td>
					</tr>
					<tr>
						<td>Phone</td>
						<td>${dto.phone}</td>
					</tr>
					<tr>
						<td>Address</td>
						<td>${dto.address}</td>
					</tr>
					<tr>
						<td colspan="2">
							<form action="GoBackToTrainerPageAction" method="post">
								<input type="hidden" name="actionName" value="${actionName}">
								<input type="hidden" name="subjectID" value="${subjectID}">
								<button class="btn btn-primary float-right" type="submit"
									style="width: 200px; background-color: #6b5e4f;"><<<
									Go Back</button>
							</form>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
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
</body>
</html>