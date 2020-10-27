$(function() {
	var requestObj = {
		message : null,
	};
	$("#request").click(function() {
		//リクエスト用のオブジェクトに値を詰め込む
		requestObj.message = $("#message").val();
		//JavaScriptのオブジェクトをJSONに変換する
		var requestJson = $.toJSON(requestObj);
		console.log(requestJson);


		
		$.ajax({
			type: "GET",
			url : "http://localhost:8080/CustomerMaster/JsonTestservlet2",
			data: {requestJs : requestJson},
			success : function(data) {
				//alert(data.responseMessage);
			
				$("#result").append(data.responseMessage);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("リクエスト時になんらかのエラーが発生しました：" + textStatus +":\n" + errorThrown);
			}
		});

	});
});