<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
<head>
<title>Title</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->

<link rel="stylesheet"
	href="<c:url value="/guestAssets/css/style_login.css" />">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-7">
				<div id="carouselExampleControls" class="carousel slide"
					data-ride="carousel">
					<div class="carousel-inner">
						<div class="carousel-item active">
							<img class="d-block w-100"
								src="https://cnet2.cbsistatic.com/img/dA4SzQUk7rBWYXmV5x5nbuCuQ8Q=/1200x675/2020/01/13/6e566b3e-faef-4ffd-9909-01acca997b6d/windows-10-cropped-for-promo.png"
								alt="First slide">
						</div>
						<div class="carousel-item">
							<img class="d-block w-100"
								src="https://cnet2.cbsistatic.com/img/dA4SzQUk7rBWYXmV5x5nbuCuQ8Q=/1200x675/2020/01/13/6e566b3e-faef-4ffd-9909-01acca997b6d/windows-10-cropped-for-promo.png"
								alt="Second slide">
						</div>
						<div class="carousel-item">
							<img class="d-block w-100"
								src="https://cnet2.cbsistatic.com/img/dA4SzQUk7rBWYXmV5x5nbuCuQ8Q=/1200x675/2020/01/13/6e566b3e-faef-4ffd-9909-01acca997b6d/windows-10-cropped-for-promo.png"
								alt="Third slide">
						</div>
					</div>
					<a class="carousel-control-prev" href="#carouselExampleControls"
						role="button" data-slide="prev"> <span
						class="carousel-control-prev-icon" aria-hidden="true"></span> <span
						class="sr-only">Previous</span>
					</a> <a class="carousel-control-next" href="#carouselExampleControls"
						role="button" data-slide="next"> <span
						class="carousel-control-next-icon" aria-hidden="true"></span> <span
						class="sr-only">Next</span>
					</a>
				</div>
			</div>
			<div class="col-md-5">
				<form action="loginProcess" method="POST" id="login_form">
					<c:if test="${param.error!=null }">
						<i style="color: red"> Sorry you entered invalid username or
							password!</i>
					</c:if>
					<c:if test="${deleteStatus == 2}">
					<i style="color: red"> Account was banned ! Please contact admin by email: admin@gmail.com</i>
					</c:if>
					<div class="form-group">
						<label for="">Email</label> <input type="email"
							class="form-control" name="userEmail" id="email"
							aria-describedby="emailHelpId" placeholder="example@gmail.com">
						<small id="email_error_message" class="form-text text-muted"></small>
					</div>
					<div class="form-group">
						<label for="">Password</label> <input type="password"
							class="form-control" name="password" id="password"
							placeholder="Your Password"> <small
							id="password_error_message" class="form-text text-muted"></small>
					</div>
					<input type="hidden" name="remember-me" value="true" />
					<button type="submit" class="btn btn-primary">Submit</button>
					<a class="btn btn-warning"
						href="${pageContext.request.contextPath}/guest/home">Go back home</a> <a
						class="btn btn-dark" href="${pageContext.request.contextPath}/guest/forgot-password">Forgot password?</a>
				</form>
				<!-- 				<h3 style="text-align: center;">Or</h3>
				<div class="social-media">
					<ul>
						<li><img src="images/facebook.png"></li>

					</ul>
				</div> -->
			</div>
		</div>




	</div>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script src="<c:url value="/guestAssets/js/script_login.js" />"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>