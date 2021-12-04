<%@page import="com.online.shop.util.SecurityUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>DASHBOARD</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
        integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
</head>

<body class="text-monospace text-nowrap">
    <header>
        <div class="d-xl-flex flex-shrink-1 flex-fill">
            <img class="d-xl-flex justify-content-xl-center align-items-xl-center"
                src="<c:url value="/traineeAssets/img/banner.jpg"/>"
                style="width: 100%; height: 480px;">
        </div>
        <div>
            <nav class="navbar navbar-light navbar-expand-md navigation-clean"
                style="padding: 5px;background-color: rgb(255,255,255);">
                <div class="container"><a class="navbar-brand" href="#" style="color: #213d30;">
                        <img src="<c:url value="/traineeAssets/img/logo.png"/>"
                            style="width: 150px;height: 100px;">
                    </a>
                    <button class="navbar-toggler" data-toggle="collapse" data-target="#navcol-1">
                        <span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon">

                        </span>
                    </button>
                    <div class="collapse navbar-collapse" id="navcol-1">
                        <ul class="nav navbar-nav mr-auto">
                            <li class="nav-item" role="presentation">
                                <a class="nav-link active shadow" href="trainee-Home">DASHBOARD </a>
                            </li>
                            <li class="nav-item" role="presentation">
                                <a class="nav-link shadow" href="newPost">New Post</a>
                            </li>
                        </ul>
                        <ul class="nav navbar-nav ml-auto">
                            <li class="nav-item dropdown show">
                                <a class="dropdown-toggle nav-link shadow" data-toggle="dropdown" aria-expanded="true"
                                    href="#" style="padding:10px;">
                                    ${traineeInfo.fullName}
                                    <img src="<c:url value="${traineeInfo.avatar}"/>"
                                        style="height: 30px;padding: 0;margin-left: 5px;width: 30px;">
                                </a>
                                <div class="dropdown-menu" role="menu">
                                    <a class="dropdown-item" role="presentation" href="viewProfile">PROFILE
                                        <i class="fa fa-address-book-o float-right" id="logouticon"
                                            style="margin: 0px;width: 19px;height: 19px;">
                                        </i>
                                    </a>
                                    <a class="dropdown-item" role="presentation" href="<c:url value="/guest/logout"/>">LOGOUT
                                        <i class="fa fa-user-times float-right" id="logouticon"
                                            style="width: 19px;height: 19px;margin: 0px;">
                                        </i>
                                    </a>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
    </header>
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
    
    <div class="container">
    	<div role="alert" class="alert alert-light text-center"><span style="font-size: 22px;"><strong>Recommended Trainers</strong><br /></span></div>
	</div>
    <div class="container">
        <!-- Search Form -->
        <div>
            <div class="row" style="padding: 30px 0px;">
                <div class="col">
                    <form action="trainee-Home" method="post"
                        class="d-flex d-md-flex justify-content-start justify-content-md-start justify-content-xl-center align-items-xl-center">
                        <span><i class="fa fa-book border rounded d-flex d-sm-flex justify-content-center align-items-center justify-content-sm-center align-items-sm-center"
                                style="height: 38px;width: 45px;background-color: #b5e8bf;font-size: 20px;">
                            </i>
                        </span>
                        <select name="subjectID"
                        class="form-control">
                        <optgroup label="Select object">
                        	<option value="0" 
                        		<c:if test="${0 == subjectID}">
                        			selected="selected"
                        		</c:if>
                        	>
                        		All subjects
                        	</option>
                        	
                        	<c:forEach var="subjectList" items="${availSubject}">
                        		<option value="${subjectList.subjectID}"
                        				<c:if test="${subjectList.subjectID == subjectID}">
                        					selected="selected"
                        				</c:if>
                        		>
                        			${subjectList.subject}
                        		</option>
                        	</c:forEach>
                        </optgroup>
                        </select>
                        <button class="btn btn-primary" type="submit"
                            style="background-color: #b5e8bf;color: rgb(0,0,0);">Search Trainer</button>
