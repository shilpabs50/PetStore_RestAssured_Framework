package retry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RetryContext {

	//to attach retry analyzer to each test method by default
	
    private static final Map<String, Integer> retryMap =  new ConcurrentHashMap<>();

    public static void setRetryCount(String testName, int retryCount) {

        retryMap.put(testName, retryCount);
    }

    public static int getRetryCount( String testName) {

        return retryMap.getOrDefault(testName, 0);
    }
}
