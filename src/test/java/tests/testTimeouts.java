package tests;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import groovy.util.logging.Log;
import retry.RetryAnalyzer;

public class testTimeouts extends BaseTest {
	
	

	
	@Test()
	public void testTimeOutRetry() throws SocketTimeoutException {

		logger.info("Validating request time out scenario");
		throw new SocketTimeoutException("Read timed out");

	}
	
	
	 @Test
	    public void test503Retry() {

		 logger.info("Validating request runtime exception scenario");
	        throw new RuntimeException(
	                "503 Service Unavailable"
	        );
	    }

	    @Test
	    public void test504Retry() {
	    	
	    	logger.info("Validating request 504 Gateway timout scenario");

	        throw new RuntimeException(
	                "504 Gateway Timeout"
	        );
	    }
	    
	    
	    @Test
	    public void testConnectionRetry()
	            throws ConnectException {
	    	
	    	logger.info("Validating request test connection exception scenario");

	        throw new ConnectException(
	                "Connection refused"
	        );
	    }
	    
	    @Test
	    public void testAssertionFailure() {
	    	
	    	logger.info("Validating request test assertion failure scenario");

	        Assert.assertEquals(
	                500,
	                200,
	                "Expected status code mismatch"
	        );
	    }
	    
	    @Test
	    public void testNullErrorMessage() {
	    	
	    	logger.info("Validating request nullError exception scenario");

	        throw new RuntimeException();
	    }

	
}
