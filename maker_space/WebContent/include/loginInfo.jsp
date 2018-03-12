<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
			<% if(session.getAttribute("grade").equals("G") && session.getAttribute("name")!=null) { %>
			<h7 class="navbar-brand"><%=session.getAttribute("company")%> <%=session.getAttribute("name")%></h7>
			<% 	}else if(session.getAttribute("grade").equals("A") && session.getAttribute("name")!=null) { %>
			<h7 class="navbar-brand"> °ü¸®ÀÚ <%=session.getAttribute("name")%></h7>
			<%	} else {}  %>
			
			<% if(session.getAttribute("name")!=null) { %>
			<form method="post" action="membercontroller"class="form-inline my-2 my-lg-0">
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