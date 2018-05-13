package webservices;

import java.util.ArrayList;
import java.util.List;

import javax.json.JsonObject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



@Path("/checkuser")
public class CheckUserWS {
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public Boolean checkUser( JsonObject jsonObject)
	{
		String user=jsonObject.getString("username");
		String pass=jsonObject.getString("password");
		
		List<Users> players=new ArrayList<Users>();
		players=DBManager.getInstance().getUsersList();
		for(Users player:players)
		{
			if(user.equals(player.getUsername()) && pass.equals(player.getPassword()))
			{
				return true; //exista utilizatorul cu datele de logare acc
			}
		}
		return false;
	}
}
