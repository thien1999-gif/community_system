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
<title>Trainer Trainee Request</title>

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

.pageLoading {
	text-align: center;
	background: #00000075;
	width: 100%;
	height: 100%;
	position: fixed;
	z-index: 100;
	top: 0;
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
		<div
			class="row d-xl-flex justify-content-xl-center align-items-xl-center"
			style="padding: 30px 0px;">
			<div class="col">
				<form
					class="d-flex d-md-flex justify-content-start justify-content-md-start justify-content-xl-center align-items-xl-center">
					<span><i
						class="fa fa-th-list border rounded d-flex d-sm-flex justify-content-center align-items-center justify-content-sm-center align-items-sm-center"
						style="height: 38px; width: 45px; background-color: #b5e8bf; font-size: 20px;"></i></span>
					<select id="trainerRequest"
						onchange="if (this.value) window.location.href=this.value"
						class="form-control">
						<optgroup label="Select option">
							<option
								value="<c:url value="/trainer/trainer-Manage-RequestCourse"/>">Requesting
								Course</option>
							<option
								value="<c:url value="/trainer/trainer-Manage-TeachingCourse"/>">Teaching
								Course</option>
							<option
								value="<c:url value="/trainer/trainer-Manage-TraineeRequest"/>"
								selected="">Trainee request</option>
							<option
								value="<c:url value="/trainer/trainer-Manage-EndedCourse"/>">Ended
								Course</option>
						</optgroup>
					</select>
				</form>
			</div>
		</div>
	</div>
	<div class="container">
		<div role="alert" class="alert alert-light text-center">
			<span style="font-size: 22px;"><strong>Trainee
					Requests</strong><br /></span>
		</div>
	</div>
	<div class="container">
		<div class="row" style="padding: 30px 0px;">
			<div class="col">
				<form
					class="d-flex d-md-flex justify-content-start justify-content-md-start justify-content-xl-center align-items-xl-center"
					method="post" action="trainer-Manage-TraineeRequest">
					<span> <i
						class="fa fa-book border rounded d-flex d-sm-flex justify-content-center align-items-center justify-content-sm-center align-items-sm-center"
						style="height: 38px; width: 45px; background-color: #b5e8bf; font-size: 20px;"></i>
					</span> <select name="subjectID" class="form-control">
						<optgroup label="Select object">
							<option value="0"
								<c:if test="${subjectList.subjectID == subjectID}">
                        			selected="selected"
                        		</c:if>>
								All subjects</option>

							<c:forEach var="subjectList" items="${trainerSubject}">
								<option value="${subjectList.subjectID}"
									<c:if test="${subjectList.subjectID == subjectID}">
                        					selected="selected"
                        				</c:if>>
									${subjectList.subject}</option>
							</c:forEach>
						</optgroup>
					</select>
					<button ${trainer.verify == 0? 'disabled':''}
						class="btn btn-primary" type="submit"
						style="background-color: #b5e8bf; color: rgb(0, 0, 0);">Search</button>
				</form>
			</div>
		</div>
	</div>
	<c:if test="${not empty updateMessage}">
		<div class="container">
			<div role="" class="alert alert-info">
				<span> <strong>Info:</strong> ${updateMessage}
				</span>
			</div>
		</div>
	</c:if>
	<c:if test="${not empty traineeRequestList}">
		<div class="container">
			<c:forEach var="list" items="${traineeRequestList}">
				<div
					class="table-responsive table-bordered bg-light border rounded border-dark shadow"
					style="margin: 5px 0px;">
					<table class="table table-striped table-bordered table-sm">
						<thead>
							<tr>
								<th><img src="<c:url value="${list.traineeAvatar}"/>"
									style="width: 50px; height: 50px;"></th>
								<th style="height: 50px; margin: 10px 0px;"><span
									class="float-left"> ${list.traineeName} </span>
									<form action="ViewTraineeProfile" method="post">
										<input type="hidden" name="actionName"
											value="trainer-Manage-TraineeRequest"> <input
											type="hidden" name="traineeID" value="${list.traineeID}">
										<input type="hidden" name="subjectID" value="${subjectID}">
										<button class="btn btn-primary float-right" type="submit"
											name="btAction" style="background-color: #008772;">
											View profile</button>
									</form></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Title</td>
								<td class="d-block">${list.title}</td>
							</tr>
							<tr>
								<td>Subject</td>
								<td>${list.subject}</td>
							</tr>
							<tr>
								<td>Description</td>
								<td>${list.description}</td>
							</tr>
							<tr>
								<td>Location</td>
								<td>${list.address}</td>
							</tr>
							<tr>
								<td>Deposit</td>
								<td>${list.deposit}</td>
							</tr>
							<tr>
								<td>Calendar</td>
								<td>${list.dayInWeek}- ${list.timeInDay}</td>
							</tr>
							<tr>
								<td colspan="2">
									<form action="AcceptTraineeRequestAction" method="post">
										<input type="hidden" name="courseID" value="${list.courseID}">
										<input type="hidden" name="subjectID" value="${subjectID}">
										<button class="btn btn-primary float-right" type="submit"
											style="width: 200px; background-color: green;"
											onclick="return confirm('Are you sure you want to accept this course?');">
											Accept this course</button>
									</form>
									<form action="DenyTraineeRequestAction" method="post">
										<input type="hidden" name="courseID" value="${list.courseID}">
										<input type="hidden" name="subjectID" value="${subjectID}">
										<button class="btn btn-primary float-right" type="submit"
											style="width: 200px; background-color: red;"
											onclick="return confirm('Are you sure you want to deny this course?');">
											Deny this course</button>
									</form>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</c:forEach>
		</div>
		<div class="container">
			<form action="trainer-Manage-TraineeRequest" method="post">
				<c:set var="current" value="${curPage}" />
				<c:set var="total" value="${totalPage}" />
				<c:set var="maxDisplay" value="${5}" />
				<input type="hidden" name="subjectID" value="${subjectID}">
				<nav
					class="d-flex d-sm-flex justify-content-center justify-content-sm-center">
					<input id="firstPage" class="page-item page-link" type="submit"
						name="curPage" value="<<"
		            onclick="
						getElementById('firstPage').value=1
						" ${current>1 ?'':'disabled=""'}>

					<c:set var="count"
						value="${(total <= maxDisplay or current - 2 <= 1) ? 
		            							1 : (total-2 >=current ? 
		            							current-2 : total-(maxDisplay-1))}" />
					<c:forEach var="pageNum" begin="1" step="1"
						end="${total < maxDisplay ? total : maxDisplay}">
						<input class="page-item page-link" type="submit" name="curPage"
							value="${count}" ${count==current ? 'style="color: red"':''}>
						<c:set var="count" value="${count + 1}" />
					</c:forEach>
					<input id="lastPage" class="page-item page-link" type="submit"
						name="curPage" value=">>"
						onclick="getElementById('lastPage').value = ${total}"
						${current<totalPage ?'':'disabled=""'}>
				</nav>
			</form>
		</div>
	</c:if>
	<c:if test="${empty traineeRequestList}">
		<div class="container">
			<div role="alert" class="alert alert-warning">
				<span> <strong>Alert:</strong> No request can be found!
				</span>
			</div>
		</div>
	</c:if>
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

	<c:if test="${trainer.verify == 0}">

		<div id="pageLoading" class="pageLoading">
			<h1 style="color: red;">Please wait admin verify!!!</h1>
		</div>

	</c:if>

	<script src="<c:url value="/trainerAssets/js/jquery.min.js"/>"></script>
	<script
		src="<c:url value="/trainerAssets/bootstrap/js/bootstrap.min.js"/>"></script>
	<script
		src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.10.15/js/dataTables.bootstrap.min.js"></script>
	<script src="<c:url value="/trainerAssets/js/Table-With-Search.js"/>"></script>
</body>

</html>