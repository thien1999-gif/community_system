<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Projects - Brand</title>

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

<style>
.portfolio-block form {
    max-width: 100%;
    padding: 20px;
    margin: auto;
    box-shadow: 0 2px 10px rgba(0,0,0,.1);
}

</style>
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
							style="width: 30px; height: 30px;">
							${profileAdmin.fullName}
					</a>
						<div class="dropdown-menu" role="menu">
							<a class="dropdown-item" role="presentation"
								href="${pageContext.request.contextPath}/admin/AdminInfo">Profile</a>
							<a class="dropdown-item" role="presentation"
								href="<c:url value="/guest/logout"/>">Sign out</a>
						</div></li>
				</ul>
			</div>
		</div>
	</nav>
	<main class="page projects-page">
		<div class="stylecss" style="height: 100px;">
			<c:if test="${!status.isEmpty()}">
				<p class="loop">
					<strong style="color: Green">${status}</strong>
				</p>
			</c:if>
		</div>
		<section class="portfolio-block projects-cards"
			style="text-align: center;">
			<h1
				style="font-size: 30px; margin-bottom: 53px; margin-top: 0px; color: rgb(197, 102, 102); margin-right: 0px; margin-left: 30px; padding-top: 0px;">
				LIST COURSE</h1>
			<div class="container">
				<!-- 	Form Search + Form Add		 -->
				<div class="row">
					<div class="col-lg-6">
						<h2>Add New Course</h2>
						<form action="addNewCourse">
							<input type="text" name="addCourseValue" required="required"
								minlength="1">
							<button class="btn btn-primary" type="submit"
								style="width: 100px;">Add</button>
						</form>
					</div>
					<div class="col-lg-6">
						<h2>Search Course</h2>
						<form action="searchCourse">
							<input type="text" name="searchCourseValue" required="required"
								minlength="1">
							<button class="btn btn-primary" type="submit"
								style="width: 100px;">Search</button>
						</form>
					</div>
				</div>
				<div>
					<c:if test="${!list.isEmpty()}">
						<h2>List of Subjects</h2>
						<form action="deleteAllSubject" method="POST" >
						<table class="table table-hover table-bordered results"
							style="text-align: left; margin-top: 10px;">
							<thead>
								<tr>
									<th>Checkbox</th>
									<th>#</th>
									<th class="col-md-4 col-xs-4">Course name</th>
									<th class="col-md-3 col-xs-3">Action</th>
								</tr>
								<tr class="warning no-result">
									<td colspan="4"><i class="fa fa-warning"></i> No result</td>
								</tr>
							</thead>

							<tbody>
								<c:forEach var="subjectObj" items="${list}" varStatus="counter">
									<!-- 		Form Delete -->
									
										<tr>
											<td><input type = "checkbox" name=IDsubject value="${subjectObj.subjectID}"></td>
											<td scope="row">${counter.count}</td>
											<td><input style="width: 100%; height:100%;" type="text" id="subjectName${counter.count}" name="subjectName"
												value="${subjectObj.subject}"></td>
											<td><input type="hidden" name="subjectID"
												value="${subjectObj.subjectID}">
												<a class="btn btn-primary"  id="deleteSubject" href="deleteSubject?subjectId=${subjectObj.subjectID}"
													style="font-size: 12px; background-color: rgb(245, 28, 14);">
													<i class="far fa-trash-alt" style="font-size: 14px;"></i>
												</a>
												
												<a class="btn btn-primary" id="updateSubject${counter.count}" 	href="#"
													style="font-size: 12px; background-color: green;">
													<i class="fa fa-check" aria-hidden="true"></i>
												</a>												
												
												
												</td>
										</tr>
								<script>
							    $("#subjectName${counter.count}").keyup(function (e) { 
							    	$("#updateSubject${counter.count}").prop('href', 'updateSubject?subjectId=${subjectObj.subjectID}&subjectName='+encodeURIComponent(this.value))
							         }); 
								
								</script>
											
								</c:forEach>
							</tbody>
						</table>
																
												<button class="btn btn-primary"  type="submit"
													> Delete by checkbox
													<i class="far fa-trash-alt" style="font-size: 14px;"></i>
												</button>
												
							</form>
					</c:if>
					<c:if test="${list.isEmpty()}">
						<h2>Empty Record</h2>
					</c:if>
				</div>
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