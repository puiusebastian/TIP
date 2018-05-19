package test;

import java.io.StringReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import webservices.Users; //to use class Users

public class ClientTest {
	public static void main(String[] args) {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		
		

		WebTarget target = client.target(getBaseURI());
		
		String accArray="{\"username\":\"ilie\",\"password\":\"yoki\"}"; // {"username":"wanted username","password":"wanted password"}  
		JsonObject jsonObject=Json.createReader(new StringReader(accArray)).readObject();
		//check if user "tank" with password "tank" exists
		System.out.println(target.path("api").path("checkuser").request(MediaType.TEXT_PLAIN)
				.post(Entity.entity(jsonObject,MediaType.APPLICATION_JSON),Boolean.class));
		
		//display users
		System.out.println(target.path("api").path("ssw").path("getTanks").request().accept(MediaType.APPLICATION_JSON).get(String.class));
		System.out.println(target.path("api/ssw/getTanks").request().accept(MediaType.APPLICATION_JSON).get(String.class));
		//save list
		//List<Users> users=new ArrayList<Users>();
		JsonArray tanks;
		tanks=target.path("api").path("ssw").path("getTanks").request().accept(MediaType.APPLICATION_JSON).get(JsonArray.class);
		System.out.println(tanks);//display all jsonArray
		for(int i=0;i<tanks.size();i++)
		{
			JsonObject tank=tanks.getJsonObject(i);
			System.out.println(tank.getString("name")+" has "+tank.getInt("health")+" HP;"); //display by JsonObject
		}
		//fara nebuneli
		WebTarget secTarget=client.target("http://localhost:8888/api/ssw/showTanks");
		System.out.println(secTarget.request().accept(MediaType.TEXT_PLAIN).get(String.class));
		
		
		
	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8888/").build();
	}
}