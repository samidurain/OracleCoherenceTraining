package com.oracle.education.retail;

import java.io.Serializable;

@SuppressWarnings("unchecked")
public class Item extends Base implements Entity<Long>, Serializable, Comparable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String description;
	private float price;
	private String sku;

	public Item(String description, float price, String sku) {
		super(Item.class);
		this.id = idGen.generateIdentity();
		this.description = description;
		this.price = price;
		this.sku = sku;
	}

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	// Not auto-generated do not delete
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Item:\n");
		sb.append("\tID:").append(id).append("\n");
		sb.append("\tDescription:").append(description).append("\n");
		sb.append("\tPrice:").append(price).append("\n");
		sb.append("\tSKU:").append(sku).append("\n");

		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + Float.floatToIntBits(price);
		result = prime * result + ((sku == null) ? 0 : sku.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Item other = (Item)obj;
		if (description == null) {
			if (other.description != null) return false;
		} else if (!description.equals(other.description)) return false;
		if (id == null) {
			if (other.id != null) return false;
		} else if (!id.equals(other.id)) return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price)) return false;
		if (sku == null) {
			if (other.sku != null) return false;
		} else if (!sku.equals(other.sku)) return false;
		return true;
	}

	
	@Override
	public int compareTo(Object o) {
		Item item = (Item)o;
		return sku.compareTo(item.getSku());
	}
}
