package webservices;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/checkuser_tank")

public class CheckUser_TankWS {
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public Boolean checkTank( String acc)
	{
		JsonObject jsonObject=Json.createReader(new StringReader(acc)).readObject();
		int Uid=jsonObject.getInt("user_id");
		int Tid=jsonObject.getInt("tank_id");
		
		List<User_Tanks>user_tanks=new ArrayList<User_Tanks>();
		user_tanks=DBManager.getInstance().getUser_TanksList();
		for(User_Tanks user_tank:user_tanks)
		{
			if(Uid.equals(user_tank.getU_Id()) && Tid.equals(user_tank.getT_Id()))
			{
				return true; 
			}
			
		}
		return false;
	}
	

}
