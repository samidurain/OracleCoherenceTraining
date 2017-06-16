package retail;

import com.tangosol.io.ExternalizableLite;
import com.tangosol.util.ExternalizableHelper;
import java.io.DataInput;
import java.io.IOException;
import java.io.DataOutput;


/**
 * 
 * @author Education
 *
 */
// TODO: Must implement ExternalizableLite
public class Customer implements ExternalizableLite, Entity <Long> {

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


    // TODO: Must include a readExternal methods/
	// which reads fields in the same order as written
	public void readExternal(DataInput in) throws IOException {
		id = new Long(in.readLong());
		name = ExternalizableHelper.readSafeUTF(in);
		address = (Address) ExternalizableHelper.readObject(in);
	}

	// TODO: Must include a writeExternal method
	// which writes fields in the same order read
	public void writeExternal(DataOutput out) throws IOException {
		out.writeLong(id.longValue());
		ExternalizableHelper.writeSafeUTF(out, name);
		ExternalizableHelper.writeObject(out, address);
	}


}
