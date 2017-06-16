package com.oracle.education.retail.repository;

import java.util.Collection;

import com.oracle.education.retail.CreditCard;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class CoherenceCreditCardRepository extends AbstractCoherenceRepository<Long, CreditCard>
		implements CreditCardRepository {
	public static final String CACHENAME = "CreditCards";
	private static final NamedCache m_cachedItems = CacheFactory.getCache(CACHENAME);

	public NamedCache getCache() {
		return m_cachedItems;
	}

	@Override
	public CreditCard getById(Long key) {
		return super.get(key);
	}

	@Override
	public Collection<CreditCard> getAll(Collection<Long> keys) {
		return super.getAll(keys);
	}

}
