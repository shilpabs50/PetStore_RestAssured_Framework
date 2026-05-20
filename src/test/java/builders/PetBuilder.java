package builders;

import java.util.Arrays;
import java.util.List;

import com.github.javafaker.Faker;

import models.Category;
import models.Pet;
import models.Tag;

public class PetBuilder {
	
	   Faker faker = new Faker();

	    private Pet pet;

	    public PetBuilder() {
	        pet = new Pet();
	    }
	    
	    
       public PetBuilder withDefaultPetData() {
    	   
    	   pet.setId(faker.number().randomNumber());
    	   pet.setName(faker.dog().name());
    	   pet.setStatus("available");
    	   pet.setCategory(new CategoryBuilder().withDefaultCategory().build());
    	   pet.setTags(Arrays.asList(
    			   new TagBuilder().withDefaultTag().build()
    			   ));
    	   pet.setPhotoUrls(Arrays.asList(
    			   faker.internet().url()
    			   ));
    	   
    	   return this;
       }
       
       //WITH CUSTOM NAME
       public PetBuilder withName(String name) {    	   
    	   pet.setName(name);    	   
    	   return this;    	   
       }
       
       //WITH CUSTOM STATUS
       public PetBuilder withStatus(String status) {
    	   pet.setStatus(status);
    	   return this;
       }
       
       //WITH CUSTOM ID
       public PetBuilder withId(long id) {
    	   pet.setId(id);
    	   return this;
       }
       
       //WITH CUSTOM CATEGORY
       public PetBuilder withCategory(Category category) {
    	   pet.setCategory(category);
    	   return this;
    	   
       }
       
       //WITH CUSTOM TAGS
       public PetBuilder withTags(List<Tag> tags) {
    	   pet.setTags(tags);
    	   return this;
    	   
       }
       
       //WITH CUSTOM PHOTOURL'S
       public PetBuilder withPhotoUrls(List<String> photoUrls) {    	   
    	   pet.setPhotoUrls(photoUrls);
    	   return this;
    	   
       }
       
       
       //WITH INVALID STATUS
       public PetBuilder withInvalidStatus() {
    	   
    	   pet.setStatus("wrong");
    	   return this;
    	   
       }
       
       
       //WITH NULL NAME
       public PetBuilder withNullName() {
    	   
    	   pet.setName(null);
    	   return this;
    	   
       }
       
       
     //WITH EMPTY NAME
       public PetBuilder withEmptyName() {
    	   
    	   pet.setName("");
    	   return this;
    	   
       }
       
       
       //WITH EMPTY NAME
       public PetBuilder withNegativeCategoryId() {
    	   
    	   pet.getCategory().setId(-10L);
    	   
    	   return this;
    	   
       }
       
       
       
       
	    
	    public Pet build() {
	        return pet;
	    }
	    
	}


