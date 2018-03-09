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
	<li class="table-active"><a class="nav-link" href="businessIt.jsp">IT</a></li>
	<li class="table-active"><a class="nav-link"
		href="businessSalesMarketing.jsp">Sales / Marketing</a></li>
	<li class="table-active"><a class="nav-link"
		href="businessMedia.jsp">Media</a></li>
	<li class="table-active"><a class="nav-link"
		href="businessPlus.jsp">Plus</a></li>
	<br>
	<li class="nav-item"><a class="nav-link active"
		href="coolTips.jsp">Cool Tips</a></li>
	<br>
	<li class="nav-item"><a class="nav-link active" href="myPage.jsp">My
			Page</a>
	<li class="table-active"><a class="nav-link" href="myIdea.jsp">My
			Idea</a></li>
	<li class="table-active"><a class="nav-link" href="myScraps.jsp">My
			Scraps</a></li>
	<li class="table-active"><a class="nav-link" href="myProcess.jsp">My
			Process</a></li>
	<li class="table-active"><a class="nav-link"
		href="membercontroller?action=getMyInfo">My Account</a>
</ul>