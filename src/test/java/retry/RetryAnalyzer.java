package retry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


public class RetryAnalyzer implements IRetryAnalyzer {
	
	
	private static final Logger logger =
				LogManager.getLogger(RetryAnalyzer.class);
	
	private int retryCount = 0;
	
	private static final int MAX_RETRY_COUNT = 2;
	
	

	@Override
	public boolean retry(ITestResult result) {
		
		Throwable error = result.getThrowable();
		
		String errorMessage = error != null ? error.toString() : ""; // to check this statement
		
		boolean isRetryableError =
		 errorMessage.contains("SocketTimeoutException")
		||errorMessage.contains("ConnectException")
	    ||errorMessage.contains("503")
	    ||errorMessage.contains("504");
		
		
		if(isRetryableError && retryCount < MAX_RETRY_COUNT) {
			
			retryCount++;
		

			RetryContext.setRetryCount(result.getName(),retryCount);	          
			 
			String retryMessage = "Retrying Test: " 
								+ result.getName()
								+ "| Retry count: "
								+ retryCount;								
	
			//for displaying log in the console, for reporting using allure attachements in the TestListener class
			logger.warn(retryMessage);	
			
			return true;
		}
		
		
		
		  logger.error(
	                "Test Failed: {} | Reason: {}",
	                result.getName(),
	                errorMessage
	        );
		
		
		return false;
	}
	


}
