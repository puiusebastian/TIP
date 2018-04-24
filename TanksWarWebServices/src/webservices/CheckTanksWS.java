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

@Path("/checktank")

public class CheckTanksWS {
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public Boolean checkTank( String acc)
	{
		JsonObject jsonObject=Json.createReader(new StringReader(acc)).readObject();
		int spe=jsonObject.getInt("speed");
		int heal =jsonObject.getInt("health");
		int dam =jsonObject.getInt("damage");
		int rang =jsonObject.getInt("tank_range");
		String name=jsonObject.getString("tank_name");
		
		List<Tanks> tanks=new ArrayList<Tanks>();
		tanks=DBManager.getInstance().getTanksList();
		for(Tanks tank:tanks)
		{
			if(spe.equals(tank.getSpeed()) && heal.equals(tank.getHealth()) && dam.equals(tank.getDamage()) && rang.equals(tank.getRange()) && name.equals(tank.getName()))
			{
				return true; 
			}
			
		}
		return false;
	}

}
