package servlets;

import java.io.StringReader;
import java.net.URI;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

public class UpdateStats {
	public static Boolean UpdateMoney(String username,Integer money_to_add) {
		
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);		
		WebTarget target = client.target(getBaseURI());
		
		Integer id=null;
		JsonArray users;
		JsonObject user;
		users=target.path("api").path("ssw").path("getUsers").request().accept(MediaType.APPLICATION_JSON).get(JsonArray.class);
		
		for(Integer i=0;i<users.size();i++) {
			user=users.getJsonObject(i);
			if(user.getString("username").equals(username)) {
				System.out.println(user);
				id=user.getInt("id");
			}
		}
		if(id!=null) {
			if(target.path("api/ssw/updatemoney/"+id.toString()).request(MediaType.TEXT_PLAIN).put(Entity.entity(money_to_add, MediaType.TEXT_PLAIN),Boolean.class)){
				System.out.println("Money has been updated!");
				return true;
			}
			else {
				System.out.println("Money hasn't been updated!");
				return false;
			}
		}else {
			System.out.println("Error!!! Unknown username!");
		}
		return false;
	}
	
	public static Boolean UpdateGamesNumber(String username,Integer game_played,Integer game_won) {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);		
		WebTarget target = client.target(getBaseURI());
		
		Integer id=null;
		JsonArray users;
		JsonObject user;
		users=target.path("api").path("ssw").path("getUsers").request().accept(MediaType.APPLICATION_JSON).get(JsonArray.class);
		
		for(Integer i=0;i<users.size();i++) {
			user=users.getJsonObject(i);
			if(user.getString("username").equals(username)) {
				System.out.println(user);
				id=user.getInt("id");
			}
		}
		
		if(id!=null) {
			System.out.println(id);
			JsonObject gamesNumber=Json.createReader(new StringReader("{\"games_played\":"+game_played+",\"games_won\":"+game_won+"}")).readObject();
			System.out.println(gamesNumber);
			if(target.path("api/ssw/updateplayedwongames/"+id).request(MediaType.TEXT_PLAIN).put(Entity.entity(gamesNumber, MediaType.APPLICATION_JSON),Boolean.class)){
				System.out.println("Games number have been updated!");
				return true;
			}
			else {
				System.out.println("Games number haven't been updated!");
				return false;
			}
		}else {
			System.out.println("Error!!! Unknown username!");
		}
	
		return false;
	}
	
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8888/").build();
	}
}
