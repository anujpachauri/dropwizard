package dropwizard.resource;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.inject.Inject;
import com.google.inject.Provider;

@Path("/sample")
@Produces("application/json")
public class SampleResource {

	
	
	@Inject
	private Provider<HttpServletRequest> requestProvider;
	
	
	@Inject
   public SampleResource() {
	
	}
	
	@GET
	@Path("/")
	public Response ask() {
		
		final String ip=requestProvider.get().getRemoteAddr();
		System.out.println("Ip address : " + ip);
		return Response.ok(ip).build();
	}
}
