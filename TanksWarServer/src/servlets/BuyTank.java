package servlets;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;

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

/**
 * Servlet implementation class BuyTank
 */
@WebServlet("/BuyTank")
public class BuyTank extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyTank() {
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
		
		String p=request.getParameter("price"); 
		String tank_id=request.getParameter("tank"); 
		System.out.println("tank idd"+tank_id);
		Integer money=null,price=Integer.parseInt(p);
		
		
		Object username =  request.getSession().getAttribute("user"); 
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseURI());
		Integer id=null;
		JsonArray users;
		users=target.path("api").path("ssw").path("getUsers").request().accept(MediaType.APPLICATION_JSON).get(JsonArray.class);
		JsonObject user;
		for(Integer i=0;i<users.size();i++) {
			user=users.getJsonObject(i);
			if(user.getString("username").equals((String)username)){
				id=user.getInt("id");
				money=user.getInt("money");
			}
		}
		
		if(money>=price) {
			System.out.println(" enough money to buy tank");
			JsonObject tpObject=Json.createReader(new StringReader("{\"user_id\":\""+id+"\",\"tank_id\":"+Integer.parseInt(tank_id)+"}")).readObject();
			if(target.path("api/ssw/insertusertank").request(MediaType.TEXT_PLAIN).post(Entity.entity(tpObject, MediaType.APPLICATION_JSON),Boolean.class)){
				System.out.println("New tank has been iserted into user_tank table");
				price=0-price;
				System.out.println("money to update= "+price);
				if(target.path("api/ssw/updatemoney/"+id.toString()).request(MediaType.TEXT_PLAIN).put(Entity.entity(price, MediaType.TEXT_PLAIN),Boolean.class)){
					System.out.println("Money has been updated!");
				}
				else {
					System.out.println("Money hasn't been updated!");
				}
			}
			else {
				System.out.println("The tank hasn't been iserted into user_tank table");
			}
		}else {
			session.setAttribute("no_money_"+tank_id, "No money");
			System.out.println("not enough money");
		}
		System.out.println(price);
		response.sendRedirect("tanks.jsp");
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
