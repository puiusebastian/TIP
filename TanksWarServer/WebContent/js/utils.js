/**
 * 
 */

function blockToPixel(block, blockSize) {
	return block * blockSize;
}

function pixelToBlock(pixel, blockSize) {
	return Math.floor(pixel / blockSize)
}

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
		case "tilesheet":
			return 'assets/terrainTiles_default.png';
			break;
		case "spritesheet":
			return 'assets/onlyObjects_default.png';
			break;
	}
}

function getGraphicElementCoords(name) {
	switch(name) {
		case "spritesheet_black_tank":
            return [174, 0, 42, 46];
            break;
		case "spritesheet_blue_tank":
			return [173, 60, 42, 46];
            break;
		case "spritesheet_green_tank":
            return [215, 46, 42, 46];
            break;
		case "spritesheet_red_tank":
			return [258, 184, 38, 46];
            break;
		case "spritesheet_beige_tank":
			return [216, 248, 42, 46];
            break;
		case "missile":
			return [344, 88, 16, 16];
			break;
		case "tilesheet_grass":
			return [0, 0, 64, 64];
			break;
		case "tilesheet_sand":
			return [0, 128, 64, 64];
			break;
		case "tilesheet_left_grass_right_sand":
			return [448, 0, 64, 64];
			break;
		case "tilesheet_road_left_gray_right_brown":
			return [448, 128, 64, 64];
			break;
		case "tilesheet_road_gray_left_right":
			return [128, 0, 64, 64];
			break;
		case "tilesheet_road_gray_top_bottom":
			return [64, 0, 64, 64];
			break;
		case "tilesheet_road_gray_bottom_right":
			return [192, 64, 64, 64];
			break;
		case "tilesheet_road_gray_bottom_left":
			return [256, 64, 64, 64];
			break;
		case "tilesheet_road_gray_top_right":
			return [320, 64, 64, 64];
			break;
		case "tilesheet_road_gray_top_left":
			return [384, 64, 64, 64];
			break;
		case "tilesheet_road_gray_top_bottom_right":
			return [192, 0, 64, 64];
			break;
		case "tilesheet_road_gray_top_bottom_left":
			return [256, 0, 64, 64];
			break;
		case "tilesheet_road_gray_top_left_right":
			return [320, 0, 64, 64];
			break;
		case "tilesheet_road_gray_bottom_left_right":
			return [384, 0, 64, 64];
			break;
		case "tilesheet_road_gray_top_bottom_left_right":
			return [64, 64, 64, 64];
			break;
		case "tilesheet_road_brown_left_right":
			return [128, 128, 64, 64];
			break;
		case "tilesheet_road_brown_top_bottom":
			return [64, 128, 64, 64];
			break;
		case "tilesheet_road_brown_bottom_right":
			return [192, 192, 64, 64];
			break;
		case "tilesheet_road_brown_bottom_left":
			return [256, 192, 64, 64];
			break;
		case "tilesheet_road_brown_top_right":
			return [320, 192, 64, 64];
			break;
		case "tilesheet_road_brown_top_left":
			return [384, 192, 64, 64];
			break;
		case "tilesheet_road_brown_top_bottom_right":
			return [192, 128, 64, 64];
			break;
		case "tilesheet_road_brown_top_bottom_left":
			return [256, 128, 64, 64];
			break;
		case "tilesheet_road_brown_top_left_right":
			return [320, 128, 64, 64];
			break;
		case "tilesheet_road_brown_bottom_left_right":
			return [384, 128, 64, 64];
			break;
		case "tilesheet_road_brown_top_bottom_left_right":
			return [64, 192, 64, 64];
			break;
	}
}

function displayScores(playersArray) {
	var scoreContainer = document.getElementById("score_container");
	if(scoreContainer.style.display === "none") {
		scoreContainer.style.display = "block";
	}
	else {
		scoreContainer.style.display = "none";
	}
	
	var table;
	
	var firstTeamWrapper = document.getElementById("first_team_wrapper");
	table = createPlayersTeamTable(playersArray, 1);
	firstTeamWrapper.innerHTML = table;
	
	var secondTeamWrapper = document.getElementById("second_team_wrapper");
	table = createPlayersTeamTable(playersArray, 2);
	secondTeamWrapper.innerHTML = table;
}

function createPlayersTeamTable(playersArray, playersTeam) {
	var table = "";
	table += (playersTeam == 1 ? "<table class=\"players_table blue_text\" id=\"first_team_players\">" : "<table class=\"players_table red_text\" id=\"second_team_players\">") +
				'<thead>' + 
					'<tr>' +
					'<th><img src="assets/empty.png" width=20></th>' +
					'<th id="players_table_username">Player</th>' +
					'<th>Kills</th>' +
					'<th>Deaths</th>' +
					'</tr>' +
				'</thead>';
	for(var i = 0; i < playersArray.length; ++i) {
		if(playersArray[i].team == playersTeam) {
			if(i == playerIndex) {
				table += '<tr class="white_text">';
			}
			else {
				table += '<tr>';
			}
			if(playersArray[i].alive == true) {
				table += '<td id="players_table_alive"><img src="assets/empty.png" width=20></td>';
			}
			else {
				table += '<td id="players_table_alive"><img src="assets/skull.png" width=20></td>';
			}
			table += '<td>' + playersArray[i].username + '</td>';
			table += '<td>' + playersArray[i].kills + '</td>';
			table += '<td>' + playersArray[i].deaths + '</td>';
			table += '</tr>';
		}
	}
	table += '</table>';
	
	return table;
}