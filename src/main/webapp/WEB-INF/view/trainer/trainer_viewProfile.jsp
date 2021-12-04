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
<title>trainer dashboard</title>

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
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<style type="text/css">
input {
	width: 100%;
}

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
	z-index: 1000;
	top: 0;
}
</style>

</head>

<body class="text-monospace text-nowrap">
	<header>
		<div class="d-xl-flex flex-shrink-1 flex-fill">
			<img
				class="d-xl-flex justify-content-xl-center align-items-xl-center"
				src="<c:url value="/trainerAssets/img/banner.jpg"/>" width="100%">
		</div>
		<div>
			<nav class="navbar navbar-light navbar-expand-md navigation-clean"
				style="padding: 5px; background-color: rgb(255, 255, 255);">
				<div class="container">
					<a class="navbar-brand" href="#" style="color: #213d30;"><img
						src="<c:url value="/trainerAssets/img/logo.png"/>"
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
								href="<c:url value="/trainer/trainer-Home"/>">Dashboard </a></li>
							<li class="nav-item" role="presentation"><a
								class="nav-link shadow ${trainer.verify ==0 ? 'disable':''}"
								href="<c:url value="/trainer/trainer-Manage-RequestCourse"/>">Manage
									Course</a></li>
							<li class="nav-item dropdown show"><a
								class="dropdown-toggle nav-link shadow" data-toggle="dropdown"
								aria-expanded="true" href="#" style="padding: 10px;">${trainerProfile.fullName}<img
									src="<c:url value="${trainerProfile.avatar}"/>"
									style="height: 30px; padding: 0; margin-left: 5px; width: 30px;"></a>
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
		<!-- Data Form -->
		<h1 style="text-align: center">Trainer View Profile</h1>
		<form:form id="viewTrainerProfile" action=""
			commandName="trainerProfile" method="POST"
			enctype="multipart/form-data">
			<div class="row justify-content-md-center">
				<div class="col-lg-auto col-md-auto col-sm-auto">
					<div style="text-align: center;">
						<img src="<c:url value="${trainerProfile.avatar}"/>"
							style="width: 100px; height: 100px;">
					</div>
					<div style="text-align: center; margin-top: 5px;">
						<form:input id="avatar" path="profileImage" type="file" />
					</div>
					<div
						class="table-responsive table-bordered bg-light border rounded border-dark shadow"
						style="margin: 10px 0px; white-space: initial;">
						<table class="table table-striped table-bordered table-sm">
							<thead>
							</thead>
							<tbody>
								<tr>
									<td>Name</td>
									<td><form:input id="fullname" required="true"
											path="fullName" disabled="${watting}" /> <small
										id="fullname_error_message" class="form-text text-muted"></small>
									</td>
								</tr>
								<tr>
									<td>Email</td>
									<td><form:input path="email" disabled="true" /></td>
								</tr>
								<tr>
									<td>Gender</td>
									<td><form:select class="form-control" path="gender"
											disabled="${watting}">
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
									<td><form:input type="date" path="dayOfBirth"
											disabled="${watting}" /> <small id="date_error_message"
										class="form-text text-muted"></small></td>
								</tr>
								<tr>
									<td>Address</td>
									<td><form:input disabled="true" path="address" /> <%@ include
											file="../includes/address.jsp"%></td>
								</tr>
								<tr>
									<td>Graduation Year</td>
									<td><form:input type="number" required="true" min="1900"
											max="2300" path="graduationYear" disabled="${watting}" /></td>

								</tr>
								<tr>
									<td>Experience</td>
									<td><form:input required="true" path="experience"
											disabled="${watting}" /></td>

								</tr>
								<tr>
									<td>Salary Per Hour</td>
									<td><form:input id="salary" required="true" type="number"
											min="100000" path="salaryPerHour" disabled="${watting}" /> <small
										id="salary_error_message" class="form-text text-muted"></small>
									</td>

								</tr>

								<tr>
									<td>Facebook</td>
									<td><form:input path="facebook" disabled="${watting}" /></td>

								</tr>
								<tr>
									<td>Zalo</td>
									<td><form:input path="zalo" disabled="${watting}" /></td>

								</tr>

								<tr>
									<td>Phone</td>
									<td><form:input id="phone" required="true" type="number"
											path="phone" disabled="${watting}" /> <small
										id="phone_error_message" class="form-text text-muted"></small>
									</td>
								</tr>
							</tbody>
						</table>

						<input type="hidden" id="villages" name="numberAddress"
							value="null" /> <input type="hidden" id="wards" name="wards"
							value="null" /> <input type="hidden" id="districts"
							name="district" value="null" /> <input type="hidden" id="citys"
							name="city" value="null" /> <input type="hidden"
							name="addressHidden" value="${trainerProfile.address}" />

						<form:input type="hidden" path="avatar" />
						<form:input path="idCard" type="hidden" />
						<form:input path="resumeLink" type="hidden" />
						<form:input path="description" type="hidden" />
						<form:button type="submit" class="btn btn-primary"
							disabled="${watting}"> ${watting == true ? 'Watting for Update': 'Update'}</form:button>
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

	<c:if test="${trainerProfile.verify == 0}">

		<div id="pageLoading" class="pageLoading">
			<h1 style="color: red;">Please wait admin verify!!!</h1>
		</div>

	</c:if>


	<script
		src="<c:url value="/trainerAssets/js/CheckViewProfileTrainer.js" />"></script>
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