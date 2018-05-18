<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
%>
<%@page import="javax.json.JsonObject"%>
<%@page import="javax.json.JsonArray"%>
<%@ page import="servlets.UsersHelper" %>

<%		Object username =  request.getSession().getAttribute("user"); 
		if( username != null){ 
			JsonArray users = UsersHelper.GetUsers();
			JsonObject user;
%>
<!DOCTYPE html>
<html>
<head>
  <title>Tank War Stats</title>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <meta name="viewport" content="width=device-width, initial-scale=1">
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
		<table class="table table-dark table-striped ">
		<thead>
	      <tr>
	        <th>USER</th>
	        <th>Points</th>
	      </tr>
	      <tbody>
<%
for(int i=0;i<users.size();i++)
{
	user=users.getJsonObject(i);
	out.println("<tr><td>"+user.getString("username")+"</td><td>"+user.getInt("age")+"</td></tr>");
}
%>
		  </tbody>
    	</thead>
		</table>
	</div>

	<footer class="page-footer font-small blue pt-4 mt-4">
		<div class="footer-copyright py-3 text-center">
        	© 2018 Copyright -- Petrasco Ilie, Puiu Sebastian, Ungureanu Ionut, Garila Maria
    	</div>
	</footer>
</body>
<%}else{
	response.sendRedirect("login.jsp");
} %>