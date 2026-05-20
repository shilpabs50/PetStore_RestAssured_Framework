package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import base.BaseTest;
import builders.OrderBuilder;
import clients.StoreClient;
import io.restassured.response.Response;
import models.Order;
import services.StoreService;
import utils.ConfigManager;
import utils.JsonUtils;
import utils.ResponseValidator;

public class OrderWorkflowNegativeTest extends BaseTest{
	
	
	@Test(groups= {"regression","store","negative"})
	public void getNonExistentOrderTest() {
		
		logger.info("Get invalid/non existent OrderId");	
		
		//StoreService storeService = new StoreService(requestSpec,ConfigManager.getInstance().get("base.url"));
		
		long invalidOrderId = 999999999L;
		
		Response response = storeService.getOrderById(invalidOrderId);
		
		assertEquals(response.getStatusCode(),404);
		
		assertEquals(JsonUtils.getString(response, "message"),"Order not found");
		
		
	}
	
	
	@Test(groups= {"regression","store","negative"})
	public void deleteNonExistentOrderTest() {
		
		logger.info("Get invalid/non existent OrderId");	
		
		//StoreService storeService = new StoreService(requestSpec,ConfigManager.getInstance().get("base.url"));
		
		long invalidOrderId = 88888888L;
		
		Response response = storeService.deleteOrder(invalidOrderId);
		
		assertEquals(response.getStatusCode(),404);
		
	
		
	}
	
	
		@Test(groups= {"regression","store","negative"})
		public void getDeletedOrderTest() {
			
			logger.info("Get deleted OrderId");		
			//StoreService storeService = new StoreService(requestSpec,ConfigManager.getInstance().get("base.url"));
			
			Order order = new OrderBuilder()
					.withDefaultStoreData()					
					.build();
			
			
			Response createResponse = storeService.createOrder(order);
			long orderId = JsonUtils.getLong(createResponse, "id");
			
			storeService.deleteOrder(orderId);
			
			Response getResponse = storeService.getOrderById(orderId);
			
			assertEquals(getResponse.getStatusCode(),404);			
			
			assertEquals(JsonUtils.getString(getResponse, "message"),"Order not found");
			
			
		}
	
		
		@Test(groups= {"regression","store","negative"})
		public void deleteOrderTwiceTest() {
			
			//StoreService storeService = new StoreService(requestSpec,ConfigManager.getInstance().get("base.url"));

			Order order = new OrderBuilder()
					.withDefaultStoreData()					
					.build();			

		    storeService.createOrder(order);
		    
		    long orderId = order.getId();

		    storeService.deleteOrder(orderId);

		    Response response =
		            storeService.deleteOrder(orderId);

		    assertEquals(response.getStatusCode(), 404);
		}
	
	
	
	
		@Test(groups= {"regression","store","negative"})
		public void getOrderWithInvalidPathParamTest() {
			
			//StoreService storeService = new StoreService(requestSpec,ConfigManager.getInstance().get("base.url"));
			
			Response response = storeService.getInventories("/abc/1234");		
		
		    assertEquals(response.getStatusCode(), 404);
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
