<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
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
			  <a href="#" class="navbar-brand"><span class="glyphicon glyphicon-picture" aria-hidden="true"></span> IMGS</a>
			</div>
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="#">About</a></li>
					<li><a href="#">Contact</a></li>
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
		
		  <div class="form-group">
		    <label for="firstName3" class="col-sm-2 control-label">First name</label>
		    <div class="col-sm-10">
		    		<form:input path="student.firstName" class="form-control" id="firstName3" placeholder="First name"/>
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="lastName3" class="col-sm-2 control-label">Last name</label>
		    <div class="col-sm-10">
		     	<form:input path="student.lastName" class="form-control" id="lastName3" placeholder="Last name"/> 
		     	<form:errors path="student.lastName" cssClass="error" />
		    </div>
		  </div>
		  
		  <div class="form-group blas">
		    <label for="inputEmail3" class="col-sm-2 control-label">Email</label>   
		    <div class="col-sm-10">
		     	<form:input path="student.email" class="form-control" id="inputEmail3" placeholder="Email"/> 
		     	<form:errors path="student.email" cssClass="error" />
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
	<p id="p">
		<a href="${pageContext.request.contextPath}/enrol/course/list">Back to List</a>
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