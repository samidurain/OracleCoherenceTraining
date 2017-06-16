package com.oracle.education.retail.repository;

import java.util.Collection;

import com.oracle.education.retail.Item;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class CoherenceItemRepository extends AbstractCoherenceRepository<Long, Item> implements
		ItemRepository {
	public static final String CACHENAME = "Items";
	private static final NamedCache m_cachedItems = CacheFactory.getCache(CACHENAME);

	public NamedCache getCache() {
		return m_cachedItems;
	}

	@Override
	public Item getById(Long key) {
		return super.get(key);
	}

	@Override
	public Collection<Item> getAll(Collection<Long> keys) {
		return super.getAll(keys);
	}

}
