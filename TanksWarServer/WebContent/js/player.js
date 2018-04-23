/**
 * 
 */

function Player(imageSrc, posX, posY, team) {
	this.image = new Image();
	this.image.src = imageSrc;

	this.x = posX;
	this.y = posY;
	
	this.team = team;

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

	switch(this.movementDirection) {
		case "up":
			/*if(this.animation["up"] <= this.timeBetweenAnimStates) {
				coords = getGraphicElement("vertical_walk_up_1");
			}
			else if(this.animation["up"] <= 2 * this.timeBetweenAnimStates) {
				coords = getGraphicElement("vertical_walk_up_2");
			}
			else if(this.animation["up"] <= 3 * this.timeBetweenAnimStates) {
				coords = getGraphicElement("vertical_walk_up_3");
			}*/
			
			//coords = [2, 0, 29, 31];
			coords = getGraphicElementCoords("tank_move_up");
			break;
		case "right":
			/*if(this.animation["right"] <= this.timeBetweenAnimStates) {
				coords = getGraphicElement("horizontal_walk_right_1");
			}
			else if(this.animation["right"] <= 2 * this.timeBetweenAnimStates) {
				coords = getGraphicElement("horizontal_walk_right_2");
			}
			else if(this.animation["right"] <= 3 * this.timeBetweenAnimStates) {
				coords = getGraphicElement("horizontal_walk_right_3");
			}*/
			
			//coords = [0, 98, 31, 29];
			coords = getGraphicElementCoords("tank_move_right");
			break;
		case "down":
			/*if(this.animation["down"] <= this.timeBetweenAnimStates) {
				coords = getGraphicElement("vertical_walk_down_1");
			}
			else if(this.animation["down"] <= 2 * this.timeBetweenAnimStates) {
				coords = getGraphicElement("vertical_walk_down_2");
			}
			else if(this.animation["down"] <= 3 * this.timeBetweenAnimStates) {
				coords = getGraphicElement("vertical_walk_down_3");
			}*/
			
			//coords = [2, 33, 29, 31];
			coords = getGraphicElementCoords("tank_move_down");
			break;
		case "left":
			/*if(this.animation["left"] <= this.timeBetweenAnimStates) {
				coords = getGraphicElement("horizontal_walk_left_1");
			}
			else if(this.animation["left"] <= 2 * this.timeBetweenAnimStates) {
				coords = getGraphicElement("horizontal_walk_left_2");
			}
			else if(this.animation["left"] <= 3 * this.timeBetweenAnimStates) {
				coords = getGraphicElement("horizontal_walk_left_3");
			}*/
			
			//coords = [0, 66, 31, 29];
			coords = getGraphicElementCoords("tank_move_left");
			break;
	}

	// Determine the character's dimensions
	var width = tileSize;
	var height = (coords[3] * tileSize) / coords[2];
	// Determine the character's position
	var posX = this.x - (players[playerIndex].x + tileSize/2) + boardWidth/2;
	var posY = this.y - (players[playerIndex].y + tileSize/2) + boardHeight/2;
	//var posY = this.y - (height - tileSize) - (players[playerIndex].y + tileSize/2) + boardHeight/2;
	// Draw the character
	canvasContext.drawImage(this.image, coords[0], coords[1], coords[2], coords[3], posX, posY, width, height);
	
}