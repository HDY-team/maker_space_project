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
					<a class="list-group-item">◎ Business</a> <a href="businessIt.jsp"
						class="list-group-item">&emsp;&emsp;▶ IT</a> <a
						href="businessSalesMarketing.jsp" class="list-group-item">&emsp;&emsp;▶
						Sales / Marketing</a> <a href="businessMedia.jsp"
						class="list-group-item">&emsp;&emsp;▶ Media</a> <a
						href="businessPlus.jsp" class="list-group-item">&emsp;&emsp;▶
						Plus</a> <a href="coolTips.jsp" class="list-group-item">◎ Cool
						Tips</a> <a href="mypage.jsp" class="list-group-item">◎ My Page</a>
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
				<div class="container">
					<table class="table table-hover" style="border: solid 2px #cccccc">
						<thead>
							<tr class="table-active">
								<th scope="col"></th>
								<th scope="col">Title</th>
								<th scope="col">Name</th>
								<th scope="col">Write date</th>
								<th scope="col">Hits</th>
							</tr>
						</thead>

						<c:if test="${map.lists.size()==0 }">
							<tfoot>
								<tr align="center">
									<td width="5"></td>
									<td width="250">현재 게시글이 존재하지 않습니다!</td>
									<td width="50"></td>
									<td width="10"></td>
									<td width="5"></td>
								</tr>
							</tfoot>
						</c:if>
						<tbody>
							<c:forEach items="${map.lists}" var="dto">
								<tr>
									<td width="5"><c:out value="${dto.businessIdx}" /></td>
									<td width="250"><a
										href="boardcontroller?action=getBoard&category=it&boardIndex=${dto.businessIdx}"><c:out
												value="${dto.title}" /></a></td>
									<td width="50"><c:out value="${dto.name}" /></td>
									<td width="10"><c:out value="${dto.writeDate}" /></td>
									<td width="5"><c:out value="${dto.hits}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div align="center">
					<!-- Paginatoin -->
					<div style="display: inline-block; vertical-align: middle;">
						<ul class="pagination">
							<c:if test="${map.prevPage <= 0}">
								<li class="page-item disabled"><a class="page-link">&laquo;</a>
								</li>
							</c:if>
							<c:if test="${map.prevPage > 0}">
								<li class="page-item"><a class="page-link"
									href="boardcontroller?action=getBoards&page=${map.prevPage}&category=it&field=${map.field }">&laquo;</a>
								</li>
							</c:if>
							<c:if test="${map.pageCount <= 5}">
								<c:forEach begin="${map.beginPage}"
									end="${map.beginPage + map.pageCount - 1}" var="page">
									<c:choose>
										<c:when test="${map.currentPage == page}">
											<li class="page-item active"><a class="page-link"
												href="#">${page}</a>
										</c:when>
										<c:otherwise>
											<li class="page-item"><a class="page-link"
												href="boardcontroller?action=getBoards&page=${page}&field=${map.field}&category=it">${page}</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:if>
							<c:if test="${map.pageCount > 5}">
								<c:forEach begin="${map.beginPage}" end="${map.endPage}"
									var="page">
									<c:choose>
										<c:when test="${map.currentPage == page}">
											<li class="page-item active"><a class="page-link"
												href="#">${page}</a>
										</c:when>
										<c:otherwise>
											<li class="page-item"><a class="page-link"
												href="boardcontroller?action=getBoards&page=${page}&field=${map.field}&category=it">${page}</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:if>
							<c:if test="${map.nextPage <= 0}">
								<li class="page-item disabled"><a class="page-link">&raquo;</a>
								</li>
							</c:if>
							<c:if test="${map.nextPage > 0}">
								<li class="page-item"><a class="page-link"
									href="boardcontroller?action=getBoards&page=${map.nextPage}&field=${map.field }&category=it">&raquo;</a>
								</li>
							</c:if>
						</ul>
					</div>
					<form method="post" action="write.jsp">
						<input type="hidden" name="category" value="it"> <input
							type="submit" class="btn btn-block-sm btn-lg btn-primary"
							style="display: inline-block;" value="Write" />
					</form>
				</div>
				<div align="center">
					<input type="text" class="form-control-my"
						placeholder="# 5G # 1등 KT ... "
						style="margin-right: 20px; vertical-align: middle; display: inline-block;">
					<button type="button" class="btn btn-primary dropdown-toggle"
						data-toggle="dropdown">선택</button>
					<div class="dropdown-menu" style="display: inline-block;">
						<a class="dropdown-item" href="#">제목</a> <a class="dropdown-item"
							href="#">내용</a> <a class="dropdown-item" href="#">작성자</a>
					</div>
					<input type="button" class="btn btn-block-sm btn-lg-my btn-primary"
						onclick="location.href='search.jsp'" value="Search"
						style="margin-right: 20px; display: inline-block;"> <label>&nbsp;</label>
					<br>
				</div>

				<form>
					<div align="right">
						<button type="submit"
							class="btn btn-block-sm btn-lg-my btn-primary"
							style="display: inline-block;">HashTag</button>
					</div>
				</form>
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