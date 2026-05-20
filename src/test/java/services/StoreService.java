package services;

import clients.StoreClient;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Order;
import utils.ConfigManager;

public class StoreService {
	
	private StoreClient storeClient;
	
	
	public StoreService(RequestSpecification requestSpec,String baseUrl) {
		
		storeClient = new StoreClient(requestSpec,baseUrl);
		
	}
	
	
	public Response createOrder(Order order) {
		
		return storeClient.post(ConfigManager.getInstance().get("orderPost"), order);
		
	}
	
	
	public Response getInventories() {
		
		return storeClient.get(ConfigManager.getInstance().get("inventoryGet"));
		
	}
	
	public Response getInventories(String endpoint) {
		
		return storeClient.get(ConfigManager.getInstance().get("inventoryGet")+ endpoint);
		
	}
	
	public Response getOrderById(long orderId) {
		
		return storeClient.get(ConfigManager.getInstance().get("orderGet") + orderId);
		
	}
	
	
	public Response deleteOrder(long orderId) {
		
		
		return storeClient.delete(ConfigManager.getInstance().get("orderDelete") + orderId);
	}

}
