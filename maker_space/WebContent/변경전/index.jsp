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
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="index.jsp">MakerSpace</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
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
			</div>
		</div>
	</nav>

	<!-- Masthead -->
	<label for="exampleInputEmail1">&nbsp;</label>
	<br>
	<label for="exampleInputEmail1">&nbsp;</label>
	<br>
<%
	String CONTEXT_PATH = application.getContextPath();
%>
	<header class="masthead text-white text-center">
		<div class="overlay"></div>
		<div class="container">
			<div class="row">
				<div class="col-xl-9 mx-auto">
					<h1 class="mb-5">share your Idea</h1>
				</div>
				<div class="col-md-10 col-lg-8 col-xl-7 mx-auto">
					<form action="<%= CONTEXT_PATH %>/search.jsp">
						<div class="form-row">
							<div class="col-12 col-md-9 mb-2 mb-md-0">
								<input type="text" class="form-control form-control-lg"
									placeholder="# 5G # 1�� KT ... ">
							</div>
							<div class="col-12 col-md-3">
								<input type="button" class="btn btn-block btn-lg btn-primary"
									onclick="location.href='search.jsp'" value="search">
								
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</header>

	<!-- ���� table -->
	<label for="exampleInputEmail1">&nbsp;</label>
	<br>
	<label for="exampleInputEmail1">&nbsp;</label>
	<br>
	<label for="exampleInputEmail1">&nbsp;</label>
	<br>
	<div class="container">
		<div class="alert alert-dismissible alert-primary">
			<h2 class="alert-heading">
				<a href="coolTips.jsp" class="alert-link">������ ���� BEST 5</a>
			</h2>
		</div>
	</div>
	<div class="container">
		<table class="table table-hover">
			<thead>
				<tr class="table-active">
					<th scope="col">����</th>
					<th scope="col">����</th>
					<th scope="col">�ۼ���</th>
					<th scope="col">��ȸ��</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th scope="row">1</th>
					<td><a href="#" class="alert-link"> ���̰� ���� �̷����� �̷��� �ϴ� ����
							������ �����ٸ��� �̷��� �ϴٰ� �����ٷ� ȸ���� �ְ� ���� �̾��̾�ȣ</a></td>
					<td>Ȳ����</td>
					<td>11</td>
				</tr>
				<tr>
					<th scope="row">2</th>
					<td><a href="#" class="alert-link"> Column content</a></td>
					<td>Column content</td>
					<td>Column content</td>
				</tr>
				<tr>
					<th scope="row">3</th>
					<td><a href="#" class="alert-link">Column content</a></td>
					<td>Column content</td>
					<td>Column content</td>
				</tr>
				<tr>
					<th scope="row">4</th>
					<td><a href="#" class="alert-link">Column content</a></td>
					<td>Column content</td>
					<td>Column content</td>
				</tr>
				<tr>
					<th scope="row">5</th>
					<td><a href="#" class="alert-link">Column content</a></td>
					<td>Column content</td>
					<td>Column content</td>
				</tr>
			</tbody>
		</table>
	</div>

	<!-- ����Ͻ� Icons Grid -->
	<label for="exampleInputEmail1">&nbsp;</label>
	<br>
	<label for="exampleInputEmail1">&nbsp;</label>
	<br>
	<label for="exampleInputEmail1">&nbsp;</label>
	<br>
	<div class="container">
		<div class="alert alert-dismissible alert-primary">
			<h2 class="alert-heading">
				<a href="#" class="alert-link">����Ͻ�</a>
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
							<a href="businessIt.jsp" class="alert-link">IT</a>
						</h3>
					</div>
				</div>
				<div class="col-lg-6">
					<div class="testimonial-item mx-auto mb-5 mb-lg-0">
						<img class="img-fluid rounded-circle mb-5"
							src="./Resource/mms/img/testimonials-5.PNG" alt="">
						<h3>
							<a href="businessSalesMarketing.jsp" class="alert-link">SALES / MARKETING</a>
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
							<a href="businessMedia.jsp" class="alert-link">MEDIA</a>
						</h3>
					</div>
				</div>
				<div class="col-lg-6">
					<div class="testimonial-item mx-auto mb-5 mb-lg-0">
						<img class="img-fluid rounded-circle mb-3"
							src="./Resource/mms/img/testimonials-7.png" alt="">
						<h3>
							<a href="businessPlus.jsp" class="alert-link">PLUS</a>
						</h3>
					</div>
				</div>
			</div>
		</div>
	</section>
	
	<!-- My Page -->
	<label for="exampleInputEmail1">&nbsp;</label>
	<br>
	<div class="container">
		<div class="alert alert-dismissible alert-primary">
			<h2 class="alert-heading">
				<a href="mypage.jsp" class="alert-link">My Page</a>
			</h2>
		</div>
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