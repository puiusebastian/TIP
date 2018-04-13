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
	var coords = [344, 88, 16, 16];
	
	canvasContext.drawImage(this.image, coords[0], coords[1], coords[2], coords[3], this.x, this.y, 10, 10);
}