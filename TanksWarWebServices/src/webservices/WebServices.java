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
	
	@GET
	@Path("/getTanks")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Tanks> getTanks(){
		return DBManager.getInstance().getTanksList();
	}
	
	@GET
	@Path("/getUT")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User_tank> getUT(){
		return DBManager.getInstance().getUTList();
	}
}
