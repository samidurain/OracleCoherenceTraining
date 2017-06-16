import com.oracle.education.retail.Customer;
import com.tangosol.util.InvocableMap.Entry;
import com.tangosol.util.processor.AbstractProcessor;


public class HereIAmProcessor extends AbstractProcessor {

	private static final long serialVersionUID = -6167300806828114650L;
	
	public Object process(Entry entry) {

		if (entry.isPresent() && entry.getValue() instanceof Customer) {
			System.out.println("Here I am: " + ((Customer)entry.getValue()).toString());
		} else {
			System.out.println("Expected a customer");
		}
		return null;
	}
}