<!--                         <button class="btn btn-primary" type="button"
                            style="background-color: #b5e8bf;color: rgb(0,0,0);">Search Trainee</button> -->
                    </form>
                </div>
            </div>
        </div>
    <!-- Data Form -->
    <c:if test="${not empty updateMessage}">
    	<div class="container">
    		<div role="" class="alert alert-info">
    			<span>
    				<strong>Info:</strong> ${updateMessage}
    			</span>
    		</div>
		</div>
    </c:if>
    <c:if test="${not empty trainerProfileList}">
        <div class="row">
        	<c:forEach var="list" items="${trainerProfileList}">
	            <div class="col-md-6" style="margin-top:10px;">
	                <div class="row justify-content-md-center">
	                    <div class="col-lg-auto col-md-auto col-sm-auto">
	                        <div style="text-align: center;">
	                            <img src="<c:url value="${list.key.avatar}"/>"
	                                style="width: 100px;height: 100px;">
	                        </div>
	
	                        <div class="table-responsive table-bordered bg-light border rounded border-dark shadow"
	                            style="margin: 10px 0px; white-space: initial; width: 320px">
	                            <table class="table table-striped table-bordered table-sm">
	                                <thead>
	                                </thead>
	                                <tbody>
	                                    <tr>
	                                        <td>Name</td>
	                                        <td>
	                                            <a href="trainerProfile?trainerID=${list.key.email}">
	                                                ${list.key.fullName}
	                                            </a>
	                                        </td>
	                                    </tr>
	                                    <tr>
	                                        <td>Subject</td>
	                                        <td>
	                                        	<c:forEach var="subjectList" items="${list.value}">
			                                        <c:forEach var="sub" items="${subjectList.value}">
			                                        	<em class="border rounded border-dark" 
		                                        		style="background-color: #99d6dd;padding: 0px 2px;">
			                                        		${sub.subject}
			                                        	</em>	
			                                        </c:forEach>		                                        	
	                                        	</c:forEach>
	                                        </td>
	                                    </tr>
	                                    <tr>
	                                        <td>Location</td>
	                                        <td>${list.key.address}</td>
	                                    </tr>
	                                    <tr>
	                                        <td>Salary</td>
	                                        <td>${list.key.salaryPerHour} d/hour</td>
	                                    </tr>
	                                    <tr>
	                                        <td>Rating</td>
	                                        <td>
	                                        	<c:forEach var="rating" items="${list.value}">
			                                        ${rating.key == -1.0 ? 'n/a':rating.key}		                                        	
	                                        	</c:forEach>
	                                        </td>
	                                    </tr>
	                                </tbody>
	                            </table>
	                        </div>
	                    </div>
	                </div>
	            </div>
	    	</c:forEach>
		</div>
        <!-- Paging -->
        <div class="container">
	    	<form action="trainee-Home" method="post">
	    		<c:set var="current" value="${curPage}"/>
	    		<c:set var="total" value="${totalPage}"/>
	    		<c:set var="maxDisplay" value="${5}"/>
	    		<input type="hidden" name="subjectID" value="${subjectID}">
		    	<nav class="d-flex d-sm-flex justify-content-center justify-content-sm-center">
		            <input id="firstPage" class="page-item page-link" type="submit" name="curPage" value="<<"
		            onclick="getElementById('firstPage').value = 1" ${current>1 ?'':'disabled=""'}>
		            
		            <c:set var="count" value="${(total <= maxDisplay or current - 2 <= 1) ? 
		            							1 : (total-2 >=current ? 
		            							current-2 : total-(maxDisplay-1))}"/>
		            <c:forEach var="pageNum" begin="1" step="1" end="${total < maxDisplay ? total : maxDisplay}">
		            	<input class="page-item page-link" 
		            		type="submit" name="curPage" 
		            		value="${count}" ${count==current ? 'style="color: red"':''}>
		            	<c:set var="count" value="${count + 1}"/>
		            </c:forEach>
		            <input id="lastPage" class="page-item page-link" type="submit" name="curPage" value=">>" 
		            onclick="getElementById('lastPage').value = ${total}" ${current<totalPage ?'':'disabled=""'}>
		        </nav>
	    	</form>
	    </div>	
    </c:if>
    <c:if test="${empty trainerProfileList}">
    	<div class="container">
    		<div role="alert" class="alert alert-warning">
    			<span>
    				<strong>Alert:</strong> No trainee courses can be found!
    			</span>
    		</div>
		</div>
    </c:if>
    </div>
    <div class="footer-clean">
        <footer>
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-sm-4 col-md-3 item">
                        <h3>Services</h3>
                        <ul>
                            <li>
                                <a href="#">Web design</a>
                            </li>
                            <li>
                                <a href="#">Development</a>
                            </li>
                            <li>
                                <a href="#">Hosting</a>
                            </li>
                        </ul>
                    </div>
                    <div class="col-sm-4 col-md-3 item">
                        <h3>About</h3>
                        <ul>
                            <li>
                                <a href="#">Company</a>
                            </li>
                            <li>
                                <a href="#">Team</a>
                            </li>
                            <li>
                                <a href="#">Legacy</a>
                            </li>
                        </ul>
                    </div>
                    <div class="col-sm-4 col-md-3 item">
                        <h3>Careers</h3>
                        <ul>
                            <li>
                                <a href="#">Job openings</a>
                            </li>
                            <li>
                                <a href="#">Employee success</a>
                            </li>
                            <li>
                                <a href="#">Benefits</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </footer>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>
</body>

</html>