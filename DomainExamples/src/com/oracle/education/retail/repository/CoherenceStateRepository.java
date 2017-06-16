package com.oracle.education.retail.repository;

import java.util.Collection;

import com.oracle.education.retail.State;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class CoherenceStateRepository extends
		AbstractCoherenceRepository<Long, State> implements StateRepository {
	public static final String CACHENAME = "States";
	
	private static final NamedCache m_cachedItems = CacheFactory.getCache(CACHENAME);

	public NamedCache getCache() {
		return m_cachedItems;
	}

	@Override
	public State getById(Long key) {
		return super.get(key);
	}

	@Override
	public Collection<State> getAll(Collection<Long> keys) {
		return super.getAll(keys);
	}

}