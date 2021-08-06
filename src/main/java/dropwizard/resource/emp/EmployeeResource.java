package dropwizard.resource.emp;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;

import dropwizard.dao.EmployeeDao;
import dropwizard.model.Employee;


@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResource {

	
	private EmployeeDao employeeDao;

	
	public EmployeeResource() {
		super();
	}



	public EmployeeResource( EmployeeDao employeeDao) {
		super();
		this.employeeDao = employeeDao;
	}
	
	
	
	@GET
	public List<Employee> getAllEmployees(){
		return employeeDao.getAll();
	}
	@POST
	public Employee addEmployee(@Valid Employee employee) {
		int employeeId=this.employeeDao.addEmployee(employee);
		return employee;
	}
	@DELETE
	@Path("{id}")
	public int removeEmployee(@PathParam("id") int id) {
		this.employeeDao.removeEmployeeById(id);
		return id;
	}
	
}
