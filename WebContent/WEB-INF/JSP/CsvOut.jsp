<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>CSV出力確認</title>
<link rel="stylesheet" href="css/webtest_style.css" type="text/css">
</head>
<body>
	<div id="container">
		<div id="main">


			<div align="center">
				<%request.getAttribute("csvf"); %>
				<P>出力が完了しました。</p>

				<FORM action='move' method=post>
					<INPUT type='submit' name=logok value='商品マスタ'><br>
					<br>
				</FORM>

				<FORM action='SessionInvalidate' method="post">
					<INPUT type='submit' name='button' value='ログアウト'><br>
					<br>
				</FORM>


				<div id="footer">
					<hr align="center" ; width="95%">
					<div class="aqua15">Copyright © 2011 St. MASAAKI S INC. All
						rights reserved</div>
					<hr align="center" ; width="95%">
				</div>
			</div>
		</div>
	</div>
</body>
</html>