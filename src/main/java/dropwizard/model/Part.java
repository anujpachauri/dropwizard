package dropwizard.model;

import javax.validation.constraints.NotEmpty;

public class Part {

	
	
	public Part() {
		super();
	}
	private int id;
	@NotEmpty
	private String name;
	@NotEmpty
	private String code;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	public Part(int id, String name, String code) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
	}
	
	
	
}
