package listeners;

import io.qameta.allure.Allure;
import retry.RetryContext;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {


    
    @Override
    public void onTestFailure(ITestResult result) {
    	
    	//for reporting
    	
    	 int retryCount = RetryContext.getRetryCount(result.getName());       
        
        int totalAttempts = retryCount + 1;                
        
        Allure.addAttachment(
                "Retry Information",
                "Test Name: " + result.getName()
                + "\nRetry Count: " + retryCount
                + "\nTotal Attempts: " + totalAttempts
        );
    }
    
    
    @Override
    public void onTestSuccess(ITestResult result) {
    	
    	//for reporting
    	
    	long executionTime =  result.getEndMillis()  - result.getStartMillis();
    	
    	Allure.addAttachment("Test Execution summary: ", 
    			              "Test Name: "+result.getName() +
    			              "\nStatus: PASSED" +
    			              "\nExecution time: "+executionTime + " ms");
    	
    
    	
    }
    
    
}