package com.oracle.education.retail;

import java.io.Serializable;

import com.oracle.education.retail.repository.CoherenceItemRepository;
import com.oracle.education.retail.repository.ItemRepository;

public class Line extends Base implements Serializable {

	public class LineKey implements Serializable {
		private static final long serialVersionUID = 1L;

		long orderId;
		long lineId;

		public LineKey(long orderId, long lineId) {
			super();
			this.orderId = orderId;
			this.lineId = lineId;
		}

		public long getOrderId() {
			return orderId;
		}

		public long getLineId() {
			return lineId;
		}

		public String toString() {
			StringBuffer sb = new StringBuffer();
			sb.append("\t\tLineKey:\n");
			sb.append("\t\tOrderId:").append(orderId).append("\n");
			sb.append("\t\tLineId:").append(lineId).append("\n");
			return sb.toString();
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			// Do NOT uncomment this line. It causes an unending loop and stackoverflow error
			// result = prime * result + getOuterType().hashCode();
			result = prime * result + (int)(lineId ^ (lineId >>> 32));
			result = prime * result + (int)(orderId ^ (orderId >>> 32));
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (obj == null) return false;
			if (getClass() != obj.getClass()) return false;
			LineKey other = (LineKey)obj;
			if (!getOuterType().equals(other.getOuterType())) return false;
			if (lineId != other.lineId) return false;
			if (orderId != other.orderId) return false;
			return true;
		}

		private Line getOuterType() {
			return Line.this;
		}

	}

	private static final long serialVersionUID = 1L;

	LineKey key;
	long itemId;
	long quantity;
	float lineTotal;

	private transient ItemRepository item;

	public Line(Long orderId, long itemId) {
		super(Line.class);
		this.key = new LineKey(orderId, idGen.generateIdentity());
		this.itemId = itemId;
		this.quantity = 1; // Defaults to 1
		updateLineTotal();
	}

	private void updateLineTotal() {
		Item item = getItem();
		setLineTotal((item == null? 0:item.getPrice()) * getQuantity());
	}

	public LineKey getKey() {
		return key;
	}

	public Long getLineNumber() {
		return key.getLineId();
	}

	public Long getOrderId() {
		return key.getOrderId();
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
		updateLineTotal();
	}

	public float getLineTotal() {
		return lineTotal;
	}

	private void setLineTotal(float lineTotal) {
		this.lineTotal = lineTotal;
	}

	private ItemRepository getItemRepository() {
		if (item == null) {
			item = new CoherenceItemRepository();
		}
		return item;
	}

	public Item getItem() {
		return getItemRepository().getById(itemId);
	}

	public void setItem(Item item) {
		itemId = item.getId();
	}

	// Not auto-generated do not delete
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Line:\n");
		sb.append("\tID:").append("\n").append(key);
		sb.append("\tItem Id:").append(itemId).append("\n");
		sb.append("\tQuantity:").append(quantity).append("\n");
		sb.append("\tLine total:").append(lineTotal).append("\n");
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int)(itemId ^ (itemId >>> 32));
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + Float.floatToIntBits(lineTotal);
		result = prime * result + (int)(quantity ^ (quantity >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Line other = (Line)obj;
		if (itemId != other.itemId) return false;
		if (key == null) {
			if (other.key != null) return false;
		} else if (!key.equals(other.key)) return false;
		if (Float.floatToIntBits(lineTotal) != Float.floatToIntBits(other.lineTotal)) return false;
		if (quantity != other.quantity) return false;
		return true;
	}

}
