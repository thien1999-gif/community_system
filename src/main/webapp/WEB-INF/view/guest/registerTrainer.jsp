<%@page import="com.online.shop.dto.Village"%>
<%@page import="com.online.shop.dto.Ward"%>
<%@page import="com.online.shop.dto.District"%>
<%@page import="com.online.shop.dto.Province"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s"%>
<!doctype html>
<html lang="en">
<head>
<title>Community System</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="style.css">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="<c:url value="/guestAssets/css/style_register_teacher.css" />">
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	
<style type="text/css">
.custom-file-label{
overflow: hidden;
}
.container-for-checkbox { border:2px solid #ccc; width:auto; height: 100px; overflow-y: scroll; background-color: white ; color: black; }
.pageLoading{
	background: #00000075;
    width: 100%;
    height: 100%;
    position: fixed;
    z-index: 100;
    top: 0;
}


.lds-roller {
  position: fixed;
  width: 80px;
  height: 80px;
  top:50%;
  left:50%;
}
#phantram{
  position: relative;
  width: 80px;
  height: 80px;
  top:60%;
  left:50%;
  color:white;
}
.lds-roller div {
  animation: lds-roller 1.2s cubic-bezier(0.5, 0, 0.5, 1) infinite;
  transform-origin: 40px 40px;
}
.lds-roller div:after {
  content: " ";
  display: block;
  position: absolute;
  width: 7px;
  height: 7px;
  border-radius: 50%;
  background: #fff;
  margin: -4px 0 0 -4px;
}
.lds-roller div:nth-child(1) {
  animation-delay: -0.036s;
}
.lds-roller div:nth-child(1):after {
  top: 63px;
  left: 63px;
}
.lds-roller div:nth-child(2) {
  animation-delay: -0.072s;
}
.lds-roller div:nth-child(2):after {
  top: 68px;
  left: 56px;
}
.lds-roller div:nth-child(3) {
  animation-delay: -0.108s;
}
.lds-roller div:nth-child(3):after {
  top: 71px;
  left: 48px;
}
.lds-roller div:nth-child(4) {
  animation-delay: -0.144s;
}
.lds-roller div:nth-child(4):after {
  top: 72px;
  left: 40px;
}
.lds-roller div:nth-child(5) {
  animation-delay: -0.18s;
}
.lds-roller div:nth-child(5):after {
  top: 71px;
  left: 32px;
}
.lds-roller div:nth-child(6) {
  animation-delay: -0.216s;
}
.lds-roller div:nth-child(6):after {
  top: 68px;
  left: 24px;
}
.lds-roller div:nth-child(7) {
  animation-delay: -0.252s;
}
.lds-roller div:nth-child(7):after {
  top: 63px;
  left: 17px;
}
.lds-roller div:nth-child(8) {
  animation-delay: -0.288s;
}
.lds-roller div:nth-child(8):after {
  top: 56px;
  left: 12px;
}
@keyframes lds-roller {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}


