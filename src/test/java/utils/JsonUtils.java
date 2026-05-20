package utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonUtils {
	
	
	private static final ObjectMapper mapper = new ObjectMapper()
												.setSerializationInclusion(JsonInclude.Include.ALWAYS);
	
	//Convert POJO to JSON String
	public static String convertPojoToJson(Object object) {
		
		try {
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
		} catch (Exception e) {
			
			throw new RuntimeException("Failed to convert POJO to JSON", e);
		}
	
	}
	
	//Convert JSON String to POJO
	public static <T> T convertJsonToPojo(String json,Class<T> clazz) {
		
		
				try {
					return mapper.readValue(json, clazz);
				} catch (Exception e) {
					
					throw new RuntimeException("Failed to convert JSON to POJO",e);
				}
			
	}

	//Convert Response to POJO
	public static <T> T convertResponseToPojo(Response response, Class<T> clazz) {
		
		try {
			return mapper.readValue(response.asString(), clazz);
		} catch (Exception e) {
			
			throw new RuntimeException("Failed to convert Response to pojo",e);
		} 
		
		
	}
	
	
	//Read JSON file
	public static <T> T readJSONFile(String filePath,Class<T> clazz) {
		
		try {
			return mapper.readValue(new File(filePath), clazz);		
		} catch (IOException e) {
			
			throw new RuntimeException("Failed to read JSON File", e);
		}
			
	}
		
	
	// Read JSON File as String
	public static String readJsonAsString(String filePath) {
		
		
		try {
			return mapper.writeValueAsString(
					mapper.readTree(new File(filePath)));		
		} catch (Exception e) {
			throw new RuntimeException("Failed to read JSON File", e);			
		}
		
	}
	
	
	
	//Pretty Print JSON
	public static String prettyPrintJson(Object object) {
		
		try {
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
		} catch (Exception e) {			
			throw new RuntimeException("Failed to read JSON File", e);
		}
		
	}
	
	
	//get String value from response
	public static String getString(Response response, String jsonPath) {
		
		return response.jsonPath().getString(jsonPath);
	}
	
	//get int value from response
	public static int getInt(Response response, String jsonPath) {
		
		return response.jsonPath().getInt(jsonPath);
	}
	
	
	//get long value from response
	public static long getLong(Response response, String jsonPath) {
		
		return response.jsonPath().getLong(jsonPath);
	}

	
	//get boolean value from response
	public static boolean getBoolean(Response response, String jsonPath) {
		return response.jsonPath().getBoolean(jsonPath);
	}

	
	//Get List from the response
	public static <T> List<T> getList(Response response, String jsonPath){
		
		return response.jsonPath().getList(jsonPath);
		
	}
	
	
	//get Map from response
	public static Map<String,Object> getMap(Response response, String jsonPath){
		
		return response.jsonPath().getMap(jsonPath);
	}
	
	
	//to check if the jsonPath exists
	public static boolean hasJsonKey(Response response, String jsonPath) {
		
		JsonPath jp = response.jsonPath();
		return jp.get(jsonPath) != null;
	}
	
	
	
	
}


















