<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "java.io.*,java.util.*"%>
    
    
<%
	String name = "Admin";
	session.setAttribute("user", name); 
	out.println(session.getAttribute("user"));
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tomcat WebSocket</title>
</head>
<body>
	<form>
		<input id="message" type="text">
		<input onclick="wsSendMessage();" value="Echo" type="button">
		<input onclick="wsCloseConnection();" value="Disconnect" type="button">
	</form>
	<br>
	<textarea id="echoText" rows="20" cols="60"></textarea>
	
	<script type="text/javascript">
		var webSocket = new WebSocket("ws://localhost:8888/TanksWarServer/websocketendpoint");
		var echoText = document.getElementById("echoText");
		echoText.value = "";
		var message = document.getElementById("message");
		webSocket.onopen = function(message){ wsOpen(message);};
		webSocket.onmessage = function(message){ wsGetMessage(message);};
		webSocket.onclose = function(message){ wsClose(message);};
		webSocket.onerror = function(message){ wsError(message);};
		function wsOpen(message){
			echoText.value += "Connected ... \n";
		}
		function wsSendMessage(){
			var msgToSend = {message: message.value, sender: "Admin"};
			console.log(JSON.stringify(msgToSend));
			webSocket.send(JSON.stringify(msgToSend));
			//webSocket.send(message.value);
			echoText.value += "Message sent to the server : " + message.value + "\n";
			message.value = "";
		}
		function wsCloseConnection(){
			webSocket.close();
		}
		function wsGetMessage(message){
			var msg = JSON.parse(message.data);
			echoText.value += msg.sender + ": " + msg.message + "\n";
		}
		function wsClose(message){
			echoText.value += "Disconnect ... \n";
		}

		function wserror(message){
			echoText.value += "Error ... \n";
		}
	</script>
</body>
</html>