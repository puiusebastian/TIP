package webservices;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/ssw/delete")
public class SSWdeleteUser {
	//delete user service
		@DELETE
		@Path("{id}")
		@Produces(MediaType.TEXT_PLAIN)
		public boolean deleteUser(@PathParam("id") String id) {
			return DBManager.getInstance().deleteU(Integer.parseInt(id));
		}
}
