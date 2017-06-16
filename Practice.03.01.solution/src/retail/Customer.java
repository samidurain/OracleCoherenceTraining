package retail;

import java.io.Serializable;

public class Customer implements Serializable, Entity<Long> {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 10L;
	private Long id;
	private String name;
	private Address address;
	
	@Override
	public Long getId() {
		return id;
	}


	public Customer(Long id, String name, Address address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Customer:\n");
		sb.append("\tID:").append(id).append("\n");
		sb.append("\tName:").append(name).append("\n");
		sb.append(address.toString(1)).append("\n");
		
		return sb.toString();
	}
	
	
}
