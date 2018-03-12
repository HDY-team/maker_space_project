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
<link href="./Resource/mms/vendor/bootstrap/css/bootstrap2.min.css"
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

<body class="" id="">

	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary" id="">
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
			data-target="#navbarColor01" aria-controls="navbarColor01"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarColor01">
			<ul class="navbar-nav mr-auto">
			</ul>
			<%@include file="./include/loginInfo.jsp"%>
		</div>
	</nav>


	<nav class="navbar navbar-expand-lg navbar-dark bg-primary2" id="">
		<div class="container">
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav mx-auto">
					<li class="nav-item active px-lg-4"><a
						class="nav-link text-uppercase text-expanded"
						href="mainService.jsp">Home <span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item px-lg-4"><a
						class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
						href="#coolTips">Tips</a></li>
					<li class="nav-item px-lg-4"><a
						class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
						href="#business">Business</a></li>

				</ul>
			</div>
		</div>
	</nav>


	<!-- Masthead -->
	<header class="masthead text-white text-center">
		<div class="overlay"></div>
		<div class="container">
			<div class="row">
				<div class="col-xl-9 mx-auto">
					<h1 class="mb-5">Get and Share Idea!</h1>
				</div>
				<div class="col-md-10 col-lg-8 col-xl-7 mx-auto">
					<form>
						<div class="form-row">
							<div class="col-12 col-md-9 mb-2 mb-md-0">
								<input type="text" class="form-control form-control-lg"
									placeholder="# 5G # 1등 KT ... ">
							</div>


							<div class="col-12 col-md-3">
								<input type="button" class="btn btn-block btn-lg btn-primary"
									onclick="location.href='search.jsp'" value="Search">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>

	</header>

	<!-- 꿀팁 table -->
   <label for="exampleInputEmail1">&nbsp;</label>
   <br>
   <section class="coolTips" id="coolTips">
      <div class="container">
         <div class="alert alert-dismissible alert-primary">
            <h2 class="alert-heading">
               <a href="coolTips.jsp" class="alert-link">Monthly Tips Best 5</a>
            </h2>
         </div>
      </div>
      <div class="container">
         <table class="table table-hover">
            <thead>
               <tr class="table-active">
                  <th scope="col">순위</th>
                  <th scope="col">제목</th>
                  <th scope="col">작성자</th>
                  <th scope="col">조회수</th>
               </tr>
            </thead>
            <tbody>
               <tr>
                  <th scope="row">1</th>
                  <td><a href="#" class="alert-link"> 아이고 세상에 이런일이</a></td>
                  <td>황보영</td>
                  <td>11</td>
               </tr>
               <tr>
                  <th scope="row">2</th>
                  <td>Column content</td>
                  <td>Column content</td>
                  <td>Column content</td>
               </tr>
               <tr>
                  <th scope="row">3</th>
                  <td>Column content</td>
                  <td>Column content</td>
                  <td>Column content</td>
               </tr>
               <tr>
                  <th scope="row">4</th>
                  <td>Column content</td>
                  <td>Column content</td>
                  <td>Column content</td>
               </tr>
               <tr>
                  <th scope="row">5</th>
                  <td>Column content</td>
                  <td>Column content</td>
                  <td>Column content</td>
               </tr>
            </tbody>
         </table>
      </div>
   </section>

   <!-- 비즈니스 Icons Grid -->
   
   <label for="exampleInputEmail1">&nbsp;</label>
   <br>
   <section class="business" id="business">
      <div class="container">
         <div class="alert alert-dismissible alert-primary">
            <h2 class="alert-heading">
               <h2 class="alert-link">Business</h2>
            </h2>
         </div>
      </div>
      <section class="testimonials text-center bg-light">
         <div class="container">
            <div class="row">
               <div class="col-lg-3">
                  <div class="testimonial-item mx-auto mb-8 mb-lg-0">
                     <img class="img-fluid rounded-circle mb-5"
                        src="./Resource/mms/img/it.png" alt="">
                     <h3>
                       <a href="boardcontroller?action=getBoards&category=it" class="alert-link">IT</a>
                     </h3>
                  </div>
               </div>
               <div class="col-lg-3">
                  <div class="testimonial-item mx-auto mb-5 mb-lg-0">
                     <img class="img-fluid rounded-circle mb-5"
                        src="./Resource/mms/img/sales&marketing.png" alt="">
                     <h3>
                        <a href="businessSalesMarketing.jsp" class="alert-link">SALES
                           & MARKETING</a>
                     </h3>
                  </div>
               </div>
               <div class="col-lg-3">
                  <div class="testimonial-item mx-auto mb-5 mb-lg-0">
                     <img class="img-fluid rounded-circle mb-5"
                        src="./Resource/mms/img/media.png" alt="">
                     <h3>
                        <a href="businessMedia.jsp" class="alert-link">MEDIA</a>
                     </h3>
                  </div>
               </div>
               <div class="col-lg-3">
                  <div class="testimonial-item mx-auto mb-5 mb-lg-0">
                     <img class="img-fluid rounded-circle mb-5"
                        src="./Resource/mms/img/plus.png" alt="">
                     <h3>
                        <a href="businessPlus.jsp" class="alert-link">PLUS</a>
                     </h3>
                  </div>
               </div>
            </div>
         </div>
      </section>
   </section>
   	<%@include file="./include/footer.jsp" %>

	<!-- Bootstrap core JavaScript -->
	<script src="./Resource/mms/vendor/jquery/jquery.min.js"></script>
	<script
		src="./Resource/mms/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>

</html>