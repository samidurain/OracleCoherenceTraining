package com.oracle.education.retail;

import java.io.Serializable;

public class CreditCard extends Base implements Entity<Long>, Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String cardNumber;
	private String description;
	private float limit;
	private float balance;
	private String expiration;
	private String csv;

	public CreditCard(String cardNumber, String description, String expiration, String csv) {
		super(CreditCard.class);
		this.id = idGen.generateIdentity();
		this.cardNumber = cardNumber;
		this.description = description;
		this.expiration = expiration;
		this.csv = csv;
	}

	public Long getId() {
		return id;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getLimit() {
		return limit;
	}

	public void setLimit(float limit) {
		this.limit = limit;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public String getExpiration() {
		return expiration;
	}

	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}

	public String getCsv() {
		return csv;
	}

	public void setCsv(String csv) {
		this.csv = csv;
	}

	// Not auto-generated do not delete
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\tCreditCard:\n");
		sb.append("\t\tID:").append(id).append("\n");
		sb.append("\t\tCard Number:").append(cardNumber).append("\n");
		sb.append("\t\tDescription:").append(description).append("\n");
		sb.append("\t\tLimit:").append(limit).append("\n");
		sb.append("\t\tBalance:").append(balance).append("\n");
		sb.append("\t\tExpiration:").append(expiration).append("\n");
		sb.append("\t\tCSV:").append(csv).append("\n");

		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(balance);
		result = prime * result + ((cardNumber == null) ? 0 : cardNumber.hashCode());
		result = prime * result + ((csv == null) ? 0 : csv.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((expiration == null) ? 0 : expiration.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + Float.floatToIntBits(limit);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		CreditCard other = (CreditCard)obj;
		if (Float.floatToIntBits(balance) != Float.floatToIntBits(other.balance)) return false;
		if (cardNumber == null) {
			if (other.cardNumber != null) return false;
		} else if (!cardNumber.equals(other.cardNumber)) return false;
		if (csv == null) {
			if (other.csv != null) return false;
		} else if (!csv.equals(other.csv)) return false;
		if (description == null) {
			if (other.description != null) return false;
		} else if (!description.equals(other.description)) return false;
		if (expiration == null) {
			if (other.expiration != null) return false;
		} else if (!expiration.equals(other.expiration)) return false;
		if (id == null) {
			if (other.id != null) return false;
		} else if (!id.equals(other.id)) return false;
		if (Float.floatToIntBits(limit) != Float.floatToIntBits(other.limit)) return false;
		return true;
	}

}
