<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
	<title>Enrol List</title>
	<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

	<link rel="stylesheet" type="text/css" href="/resources/css/appp.css">

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
		<a href="${pageContext.request.contextPath}/instructor/list"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> Instructors</a><br>
		<a href="${pageContext.request.contextPath}/course/list"><span class="glyphicon glyphicon-book" aria-hidden="true"></span> Courses</a><br>
		<a href="${pageContext.request.contextPath}/student/list"><span class="glyphicon glyphicon-education" aria-hidden="true"></span> Students</a><br>
		<a href="${pageContext.request.contextPath}/enrol/list"><span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span> Enrols</a>
	</div> 
	<!-- <p>
		User: <security:authentication property="principal.username" />
	</p> -->
		<div id="container">
	
		<div id="content">


	<table>
	
		<tr>
			<th>Enrol Date</th>
			<th id="ll">Course</th>
			<th>Student</th>
			<th id="hh11">Action</th>
		</tr>
		
		<c:forEach var="tempEnrol" items="${enrols}">
		
					
					<!-- Construct an "delete" link with customer id -->
					<c:url var="deleteLink" value="/enrol/deleteEnrol">
						<c:param name="enrolId" value="${tempEnrol.id}" />
					</c:url>
		
			<tr>
				<td id="w3"> ${tempEnrol.date} </td>
				<td class="w1"> ${tempEnrol.course.title} </td>
				<td class="w1"> ${tempEnrol.student.firstName} ${tempEnrol.student.lastName} </td>
				
				
				<td id="w4">

					<!-- dispaly the show details link
					<a href="${instructorDetailsLink}">Show review</a>
							| -->
					<a href="${deleteLink}"
					onclick="if (!(confirm('Are you sure you want to delete this enrol?'))) return false">Delete</a>
					
				</td>
				
			</tr>
		
		</c:forEach>
	
	</table>
	<br><br>
	<form:form action="${pageContext.request.contextPath}/logout" method="POST">
		
		<input type="submit" value="Logout" class="add-button" />
	
	</form:form>
	
	</div>
	
</div>

	
</div>

<div class="container">
<hr>
<p id="nesto">
	Copyright 2018 Vladimir Brkic; Developed by: Vladimir Brkic; vladimirbrkic90@gmail.com
</p>
</div>

	<!-- </div> -->
	
</body>
</html>