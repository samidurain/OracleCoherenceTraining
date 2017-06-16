package retail.test;

import retail.*;

import com.tangosol.io.pof.PortableObjectSerializer;
import com.tangosol.io.pof.SimplePofContext;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.Binary;
import com.tangosol.util.ExternalizableHelper;
import com.tangosol.util.Base;

public class TestCustomer {

	
	public static void main(String[] args) {

		 NamedCache cache=  CacheFactory.getCache("retail.customers");
	      
	        cache.clear();
	        
        SimplePofContext ctx = new SimplePofContext();
        ctx.registerUserType(1000, Customer.class ,new PortableObjectSerializer(1000));
        ctx.registerUserType(1001, Address.class ,new PortableObjectSerializer(1001));
        
       
        Address address = new Address("8 Van de Graaf Drive", "Burlington", "MA","01803");
        Customer original =  new Customer(1l,"Bill Iards",address);
        
        System.out.println("Original: " + original);      
        
        Binary binary   = ExternalizableHelper.toBinary(original, ctx);
        cache.put(original.getId(),binary);
        System.out.println("\n\nBinary value ("+binary.length()+"):" + Base.toHexDump(binary.toByteArray(), 16) +"\n\n");

       Object copy     = ExternalizableHelper.fromBinary(binary, ctx);  
        System.out.println("Returned: " + copy);
        System.out.println("Copy: " + original);      
        
	}	
}
