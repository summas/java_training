$(function() {
$("#pseach").click(function() {


	var pid = $("#pid").val();

	//if (pid.match(/[^0-9]+/g)){
	if (pid.match(/[^0-9]+/g)){
		alert("半角数字を入力してください");
		return;
	}
  
	console.log(pid);
	
	$.ajax({
		type: "post",
		url :"http://localhost:8080/PracticeWebApp/ProductdispAjax",
		dataType : "jsonp",
		data: {requestJs : pid},
		success : function(json) {
			console.log(json); //★1
			//$('#result').text(json);
			pseach(json);
		}
	});
});
});

function pseach(json) {

	var jsStr = JSON.stringify(json);
	console.log(jsStr);
	var jsObject = JSON.parse(jsStr);
	console.log(jsObject);
//	$("#id").text("商品ID：" + jsObject["id"]);
//	$("#pname").text("商品名：" + jsObject["name"]);
	$("#id").val(jsObject["id"]);
	$("#pname").val(jsObject["name"]);
	$("#deleteid").val(jsObject["id"]);
	$("#deletename").val(jsObject["name"]);
	$("#updateid").val(jsObject["id"]);
	$("#updatename").val(jsObject["name"]);
	$("#excelid").val(jsObject["id"]);
	$("#excelname").val(jsObject["name"]);
	$("#pdfid").val(jsObject["id"]);
	$("#pdfname").val(jsObject["name"]);
}
