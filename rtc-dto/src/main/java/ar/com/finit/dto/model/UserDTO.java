package ar.com.finit.dto.model;

import java.util.LinkedHashSet;
import java.util.Set;

import ar.com.finit.dto.helper.EqualsHelper;

/**
 * @author leo
 */
public class UserDTO {

	private String id;

	private String username;

	private String password;

	private boolean enabled;

	private Set<RoleDTO> roles;

	private String urlRoles;

	
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<RoleDTO> getRoles() {
		if (roles == null) roles = new LinkedHashSet<RoleDTO>();
		return roles;
	}

	public void setRoles(Set<RoleDTO> roles) {
		this.roles = roles;
	}

	public String getUrlRoles() {
		return urlRoles;
	}

	public void setUrlRoles(String urlRoles) {
		this.urlRoles = urlRoles;
	}

	public void addRole(RoleDTO role) {
		if (this.roles == null) {
			this.roles = new LinkedHashSet<RoleDTO>();
		}
		this.roles.add(role);
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		if (!(other instanceof UserDTO)) {
			return false;
		}
		UserDTO otherUser = (UserDTO) other;
		return EqualsHelper.equals(otherUser.getId(), this.getId()) && EqualsHelper.equals(otherUser.getUsername(), this.getUsername())
				&& EqualsHelper.equals(otherUser.getPassword(), this.getPassword()) && EqualsHelper.equalsCollection(otherUser.getRoles(), this.getRoles());
	}

}