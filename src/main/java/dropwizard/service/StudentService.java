package dropwizard.service;

import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dropwizard.cache.CacheConfigManager;
import dropwizard.model.Student;

public class StudentService {

	private static final Logger logger=LoggerFactory.getLogger(StudentService.class);

	public StudentService() {
		
	}
	
	public Student getFromDatabase(String key) {
		Student s1=new Student("anuj", "1", "Math");
		Student s2=new Student("anuj", "2", "DSA");
		Student s3=new Student("anuj", "3", "AI");
		Map<String,Student> data=new HashMap<>();
		data.put("s1", s1);
		data.put("s2", s2);
		
		data.put("s3", s3);
		
		 logger.info("Database called for: {}", key);
	        return data.get(key);
	}

}
