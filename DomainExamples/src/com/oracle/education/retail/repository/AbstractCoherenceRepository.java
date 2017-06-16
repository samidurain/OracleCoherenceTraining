package com.oracle.education.retail.repository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.oracle.education.retail.Entity;
import com.tangosol.net.NamedCache;

public abstract class AbstractCoherenceRepository<K, V extends Entity<K>> {
	public abstract NamedCache getCache();

	@SuppressWarnings("unchecked")
	public V get(K key) {
		return (V) getCache().get(key);
	}

	@SuppressWarnings("unchecked")
	public Collection<V> getAll(Collection<K> keys) {
		return getCache().getAll(keys).values();
	}

	public void save(V value) {
		getCache().putAll(Collections.singletonMap(value.getId(), value));
	}

	@SuppressWarnings("unchecked")
	public void saveAll(Collection<V> values) {
		Map batch = new HashMap(values.size());
		for (V value : values) {
			batch.put(value.getId(), value);
		}
		getCache().putAll(batch);
	}
}