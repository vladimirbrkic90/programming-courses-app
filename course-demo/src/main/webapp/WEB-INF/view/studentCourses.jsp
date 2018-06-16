<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
	<title>Student Courses</title>
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <link href="https://fonts.googleapis.com/css?family=Patua+One|Rajdhani" rel="stylesheet">

	<link rel="stylesheet" type="text/css" href="/resources/css/appp.css">
	
	<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script> -->

	<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>

	<div class="container">
		<div class="jumbotron">
			
		</div>
	</div>

	<div class="container brd">
		<nav class="navbar navbar-inverse ">
		
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		      </button>
			  <a href="${pageContext.request.contextPath}/instructor/list" class="navbar-brand">
			  			<span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a>
			</div>
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="#">About</a></li>
					<li><a href="#">Contact</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">Logged as: <security:authentication property="principal.username" /></a></li>
					<li><a href="${pageContext.request.contextPath}/logout" method="POST"><span class="glyphicon glyphicon-off" aria-hidden="true"></span> Logout</a></li>
				</ul>
			</div>
		
		</nav>
		

	<div id="cont">	
		<a href="${pageContext.request.contextPath}/instructor/list">Instructors</a><br>
		<a href="${pageContext.request.contextPath}/course/list">Courses</a><br>
		<a href="${pageContext.request.contextPath}/student/list">Students</a><br>
		<a href="${pageContext.request.contextPath}/enrol/list">Enrols</a>
	</div> 

		<div id="container">
		
		<p id="z">Student: <span id="zz">${student.firstName} ${student.lastName}</span></p>
		
		<table id="xxx">
	
		<tr>
			<th id="rr">Enroled Courses</th>
			<th>Enrol Date</th>
		</tr>
		
		<c:forEach var="enrol" items="${enrols}">
		
			<tr>
				<td> ${enrol.course.title} </td>
				<td> ${enrol.date} </td>
				
			</tr>
			
		</c:forEach>	
			
		</table>		
		
	  <!--    	<p id="z">Student: <span id="zz">${student.firstName} ${student.lastName}</span></p>
			
			<p class="z1">Enrol Date: <span class="zz2">${enrol.date}</span></p>

			<p class="z1">Course: <span class="zz2">${course.title}</span></p>  -->
			
		
			
		
		</div>

	<br>
	<p id="p">
		<a href="${pageContext.request.contextPath}/student/list">Back to List</a>
	</p>
	
</div>

<div class="container">
<hr>
<p id="nesto">
	Copyright 2018 Vladimir Brkic; Developed by: Vladimir Brkic; vladimirbrkic90@gmail.com
</p>
</div>
	
</body>
</html>