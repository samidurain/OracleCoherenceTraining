import java.util.Map;
import java.util.Set;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class CacheTester {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		int start = 0;
		int finish = 10;

		// Process args
		for (int i = 0; i < args.length; i++) {
			if (args[i].equalsIgnoreCase("-s"))
				start = Integer.parseInt(args[i + 1]);
			if (args[i].equalsIgnoreCase("-f"))
				finish = Integer.parseInt(args[i + 1]);
		}

		System.out.println("Start=" + start);
		System.out.println("Finish=" + finish);

		NamedCache customers = CacheFactory.getCache("Custome");

		// Load data in cache
		System.out.println("Loading data in cache: " + start + "-" + finish);
		for (int i = start; i < finish; i++) {
			customers.put("customer" + i, "Mark " + i);
		}

		// Sleep for 15 seconds to allow both all programs to run in a cluster
		// and store their data... then let them list what entries they have
		System.out.println("Pausing for 15 seconds...");
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Get list of all cache entries
		Set<Map.Entry> entries = customers.entrySet(null, null);

		// Lists all items in the cache
		for (Map.Entry entry : entries) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}

		//CacheFactory.shutdown();
	}
}
