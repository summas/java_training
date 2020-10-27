<%@ page contentType="text/html;charset=UTF-8" %>


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
 <title>商品マスタ</title>
<link rel="stylesheet" href="css/webtest_style.css" type="text/css">
<script src="js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
<script type="text/javascript" src="js/jquery.json.js"></script>
<script type="text/javascript" src="js/Productdisp.js" charset="utf-8"></script>

</head>
<body>
<div id="container">
<div id="main">
<h1><hr width ="95%">商品マスタ<hr width ="95%"> </h1>

<div id="contents">
<div id="mainContents">
<div align="right">

<form>
商品検索：<br>
商品ID：<input type="text" size="3" id="pid" style= "text-align:right"><br>
<input type="button" id="pseach" value="検索"　onclick="pseach()">
<br><br>
</form>

<%
Object ob = session.getAttribute( "auth" );
String objStr = ob.toString();
int au = new Integer(objStr);

if(au == 9){
	out.println("商品追加：");
	out.println("<FORM action='ProductAdd' method=post>");
	out.println("商品ID：<INPUT type='text' name='ADDID' size=\"5\"value='' style= 'text-align:right'><br>");
	out.println("商品名：<INPUT type='text' name='PNAME' size=\"10\"　value='' style= 'text-align:right'><br>");
	out.println("<INPUT type='submit' name='button' value='追加'><br><br>");
	out.println("</FORM>");
	
	out.println("商品削除：");
	out.println("<FORM action='ProductDelete' method=post>");
	out.println("商品ID：<INPUT type='text' name='PID' id=\"deleteid\" size=\"1\" value='' style= 'text-align:right'> <br>");
	out.println("商品名：<INPUT type='text' name='PNAME' id=\"deletename\" size=\"10\" value='' style= 'text-align:right'><br>");
	out.println("<INPUT type='submit' name='button' value='削除'><br><br>");
	out.println("</FORM>");
	
	out.println("商品名修正：");
	out.println("<FORM action='ProductUpdate' method=post>");
	out.println("商品ID：<INPUT type='text' id=\"updateid\" name='PID' size=\"1\" value='' readonly=\"readonly\" style= 'text-align:right'>　<br>");
	out.println("商品名：<INPUT type='text'id=\"updatename\" name='PNAME' size=\"10\"　value='' style= 'text-align:right'><br>");
	out.println("<INPUT type='submit' name='button' value='修正'><br><br>");
	out.println("</FORM>");

	out.println("<form method='POST' enctype='multipart/form-data' action='UploadServlet'>");
	out.println("CSVファイルアップロード：　<input type='file' name='fl' size='20' />");
	out.println(" <input type='submit' value='アップロード'/><br><br>");
	out.println("</form>");
}
%>

<FORM action='alldisp'  method=post>
<INPUT type='submit' name='button' value='全商品表示'><br><br>
</FORM>

<FORM action='CsvOut'  method="post" >
CSV出力：<br>
ファイル名：<INPUT type='text' name='FILENAME' size="9"value=''>.csv<br>
<INPUT type='submit' name='button' value='Csv出力'><br><br>
</FORM>





<FORM action='excel'  method=post>
EXCEL出力：<br>
商品ID：<INPUT type='text' name='PID' id="excelid" size="1" value='<%= request.getAttribute( "id" )%>' style= 'text-align:right'>　<br>
商品名：<INPUT type='text' name='PNAME' id="excelname"size="10" value='<%= request.getAttribute( "name" )%>' style= 'text-align:right'><br>
数量：<INPUT type='text' name='AMOUNT' size="5"value='10' style= 'text-align:right'><br>
単位：<INPUT type='text' name='UNIT' size="5"value='本／冊' style= 'text-align:right'><br>
単価：<INPUT type='text' name='UNIPRICE' size="5"value='120' style= 'text-align:right'><br>
金額：<INPUT type='text' name='PRICE' size="5"value='1200' style= 'text-align:right'><br>
備考：<INPUT type='text' name='MEMO' size="5"value='' style= 'text-align:right'><br>

ファイル名：<INPUT type='text' name='FILENAME' size="9"value=''>.xlsx<br>

<INPUT type='submit' name='button' value='excel出力'><br><br>
</FORM>

<FORM action='pdf'  method=post>
PDF出力：<br>
商品ID：<INPUT type='text' name='PID' id="pdfid" size="1" value='<%= request.getAttribute( "id" )%>' style= 'text-align:right'><br>
商品名：<INPUT type='text' name='PNAME' id="pdfname" size="10" value='<%= request.getAttribute( "name" )%>' style= 'text-align:right'><br>
数量：<INPUT type='text' name='AMOUNT' size="5"value='10' style= 'text-align:right'><br>
単位：<INPUT type='text' name='UNIT' size="8"value="本／冊" style= 'text-align:right'><br>
単価：<INPUT type='text' name='UNIPRICE' size="5"value='120' style= 'text-align:right'><br>
金額:<INPUT type='text' name='PRICE' size="8"value='1200' style= 'text-align:right'><br>
備考：<INPUT type='text' name='MEMO' size="5"value='' style= 'text-align:right'><br>

ファイル名：<INPUT type='text' name='FILENAME' size="9"value=''>.pdf<br>

<INPUT type='submit' name='button' value='pdf出力'><br><br>
</FORM>

<form method="POST" action="${pageContext.request.contextPath}/upload"
        enctype="multipart/form-data">
        <input type="file" name="fileupload"> <br> <input
            type="submit" value="アップロード">
</form>


</div>
</div>
<div id="subContents">
<!-- %= session.getId() %> -->
<br>

<br><br><br><br><br>
<div align="center">
 <form method="POST" action="ProductMaster">
商品ID：<INPUT type='text' id="id" name='PID' size="12" value='<%= request.getAttribute( "id" )%>' readonly="readonly"  style= "background-color: #F5FFFA; text-align:center"><br>
商品名：<INPUT type='text' id="pname" name='PID' size="12" value='<%= request.getAttribute( "name" )%>' readonly="readonly" style= "background-color: #F5FFFA; text-align:center">
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
<hr align="center"; width ="95%" >
<div class="aqua15">Copyright © 2011 St. MASAAKI S INC. All rights reserved</div>
<hr align="center"; width ="95%" >
</div>
</div>
</div>
 </body>
 </html>