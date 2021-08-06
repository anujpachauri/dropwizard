package dropwizard.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dropwizard.model.Employee;
import io.dropwizard.jackson.Jackson;
import static io.dropwizard.testing.FixtureHelpers.*;

public class EmployeeTest {

	
	private static final ObjectMapper MAPPER=Jackson.newObjectMapper();
	
	@Test
	public void serializesToJSON() throws JsonMappingException, JsonProcessingException {
		final Employee emp=new Employee("anuj","anuj97190@gmail.com");
		
		final String expected=MAPPER.
				writeValueAsString(MAPPER.
						readValue(fixture("fixtures/Employee.json"), Employee.class));
		
		assertThat(MAPPER.writeValueAsString(emp).equals(expected));
		
	}
}
