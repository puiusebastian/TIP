package webservices;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
@Path("/ssw")
public class WebServices {
	@GET
	@Path("/getUsers")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Users> getUsers(){
		return DBManager.getInstance().getUsersList();
	}
}
