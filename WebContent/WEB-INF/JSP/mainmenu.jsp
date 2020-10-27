<%@ page contentType="text/html;charset=UTF-8" %>
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


<h1><hr width ="95%">メインメニュー<hr width ="95%"> </h1>
<div align="center">

 こんにちは<%=session.getAttribute("name")%>さん。
ID:<%= session.getAttribute( "id" )%>  <br><br>


<FORM action='move' method=post>
<INPUT type='submit' name= button  value='商品マスタ'><br><br>
</FORM>

<%

Object ob = session.getAttribute( "auth" );
String objStr = ob.toString();
int au = new Integer(objStr);

if(au == 9){

out.println("<FORM action='CustomerMasterScript' method=get>");
out.println("<INPUT type='submit' name= button value='顧客マスタ'><br><br>");
out.println("</FORM>");

out.println("<FORM action='register' method=get>");
out.println("<INPUT type='submit' name= button value='職員登録'><br><br>");
out.println("</FORM>");

out.println("<FORM action='escapetest' method=get>");
out.println("<INPUT type='submit' name= button value='HTMLエスケープtest'><br><br>");
out.println("</FORM>");

out.println("<FORM action='mail' method=get>");
out.println("<INPUT type='submit' name= button value='メールテスト'><br><br>");
out.println("</FORM>");
}
%>


<FORM action='SessionInvalidate'  method="post" >
<INPUT type='submit' name='button' value='ログアウト'><br><br>
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