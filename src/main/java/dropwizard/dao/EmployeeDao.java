package dropwizard.dao;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import dropwizard.model.Employee;

@RegisterMapperFactory(BeanMapperFactory.class)
public interface EmployeeDao {


	 @SqlUpdate("CREATE TABLE IF NOT EXISTS Employee (id INT NOT NULL AUTO_INCREMENT, name VARCHAR(255) NOT NULL,email VARCHAR(255) NOT NULL, primary key(id))")
	    void createEmployeeTable();

	    @SqlUpdate("INSERT INTO Employee (name,email) VALUES(:name,:email)")
	    @GetGeneratedKeys
	    int addEmployee(@BindBean Employee employee);

	    @SqlQuery("SELECT * FROM Employee")
	    List<Employee> getAll();

	    @SqlUpdate("DELETE FROM Employee WHERE id = :id")
	    int removeEmployeeById(@Bind("id") int id);
}
