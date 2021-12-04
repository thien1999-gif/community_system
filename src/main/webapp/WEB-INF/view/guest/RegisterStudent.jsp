<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
<title>Title</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="style.css">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="<c:url value="/guestAssets/css/style_register_student.css" />">
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-md-10 offset=md-1 "></div>
		</div>
		<div class="row dong2">
			<div class="col-md-5 bentrai">
				<h3>Welcome Student</h3>
				<p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Est
					libero ut quasi optio nam iste accusantium laboriosam commodi,
					culpa illo impedit totam nihil, nostrum assumenda, eaque similique
					earum tempore obcaecati.</p>
				<button type="button" class="btn btn-primary">About Us</button>
			</div>
			<div class="col-md-7 benphai">
				<h2>Registration Student</h2>
				<form action="" id="registration_form" method="POST">
					<div class="form-group">
						<label for="">Email</label> <input type="email"
							class="form-control" name="" id="email"
							aria-describedby="emailHelpId" placeholder="example@gmail.com">
						<small id="email_error_message" class="form-text text-muted"></small>
					</div>
					<div class="form-group">
						<label for="">Password</label> <input type="text"
							class="form-control" name="" id="password"
							placeholder="Your Password"> <small
							id="password_error_message" class="form-text text-muted"></small>
					</div>
					<div class="form-group">
						<label for="">Confirm</label> <input type="text"
							class="form-control" name="" id="confirm_password"
							placeholder="Confirm password"> <small
							id="confirm_password_error_message" class="form-text text-muted"></small>
					</div>
					<div class="form-group">
						<label for="">FullName</label> <input type="text"
							class="form-control" name="" id="fullname"
							aria-describedby="helpId" placeholder=""> <small
							id="fullname_error_message" class="form-text text-muted"></small>
					</div>
					<div class="form-group">
						<label for="">Gender</label> <select class="form-control" name=""
							id="">
							<option>---Orther---</option>
							<option>Female</option>
							<option>Male</option>
						</select>
					</div>
					<div class="form-group">
						<label for="">Address</label> <input type="text"
							class="form-control" name="" id="" aria-describedby="helpId"
							placeholder="">

					</div>
					<div class="form-group">
						<label for="">Date of Birth</label> <input type="date"
							class="form-control" name="" id="" aria-describedby="helpId"
							placeholder="">

					</div>

					<div class="form-group">
						<label for="">Phone</label> <input type="text"
							class="form-control" name="" id="phone" aria-describedby="helpId"
							placeholder="(012)1234567"> <small
							id="phone_error_message" class="form-text text-muted"></small>
					</div>
					<div class="form-group">
						<label for="">Zalo</label> <input type="text" class="form-control"
							name="" id="" aria-describedby="helpId"
							placeholder="https://www.zalo.me/">

					</div>
					<div class="form-group">
						<label for="">Facebook</label> <input type="text"
							class="form-control" name="" id="" aria-describedby="helpId"
							placeholder="https://www.facebook.com/">

					</div>
					<label for="">Avatar </label>
					<div class="custom-file">
						<input type="file" class="custom-file-input"
							id="validatedCustomFile" required> <label
							class="custom-file-label" for="validatedCustomFile">Choose
							file...</label>
					</div>
					<br> <br>


					<button type="submit" class="btn btn-primary nutsubmit">Submit</button>
					<button type="button" class="btn btn-primary nutsubmit nutCancel">Cancel</button>
				</form>
			</div>
		</div>
	</div>



	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
		<script src="<c:url value="/guestAssets/js/script_register_student.js" />"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
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