import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class TestTransactionScheme {

	private static Set<Map.Entry> entries;

	public static void main(String[] args) {
		NamedCache customers = CacheFactory.getCache("tx-cache");

		// Load data in cache
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 1; i < 6; i++) {
			map.put("customer" + i, "value" + i);
		}
		customers.putAll(map);

		// Get list of all cache entries
		entries = customers.entrySet();

		// Lists all items in the cache
		for (Map.Entry entry : entries) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}

		CacheFactory.shutdown();
	}
}
