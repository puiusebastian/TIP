package servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	          
	    String n=request.getParameter("username");  
	    String p=request.getParameter("userpass");  
	          	             
	    ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
	    
	    WebTarget target = client.target(getBaseURI());
		
		boolean responseFromRest=target.path("api").path("checkuser").request(MediaType.TEXT_PLAIN)
				.post(Entity.entity(n+p,MediaType.TEXT_PLAIN),Boolean.class);
		
		if(responseFromRest)
			System.out.println("Successful login");
		else
			System.out.println("Not really Successful login");
	    
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
