package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import builders.PetBuilder;
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
import validators.PetValidator;

public class PetPOJOTest extends BaseTest{
	
	
	@Epic("Pet Module")
	@Feature("Create Pet workflow")
	@Severity(SeverityLevel.CRITICAL)
    @Test(groups= {"smoke","pet"})
	public void validatePetusingPOJO() {
		
	logger.info("Building pojo class from json");	
	
	PetService petService = new PetService(requestSpec,ConfigManager.getInstance().get("base.url"));

    //CREATE REQUEST PAYLOAD
	logger.info("Building pet payload");
	Pet pet = new PetBuilder()
            .withDefaultPetData()
            .build();
	
	 // CREATE PET
	 Response postResponse = petService.createPet(pet);
	 
	 //VALIDATE STATUS CODE
	 ResponseValidator.validateResponseCode(postResponse, 200);
	 //VALIDATE RESPONSE TIME
	 ResponseValidator.validateResponseTime(postResponse, 3000);
	 //VALIDATE BUSINESS CONTRACT - SCHEMA
	 PetValidator.validatePetPostSchema(postResponse);	 
	 
	 
	 //DESERIALIZE THE RESPONSE -> POJO	 
	Pet responsePet =  JsonUtils.convertResponseToPojo(postResponse, Pet.class);
	
	logger.info("Response converted to POJO successfully");
	
	//VALIDATE RESPONSE DATA
	Assert.assertEquals(responsePet.getId(),pet.getId());
	
	Assert.assertEquals(responsePet.getName(), pet.getName());
	
	Assert.assertEquals(responsePet.getStatus(), pet.getStatus());
	
	Assert.assertEquals(postResponse.getHeader("Content-Type"), "application/json");
	
	Assert.assertNotNull(responsePet.getId());
	
	Assert.assertTrue(responsePet.getStatus().equals("available") || responsePet.getStatus().equals("sold"));
	
	Assert.assertTrue(JsonUtils.hasJsonKey(postResponse, "id"));
	
	logger.info("POJO response validation successful");
	 
		
		
	}
	
	

}
