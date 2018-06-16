<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!doctype html>
<html>
<head>
	<title>Enrol Course</title>
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    
    <link href="https://fonts.googleapis.com/css?family=Patua+One|Rajdhani" rel="stylesheet">

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
			  <a href="${pageContext.request.contextPath}/enrol/course/list" class="navbar-brand">
			  			<span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a>
			</div>
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="#" id="j7"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> About</a></li>
					<li><a href="#" id="j6"><span class="glyphicon glyphicon-phone" aria-hidden="true"></span> Contact</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					
					
				</ul>
			</div>
		
		</nav>
		

		<div id="container">
		<p id="z">Enrol course: ${enrol.course.title}<p>
		
		<c:url var="course" value="enrol.course.id">
					<c:param name="course_id" value="${course}" />
		</c:url>
		
		<form:form action="saveEnrol" modelAttribute="enrol"  method="POST" class="form-horizontal">

		<!-- need to associate this data with instructor id -->
			<form:hidden path="id" />
			<form:hidden path="course.id" />
								
					 <!-- Place for messages: error, alert etc ... -->
					    <div class="form-group" id="asadd">
					        <div class="col-xs-15">
					            <div>
								
									<!-- Check for registration error -->
									<c:if test="${error != null}">
								
										<div class="alert alert-danger col-xs-offset-1 col-xs-10">
											${error}
										</div>
		
									</c:if>
																			
					            </div>
					        </div>
					    </div>
																			
		   <div class="form-group blas">
		    <label for="inputEmail3" class="col-sm-2 control-label">Username</label>   
		    <div class="col-sm-10">
		     	<form:input path="student.username" class="form-control" id="inputEmail3" placeholder="Username"/> 
		     	<form:errors path="student.username" cssClass="error" />
		    </div>
		  </div>
		  
		   <div class="form-group blas">
		    <label for="inputEmail3" class="col-sm-2 control-label">Password</label>   
		    <div class="col-sm-10">
		     	<form:input type="password" path="student.password" class="form-control" id="inputEmail3" placeholder="Password"/> 
		     	<form:errors path="student.password" cssClass="error" />
		    </div>
		  </div>
		  
		 
		  <div class="form-group blas">
		    <div class="col-sm-offset-2 col-sm-10">
		      <button type="submit" class="btn btn-default">Enrol</button>
		    </div>
		  </div>

		</form:form>
		
	</div>
	
	<br>
	<br>
	<div class="p">
		<a href="${pageContext.request.contextPath}/security/showRegistrationForm2" 
				class="btn btn-primary" role="button" aria-pressed="true">Register New User</a>
	</div>

	<br>
	<br>
	
</div>

<div class="container">
	<hr>
		<p id="nesto">
		Copyright 2018 Vladimir Brkic; Developed by: Vladimir Brkic; vladimirbrkic90@gmail.com
	</p>
</div>
	
	
</body>
</html>