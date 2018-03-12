<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Register</title>
<!-- Bootstrap core CSS-->
<link href="./Resource/mms/vendor/bootstrap/css/bootstrap2.min.css"
	rel="stylesheet">
<!-- Custom fonts for this template-->
<link href="./Resource/mms/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<!-- Custom styles for this template-->
<link href="./Resource/mms/css/sb-admin.css" rel="stylesheet">
<!-- jquery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

</head>
<script>
$(document).ready(function(){
	//인증코드를 메일로 발송하고 코드를 저장
	$("#auth").click(function() {
	   var email = $("#email").val();
	   if (email != "") {
	      $.ajax({
	         "url" : 'membercontroller',
	         "method" : 'post',
	         "data" : "email=" + email + "&action=confirmEmail",
	         success : function(data) {
	        	alert('Success send a code'); 
	            
	            $("#code")[0].value = data;
	            $("#auth").show();
	         }
	      })
	   } else {
	      alert('write your Email');
	   }
	});
})


</script>
<body class="bg-dark">
	<div class="container">
		<div class="card card-register mx-auto mt-5">
			<div class="card-header">
				Create an Account
				<button type="button" class="close" aria-label="Close"
					onclick="javascript:history.back()">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="card-body">
				<form method="post" action="membercontroller">
					<div class="form-group">
						<div class="form-row">
							<div class="col-md-6">
								<label for="exampleInputName">Name</label> <input
									class="form-control" name="name" type="text"
									aria-describedby="nameHelp" placeholder="Enter name">
							</div>
							<div class="col-md-6">
								<label for="exampleInputLastName">Phone Number</label> <input
									class="form-control" name="mobile" type="text"
									aria-describedby="nameHelp" placeholder="Enter phone number">
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="form-row">
							<div class="col-md-8">
								<label for="exampleInputEmail1">Email address</label> 
								<input class="form-control" name="email" type="email" id='email' aria-describedby="emailHelp" placeholder="Enter email">
							</div>
							<div class = "col-md-4">
								<label for="exampleInputEmail1">&nbsp;</label>
								<input class="btn btn-primary btn-block" type="button" class='btn' id='auth' value='send Code'>
							</div>
							<div class="col-md-6">
							<br>
								<label for="confirmedEmailCode">Code</label> 
								<input class="form-control" name="code" type="text" Placeholder="Code">
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="form-row">
							<div class="col-md-6">
								<label for="exampleInputPassword1">Password</label> <input
									class="form-control" name="password" type="password"
									placeholder="Password">
							</div>
							<div class="col-md-6">
								<label for="exampleConfirmPassword">Confirm password</label> <input
									class="form-control" name="confirmPassword" type="password"
									placeholder="Confirm password">
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="form-row">
							<div class="col-md-6">
								<label for="exampleInputPassword1">Company</label> 
								<input class="form-control" name="company" type="text" placeholder="company">
							</div>
						</div>
					</div>


					<div class="text-center">
						<input type="hidden" name="action" value="join">
						<button type="submit" class="btn btn-primary btn-block">Sign up</button>

						<a class="d-block small mt-3" href="login.jsp">Login Page</a> <a
							class="d-block small" href="forgot-password.jsp">Forgot
							Password?</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Bootstrap core JavaScript-->
	<script src="./Resource/mms/vendor/jquery/jquery.min.js"></script>
	<script
		src="./Resource/mms/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- Core plugin JavaScript-->
	<script src="./Resource/mms/vendor/jquery-easing/jquery.easing.min.js"></script>
</body>

</html>