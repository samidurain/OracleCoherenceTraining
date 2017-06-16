package retail.test;

import java.util.Map;
import java.util.Set;

import retail.aggregator.StringAggregator;
import retail.filter.CustomerComparator;
import retail.listener.AddStateMapListener;

import com.oracle.education.loader.Loader;
import com.oracle.education.retail.repository.CoherenceCustomerRepository;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.Filter;
import com.tangosol.util.QueryHelper;
import com.tangosol.util.extractor.ChainedExtractor;
import com.tangosol.util.extractor.ReflectionExtractor;
import com.tangosol.util.filter.EqualsFilter;

public class TestCacheStore {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		NamedCache customerCache = CacheFactory
				.getCache(CoherenceCustomerRepository.CACHENAME);

		try {
			Loader.load(true);
		} catch (Exception e) {
			System.out
					.println("Loading cache failed. Check output for errors.");
			System.out
					.println("Most likely there is an issue with localstorage disabled or no cacheserver running.");
			e.printStackTrace();
			return;
		}

		System.out.println("\nCache is loaded\n");
		Set<Map.Entry> entries = customerCache.entrySet(null,
				new CustomerComparator());
		for (Map.Entry entry : entries) {
			System.out.println("Returned '" + entry.getKey() + "' for '"
					+ entry.getValue() + "'");
			customerCache.remove(entry.getKey());
		}
		CacheFactory.shutdown();

	}
}
