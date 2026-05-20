package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import builders.PetBuilder;
import dataProviders.PetNegativeE2EDataProvider;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import models.Pet;
import services.PetService;
import utils.ConfigManager;
import utils.JsonUtils;
import utils.ResponseValidator;

public class PetNegativeWorkflowTest extends BaseTest{
	
	 @Epic("Pet Module")
	 @Feature("E2E Pet Workflow with negative scenarios")
	 @Severity(SeverityLevel.CRITICAL)
	@Test(dataProvider = "negativeE2EWorkflow",
			dataProviderClass = PetNegativeE2EDataProvider.class,
			groups= {"smoke","pet"})	
	public void PetNegativeWorkflow(String scenario) {
		
		logger.info("Scenario: {}", scenario);
		
		PetService petService = new PetService(requestSpec, ConfigManager.getInstance().get("base.url"));
		
		
		
		
		//CREATE PET
		Pet pet = new PetBuilder()
				.withDefaultPetData()				
				.build();
					
		
		Response createResponse = petService.createPet(pet);
		
		long petId = JsonUtils.getLong(createResponse, "id");
		
		
		//DELETE PET
		petService.deletePet(petId);
		
		
		switch(scenario) {
		
		case "Get After Delete":
			
			Response getResponse = petService.getPetById(petId);
			ResponseValidator.validateResponseCode(getResponse, 404);
			break;
			
		case "Delete Twice":
			
			Response deleteAgainResponse = petService.deletePet(petId);
			ResponseValidator.validateResponseCode(deleteAgainResponse, 404);
			logger.info(deleteAgainResponse.asPrettyString());
			break;	
			
		case "Update Deleted Pet":
			
			pet.setStatus("sold");
			
			Response updatedResponse = petService.updatePet(pet);
			//ResponseValidator.validateResponseCode(updatedResponse, 404);
			logger.info(updatedResponse.asPrettyString());
			break;	
		
		
		}
		
		
	}
	
	
	

}
