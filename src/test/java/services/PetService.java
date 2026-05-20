package services;

import clients.RestClient;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Pet;
import utils.ConfigManager;

public class PetService {
	
	private RestClient client;
	
	public PetService(RequestSpecification requestSpec,String baseurl) {
		
		client = new RestClient(requestSpec,baseurl);
		
	}
	
			
	   // private RestClient client = new RestClient();

	    public Response createPet(Pet pet) {

	        return client.post(ConfigManager.getInstance().get("petPost"), pet);
	    }

	    public Response createPet(String pet) {

	        return client.post(ConfigManager.getInstance().get("petPost"), pet);
	    }
	    
	    public Response getPetById(long petId) {

	        return client.get(ConfigManager.getInstance().get("petGet") + petId);
	    }

	    public Response updatePet(Pet pet) {

	        return client.put(ConfigManager.getInstance().get("petPut"), pet);
	    }

	    public Response deletePet(long petId) {

	        return client.delete(ConfigManager.getInstance().get("petDelete") + petId);
	    }
	}
	

