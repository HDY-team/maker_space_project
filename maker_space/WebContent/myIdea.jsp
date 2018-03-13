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
		%>      <button class="navbar-toggler" type="button" data-toggle="collapse"
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
            <h1 class="my-4">My Idea</h1>
            <%@include file="./include/sideMenu.jsp" %>
         </div>
         <!-- /.Side Menu -->
         <!-- Table -->
         <div class="col-lg-9">
            <h1 class="my-4">&nbsp;</h1>
            <div class="container">
               <ol class="breadcrumb">
                  <li class="breadcrumb-item">My page</li>
                  <li class="breadcrumb-item active">Idea</li>
               </ol>

            </div>
				<div class="container">
					<table class="table table-hover">
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
										href="boardcontroller?action=getBoard&category=myIdea&boardIndex=${dto.businessIdx}"><c:out
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
									href="boardcontroller?action=getBoard&page=${map.prevPage}&category=myIdea&field=${map.field }">&laquo;</a>
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
												href="boardcontroller?action=getBoard&page=${page}&field=${map.field}&category=myIdea">${page}</a></li>
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
												href="boardcontroller?action=getBoard&page=${page}&field=${map.field}&category=myIdea">${page}</a></li>
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
									href="boardcontroller?action=getBoard&page=${map.nextPage}&field=${map.field }&category=myIdea">&raquo;</a>
								</li>
							</c:if>
						</ul>
					</div>
			<ol class="breadcrumb">
            	<li class="breadcrumb-item">My page</li>
                <li class="breadcrumb-item active">Tips</li>
            </ol>
            </div>
				<div class="container">
					<table class="table table-hover">
						<thead>
							<tr class="table-active">
								<th scope="col"></th>
								<th scope="col">Title</th>
								<th scope="col">Name</th>
								<th scope="col">Write date</th>
								<th scope="col">Hits</th>
							</tr>
						</thead>

						<c:if test="${map2.lists.size()==0 }">
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
							<c:forEach items="${map2.lists}" var="dto">
								<tr>
									<td width="5"><c:out value="${dto.businessIdx}" /></td>
									<td width="250"><a
										href="boardcontroller?action=getBoard&category=myTips&boardIndex=${dto.businessIdx}"><c:out
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
							<c:if test="${map2.prevPage <= 0}">
								<li class="page-item disabled"><a class="page-link">&laquo;</a>
								</li>
							</c:if>
							<c:if test="${map2.prevPage > 0}">
								<li class="page-item"><a class="page-link"
									href="boardcontroller?action=getBoard&page=${map2.prevPage}&category=myTips&field=${map2.field }">&laquo;</a>
								</li>
							</c:if>
							<c:if test="${map2.pageCount <= 5}">
								<c:forEach begin="${map2.beginPage}"
									end="${map2.beginPage + map2.pageCount - 1}" var="page">
									<c:choose>
										<c:when test="${map2.currentPage == page}">
											<li class="page-item active"><a class="page-link"
												href="#">${page}</a>
										</c:when>
										<c:otherwise>
											<li class="page-item"><a class="page-link"
												href="boardcontroller?action=getBoard&page=${page}&field=${map2.field}&category=myTips">${page}</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:if>
							<c:if test="${map2.pageCount > 5}">
								<c:forEach begin="${map2.beginPage}" end="${map2.endPage}"
									var="page">
									<c:choose>
										<c:when test="${map2.currentPage == page}">
											<li class="page-item active"><a class="page-link"
												href="#">${page}</a>
										</c:when>
										<c:otherwise>
											<li class="page-item"><a class="page-link"
												href="boardcontroller?action=getBoard&page=${page}&field=${map2.field}&category=myTips">${page}</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:if>
							<c:if test="${map2.nextPage <= 0}">
								<li class="page-item disabled"><a class="page-link">&raquo;</a>
								</li>
							</c:if>
							<c:if test="${map2.nextPage > 0}">
								<li class="page-item"><a class="page-link"
									href="boardcontroller?action=getBoard&page=${map2.nextPage}&field=${map2.field }&category=myTips">&raquo;</a>
								</li>
							</c:if>
						</ul>
					</div>
            <!-- Search bar -->
            <form>
               <div class="form-row">
                  <div class="col-12 col-md-2">
                     <div class="dropdown">
                        <button type="button" class="btn btn-primary dropdown-toggle"
                           data-toggle="dropdown">선택사항</button>
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
                     <form class="form-inline my-2 my-lg-0">
                        <button type="button" class="btn btn-secondary my-2 my-sm-0"
                           onclick="location.href='search.jsp'">Search</button>
                     </form>

                  </div>
               </div>
            </form>
            <div class="form-row">
               <div class="col-12 col-md-2">
                  <button type="submit" class="btn btn-block btn-lg btn-primary">Hash
                     Tag</button>
               </div>
            </div>

            <!-- /.Search -->
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