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
<title>MakerSpace</title>
<!-- Bootstrap core CSS-->
<link href="./Resource/mms/vendor/bootstrap/css/bootstrap2.min.css"
	rel="stylesheet">
<!-- Custom fonts for this template-->
<link href="./Resource/mms/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<!-- Custom styles for this template-->
<link href="./Resource/mms/css/sb-admin.css" rel="stylesheet">
</head>

<body class="bg-dark">
	<div class="container">
		<div class="card card-login mx-auto mt-5">
			<div class="card-header">
				Sign In
				<button type="button" class="close" aria-label="Close" onclick="javascript:history.back()" >
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="card-body">

				<form method="post" action="membercontroller">
					<div class="form-group">
						<label for="exampleInputEmail1">Email address</label> <input
							class="form-control" name="email" type="email"
							aria-describedby="emailHelp" placeholder="Enter email">
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">Password</label> <input
							class="form-control" name="password" type="password"
							placeholder="Password">
					</div>
					<div class="text-center">
						<input type="hidden" name="action" value="login"> <input
							class="btn btn-primary btn-block" type="submit" value="Sign in">
						<a class="d-block small mt-3" href="register.jsp">Register an
							Account</a> <a class="d-block small" href="forgot_password.jsp">Forgot
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
