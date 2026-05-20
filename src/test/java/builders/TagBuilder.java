package builders;

import com.github.javafaker.Faker;

import models.Tag;

public class TagBuilder {

	
	private static Faker faker = new Faker();
	
	private Tag tag;
	
	
	public TagBuilder() {
		
		tag = new Tag();
		
	}
	
	
	public TagBuilder withDefaultTag() {
		tag.setId(faker.number().randomNumber());
		tag.setName(faker.dog().breed());
		
		
		return this;
		
	}
	
	
	public Tag build() {
		
		return tag;
	}
	
	
}
