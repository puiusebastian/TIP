/**
 * 
 */

var canvasContext;
var boardWidth = 500;
var boardHeight = 500;
var playerIndex = 0;
var playerBufferIndex = 0;
var players = [];
var playersBuffer = [];
var missiles = [];
var missilesBuffer = [];

function loadGame() {
	connectToWebsocket();
	
	var gameSection = document.getElementById("game_section");
	
	var canvas = document.getElementById("game_canvas");
	canvasContext = canvas.getContext("2d");
	
	// Set canvas dimensions
	canvas.width = boardWidth;
	canvas.height = boardHeight;
	
	// Create a player
	players[0] = new Player(getImage("blue_tank"), 250, 250);
	
	// Add input events
	getInput();
	
	// Run the game
	runGame();
}

function runGame() {
	players = playersBuffer;
	missiles = missilesBuffer;
	
	draw();
	
	window.requestAnimationFrame(runGame);
}

function draw() {
	canvasContext.clearRect(0, 0, boardWidth, boardHeight);
	
	// Draw players
	for(var i = 0; i < players.length; ++i) {
		players[i].draw();
	}
	
	// Draw missiles
	for(var i = 0; i < missiles.length; ++i) {
		missiles[i].draw();
	}
}

function getInput() {
	document.addEventListener('keydown', function(event) {
		switch(event.keyCode) {
			case 37:    // left arrow
                wsSendMessage("left");
                break;
            case 38:    // up arrow
            	wsSendMessage("up");
                break;
            case 39:    // right arrow
            	wsSendMessage("right");
                break;
            case 40:    // down arrow
            	wsSendMessage("down");
                break;
            case 32:    // space
                wsSendMessage("space");
                break;
		}
	});

	document.addEventListener('keyup', function(event) {
		switch(event.keyCode) {
			case 37:    // left arrow
				wsSendMessage("no_left");
                break;
            case 38:    // up arrow
            	wsSendMessage("no_up");
                break;
            case 39:    // right arrow
            	wsSendMessage("no_right");
                break;
            case 40:    // down arrow
            	wsSendMessage("no_down");
                break;
		}
	});
}

function gameUpdateBuffers(data) {
	var dataArray = JSON.parse(data);
	
	playerBufferIndex = dataArray[0].index;
	var numberOfPlayers = dataArray[0].numberOfPlayers;
	var numberOfMissiles = dataArray[0].numberOfMissiles;
	
	playersBuffer = [];
	for(var i = 1; i < numberOfPlayers + 1; ++i) {
		playersBuffer[i-1] = new Player(getImage("blue_tank"), dataArray[i].posX, dataArray[i].posY);
		playersBuffer[i-1].movementDirection = dataArray[i].movementDirection;
	}
	
	missilesBuffer = [];
	for(var i = numberOfPlayers + 1; i < numberOfPlayers + numberOfMissiles + 1; ++i) {
		var index = i - (numberOfPlayers + 1);
		missilesBuffer[index] = new Missile(getImage("missile"), dataArray[i].posX, dataArray[i].posY);
	}
}