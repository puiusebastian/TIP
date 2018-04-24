package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/username")
public class userName extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	HttpSession session;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		      throws ServletException, IOException {
		session = request.getSession();
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String getUserName() {
		Object user=session.getAttribute("user");
		return (String) user;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
