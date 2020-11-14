<%@ page contentType="text/html;charset=UTF-8"%>
<%
 Object name = session.getAttribute("name");
 response.setHeader("Pragma","no-cache");
 response.setHeader("Cache-Control","no-cache");
 response.addHeader("Cache-Control","no-store");
 response.setDateHeader("Expires", 0);

 %>


<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
<link rel="stylesheet" href="css/webtest_style.css" type="text/css">
</head>
<body>


	<div id="container">
		<div id="main">

			<h1>
				<hr width="95%">
				ログイン
				<hr width="95%">
			</h1>

			<!--  
Working with server:< %= application.getServerInfo() %><br>

Servlet Specification:< %= application.getMajorVersion() %>.< %= application.getMinorVersion() %><br>

JSP version: < %= JspFactory.getDefaultFactory().getEngineInfo().getSpecificationVersion() %><br>
-->

			<div align="center">
				<FORM action='login' method=post>
					職員番号 ：<INPUT type='text' size="8" name='ID' value=''><br>
					パスワード：<INPUT type='password' size="8" name='PASS' value=''><br>
					<br> <INPUT type='submit' name='button' value='ログイン'><br>
					<br>
				</FORM>
				<FONT color="red"><%= request.getAttribute( "iderr" )%></FONT> <br>
			</div>


			<div id="footer">
				<hr align="center" width="95%">
				<div class="aqua15">Copyright © 2011 St. MASAAKI S INC. All
					rights reserved</div>
				<hr align="center" width="95%">
			</div>
		</div>
	</div>
</body>
</html>