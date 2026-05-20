package builders;

import org.joda.time.Instant;

import com.github.javafaker.Faker;

import models.Order;
import models.Pet;

public class OrderBuilder {

	Faker faker = new Faker();
	
	private Order order;
	
	
	public OrderBuilder() {
		
		order = new Order();
	}
	
	
	public OrderBuilder withDefaultStoreData() {
		
		order.setId(faker.number().randomNumber());
		order.setPetId(faker.number().randomNumber());
		order.setQuantity(faker.number().numberBetween(1, 10));
		order.setShipDate(Instant.now().toString());
		order.setStatus(faker.options().option("placed","approved","delivered"));
		order.setComplete(faker.bool().bool());
		
		return this;
		
		
	}
	
	
	public OrderBuilder withQuantity(int quantity){
		
		order.setQuantity(quantity);
		return this;
		
	}
	
	
	public OrderBuilder withStatus(String status){
		
	    order.setStatus(status);
		return this;
		
	}
	
	 public Order build() {
	        return order;
	    }
	
	
	
}
