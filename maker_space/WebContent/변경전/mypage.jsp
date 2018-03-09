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

<!-- Bootstrap side-bar menu -->
<link href="./Resource/mms/vendor/bootstrap/css/shop-homepage.css"
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

<body>
	<%
	String CONTEXT_PATH = application.getContextPath();
%>
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
						onclick="location.href='index.jsp'">Logout</button>
				</form>
			</div>
		</div>
	</nav>

	<!-- Page Content -->
	<div class="container">
		<div class="row">
			<!-- Side Menu -->
			<div class="col-lg-3">
				<h1 class="my-4">My Page</h1>
				<div class="list-group">
					<a class="list-group-item">�� Business</a> <a href="businessIt.jsp"
						class="list-group-item">&emsp;&emsp;�� IT</a> <a
						href="businessSalesMarketing.jsp" class="list-group-item">&emsp;&emsp;��
						Sales / Marketing</a> <a href="businessMedia.jsp"
						class="list-group-item">&emsp;&emsp;�� Media</a> <a
						href="businessPlus.jsp" class="list-group-item">&emsp;&emsp;��
						Plus</a> <a href="coolTips.jsp" class="list-group-item">�� Cool
						Tips</a> <a href="mypage.jsp" class="list-group-item">�� My Page</a>
				</div>
			</div>
			<!-- /.Side Menu -->

			<!-- Search bar -->
			<div class="col-lg-9">
				<label for="exampleInputEmail1">&nbsp;</label> <br> <label
					for="exampleInputEmail1">&nbsp;</label> <br>
				<form action="<%= CONTEXT_PATH %>/search.jsp">
					<div class="form-row">
						<div class="col-12 col-md-2">
							<div class="dropdown">
								<button type="button" class="btn btn-primary dropdown-toggle"
									data-toggle="dropdown">����</button>
								<div class="dropdown-menu">
									<a class="dropdown-item" href="#">����</a> <a
										class="dropdown-item" href="#">����</a> <a class="dropdown-item"
										href="#">�ۼ���</a>
								</div>
							</div>
						</div>

						<div class="col-12 col-md-8 mb-2 mb-md-0">
							<input type="text" class="form-control form-control-lg"
								placeholder="# 5G # 1�� KT ... ">
						</div>

						<div class="col-12 col-md-2">
							<input type="button" class="btn btn-block btn-lg btn-primary"
								onclick="location.href='search.jsp'" value="search">
						</div>
					</div>
				</form>
				<div class="form-row">
					<div class="float-right">
						<button type="submit" class="btn btn-block btn-lg btn-primary">Hash
							Tag</button>
					</div>
				</div>

				<!-- Table -->
				<label for="exampleInputEmail1">&nbsp;</label> <br> <label
					for="exampleInputEmail1">&nbsp;</label> <br> <label
					for="exampleInputEmail1">&nbsp;</label> <br>
				<div class="container">
					<ol class="breadcrumb">
						<li class="breadcrumb-item">My page</li>
						<li class="breadcrumb-item active">My scraps</li>
					</ol>
				</div>
				<div class="container" class="jumbotron">
					<table class="table table-hover">
						<thead>
							<tr class="table-active">
								<th scope="col">�ֽż�</th>
								<th scope="col">����</th>
								<th scope="col">�ۼ���</th>
								<th scope="col">��ȸ��</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th scope="row">1</th>
								<td><a href="#" class="alert-link"> ��ũ�� ������ ���;���!</a></td>
								<td>Ȳ����</td>
								<td>11</td>
							</tr>
							<tr>
								<th scope="row">2</th>
								<td><a href="#" class="alert-link">Column content</a></td>
								<td>Column content</td>
								<td>Column content</td>
							</tr>
							<tr>
								<th scope="row">3</th>
								<td><a href="#" class="alert-link">Column content</a></td>
								<td>Column content</td>
								<td>Column content</td>
							</tr>
						</tbody>
					</table>
					<button type="submit" class="btn btn-block btn-lg btn-primary"
						onclick="location.href=#">See more</button>
				</div>
				<!-- /.Table -->
				<!-- Table -->
				<label for="exampleInputEmail1">&nbsp;</label> <br> <label
					for="exampleInputEmail1">&nbsp;</label> <br> <label
					for="exampleInputEmail1">&nbsp;</label> <br>
				<div class="container">
					<ol class="breadcrumb">
						<li class="breadcrumb-item">My page</li>
						<li class="breadcrumb-item active">My scraps</li>
					</ol>
				</div>
				<div class="container" class="jumbotron">
					<table class="table table-hover">

						<thead>
							<tr class="table-active">
								<th scope="col">�ֽż�</th>
								<th scope="col">����</th>
								<th scope="col">�ۼ���</th>
								<th scope="col">��ȸ��</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th scope="row">1</th>
								<td><a href="#" class="alert-link"> ���̰� ���� �̷����� �̷��� �ϴ�
										���� ������ �����ٸ��� �̷��� �ϴٰ� ��</a></td>
								<td>Ȳ����</td>
								<td>11</td>
							</tr>
							<tr>
								<th scope="row">2</th>
								<td><a href="#" class="alert-link">Column content</a></td>
								<td>Column content</td>
								<td>Column content</td>
							</tr>
							<tr>
								<th scope="row">3</th>
								<td><a href="#" class="alert-link">Column content</a></td>
								<td>Column content</td>
								<td>Column content</td>
							</tr>

						</tbody>
					</table>
					<button type="submit" class="btn btn-block btn-lg btn-primary"
						onclick="location.href=#">See more</button>
				</div>
				<!-- /.Table -->
				<div id="page-wrapper">
					<div class="row">
						<div class="col-lg-12">
							<h1 class="page-header">Dashboard</h1>
						</div>
						<!-- /.col-lg-12 -->
					</div>
					<!-- /.row -->
					<div class="row">
						<div class="col-lg-3 col-md-6">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-3">
											<i class="fa fa-comments fa-5x"></i>
										</div>
										<div class="col-xs-9 text-right">
											<div class="huge">26</div>
											<div>New Comments!</div>
										</div>
									</div>
								</div>
								<a href="#">
									<div class="panel-footer">
										<span class="pull-left">View Details</span> <span
											class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
										<div class="clearfix"></div>
									</div>
								</a>
							</div>
						</div>
						<div class="col-lg-3 col-md-6">
							<div class="panel panel-green">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-3">
											<i class="fa fa-tasks fa-5x"></i>
										</div>
										<div class="col-xs-9 text-right">
											<div class="huge">12</div>
											<div>New Tasks!</div>
										</div>
									</div>
								</div>
								<a href="#">
									<div class="panel-footer">
										<span class="pull-left">View Details</span> <span
											class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
										<div class="clearfix"></div>
									</div>
								</a>
							</div>
						</div>
						<div class="col-lg-3 col-md-6">
							<div class="panel panel-yellow">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-3">
											<i class="fa fa-shopping-cart fa-5x"></i>
										</div>
										<div class="col-xs-9 text-right">
											<div class="huge">124</div>
											<div>New Orders!</div>
										</div>
									</div>
								</div>
								<a href="#">
									<div class="panel-footer">
										<span class="pull-left">View Details</span> <span
											class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
										<div class="clearfix"></div>
									</div>
								</a>
							</div>
						</div>
						<div class="col-lg-3 col-md-6">
							<div class="panel panel-red">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-3">
											<i class="fa fa-support fa-5x"></i>
										</div>
										<div class="col-xs-9 text-right">
											<div class="huge">13</div>
											<div>Support Tickets!</div>
										</div>
									</div>
								</div>
								<a href="#">
									<div class="panel-footer">
										<span class="pull-left">View Details</span> <span
											class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
										<div class="clearfix"></div>
									</div>
								</a>
							</div>
						</div>
					</div>
					<!-- /.row -->
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