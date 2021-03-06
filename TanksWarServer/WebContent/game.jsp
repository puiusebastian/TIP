<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
%>
<%@page import="javax.json.JsonObject"%>
<%@page import="javax.json.JsonArray"%>
<%@ page import="servlets.TanksHelper" %>
<%@ page import="servlets.UserTankHelper" %>
<%@ page import="servlets.UsersHelper" %>
<%		Object username =  request.getSession().getAttribute("user");        
		if( username != null){							
			Boolean ok;
			JsonArray tanksSpec = TanksHelper.GetTanks();	
			JsonArray users_tanks = UserTankHelper.GetUsersTanks();
			JsonArray users=UsersHelper.GetUsers();
			Integer id=-1,j=0;
			Integer []tanksarray=new Integer[5];

			JsonObject user,t;
			
			for(int i=0;i<users.size();i++){              //get te user_id using usernamesession variable
				user=users.getJsonObject(i);
				if(user.getString("username").equals(username)){
					id=user.getInt("id");
				}
			}

			for(int i=0;i<users_tanks.size();i++)
			{
				user=users_tanks.getJsonObject(i);
				if(user.getInt("UT_userId")==id){
					tanksarray[j]=user.getInt("UT_tankId");  // get the user tanks available into an array
					j++;
				}
			}
			if(j==0){  										//in case of the user does not have any tanks
				j=4;
				tanksarray[0]=0;
				tanksarray[1]=0;
				tanksarray[2]=0;
				tanksarray[3]=0;
			}
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
	
	<link rel="stylesheet" href="css/game_style.css">
	
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
			t=tanksSpec.getJsonObject(0);
%>
	<div class="row-tank">
        <form action="PickTank" method="post">
            	<label class="column">
            		<input type='radio' name="tank" value=1>
            		<img src="css/tank_dark.png" height="280" width="250">
                    <p class="tank_name"><%out.println(t.getString("name")); %></p>
            	</label>  
<%
t=tanksSpec.getJsonObject(1);
ok=false;
for(int i=0;i<j;i++){
	if(tanksarray[i]==2){
		ok=true;
	}
}
%>    
            	<label class="column">
            		<input type='radio' name="tank" value=2 <%if(!ok){%>disabled<%} %>>
            		<img src="css/tank_red.png" height="280 " width="250">
                    <p class="tank_name"><%out.println(t.getString("name")); %></p>
            	</label>
<%
t=tanksSpec.getJsonObject(2);
ok=false;
for(int i=0;i<j;i++){
	if(tanksarray[i]==3){
		ok=true;
	}
}
%> 
                <label class="column">
                    <input type='radio' name="tank" value=3 <%if(!ok){%>disabled<%} %>>
                    <img src="css/tank_sand.png" height="280 " width="250">
                    <p class="tank_name"><%out.println(t.getString("name")); %></p>
                </label>
<%
t=tanksSpec.getJsonObject(3);
ok=false;
for(int i=0;i<j;i++){
	if(tanksarray[i]==4){
		ok=true;
	}
}
%> 
                <label class="column">
                    <input type='radio' name="tank" value=4 <%if(!ok){%>disabled<%} %>>
                    <img src="css/tank_green.png" height="280 " width="250">
                    <p class="tank_name"><%out.println(t.getString("name")); %></p>
                </label>
<%
t=tanksSpec.getJsonObject(4);
ok=false;
for(int i=0;i<j;i++){
	if(tanksarray[i]==5){
		ok=true;
	}
}
%> 
                <label class="column">
                    <input type='radio' name="tank" value=5 <%if(!ok){%>disabled<%} %>>
                    <img src="css/tank_blue.png" height="280 " width="250">
                    <p class="tank_name"><%out.println(t.getString("name")); %></p>
                </label><br><br><br><br><br><br>
                <button id="submit_button" class="submit btn btn-primary btn-block" type="submit" disabled="disabled" name="submit">Start Game</button>
        </form>
    </div>
    
<script>
	$("input:radio").change(function () {
	    $("#submit_button").attr("disabled", false);
	});
</script>
    
<%}else{%>
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
			
			<div id="end_message" style="display: none"></div>
		</div>
	</section>
	<form method="post" action="EndGame" style="position: relative">
	<button type="submit" id="submit_button" class="btn btn-primary btn-endgame">End Game</button>
	</form>
	<script>
		gameLobby()
	</script>
</body>
</html>
<%}}else{response.sendRedirect("login.jsp");} %>