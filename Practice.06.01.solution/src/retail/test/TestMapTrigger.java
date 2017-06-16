package retail.test;

import java.util.Map;
import java.util.Set;

import retail.*;
import retail.listener.AddStateMapListener;
import retail.trigger.ToLowerMapTrigger;

import com.oracle.education.loader.Loader;
import com.oracle.education.retail.repository.CoherenceCustomerRepository;
import com.oracle.education.retail.repository.CoherenceItemRepository;
import com.oracle.education.retail.repository.CoherenceStateRepository;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.Binary;
import com.tangosol.util.ExternalizableHelper;
import com.tangosol.util.Base;
import com.tangosol.util.MapTriggerListener;

public class TestMapTrigger {

	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {


        NamedCache customerCache=
            CacheFactory.getCache(CoherenceCustomerRepository.CACHENAME);
		customerCache.addMapListener(new AddStateMapListener());
        
        
        NamedCache itemCache=
           CacheFactory.getCache(CoherenceItemRepository.CACHENAME);
        
        
        // wrap it into a listener
        // and add the listener to the cache
        ToLowerMapTrigger trigger = new ToLowerMapTrigger();
        MapTriggerListener listener = new MapTriggerListener(trigger);
        itemCache.addMapListener(listener);

		try {
			Loader.load();
		} catch (Exception e) {
			System.out.println("Loading cache failed. Check output for errors.");
			System.out.println("Most likely there is an issue with localstorage disabled or no cacheserver running.");
			e.printStackTrace();
			return;
		}
		System.out.println("\nCache is loaded\n");
		
   
        Set<Map.Entry> entries = itemCache.entrySet(null,null);
        for (Map.Entry entry: entries) {
        	System.out.println("Returned '"+entry.getKey()+"' for '"+entry.getValue()+"'");
        }

        
        CacheFactory.shutdown();		
	}	
}
