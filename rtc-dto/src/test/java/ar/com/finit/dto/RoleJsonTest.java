package ar.com.finit.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.finit.dto.model.RoleDTO;
import ar.com.finit.dto.model.factory.RoleDTOFactory;

/**
 * @author usuario
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/rtc-dto-config.xml" })
public class RoleJsonTest {
	private static final String ONE_ROLE = "{\"id\":1,\"name\":\"Role name 1\"}";
	private static final String TWO_ROLES = "[{\"id\":1,\"name\":\"Role name 1\"},{\"id\":2,\"name\":\"Role name 2\"}]";

	public static final RoleDTO role1 = makeRole(1);
	public static final RoleDTO role2 = makeRole(2);

	@Autowired
	private RoleDTOFactory roleDTOFactory;

	@Test
	public void toJson() {
		assertEquals(ONE_ROLE, roleDTOFactory.toJson(role1));

		Collection<RoleDTO> roles = new ArrayList<RoleDTO>();
		roles.add(role1);
		roles.add(role2);

		assertEquals(TWO_ROLES, roleDTOFactory.toJson(roles));
	}

	@Test
	public void fromJson() {
		assertEquals(roleDTOFactory.makeDTO(ONE_ROLE), role1);

		Collection<RoleDTO> roles_fj = roleDTOFactory.makeDTOs(TWO_ROLES);

		Collection<RoleDTO> roles = new ArrayList<RoleDTO>();
		roles.add(role1);
		roles.add(role2);

		for (RoleDTO roleDTO : roles) {
			assertTrue(roles_fj.contains(roleDTO));
		}
	}

	private static RoleDTO makeRole(int number) {
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setId((long) number);
		roleDTO.setName("Role name " + number);
		return roleDTO;
	}
}