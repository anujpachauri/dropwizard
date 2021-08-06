package dropwizard.redis;

import org.redisson.api.RedissonClient;

import io.dropwizard.lifecycle.Managed;

public class RedissonManaged implements Managed {

	
	private RedissonClient redissonClient;
	
	public RedissonManaged(RedissonClient redissonClient) {
		super();
		this.redissonClient = redissonClient;
	}

	@Override
	public void start() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() throws Exception {
		this.redissonClient.shutdown();
		
	}

}
