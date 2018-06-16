<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>Save Course</title>
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

	<link rel="stylesheet" type="text/css" href="/resources/css/form.css">
	
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
		<h4 id="hh">Save Course</h4>
		
		<form:form action="saveCourse" modelAttribute="course" method="POST" class="form-horizontal">

		<!-- need to associate this data with course id -->
			<form:hidden path="id" />
		
		  <div class="form-group">
		    <label for="Title3" class="col-sm-2 control-label">Title</label>
		    <div class="col-sm-10">
		      <form:input path="title" class="form-control" id="title3" placeholder="Title"/>
		      <form:errors path="title" cssClass="error" />
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="Price3" class="col-sm-2 control-label">Price</label>
		    <div class="col-sm-10">
		      <form:input path="price" class="form-control" id="price3" placeholder="$"/>
		      <form:errors path="price" cssClass="error" />
		    </div>
		  </div>

		  <label for="firstName3" class="col-sm-2 control-label">Instructor</label>
		  <div class="ddw">
		  	
		  		<select name="instructor" ="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true" >  
		  			
  					<c:forEach var="instructors" items="${instructors}">
       				<option  id="${instructors.id}" value="${instructors.id}">${instructors.firstName} ${instructors.lastName}</option> 
       							  
   					</c:forEach>
		  		</select>
		  </div>
		  
		 
		  <div class="form-group blas">
		    <div class="col-sm-offset-2 col-sm-10">
		      <button type="submit" class="btn btn-default">Save</button>
		    </div>
		  </div>

		</form:form>
		
	</div>

	<br>
	<p id="p">
		<a href="${pageContext.request.contextPath}/course/list">Back to List</a>
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