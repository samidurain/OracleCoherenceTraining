package com.oracle.education.retail;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import com.oracle.education.retail.repository.CoherenceCreditCardRepository;
import com.oracle.education.retail.repository.CoherenceOrderRepository;
import com.oracle.education.retail.repository.CreditCardRepository;
import com.oracle.education.retail.repository.OrderRepository;

public class Customer extends Base implements Serializable, Entity<Long> {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Address address;
	private Collection<Long> ccIds = new HashSet<Long>();
	private Collection<Long> orderIds = new HashSet<Long>();
	private transient CreditCardRepository creditCards;
	private transient OrderRepository orders;

	public Customer() {}

	public Customer(String name, Address address) {
		super(Customer.class);
		this.id = idGen.generateIdentity();
		this.name = name;
		this.address = address;
	}
	public Customer(long id, String name, Address address) {
		super(Customer.class);
		this.id = id;
		this.name = name;
		this.address = address;
	}

	@Override
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	private CreditCardRepository getCreditCardRepository() {
		if (creditCards == null) {
			creditCards = new CoherenceCreditCardRepository();
		}
		return creditCards;
	}

	public Collection<CreditCard> getCreditCards() {
		return getCreditCardRepository().getAll(ccIds);
	}

	public void addCreditCard(CreditCard creditCard) {
		if (!ccIds.contains(creditCard.getId())) ccIds.add(creditCard.getId());
		getCreditCardRepository().save(creditCard);
	}

	private OrderRepository getOrderRepository() {
		if (orders == null) {
			orders = new CoherenceOrderRepository();
		}
		return orders;
	}

	public Collection<Order> getOrders() {
		return getOrderRepository().getAll(orderIds);
	}

	public void addOrder(Order order) {
		if (!orderIds.contains(order.getId())) orderIds.add(order.getId());
		getOrderRepository().save(order);
	}

	// Not auto-generated do not delete
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Customer:\n");
		sb.append("\tID:").append(id).append("\n");
		sb.append("\tName:").append(name).append("\n");
		sb.append("\tAddress:").append(address).append("\n");
		sb.append("\tCredit Card IDs:").append(ccIds).append("\n");
		sb.append("\tOrder IDs:").append(orderIds).append("\n");

		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((ccIds == null) ? 0 : ccIds.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((orderIds == null) ? 0 : orderIds.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Customer other = (Customer)obj;
		if (address == null) {
			if (other.address != null) return false;
		} else if (!address.equals(other.address)) return false;
		if (ccIds == null) {
			if (other.ccIds != null) return false;
		} else if (!ccIds.equals(other.ccIds)) return false;
		if (id == null) {
			if (other.id != null) return false;
		} else if (!id.equals(other.id)) return false;
		if (name == null) {
			if (other.name != null) return false;
		} else if (!name.equals(other.name)) return false;
		if (orderIds == null) {
			if (other.orderIds != null) return false;
		} else if (!orderIds.equals(other.orderIds)) return false;
		return true;
	}

}
