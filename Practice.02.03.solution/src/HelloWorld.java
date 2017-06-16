import java.util.Map;
import java.util.Set;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;


public class HelloWorld {

	/**
	 * @param args
	 */
	
	
	public static void main(String[] args) {
		

	    NamedCache cache= CacheFactory.getCache("testcache");
//      	cache.put("MA","Massachustts");
//        cache.put("NH","New Hampshire");
//        cache.put("CT","Connecticut");
        
        
        Set<Map.Entry> entries = cache.entrySet(null,null);
        for (Map.Entry entry: entries) {
        	System.out.println("Returned '"+entry.getKey()+"' for '"+entry.getValue()+"'");
        }
     
        cache.put("MA", "Massachusetts");
        String value = (String)cache.get("MA");
    	System.out.println("Returned '"+value+"' for 'MA'");
        
	}

}
