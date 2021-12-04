<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<style>
input{
width: 100%;}
</style>
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
					<h2>Detail</h2>
				</div>


				<form:form method="POST" commandName="modelCourse">
					<div
						class="table-responsive table-bordered bg-light border rounded border-dark shadow"
						style="margin: 10px 0px; white-space: initial;">
						<table class="table table-striped table-bordered table-sm">
							<thead>
							</thead>
							<tbody>
								<tr>
									<td>Name</td>
									<td>${trainee.fullName}</td>
								</tr>
								<tr>
									<td>Title</td>
									<td><form:input type="text" path="title"
											disabled="${not empty modelCourse.title ? 'true':'false' }" /></td>
								</tr>
								<tr>
									<td>Subject</td>
									<td><form:select class="form-control" path="subjectID"
											disabled="${ fn:contains(listSubjectID,modelCourse.subjectID) ? 'true':'false' }">
											<optgroup label="Choose one subject">
												<c:forEach items="${listSubject}" var="subject">
													<option value="${subject.subjectID}"
														${subject.subjectID == modelCourse.subjectID? 'selected':'' }>${subject.subject}</option>
												</c:forEach>
											</optgroup>
										</form:select></td>
								</tr>
								<tr>
									<td>Description</td>
									<td><form:textarea style="width:100%" name="" id="" cols="30" rows="10"
											disabled="${not empty modelCourse.description ? 'true':'false' }"
											path="description"></form:textarea></td>
								</tr>
								<tr>
									<td>Day</td>
									<td><c:choose>

											<c:when test="${modelCourse.dayInWeek == 0}">
												<form:select class="form-control" path="dayInWeek">
													<optgroup label="Choose one subject">
														<option value="1">Mon Web Fri</option>
														<option value="2">Tue Thu Sat</option>
													</optgroup>
												</form:select>
											</c:when>
											<c:otherwise>
												<form:select class="form-control" path="dayInWeek"
													disabled="true">
													<optgroup label="Choose one subject">
														<option value="1"
															${modelCourse.dayInWeek == 1? 'selected':'' }>Mon
															Web Fri</option>
														<option value="2"
															${modelCourse.dayInWeek == 2? 'selected':'' }>Tue
															Thu Sat</option>
													</optgroup>
												</form:select>
											</c:otherwise>
										</c:choose></td>
								</tr>
								<tr>
									<td>Slot</td>
									<td><c:choose>
											<c:when test="${modelCourse.timeInDay == 0 }">
												<form:select class="form-control" path="timeInDay">
													<optgroup label="Choose one subject">
														<option value="1">Morning</option>
														<option value="2">Aternoon</option>
														<option value="3">Evening</option>
													</optgroup>
												</form:select>
											</c:when>
											<c:otherwise>
												<form:select class="form-control" path="timeInDay"
													disabled="true">
													<optgroup label="Choose one subject">
														<option value="1"
															${modelCourse.timeInDay == 1? 'selected': '' }>Morning</option>
														<option value="2"
															${modelCourse.timeInDay == 2? 'selected': '' }>Evening</option>
														<option value="3"
															${modelCourse.timeInDay == 3? 'selected': '' }>Night</option>
													</optgroup>
												</form:select>
											</c:otherwise>
										</c:choose></td>

								</tr>
								<tr>
									<td>Address</td>
									<td>${trainee.address}</td>
								</tr>
								<tr>
									<td>Deposit</td>
									<td><form:input path="deposit"
											disabled="${not empty modelCourse.title ? 'true':'false' }" /></td>
								</tr>
							</tbody>
						</table>

					</div>

					<button type="submit" class="btn btn-primary">Accept</button>
					<a
						href="${pageContext.request.contextPath}/admin/DenyCoursePost?id=${modelCourse.courseID}"
						class="btn btn-primary">Deny</a>
					<a
						href="${pageContext.request.contextPath}/admin/ListRequestVerifyPost"
						class="btn btn-primary">Cancel</a>
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