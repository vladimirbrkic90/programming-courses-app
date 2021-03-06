<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<!DOCTYPE html>
<html>
<head>
	<title>Student</title>
	<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

	<link href="https://fonts.googleapis.com/css?family=Patua+One|Rajdhani" rel="stylesheet">

	<link rel="stylesheet" type="text/css" href="/resources/css/appp-p.css">

	<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

	<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

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
			  <a href="${pageContext.request.contextPath}/enrol/course/list" class="navbar-brand">
			  			<span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a>
			</div>
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}/security/about" id="j7"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> About</a></li>
					<li><a href="${pageContext.request.contextPath}/security/contact" id="j6"><span class="glyphicon glyphicon-phone" aria-hidden="true"></span> Contact</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">Logged as: <security:authentication property="principal.username" /></a></li>
					<li><a href="${pageContext.request.contextPath}/logout" method="POST"><span class="glyphicon glyphicon-off" aria-hidden="true"></span> Logout</a></li>
				</ul>
			</div>
		
		</nav>

		<div id="container">
		
			<div id="content">

				<p class="x1">My Courses:</p>
				<br>
				


			<ul class="products">
				<c:forEach var="enrol" items="${enrols}">
				
				<c:url var="courseVideoLink" value="/student/showCourseVideo">
						<c:param name="courseId" value="${enrol.course.id}" />
					</c:url>
					
				<a href="${courseVideoLink}">
				<li>
					<img src="${enrol.course.image}">
					<p></p>
					<p id="zz">${enrol.course.title}</p>
					
				</li>
				</a>
				</c:forEach>
			</ul> 
			
			<br>
			<br>
		
				
			
			<p class="x1">Recommended for You:</p>
			<br>
			
			<c:if test="${courses != null}"> 
			<ul class="products">
				<c:forEach var="course" items="${courses}">
				
				<c:url var="enrolLink" value="/enrol/showCourseEnrolForm">
						<c:param name="courseId" value="${course.id}" />
				</c:url>
				
				<a href="${enrolLink}">	
				<li>
					<img src="${course.image}">
					<p></p>
					<p id="zz">${course.title}</p>
					
				</li>
				</a>
				</c:forEach>
				
			</ul>
	    	</c:if> 
			</div>	
		
		</div>

	
	
</div>

<div class="container">
<hr>
<p id="nesto">
	Copyright 2018 Vladimir Brkic; Developed by: Vladimir Brkic; vladimirbrkic90@gmail.com
</p>
</div>
	
	
</body>
</html>