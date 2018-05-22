package servlets;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.client.Entity;
import org.glassfish.jersey.client.ClientConfig;


/**
 * Servlet implementation class PickTank
 */
@WebServlet("/PickTank")
public class PickTank extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PickTank() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		
		

		WebTarget target = client.target(getBaseURI());
		HttpSession session = request.getSession();
		
	    String t=request.getParameter("tank"); 
	    Object username =  request.getSession().getAttribute("user");
	    
	    JsonObject tpObject=Json.createReader(new StringReader("{\"user\":\""+(String) username+"\",\"tank\":"+Integer.parseInt(t)+"}")).readObject();
		if(target.path("api/ssw/inserttankpicked").request(MediaType.TEXT_PLAIN).post(Entity.entity(tpObject, MediaType.APPLICATION_JSON),Boolean.class)){
			System.out.println("Line in tank_picked was inserted!");
		}
		else {
			System.out.println("Line in tank_picked wasn't inserted!");
		}
	    
	    session.setAttribute( (String) username, t);                   //use username to create a session ----  (user, tank_picked)
		//setting session to expiry in 30 mins
		session.setMaxInactiveInterval(30*60);
		response.sendRedirect("game.jsp");
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