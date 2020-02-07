package test;

import java.net.URI;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class TestClient {

	public static void main(String[] args) {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(getBaseURI());
		// Get TEXT for application
		System.out.println("GET TEXT");
		System.out.println(service.path("service").path("carto").accept(MediaType.TEXT_PLAIN).get(String.class));
		// Get XML for application
		System.out.println("GET XML");
		System.out.println(service.path("service").path("stationdetails").
				path("paris").path("14024").accept(MediaType.TEXT_XML).get(String.class));
		// Get HTML for application
		System.out.println("GET HTML");
		System.out.println(service.path("service").path("stationdetails").
				path("paris").path("14024").accept(MediaType.TEXT_HTML).get(String.class));
		
	}
	
	private static URI getBaseURI() {
		return UriBuilder.fromUri(
				"http://www.velib.paris/").build();
	}
}