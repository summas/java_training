<%@ page contentType="text/html;charset=UTF-8" %>


<!DOCTYPE html> 
<html lang="ja">
 <head>
<meta charset="UTF-8">
 <title>エスケープテスト</title>
<link rel="stylesheet" href="css/webtest_style.css" type="text/css">
 </head>
 <body>
<div id="container">
<div id="main">
<%= session.getId() %>
<%= session.getMaxInactiveInterval() %>

<h1><hr width ="95%">エスケープテスト<hr width ="95%"> </h1>
<div align="center">
 <form method="POST" action="login">
 こんにちは<%=session.getAttribute("name")%>さん。
ID:<%= session.getAttribute( "id" )%>  <br><br>
</form>

 <form method="POST" action="escape">
<%= request.getAttribute( "strOut" )%>  <br><br>
</form>

<FORM action='escapetest' method=post>
<INPUT type='text' name='escape' size="5"value=''><br>
<INPUT type='submit' name='button' value='エスケープ'><br><br>
</FORM>

<FORM action='move'  method="get" >
<INPUT type='submit' name='button' value='メインメニュー'><br><br>
</FORM>



</div>

 
 <div id="footer">
<hr align="center"; width ="95%" >
<div class="aqua15">Copyright © 2011 St. MASAAKI S INC. All rights reserved</div>
<hr align="center"; width ="95%" >
</div>
</div>
</div>
 </body>
 </html>