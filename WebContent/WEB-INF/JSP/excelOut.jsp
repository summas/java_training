<%@ page contentType="text/html;charset=UTF-8"%>



<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>納品書出力</title>
<link rel="stylesheet" href="css/webtest_style.css" type="text/css">

</head>
<body>
	<div id="container">
		<div id="main">
			<h1>
				<hr width="95%">
				納品書出力
				<hr width="95%">
			</h1>

			<div id="contents">

				<div id="mainContents">
					<div align="right">

						<FORM action='ProductMaster' method=post>
							商品ID：<INPUT type='text' name='PID' size="5" 　value=''><br>
							<INPUT type='submit' name='button' value='検索'><br>
							<br>
						</FORM>

						<FORM action='ProductAdd' method=post>
							追加商品ID：<INPUT type='text' name='ADDID' size="5" value=''><br>
							追加商品名:<INPUT type='text' name='PNAME' size="8" 　value=''><br>
							<INPUT type='submit' name='button' value='追加'><br>
							<br>
						</FORM>


						<FORM action='ProductDelete' method=post>
							商品ID：<INPUT type='text' name='PID' size="1"
								value='<%= request.getAttribute( "id" )%> '> <br>
							商品名：<INPUT type='text' name='PNAME' size="10"
								value='<%= request.getAttribute( "name" )%> '><br>
							<INPUT type='submit' name='button' value='削除'><br>
							<br>
						</FORM>

						<FORM action='ProductUpdate' method=post>
							商品ID：<INPUT type='text' name='PID' size="1"
								value='<%= request.getAttribute( "id" )%> ' readonly="readonly">
							<br> 商品名：<INPUT type='text' name='PNAME' size="10"
								value='<%= request.getAttribute( "name" )%> '><br>
							<INPUT type='submit' name='button' value='修正'><br>
							<br>
						</FORM>


						<FORM action='alldisp' method=post>
							<INPUT type='submit' name='button' value='全商品表示'><br>
							<br>
						</FORM>



						<FORM action='CsvOut2' method="post">
							<INPUT type='submit' name='button' value='Csv出力'><br>
							<br>
						</FORM>

						<!--

<FORM action='CsvReaderServ'  method=post>
<INPUT type='submit' name='button' value='CSV取込みtest'><br><br>
</FORM>
-->

						<FORM action='excel' method=post>
							商品ID：<INPUT type='text' name='PID' size="1"
								value='<%= request.getAttribute( "id" )%> >　<br>
商品名：<INPUT type='text' name='PNAME' size="10" value='<%= request.getAttribute( "name" )%> '><br>
<INPUT type='submit' name='button' value='納品書出力'><br><br>
</FORM>



</div>
</div>
<div id="subContents">
<!-- %= session.getId() %> -->
<br>

<br><br><br><br><br>
<div align="center">
 <form method="POST" action="ProductMaster">

商品ID：<%= request.getAttribute( "id" )%>  <br>
商品名：<%= request.getAttribute( "name" )%> <br><br>
</form>
</div>

</div>
</div>

<div align="center">

<FORM action='move'  method="get" >
<INPUT type='submit' name='button' value='メインメニュー'><br><br>
</FORM>

<FORM action='SessionInvalidate'  method="post" >
<INPUT type='submit' name='button' value='ログアウト'><br><br>
</FORM>

</div>

 <div id="footer">
<hr align="center";
								width="95%">
							<div class="aqua15">Copyright © 2011 St. MASAAKI S INC. All
								rights reserved</div>
							<hr align="center" ; width="95%">
					</div>
				</div>
			</div>
</body>
</html>