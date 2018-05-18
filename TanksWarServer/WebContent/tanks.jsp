<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
%>
<%@page import="javax.json.JsonObject"%>
<%@page import="javax.json.JsonArray"%>
<%@ page import="servlets.TanksHelper" %>

<%		Object username =  request.getSession().getAttribute("user"); 
		if( username != null){ 
			JsonArray tanksSpec = TanksHelper.GetTanks();
			JsonObject tank;
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Tank War Tanks</title>
	<link rel="stylesheet" href="css/tanks_style.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  	<link rel="stylesheet" href="css/index_style.css">		
  	
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

	<div class="row">
	  <div class="column" onclick="openTab('b1');" style="background: grey ;">
	    <img src="css/tank_dark.png">
<%
	tank=tanksSpec.getJsonObject(0);
%>
	    <p><%out.println(tank.getString("name")); %></p>
	    <form action="BuyTank" method="post">
	    	<input type='text' name='price' value='45' hidden=true/>
	    	<button type="submit" class="btn" <%if(false){%>style="visibility: hidden;<%}%>" >BUY</button>
	    	<%if(true){%><p style="color:red; font-size:12px">*You don't have enough money to buy this tank!</p><%}%> 
	    </form>
	  </div>
	  <div class="column" onclick="openTab('b2');" style="background:grey;">
	    <img src="css/tank_red.png">
<%
tank=tanksSpec.getJsonObject(1);
%>
	    <p><%out.println(tank.getString("name")); %></p>
	    <form action="BuyTank" method="post">
	    	<input type='text' name='price' value='45' hidden=true/>
	    	<button type="submit" class="btn" <%if(false){%>style="visibility: hidden;" <%}%>>BUY</button>
	    	<%if(true){%><p style="color:red; font-size:12px">*You don't have enough money to buy this tank!</p><%}%>
	    </form>
	  </div>
	  <div class="column" onclick="openTab('b3');" style="background:grey;">
	    <img src="css/tank_sand.png">
<%
	tank=tanksSpec.getJsonObject(2);
%>
	    <p><%out.println(tank.getString("name")); %></p>
	    <form action="BuyTank" method="post">
	    	<input type='text' name='price' value='45' hidden=true/>
	    	<button type="submit" class="btn" <%if(false){%>style="visibility: hidden;" <%}%>>BUY</button>
	    	<%if(true){%><p style="color:red; font-size:12px">*You don't have enough money to buy this tank!</p><%}%>
	    </form>
	  </div>
	</div>

<%
tank=tanksSpec.getJsonObject(0);
%>
	<div id="b1" class="containerTab" style="display:none;background:#88421D">
	  <span onclick="this.parentElement.style.display='none'" class="closebtn">&times;</span>
	   <p style="font-size:16px"><b><%out.println(tank.getString("name")); %></b></p>
	   <p>speed: <%out.println(tank.getInt("speed")); %></p>
	   <p>health: <%out.println(tank.getInt("health")); %></p>
	   <p>damage: <%out.println(tank.getInt("damage")); %></p>
	   <p>range:<%out.println(tank.getInt("range")); %></p>
	   <p>price:</p>
	</div>
<%
tank=tanksSpec.getJsonObject(1);
%>	
	<div id="b2" class="containerTab" style="display:none;background:#88421D">
	  <span onclick="this.parentElement.style.display='none'" class="closebtn">&times;</span>
	  <p style="font-size:16px"><b><%out.println(tank.getString("name")); %></b></p>
	  <p>speed: <%out.println(tank.getInt("speed")); %></p>
	  <p>health: <%out.println(tank.getInt("health")); %></p>
	  <p>damage: <%out.println(tank.getInt("damage")); %></p>
	  <p>range:<%out.println(tank.getInt("range")); %></p>
	  <p>price:</p>
	</div>
<%
tank=tanksSpec.getJsonObject(2);
%>	
	<div id="b3" class="containerTab" style="display:none;background:#88421D">
	  <span onclick="this.parentElement.style.display='none'" class="closebtn">&times;</span>
	  <p style="font-size:16px"><b><%out.println(tank.getString("name")); %></b></p>
	  <p>speed: <%out.println(tank.getInt("speed")); %></p>
	  <p>health: <%out.println(tank.getInt("health")); %></p>
	  <p>damage: <%out.println(tank.getInt("damage")); %></p>
	  <p>range:<%out.println(tank.getInt("range")); %></p>
	   <p>price:</p>
	</div>
<%
tank=tanksSpec.getJsonObject(3);
%>
	<div id="b4" class="containerTab" style="display:none;background:#88421D">
	  <span onclick="this.parentElement.style.display='none'" class="closebtn">&times;</span>
	  <p style="font-size:16px"><b><%out.println(tank.getString("name")); %></b></p>
	  <p>speed: <%out.println(tank.getInt("speed")); %></p>
	  <p>health: <%out.println(tank.getInt("health")); %></p>
	  <p>damage: <%out.println(tank.getInt("damage")); %></p>
	  <p>range:<%out.println(tank.getInt("range")); %></p>
	   <p>price:</p>
	</div>
<%
tank=tanksSpec.getJsonObject(4);
%>
	<div id="b5" class="containerTab" style="display:none;background:#88421D">
	  <span onclick="this.parentElement.style.display='none'" class="closebtn">&times;</span>
	  <p style="font-size:16px"><b><%out.println(tank.getString("name")); %></b></p>
	  <p>speed: <%out.println(tank.getInt("speed")); %></p>
	  <p>health: <%out.println(tank.getInt("health")); %></p>
	  <p>damage: <%out.println(tank.getInt("damage")); %></p>
	  <p>range:<%out.println(tank.getInt("range")); %></p>
	   <p>price:</p>
	</div>
	
	
	<div class="row second-row">
	  <div class="column" onclick="openTab('b4');" style="background:grey;">
	    <img src="css/tank_green.png">
<%
tank=tanksSpec.getJsonObject(3);
%>
	    <p><%out.println(tank.getString("name")); %></p>
	    <form action="BuyTank" method="post">
	    	<input type='text' name='price' value='45' hidden=true/>
	    	<button type="submit" class="btn" <%if(false){%>style="visibility: hidden;" <%}%>>BUY</button>
	    	<%if(true){%><p style="color:red; font-size:12px">*You don't have enough money to buy this tank!</p><%}%>
	    </form>
	  </div>
	  <div class="column" onclick="openTab('b5');" style="background:grey;">
	    <img src="css/tank_blue.png">
<%
tank=tanksSpec.getJsonObject(4);
%>
	    <p><%out.println(tank.getString("name")); %></p>
	    <form action="BuyTank" method="post">
	    	<input type='text' name='price' value='45' hidden=true/>
	    	<button type="submit" class="btn" <%if(false){%>style="visibility: hidden;" <%}%>>BUY</button>
	    	<%if(true){%><p style="color:red; font-size:12px">*You don't have enough money to buy this tank!</p><%}%>
	    </form>
	  </div>
	</div>
	
	<footer class="page-footer font-small blue pt-4 mt-4">
		<div class="footer-copyright py-3 text-center">
        	© 2018 Copyright
    	</div>
	</footer>
	
	<script>
		function openTab(tabName) {
		  var i, x;
		  x = document.getElementsByClassName("containerTab");
		  for (i = 0; i < x.length; i++) {
		     x[i].style.display = "none";
		  }
		  document.getElementById(tabName).style.display = "block";
		}
	</script>
</body>
</html>
<%}else{
	response.sendRedirect("login.jsp");
} %>
