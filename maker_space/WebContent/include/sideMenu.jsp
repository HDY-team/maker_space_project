<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<ul class="nav nav-pills flex-column">
	<%
if(session.getAttribute("grade").equals("A")) {
%>
	<li class="nav-item"><a class="nav-link active">Member Info</a></li>
	<li class="table-active"><a class="nav-link"
		href="membercontroller?action=getAllInfoByAdmin">Member
			Information</a></li><br>
	<%
} else {}
%>
	<li class="nav-item"><a class="nav-link active">Business</a></li>
	<li class="table-active"><a class="nav-link" href="boardcontroller?action=getBoards&category=it">IT</a></li>
	<li class="table-active"><a class="nav-link" href="boardcontroller?action=getBoards&category=market">Sales / Marketing</a></li>
	<li class="table-active"><a class="nav-link" href="boardcontroller?action=getBoards&category=media">Media</a></li>
	<li class="table-active"><a class="nav-link" href="boardcontroller?action=getBoards&category=etc">Plus</a></li>
	<br>
	<li class="nav-item"><a class="nav-link active"
		href="coolTips.jsp">Cool Tips</a></li>
	<br>
	<li class="nav-item"><a class="nav-link active" href="myPage.jsp">My
			Page</a>
	<li class="table-active"><a class="nav-link" href="boardcontroller?action=getBoards&category=myIdea" >My Idea</a>
	<li class="table-active"><a class="nav-link" href="boardcontroller?action=getBoards&category=scrap">My
			Scraps</a></li>
	<li class="table-active"><a class="nav-link" href="boardcontroller?action=getBoards&category=select">My
			Process</a></li>
	<li class="table-active"><a class="nav-link"
		href="membercontroller?action=getMyInfo">My Account</a>
</ul>

<!-- Bootstrap core JavaScript -->
	<script src="./Resource/mms/vendor/jquery/jquery.min.js"></script>
	<script src="./Resource/mms/vendor/jquery/jquery.slim.min.js"></script>
	<script
		src="./Resource/mms/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="./Resource/mms/vendor/bootstrap/js/bootstrap.min.js"></script>