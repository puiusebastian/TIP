/**
 * 
 */

var canvasContext;
var boardWidth = 900;
var boardHeight = 600;
var mapWidth = 1500;
var mapHeight = 1200;
var tileSize = 64;
var missileTileSize = 10;
var playerTileSize = 42;
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
var gameEnded = false;
var winnerTeam = 0;
var firstTeamFlagCapturedSeconds = 0;
var secondTeamFlagCapturedSeconds = 0;

function gameLobby() {
	loadGame();
}

function loadGame() {
	connectToWebsocket();
	
	var gameSection = document.getElementById("game_section");
	
	var canvas = document.getElementById("game_canvas");
	canvasContext = canvas.getContext("2d");
	
	// Set canvas dimensions
	canvas.width = boardWidth;
	canvas.height = boardHeight;
	
	// Create a player
	playersBuffer[0] = new Player('assets/empty.png', 600, 600, 0);
	playersBuffer[0].alive = false;
	
	// Add input events
	getInput();
	
	// Run the game
	runGame();
}

function runGame() {
	// Check if game ended
	if(gameEnded == true) {
		var result;
		if(winnerTeam == 0) {
			result = '<span class="white_text">TIE</span>';
		}
		else if(winnerTeam == players[playerIndex].team) {
			result = '<span class="green_text">VICTORY</span>';
		}
		else {
			result = '<span class="red_text">DEFEAT</span>';
		}
		document.getElementById("end_message").innerHTML = result;
		document.getElementById("end_message").style.display = "block";
	}
	else {
		document.getElementById("end_message").innerHTML = "";
		document.getElementById("end_message").style.display = "none";
	}
	
	// Update game statistics
	document.getElementById("round_time_elapsed").innerHTML = roundTimeElapsed;
	document.getElementById("round_number").innerHTML = roundNumber;
	document.getElementById("first_team_score").innerHTML = firstTeamScore;
	document.getElementById("second_team_score").innerHTML = secondTeamScore;
	
	// Set canvas dimensions
	var canvas = document.getElementById("game_canvas");
	if(canvas.width != boardWidth || canvas.height != boardHeight) {
		canvas.width = boardWidth;
		canvas.height = boardHeight;
	}
	
	// Update buffers
	players = playersBuffer;
	missiles = missilesBuffer;
	playerIndex = playerBufferIndex;
	
	// Draw the game
	draw();
	
	window.requestAnimationFrame(runGame);
}

function draw() {
	// Clear the canvas
	canvasContext.clearRect(0, 0, boardWidth, boardHeight);
	
	// Draw the map
	mapDraw();
	
	// Draw players
	for(var i = 0; i < players.length; ++i) {
		players[i].draw();
	}
	
	// Draw missiles
	for(var i = 0; i < missiles.length; ++i) {
		missiles[i].draw();
	}
	
	// Draw flag capturing indicators
	var indicatorWidth = 80;
		// First team flag
	canvasContext.beginPath();
	canvasContext.rect(boardWidth/2 - (indicatorWidth + 10), 10, indicatorWidth, 8);
	canvasContext.fillStyle = "#dddddd";
	canvasContext.fill();
	
	canvasContext.beginPath();
	canvasContext.rect(boardWidth/2 - (indicatorWidth + 10), 10, (firstTeamFlagCapturedSeconds / 120) * indicatorWidth, 8);
	canvasContext.fillStyle = "#002dda";
	canvasContext.fill();
	
	canvasContext.beginPath();
	canvasContext.rect(boardWidth/2 - (indicatorWidth + 10), 10, indicatorWidth, 8);
	canvasContext.lineWidth = 1.5;
	canvasContext.strokeStyle = "#0001a1";
	canvasContext.stroke();
		// Second team flag
	canvasContext.beginPath();
	canvasContext.rect(boardWidth/2 + 10, 10, indicatorWidth, 8);
	canvasContext.fillStyle = "#dddddd";
	canvasContext.fill();
	
	canvasContext.beginPath();
	canvasContext.rect(boardWidth/2 + 10, 10, (secondTeamFlagCapturedSeconds / 120) * indicatorWidth, 8);
	canvasContext.fillStyle = "#a00000";
	canvasContext.fill();
	
	canvasContext.beginPath();
	canvasContext.rect(boardWidth/2 + 10, 10, indicatorWidth, 8);
	canvasContext.lineWidth = 1.5;
	canvasContext.strokeStyle = "#770000";
	canvasContext.stroke();
}

