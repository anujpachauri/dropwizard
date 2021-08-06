package dropwizard;


import org.redisson.Redisson;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.api.map.event.EntryExpiredListener;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pradyothadavi.core.RateLimitModule;
import com.github.pradyothadavi.core.RateLimitRegistration;
import com.google.inject.Guice;
import com.google.inject.Injector;

import dropwizard.dao.EmployeeDao;
import dropwizard.health.DatabaseHealthCheck;
import dropwizard.model.Student;
import dropwizard.module.SampleModule;
import dropwizard.redis.RedissonManaged;
import dropwizard.resource.PartsResource;
import dropwizard.resource.StudentResource;
import dropwizard.service.PartService;
import dropwizard.service.StudentService;
import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import ru.vyarus.dropwizard.guice.GuiceBundle;

public class App  extends Application<HelloWorld>{
	private static final Logger logger=LoggerFactory.getLogger(App.class);
	public static void main(String ...s) throws Exception {
		new App().run(new String[] {"server",s[0]});
	}
	
	@Override
	 public void initialize(Bootstrap<HelloWorld> bootstrap) {
	 
		 bootstrap.addBundle(new MigrationsBundle<HelloWorld>() {

			@Override
			public PooledDataSourceFactory getDataSourceFactory(final HelloWorld conf) {
				
				return conf.getDataSourcFactory();
			}
		});
		 bootstrap.addBundle(new SwaggerBundle<HelloWorld>() {

				@Override
				protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(HelloWorld configuration) {
				
					
					return configuration.getSwaggerBundleConfiguration();
				}
				 
			});
		 
		 bootstrap.addBundle(GuiceBundle.builder().modules(new SampleModule()).build());
		 
		
	 
	 }
	
	
	@Override
	public void run(HelloWorld conf, Environment env) throws Exception {
	
		final DBIFactory factory=new DBIFactory();
		
		final DBI jdbi =factory.build(env, conf.getDataSourcFactory(), "h2");
		
		final EmployeeDao employeeDao=jdbi.onDemand(EmployeeDao.class);
		employeeDao.createEmployeeTable();
		
		RedissonClient redissonClient=Redisson.create();
		RedissonManaged redissonManaged=new RedissonManaged(redissonClient);
		env.lifecycle().manage(redissonManaged);
		RMapCache<String, Student> studentMap=redissonClient.getMapCache(conf.getStudentCacheKey());
		
		
		 studentMap.addListener((EntryExpiredListener<String, String>) entryEvent -> {
	            logger.info("Expired key is: {}, Expired value is: {}", entryEvent.getKey(),entryEvent.getValue());
	        });
		
		 
		 StudentService studentService=new StudentService();
		 
		 logger.info("Registring Student Resource..");
		env.jersey().register(new StudentResource(studentService,studentMap));
		env.jersey().register(new PartsResource(new PartService()));
		env.jersey().register(new dropwizard.resource.emp.EmployeeResource(employeeDao));
		
		
		
		
		
		
		
		
	//	Injector injector=Guice.createInjector(new RateLimitModule(conf.getRateLimiterConfiguration()));
	//	env.jersey().getResourceConfig().getResources().add(injector.getInstance(RateLimitRegistration.class));
		
		//env.healthChecks().register("default", new DefaultHealthCheck());
		env.healthChecks().register("health", new DatabaseHealthCheck(jdbi, conf.getDataSourcFactory().getValidationQuery()));
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		env.jersey().
//		register(new AuthDynamicFeature(
//				new BasicCredentialAuthFilter.Builder<User>()
//				.setAuthenticator(new SecurityAuthenticator())
//				.setAuthorizer(new SecurityAuthorizer())
//				.setRealm("SUPER SECRET STUFF")
//				.buildAuthFilter()));
//		env.servlets().addFilter("ApiOriginFilter", new ApiOriginFilter())
//		.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
	
		
		
		
	}

}
