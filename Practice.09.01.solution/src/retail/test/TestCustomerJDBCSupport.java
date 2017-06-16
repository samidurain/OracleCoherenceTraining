package retail.test;

import com.oracle.education.retail.Address;
import com.oracle.education.retail.Customer;

import retail.cachestore.CustomerCachestore;

public class TestCustomerJDBCSupport {

	public static void main(String[] args) {
		
		CustomerCachestore ccs = new CustomerCachestore();
		
		Address address = new Address("4 pumpkin", "shirley", "MA", "01445");
		Customer customer = new Customer (1,"custName",address);
		Object key = new Long(1);
		
		ccs.store(key, customer);
		Customer c2 = (Customer) ccs.load(key);
		if ( c2 != null ) {
			System.out.println("Successfully loaded '"+c2+"'");
		}
		
		ccs.erase(key);
		
		Object o = ccs.load(key);
		if ( o !=null) {
			System.out.println("Error, found object after delete");
		} else {
			System.out.println("Successfully deleted object with key '"+key+"'");
		}
		
	}
}
