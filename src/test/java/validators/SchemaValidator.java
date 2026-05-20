package validators;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.response.Response;

public class SchemaValidator {
	
	
	private static final Logger logger = LogManager.getLogger(SchemaValidator.class);
	
	
	
	public static void validatePostOrderSchema(Response response) {
		
		logger.info("Validating post request for Order creation");
		
		response
		.then()
		.assertThat()
		.body(matchesJsonSchemaInClasspath("schemas/orderSchema.json"));
		
		
	}
	
	
	
	public static void validateDeleteOrderSchema(Response response) {
		
		logger.info("Validating delete order schema");
		
		response
		.then()
		.assertThat()
		.body(matchesJsonSchemaInClasspath("schemas/deleteOrderSchema.json"));
		
		
	}
	
	
	
	public static void validateGetInventorySchema(Response response) {
		
		logger.info("Validating get inventory list schema");
		
		response
		.then()
		.assertThat()
		.body(matchesJsonSchemaInClasspath("schemas\\inventorySchema.json"));
		
		
	}

}
