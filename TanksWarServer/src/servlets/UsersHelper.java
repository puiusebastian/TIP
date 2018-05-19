package servlets;

import java.net.URI;

import javax.json.JsonArray;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

public class UsersHelper {
	public static JsonArray GetUsers() {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		
		WebTarget target = client.target(getBaseURI());
		
		JsonArray users;
		users=target.path("api").path("ssw").path("getUsers").request().accept(MediaType.APPLICATION_JSON).get(JsonArray.class);
		
		return users;

	}
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8888/").build();
	}
}
