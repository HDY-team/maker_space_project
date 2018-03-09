<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>MakerSpace</title>

<!-- Bootstrap core CSS -->
<link href="./Resource/mms/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom fonts for this template -->
<link href="./Resource/mms/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link
	href="./Resource/mms/vendor/simple-line-icons/css/simple-line-icons.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic"
	rel="stylesheet" type="text/css">

<!-- Custom styles for this template -->
<link href="./Resource/mms/css/landing-page.min.css" rel="stylesheet">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

</head>

<body class="" id="">

	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary" id="">
		<a class="navbar-brand" href="index.jsp">MakerSpace</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarColor01" aria-controls="navbarColor01"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarColor01">
			<ul class="navbar-nav mr-auto">
			</ul>
			<form class="form-inline my-2 my-lg-0">
				<button type="button" class="btn btn-secondary my-2 my-sm-0"
					onclick="location.href='login.jsp'">Sign in</button>
			</form>
			&nbsp; &nbsp;
			<form class="form-inline my-2 my-lg-0">
				<button type="button" class="btn btn-secondary my-2 my-sm-0"
					onclick="location.href='register.jsp'">Sign up</button>
			</form>
		</div>
	</nav>

	<label for="exampleInputEmail1">&nbsp;</label>
	<br>
	
	<!-- Masthead -->
	<label for="exampleInputEmail1">&nbsp;</label>
	<br>
	<header class="masthead text-white text-center">
		<div class="overlay"></div>
		<div class="container">
			<div class="row">
				<div class="col-xl-9 mx-auto">
					<h1 class="mb-5">Get and Share Idea!</h1>
				</div>
				<div class="col-md-10 col-lg-8 col-xl-7 mx-auto">
					<form>
						<div class="form-row">
							<div class="col-12 col-md-9 mb-2 mb-md-0">
								<input type="text" class="form-control form-control-lg"
									placeholder="# 5G # 1등 KT ... ">
							</div>

							<div class="col-12 col-md-3">
								<input type="button" class="btn btn-block btn-lg btn-primary"
									onclick="location.href='login.jsp'" value="Search">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		
	</header>

	
	<!-- 비즈니스 Icons Grid -->
	<label for="exampleInputEmail1">&nbsp;</label>
	<br>
	<label for="exampleInputEmail1">&nbsp;</label>
	<br>
	<div class="container">
		<div class="alert alert-dismissible alert-primary">
			<h2 class="alert-heading">
				<h3 class="alert-link">Business</h3>
			</h2>
		</div>
	</div>
	<section class="testimonials text-center bg-light">
		<div class="container">
			<div class="row">
				<div class="col-lg-6">
					<div class="testimonial-item mx-auto mb-8 mb-lg-0">
						<img class="img-fluid rounded-circle mb-5"
							src="./Resource/mms/img/testimonials-4.PNG" alt="">
						<h3>
							<a href="login.jsp" class="alert-link">IT</a>
						</h3>
					</div>
				</div>
				<div class="col-lg-6">
					<div class="testimonial-item mx-auto mb-5 mb-lg-0">
						<img class="img-fluid rounded-circle mb-5"
							src="./Resource/mms/img/testimonials-5.PNG" alt="">
						<h3>
							<a href="login.jsp" class="alert-link">SALES & MARKETING</a>
						</h3>
					</div>
				</div>
			</div>
		</div>
	</section>

	<section class="testimonials text-center bg-light">
		<div class="container">
			<div class="row">
				<div class="col-lg-6">
					<div class="testimonial-item mx-auto mb-5 mb-lg-0">
						<img class="img-fluid rounded-circle mb-3"
							src="./Resource/mms/img/testimonials-6.jpg" alt="">
						<h3>
							<a href="login.jsp" class="alert-link">MEDIA</a>
						</h3>
					</div>
				</div>
				<div class="col-lg-6">
					<div class="testimonial-item mx-auto mb-5 mb-lg-0">
						<img class="img-fluid rounded-circle mb-3"
							src="./Resource/mms/img/testimonials-7.png" alt="">
						<h3>
							<a href="login.jsp" class="alert-link">PLUS</a>
						</h3>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- 꿀팁 table -->
	<label for="exampleInputEmail1">&nbsp;</label>
	<br>
	<div class="container">
		<div class="alert alert-dismissible alert-primary">
			<h2 class="alert-heading">
				<a href="login.jsp" class="alert-link">Monthly Tips Best 5</a>
			</h2>
		</div>
	</div>
	<div class="container">
		<table class="table table-hover">
			<thead>
				<tr class="table-active">
					<th scope="col">순위</th>
					<th scope="col">제목</th>
					<th scope="col">작성자</th>
					<th scope="col">조회수</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th scope="row">1</th>
					<td><a href="#" class="alert-link"> 아이고 세상에 이런일이 이렇게 하니 정말
							좋구나 만세다만세 이렇게 하다간 내가바로 회사의 최고 인재 이야이야호</a></td>
					<td>황보영</td>
					<td>11</td>
				</tr>
				<tr>
					<th scope="row">2</th>
					<td>Column content</td>
					<td>Column content</td>
					<td>Column content</td>
				</tr>
				<tr>
					<th scope="row">3</th>
					<td>Column content</td>
					<td>Column content</td>
					<td>Column content</td>
				</tr>
				<tr>
					<th scope="row">4</th>
					<td>Column content</td>
					<td>Column content</td>
					<td>Column content</td>
				</tr>
				<tr>
					<th scope="row">5</th>
					<td>Column content</td>
					<td>Column content</td>
					<td>Column content</td>
				</tr>
			</tbody>
		</table>
	</div>
	

	<!-- Footer -->
	<footer class="footer bg-light">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 h-100 text-center text-lg-left my-auto">
					<ul class="list-inline mb-2">
						<li class="list-inline-item"><a href="#">About</a></li>
						<li class="list-inline-item">&sdot;</li>
						<li class="list-inline-item"><a href="#">Contact</a></li>
						<li class="list-inline-item">&sdot;</li>
						<li class="list-inline-item"><a href="#">Terms of Use</a></li>
						<li class="list-inline-item">&sdot;</li>
						<li class="list-inline-item"><a href="#">Privacy Policy</a></li>
					</ul>
					<p class="text-muted small mb-4 mb-lg-0">&copy; Your Website
						2018. All Rights Reserved.</p>
				</div>
				<div class="col-lg-6 h-100 text-center text-lg-right my-auto">
					<ul class="list-inline mb-0">
						<li class="list-inline-item mr-3"><a href="#"> <i
								class="fa fa-facebook fa-2x fa-fw"></i>
						</a></li>
						<li class="list-inline-item mr-3"><a href="#"> <i
								class="fa fa-twitter fa-2x fa-fw"></i>
						</a></li>
						<li class="list-inline-item"><a href="#"> <i
								class="fa fa-instagram fa-2x fa-fw"></i>
						</a></li>
					</ul>
				</div>
			</div>
		</div>
	</footer>

	<!-- Bootstrap core JavaScript -->
	<script src="./Resource/mms/vendor/jquery/jquery.min.js"></script>
	<script
		src="./Resource/mms/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>

</html>