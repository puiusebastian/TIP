/**
 * 
 */

function Missile(imageSrc, posX, posY) {
	this.image = new Image();
	this.image.src = imageSrc;

	this.x = posX;
	this.y = posY;
}

Missile.prototype.draw = function() {
	var coords = getGraphicElementCoords("missile");
	
	//var posX = this.x - (players[playerIndex].x + tileSize/2) + boardWidth/2;
	//var posY = this.y - (players[playerIndex].y + tileSize/2) + boardHeight/2;
	var posX;
	var posY;
	if(players[playerIndex].x + tileSize/2 - boardWidth/2 > 0 &&
			players[playerIndex].x + tileSize/2 + boardWidth/2 < mapWidth) {
		posX = this.x - (players[playerIndex].x + playerTileSize/2) + boardWidth/2;
	}
	else {
		if(players[playerIndex].x + tileSize/2 - boardWidth/2 <= 0) {
			posX = this.x;
		}
		if(players[playerIndex].x + tileSize/2 + boardWidth/2 >= mapWidth) {
			posX = this.x - players[playerIndex].x + boardWidth/2 + (players[playerIndex].x + boardWidth/2 - mapWidth);
		}
	}
	
	if(players[playerIndex].y + tileSize/2 - boardHeight/2 > 0 &&
			players[playerIndex].y + tileSize/2 + boardHeight/2 < mapHeight) {
		posY = this.y - (players[playerIndex].y + playerTileSize/2) + boardHeight/2;
	}
	else {
		if(players[playerIndex].y + tileSize/2 - boardHeight/2 <= 0) {
			posY = this.y;
		}
		if(players[playerIndex].y + tileSize/2 + boardHeight/2 >= mapHeight) {
			posY = this.y - players[playerIndex].y + boardHeight/2 + (players[playerIndex].y + boardHeight/2 - mapHeight);
		}
	}
	
	canvasContext.drawImage(this.image, coords[0], coords[1], coords[2], coords[3], posX, posY, missileTileSize, missileTileSize);
}