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
<title>COURSE</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
	
	<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
	
	
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
								aria-expanded="true" href="#" style="padding: 10px;">${traineeInfo.fullName} <img
									src="<c:url value="${traineeInfo.avatar}"/>"
									style="height: 30px; padding: 0; margin-left: 5px; width: 30px;">
							</a>
								<div class="dropdown-menu" role="menu">
									<a class="dropdown-item" role="presentation" href="viewProfile">PROFILE
										<i class="fa fa-address-book-o float-right" id="logouticon"
										style="margin: 0px; width: 19px; height: 19px;"> </i>
									</a> <a class="dropdown-item" role="presentation" href="<c:url value="/guest/logout"/>">LOGOUT
										<i class="fa fa-user-times float-right" id="logouticon"
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
						class="nav-link active shadow" href="myCourse">MY COURSE</a>
					</li>
					<li class="nav-item" role="presentation"><a
						class="nav-link active shadow" href="manageClass">RATING TRAINER</a>
					</li>
					<li class="nav-item" role="presentation"><a
						class="nav-link shadow" href="viewTrainerRequests">TRAINER
							REQUEST </a></li>
					<li class="nav-item" role="presentation"><a
						class="nav-link shadow" href="createPost">CREATE POST</a></li>
				</ul>
			</nav>
		</div>

		<!-- Data Form -->
		<div class="row justify-content-md-center">
			<div class="col-lg-auto col-md-auto col-sm-auto">

				<div style="height: 100px; text-align: center;">
					<h1>Post Information</h1>
				</div>
				<c:if test="${not empty SUCCESS}"><p style="color:red;">${SUCCESS}</p></c:if>
				<form:form id="createPost"
					action="${pageContext.request.contextPath}/trainee/createPost"
					method="POST" commandName="modelCourse">
					<div
						class="table-responsive table-bordered bg-light border rounded border-dark shadow"
						style="margin: 10px 0px; white-space: initial;">
						<table class="table table-striped table-bordered table-sm">
							<thead>
							</thead>
							<tbody>
								<tr>
									<td>Name</td>
									<td>${traineeInfo.fullName}</td>
								</tr>
								<tr>
									<td>Title</td>
									<td><form:input required="true" type="text" path="title" disabled="${not empty modelCourse.title ? 'true':'false' }" /></td>
								</tr>
								<tr>
									<td>Subject</td>
									<td>
									<form:select class="form-control" path="subjectID" disabled="${ fn:contains(listSubjectID,modelCourse.subjectID) ? 'true':'false' }">
											<optgroup label="Choose one subject">
												<c:forEach items="${listSubject}" var="subject" varStatus="theCount" >
												<c:if test="${theCount.count == 1}">
													<option selected value="${subject.subjectID}" ${subject.subjectID == modelCourse.subjectID? 'selected':'' } >${subject.subject}</option>
												</c:if>
												<c:if test="${theCount.count != 1}">
													<option value="${subject.subjectID}" ${subject.subjectID == modelCourse.subjectID? 'selected':'' } >${subject.subject}</option>
												</c:if>												
												</c:forEach>
											</optgroup>
										</form:select>
										</td>
								</tr>
								<tr>
									<td>Description</td>
									<td><form:textarea required="true" style="width:100%" name="" id="" cols="30" rows="10" disabled="${not empty modelCourse.description ? 'true':'false' }"
											path="description"></form:textarea></td>
								</tr>
								<tr>
									<td>Day</td>
									<td>
								
									<c:choose>

											<c:when test="${modelCourse.dayInWeek == 0}">
												<form:select class="form-control" path="dayInWeek"  id = "dayInWeek">
													
													<option disabled value selected >Day In Week</option>
														<option  value="1">Mon Web Fri</option>
														<option value="2">Tue Thu Sat</option>
												
												</form:select>
											</c:when>
											<c:otherwise>
												<form:select class="form-control" path="dayInWeek" disabled="true" >
													<optgroup label="Choose one subject">
														<option value="1" ${modelCourse.dayInWeek == 1? 'selected':'' } >Mon Web Fri</option>
														<option value="2" ${modelCourse.dayInWeek == 2? 'selected':'' }>Tue Thu Sat</option>
													</optgroup>
												</form:select>
											</c:otherwise>
										</c:choose></td>
								</tr>
								<tr>
									<td>Slot</td>
									<td>
									<c:choose>
									<c:when test="${modelCourse.timeInDay == 0 }">
									<form:select class="form-control" path="timeInDay" id="timeInDay">
											
									</form:select>
									
																	<script>

											
											 $('#dayInWeek').change(function () {
													
										            var optionSelected = $(this).find("option:selected");
										            var valueSelected  = optionSelected.val();
										            var textSelected   = optionSelected.text();
										            <% boolean MWFMorning = (boolean) request.getAttribute("MWFMorning");
										            boolean MWFEvening = (boolean) request.getAttribute("MWFEvening");
										            boolean MWFAfternoon = (boolean) request.getAttribute("MWFAfternoon");
										            
										            boolean TTSMorning = (boolean) request.getAttribute("TTSMorning");
										            boolean TTSEvening = (boolean) request.getAttribute("TTSEvening");
										            boolean TTSAfternoon = (boolean) request.getAttribute("TTSAfternoon");
										          
										            
										            %>
										            
												 if(valueSelected == 1){
													 var listTime = '<option selected disabled value>Time In Day</option> '+
														 '<option  <%= MWFMorning?"disabled ":""  %> value="1">Morning</option>' + 
														   '<option <%= MWFAfternoon?"disabled ":""  %> value="2">Afternoon</option>'+
														   '<option <%= MWFEvening?"disabled":""  %> value="3">Evening</option>'
														   $("#timeInDay").html(listTime);
													 
												 }
												 else if(valueSelected = 2){
													 var listTime = '<option selected disabled value>Time In Day</option> '+
													 '<option <%= TTSMorning?"disabled ":""  %> value="1">Morning</option>' + 
													   '<option <%= TTSAfternoon?"disabled ":""  %> value="2">Afternoon</option>'+
													   '<option <%= TTSEvening?"disabled":""  %> value="3">Evening</option>'
													   $("#timeInDay").html(listTime);
												 }
												 
											 });
											
											</script>
								
									
									
									
										</c:when>
										<c:otherwise>
									<form:select class="form-control" path="timeInDay" disabled="true" >
											<optgroup label="Choose one subject">
												<option value="1" ${modelCourse.timeInDay == 1? 'selected': '' } >Morning</option>
												<option value="2" ${modelCourse.timeInDay == 2? 'selected': '' }>Evening</option>
												<option value="3" ${modelCourse.timeInDay == 3? 'selected': '' }>Night</option>
											</optgroup>
										</form:select>										
										</c:otherwise>
									</c:choose>	
										</td>
										
								</tr>
								

								
								
								<tr>
									<td>Address</td>
									<td>${traineeInfo.address}</td>
								</tr>
										<tr>
										
										<datalist id="listDeposit">
									    <option value="1000000">
									    <option value="2200000">
									    <option value="3300000">
									    <option value="3500000">
									    <option value="4000000">
									    <option value="4500000">
									    <option value="4700000">
										</datalist>
									<td>Deposit</td>
									<td><form:input id="deposit" list="listDeposit"  step="100000" type="number"  path="deposit" disabled="${not empty modelCourse.title ? 'true':'false' }"/>VNĐ <span style="color:red" >(optional*)</span></td>

								
								</tr>
							</tbody>
						</table>

					</div>
					<c:if test="${empty SUCCESS}">
					<button type="submit" class="btn btn-primary">SEND POST</button>
					
					</c:if>
				</form:form>
				<c:if test="${not empty SUCCESS}">
				<form:form action="${pageContext.request.contextPath}/trainee/createPost" method="GET" >
				<button type="submit" class="btn btn-primary">BACK TO CREATE POST</button>
				</form:form>
				</c:if>
				
				<div style="text-align: center;"></div>
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
<!-- 	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" -->
<!-- 		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" -->
<!-- 		crossorigin="anonymous"></script> -->
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