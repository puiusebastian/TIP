<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
%>
<%@ page import="servlets.TankPicked" %>
<%@ page import="servlets.UpdateStats" %>
<%		Object username =  request.getSession().getAttribute("user"); 
		if( username != null){
			Boolean t=UpdateStats.UpdateGamesNumber((String)username, 1, 0);
			out.println(t);
%>
<!DOCTYPE html>
<html>
<head>
  <title>Tank War</title>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
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
	
	<header class="row">
            <p>IT'S YOUR TIME TO WIN!</p>
        </header>
               
        <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->
            <ol class="carousel-indicators">
                  <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                  <li data-target="#myCarousel" data-slide-to="1"></li>
                  <li data-target="#myCarousel" data-slide-to="2"></li>
                  <li data-target="#myCarousel" data-slide-to="3"></li>
            </ol>

            <!-- Wrapper for slides -->
            <div class="carousel-inner">

                  <div class="item active">
                    <img src="css/images6.jpg" style="width:100%;">
                  </div>

                  <div class="item">
                    <img src="css/Capture2.PNG"style="width:100%;">
                  </div>

                  <div class="item">
                    <img src="css/Capture.PNG" style="width:100%;">
                  </div>
                
                  <div class="item">
                    <img src="css/Capture1.PNG" style="width:100%;">
                  </div>
            </div>

            <!-- Left and right controls -->
            <a class="left carousel-control" href="#myCarousel" data-slide="prev">
                  <span class="glyphicon glyphicon-chevron-left"></span>
                  <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#myCarousel" data-slide="next">
                  <span class="glyphicon glyphicon-chevron-right"></span>
                  <span class="sr-only">Next</span>
            </a>
        </div>

        <br>
         <div class="play_button">
            <a href="game.jsp" style="cursor:pointer !important; all:initial" ><img src="css/button.jpg" style="width: 150px; height: auto;"></a> 
        </div>
	
	</div>
</body>
<%}else{
	response.sendRedirect("login.jsp");
} %>
