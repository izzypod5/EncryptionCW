<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script>
            $(document).on("click", "#somebutton", function() { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
            	var init_text = $("#initText").val();
/*             	$.post("encryption",{"init_text":init_text}, function(responseText) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                    console.log("Response Text: " + responseText);
            		$("#initText").text(responseText);           // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
                }); */
                $.ajax({
             	   type: "POST",
             	   url: "encryption",
             	   data:{
             		   init_text:init_text            	    
             	    },
             	   success: function(responseText){
             		   console.log("Response Text: " + responseText);
                		$("#initText").text(responseText); 
                		$("#initText").val(responseText); 
             	   }
             	})
            });
           
        </script>
</head>
<body>
<h1>Computer Security Coursework</h1>

<!--  TODO: ADD ANIMATION -->

Initial Text: <input id="initText" type="text" name="init_text"/>
<button id="somebutton">press here</button>
     
</body>
</html>