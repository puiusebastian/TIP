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
	
	var posX = this.x - (players[playerIndex].x + tileSize/2) + boardWidth/2;
	var posY = this.y - (players[playerIndex].y + tileSize/2) + boardHeight/2;
	
	canvasContext.drawImage(this.image, coords[0], coords[1], coords[2], coords[3], posX, posY, missileSize, missileSize);
}