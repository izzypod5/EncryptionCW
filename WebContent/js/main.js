/**
 * 
 */

$(document).on("click", "#somebutton", function() { 
	var init_text = $("#initText").val();
	$.ajax({
		type : "POST",
		url : "encryption",
		data : {
			init_text : init_text
		},
		success : function(responseText) {
			console.log("Response Text: " + responseText);
			$("#initText").text(responseText);
			$("#initText").val(responseText);
		}
	})
});

