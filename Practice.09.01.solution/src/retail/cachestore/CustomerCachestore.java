package retail.cachestore;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import com.oracle.education.retail.Customer;
import com.tangosol.net.cache.CacheStore;


//
// TODO: Once completed, this class needs to be registered as a CacheStore with appropriate initializer arguments
//
@SuppressWarnings("unchecked")
public class CustomerCachestore extends CustomerJdbcSupport implements CacheStore {

	
	//
	// TODO: create a constructor which takes three arguments
	// the connection info, uName and password
	// and uses them rather then hard coded values
	//  Perhaps the simplest way is via the eclipse right click > source > generate from super class
	public CustomerCachestore(String connectionInfo, String uName, String pwd) {
		super(connectionInfo, uName, pwd);
		System.out.println("\n\n\n+++++++++++++++++++");
		System.out.println("CustomerCachestore called with '"+connectionInfo+"', '"+uName+"', '"+pwd+"'");
		System.out.println("+++++++++++++++++++\n\n\n");
	}
	
	private static String connectionInfo = "jdbc:oracle:thin:@localhost:1521:XE";
	private static String uName = "COHERENCE";
	private static String pwd = "COHERENCE";
	
	
	public CustomerCachestore() {
		super(connectionInfo, uName, pwd);
	}
	@Override
	public void erase(Object oKey) {
		System.out.println("\n\n\n+++++++++++++++++++");
		System.out.println("CustomerCachestore.erase('"+oKey+"') called ");
		System.out.println("+++++++++++++++++++\n\n\n");

		Collection<Long> keys = new LinkedList<Long>();
		if ( oKey instanceof Long)
			keys.add((Long)oKey);
		deleteCustomers(keys);	
	}
	
	@Override
	public void eraseAll(Collection colKeys) {
		deleteCustomers(colKeys);
	}

	@Override
	public void store(Object oKey, Object oValue) {
		System.out.println("\n\n\n+++++++++++++++++++");
		System.out.println("CustomerCachestore.store('"+oKey+"','"+oValue+"') called ");
		System.out.println("+++++++++++++++++++\n\n\n");

		Collection<Customer> customers = new LinkedList<Customer>();
		if ( oValue instanceof Customer) {
			customers.add((Customer) oValue);
		}
		saveCustomers(customers);
	}

	@Override
	public void storeAll(Map mapEntries) {
		Iterator it = mapEntries.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
            store(pair.getKey(),pair.getValue());
	    }
	}

	@Override
	public Object load(Object oKey) {
		System.out.println("\n\n\n+++++++++++++++++++");
		System.out.println("CustomerCachestore.load('"+oKey+"') called ");
		System.out.println("+++++++++++++++++++\n\n\n");

		Object result = (oKey instanceof Long)?loadCustomer((Long)oKey):null;
		System.out.println("\n\n\n+++++++++++++++++++");
		System.out.println("CustomerCachestore.load('"+oKey+"') returning '"+result+"'");
		System.out.println("+++++++++++++++++++\n\n\n");
		return result;
	}

	
	@Override
	public Map loadAll(Collection colKeys) {
		Map results = new HashMap();
		
		for (Object key: colKeys ) {
			Object value = load(key);
			
			if ( value != null)
				results.put(key,value);
		}
		return (results.isEmpty()? null: results);
	}


}
