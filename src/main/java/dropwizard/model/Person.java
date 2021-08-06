package dropwizard.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {

	private String firstName;
	private String lastName;
	public Person() {}
	public Person(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	/**
	 * @return the firstName
	 */
	@JsonProperty
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @return the lastName
	 */

	@JsonProperty
	public String getLastName() {
		return lastName;
	}
	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	

	
	
}
