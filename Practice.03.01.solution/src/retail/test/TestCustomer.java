package retail.test;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.Base;
import com.tangosol.util.Binary;
import com.tangosol.util.ExternalizableHelper;

import retail.Address;
import retail.Customer;

public class TestCustomer {

	
	public static void main(String[] args) {

        NamedCache cache= CacheFactory.getCache("retail.customers");
      
        cache.clear();
      

        Address address = new Address("8 Van de Graaf Drive", "Burlington", "MA","01803");
        
        Customer customer = new Customer(1l,"Bill Iards",address);
        cache.put(customer.getId(),customer);
        
        Binary binValue = null;
        binValue = ExternalizableHelper.toBinary(customer);  
        System.out.println("\n\nBinary value ("+binValue.length()+"):" + Base.toHexDump(binValue.toByteArray(), 16) +"\n\n");
        Customer customerOut = (Customer)cache.get(customer.getId());
        
        System.out.println("Returned: " + customerOut);
	}	
}
