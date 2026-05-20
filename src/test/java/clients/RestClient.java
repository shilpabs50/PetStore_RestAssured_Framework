package clients;

import static io.restassured.RestAssured.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import auth.AuthFilter;
import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {
	
	
	private RequestSpecification requestSpec;
	private String baseUrl;
	private static final Logger logger = LogManager.getLogger(RestClient.class);
	
	
	public RestClient(RequestSpecification requestSpec,String baseUrl) {
		
		
		this.requestSpec = requestSpec;	
		this.baseUrl = baseUrl;
		logger.info("RequestSpec initialized successfully");
		
		
	}
	
	
	
	public Response get(String endpoint) {
			
		                     
		Allure.addAttachment("GET Request: ",
				"Method: GET\n"+
                "URL: "+ baseUrl + endpoint +"\n");                    
		
		Response response =		
				RestAssured
				.given(requestSpec)				
				.when()
				.get(endpoint);		


		Allure.addAttachment("GET Response: ", 
				"GET Status Code: " + String.valueOf(response.getStatusCode()) +
				"\n\nGET Response Body:\n" + response.asPrettyString() );
		
		return response;
				
	}
	
	
	public Response post(String endpoint,Object payload) {
		
		logger.info("Sending POST request to {}",endpoint);
	
		
		Allure.addAttachment("POST Request: ", 
				 "Method: POST\n" +
					        "URL: " + baseUrl + endpoint + "\n\n" +
					        "Payload:\n" +
					        new Gson().toJson(payload));	
		
		Response response =
		 RestAssured
				.given(requestSpec)				
				.body(payload)
				.when()
				.post(endpoint);
		
		logger.info("POST response status code: {}",response.getStatusCode());
	   
	   Allure.addAttachment("POST Response: ", 
			   "POST Status Code: "+String.valueOf(response.getStatusCode()) +
			   "\n\nPOST Create Pet Response: \n" + response.asPrettyString());
		
	   return response;
	}
	
	
	public Response put(String endpoint,Object payload) {
				
		
		Allure.addAttachment("PUT Request: ",				
		        "Method: PUT\n" +
		        "URL: " + baseUrl + endpoint + "\n\n" +
		        "Payload:\n" +
		        new Gson().toJson(payload));
		
		
		Response response =
		 RestAssured
				.given(requestSpec)				
				.body(payload)
				.when()
				.put(endpoint);	

		
		 Allure.addAttachment("PUT Response: ", 
				   "PUT Status Code: "+String.valueOf(response.getStatusCode()) +
				   "\n\nPUT Update Pet Response: \n" + response.asPrettyString());
		
		return response;
	}
	
	
	
	public Response delete(String endpoint) {
		
		Allure.addAttachment("Delete Request: ", 
							"Method: DELETE\n"+
							"URL: "+ baseUrl + endpoint +"\n");  
                             
		
		Response response = 
		        RestAssured
				.given(requestSpec)							
				.when()
				.delete(endpoint);		

		Allure.addAttachment("DELETE Response: ", 
				   "DELETE Status Code: "+String.valueOf(response.getStatusCode()) +
				   "\n\nDELETE Pet Response: \n" + response.asPrettyString());
		
		return response;
				
	}
	
	

}
