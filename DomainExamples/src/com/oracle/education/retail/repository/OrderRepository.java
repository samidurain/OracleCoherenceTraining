package com.oracle.education.retail.repository;

import java.util.Collection;

import com.oracle.education.retail.Order;

public interface OrderRepository {
	public Order getById(Long key);

	public Collection<Order> getAll(Collection<Long> keys);

	public void save(Order cachedItem);
}
