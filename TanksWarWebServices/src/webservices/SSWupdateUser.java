package webservices;


import javax.json.JsonObject;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/ssw/update")
public class SSWupdateUser {
	//update user service
	@PUT
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean updateUser(@PathParam("id") String id,JsonObject info)
	{
		return DBManager.getInstance().updateU(Integer.parseInt(id), info);
	}
}
