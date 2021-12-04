<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>COURSE</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
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
								class="nav-link shadow" href="trainee-Home">DASHBOARD </a></li>
							<li class="nav-item" role="presentation"><a
								class="nav-link active shadow" href="newPost">New Post</a></li>
						</ul>
						<ul class="nav navbar-nav ml-auto">
							<li class="nav-item dropdown show"><a
								class="dropdown-toggle nav-link shadow" data-toggle="dropdown"
								aria-expanded="true" href="#" style="padding: 10px;">${traineeInfo.fullName}
									<img src="<c:url value="${traineeInfo.avatar}"/>"
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
			<div class="row">
			
							<main class="page projects-page">
					<section class="portfolio-block projects-cards">
						<h1
							style="font-size: 30px; margin-bottom: 0px; margin-top: 0px; color: rgb(197, 102, 102); text-align: center;">
							The course is waiting</h1>
						<div class="container">
							<div class="col-md-12 search-table-col"
								style="margin-bottom: 0px; margin-top: 24px;">

								<span class="counter pull-right"></span>
								<div
									class="table-responsive table-bordered table table-hover table-bordered results">
									<table class="table table-bordered table-hover">
										<thead class="bill-header cs">
											<tr>
												<th id="trs-hd" class="col-lg-2" style="width: 300px;">NO.</th>
												<th id="trs-hd" class="col-lg-3" style="width: 300px;">Subject</th>
												<th id="trs-hd" class="col-lg-3" style="width: 300px;">Title</th>
												<th id="trs-hd" class="col-lg-3" style="width: 300px;">Create Date Post</th>
												<th id="trs-hd" class="col-lg-2" style="width: 300px;">Day</th>
												<th id="trs-hd" class="col-lg-2" style="width: 300px;">Slot</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="courseWaiting" items="${listCustomCourseWaitng}"
												varStatus="counter">
												<tr>
													<td>${counter.count}</td>
													<td>${courseWaiting.subject}</td>
													<td>${courseWaiting.title}</td>
													<td>${courseWaiting.createDate}</td>
													<td>${courseWaiting.dayInWeek}</td>
													<td>${courseWaiting.timeInDay}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</section>
				</main>

				<main class="page projects-page">
					<section class="portfolio-block projects-cards">
						<h1
							style="font-size: 30px; margin-bottom: 0px; margin-top: 0px; color: rgb(197, 102, 102); text-align: center;">
							The course is studying</h1>
						<div class="container">
							<div class="col-md-12 search-table-col"
								style="margin-bottom: 0px; margin-top: 24px;">

								<span class="counter pull-right"></span>
								<div
									class="table-responsive table-bordered table table-hover table-bordered results">
									<table class="table table-bordered table-hover">
										<thead class="bill-header cs">
											<tr>
												<th id="trs-hd" class="col-lg-2" style="width: 300px;">NO.</th>
												<th id="trs-hd" class="col-lg-3" style="width: 300px;">Subject</th>
												<th id="trs-hd" class="col-lg-3" style="width: 300px;">Title</th>
												<th id="trs-hd" class="col-lg-2" style="width: 300px;"><br>Deposit<br></th>
												<th id="trs-hd" class="col-lg-2" style="width: 300px;">Day</th>
												<th id="trs-hd" class="col-lg-2" style="width: 300px;">Slot</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="courseBegin" items="${listCustomCourseBegin}"
												varStatus="counter">
												<tr>
													<td>${counter.count}</td>
													<td>${courseBegin.subject}</td>
													<td>${courseBegin.title}</td>
													<td>${courseBegin.deposit}</td>
													<td>${courseBegin.dayInWeek}</td>
													<td>${courseBegin.timeInDay}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</section>
				</main>

				<main class="page projects-page">
					<section class="portfolio-block projects-cards">
						<h1
							style="font-size: 30px; margin-bottom: 0px; margin-top: 0px; color: rgb(197, 102, 102); text-align: center;">
							The course has ended</h1>
						<div class="container">
							<div class="col-md-12 search-table-col"
								style="margin-bottom: 0px; margin-top: 24px;">

								<span class="counter pull-right"></span>
								<div
									class="table-responsive table-bordered table table-hover table-bordered results">
									<table class="table table-bordered table-hover">
										<thead class="bill-header cs">
											<tr>
												<th id="trs-hd" class="col-lg-2" style="width: 300px;">No.</th>
												<th id="trs-hd" class="col-lg-3" style="width: 300px;">Subject</th>
												<th id="trs-hd" class="col-lg-3" style="width: 300px;">Title</th>
												<th id="trs-hd" class="col-lg-2" style="width: 300px;"><br>Deposit<br></th>
												<th id="trs-hd" class="col-lg-2" style="width: 300px;">Day</th>
												<th id="trs-hd" class="col-lg-2" style="width: 300px;">Slot</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="courseDone" items="${listCustomCourseDone}"
												varStatus="counter">
												<tr>
													<td>${counter.count}</td>
													<td>${courseDone.subject}</td>
													<td>${courseDone.title}</td>
													<td>${courseDone.deposit}</td>
													<td>${courseDone.dayInWeek}</td>
													<td>${courseDone.timeInDay}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</section>
				</main>

			</div>

		</div>
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
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
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