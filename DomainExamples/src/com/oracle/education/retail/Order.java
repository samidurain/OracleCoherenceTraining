package com.oracle.education.retail;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import com.oracle.education.retail.repository.CoherenceCreditCardRepository;
import com.oracle.education.retail.repository.CoherenceCustomerRepository;
import com.oracle.education.retail.repository.CreditCardRepository;
import com.oracle.education.retail.repository.CustomerRepository;

public class Order extends Base implements Entity<Long>, Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long customerId;
	private Address shippingAddress;
	private Date orderDate;
	private Long creditCardId;
	private float orderTotal;

	private transient CustomerRepository customer;
	private transient CreditCardRepository creditCard;
	private Collection<Line> lines = new HashSet<Line>();

	public Long getId() {
		return id;
	}

	public Order(Address shippingAddress, Date orderDate) {
		super(Order.class);
		this.id = idGen.generateIdentity();
		this.shippingAddress = shippingAddress;
		this.orderDate = orderDate;
		lines = new HashSet<Line>();
	}

	public Long getCustomerId() {
		return customerId;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Collection<Line> getLines() {
		return lines;
	}

	public void setLines(Collection<Line> lines) {
		this.lines = lines;
	}

	public void addLine(Line line) {
		lines.add(line);

		// Calculate and set orderTotal as lines are added to order
		setOrderTotal(getOrderTotal() + line.getItem().getPrice() * line.getQuantity());
	}

	private CustomerRepository getCustomerRepository() {
		if (customer == null) {
			customer = new CoherenceCustomerRepository();
		}
		return customer;
	}

	public Customer getCustomer() {
		return getCustomerRepository().getById(customerId);
	}

	public void setCustomer(Customer customer) {
		customerId = customer.getId();
	}

	private CreditCardRepository getCreditCardRepository() {
		if (creditCard == null) {
			creditCard = new CoherenceCreditCardRepository();
		}
		return creditCard;
	}

	public CreditCard getCreditCard() {
		return getCreditCardRepository().getById(creditCardId);
	}

	public void setCreditCard(CreditCard creditCard) {
		creditCardId = creditCard.getId();
	}

	public float getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(float orderTotal) {
		this.orderTotal = orderTotal;
	}

	// Not auto-generated do not delete
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Order:\n");
		sb.append("\tID:").append(id).append("\n");
		sb.append("\tCustomer Id:").append(customerId).append("\n");
		sb.append("\tShipping Address:").append(shippingAddress).append("\n");
		sb.append("\tOrder Date:").append(orderDate).append("\n");
		sb.append("\tCredit Card ID:").append(creditCardId).append("\n");
		sb.append("\tOrder Total:").append(orderTotal).append("\n");
		sb.append("\tOrdered Items:\n");
		sb.append("\tLineIds: [");
		for (Line line : getLines()) {
			sb.append(line.getLineNumber() + ", ");
		}
		sb.append("]\n");

		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creditCardId == null) ? 0 : creditCardId.hashCode());
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lines == null) ? 0 : lines.hashCode());
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + Float.floatToIntBits(orderTotal);
		result = prime * result + ((shippingAddress == null) ? 0 : shippingAddress.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Order other = (Order)obj;
		if (creditCardId == null) {
			if (other.creditCardId != null) return false;
		} else if (!creditCardId.equals(other.creditCardId)) return false;
		if (customerId == null) {
			if (other.customerId != null) return false;
		} else if (!customerId.equals(other.customerId)) return false;
		if (id == null) {
			if (other.id != null) return false;
		} else if (!id.equals(other.id)) return false;
		if (lines == null) {
			if (other.lines != null) return false;
		} else if (!lines.equals(other.lines)) return false;
		if (orderDate == null) {
			if (other.orderDate != null) return false;
		} else if (!orderDate.equals(other.orderDate)) return false;
		if (Float.floatToIntBits(orderTotal) != Float.floatToIntBits(other.orderTotal))
			return false;
		if (shippingAddress == null) {
			if (other.shippingAddress != null) return false;
		} else if (!shippingAddress.equals(other.shippingAddress)) return false;
		return true;
	}

}
