<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
	import="javax.json.JsonArray"
	import="javax.json.JsonObject"
	import="java.io.StringReader"
	import ="javax.json.Json"
%>
<%		Object username =  request.getSession().getAttribute("user"); 
		if( username != null){
			
%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
</head>

<body>
	<div class="row">
	  <div class="column" onclick="openTab('b1');" style="background: grey ;">
	    <img src="css/tank_dark.png">
	    <p>DARK TANK</p>
	    <form action="" method="post">
	    	<input type='text' name='price' value='45' hidden=true/>
	    	<button type="submit" class="btn" <%if(false){%>style="visibility: hidden;" <%}%>>BUY</button>
	    </form>
	  </div>
	  <div class="column" onclick="openTab('b2');" style="background:grey;">
	    <img src="css/tank_red.png">
	    <p>SAND TANK</p>
	    <form action="" method="post">
	    	<input type='text' name='price' value='45' hidden=true/>
	    	<button type="submit" class="btn" <%if(false){%>style="visibility: hidden;" <%}%>>BUY</button>
	    </form>
	  </div>
	  <div class="column" onclick="openTab('b3');" style="background:grey;">
	    <img src="css/tank_dark.png">
	    <p>RED TANK</p>
	    <form action="" method="post">
	    	<input type='text' name='price' value='45' hidden=true/>
	    	<button type="submit" class="btn" <%if(false){%>style="visibility: hidden;" <%}%>>BUY</button>
	    </form>
	  </div>
	</div>
	
	<div id="b1" class="containerTab" style="display:none;background:#88421D">
	  <span onclick="this.parentElement.style.display='none'" class="closebtn">&times;</span>
	  	<p style="font-size:16px"><b>DARK TANK</b></p>
	   <p>speed: asd</p>
	   <p>health: asdasd</p>
	   <p>damage: </p>
	   <p>range:</p>
	   <p>price:</p>
	</div>
	
	<div id="b2" class="containerTab" style="display:none;background:#88421D">
	  <span onclick="this.parentElement.style.display='none'" class="closebtn">&times;</span>
	  <p>speed: asd</p>
	   <p>health: asdasd</p>
	   <p>damage: </p>
	   <p>range:</p>
	   <p>price:</p>
	</div>
	
	<div id="b3" class="containerTab" style="display:none;background:#88421D">
	  <span onclick="this.parentElement.style.display='none'" class="closebtn">&times;</span>
	  <p>speed: asd</p>
	   <p>health: asdasd</p>
	   <p>damage: </p>
	   <p>range:</p>
	   <p>price:</p>
	</div>
	
	<div id="b4" class="containerTab" style="display:none;background:#88421D">
	  <span onclick="this.parentElement.style.display='none'" class="closebtn">&times;</span>
	  <p>speed: asd</p>
	   <p>health: asdasd</p>
	   <p>damage: </p>
	   <p>range:</p>
	   <p>price:</p>
	</div>
	
	<div id="b5" class="containerTab" style="display:none;background:#88421D">
	  <span onclick="this.parentElement.style.display='none'" class="closebtn">&times;</span>
	  <p>speed: asd</p>
	   <p>health: asdasd</p>
	   <p>damage: </p>
	   <p>range:</p>
	   <p>price:</p>
	</div>
	
	
	<div class="row second-row">
	  <div class="column" onclick="openTab('b4');" style="background:grey;">
	    <img src="css/tank_green.png">
	    <p>GREEN TANK</p>
	    <form action="" method="post">
	    	<input type='text' name='price' value='45' hidden=true/>
	    	<button type="submit" class="btn" <%if(false){%>style="visibility: hidden;" <%}%>>BUY</button>
	    </form>
	  </div>
	  <div class="column" onclick="openTab('b5');" style="background:grey;">
	    <img src="css/tank_blue.png">
	    <p>BLUE TANK</p>
	    <form action="" method="post">
	    	<input type='text' name='price' value='45' hidden=true/>
	    	<button type="submit" class="btn" <%if(false){%>style="visibility: hidden;" <%}%>>BUY</button>
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
