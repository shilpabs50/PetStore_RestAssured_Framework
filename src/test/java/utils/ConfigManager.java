package utils;

import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigManager {
	
	
	private static final Logger logger = LogManager.getLogger(ConfigManager.class);
	
	//single shared object, designed in singleton pattern
	private static ConfigManager instance;	
	
	private ResourceBundle bundle ;
	
	
	private ConfigManager(){
		
		String env = System.getProperty("env");
		
		
		//set default env
		if(env == null || env.isEmpty()) {
			env = "qa";
		}
		
		
		bundle = ResourceBundle.getBundle("config.config_"+ env);
		logger.info("Loaded environment is: {}", env);
		
	}
	
	
	
	//Global access point
	public static ConfigManager getInstance() {
		
		if(instance == null) {
			logger.info("Creating ConfigManager instance");
			instance = new ConfigManager();
		}
		
		return instance;
		
	}
	
	
	public String get(String key) {
		
		logger.debug("Fetching config value for key: {}", key);
		return bundle.getString(key);
	}
	
	

}
