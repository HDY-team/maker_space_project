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
<!-- jquery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

</head>
<script>
$(document).ready(function(){
	//인증코드를 메일로 발송하고 코드를 저장
	$("#auth").click(function() {
	   var email = $("#email").val();
	   if (email != "") {
	      $.ajax({
	         "url" : 'membercontroller',
	         "method" : 'post',
	         "data" : "email=" + email + "&action=confirmEmail",
	         success : function(data) {
	        	alert('Success send a code'); 
	            
	            $("#code")[0].value = data;
	            $("#auth").show();
	         }
	      })
	   } else {
	      alert('write your Email');
	   }
	});
})


</script>

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
            <h1 class="my-4">My Account</h1>
            <%@include file="./include/sideMenu.jsp" %>
         </div>

			<!-- /.Side Menu -->

			<!-- My Account -->
			         <div class="col-lg-9">
            <label for="exampleInputEmail1">&nbsp;</label> <br> <label
               for="exampleInputEmail1">&nbsp;</label> <br>

            <div class="card-body">
				<form method="post" action="membercontroller">
                  <div class="form-group">
                     
                     <div class="form-row">
                        <div class="col-md-6">
                           <label for="exampleInputName">Email</label> 
  							<input class="form-control" name="email" type="email" placeholder="email"><br>
                        </div>
                     </div>
                     <div class="form-row">
                        <div class="col-md-6">
                           <label for="exampleInputPassword1">Password</label> 
                            <input class="form-control" name="password" type="password" placeholder="original password">
                        </div>
                     </div>
                  </div>
               
				  
				  <form action="<%=CONTEXT_PATH%>/search.jsp">
                     <div class="form-row">
                        <div class="col-md-5">
                        <input type="hidden" name="action" value="removeMember">
                           <button type="submit" class="btn btn-block btn-lg btn-primary">delete</button>
                           </div>

                     </div>
                  </form>
            	</form>
            </div>
         </div>
         </div>
         </div>
         <%@include file="./include/footer.jsp" %>
</body>
</html>