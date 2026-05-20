package builders;

import com.github.javafaker.Faker;

import models.Category;

public class CategoryBuilder {
	
	private static Faker faker = new Faker();
	
	
	private Category category;
	
	
	public CategoryBuilder() {
		
		category = new Category();
		
	}
	
	
	public CategoryBuilder withDefaultCategory() {
		
		category.setId(faker.number().randomNumber());
		category.setName(faker.animal().name());
		return this;
		
	}
	
	
	public Category build() {
		return category;
	}

}
