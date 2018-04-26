<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>TanksWar Register</title>
<link rel="stylesheet" href="css/login_style.css">
</head>
<body style='background-image: url("css/register_background.jpg") !important; background-size: cover; background-position: 100% 100%;'>
	<div class="login-page">
		<div class="form">
			<form class="login-form" action="Register" method="post">
				<input type="text" placeholder="name" name="name" required />
				<input type="email" placeholder="email" name="email" required/>
				<input type="number" min="5" max="99" placeholder="age" name="age" required/> 
				<input type="text" placeholder="username" name="username" required/>   
				<input type="password" placeholder="password" name="password" required/>
				<button>create</button>
				<p class="message">
					Already registered? <a href="login.jsp">Sign In</a>
				</p>
				
		</div>
	</div>
</body>
</html>