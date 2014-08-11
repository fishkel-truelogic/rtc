package ar.com.finit.service.model.converter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import ar.com.finit.core.model.Role;
import ar.com.finit.core.model.impl.RoleImpl;
import ar.com.finit.dto.model.RoleDTO;

/**
 * @author leo
 */
public class RoleConverter {

	public Set<RoleDTO> toDTO(Collection<Role> roles) {
		Set<RoleDTO> dtos = new HashSet<RoleDTO>();
		for (Role role : roles) {
			dtos.add(toDTO(role));
		}
		return dtos;
	}

	public Set<Role> toEntity(Set<RoleDTO> roles) {
		Set<Role> entities = new HashSet<Role>();
		for (RoleDTO roleDTO : roles) {
			entities.add(toEntity(roleDTO));
		}
		return entities;
	}

	public RoleDTO toDTO(Role role) {
		if (role == null) {
			return null;
		}
		RoleDTO dto = new RoleDTO();
		dto.setId(role.getId());
		dto.setName(role.getName());
		return dto;
	}

	public Role toEntity(RoleDTO dto) {
		RoleImpl role = new RoleImpl();
		role.setId(dto.getId());
		role.setName(dto.getName());
		return role;
	}
}