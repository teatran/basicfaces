package io.tea;

import java.io.Serializable;

public class Name implements Serializable {
	private static final long serialVersionUID = 761644615081075129L;

	private String first;
	private String last;
	
	public Name(String first, String last) {
		this.first = first;
		this.last = last;
	}
	
	public String getFirst() { return first; }
	public void setFirst(String newValue) { first = newValue; }
	
	public String getLast() { return last; }
	public void setLast(String newValue) { last = newValue; }
}
