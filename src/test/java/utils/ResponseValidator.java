package utils;

import io.qameta.allure.Allure;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.lessThan;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ResponseValidator {
	
   private static final Logger logger = LogManager.getLogger(ResponseValidator.class);
	
	public static void validateResponseCode(Response response,int expectedstatuscode) {
		
		//Allure.addAttachment("Expected Status code", null);
		
		
		response.then().statusCode(expectedstatuscode);
		logger.info("-------------Status code assertion passed-----------------");
		
		
	}
	
	public static void validateResponseTime(Response response, long expectedTime) {
		
		response.then().time(lessThan(expectedTime));
		logger.info("Response time of the request is below the expected time: Pass");
		
		
	}
	
	
	

}
