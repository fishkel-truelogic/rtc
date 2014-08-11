package ar.com.finit.admin.web.model;

/**
 * @author leo
 */
public class UserModel {
	
	private String id;
	
	private String username;
	
	private String role0;
	
	private String role1;
	
	private String role2;
	
	private String password;

	private boolean enabled;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole0() {
		return role0;
	}

	public void setRole0(String role0) {
		this.role0 = role0;
	}

	public String getRole1() {
		return role1;
	}

	public void setRole1(String role1) {
		this.role1 = role1;
	}

	public String getRole2() {
		return role2;
	}

	public void setRole2(String role2) {
		this.role2 = role2;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	

}
