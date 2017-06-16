package retail.test;

import java.util.Map;
import java.util.Set;

import retail.aggregator.StringAggregator;
import retail.filter.CustomerComparator;
import retail.listener.AddStateMapListener;

import com.oracle.education.loader.Loader;
import com.oracle.education.retail.repository.CoherenceCustomerRepository;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.Filter;
import com.tangosol.util.QueryHelper;
import com.tangosol.util.extractor.ChainedExtractor;
import com.tangosol.util.extractor.ReflectionExtractor;
import com.tangosol.util.filter.EqualsFilter;

public class TestQuery {

	
	@SuppressWarnings("unchecked")
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
		// TODO: Replace the state filter with an equivalent where statement using a single argument
		// taking a string state
		//
		System.out.println("\nCache is loaded\n");
		Filter stateFilter = new EqualsFilter("getAddress.getState","CA");
		stateFilter = QueryHelper.createFilter("address.state=?1", new Object[]{ new String("CA")} );		
		
		Set<Map.Entry>  entries = customerCache.entrySet(null,new CustomerComparator());
        int allSize = entries.size();
        
        
        entries = customerCache.entrySet(stateFilter,new CustomerComparator());
        int filteredSize = entries.size();
        System.out.println("All entries = '"+allSize+"', filtered size '"+filteredSize+"'");

        
        for (Map.Entry entry: entries) {
        	System.out.println("Returned '"+entry.getKey()+"' for '"+entry.getValue()+"'");
        }
        
        Object result = customerCache.aggregate(stateFilter, new StringAggregator(new ChainedExtractor("getAddress.getZip")));
        if ( result != null ) {
        	System.out.println("Aggregator returned '"+result+"'");
        }
       	CacheFactory.shutdown();		
       
	}	
}
