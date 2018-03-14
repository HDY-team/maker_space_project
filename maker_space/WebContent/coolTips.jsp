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
<style>
.a {
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}
</style>
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
            <h1 class="my-4">IT</h1>
            <%@include file="./include/sideMenu.jsp" %>
         </div>
         <!-- /.Side Menu -->



         <!-- Table -->
         <div class="col-lg-9">
            <h1 class="my-4">&nbsp;</h1>
            <div class="container">
               <ol class="breadcrumb">
                  <li class="breadcrumb-item">Cool Tips</li>
                  <li class="breadcrumb-item active">Tips</li>
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
								<th scope="col">Scraps</th>
							</tr>
						</thead>

						<c:if test="${tipMap.lists.size()==0 }">
							<tfoot>
								<tr align="center">
									<td width="5"></td>
									<td width="240">현재 게시글이 존재하지 않습니다!</td>
									<td width="50"></td>
									<td width="10"></td>
									<td width="5"></td>
									<td width="5"></td>
								</tr>
							</tfoot>
						</c:if>
						<tbody>
							<c:forEach items="${tipMap.lists}" var="dto" varStatus="status">
								<tr>
									<td width="5"><c:out value="${tipMap.pageTotalCount - status.index}" /></td>
									<td width="240"><a
										href="tipboardcontroller?action=getTipBoard&category=tips&tipBoardsIdx=${dto.tipIdx}"><c:out
												value="${dto.title}" /></a></td>
									<td width="50"><c:out value="${dto.name}" /></td>
									<td width="10"><c:out value="${dto.writeDate}" /></td>
									<td width="5"><c:out value="${dto.hits}" /></td>
									<td width="5"><c:out value="${dto.scraps}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div align="center">
					<!-- Paginatoin -->
					<div style="display: inline-block; vertical-align: middle;">
						<ul class="pagination">
							<c:if test="${tipMap.prevPage <= 0}">
								<li class="page-item disabled"><a class="page-link">&laquo;</a>
								</li>
							</c:if>
							<c:if test="${tipMap.prevPage > 0}">
								<li class="page-item"><a class="page-link"
									href="boardcontroller?action=getTipBoards&page=${tipMap.prevPage}&category=tips&field=${tipMap.field}">&laquo;</a>
								</li>
							</c:if>
							<c:if test="${tipMap.pageCount <= 5}">
								<c:forEach begin="${tipMap.beginPage}"
									end="${tipMap.beginPage + tipMap.pageCount - 1}" var="page">
									<c:choose>
										<c:when test="${tipMap.currentPage == page}">
											<li class="page-item active"><a class="page-link"
												href="#">${page}</a>
										</c:when>
										<c:otherwise>
											<li class="page-item"><a class="page-link"
												href="boardcontroller?action=getTipBoards&page=${page}&field=${tipMap.field}&category=tips">${page}</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:if>
							<c:if test="${tipMap.pageCount > 5}">
								<c:forEach begin="${tipMap.beginPage}" end="${tipMap.endPage}"
									var="page">
									<c:choose>
										<c:when test="${tipMap.currentPage == page}">
											<li class="page-item active"><a class="page-link"
												href="#">${page}</a>
										</c:when>
										<c:otherwise>
											<li class="page-item"><a class="page-link"
												href="boardcontroller?action=getBoards&page=${page}&field=${tipMap.field}&category=tips">${page}</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:if>
							<c:if test="${tipMap.nextPage <= 0}">
								<li class="page-item disabled"><a class="page-link">&raquo;</a>
								</li>
							</c:if>
							<c:if test="${tipMap.nextPage > 0}">
								<li class="page-item"><a class="page-link"
									href="boardcontroller?action=getTipBoards&page=${tipMap.nextPage}&field=${tipMap.field }&category=tips">&raquo;</a>
								</li>
							</c:if>
						</ul>
					</div>
					<form method="post" action="writeForTips.jsp">
						<input type="hidden" name="category" value="tips"> <input
							type="submit" class="btn btn-block-sm btn-lg btn-primary"
							style="display: inline-block;" value="Write" />
					</form>
				</div>
            <!-- Search bar -->
				<form id="searchForm" name="searchForm" method="post"
					action="tipboardcontroller">
					<input type="hidden" name="action" value="find">
					<input type="hidden" name="category" value="tips">
					<div class="form-row">
						<div class="col-12 col-md-2">
							<select id="searchMethod" name="searchMethod"
								class="btn btn-secondary my-2 my-sm-0">
								<option selected value=0>title
								<option value=1>content
								<option value=2>name
							</select>
						</div>

						<div class="col-12 col-md-8 mb-2 mb-md-0">
							<input id="searchContent" type="text" name="searchContent"
								class="form-control form-control-lg"
								>
						</div>
						<div class="col-12 col-md-2">
							<button id="searchBtn" type="submit"
								class="btn btn-secondary my-2 my-sm-0">Search</button>
						</div>
					</div>
				</form>
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