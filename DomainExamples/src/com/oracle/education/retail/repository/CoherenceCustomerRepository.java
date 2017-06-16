package com.oracle.education.retail.repository;

import java.util.Collection;

import com.oracle.education.retail.Customer;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class CoherenceCustomerRepository extends AbstractCoherenceRepository<Long, Customer>
		implements CustomerRepository {
	public static final String CACHENAME = "Customers";
	private static final NamedCache m_cachedItems = CacheFactory.getCache(CACHENAME);

	public NamedCache getCache() {
		return m_cachedItems;
	}

	@Override
	public Customer getById(Long key) {
		return super.get(key);
	}

	@Override
	public Collection<Customer> getAll(Collection<Long> keys) {
		return super.getAll(keys);
	}

}
