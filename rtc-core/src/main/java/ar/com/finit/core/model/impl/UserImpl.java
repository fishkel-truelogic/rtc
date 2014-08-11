package ar.com.finit.core.model.impl;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.NotBlank;

import ar.com.finit.core.model.Role;
import ar.com.finit.core.model.User;

/**
 * @author leo
 */
public class UserImpl implements User {

	private String id;
	@NotBlank
	private String username;
	@NotBlank
	private String password;
	private boolean enabled;
	private Set<Role> roles;

	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		if (roles == null) {
			roles = new HashSet<Role>();
		}
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void addRole(Role role) {
		this.getRoles().add(role);
	}

	public void removeRole(Role role) {
		this.getRoles().remove(role);
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	

}