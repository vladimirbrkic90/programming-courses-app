<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
	<title>Index</title>
	<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

	<link rel="stylesheet" type="text/css" href="/resources/css/app.css">

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
				
				<li id="j9"><form:form action="searchCourse" method="POST">

					<input type="text" id="j8" name="theSearchName" placeholder="Search..." autocomplete="off" />
					<button type="submit" id="j10">
						<span class="glyphicon glyphicon-search" aria-hidden="true" id="j11"> </span>
					</button>

				</form:form></li>
					
					<li id="j19"><a href="${pageContext.request.contextPath}/student/showMyCourses"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> Login</a></li>
				</ul>
			</div>
		
		</nav>
		
		<div id="container1">
	
		<p id="cc51"><img src="${course.image}" width="50px" height="40px"> ${course.title} - Reviews</p>
		<br>
		
		<ul id="s11">
			<c:forEach var="review" items="${courseReviews}">
			
			<li id="zz51">
				<p id="">${review.comment}</p>
			</li>
			
			</c:forEach>
		</ul>
	
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