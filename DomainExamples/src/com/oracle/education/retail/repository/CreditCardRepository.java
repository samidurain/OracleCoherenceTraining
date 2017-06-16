package com.oracle.education.retail.repository;

import java.util.Collection;

import com.oracle.education.retail.CreditCard;

public interface CreditCardRepository {
	public CreditCard getById(Long key);

	public Collection<CreditCard> getAll(Collection<Long> keys);

	public void save(CreditCard cachedCreditCard);
}
