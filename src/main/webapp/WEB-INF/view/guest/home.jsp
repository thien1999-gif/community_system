<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Community System</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<%-- <link rel="stylesheet"
	href="<c:url value="/adminAssets/bootstrap/css/bootstrap2.min.css" />"> --%>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">


<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
/* Remove the navbar's default margin-bottom and rounded borders */
.navbar {
	margin-bottom: 0;
	border-radius: 0;
}

.mySlides {
	display: none;
	text-align: center;
	width: 100%;
}

.text {
	color: #f2f2f2;
	font-size: 20px;
	font-weight: 20px;
	padding: 8px 12px;
	position: absolute;
	bottom: 8px;
	width: 100%;
	text-align: left;
}
</style>
</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<img
					src="https://cdn.shopify.com/s/files/1/0141/3024/9828/products/image_53401ebd-940a-4191-8687-3470ba9d0c78.png?v=1585743240"
					height="50px" width="50px">
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
					<!-- <li><a href="#">About</a></li> -->
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="formLogin"><span
							class="glyphicon glyphicon-log-in"></span> Login</a></li>
					<li><a
						href="${pageContext.request.contextPath}/guest/showFormRegisterStudent"><span
							class="glyphicon glyphicon-log-in"></span> Register for student</a></li>
					<li><a
						href="${pageContext.request.contextPath}/guest/showFormRegisterTeacher"><span
							class="glyphicon glyphicon-log-in"></span> Register for teacher</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="jumbotron">
		<div class="container">
			<div id="myCarousel" class="carousel slide" data-ride="carousel">
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					<li data-target="#myCarousel" data-slide-to="1"></li>
					<li data-target="#myCarousel" data-slide-to="2"></li>
				</ol>

				<!-- Wrapper for slides -->
				<div class="carousel-inner">
					<div class="item active">
						<img
							src="https://d1plicc6iqzi9y.cloudfront.net/sites/default/files/default_images/trung-tam-gia-su.jpg"
							alt="image_1" style="width: 100%;">
					</div>

					<div class="item">
						<img
							src="https://d1plicc6iqzi9y.cloudfront.net/sites/default/files/imagebla/english/be-hoc-tieng-anh.jpg"
							alt="image_2" style="width: 100%;">
					</div>

					<div class="item">
						<img
							src="https://d1plicc6iqzi9y.cloudfront.net/sites/default/files/imagebla/english/bang-chu-cai-tieng-anh.jpg"
							alt="image_3" style="width: 100%;">
					</div>
				</div>

				<!-- Left and right controls -->
				<a class="left carousel-control" href="#myCarousel"
					data-slide="prev"> <span
					class="glyphicon glyphicon-chevron-left"></span> <span
					class="sr-only">Previous</span>
				</a> <a class="right carousel-control" href="#myCarousel"
					data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right"></span> <span
					class="sr-only">Next</span>
				</a>
			</div>
		</div>
	</div>

	<div class="container-fluid bg-3 text-center">
		<h3>Top 4 teachers</h3>
		<br>
		<div class="row">
			<c:forEach var="topTrainer" items="${topTrainer}">
				<div class="col-sm-3">
					<img
						style="width: 50%; height: 150px; margin: 50px auto; text-align: center;"
						src="<c:url value="${topTrainer.avatar}"/>" class="img-responsive"
						style="width: 100%" alt="Image">
					<p>Tên giáo viên: ${topTrainer.fullName}</p>
					<p>Lương cơ bản: ${topTrainer.salaryPerHour}/hour</p>
					<p>Đánh giá bởi học sinh: ${topTrainer.rating}/5</p>
				</div>
			</c:forEach>


		</div>
		<br>
	</div>
	<div class="w3-content w3-display-container">
		<table class="mySlides">
			<tr>
				<th style="width: 50%;"><img
					src='<c:url value="/guestAssets/img/FPTU.jpg"></c:url>'
					style="width: 100%; height: 300px; text-align: center;">
					<div class="text">School</div></th>
				<th style="width: 50%;"><img
					src='<c:url value="/guestAssets/img/FPTU2.jpg"></c:url>'
					style="width: 100%; height: 300px; text-align: center;">
					<div class="text">School yard</div></th>
			</tr>
		</table>
		<table class="mySlides">
			<tr>
				<th style="width: 50%;"><img
					src='<c:url value="/guestAssets/img/HUNG2.jpg"></c:url>'
					style="width: 100%; height: 300px; text-align: center;">
					<div class="text">Founder Mr.Phan Phu Hung</div></th>
				<th style="width: 50%;"><img
					src='<c:url value="/guestAssets/img/TRUONG.jpg"></c:url>'
					style="width: 100%; height: 300px; text-align: center;">
					<div class="text">Founder Mr.Ha Nguyen Truong</div></th>
			</tr>
		</table>

		<table class="mySlides">
			<tr>
				<th style="width: 50%;"><img
					src='<c:url value="/guestAssets/img/THIEN2.jpg"></c:url>'
					style="width: 100%; height: 300px; text-align: center;">
					<div class="text">Principal Mr.Hoang Thien</div></th>
				<th style="width: 50%;"><img
					src='<c:url value="/guestAssets/img/hieu.jpg"></c:url>'
					style="width: 100%; height: 300px; text-align: center;">
					<div class="text">Vice Principal Mr. Bui Hieu</div></th>
			</tr>
		</table>
		<table class="mySlides">
			<tr>
				<th style="width: 50%;"><img
					src='<c:url value="/guestAssets/img/thuvien2.png"></c:url>'
					style="width: 100%; height: 300px; text-align: center;">
					<div class="text">Libary</div></th>
				<th style="width: 50%;"><img
					src='<c:url value="/guestAssets/img/thuvien.png"></c:url>'
					style="width: 100%; height: 300px; text-align: center;">
					<div class="text"></div></th>
			</tr>
		</table>
		<table class="mySlides">
			<tr>
				<th style="width: 50%;"><img
					src='<c:url value="/guestAssets/img/class.jpg"></c:url>'
					style="width: 100%; height: 300px; text-align: center;">
					<div class="text">Class room</div></th>
				<th style="width: 50%;"><img
					src='<c:url value="/guestAssets/img/class2.jpg"></c:url>'
					style="width: 100%; height: 300px; text-align: center;">
					<div class="text"></div></th>
			</tr>
		</table>



		<button class="w3-button w3-black w3-display-left"
			onclick="plusDivs(-1)">&#10094;</button>
		<button class="w3-button w3-black w3-display-right"
			onclick="plusDivs(1)">&#10095;</button>
	</div>

	<script>
		var slideIndex = 1;
		showDivs(slideIndex);

		function plusDivs(n) {
			showDivs(slideIndex += n);
		}

		function showDivs(n) {
			var i;
			var x = document.getElementsByClassName("mySlides");
			if (n > x.length) {
				slideIndex = 1
			}
			if (n < 1) {
				slideIndex = x.length
			}
			for (i = 0; i < x.length; i++) {
				x[i].style.display = "none";
			}
			x[slideIndex - 1].style.display = "block";
		}
	</script>
	<br>
	<br>
</body>

<footer>
	<div class="container" style="text-align: center; margin:50px auto;">
		<div class="row justify-content-center"
			style="text-align: center; margin-left: auto;">
			<div class="col-sm-4 col-md-3 item" style="width: 33%; text-align: center;">
				<h3>Services</h3>
				<ul style="text-align: left;margin-left: 30%;">
					<li><a href="#">Web design</a></li>
					<li><a href="#">Development</a></li>
					<li><a href="#">Hosting</a></li>
				</ul>
			</div>
			<div class="col-sm-4 col-md-3 item" style="width: 33%; text-align: center;">
				<h3>About</h3>
				<ul style="width: 33%; text-align: left;margin-left: 30% ;">
					<li><a href="#">Company</a></li>
					<li><a href="#">Team</a></li>
					<li><a href="#">Legacy</a></li>
				</ul>
			</div>
			<div class="col-sm-4 col-md-3 item" style="width: 33%; text-align: center;">
				<h3>Careers</h3>
				<ul style="text-align: left;margin-left: 30%;">
					<li><a href="#">Job openings</a></li>
					<li><a href="#">Employee success</a></li>
					<li><a href="#">Benefits</a></li>
				</ul>
			</div>
		</div>
	</div>
</footer>

</html>
