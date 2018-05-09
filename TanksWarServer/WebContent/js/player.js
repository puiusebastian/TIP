/**
 * 
 */

function Player(imageSrc, posX, posY, team, username, kills, deaths) {
	this.image = new Image();
	this.image.src = imageSrc;

	this.x = posX;
	this.y = posY;
	
	this.team = team;
	
	this.username = username;
	this.kills = kills;
	this.deaths = deaths;
	
	this.tankId = 1;

	this.alive = true;

	this.movementDirection = "up";
	
	this.animation = { "up": 0, "right": 0, "down": 0, "left": 0 };

	this.moveUp = false;
	this.moveRight = false;
	this.moveDown = false;
	this.moveLeft = false;

	this.timeBetweenAnimStates = 5; // 
}

Player.prototype.draw = function() {
	var coords = [];
	var rotationAngle = 0;
	// Get tank tile
	switch(this.tankId) {
	case 1:
		coords = getGraphicElementCoords("spritesheet_black_tank");
		break;
	case 2:
		coords = getGraphicElementCoords("spritesheet_blue_tank");
		break;
	case 3:
		coords = getGraphicElementCoords("spritesheet_green_tank");
		break;
	case 4:
		coords = getGraphicElementCoords("spritesheet_red_tank");
		break;
	case 5:
		coords = getGraphicElementCoords("spritesheet_beige_tank");
		break;
	}
	
	switch(this.movementDirection) {
		case "up":
			rotationAngle = Math.PI;
			break;
		case "right":
			rotationAngle = (3 * Math.PI) / 2;
			break;
		case "down":
			rotationAngle = 0;
			break;
		case "left":
			rotationAngle = Math.PI / 2;
			break;
	}

	// Determine character's dimensions
	var width = playerTileSize;
	var height = (coords[3] * playerTileSize) / coords[2];
	// Determine the character's position
	var posX;
	var posY;
	if(players[playerIndex].x + playerTileSize/2 - boardWidth/2 > 0 &&
			players[playerIndex].x + playerTileSize/2 + boardWidth/2 < mapWidth) {
		posX = this.x - (players[playerIndex].x + playerTileSize/2) + boardWidth/2;
	}
	else {
		if(players[playerIndex].x + playerTileSize/2 - boardWidth/2 <= 0) {
			posX = this.x;
		}
		if(players[playerIndex].x + playerTileSize/2 + boardWidth/2 >= mapWidth) {
		console.log(this.x);
			posX = this.x - players[playerIndex].x + boardWidth/2 + (this.x + boardWidth/2 - mapWidth);
		}
	}
	if(players[playerIndex].y + playerTileSize/2 - boardHeight/2 > 0 &&
			players[playerIndex].y + playerTileSize/2 + boardHeight/2 < mapHeight) {
		posY = this.y - (players[playerIndex].y + playerTileSize/2) + boardHeight/2;
	}
	else {
		if(players[playerIndex].y + playerTileSize/2 - boardHeight/2 <= 0) {
			posY = this.y;
		}
		if(players[playerIndex].y + playerTileSize/2 + boardHeight/2 >= mapHeight) {
			posY = this.y - players[playerIndex].y + boardHeight/2 + (this.y + boardHeight/2 - mapHeight);
		}
	}
	
	// Draw the character
	canvasContext.translate(posX + playerTileSize/2, posY + playerTileSize/2);
	canvasContext.rotate(rotationAngle);
	canvasContext.drawImage(this.image, coords[0], coords[1], coords[2], coords[3], -playerTileSize/2, -playerTileSize/2, width, height);
	canvasContext.rotate(-rotationAngle);
	canvasContext.translate(-(posX + playerTileSize/2), -(posY + playerTileSize/2));
	
	// Draw team identifier
	canvasContext.beginPath();
	canvasContext.arc(posX, posY, 4, 0, 2 * Math.PI);
	if(this.team == players[playerIndex].team) {
		canvasContext.fillStyle = "green";
	}
	else {
		canvasContext.fillStyle = "red";
	}
	canvasContext.fill();
	
	// Draw health bar
	canvasContext.beginPath();
	canvasContext.rect(posX, posY - 20, playerTileSize, 6);
	canvasContext.fillStyle = "#919191";
	canvasContext.fill();
	
	canvasContext.beginPath();
	canvasContext.rect(posX, posY - 20, 0.5*playerTileSize, 6);
	canvasContext.fillStyle = "green";
	canvasContext.fill();
	
	canvasContext.beginPath();
	canvasContext.rect(posX, posY - 20, playerTileSize, 6);
	canvasContext.lineWidth = 1.5;
	canvasContext.strokeStyle = "#003300";
	canvasContext.stroke();
}