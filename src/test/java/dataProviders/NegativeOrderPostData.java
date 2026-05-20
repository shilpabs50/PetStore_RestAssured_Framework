package dataProviders;

import org.testng.annotations.DataProvider;

public class NegativeOrderPostData {
	
	
	@DataProvider(name = "negativeOrderData")
	public Object[][] negativeOrderData(){
		
		return new Object[][] {
			
			    {-1, "placed"},
	            {0, "placed"},
	            {99999999, "placed"},
	            {5, ""},
	            {5, null},
	            {5, "INVALID"}
	
			
		};

	}
	
	

 }
