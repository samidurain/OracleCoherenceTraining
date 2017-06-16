package retail.test;

import java.util.Map;
import java.util.Set;

import retail.filter.CustomerComparator;
import retail.listener.AddStateMapListener;

import com.oracle.education.loader.Loader;
import com.oracle.education.retail.repository.CoherenceCustomerRepository;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.Filter;
import com.tangosol.util.filter.EqualsFilter;

public class TestMapFilter {

	
	public static void main(String[] args) {

        NamedCache customerCache=
            CacheFactory.getCache(CoherenceCustomerRepository.CACHENAME);
        customerCache.addMapListener(new AddStateMapListener());

		try {
			Loader.load();
		} catch (Exception e) {
			System.out.println("Loading cache failed. Check output for errors.");
			System.out.println("Most likely there is an issue with localstorage disabled or no cacheserver running.");
			e.printStackTrace();
			return;
		}

		//
		// TODO: Add a simple Equals filter which use the getAddress method to get an address
		//       and the getState method to return the state
		//
		System.out.println("\nCache is loaded\n");
		Filter stateFilter = new EqualsFilter("getAddress.getState","CA");
		
		//
		// TODO: Complete the Customer comparator
		//
		Set<Map.Entry>  entries = customerCache.entrySet(null,new CustomerComparator());
        int allSize = entries.size();
        
        //
        // TODO: Add the comparator here as well
        //
        entries = customerCache.entrySet(stateFilter,new CustomerComparator());
        int filteredSize = entries.size();
        System.out.println("All entries = '"+allSize+"', filtered size '"+filteredSize+"'");

        
        for (Map.Entry entry: entries) {
        	System.out.println("Returned '"+entry.getKey()+"' for '"+entry.getValue()+"'");
        }
       	CacheFactory.shutdown();		
       
	}	
}
