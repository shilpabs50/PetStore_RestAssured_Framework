package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import dataProviders.PetWorkflowDataProvider;
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

public class PetWorkflowDDTTest extends BaseTest{
	
	 @Epic("Pet Module")
	 @Feature("E2E Pet Workflow with multiple dataset")
	 @Severity(SeverityLevel.CRITICAL)
	@Test(dataProvider="positiveWorkflowData",
			dataProviderClass=PetWorkflowDataProvider.class,
			groups= {"smoke","pet"})
	public void validatePetWorkflow(String scenario, Pet pet, String updatedStatus) {
		
		
		logger.info("Executing scenario: {}",scenario);
		
		
		PetService petService = new PetService(requestSpec,ConfigManager.getInstance().get("base.url"));
		
		Response createResponse = petService.createPet(pet);
		
		ResponseValidator.validateResponseCode(createResponse, 200);
		
		long petId = JsonUtils.getLong(createResponse, "id");
		
		logger.info("Pet creeated: {}",petId);
		
		
		//GET
		Response getResponse = petService.getPetById(petId);
		ResponseValidator.validateResponseCode(getResponse, 200);
		
		
		//CHANGE STATUS VALUE IN THE PET OBJECT FOR THE UPDATE CALL
		pet.setStatus(updatedStatus);
		
		logger.info("Updated Pet payload before PUT call: {}",JsonUtils.convertPojoToJson(pet));
		
		//UPDATE
		Response updateResponse = petService.updatePet(pet);
		ResponseValidator.validateResponseCode(updateResponse, 200);
		
		
		//VALIDATE UPDATED STATUS
		
		Pet updatedPet = JsonUtils.convertResponseToPojo(updateResponse, Pet.class);
		
		Assert.assertEquals(updatedPet.getStatus(), updatedStatus);
		
		
		//DELETE 
		Response deleteResponse = petService.deletePet(petId);
		ResponseValidator.validateResponseCode(deleteResponse, 200);
		
		
		logger.info("Workflow completed: {}",scenario);
		
		
	}
	
	
	
	
	
}
