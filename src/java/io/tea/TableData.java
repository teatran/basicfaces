package io.tea;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.util.ArrayList;
import java.util.Arrays;


@ManagedBean
@SessionScoped
public class TableData implements Serializable{
	private static final long serialVersionUID = 465463934442231477L;
	
	private ArrayList<Name> names = new ArrayList<Name>(Arrays.asList(
			new Name("Ronaldinho", "Gaucho"),
			new Name("Messi", "Leo"),
			new Name("Scholes", "Paul"),
			new Name("Pirlo", "Andre")
	));
	
	public ArrayList<Name> getNames() { return names; }
	
	public String deleteRow(Name nameToDelete) {
		names.remove(nameToDelete);
		return "abc";
	}
	
}
