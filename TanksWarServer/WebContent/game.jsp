<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>TanksWar Game</title>
	
	<link rel="stylesheet" href="css/font/font.css">
	<link rel="stylesheet" href="css/style.css">
	
	<script src="js/websocket.js"></script>
	<script src="js/missile.js"></script>
	<script src="js/player.js"></script>
	<script src="js/utils.js"></script>
	<script src="js/maps.js"></script>
	<script src="js/game.js"></script>
</head>
<body onload="gameLobby()">
	<section id="game_section">
		<div id="game_wrapper">
			<div id="canvas_container">
				<canvas id="game_canvas" width="800" height="600"></canvas>
			</div>
			<div id="score_container" style="display: none">
				<div class="centered_text"><span id="round_time_elapsed"></span></div>
				<div class="centered_text">Rounds left: <span id="round_number"></span></div>
				<hr>
				<div class="blue_text"><span id="first_team_score"></span> wins</div>
				<div id="first_team_wrapper"></div>
				<hr>
				<div class="red_text"><span id="second_team_score"></span> wins</div>
				<div id = "second_team_wrapper"></div>
			</div>
		</div>
	</section>
</body>
</html>