package com.oracle.education.loader;

import com.seovic.identity.IdentityGenerator;
import com.seovic.identity.SequenceGenerator;


public class Base {
	
	private static IdentityGenerator<Long> idGen = null;
	
	public Base() {}
	
	@SuppressWarnings("unchecked")
	public Base(Class itemClass) {
		idGen = SequenceGenerator.create(itemClass.getName()+".id", 20);
	}
	
	public IdentityGenerator<Long> getGenId() { return idGen; }
}
