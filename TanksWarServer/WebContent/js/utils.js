/**
 * 
 */

function getImage(name) {
	switch(name) {
		case "blue_tank":
			return 'assets/bluetank.png';
			break;
		case "red_tank":
			return 'assets/redtank.png';
			break;
		case "disabled_tank":
			return 'assets/disabledtank.png';
			break;
		case "missile":
			return 'assets/tank_constr1.png';
			break;
	}
}

function getGraphicElementCoords(name) {
	switch(name) {
		case "tank_move_up":
            return [2, 1, 29, 29];
            break;
		case "tank_move_down":
			return [2, 34, 29, 29];
            break;
		case "tank_move_left":
            return [1, 66, 29, 29];
            break;
		case "tank_move_right":
			return [1, 98, 29, 29];
            break;
		case "missile":
			return [344, 88, 16, 16];
			break;
	}
}