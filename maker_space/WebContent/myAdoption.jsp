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
			<% if(session.getAttribute("name")!=null) { %>
		<a class="navbar-brand" href="mainService.jsp">MakerSpace</a>
		<% 	}else { %>
		<a class="navbar-brand" href="index.jsp">MakerSpace</a>
		<%	}  %>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarColor01">
				<ul class="navbar-nav mr-auto">
				</ul>
				<%@include file="./include/loginInfo.jsp" %>
			</div>
	</nav>

	<!-- Page Content -->
	<div class="container">
		<div class="row">
			<!-- Side Menu -->
			<div class="col-lg-3">
				<h1 class="my-4">My Adoption</h1>
				<%@include file="./include/sideMenu.jsp" %>
			</div>
			<!-- /.Side Menu -->

			<div class="col-lg-9">
				<!-- Table -->
				<h1 class="my-4">&nbsp;</h1>
				<div class="container">
					<ol class="breadcrumb">
						<li class="breadcrumb-item">My Process</li>
						<li class="breadcrumb-item active">My Adoption</li>
					</ol>

				</div>
				<div class="container">
					<table class="table table-hover">
						<thead>
							<tr class="table-active">
								<th scope="col">최신순</th>
								<th scope="col">제목</th>
								<th scope="col">작성자</th>
								<th scope="col">조회수</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th scope="row">1</th>
								<td><a href="#" class="alert-link"> 아이고 세상에 이런일이 이렇게 하니
										정말 좋구나 만세다만세 이렇게 하다간 내</a></td>
								<td>황보영</td>
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
							<tr>
								<th scope="row">6</th>
								<td><a href="#" class="alert-link">Column content</a></td>
								<td>Column content</td>
								<td>Column content</td>
							</tr>
							<tr>
								<th scope="row">7</th>
								<td><a href="#" class="alert-link">Column content</a></td>
								<td>Column content</td>
								<td>Column content</td>
							</tr>
							<tr>
								<th scope="row">8</th>
								<td><a href="#" class="alert-link">Column content</a></td>
								<td>Column content</td>
								<td>Column content</td>
							</tr>
							<tr>
								<th scope="row">9</th>
								<td><a href="#" class="alert-link">Column content</a></td>
								<td>Column content</td>
								<td>Column content</td>
							</tr>
							<tr>
								<th scope="row">10</th>
								<td><a href="#" class="alert-link">Column content</a></td>
								<td>Column content</td>
								<td>Column content</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- /.Table -->

				<!-- 글쓰기 버튼 -->
				<a class="btn btn-block btn-lg btn-primary pull-right"
					onclick="location.href=#">삭제하기</a> <label
					for="exampleInputEmail1">&nbsp;</label> <br> <label
					for="exampleInputEmail1">&nbsp;</label> <br>

				<!-- Search bar -->
				<form>
					<div class="form-row">
						<div class="col-12 col-md-2">
							<div class="dropdown">
								<button type="button" class="btn btn-primary dropdown-toggle"
									data-toggle="dropdown">선택</button>
								<div class="dropdown-menu">
									<a class="dropdown-item" href="#">제목</a> <a
										class="dropdown-item" href="#">내용</a> <a class="dropdown-item"
										href="#">작성자</a>
								</div>
							</div>
						</div>

						<div class="col-12 col-md-8 mb-2 mb-md-0">
							<input type="text" class="form-control form-control-lg"
								placeholder="# 5G # 1등 KT ... ">
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


				<!-- Paginatoin -->
				<div class="text-center">
					<ul class="pagination">
						<li class="page-item disabled"><a class="page-link" href="#">&laquo;</a>
						</li>
						<li class="page-item active"><a class="page-link" href="#">1</a>
						</li>
						<li class="page-item"><a class="page-link" href="#">2</a></li>
						<li class="page-item"><a class="page-link" href="#">3</a></li>
						<li class="page-item"><a class="page-link" href="#">4</a></li>
						<li class="page-item"><a class="page-link" href="#">5</a></li>
						<li class="page-item"><a class="page-link" href="#">&raquo;</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<%@include file="./include/footer.jsp" %>

	<!-- Bootstrap core JavaScript -->
	<script src="./Resource/mms/vendor/jquery/jquery.min.js"></script>
	<script src="./Resource/mms/vendor/jquery/jquery.slim.min.js"></script>
	<script
		src="./Resource/mms/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="./Resource/mms/vendor/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
