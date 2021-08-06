package dropwizard.model;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Representation<T> {

	private long code;
	@Length(max = 3)
	private T data;
	public Representation(long code, T data) {
		super();
		this.code = code;
		this.data = data;
	}
	public Representation() {
		super();
	}
	/**
	 * @return the code
	 */
	@JsonProperty
	public long getCode() {
		return code;
	}
	/**
	 * @return the data
	 */
	@JsonProperty
	public T getData() {
		return data;
	}
	
	
}
