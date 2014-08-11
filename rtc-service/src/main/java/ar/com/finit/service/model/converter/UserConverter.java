package ar.com.finit.service.model.converter;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.finit.core.model.User;
import ar.com.finit.core.model.impl.UserImpl;
import ar.com.finit.dto.model.UserDTO;

/**
 * @author leo
 */
public class UserConverter {

	@Autowired
	private RoleConverter roleConverter;

	public Collection<UserDTO> toDTO(Collection<User> users) {
		Collection<UserDTO> dtos = new HashSet<UserDTO>();
		for (User user : users) {
			dtos.add(this.toDTO(user));
		}
		return dtos;
	}

	public UserDTO toDTO(User user) {
		if (user == null) {
			return null;
		}
		UserDTO dto = new UserDTO();
		dto.setRoles(roleConverter.toDTO(user.getRoles()));
		dto.setId(user.getId());
		dto.setUsername(user.getUsername());
		dto.setEnabled(user.isEnabled());
		dto.setPassword(user.getPassword());
		return dto;
	}

	public User toEntity(UserDTO userDto) {
		UserImpl user = new UserImpl();
		user.setId(userDto.getId());
		user.setUsername(userDto.getUsername());
		user.setEnabled(userDto.isEnabled());
		user.setPassword(userDto.getPassword());
		user.setRoles(roleConverter.toEntity(userDto.getRoles()));
		return user;
	}
}