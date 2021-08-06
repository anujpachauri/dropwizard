package dropwizard;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.pradyothadavi.core.RateLimitBundleConfiguration;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HelloWorld extends Configuration {
	
	public HelloWorld() {
		
	}
	
	@Valid
	@NotNull
	private DataSourceFactory database=new DataSourceFactory();
	@JsonProperty
	private String studentCacheKey;
	
	/**
	 * @return the studentCacheKey
	 */
	public String getStudentCacheKey() {
		return studentCacheKey;
	}
	/**
	 * @param studentCacheKey the studentCacheKey to set
	 */
	public void setStudentCacheKey(String studentCacheKey) {
		this.studentCacheKey = studentCacheKey;
	}

	@Valid
//	@NotNull
	@JsonProperty("swagger")
	private SwaggerBundleConfiguration swaggerBundleConfiguration;
	/**
	 * @return the swaggerBundleConfiguration
	 */
	public SwaggerBundleConfiguration getSwaggerBundleConfiguration() {
		return swaggerBundleConfiguration;
	}
	/**
	 * @param swaggerBundleConfiguration the swaggerBundleConfiguration to set
	 */
	public void setSwaggerBundleConfiguration(SwaggerBundleConfiguration swaggerBundleConfiguration) {
		this.swaggerBundleConfiguration = swaggerBundleConfiguration;
	}
	
	public DataSourceFactory getDataSourcFactory() {
		return database;
	}
	 @JsonProperty("database")
	  public void setDataSourceFactory(DataSourceFactory factory) {
	    this.database = factory;
	    //database.getValidationQuery();
	  }
	
	
	
}