function mapDraw() {
	// Get tiles image
	var tilesImage = new Image();
	tilesImage.src = getImage("tilesheet");
	
	// Get map configuration
	var map = maps[0];
	
	// Determine coordinates for the center of the view
	var centerX;
	var centerY;
	if(players[playerIndex].x + playerTileSize / 2 - boardWidth / 2 > 0 &&
			players[playerIndex].x + playerTileSize/2 + boardWidth / 2 < mapWidth) {
		centerX = players[playerIndex].x + playerTileSize / 2;
	}
	else {
		if(players[playerIndex].x + playerTileSize / 2 - boardWidth / 2 <= 0) {
			centerX = boardWidth / 2;
		}
		if(players[playerIndex].x + playerTileSize + boardWidth / 2 >= mapWidth) {
			centerX = mapWidth - boardWidth/2;
		}
	}
	if(players[playerIndex].y + playerTileSize / 2 - boardHeight / 2 > 0 &&
			players[playerIndex].y + playerTileSize/2 + boardHeight / 2 < mapHeight) {
		centerY = players[playerIndex].y + playerTileSize / 2;
	}
	else {
		if(players[playerIndex].y + playerTileSize / 2 - boardHeight / 2 <= 0) {
			centerY = boardHeight / 2;
		}
		if(players[playerIndex].y + playerTileSize/2 + boardHeight / 2 >= mapHeight) {
			centerY = mapHeight - boardHeight/2;
		}
	}
	var centerBlockX = pixelToBlock(centerX, tileSize);
	var centerBlockY = pixelToBlock(centerY, tileSize);
	
	// Draw the map
	for(var i = pixelToBlock(centerY - boardHeight/2, tileSize); i <= pixelToBlock(centerY + boardHeight/2, tileSize); i++) {
		for(var j = pixelToBlock(centerX - boardWidth/2, tileSize); j <= pixelToBlock(centerX + boardWidth/2, tileSize); j++) {

			// Get block coordinates
			var x = blockToPixel(j, tileSize) - (centerX - boardWidth / 2);
			var y = blockToPixel(i, tileSize) - (centerY - boardHeight / 2);

			// Draw block
			if(typeof map[i] !== 'undefined') {
				var coords;
				switch(map[i][j]) {
				case 0:		// grass
					coords = getGraphicElementCoords("tilesheet_grass");
					break;
				case 1:		// sand
					coords = getGraphicElementCoords("tilesheet_sand");
					break;
				case 2:		// left grass - right sand
					coords = getGraphicElementCoords("tilesheet_left_grass_right_sand");
					break;
				case 3:
					coords = getGraphicElementCoords("tilesheet_road_left_gray_right_brown");
					break;
				case 4:
					coords = getGraphicElementCoords("tilesheet_road_gray_left_right");
					break;
				case 5:
					coords = getGraphicElementCoords("tilesheet_road_gray_top_bottom");
					break;
				case 6:
					coords = getGraphicElementCoords("tilesheet_road_gray_bottom_right");
					break;
				case 7:
					coords = getGraphicElementCoords("tilesheet_road_gray_bottom_left");
					break;
				case 8:
					coords = getGraphicElementCoords("tilesheet_road_gray_top_right");
					break;
				case 9:
					coords = getGraphicElementCoords("tilesheet_road_gray_top_left");
					break;
				case 10:
					coords = getGraphicElementCoords("tilesheet_road_gray_top_bottom_right");
					break;
				case 11:
					coords = getGraphicElementCoords("tilesheet_road_gray_top_bottom_left");
					break;
				case 12:
					coords = getGraphicElementCoords("tilesheet_road_gray_top_left_right");
					break;
				case 13:
					coords = getGraphicElementCoords("tilesheet_road_gray_bottom_left_right");
					break;
				case 14:
					coords = getGraphicElementCoords("tilesheet_road_gray_top_bottom_left_right");
					break;
				case 15:
					coords = getGraphicElementCoords("tilesheet_road_brown_left_right");
					break;
				case 16:
					coords = getGraphicElementCoords("tilesheet_road_brown_top_bottom");
					break;
				case 17:
					coords = getGraphicElementCoords("tilesheet_road_brown_bottom_right");
					break;
				case 18:
					coords = getGraphicElementCoords("tilesheet_road_brown_bottom_left");
					break;
				case 19:
					coords = getGraphicElementCoords("tilesheet_road_brown_top_right");
					break;
				case 20:
					coords = getGraphicElementCoords("tilesheet_road_brown_top_left");
					break;
				case 21:
					coords = getGraphicElementCoords("tilesheet_road_brown_top_bottom_right");
					break;
				case 22:
					coords = getGraphicElementCoords("tilesheet_road_brown_top_bottom_left");
					break;
				case 23:
					coords = getGraphicElementCoords("tilesheet_road_brown_top_left_right");
					break;
				case 24:
					coords = getGraphicElementCoords("tilesheet_road_brown_bottom_left_right");
					break;
				case 25:
					coords = getGraphicElementCoords("tilesheet_road_brown_top_bottom_left_right");
					break;
				default:
					coords = [0, 0, 0, 0];
				}
				
				// Draw the tile
				canvasContext.drawImage(tilesImage, coords[0], coords[1], coords[2], coords[3], x, y, tileSize, tileSize);
			}
		}
	}
}

