<%@ page contentType="text/html;charset=UTF-8"%>
<%
 response.setHeader("Pragma","no-cache");
 response.setHeader("Cache-Control","no-cache");
 response.addHeader("Cache-Control","no-store");
 response.setDateHeader("Expires", 0);

 %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>メインメニュー</title>
<link rel="stylesheet" href="css/webtest_style.css" type="text/css">
</head>
<body>
	<div id="container">
		<div id="main">
			<!-- %= session.getId() %>
%= session.getMaxInactiveInterval() %> -->

			<h1>
				<hr width="95%">
				メールテスト
				<hr width="95%">
			</h1>
			<div align="center">

				<FORM action='mail' method="post">
					タイトル：<INPUT type='text' name='TITLE' size="20" value=''><br>
					<br> 本文<br>
					<TEXTAREA cols="40" rows="6" name='MAIL' wrap="hard"></TEXTAREA>
					<br>
					<br> 添付ファイル： <input type='file' name='FILENAME' size='20' /><br>
					<br> <INPUT type='submit' name='button' value='メール送信'><br>
					<br>
				</FORM>

				<%=request.getAttribute("result") %><br>
				<br>

				<FORM action='move' method="get">
					<INPUT type='submit' name='button' value='メインメニュー'><br>
					<br>
				</FORM>

				<FORM action='SessionInvalidate' method="post">
					<INPUT type='submit' name='button' value='ログアウト'><br>
					<br>
				</FORM>

			</div>
			<div id="footer">
				<hr align="center" ; width="95%">
				<div class="aqua15">Copyright © 2011 St. MASAAKI S INC. All
					rights reserved</div>
				<hr align="center" ; width="95%">
			</div>
		</div>
	</div>
</body>
</html>