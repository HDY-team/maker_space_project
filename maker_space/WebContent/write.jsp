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
	<% String category = request.getParameter("category");%>
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
			<h5 class="navbar-brand"><%= session.getAttribute("company")%> <%=session.getAttribute("name")%></h5>
			<form class="form-inline my-2 my-lg-0">
				<button type="button" class="btn btn-secondary my-2 my-sm-0"
					onclick="location.href='membercontroller?action=logout'">Logout</button>
			</form>
		</div>
	</nav>
	<!-- Writig Form -->
	<div class="container">
		<label for="exampleInputEmail1">&nbsp;</label> <br> <label
			for="exampleInputEmail1">&nbsp;</label> <br>
		<form id="writeForm" method="post" action="boardcontroller">
			<fieldset>
				<legend>Writing</legend>
				<div class="form-group">
					<label for="exampleSelect1">Title</label> <input type="text"
						class="form-control" name="title" placeholder="Enter title">
				</div>
				<div class="form-group">
					<label for="exampleSelect1">Hash Tag</label> <input type="text"
						class="form-control" name="hash_tag" placeholder="Enter hash tag">
				</div>
				<div class="form-group">
					<label for="exampleTextarea">Expectation Effectiveness</label>
					<textarea class="form-control" name="result" rows="6"
						form="writeForm" placeholder="Enter expectation effectiveness"></textarea>
				</div>
				<div class="form-group">
					<label for="exampleTextarea">Content</label>
					<textarea class="form-control" name="content" rows="10"
						form="writeForm" placeholder="Enter content"></textarea>
				</div>
				<div class="form-group">
					<label for="exampleInputFile">File input</label> <input type="file"
						class="form-control-file" name="file" aria-describedby="fileHelp">
					<small id="fileHelp" class="form-text text-muted">File max
						capacity</small>
				</div>
				<div class="text-center">
					<input type="hidden" name="category" value=<%=request.getParameter("category")%>>
					<input type="hidden" name="action" value="write">
					<button type="submit" class="btn btn-block-sm btn-lg btn-primary"
						style="display: inline-block;">Submit</button>
				</div>
			</fieldset>
		</form>
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