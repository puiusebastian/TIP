package webservices;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/ssw/deletetankpicked")
public class SSWdeleteTankPicked {
	//delete user service
	@DELETE
	@Path("{user}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean deleteTankPicked(@PathParam("user") String user) {
		return DBManager.getInstance().deleteTP(user);
	}
}
