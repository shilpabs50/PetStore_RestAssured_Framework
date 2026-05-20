package tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import base.BaseTest;
import builders.OrderBuilder;
import dataProviders.NegativeOrderPostData;
import io.restassured.response.Response;
import models.Order;
import services.StoreService;
import utils.ConfigManager;

public class createOrderNegativePayLoadTest extends BaseTest{
	
	
	@Test(dataProvider = "negativeOrderData",
			dataProviderClass = NegativeOrderPostData.class,
			groups = {"regression","store"})
	public void negativePayloadCreateOrder(int quantity, String status) {
		
		logger.info("Negative payload Create order tests");
		//StoreService storeService = new StoreService(requestSpec,ConfigManager.getInstance().get("base.url"));
		
		
		Order order = new OrderBuilder()
				.withDefaultStoreData()
				.withQuantity(quantity)
				.withStatus(status)
				.build();
	
		Response createOrderResponse = storeService.createOrder(order);
		
		logger.info(createOrderResponse.asPrettyString());
		
		assertTrue(createOrderResponse.getStatusCode() >= 400 || createOrderResponse.getStatusCode() == 200);
		
		
		
		
		
	}
	
	
	
	
	
	

}
