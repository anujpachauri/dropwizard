package dropwizard.cache;

import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.CollectionUtils;
import org.redisson.api.RMapCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dropwizard.model.Student;
import dropwizard.service.StudentService;

public class CacheConfigManager {

	private static final Logger logger=LoggerFactory.getLogger(CacheConfigManager.class);
	
	private static CacheConfigManager cacheConfigManager=new CacheConfigManager();
	
	public static CacheConfigManager getInstance() {
		return cacheConfigManager;
	}
	
	
	public Student getStudentDataFromCache(String key,
			StudentService studentService,RMapCache<String, Student> rcache) {
		
		try {
			Student student;
			if(CollectionUtils.isEmpty(rcache.keySet())) {
				student=studentService.getFromDatabase(key);
				rcache.put(key, student,30,TimeUnit.MINUTES);
				
			}else {
				if(rcache.containsKey(key)) {
					student=rcache.get(key);
				}else {
					student=studentService.getFromDatabase(key);
					rcache.put(key, student,30,TimeUnit.MINUTES);
				}
				
			}
			
			  logger.info("All Entries in Student map: {}",rcache.readAllEntrySet());
			  return student;
		} catch (Exception e) {
			  logger.error("Error Retrieving Elements from the Student Cache"
	                    + e.getMessage());
	            return null;
		}
		
		
	}
	
	
}
