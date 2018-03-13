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
		String category = request.getParameter("category");
	%>
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
			<h5 class="navbar-brand"><%=session.getAttribute("company")%>
				<%=session.getAttribute("name")%></h5>
			<form class="form-inline my-2 my-lg-0">
				<button type="button" class="btn btn-secondary my-2 my-sm-0"
					onclick="location.href='membercontroller?action=logout'">Logout</button>
			</form>
		</div>
	</nav>
	<%
		String title = (String) request.getAttribute("title");
		String hashTag = (String) request.getAttribute("hashTag");
		String result = (String) request.getAttribute("result");
		String content = (String) request.getAttribute("content");
		String files = (String) request.getAttribute("files");
	%>
	<!-- edit Form -->
	<div class="container">
		<label for="exampleInputEmail1">&nbsp;</label> <br> <label
			for="exampleInputEmail1">&nbsp;</label> <br>

		<form id="writeForm" method="post" action="boardcontroller">
			<input type="hidden" name="businessBoardsIdx"
				value=<%=request.getAttribute("businessBoardsIdx")%>> <input
				type="hidden" name="action" value="edit"> <input
				type="hidden" name="category" value="it"> <input
				type="submit" class="btn btn-block-lg btn-lg btn-primary"
				style="display: inline-block;" value="Complete" />
			<button type="button" class="btn btn-secondary my-2 my-sm-0"
				onclick="location.href='javascript:history.back()'">Cancel</button>
			<fieldset>
				<div class="form-group">
					<label for="exampleSelect1">Title</label> <input type="text"
						class="form-control" name="title"
						value=<c:if test="${!empty requestScope.title}"> 
									 <%=title%>
									 </c:if>>
				</div>
				<div class="form-group">
					<label for="exampleSelect1">Hash Tag</label> <input type="text"
						class="form-control" name="hash_tag"
						value=<c:if test="${!empty requestScope.hashTag}"> 
									 <%=hashTag%>
									 </c:if>>
				</div>
				<div class="form-group">
					<label for="exampleTextarea">Expectation Effectiveness</label>
					<textarea class="form-control" name="result" rows="6"><c:if
							test="${!empty requestScope.result}"><%=result%></c:if></textarea>
				</div>
				<div class="form-group">
					<label for="exampleTextarea">Content</label>
					<textarea class="form-control" name="content" rows="10"><c:if
							test="${!empty requestScope.content}"><%=content%></c:if></textarea>
				</div>
				<div class="form-group">
					<label for="exampleInputFile">File</label><input type="file"
						class="form-control-file" name="file" readonly="readonly"
						aria-describedby="fileHelp"><small id="fileHelp"
						class="form-text text-muted">File max capacity</small>
				</div>
			</fieldset>
		</form>
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