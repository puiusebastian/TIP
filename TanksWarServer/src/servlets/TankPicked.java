package servlets;

import java.net.URI;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

public class TankPicked {
	public int tank_id;
	public int speed ;
	public int health;
	public int damage;
	public int tank_range;
	public String tank_name;
	
	public TankPicked(String username)
	{
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		
		WebTarget target = client.target(getBaseURI());
		
		JsonArray tanks,users_tanks;
		Integer id=null;
		tanks=target.path("api").path("ssw").path("getTanks").request().accept(MediaType.APPLICATION_JSON).get(JsonArray.class);
		users_tanks=target.path("api/ssw/gettankpicked").request().accept(MediaType.APPLICATION_JSON).get(JsonArray.class);
		JsonObject user_tank,tank=null;
		for(int i=0;i<users_tanks.size();i++)
		{
			user_tank=users_tanks.getJsonObject(i);
			if(username.equals((String)user_tank.getString("userTP"))) {
				id=user_tank.getInt("tankTP");
				break;
			}
		}

		for(int i=0;i<tanks.size();i++) {
			tank=tanks.getJsonObject(i);
			if(id==tank.getInt("id")) {
				break;                        //tank will be the JsonObject needed
			}
		}
		
		if(tank!=null) {
			this.tank_id=tank.getInt("id");
			this.speed=tank.getInt("speed");
			this.health=tank.getInt("health");;
			this.damage=tank.getInt("damage");;
			this.tank_range=tank.getInt("range");;
			this.tank_name=tank.getString("name");;
		}
	}
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8888/").build();
	}
}
