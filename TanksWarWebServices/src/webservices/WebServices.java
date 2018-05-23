package webservices;
import java.util.List;

import javax.json.JsonObject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
@Path("/ssw")
public class WebServices {
	@GET
	@Path("/getUsers")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Users> getUsers(){
		System.out.println("Am ajuns la serviciu!");
		return DBManager.getInstance().getUsersList();
	}
	
	@GET
	@Path("/getTanks")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Tanks> getTanks(){
		return DBManager.getInstance().getTanksList();
	}
	
	@GET
	@Path("/showTanks")
	@Produces(MediaType.TEXT_PLAIN)
	public String showTanks(){
		return ""+DBManager.getInstance().getTanksList();
	}
	
	@GET
	@Path("/getUT")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User_tank> getUT(){
		return DBManager.getInstance().getUTList();
	}
	
	//insert new user
	@POST
	@Path("/insertuser")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean insertUser(JsonObject info)
	{
		return DBManager.getInstance().insertU(info);
	}
	
	//get list of table tank_picked
	@GET
	@Path("/gettankpicked")
	@Produces(MediaType.APPLICATION_JSON)
	public List<TankPicked> getTP(){
		return DBManager.getInstance().getTPList();
	}
	
	//insert in tank_picked
	@POST
	@Path("/inserttankpicked")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean insertTankPicked(JsonObject info)
	{
		return DBManager.getInstance().insertTP(info);
	}
	
	//insert in user_tank
	@POST
	@Path("/insertusertank")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean insertUserTank(JsonObject info)
	{
		return DBManager.getInstance().insertUT(info);
	}
	

}
