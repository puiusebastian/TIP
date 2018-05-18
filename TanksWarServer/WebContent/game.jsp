<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
%>
<%		Object username =  request.getSession().getAttribute("user");        //********* for getting picked tank by user use:
		if( username != null){												//********** Object tank =  request.getSession().getAttribute((String)username);
%>
<!DOCTYPE html PUBLIC>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>TanksWar Game</title>
	
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  	<link rel="stylesheet" href="css/index_style.css">
	
	<link rel="stylesheet" href="css/font/font.css">
	<link rel="stylesheet" href="css/style.css">
	
	<script src="js/websocket.js"></script>
	<script src="js/missile.js"></script>
	<script src="js/player.js"></script>
	<script src="js/utils.js"></script>
	<script src="js/maps.js"></script>
	<script src="js/game.js"></script>
	<script src="js/game.js"></script>
</head>
<body>
	<nav class='navbar navbar-invers title-bar' >
	  <div class="container-fluid">
	    <ul class="nav navbar-nav">
	      <li><span id="a-home"><a href="index.jsp"><span class="glyphicon glyphicon-home"></span> HOME</a></span></li> 
	      <li><a href="tanks.jsp">Tanks</a></li>
	      <li><a href="stats.jsp">Stats</a></li>
	      <li><a href="game.jsp">Game</a></li>
	    </ul>
	    <ul class="nav navbar-nav navbar-right">
	      <li id='user_name'><%out.println(username);%></li>
	      <li><span><a href="Logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></span></li>
	    </ul> 
	  </div>
	</nav>
<%		Object tank =  request.getSession().getAttribute((String)username); 
		if( !(tank != null)){
%>
	<form action="PickTank" method="post">
	  <input type="radio" name="tank" value="tank1"> tank1<br>
	  <input type="radio" name="tank" value="tank2"> tank2<br>
	  <input type="radio" name="tank" value="tank3"> tank3<br>
	  <input type="radio" name="tank" value="tank4"> tank4<br>
	  <input type="radio" name="tank" value="tank5"> tank5<br>
	  <button type="submit">Pick</button>
	</form> 
<%}else{%>
	<section id="game_section" >
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
			
			<div id="end_message" style="display: none"></div>
		</div>
	</section>
	<script>gameLobby()</script>
</body>
</html>
<%}}else{response.sendRedirect("login.jsp");} %>