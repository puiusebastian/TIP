/**
 * 
 */

var canvasContext;
var boardWidth = 800;
var boardHeight = 500;
var tileSize = 30;
var missileSize = 10;
var playerIndex = 0;
var playerBufferIndex = 0;
var players = [];
var playersBuffer = [];
var missiles = [];
var missilesBuffer = [];
var roundTimeElapsed = "00:00";
var roundNumber = 0;
var firstTeamScore = 0;
var secondTeamScore = 0;

function loadGame() {
	connectToWebsocket();
	
	var gameSection = document.getElementById("game_section");
	
	var canvas = document.getElementById("game_canvas");
	canvasContext = canvas.getContext("2d");
	
	// Set canvas dimensions
	canvas.width = boardWidth;
	canvas.height = boardHeight;
	
	// Create a player
	players[0] = new Player(getImage("disabled_tank"), 250, 250, 0);
	
	// Add input events
	getInput();
	
	// Run the game
	runGame();
}

function runGame() {
	// Update game statistics
	document.getElementById("round_time_elapsed").innerHTML = roundTimeElapsed;
	document.getElementById("round_number").innerHTML = roundNumber;
	document.getElementById("first_team_score").innerHTML = firstTeamScore;
	document.getElementById("second_team_score").innerHTML = secondTeamScore;
	
	// Update buffers
	players = playersBuffer;
	missiles = missilesBuffer;
	playerIndex = playerBufferIndex;
	
	// Draw the game
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
	tileSize = dataArray[0].tileSize;
	missileSize = dataArray[0].missileSize;
	roundTimeElapsed = dataArray[0].roundTimeElapsed;
	roundNumber = dataArray[0].roundNumber;
	firstTeamScore = dataArray[0].firstTeamScore;
	secondTeamScore = dataArray[0].secondTeamScore;
	boardWidth = dataArray[0].playerWindowWidth;
	boardHeight = dataArray[0].playerWindowHeight;
	
	playersBuffer = [];
	for(var i = 1; i < numberOfPlayers + 1; ++i) {
		if(dataArray[i].alive == false) {
			playersBuffer[i-1] = new Player(getImage("disabled_tank"), dataArray[i].posX, dataArray[i].posY, dataArray[i].team);
			playersBuffer[i-1].alive = false;
		}
		else {
			if(dataArray[i].team == 1) {
				playersBuffer[i-1] = new Player(getImage("blue_tank"), dataArray[i].posX, dataArray[i].posY, 1);
			}
			else {
				playersBuffer[i-1] = new Player(getImage("red_tank"), dataArray[i].posX, dataArray[i].posY, 2);
			}
		}
		
		playersBuffer[i-1].movementDirection = dataArray[i].movementDirection;
	}
	
	missilesBuffer = [];
	for(var i = numberOfPlayers + 1; i < numberOfPlayers + numberOfMissiles + 1; ++i) {
		var index = i - (numberOfPlayers + 1);
		missilesBuffer[index] = new Missile(getImage("missile"), dataArray[i].posX, dataArray[i].posY);
	}
}