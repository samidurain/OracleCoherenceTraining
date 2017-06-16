package com.oracle.education.retail.test;

import com.oracle.education.loader.Loader;
import com.oracle.education.retail.Item;
import com.oracle.education.retail.Line;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class TestItem {

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

		Line line = new Line(1l, 1l);

		cache.put(line.getKey(), line);
		Line lineReturned = (Line) cache.get(line.getKey());

		System.out.println("Returned: " + lineReturned);
	}
}
