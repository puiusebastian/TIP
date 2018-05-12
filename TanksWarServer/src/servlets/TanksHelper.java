package servlets;

import java.net.URI;

import javax.json.JsonArray;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;


public class TanksHelper {
	
	public static String GetTanks() {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		
		WebTarget target = client.target(getBaseURI());
		
		JsonArray tanks;
		tanks=target.path("api").path("ssw").path("getTanks").request().accept(MediaType.APPLICATION_JSON).get(JsonArray.class);
		String result=tanks.toString();
		return result;
	}
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8888/").build();
	}
}
