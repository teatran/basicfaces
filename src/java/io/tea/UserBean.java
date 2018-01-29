package io.tea;

import java.io.Serializable;

public class UserBean implements Serializable {

	private static final long serialVersionUID = -7966990465800948743L;

	private String name;
	private String password;
	
	public String getName() { return name; }
	public void setName(String n) { name = n; }
	
	public String getPassword() { return password; }
	public void setPassword(String p) { password = p; }
	
	public String checkAction() {
		if (password.trim() != "") 
			return "ask";
		return "index";
	}
}
