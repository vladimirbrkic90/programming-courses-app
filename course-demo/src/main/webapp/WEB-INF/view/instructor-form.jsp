<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
	<title>Save Instructor</title>
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
		<h4>Save Instructor</h4>
		
		<form:form action="saveInstructor" modelAttribute="instructor" method="POST" class="form-horizontal">

		<!-- need to associate this data with instructor id -->
			<form:hidden path="id" />
			<form:hidden path="instructorDetail.id" />
		
		  <div class="form-group">
		    <label for="firstName3" class="col-sm-2 control-label">First name</label>
		    <div class="col-sm-10">
		    		<form:input path="firstName" class="form-control" id="firstName3" placeholder="First name"/>
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="lastName3" class="col-sm-2 control-label">Last name</label>
		    <div class="col-sm-10">
		     	<form:input path="lastName" class="form-control" id="lastName3" placeholder="Last name"/>
		     	<form:errors path="lastName" cssClass="error" /> 
		    </div>
		  </div>
		  
		  <div class="form-group blas">
		    <label for="inputEmail3" class="col-sm-2 control-label">Email</label>   
		    <div class="col-sm-10">
		     	<form:input path="email" class="form-control" id="inputEmail3" placeholder="Email"/>
		     	<form:errors path="email" cssClass="error" /> 
		    </div>
		  </div>
		  
		  <div class="form-group blas">
		    <label for="inputEmail3" class="col-sm-2 control-label">Youtube channel</label>
		    <div class="col-sm-10">
		     	<form:input path="instructorDetail.youtubeChannel" class="form-control" id="inputEmail3" placeholder="Youtube channel"/> 
		    </div>
		  </div>
		  
		  <div class="form-group blas">
		    <label for="inputEmail3" class="col-sm-2 control-label">Hobby</label>
		    <div class="col-sm-10">
		     	<form:input path="instructorDetail.hobby" class="form-control" id="inputEmail3" placeholder="Hobby"/> 
		    </div>
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
		<a href="${pageContext.request.contextPath}/instructor/list">Back to List</a>
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