package dataProviders;

import org.testng.annotations.DataProvider;

import builders.PetBuilder;

public class PetNegativeDataProvider {
	
	@DataProvider(name = "negativePetData")
	public Object[][] negativePetData(){
		
		return new Object[][] {
			
			{
			
				"Invalid status",
				new PetBuilder()
				.withDefaultPetData()
				.withInvalidStatus()
				.build(),
				
			//422
			200	
				
			},
			
			{
				
				"Null name",
				new PetBuilder()
				.withDefaultPetData()
				.withNullName()
				.build(),
				
			//400
			200
				
			},
			
			{
				
				"Empty name",
				new PetBuilder()
				.withDefaultPetData()
				.withEmptyName()
				.build(),
				
			//400
			200	
				
			},
			
			{
				
				"Negative id",
				new PetBuilder()
				.withDefaultPetData()
				.withNegativeCategoryId()
				.build(),
				
			//422
			200	
				
			}
			
	
		};
		
		
	}

}
