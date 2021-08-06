package dropwizard.resource;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.redisson.api.RMapCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;

import dropwizard.cache.CacheConfigManager;
import dropwizard.model.Student;
import dropwizard.service.StudentService;

@Path(value = "/cache")
public class StudentResource {

	public StudentResource(StudentService service, RMapCache<String, Student> cache) {
		super();
		this.service = service;
		this.cache = cache;
	}

	private static final Logger logger=LoggerFactory.getLogger(StudentResource.class);

	private StudentService service;
	private RMapCache<String, Student> cache;
	
	
	@Timed
	//@Path("/student/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response processCache() {
		
		logger.info("In StudentResource.cache.. Get Student Data");
		
		for(int i=1;i<4;i++) {
			getStudentData(i);
		}
		Map<String,String> response=new HashMap<>();
		response.put("Message", "Student Data has been retrieve");
		return Response.ok(response).build();
		
	}

	private void getStudentData(int i) {
		logger.info("********** Call " + String.valueOf(i) + " Started **********");
        logger.info("Call " + String.valueOf(i) + ": {}", CacheConfigManager.getInstance().getStudentDataFromCache("s1",service, cache));
        logger.info("Call " + String.valueOf(i) + ": {}", CacheConfigManager.getInstance().getStudentDataFromCache("s2",service, cache));
        logger.info("Call " + String.valueOf(i) + ": {}", CacheConfigManager.getInstance().getStudentDataFromCache("s3",service, cache));
        logger.info("********** Call " + String.valueOf(i) + " Ended **********");
    }
	
}
