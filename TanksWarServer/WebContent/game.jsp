<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>TanksWar Game</title>
	
	<link rel="stylesheet" href="css/style.css">
	
	<script src="js/websocket.js"></script>
	<script src="js/missile.js"></script>
	<script src="js/player.js"></script>
	<script src="js/utils.js"></script>
	<script src="js/game.js"></script>
</head>
<body onload="loadGame()">
	<section id="game_section">
		<div>
			Round time: <span id="round_time_elapsed"></span>
		</div>
		<div>
			Round number: <span id="round_number"></span>
		</div>
		<div>
			Blue team score: <span id="first_team_score"></span>
		</div>
		<div>
			Red team score: <span id="second_team_score"></span>
		</div>
		<canvas id="game_canvas" width="800" height="600"></canvas>
	</section>
</body>
</html>