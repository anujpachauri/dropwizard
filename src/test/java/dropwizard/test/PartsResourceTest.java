package dropwizard.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import dropwizard.model.Part;
import dropwizard.model.Representation;
import dropwizard.resource.PartsResource;
import dropwizard.service.PartService;
import io.dropwizard.testing.junit.ResourceTestRule;

public class PartsResourceTest {

	 private static final String SUCCESS = "Success...";
	  private static final String TEST_PART_NAME = "testPartName";
	  private static final String TEST_PART_CODE = "testPartCode";
	  private static final String PARTS_ENDPOINT = "/parts";
	  
	  
	  private static final PartService partService=mock(PartService.class);
	  
	  @ClassRule
	  public static final ResourceTestRule resource=ResourceTestRule.builder().
			  addResource(new PartsResource(partService)).build();
	  
	  
	  private final Part part=new Part(1, TEST_PART_NAME, TEST_PART_CODE);
	  
	  @Before
	  public void setup() {
		  when(partService.getPart(eq(1))).thenReturn(part);
		  List<Part> parts=new ArrayList<>();
		  parts.add(part);
		  when(partService.getParts()).thenReturn(parts);
		  
		  when(partService.createPart(any(Part.class))).thenReturn(part);
		  
		  when(partService.deletePart(eq(1))).thenReturn(SUCCESS);
		 		  
	  }
	  
	  @After
	  public void tearDown() {
		  reset(partService);
	  }
	  
	  
	  @Test
	  public void testGetPart() {
		Part partResponse=resource.target(PARTS_ENDPOINT+"/1").request()
				.get(TestPartRepresentation.class).getData();
		  
		assertThat(partResponse.getId()).isEqualTo(part.getId());

		assertThat(partResponse.getName()).isEqualTo(part.getName());

		assertThat(partResponse.getCode()).isEqualTo(part.getCode());
		
		verify(partService).getPart(1);
		  
	  }
	  @Test
	  public void testGetParts() {
	    List<Part> parts =
	        resource.target(PARTS_ENDPOINT).request().get(TestPartsRepresentation.class).getData();
	    assertThat(parts.size()).isEqualTo(1);
	    assertThat(parts.get(0).getId()).isEqualTo(part.getId());
	    assertThat(parts.get(0).getName()).isEqualTo(part.getName());
	    assertThat(parts.get(0).getCode()).isEqualTo(part.getCode());
	    verify(partService).getParts();
	  }
	  
	  @Test
	  public void testCreatePart() {
	    Part newPart = resource.target(PARTS_ENDPOINT).request()
	        .post(Entity.entity(part, MediaType.APPLICATION_JSON_TYPE), TestPartRepresentation.class)
	        .getData();
	    assertNotNull(newPart);
	    assertThat(newPart.getId()).isEqualTo(part.getId());
	    assertThat(newPart.getName()).isEqualTo(part.getName());
	    assertThat(newPart.getCode()).isEqualTo(part.getCode());
	    verify(partService).createPart(any(Part.class));
	  }
	  
	  @Test
	  public void testDeletePart() {
	    assertThat(resource.target(PARTS_ENDPOINT + "/1").request()
	        .delete(TestDeleteRepresentation.class).getData()).isEqualTo(SUCCESS);
	    verify(partService).deletePart(1);
	  }
	  
	  private static class TestPartRepresentation extends Representation<Part> {

	  }

	  private static class TestPartsRepresentation extends Representation<List<Part>> {

	  }

	  private static class TestDeleteRepresentation extends Representation<String> {

	  }
	  
	  
}
