package com.oracle.education.retail.test;

import com.oracle.education.retail.Address;
import com.oracle.education.retail.Customer;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class TestCustomer {

	public static void main(String[] args) {

		NamedCache cache = CacheFactory.getCache("retail");

		cache.clear();

		Address address = new Address("4 Pumpkin Ct", "Shirley", "MA", "01464");

		Customer customer = new Customer("T.Hardy", address);
		cache.put(customer.getId(), customer);

		Customer customerOut = (Customer) cache.get(customer.getId());

		System.out.println("Returned: " + customerOut);
	}
}
