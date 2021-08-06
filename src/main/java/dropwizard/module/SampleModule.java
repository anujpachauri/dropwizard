package dropwizard.module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import dropwizard.filter.CustomHeaderFilter;
import dropwizard.mobject.SampleBootstrap;
import dropwizard.resource.SampleResource;

public class SampleModule extends AbstractModule {

	 protected void configure() {
		 
		 bind(SampleResource.class).in(Singleton.class);
		 bind(SampleBootstrap.class).in(Singleton.class);
		 bind(CustomHeaderFilter.class);
		 
		 
	 }
}
