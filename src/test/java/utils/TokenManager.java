package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TokenManager {
	
	
	private static String token;
	private static long expiryTime;
	private static final Logger logger = LogManager.getLogger(TokenManager.class);
	
	
	public static String getToken() {
		
		if(token == null || System.currentTimeMillis() >= expiryTime) {
			
			token = generateToken();
	
		}		
		
		return token;
		
	}
	
	
	private static  String generateToken() {
		

	    ConfigManager config =
	            ConfigManager.getInstance();

	    String expiry =
	            config.get("token.expiry");

	    System.out.println("Expiry = " + expiry);

	    int expiresIn =
	            Integer.parseInt(expiry);

	    expiryTime =
	            System.currentTimeMillis()
	            + (expiresIn * 1000L);

	    String authKey =
	            config.get("authKey");

	    System.out.println("AuthKey = " + authKey);

	    return authKey;
		
	}

}