function getInput() {
	document.addEventListener('keydown', function(event) {
		switch(event.keyCode) {
			case 16:		// shift key
				displayScores(players);
				break;
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
	missileTileSize = dataArray[0].missileTileSize;
	playerTileSize = dataArray[0].playerTileSize;
	roundTimeElapsed = dataArray[0].roundTimeElapsed;
	roundNumber = dataArray[0].roundNumber;
	firstTeamScore = dataArray[0].firstTeamScore;
	secondTeamScore = dataArray[0].secondTeamScore;
	boardWidth = dataArray[0].playerWindowWidth;
	boardHeight = dataArray[0].playerWindowHeight;
	mapWidth = dataArray[0].mapWidth;
	mapHeight = dataArray[0].mapHeight;
	gameEnded = dataArray[0].gameEnded;
	winnerTeam = dataArray[0].winnerTeam;
	firstTeamFlagCapturedSeconds = dataArray[0].firstTeamFlagCapturedSeconds;
	secondTeamFlagCapturedSeconds = dataArray[0].secondTeamFlagCapturedSeconds;
	
	playersBuffer = [];
	for(var i = 1; i < numberOfPlayers + 1; ++i) {
		playersBuffer[i-1] = new Player(getImage("spritesheet"), dataArray[i].posX, dataArray[i].posY,
				dataArray[i].team, dataArray[i].username, dataArray[i].kills, dataArray[i].deaths);
		if(dataArray[i].alive == false) {
			playersBuffer[i-1].alive = false;
		}
		
		playersBuffer[i-1].fullHealth = dataArray[i].playerFullHealth;
		playersBuffer[i-1].currentHealth = dataArray[i].playerCurrentHealth;
		playersBuffer[i-1].movementDirection = dataArray[i].movementDirection;
	}
	
	missilesBuffer = [];
	for(var i = numberOfPlayers + 1; i < numberOfPlayers + numberOfMissiles + 1; ++i) {
		var index = i - (numberOfPlayers + 1);
		missilesBuffer[index] = new Missile(getImage("missile"), dataArray[i].posX, dataArray[i].posY);
	}
}