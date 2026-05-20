package cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheManager {
	
	
	private static final Map<String,Object> cache = new ConcurrentHashMap<>();
	
	
	public static <T> void put(String key, T value) {
		cache.put(key, value);
	}
	
	
	public static <T> T get(String key) {
		return (T) cache.get(key);
	}
	
	public static void remove(String key) {
		cache.remove(key);
	}
	
	// clear entire cache
    public static void clear() {

        cache.clear();
    }
    
    // check key exists
    public static boolean contains(String key) {

        return cache.containsKey(key);
    }

}
