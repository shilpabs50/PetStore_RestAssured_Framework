package validators;
import io.restassured.response.Response;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class PetValidator {
	
	private static final Logger logger = LogManager.getLogger(PetValidator.class);
	
	
	public static void validatePetPostSchema(Response response) {
		
		logger.info("Validating pet schema");
		
		response
		.then()
		.assertThat()
		.body(matchesJsonSchemaInClasspath("schemas\\petSchema.json"));
		
		
		
	}
	
	

}
