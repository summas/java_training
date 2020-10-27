$(function() {
	// $("#seach").click(function() {
		 	$("#postNum").keyup(function() {
		
	var postNum = $("#postNum").val();

if(postNum.length < 7 ){
		return;
	}

	if (postNum.length != 7 || postNum.match(/[^0-9]+/g)){
		alert("7桁の半角数字を入力してください");
		return;
	}

	console.log(postNum);
	
	$.ajax({
		type: "post",
		url :"http://localhost:8080/PracticeWebApp/CustomerMasterScript",
		dataType : "jsonp",
		data: {requestJs : postNum},
		success : function(json) {
			console.log(json); //★1
			
			//$('#result').text(json);
			setResults(json);
		}
	});
  });
});

function setResults(json) {
	
	var jsStr = JSON.stringify(json);
	console.log(jsStr);
	var jsObject = JSON.parse(jsStr);
	console.log(jsObject);
//	$("#ken").text("県：" + jsObject["ken"]);
	$("#town").val(jsObject["shi"] + jsObject["machi"]);
	var ken = jsObject["ken"];
	change_pulldown(ken);
	}

function change_pulldown(ken){

    pulldown_option = document.getElementById("kenselect").getElementsByTagName('option');
    //alert(ken);
    for(i=0; i<pulldown_option.length;i++){
       if(pulldown_option[i].value == ken){
          pulldown_option[i].selected = true;
          break;
       }
    }
 }