package dropwizard.mobject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Singleton;

import io.dropwizard.lifecycle.Managed;

@Singleton
public class SampleBootstrap  implements Managed{

	
	private final Logger logger=LoggerFactory.getLogger(SampleBootstrap.class);
	@Override
	public void start() throws Exception {
	logger.info("Starting some resource..");	
		
	}

	@Override
	public void stop() throws Exception {

		logger.info("Shutting down some resource..");	
		
	}

}
