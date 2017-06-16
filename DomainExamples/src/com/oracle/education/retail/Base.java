package com.oracle.education.retail;

import com.seovic.identity.IdentityGenerator;
import com.seovic.identity.SequenceGenerator;


public class Base {
	
	protected static IdentityGenerator<Long> idGen = null;
	
	public Base() {}  //Must have this for deserialization
	
	@SuppressWarnings("unchecked")
	public Base(Class itemClass) {
		idGen = SequenceGenerator.create(itemClass.getName()+".id", 1);
	}
	
	public IdentityGenerator<Long> getGenId() { return idGen; }
}
