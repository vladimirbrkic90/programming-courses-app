<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!doctype html>
<html lang="en">

<head>
	
	<title>Login Page</title>
	<meta charset="utf-8">
	<!-- <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"> -->
	
	<!-- Reference Bootstrap files -->
	<link rel="stylesheet"
		 href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

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
			  <a href="${pageContext.request.contextPath}/enrol/course/list" class="navbar-brand">
			  				<span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a>
			</div>
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="#" id="j7"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> About</a></li>
					<li><a href="#" id="j6"><span class="glyphicon glyphicon-phone" aria-hidden="true"></span> Contact</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
				<!-- 	<li><a href="#">Sign Up</a></li>
					<li><a href="#">Login</a></li> -->
				</ul>
			</div>
		
		</nav>
		
		<div id="loginbox" style="margin-top: 5px;"
			class="mainbox col-md-3 col-md-offset-4 col-sm-6 col-sm-offset-4">
			
			<div class="panel panel-info">

				<div class="panel-heading"> 
					<div class="panel-title">Register New User</div>
				</div>

				<div style="padding-top: 30px" class="panel-body">

					<!-- Registration Form -->
					<form:form action="processRegistrationForm2" method="POST"
						  	   modelAttribute="student"
						  	   class="form-horizontal">
						  	   
						  <!--	   <form:hidden path="id" /> -->

					    <!-- Place for messages: error, alert etc ... -->
					    <div class="form-group">
					        <div class="col-xs-15">
					            <div>
									
									<!-- Check for registration error -->
									<c:if test="${registrationError != null}">
								
										<div class="alert alert-danger col-xs-offset-1 col-xs-10">
											${registrationError}
										</div>
		
									</c:if>

					            </div>
					        </div>
					    </div>

						<!-- User name -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
							
							<form:input path="username" placeholder="username" class="form-control" />
						</div>
						
						<!-- First name -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
							
							<form:input path="firstName" placeholder="First name" class="form-control" />
						</div>
						
						<!-- Last name -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
							
							<form:input path="lastName" placeholder="Last name" class="form-control" />
						</div>
						
						<!-- Email -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span> 
							
							<form:input path="email" placeholder="Email" class="form-control" />
						</div>
						
						<!-- Phone number -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-phone"></i></span> 
							
							<form:input path="phoneNumber" placeholder="Phone number" class="form-control" />
						</div>

						<!-- Password -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span> 
							
							<form:input type="password" path="password" placeholder="password" class="form-control" />
						</div>

						<!-- Register Button -->
						<div style="margin-top: 10px" class="form-group">						
							<div class="col-sm-6 controls">
								<button type="submit" class="btn btn-primary">Register</button>
							</div>
						</div>

					</form:form>

				</div>

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