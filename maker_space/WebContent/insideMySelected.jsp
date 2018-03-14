<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
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

.writeBtnLoca {
	text-algin: right;
}

.floatRight {
	float: right
}
</style>

</head>
<body>
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
				<h1 class="my-4">IT</h1>
				<%@include file="./include/sideMenu.jsp"%>
			</div>

			<div class="col-lg-9">
				<!-- Table -->
				<label for="exampleInputEmail1">&nbsp;</label> <br> <label
					for="exampleInputEmail1">&nbsp;</label> <br> <label
					for="exampleInputEmail1">&nbsp;</label> <br>
				<div class="container">
					<ol class="breadcrumb">
						<li class="breadcrumb-item">Business</li>
						<li class="breadcrumb-item active">채택 대기중</li>
					</ol>
				</div>
				<div class="floatRight">
					<form method="post" action="processcontroller">
						<input type="hidden" name="businessBoardsIdx"
							value=<%=request.getAttribute("businessBoardsIdx")%>> <input
							type="hidden" name="action" value="mySelectedCancel"> <input
							type="hidden" name="category" value="mySelected"> <input
							type="submit" class="btn btn-block-lg btn-lg btn-primary"
							style="display: inline-block;" value="취소하기" />
					</form>
				</div>
				<%
					String title = (String) request.getAttribute("title");
					String hashTag = (String) request.getAttribute("hashTag");
					String result = (String) request.getAttribute("result");
					String content = (String) request.getAttribute("content");
					String files = (String) request.getAttribute("files");
				%>

				<div class="container">
					<!-- writed form -->
					<div class="container">
						<fieldset>

							<div class="form-group">
								<label for="exampleSelect1">Title</label> <input type="text"
									readonly="readonly" class="form-control" name="title"
									value=<c:if test="${!empty requestScope.title}"> 
									 <%=title%>
									 </c:if>>
							</div>
							<div class="form-group">
								<label for="exampleSelect1">Hash Tag</label> <input type="text"
									readonly="readonly" class="form-control" name="hash_tag"
									value=<c:if test="${!empty requestScope.hashTag}"> 
									 <%=hashTag%>
									 </c:if>>
							</div>
							<div class="form-group">
								<label for="exampleTextarea">Expectation Effectiveness</label>
								<textarea class="form-control" name="result" rows="6"
									readonly="readonly"><c:if
										test="${!empty requestScope.result}"><%=result%></c:if></textarea>
							</div>
							<div class="form-group">
								<label for="exampleTextarea">Content</label>
								<textarea class="form-control" name="content" rows="10"
									readonly="readonly"><c:if
										test="${!empty requestScope.content}"><%=content%></c:if></textarea>
							</div>
							<div class="form-group">
								<label for="exampleInputFile">File</label><input type="file"
									class="form-control-file" name="file" readonly="readonly"
									aria-describedby="fileHelp"><small id="fileHelp"
									class="form-text text-muted">File max capacity</small>
							</div>
						</fieldset>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="./include/footer.jsp"%>


	<!-- Bootstrap core JavaScript -->
	<script src="./Resource/mms/vendor/jquery/jquery.min.js"></script>
	<script src="./Resource/mms/vendor/jquery/jquery.slim.min.js"></script>
	<script
		src="./Resource/mms/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="./Resource/mms/vendor/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>