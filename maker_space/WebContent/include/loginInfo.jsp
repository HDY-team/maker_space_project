<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<% if(session.getAttribute("grade").equals("G") && session.getAttribute("name")!=null) { %>
<body3> <h5 class="navbar-brand"><%=session.getAttribute("company")%> <%=session.getAttribute("name")%></h5></body3>
<% 	}else if(session.getAttribute("grade").equals("A") && session.getAttribute("name")!=null) { %>
<body3> <h5 class="navbar-brand"> 관리자 <%=session.getAttribute("name")%></h5></body3>
<%	} else {}  %>

<% if(session.getAttribute("name")!=null) { %>
<form method="post" action="membercontroller"
	class="form-inline my-2 my-lg-0">
	<input type="hidden" name="action" value="logout">
	<button type="submit" class="btn btn-secondary my-2 my-sm-0">Logout</button>
</form>
<% 	}else { %>
<form class="form-inline my-2 my-lg-0">
	<button type="button" class="btn btn-secondary my-2 my-sm-0"
		onclick="location.href='login.jsp'">Sign in</button>
</form>
&nbsp; &nbsp;
<form class="form-inline my-2 my-lg-0">
	<button type="button" class="btn btn-secondary my-2 my-sm-0"
		onclick="location.href='register.jsp'">Sign up</button>
</form>
<%	}  %>

<!-- Bootstrap core JavaScript -->
	<script src="./Resource/mms/vendor/jquery/jquery.min.js"></script>
	<script src="./Resource/mms/vendor/jquery/jquery.slim.min.js"></script>
	<script
		src="./Resource/mms/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="./Resource/mms/vendor/bootstrap/js/bootstrap.min.js"></script>