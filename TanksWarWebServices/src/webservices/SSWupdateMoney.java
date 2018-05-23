package webservices;

import javax.json.JsonObject;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/ssw/updatemoney")
public class SSWupdateMoney {
	//update games played and won 
	@PUT
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean updateGold(@PathParam("id") String id,int money)
	{
		return DBManager.getInstance().updateMoney(Integer.parseInt(id), money);
	}
}
