package com.oracle.education.retail.test;

import com.oracle.education.loader.Loader;
import com.oracle.education.retail.Item;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class TestLine {

	public static void main(String[] args) {

		try {
			Loader.load();
		} catch (Exception e) {
			System.out.println("Loading cache failed. Check output for errors.");
			System.out.println("Most likely there is an issue with localstorage disabled or no cacheserver running.");
			e.printStackTrace();
			return;
		} finally {
			System.out.println("\nCache is loaded\n");
		}
		
		NamedCache cache = CacheFactory.getCache("retail");

		cache.clear();

		Item item = new Item("iPhone 3GS", 99.99f, "apliPhone3gs");

		cache.put(item.getId(), item);
		Item itemReturned = (Item) cache.get(item.getId());

		System.out.println("Returned: " + itemReturned);
	}
}
