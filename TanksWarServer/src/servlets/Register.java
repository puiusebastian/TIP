package servlets;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	
		HttpSession session = request.getSession();
		
		String username=request.getParameter("username");  
	    String password=request.getParameter("password");
	    String name=request.getParameter("name");  
	    String email=request.getParameter("email");
	    String age=request.getParameter("age");  
	    
	    if(username.isEmpty()||password.isEmpty()||name.isEmpty()||email.isEmpty()||age.isEmpty()) {
	    	session.setAttribute("register_error", "*Invalid inputs!");
			System.out.println("Not really Succesful Register. Error on input!");
			response.sendRedirect("register.jsp");
	    }else {

			ClientConfig config = new ClientConfig();
			Client client = ClientBuilder.newClient(config);	
			WebTarget target = client.target(getBaseURI());
	    
		    JsonObject userObject=Json.createReader(new StringReader("{\"username\":\""+username+"\",\"password\":\""+password+"\",\"name\":\""+name+"\",\"email\":\""+email+"\",\"age\":"+Integer.parseInt(age)+"}")).readObject();
			if(target.path("api/ssw/insertuser").request(MediaType.TEXT_PLAIN).post(Entity.entity(userObject, MediaType.APPLICATION_JSON),Boolean.class)){
				System.out.println("Successful Register");
				response.sendRedirect("login.jsp");
			}
			else {
				session.setAttribute("register_error", "Sorry, register failed. Try again with another username!");
				System.out.println("Not really Successful Register. Error on insert in database!");
				response.sendRedirect("register.jsp");
			}
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8888/").build();
	}
}
