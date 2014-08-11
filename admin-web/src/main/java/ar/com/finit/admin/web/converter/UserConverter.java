package ar.com.finit.admin.web.converter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ar.com.finit.admin.web.model.UserModel;
import ar.com.finit.dto.helper.PasswordHelper;
import ar.com.finit.dto.model.RoleDTO;
import ar.com.finit.dto.model.UserDTO;

/**
 * @author leo
 */
@Component
@Scope("application")
public class UserConverter {

	public UserDTO toDTO(UserModel user) {
		UserDTO userDto = new UserDTO();
		userDto.setUsername(user.getUsername());
		userDto.setEnabled(user.isEnabled());
		if (user.getRole0() != null) {
			RoleDTO role = new RoleDTO();
			role.setId(Long.parseLong(user.getRole0()));
			userDto.addRole(role);
		}
		if (user.getRole1() != null) {
			RoleDTO role = new RoleDTO();
			role.setId(Long.parseLong(user.getRole1()));
			userDto.addRole(role);
		}
		if (user.getRole2() != null) {
			RoleDTO role = new RoleDTO();
			role.setId(Long.parseLong(user.getRole2()));
			userDto.addRole(role);
		}
		userDto.setPassword(PasswordHelper.hashPassword(user.getPassword()));
		userDto.setId(user.getId());
		return userDto;
	}
}
