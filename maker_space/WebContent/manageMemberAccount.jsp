<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="work.model.dto.Member"%>
<%@ page import="work.util.MyUtility"%>
<%@ page import="java.util.ArrayList"%>
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

<%
	String CONTEXT_PATH = application.getContextPath();
%>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<%
			if (session.getAttribute("name") != null) {
		%>
		<a class="navbar-brand" href="mainService.jsp">MakerSpace</a>
		<%
			} else {
		%>
		<a class="navbar-brand" href="index.jsp">MakerSpace</a>
		<%
			}
		%>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarColor01">
			<ul class="navbar-nav mr-auto">

			</ul>
			<%@include file="./include/loginInfo.jsp"%>
		</div>
</nav>

<!-- Page Content -->
<div class="container">
	<div class="row">
		<!-- Side Menu -->
		<div class="col-lg-3">
			<h1 class="my-4">Member Account</h1>
			<%@include file="./include/sideMenu.jsp" %>
		</div>

		<!-- /.Side Menu -->

		<%
			ArrayList<Member> members = (ArrayList<Member>) request.getAttribute("members");
			System.out.println("\n## members : " + members);
		%>


		<!-- Table for search Member -->
		<div class="col-lg-9">

			<h1 class="my-4">&nbsp;</h1>
			<div class="container">
				<ol class="breadcrumb">
					<li class="breadcrumb-item">Members Information</li>
					<li class="breadcrumb-item active">Search & Delete</li>
				</ol>

			</div>
			<div class="container">
				<table class="table table-hover">
					<thead>
						<tr class="table-active">
							<th scope="col">Company</th>
							<th scope="col">Name</th>
							<th scope="col">email</th>
							<th scope="col">grade</th>
							<th scope="col">&emsp;&emsp;</th>
						</tr>
					</thead>
					<%
						Member dto = null;
						for (int index = 0; index < members.size(); index++) {
							dto = members.get(index);
					%>

					<tbody>
						<tr>
							<!-- 이름 클릭시 해당 회원 상세조회 -->
							
							<td><%=dto.getCompany()%></td>
							<td><%=dto.getName()%></td>
							<td><a
								href="membercontroller?action=getInfoByAdmin&email=<%=dto.getEmail()%>"><%=dto.getEmail()%>
							</a></td>
							<td><%= dto.getGrade() %></td>
							<td>
							
							<a href="membercontroller?action=removeMemberByAdmin&email=<%= dto.getEmail() %>">	delete 	</a>
							</td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</div>
		</div>

		<!-- / Table -->

		<!-- Search bar -->
		<form>
			<div class="form-row">
				<div class="col-12 col-md-2">
					<div class="dropdown">
						<button type="button" class="btn btn-primary dropdown-toggle"
							data-toggle="dropdown">선택사항</button>
						<div class="dropdown-menu">
							<a class="dropdown-item" href="#">제목</a> <a class="dropdown-item"
								href="#">내용</a> <a class="dropdown-item" href="#">작성자</a>
						</div>
					</div>
				</div>

				<div class="col-12 col-md-8 mb-2 mb-md-0">
					<input type="text" class="form-control form-control-lg"
						placeholder="# 5G # 1등 KT ... ">
				</div>

				<div class="col-12 col-md-2">
					<form class="form-inline my-2 my-lg-0">
						<button type="button" class="btn btn-secondary my-2 my-sm-0"
							onclick="location.href='search.jsp'">Search</button>
					</form>
				</div>
			</div>
		</form>
		<div class="form-row">
			<div class="float-right">
				<button type="submit" class="btn btn-block btn-lg btn-primary">Hash
					Tag</button>
			</div>
		</div>
		
	</div>
</div>
<!-- /.Search -->

<%@ include file="./include/footer.jsp"%>

<!-- Bootstrap core JavaScript -->
<script src="./Resource/mms/vendor/jquery/jquery.min.js"></script>
<script src="./Resource/mms/vendor/jquery/jquery.slim.min.js"></script>
<script src="./Resource/mms/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="./Resource/mms/vendor/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>