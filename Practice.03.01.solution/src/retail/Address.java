package retail;

import java.io.Serializable;


/**
 * 
 * @author Education
 *
 */
public class Address implements  Serializable {

	
	private static final long serialVersionUID = 1L;
	private String street;
	private String city;
	private String state;
	private String zip;
	
	
	public String getStreet() {
		return street;
	}




	public void setStreet(String street) {
		this.street = street;
	}




	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}


	public String toString() {
		return toString(0);
	}

	private String tabs(int level) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i <=level; i++) {
			sb.append("\t");
		}
			
			return sb.toString();
	}
	public String toString(int level ) {
		StringBuffer sb = new StringBuffer();
		sb.append(tabs(level)).append("Address:\n");
		sb.append(tabs(level)).append("\tStreet:").append(street).append("\n");
		sb.append(tabs(level)).append("\tCity:").append(city).append("\n");
		sb.append(tabs(level)).append("\tState:").append(state).append("\n");
		sb.append(tabs(level)).append("\tZip:").append(zip).append("\n");
		
		
		return sb.toString();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + ((zip == null) ? 0 : zip.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		if (zip == null) {
			if (other.zip != null)
				return false;
		} else if (!zip.equals(other.zip))
			return false;
		return true;
	}




	public Address() {
		super();
	}
	public Address(String street, String city, String state, String zip) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	
}
