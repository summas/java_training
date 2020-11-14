<%@ page import="Beans.*" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%
 response.setHeader("Pragma","no-cache");
 response.setHeader("Cache-Control","no-cache");
 response.addHeader("Cache-Control","no-store");
 response.setDateHeader("Expires", 0);

 %>
<% 
Object ob = request.getAttribute("AllDisp");
List<alldispbean> list = (List)ob;
%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title><%= request.getAttribute( "title" )%></title>
<link rel="stylesheet" href="css/webtest_style.css" type="text/css">
</head>
<body>
	<div id="container">
		<div id="main">
			<h1>
				<hr width="95%"><%= request.getAttribute( "title" )%><hr
					width="95%">
			</h1>
			<!--%= session.getId() %> -->
			<div align="center">

				<table border="0" bgcolor=#fffff0>
					<% for(int i=0;i<list.size();i++) {%>
					<tr>
						<td><%=list.get(i).getId() %></td>
						<td><%=list.get(i).getName() %></td>
					</tr>
					<%} %>
				</table>

				<br>
				<br>

				<FORM action='move' method=post>
					<INPUT type='submit' name=logok value='商品マスタ'><br>
					<br>
				</FORM>


				<div id="footer">
					<hr align="center" width="95%">
					<div class="aqua15">Copyright © 2011 St. MASAAKI S INC. All
						rights reserved</div>
					<hr align="center" width="95%">
				</div>
			</div>
		</div>
	</div>
</body>
</html>