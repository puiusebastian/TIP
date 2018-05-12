package servlets;

import java.io.IOException;
import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import java.net.URI;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	          
		HttpSession session = request.getSession();
		
	    String n=request.getParameter("username");  
	    String p=request.getParameter("userpass");  
	    String user_pass = "{\"username\":\""+n+"\",\"password\":\""+p+"\"}";
	          	             
	    ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
	    
	    WebTarget target = client.target(getBaseURI());
	    		
		  
		JsonObject jsonObject=Json.createReader(new StringReader(user_pass)).readObject();
		
		boolean responseFromRest=target.path("api").path("checkuser").request(MediaType.TEXT_PLAIN)
				.post(Entity.entity(jsonObject,MediaType.APPLICATION_JSON),Boolean.class);
		
		
		if(responseFromRest) {
			System.out.println("Successful login");
			System.out.println(n);
			session.setAttribute("user", n);
			//setting session to expiry in 30 mins
			session.setMaxInactiveInterval(30*60);
			response.sendRedirect("index.jsp");
		}
		else {
			session.setAttribute("login_error", "*Invalid username or password!");
			System.out.println("Not really Successful login");
			response.sendRedirect("login.jsp");
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
