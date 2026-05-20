package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import cache.CacheManager;

public class sampleTest extends BaseTest {
	
	
	//@Test(priority=2)
	public void getPet() {
		
    Long cachedpetId = CacheManager.get("PET_ID");
    
    if (cachedpetId == null) {
        throw new RuntimeException("PET_ID not found in cache");
    }
    else {
    System.out.println("Extracted pet id from cache "+cachedpetId);
    }
		
		
	}

}
