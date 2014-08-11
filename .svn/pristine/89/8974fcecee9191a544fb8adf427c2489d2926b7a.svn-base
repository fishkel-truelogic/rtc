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

import ar.com.finit.dto.model.UserDTO;
import ar.com.finit.dto.model.factory.UserDTOFactory;
/**
 * @author usuario
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/rtc-dto-config.xml" })
public class UserJsonTest {

	private static final String ONE_USER = "" + "{\"id\":\"01234567-0123-0123-0123-0123456789a1\",\"" + "username\":\"Role name 1\",\"" + "password\":\"Password 1\",\""
			+ "urlRoles\":\"/roles?user=01234567-0123-0123-0123-0123456789a1\"" + "}";

	private static final String TWO_USERS = "[" + "{\"" + "id\":\"01234567-0123-0123-0123-0123456789a1\",\"" + "username\":\"Role name 1\",\"" + "password\":\"Password 1\",\""
			+ "urlRoles\":\"/roles?user=01234567-0123-0123-0123-0123456789a1\"" + "},{\"" + "id\":\"01234567-0123-0123-0123-0123456789a2\",\"" + "username\":\"Role name 2\",\""
			+ "password\":\"Password 2\",\"" + "urlRoles\":\"/roles?user=01234567-0123-0123-0123-0123456789a2\"" + "}]";

	public static final UserDTO user1 = makeUser(1);
	public static final UserDTO user2 = makeUser(2);

	@Autowired
	private UserDTOFactory userDTOFactory;

	@Test
	public void toJson() {
		assertEquals(ONE_USER, userDTOFactory.toJson(user1));

		Collection<UserDTO> users = new ArrayList<UserDTO>();
		users.add(user1);
		users.add(user2);

		assertEquals(TWO_USERS, userDTOFactory.toJson(users));
	}

	@Test
	public void fromJson() {
		assertEquals(userDTOFactory.makeDTO(ONE_USER), user1);

		Collection<UserDTO> users_fj = userDTOFactory.makeDTOs(TWO_USERS);

		Collection<UserDTO> users = new ArrayList<UserDTO>();
		users.add(user1);
		users.add(user2);

		for (UserDTO userDTO : users) {
			assertTrue(users_fj.contains(userDTO));
		}
	}

	private static UserDTO makeUser(int i) {
		UserDTO user = new UserDTO();
		user.setId("01234567-0123-0123-0123-0123456789a" + i);
		user.setUsername("Role name " + i);
		user.setPassword("Password " + i);
		user.setUrlRoles("/roles?user=01234567-0123-0123-0123-0123456789a" + i);
		return user;
	}
}
