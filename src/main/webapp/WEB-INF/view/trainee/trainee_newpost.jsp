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
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<style>
.collapsible {
	cursor: pointer;
	padding: 18px;
	width: 100%;
}

.content {
	padding: 0px;
	max-height: 0;
	overflow: hidden;
	transition: max-height 0.2s ease-out;
}

.rating {
	padding-left: 90px;
	display: flex;
	transform: rotateY(180deg);
	position: relative;
}

.rating input {
	display: none;
}

.rating label {
	display: block;
	cursor: pointer;
	width: 50px;
}

.rating label:before {
	content: '\f005';
	font-family: fontAwesome;
	color: gray;
	display: block;
	position: relative;
	font-size: 50px;
}

.rating label:after {
	content: '\f005';
	font-family: fontAwesome;
	color: yellow;
	display: block;
	position: absolute;
	font-size: 50px;
	top: 0;
	opacity: 0;
	transition: 0.5s;
	text-shadow: 0 2px 5px rgba(0, 0, 0, .5);
}

.rating label:hover:after, .rating label:hover ~ label:after, .rating input:checked 
	 ~ label:after {
	opacity: 1;
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
		<c:if test="${listPost != null }">
			<c:if test="${!listPost.isEmpty()}">
				<div class="row justify-content-md-center"
					style="text-align: center;">
					<c:forEach var="post" items="${listPost}" varStatus="counter">
						<div class="col-lg-6 col-md-6 col-sm-6" style="margin-top: 15px;">
							<div>
								<div
									class="table-responsive table-bordered bg-light border rounded border-dark shadow"
									style="margin: 10px 0px;">
									<table class="table table-striped table-bordered table-sm">
										<thead>
										</thead>
										<tbody>
											<tr>
												<td>Email student</td>
												<td>${post.traineeID}</td>
											</tr>
											<tr>
												<td>Title</td>
												<td>${post.title}</td>
											</tr>
											<tr>
												<td>Description</td>
												<td><textarea disabled="disabled" style="width: 100%; height: 100%;" rows="10">${post.description}</textarea></td>
											</tr>
											<tr>
												<td>Time</td>
												<td>${post.timeInDay == 1? 'Mon-Wed-Fri':'Tue-Thu-Sat'}</td>
											</tr>
											<tr>
												<td>Deposit</td>
												<td>${post.deposit}</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</c:if>


		</c:if>
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
	<script>
		var coll = document.getElementsByClassName("collapsible");
		var i;

		for (i = 0; i < coll.length; i++) {
			coll[i].addEventListener("click", function() {
				this.classList.toggle("active");
				var content = this.nextElementSibling;
				if (content.style.maxHeight) {
					content.style.maxHeight = null;
				} else {
					content.style.maxHeight = content.scrollHeight + "px";
				}
			});
		}
	</script>
</body>

</html>