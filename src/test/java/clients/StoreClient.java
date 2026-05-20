package clients;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StoreClient {
	
	private RequestSpecification requestSpec;
	private String baseUrl;
	private static final Logger logger = LogManager.getLogger(StoreClient.class);
	
	
	public StoreClient(RequestSpecification requestSpec,String baseUrl) {
		
		this.requestSpec = requestSpec;
		this.baseUrl = baseUrl;
		logger.info("Store client RequestSpec initialized successfully");
		
	}
	
	
	public Response get(String endpoint) {
		
		logger.info("Sending GET request to: {}",endpoint);
		
		Allure.addAttachment("GET Request: ", 
				"Method: GET\n"+
		        "URL: "+ baseUrl + endpoint+"\n");
		
		
		  Response response = RestAssured 
				  .given()
				  .spec(requestSpec)
				  .when() 
				  .get(endpoint);
		 
		
	
		
		Allure.addAttachment("GET Response: ", 
				             "GET Status Code: "+ response.getStatusCode() +
				             "\n\n GET Response Body: "+response.asPrettyString());
		
		
		return response;
	}
	
	
	
	public Response post(String endpoint, Object payload) {
		
		logger.info("Sending POST request to: {}",endpoint);
		
		Allure.addAttachment("POST Request: ", 
				"Method: POST\n"+
		        "URL: "+ baseUrl + endpoint+"\n");
		
		
		  Response response = RestAssured 
				  .given()
				  .spec(requestSpec)
				  .body(payload) 
				  .when()
				  .post(endpoint);
		 
			
		Allure.addAttachment("POST Response: ", 
				             "POST Status Code: "+ response.getStatusCode() +
				             "\n\n Create Order POST Response Body: "+response.asPrettyString());
		
		
		return response;
		
	}
	
	public Response delete(String endpoint) {
		
		logger.info("Sending DELETE request to: {}",endpoint);
		
		Allure.addAttachment("DELETE Request: ", 
				"Method: DELETE\n"+
		        "URL: "+ baseUrl + endpoint+"\n");
		
		
		  Response response = RestAssured 
				  			 .given()
				  			 .spec(requestSpec)
				  			 .when()
				  			 .delete(endpoint);	 
	
		
		Allure.addAttachment("DELETE ORDER Response: ", 
				             "DELETE Status Code: "+ response.getStatusCode() +
				             "\n\n DELETE ORDER Response Body: "+response.asPrettyString());
		
		
		return response;
		
		
	}
	
	
	

}
