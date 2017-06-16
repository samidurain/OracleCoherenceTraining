package com.oracle.education.retail.repository;

import java.util.Collection;

import com.oracle.education.retail.Order;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class CoherenceOrderRepository extends AbstractCoherenceRepository<Long, Order> implements
		OrderRepository {
	public static final String CACHENAME = "Orders";
	private static final NamedCache m_cachedItems = CacheFactory.getCache(CACHENAME);

	public NamedCache getCache() {
		return m_cachedItems;
	}

	@Override
	public Order getById(Long key) {
		return super.get(key);
	}

	@Override
	public Collection<Order> getAll(Collection<Long> keys) {
		return super.getAll(keys);
	}

}
