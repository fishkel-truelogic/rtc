package ar.com.finit.dto.model;

import ar.com.finit.dto.helper.EqualsHelper;

/**
 * @author leo
 */
public class RoleDTO {

	private Long id;

	private String name;

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

	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		if (!(other instanceof RoleDTO)) {
			return false;
		}
		RoleDTO otherRole = (RoleDTO) other;
		return EqualsHelper.equals(otherRole.getName(), this.getName()) && EqualsHelper.equals(otherRole.getId(), this.getId());
	}
}
