package dataProviders;

import org.testng.annotations.DataProvider;

public class PetNegativeE2EDataProvider {
	
	@DataProvider(name = "negativeE2EWorkflow" )
	public Object[][] negativeE2EWorkflow(){
		
		
		return new Object[][] {
			
			
			{
				
				"Get After Delete"
			},
			
			
			{
				
				"Delete Twice"
			},
			
			
			{
				
				"Update Deleted Pet"
				
			}
		
			
		};
		
		
	}
	

}
