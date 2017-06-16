package com.oracle.education.retail.repository;

import java.util.Collection;

import com.oracle.education.retail.Item;

public interface ItemRepository {
	public Item getById(Long key);

	public Collection<Item> getAll(Collection<Long> keys);

	public void save(Item cachedItem);
}
