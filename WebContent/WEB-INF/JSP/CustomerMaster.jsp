<%@ page contentType="text/html;charset=UTF-8" %>

<!-- 
%
 Object name = session.getAttribute("name");
 response.setHeader("Pragma","no-cache");
 response.setHeader("Cache-Control","no-cache");
 response.addHeader("Cache-Control","no-store");
 response.setDateHeader("Expires", 0);

 %>
  -->
<!DOCTYPE html> 
<html lang="ja">
 <head>
<meta charset="UTF-8">
 <title>顧客マスタ</title>
<link rel="stylesheet" href="css/webtest_style.css" type="text/css">

<script src="js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
<script type="text/javascript" src="js/jquery.json.js"></script>

<script type="text/javascript" src="js/postNum.js" charset="utf-8"></script>


  

 </head>
 <body>
<div id="container">
<div id="main">
<h1><hr width ="95%">顧客マスタ<hr width ="95%"> </h1>

<div id="contents">
<div align="center">



<form method="POST" action="CustomerRegister">
郵便番号(半角数字):
<input type="text" size="7" id="postNum"  name="postNo" ><br>

都道府県：
<select id="kenselect" name="ken" >
<option value=""></option>
<option value="1">北海道</option><option value="2">青森県</option>
<option value="3">岩手県</option><option value="4">宮城県</option>
<option value="5">秋田県</option><option value="6">山形県</option>
<option value="7">福島県</option><option value="8">茨城県</option>
<option value="9">栃木県</option><option value="10">群馬県</option>
<option value="11">埼玉県</option><option value="12">千葉県</option>
<option value="13">東京都</option><option value="14">神奈川県</option>
<option value="15">新潟県</option><option value="16">富山県</option>
<option value="17">石川県</option><option value="18">福井県</option>
<option value="19">山梨県</option><option value="20">長野県</option>
<option value="21">岐阜県</option><option value="22">静岡県</option>
<option value="23">愛知県</option><option value="24">三重県</option>
<option value="25">滋賀県</option><option value="26">京都府</option>
<option value="27">大阪府</option><option value="28">兵庫県</option>
<option value="29">奈良県</option><option value="30">和歌山県</option>
<option value="31">鳥取県</option><option value="32">島根県</option>
<option value="33">岡山県</option><option value="34">広島県</option>
<option value="35">山口県</option><option value="36">徳島県</option>
<option value="37">香川県</option><option value="38">愛媛県</option>
<option value="39">高知県</option><option value="40">福岡県</option>
<option value="41">佐賀県</option><option value="42">長崎県</option>
<option value="43">熊本県</option><option value="44">大分県</option>
<option value="45">宮崎県</option><option value="46">鹿児島県</option>
<option value="47">沖縄県</option>
</select>
市区町村：<INPUT id="town" type='text' name='shi' size="25" value=''><br>
番地マンション名など：<INPUT type='text' name='banchi' size="20" value=''><br>
<INPUT type='submit' name='button' value='登録'><br><br>
</form>

<form method="POST" enctype="multipart/form-data" 
         action="PostInfoUpload">
郵便情報CSVファイルアップロード：<br>
<input type="file" name="fl" size="20" />
 <input type="submit" value="アップロード" />
 </form>

</div>


<!-- %= session.getId() %> -->
<br>

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