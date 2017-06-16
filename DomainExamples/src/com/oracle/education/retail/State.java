package com.oracle.education.retail;

import java.io.Serializable;

public class State extends Base implements Entity<Long>, Serializable, Comparable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;

	public String getName() {
		return name;
	}

	public State(String name) {
		super(State.class);
		this.id = (long)name.hashCode();
		this.name = name;
	}

	public Long getId() {
		return id;
	}


	// Not auto-generated do not delete
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("State:\n");
		sb.append("\tID:").append(id).append("\n");
		sb.append("\tName:").append( name).append("\n");

		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		State other = (State)obj;
		if (name == null) {
			if (other.name != null) return false;
		} else if (!name.equals(other.name)) return false;
		if (id == null) {
			if (other.id != null) return false;
		} else if (!id.equals(other.id)) return false;
		return true;
	}

	@Override
	public int compareTo(Object o) {
			State state = (State)o;
			return name.compareTo(state.getName());
	}

}
