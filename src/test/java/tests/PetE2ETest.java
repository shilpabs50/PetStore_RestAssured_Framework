package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import builders.PetBuilder;
import cache.CacheManager;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import models.Pet;
import services.PetService;
import utils.ConfigManager;
import utils.ResponseValidator;
import validators.PetValidator;

public class PetE2ETest extends BaseTest {
    
	@Epic("Pet Module")
	@Feature("Create Pet workflow")
	@Severity(SeverityLevel.CRITICAL)
    @Test(groups= {"smoke","pet"})
    public void petWorkflowTest() {
    	
    	logger.info("Starting Pet Workflow Test");
    	
    	PetService petService = new PetService(requestSpec,ConfigManager.getInstance().get("base.url"));

        // CREATE
    	logger.info("Building pet payload");
    	Pet pet = new PetBuilder()
                .withDefaultPetData()
                .build();

        Response createResponse = petService.createPet(pet);
        
        logger.info("Validating POST response schema");
        PetValidator.validatePetPostSchema(createResponse);        
        logger.info("-------Post request validation passed-------");
        

        //createResponse.then().statusCode(200);
        ResponseValidator.validateResponseCode(createResponse, 200);
        
        
        long petId = createResponse.jsonPath().getLong("id");
        
		/*
		 * CacheManager.put("PET_ID", petId); long PET_ID = CacheManager.get("PET_ID");
		 */

        // GET
        Response getResponse = petService.getPetById(petId);
        

      //  getResponse.then().statusCode(200);
        
        ResponseValidator.validateResponseCode(getResponse, 200);

        // UPDATE
        pet.setStatus("sold");

        Response updateResponse = petService.updatePet(pet);

        //updateResponse.then().statusCode(200);
        
        ResponseValidator.validateResponseCode(updateResponse, 200);

        // petId
        Response deleteResponse = petService.deletePet(petId);

        //deleteResponse.then().statusCode(200);
        
        ResponseValidator.validateResponseCode(deleteResponse, 200);
        
        
    }
}
