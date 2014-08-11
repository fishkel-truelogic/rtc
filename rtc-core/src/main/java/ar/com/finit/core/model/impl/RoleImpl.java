package ar.com.finit.core.model.impl;

import org.hibernate.validator.constraints.NotBlank;

import ar.com.finit.core.model.Role;

/**
 * @author leo
 */
public class RoleImpl implements Role {

	private Long id;
	@NotBlank
	private String name;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
