<%@ page contentType="text/html;charset=UTF-8" %>


<!DOCTYPE html> 
<html lang="ja">
 <head>
<meta charset="UTF-8">
 <title>職員登録</title>
<link rel="stylesheet" href="css/webtest_style.css" type="text/css">
 </head>
 <body>
 
  
<div id="container">
<div id="main">

<h1><hr width ="95%">職員登録<hr width ="95%"> </h1>




<div id="contents">

<div align="center">

 こんにちは<%= session.getAttribute( "name" )%>さん。
ID:<%= session.getAttribute( "id" )%>  <br><br>

<FORM action='register' method=post>
職員番号：<INPUT type='text' name='ID' value=''><br><br>
職員氏名：<INPUT type='text' name='NAME' value=''><br><br>
パスワード：<INPUT type='text' name='PASS' value=''><br><br>
権限：<select name="AUTH" size=1>
<option value="1">1</option>
<option value="9">9</option>
</select>
<br><br>
<INPUT type='submit' name='button' value='登録'><br><br>
</FORM>

<!--
<FORM action='userUpdate' method=post>
職員番号：<INPUT type='text' name='ID' value=''><br><br>
職員氏名：<INPUT type='text' name='NAME' value=''><br><br>
パスワード：<INPUT type='text' name='PASS' value=''><br><br>

<INPUT type='submit' name='button' value='パスワード修正'><br><br>
</FORM>
-->


<FORM action='move'  method="get" >
<INPUT type='submit' name='button' value='メインメニュー'><br><br>
</FORM>
</div>






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