<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>MakerSpace</title>

<link rel="stylesheet" href="./Resource/mms/vendor/bootstrap/css/my.css" />
<!-- Bootstrap core CSS -->
<link href="./Resource/mms/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Bootstrap side-bar menu -->
<link href="./Resource/mms/vendor/bootstrap/css/shop-homepage.css"
	rel="stylesheet">

<!-- Custom fonts for this template -->
<link href="./Resource/mms/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link
	href="./Resource/mms/vendor/simple-line-icons/css/simple-line-icons.css"
	rel="stylesheet" type="text/css">
<link href="./Resource/mms/vendor/simple-line-icons/css/my.css"
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

<style>
.a {
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}
</style>

</head>

<body>

	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary" id="">
		<a class="navbar-brand" href="mainService.jsp">MakerSpace</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarColor01" aria-controls="navbarColor01"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarColor01">
			<ul class="navbar-nav mr-auto">
			</ul>
			<h7 class="navbar-brand"><%=session.getAttribute("company")%> <%=session.getAttribute("name")%></h7>
			<form class="form-inline my-2 my-lg-0">
				<button type="button" class="btn btn-secondary my-2 my-sm-0"
					onclick="location.href='membercontroller?action=logout'">Logout</button>
			</form>
		</div>
	</nav>

	<!-- Page Content -->
	<div class="container">
		<div class="row">
			<!-- Side Menu -->
			<div class="col-lg-3">
				<h1 class="my-4">Main Menu</h1>
				<div class="list-group">
					<a class="list-group-item">¡Ý Business</a> <a href="businessIt.jsp"
						class="list-group-item">&emsp;&emsp;¢º IT</a> <a
						href="businessSalesMarketing.jsp" class="list-group-item">&emsp;&emsp;¢º
						Sales / Marketing</a> <a href="businessMedia.jsp"
						class="list-group-item">&emsp;&emsp;¢º Media</a> <a
						href="businessPlus.jsp" class="list-group-item">&emsp;&emsp;¢º
						Plus</a> <a href="coolTips.jsp" class="list-group-item">¡Ý Cool
						Tips</a> <a href="mypage.jsp" class="list-group-item">¡Ý My Page</a>
				</div>
			</div>
			<!-- /.Side Menu -->

			<!-- Search bar -->
			<div class="col-lg-9">
				<!-- Table -->
				<label for="exampleInputEmail1">&nbsp;</label> <br> <label
					for="exampleInputEmail1">&nbsp;</label> <br> <label
					for="exampleInputEmail1">&nbsp;</label> <br>
				<div class="container">
					<ol class="breadcrumb">
						<li class="breadcrumb-item">Business</li>
						<li class="breadcrumb-item active">IT</li>
					</ol>
				</div>

				<!-- Writig Form -->
				<div class="container">

						<fieldset>
							<div class="form-group">
								<label for="exampleSelect1">Title</label> <input type="text"
									readonly="readonly" class="form-control" name="title"
									value=<%=request.getAttribute("title")%>>
							</div>
							<div class="form-group">
								<label for="exampleSelect1">Hash Tag</label> <input type="text"
									readonly="readonly" class="form-control" name="hash_tag"
									value=<%=request.getAttribute("hashTag")%>>
							</div>
							<div class="form-group">
								<label for="exampleTextarea">Expectation Effectiveness</label>
								<textarea class="form-control" name="result" rows="6"
									readonly="readonly"><%=request.getAttribute("title")%></textarea>
							</div>
							<div class="form-group">
								<label for="exampleTextarea">Content</label>
								<textarea class="form-control" name="content" rows="10"
									readonly="readonly"><%=request.getAttribute("content")%></textarea>
							</div>
							<div class="form-group">
								<label for="exampleInputFile">File</label> <input type="file"
									class="form-control-file" name="file" readonly="readonly"
									aria-describedby="fileHelp"> <small id="fileHelp"
									class="form-text text-muted">File max capacity</small>
							</div>
						</fieldset>
				</div>
			</div>
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
	<script src="./Resource/mms/vendor/jquery/jquery.slim.min.js"></script>
	<script
		src="./Resource/mms/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="./Resource/mms/vendor/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>