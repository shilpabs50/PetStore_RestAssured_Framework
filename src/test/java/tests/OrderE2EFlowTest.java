package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import builders.OrderBuilder;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import models.Order;
import services.StoreService;
import utils.ConfigManager;
import utils.ResponseValidator;
import validators.SchemaValidator;


public class OrderE2EFlowTest extends BaseTest {

	
	@Epic("Store Module")
	@Feature("End to End Order workflow")
	@Severity(SeverityLevel.CRITICAL)
	@Test(groups = {"smoke","store"})
	public void OrderWorkflowTest() {
		
		logger.info("Staring Order workflow test");
		//StoreService storeService = new StoreService(requestSpec,ConfigManager.getInstance().get("base.url"));
		
		
		//GET INVENTORY
		logger.info("Getting inventory list");
		Response inventoryResponse = storeService.getInventories();
		//SchemaValidator.validateGetInventorySchema(inventoryResponse);
		//ResponseValidator.validateResponseCode(inventoryResponse, 200);
		
		
		
		
		//CREATE
		logger.info("Building order payload");
		Order order = new OrderBuilder()
					.withDefaultStoreData()
					.build();
		
		
		Response createOrderResponse = storeService.createOrder(order);
		
		long orderId = createOrderResponse.jsonPath().getLong("id");
		
		//SchemaValidator.validatePostOrderSchema(createOrderResponse);
		//ResponseValidator.validateResponseCode(createOrderResponse, 200);
		
		
		//GET ORDER
		logger.info("Fetching order created previously");		
		
		Response getOrderResponse = storeService.getOrderById(orderId);			
		
		//ResponseValidator.validateResponseCode(getOrderResponse, 200);
		
		
		//DELETE ORDER
		logger.info("Delete order created previously");		
				
		Response deleteOrderResponse = storeService.deleteOrder(orderId);					
		
		//SchemaValidator.validateDeleteOrderSchema(deleteOrderResponse);
		//ResponseValidator.validateResponseCode(deleteOrderResponse, 200);
		
	
	}
	
	
	
	
	
	
	
}
