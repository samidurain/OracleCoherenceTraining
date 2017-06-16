package com.oracle.education.retail.repository;

import java.util.Collection;

import com.oracle.education.retail.Customer;

public interface CustomerRepository {
	public Customer getById(Long key);

	public Collection<Customer> getAll(Collection<Long> keys);

	public void save(Customer cachedCustomer);
}
