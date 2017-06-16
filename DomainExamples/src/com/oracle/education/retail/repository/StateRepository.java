package com.oracle.education.retail.repository;

import java.util.Collection;

import com.oracle.education.retail.State;

public interface StateRepository {
	public State getById(Long key);

	public Collection<State> getAll(Collection<Long> keys);

	public void save(State cachedItem);
}
