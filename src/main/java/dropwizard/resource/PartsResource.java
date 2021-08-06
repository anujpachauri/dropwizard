package dropwizard.resource;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.jetty.http.HttpStatus;

import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;

import dropwizard.model.Part;
import dropwizard.model.Representation;
import dropwizard.service.PartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.*;

@Path("/parts")
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed("ADMIN")
@Api(value = "/parts", description = "Operations about Part")
public class PartsResource {

	private final PartService partService;
	@Inject
	public PartsResource(PartService partService) {
		this.partService=partService;
	}
	
	@GET
	@ApiOperation(
			value = "Find ALL Parts", 
			notes = "Returns a Parts",
			response = Part	.class)
		@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
				@ApiResponse(code = 404, message = "Part not found") })
	@Metered
	//@Timed
	public Representation<List<Part>> getParts(){
		return new Representation<List<Part>>(HttpStatus.OK_200,
				partService.getParts()); 
	}
	
	@GET
	@Timed
	@Path("{id}")
	@ApiOperation(
			value = "Find Part by ID", 
			notes = "Returns a Part when 0 < ID <= 10. ID > 10 or nonintegers will simulate API error conditions",
			response = Part.class)
		@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
				@ApiResponse(code = 404, message = "Part not found") })
	public Representation<Part> getPart(@PathParam("id") final int id){
		return new Representation<Part>(HttpStatus.OK_200,
				partService.getPart(id)); 
	}
	
	@POST
	@Timed
	@ApiOperation(value = "Add a new Part to the store")
	@ApiResponses(value = { @ApiResponse(code = 405, message = "Invalid input") })
	public Representation<Part> createPart(@NotNull @Valid final Part part){
		return new Representation<Part>(HttpStatus.OK_200,
				partService.createPart(part)); 
	}
	
	@DELETE
	@Timed
	@Path("{id}")
	@ApiOperation(
			value = "Delete Part by ID", 
			notes = "Delete Part",
			response = Part.class)
		@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
				@ApiResponse(code = 404, message = "Part not found") })
	public Representation<String> deletePart(@PathParam("id") final int id){
		return new Representation<String>(HttpStatus.OK_200,
				partService.deletePart(id)); 
	}
	
}
