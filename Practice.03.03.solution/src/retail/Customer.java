package retail;

import java.io.IOException;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;


/**
 * 
 * @author Education
 *
 */
// TODO: Must implement PortableObject
public class Customer implements PortableObject, Entity <Long> {

	private Long id;
	private String name; 
	private Address address; 
	
	private static final long serialVersionUID = 1L;

	// TODO: Must include a no=argument constructor
	public Customer() {	}
		
	public Customer(Long id, String name, Address address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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



	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Customer:\n");
		sb.append("\tID:").append(id).append("\n");
		sb.append("\tName:").append(name).append("\n");
		if (address != null)
			sb.append(address.toString()).append("\n");
		else
			sb.append("Address: null").append("\n");
		return sb.toString();
	}


 
	public static int ID_INDEX 		= 0;
	public static int NAME_INDEX 	= 1;
	public static int ADDRESS_INDEX = 2;
	
	@Override
	public void readExternal(PofReader in) throws IOException {

		id = in.readLong(ID_INDEX);
		name = in.readString(NAME_INDEX);
		address = (Address)in.readObject(ADDRESS_INDEX);
	}

	@Override
	public void writeExternal(PofWriter out) throws IOException {
		out.writeLong(ID_INDEX, id.longValue());
		out.writeString(NAME_INDEX,name);
		out.writeObject(ADDRESS_INDEX, address);
	}


}
