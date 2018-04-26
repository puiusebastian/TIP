<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
%>
<%		Object username =  request.getSession().getAttribute("user"); 
		if( username != null){
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Tank War</title>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <meta name="viewport" content="width=device-width, initial-scale=1">
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
</body>
<%}else{
	response.sendRedirect("login.jsp");
} %>
