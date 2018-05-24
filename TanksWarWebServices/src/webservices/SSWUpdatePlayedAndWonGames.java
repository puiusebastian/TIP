package webservices;

import javax.json.JsonObject;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/ssw/updateplayedwongames")
public class SSWUpdatePlayedAndWonGames {
	//update money with a certain value
	@PUT
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean updateRanks(@PathParam("id") String id,JsonObject info)
	{
		return DBManager.getInstance().updatePlayedWonGames(Integer.parseInt(id), info);
	}
}
