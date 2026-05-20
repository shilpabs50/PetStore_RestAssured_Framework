package dataProviders;

import org.testng.annotations.DataProvider;

import builders.PetBuilder;

public class PetWorkflowDataProvider {
	
	@DataProvider(name="positiveWorkflowData")
	public Object[][] positiveWorkflowData(){
		
		return new Object[][] {
			
			{
				
				"Available to sold",
				
				new PetBuilder()
					.withDefaultPetData()
					.withStatus("available")
					.build(),
					
					
			    "sold"		
			
			},
			
			{
				
				"Available to pending",
				
				new PetBuilder()
					.withDefaultPetData()
					.withStatus("available")
					.build(),
					
					
			    "pending"		
			
			},
			
					
			

			{
				
				"Pending to Sold",
				
				new PetBuilder()
					.withDefaultPetData()
					.withStatus("pending")
					.build(),
					
					
			    "sold"		
			
			},
		
			
		};
		
		
		
		
	}

}
