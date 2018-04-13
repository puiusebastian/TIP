package webservices;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



@Path("/checkuser")
public class CheckUserWS {
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public boolean checkUser( String acc)
	{
		List<Users> players=new ArrayList<Users>();
		players=DBManager.getInstance().getUsersList();
		for(Users player:players)
		{
			if(acc.equals(player.getUsername()+player.getPassword()))
			{
				return true; //exista utilizatorul cu datele de logare acc
			}
			
		}
		return false;
	}
}
