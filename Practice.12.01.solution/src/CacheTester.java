import java.io.IOException;
import java.util.Map;
import java.util.Set;

import com.oracle.education.loader.Loader;
import com.oracle.education.retail.Order;
import com.oracle.education.retail.repository.CoherenceOrderRepository;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class CacheTester {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// Load cache using Loader
		try {
			for (int i = 1; i < 6; i++) {
				System.out.println("Loading the cache (" + i + " of 5)");
				Loader.load();
				System.out.println("Sleeping for 30 seconds...");
				Thread.sleep(30000);
			}
		} catch (Exception e) {
			System.out.println("Loading cache failed. Check output for errors.");
			System.out.println("Most likely there is an issue with localstorage disabled or no cacheserver running.");
			e.printStackTrace();
			return;
		}
		System.out.println("\nCache is loaded\n");

		NamedCache orders = CacheFactory.getCache(CoherenceOrderRepository.CACHENAME);

		// Loop through some records to work the cache a little
		for (Map.Entry entry : (Set<Map.Entry>)orders.entrySet()) {
			((Order)entry.getValue()).getOrderTotal();
		}

		System.out.println("Press enter when done");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
