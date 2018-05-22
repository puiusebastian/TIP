/**
 * 
 */

var webSocket;
var message;

function connectToWebsocket() {
	webSocket = new WebSocket("ws://localhost:8888/TanksWarServer/websocketendpoint");

	webSocket.onopen = function(message){ wsOpen(message);};
	webSocket.onmessage = function(message){ wsGetMessage(message);};
	webSocket.onclose = function(message){ wsClose(message);};
	webSocket.onerror = function(message){ wsError(message);};
}

function wsOpen(message){
	//echoText.value += "Connected ... \n";
}

function wsSendMessage(action){
	/*var msgToSend = {message: message.value, sender: "Admin"};
	console.log(JSON.stringify(msgToSend));
	webSocket.send(JSON.stringify(msgToSend));
	//webSocket.send(message.value);
	echoText.value += "Message sent to the server : " + message.value + "\n";
	message.value = "";*/
	webSocket.send(action);
}

function wsCloseConnection(){
	webSocket.close();
}

function wsGetMessage(message){
	//var msg = JSON.parse(message.data);
	//echoText.value += msg.sender + ": " + msg.message + "\n";
	//buffer = parseInt(message.data);
	gameUpdateBuffers(message.data);
}

function wsClose(message){
	//echoText.value += "Disconnect ... \n";
}

function wserror(message){
	//echoText.value += "Error ... \n";
}

function wsIsOpen() {
	if(webSocket.readyState === webSocket.OPEN) {
		return true;
	}
	return false;
}