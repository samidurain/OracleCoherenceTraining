package retail.test;

import retail.*;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.Binary;
import com.tangosol.util.ExternalizableHelper;
import com.tangosol.util.Base;

public class TestCustomer {

	
	public static void main(String[] args) {

        NamedCache cache=
            CacheFactory.getCache("retail.customers");
      
        cache.clear();
       
        Address address = new Address("8 Van de Graaf Drive", "Burlington", "MA","01803");
        Customer customer =  new Customer(1l,"Bill Iards",address);
        cache.put(customer.getId(),customer);
        System.out.println("Original: " + customer);      
        
        Binary binValue = null;
        binValue = ExternalizableHelper.toBinary(customer);  
        System.out.println("\n\nBinary value ("+binValue.length()+"):" + Base.toHexDump(binValue.toByteArray(), 16) +"\n\n");
     
        // get it back from the cache
        Customer value2 = (Customer)cache.get(customer.getId());   
        System.out.println("Returned: " + value2);
	}	
}
