<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC>
<html>
<head>
<title>TanksWar Login</title>
<link rel="stylesheet" href="css/login_style.css">
</head>
<body>
	<div class="login-page">
		<div class="form">
			<form class="login-form" action="Login" method="post">
				<input type="text" placeholder="username" name="username"/> 
				<input type="password" placeholder="password" name="userpass" />
				<button type="submit">login</button>
				<p class="message">
					Not registered? <a href="register.jsp">Create an account</a>
				</p>
<% 				
				Object error =  request.getSession().getAttribute("login_error");
				//if the username or password are invalid
				if(error!=null){
%>
				<div class="isa_error">
					<p class="error">
<%
						out.println(error);
						request.getSession().removeAttribute("login_error");
					}
%>
					</p>
				</div>				
			</form>
		</div>
	</div>
</body>
</html>