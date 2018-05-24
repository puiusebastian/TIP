<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
%>
<%@page import="javax.json.JsonObject"%>
<%@page import="javax.json.JsonArray"%>
<%@ page import="servlets.TanksHelper" %>
<%@ page import="servlets.UserTankHelper" %>
<%@ page import="servlets.UsersHelper" %>

<%		Object money_error,username =  request.getSession().getAttribute("user"); 
		if( username != null){ 	
			Boolean ok;
			JsonArray tanksSpec = TanksHelper.GetTanks();   
			JsonArray users_tanks = UserTankHelper.GetUsersTanks();
			JsonArray users=UsersHelper.GetUsers();
			Integer id=-1,j=0;
			Integer []tanksarray=new Integer[5];

			JsonObject user,tank;
			
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
	<div class="container">
	<div class="row">
	  <div class="column" onclick="openTab('b1');" style="background: grey ;">
	    <img src="css/tank_dark.png">
<%
	tank=tanksSpec.getJsonObject(0);
%>
	    <p><%out.println(tank.getString("name")); %></p>
	  </div>
	  <div class="column" onclick="openTab('b2');" style="background:grey;">
	    <img src="css/tank_red.png">
<%
	tank=tanksSpec.getJsonObject(1);
	ok=false;
	for(int i=0;i<j;i++){
		if(tanksarray[i]==2){
			ok=true;
		}
	}
	money_error =  request.getSession().getAttribute("no_money_"+tank.getInt("id"));
%>
	    <p><%out.println(tank.getString("name")); %></p>
	    <form action="BuyTank" method="post">
	    	<input type='text' name='price' value=<% out.println(tank.getInt("price"));%> hidden=true/>
	    	<input type='text' name='tank' value=<% out.println(tank.getInt("id"));%> hidden=true/>
	    	<button type="submit" class="btn" <%if(ok){%>style="visibility: hidden;" <%}%>>BUY</button>
	    	<%if(money_error!=null){%><p style="color: #8B0000; font-size:12px">*You don't have enough money to buy this tank!</p>
	    	<%} request.getSession().removeAttribute("no_money_"+tank.getInt("id"));%>
	    </form>
	  </div>
	  <div class="column" onclick="openTab('b3');" style="background:grey;">
	    <img src="css/tank_sand.png">
<%
ok=false;
for(int i=0;i<j;i++){
	if(tanksarray[i]==3){
		ok=true;
	}
}
	tank=tanksSpec.getJsonObject(2);
	money_error =  request.getSession().getAttribute("no_money_"+tank.getInt("id"));
%>
	    <p><%out.println(tank.getString("name")); %></p>
	    <form action="BuyTank" method="post">
	    	<input type='text' name='price' value=<% out.println(tank.getInt("price"));%> hidden=true/>
	    	<input type='text' name='tank' value=<% out.println(tank.getInt("id"));%> hidden=true/>
	    	<button type="submit" class="btn" <%if(ok){%>style="visibility: hidden;" <%}%>>BUY</button>
	    	<%if(money_error!=null){%><p style="color: #8B0000; font-size:12px">*You don't have enough money to buy this tank!</p>
	    	<%} request.getSession().removeAttribute("no_money_"+tank.getInt("id"));%>
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
	   <p>price:<%out.println(tank.getInt("price")); %></p>
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
	  <p>price:<%out.println(tank.getInt("price")); %></p>
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
	   <p>price:<%out.println(tank.getInt("price")); %></p>
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
	   <p>price:<%out.println(tank.getInt("price")); %></p>
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
	   <p>price:<%out.println(tank.getInt("price")); %></p>
	</div>
	
	
	<div class="row second-row">
	  <div class="column" onclick="openTab('b4');" style="background:grey;">
	    <img src="css/tank_green.png">
<%
tank=tanksSpec.getJsonObject(3);
ok=false;
for(int i=0;i<j;i++){
	if(tanksarray[i]==4){
		ok=true;
	}
}
money_error =  request.getSession().getAttribute("no_money_"+tank.getInt("id"));
%>
	    <p><%out.println(tank.getString("name")); %></p>
	    <form action="BuyTank" method="post">
	    	<input type='text' name='price' value=<% out.println(tank.getInt("price"));%> hidden=true/>
	    	<input type='text' name='tank' value=<% out.println(tank.getInt("id"));%> hidden=true/>
	    	<button type="submit" class="btn" <%if(ok){%>style="visibility: hidden;" <%}%>>BUY</button>
	    	<%if(money_error!=null){%><p style="color: #8B0000; font-size:12px">*You don't have enough money to buy this tank!</p>
	    	<%} request.getSession().removeAttribute("no_money_"+tank.getInt("id"));%>
	    </form>
	  </div>
	  <div class="column" onclick="openTab('b5');" style="background:grey;">
	    <img src="css/tank_blue.png">
<%
tank=tanksSpec.getJsonObject(4);
ok=false;
for(int i=0;i<j;i++){
	if(tanksarray[i]==5){
		ok=true;
	}
}
money_error =  request.getSession().getAttribute("no_money_"+tank.getInt("id"));
%>
	    <p><%out.println(tank.getString("name")); %></p>
	    <form action="BuyTank" method="post">
	    	<input type='text' name='price' value=<% out.println(tank.getInt("price"));%> hidden=true/>
	    	<input type='text' name='tank' value=<% out.println(tank.getInt("id"));%> hidden=true/>
	    	<button type="submit" class="btn" <%if(ok){%>style="visibility: hidden;" <%}%>>BUY</button>
	    	<%if(money_error!=null){%><p style="color: #8B0000; font-size:12px">*You don't have enough money to buy this tank!</p>
	    	<%} request.getSession().removeAttribute("no_money_"+tank.getInt("id"));%>
	    </form>
	  </div>
	</div>
	
	<footer class="page-footer font-small blue pt-4 mt-4">
		<div class="footer-copyright py-3 text-center">
        	© 2018 Copyright -- Petrasco Ilie, Puiu Sebastian, Ungureanu Ionut, Gavrila Maria
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
	</div>
</body>
</html>
<%}else{
	response.sendRedirect("login.jsp");
} %>
