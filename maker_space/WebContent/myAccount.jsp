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
            <h1 class="my-4">My Account</h1>
            <%@include file="./include/sideMenu.jsp" %>
         </div>

         <!-- /.Side Menu -->

         <!-- My Account -->
         <div class="col-lg-9">
            <label for="exampleInputEmail1">&nbsp;</label> <br> <label
               for="exampleInputEmail1">&nbsp;</label> <br>

            <div class="card-body">
				<form action="<%= CONTEXT_PATH %>/search.jsp">
                  <div class="form-group">
                     <div class="form-row">
                        <div class="col-md-6">
                           <label for="exampleInputName">Name</label> 
                          	<ol class="breadcrumb">
  							<%= request.getAttribute("name") %>
							</ol>
                        </div>
                     </div>
                     <div class="form-row">
                        <div class="col-md-6">
                           <label for="exampleInputMobile">Phone Number</label> 
                            <ol class="breadcrumb">
  							<%= request.getAttribute("mobile") %>
							</ol>
                        </div>
                     </div>
                     <div class="form-row">
                        <div class="col-md-6">
                           <label for="exampleInputEmail1">Email address</label> 
                            <ol class="breadcrumb">
  							<%= request.getAttribute("email") %>
							</ol>
                        </div>
                     </div>
                     <div class="form-row">
                        <div class="col-md-6">
                           <label for="exampleInputPassword1">Password</label> 
                            <ol class="breadcrumb">
  							<%= request.getAttribute("password") %>
							</ol>
                        </div>
                     </div>
                     <div class="form-row">
                        <div class="col-md-6">
                           <label for="exampleInputCompany">Company</label>
                           	<ol class="breadcrumb">
  							<%= request.getAttribute("company") %>
							</ol>
                        </div>
                     </div>
                  </div>
                 
                   <div class="form-row">
                  	<div class="col-12 col-md-5">
                        <input type="button" class="btn btn-block btn-lg btn-primary" onclick="location.href='editMyAccount.jsp'"
                               value="Edit my account">
                 	</div>
                    <div class="col-12 col-md-5">
                        <input type="button" class="btn btn-block btn-lg btn-primary"
                              onclick="location.href='deleteMyAccount.jsp'" value="Delete my account">
                    </div>
                   </div>
                   
            	</form>
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