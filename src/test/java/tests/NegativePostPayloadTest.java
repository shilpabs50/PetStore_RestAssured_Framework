package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import dataProviders.PetNegativeDataProvider;
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

public class NegativePostPayloadTest extends BaseTest{
	
	 @Epic("Pet Module")
	 @Feature("Negative Pet Workflow")
	 @Severity(SeverityLevel.CRITICAL)	
	@Test(dataProvider = "negativePetData",
	      dataProviderClass = PetNegativeDataProvider.class,
	      groups= {"smoke","pet"})
	public void validateNegativePetCreation(String scenario,Pet pet,int ExpectedStatusCode) {
		
		 logger.info("Excuting scenario: {}",scenario);
		 
		 PetService petService = new PetService(requestSpec,ConfigManager.getInstance().get("base.url"));
		 
		 String requestPayload = JsonUtils.convertPojoToJson(pet);
		 
		 //LOG REQUEST PAYLOAD
		 //logger.info("Request payload: \n{}",JsonUtils.convertPojoToJson(pet));
		 
		 logger.info("Request payload: \n{}",requestPayload);
		 
		 //API CALL
		 Response response = petService.createPet(requestPayload);
		 
		 logger.info("Response: \n{}",response.asPrettyString());
		 
		 
		 //VALIDATE STATUS CODE
		 ResponseValidator.validateResponseCode(response, ExpectedStatusCode);
		 
		 logger.info("Scenario completed: {}",scenario);
		 
		
		
	}

}
