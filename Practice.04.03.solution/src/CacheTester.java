import com.oracle.education.retail.Address;
import com.oracle.education.retail.Customer;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.filter.AlwaysFilter;

public class CacheTester {

	private static Address[] addresses = new Address[] {
			new Address("38 Clark Place", "San Francisco", "CA", "94121"),
			new Address("44 Pumpkin Ct", "Shirley", "MA", "01464"),
			new Address("76 Nile Ct", "Arvata", "CO", "80001"),
			new Address("1920 Voss Rd", "Houston", "TX", "77057"),
			new Address("865 Seminole Tr", "Avenel", "NJ", "07001"),
			new Address("335 Woodruff Ave", "Tampa", "FL", "33066"),
			new Address("20 Pine St", "Dolan Springs", "AZ", "86441"),
			new Address("1224 Van Ness Dr", "Torrance", "CA", "90501"),
			new Address("14789 Prospect Ave", "Jim Thorpe", "PA", "18229"),
			new Address("100 Remsen Ave", "Columbia", "MD", "21044") };

	private static String[] customerNames = new String[] { "James Jones", "Scott Riley",
			"Johnny Greif", "Tobey Sandor", "Chris Sandow", "Max Xavier", "Logan Pollard",
			"Gina Smith", "Leslie Green", "Stephanie Kramer" };

	public static void main(String[] args) {
		int start = 0;
		int finish = 5;

		// Process args
		for (int i = 0; i < args.length; i++) {
			if (args[i].equalsIgnoreCase("-s")) start = Integer.parseInt(args[i + 1]);
			if (args[i].equalsIgnoreCase("-f")) finish = Integer.parseInt(args[i + 1]);
		}

		System.out.println("Start=" + start);
		System.out.println("Finish=" + finish);

		NamedCache customers = CacheFactory.getCache("Customers");

		// Load object data in cache
		System.out.println("Loading data in cache: " + start + "-" + finish);
		for (int i = start; i < finish; i++) {
			Customer customer = new Customer(customerNames[i], addresses[i]);
			customers.put(customer.getId(), customer);
		}

		// Sleep for 15 seconds to allow both all programs to run in a cluster
		// and store their data... then let them list what entries they have
		System.out.println("Pausing for 15 seconds...");
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Have all cache entries sound off by saying, 'Here I Am: toString()'
		if (start == 0) customers.invokeAll(AlwaysFilter.INSTANCE, new HereIAmProcessor());

		CacheFactory.shutdown();
	}
}