</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-7 trai">
				<h1 class="text-left">Welcome Teacher</h1>
				<p class="text-left">This is the first opportunity you as the
					instructor have to address what the course is about. Take this time
					to briefly describe the goal of the course and to address any
					common misconceptions. This is an excellent opportunity to address
					how the course routine (i.e. content will be released each week or
					the whole course is open from the beginning).</p>
			</div>
			<div class="col-md-5">
				<div class="row">
					<div class="col-md-12">
						<h3 class="text-left">Register Teacher</h3>
					</div>
				</div>
				<hr>

				<s:form
					action="${pageContext.request.contextPath}/guest/saveTeacher"
					id="registration_form" method="POST" modelAttribute="file"
					enctype="multipart/form-data">
 					<div class="form-group">
						<label for="">Email</label> <input required type="email"
							class="form-control" name="email" id="email"
							aria-describedby="emailHelpId" placeholder="example@gmail.com">
						<small id="email_error_message" class="form-text text-muted"></small>
						<font color="red"> ${error} </font>
					</div>
					<div class="form-group">
						<label for="">Password</label> <input required type="password"
							class="form-control" name="password" id="password" value="${param.password}"
							placeholder="Your Password"> <small
							id="password_error_message" class="form-text text-muted"></small>
					</div>
					<div class="form-group">
						<label for="">Confirm</label> <input required type="password"
							class="form-control" name="confirm" id="confirm_password" value="${param.confirm}"
							placeholder="Confirm password"> <small
							id="confirm_password_error_message" class="form-text text-muted"></small>
					</div>
					<div class="form-group">
						<label for="">FullName</label> <input required type="text"
							class="form-control" name="fullName" id="fullname" value="${param.fullName}"
							aria-describedby="helpId" placeholder=""> <small
							id="fullname_error_message" class="form-text text-muted"></small>
					</div>
					<div class="form-group">
						<label for="">Subjects</label> 
						<div class="container-for-checkbox">
						<c:forEach var="listMon" items="${listSubject}" varStatus="counter">
							<input  type="checkbox" id="" name="subject" value="${listMon.subjectID}"/> ${listMon.subject} <br />
						</c:forEach>
						</div>
					</div>
					 <div class="form-group">
						<label for="">Gender</label> <select class="form-control"
							name="gender" id="">
							<optionval>---Orther---
							</option>
							<option value="2">Female</option>
							<option selected value="1">Male</option>
						</select>
					</div>
					<div class="form-group">
						<label for="">Date of Birth</label> <input required type="date"  value="${param.dayOfBirth}"
							class="form-control" name="dayOfBirth" id="dateOfBirth"
							aria-describedby="helpId" placeholder=""> <font
							color="red"> ${errorDate} </font>
							<small
							id="date_error_message" class="form-text text-muted"></small>
					</div>
					<div class="form-group">
						<label for="">Graduation Year</label> <input type="number" min="0" required type="text"
							class="form-control" name="graduationYear" id="" value="${param.graduationYear}"
							aria-describedby="helpId" placeholder="">

					</div>
					<div class="form-group">
						<label for="">Salary Per Hour</label> <input required type="number" min="0"
							class="form-control" name="salaryPerHour" id="salary" value="${param.salaryPerHour}"
							aria-describedby="helpId" placeholder=""> <small
							id="salary_error_message" class="form-text text-muted"></small>
					</div>
					<div class="form-group">
						<label for="">Phone</label> <input required type="text"
							class="form-control" name="phone" id="phone" value="${param.phone}"
							aria-describedby="helpId" placeholder="(012)1234567"> <small
							id="phone_error_message" class="form-text text-muted"></small>
					</div>
					<div class="form-group">
						<label for="">Zalo</label> <input type="text" class="form-control"
							name="zalo" id="" aria-describedby="helpId" value="${param.zalo}"
							placeholder="https://www.zalo.me/">

					</div>
					<div class="form-group">
						<label for="">Facebook</label> <input type="text"
							class="form-control" name="faceBook" id="" value="${param.faceBook}"
							aria-describedby="helpId" placeholder="https://www.facebook.com/">

					</div>
					<label for="">Avatar </label>
					<div class="custom-file">
						<s:input id="avatar"  type="file" class="custom-file-input"
							path="profileImageTeacher" />
						<label class="custom-file-label" for="validatedCustomFile">Choose
							file...</label> <font color="red"> ${errorImage} </font>
					</div>
					<label for="">Card ID - front side</label>
					<div class="custom-file">
						<s:input  type="file" class="custom-file-input" id="cardFront"
							path="profileCardID" />
						<label class="custom-file-label" for="validatedCustomFile">Choose
							file...</label> <font color="red"> ${errorCardID} </font>
					</div>

					<label for="">Card ID - back side</label>
					<div class="custom-file">
						<s:input  type="file" class="custom-file-input" id="cardBack"
							path="profileCardIDBehiden" />
						<label class="custom-file-label" for="validatedCustomFile">Choose
							file...</label> <font color="red"> ${errorCardIDBehiden} </font>
					</div>
					<label for="">Upload CV</label>
					<div class="custom-file">
						<s:input id="CV" type="file" class="custom-file-input"
							path="profileUploadCV" />
						<label class="custom-file-label" for="validatedCustomFile">Choose
							file...</label> <font color="red"> ${errorCV} </font>
					</div>
					<div class="form-group">
						<label for="">Description</label>
						<textarea required class="form-control" name="description" id="" rows="3" value="${param.description}"
							placeholder="Your description..."></textarea>
					</div>

					<div class="form-group">
						<label for="">Experience</label>
						<textarea required class="form-control" id="" rows="3" name="experience" value="${param.experience}"
							placeholder="Your description..."></textarea>

					</div> 
					<div class="form-group">
						<label for="">Address</label> 
						<%@ include file="../includes/address.jsp" %> 
						 <input type="hidden" id="villages" name="numberAddress" value="null" /> 
						 <input type="hidden" id="wards"  name="wards" value="null" />
						 <input type="hidden" id="districts" name="district"  value="null" />
						 <input type="hidden" id="citys" name="city" value="null" />	
					</div>
					<button type="submit" class="btn btn-primary">Submit</button>
					<a href="${pageContext.request.contextPath}/guest/home"
						class="btn btn-primary">Cancel</a>
				</s:form>
			</div>
		</div>
	</div>
		<div id="pageLoading" class="" >
			<div id="loading" class=""><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div></div>
			<div ><h3 id="phantram">0%</h3></div>
		
		</div>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script
		src="<c:url value="/guestAssets/js/script_register_teacher.js" />"></script>

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