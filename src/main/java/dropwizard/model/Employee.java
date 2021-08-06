package dropwizard.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {

	private String name;
	private String email;
	
	public Employee() {
		
	}
	
	

	public Employee(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}



	/**
	 * @return the name
	 */
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	/**
	 * @return the email
	 */
	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	/**
	 * @param name the name to set
	 */
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param email the email to set
	 */
	@JsonProperty("email")
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
