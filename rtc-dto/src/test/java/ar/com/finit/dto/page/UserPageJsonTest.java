package ar.com.finit.dto.page;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.finit.dto.UserJsonTest;
import ar.com.finit.dto.model.UserDTO;
import ar.com.finit.dto.model.factory.UserCollectionPageFactory;
import ar.com.finit.dto.model.page.UserCollectionPage;

/**
 * @author leo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/rtc-dto-config.xml" })
public class UserPageJsonTest {
	private static final String ONE_PAGE = "{\"" + "rowCount\":2,\"" + "pageSize\":3,\"" + "pageNumber\":1,\"" + "pageElements\":" + "[{\""
			+ "id\":\"01234567-0123-0123-0123-0123456789a1\",\"" + "username\":\"Role name 1\",\"" + "password\":\"Password 1\",\""
			+ "urlRoles\":\"/roles?user=01234567-0123-0123-0123-0123456789a1\"" + "},{\"" + "id\":\"01234567-0123-0123-0123-0123456789a2\",\"" + "username\":\"Role name 2\",\""
			+ "password\":\"Password 2\",\"" + "urlRoles\":\"/roles?user=01234567-0123-0123-0123-0123456789a2\"" + "}]}";

	private static final UserCollectionPage userCollectionPage = makeDCP();

	@Autowired
	private UserCollectionPageFactory userCollectionPageFactory;

	@Test
	public void toJson() {
		assertEquals(ONE_PAGE, userCollectionPageFactory.toJson(userCollectionPage));
	}

	@Test
	public void fromJson() {
		assertEquals(userCollectionPage, userCollectionPageFactory.makeDTO(ONE_PAGE));
	}

	private static UserCollectionPage makeDCP() {
		Collection<UserDTO> dtos = new ArrayList<UserDTO>();
		dtos.add(UserJsonTest.user1);
		dtos.add(UserJsonTest.user2);
		return new UserCollectionPage(2, 3, 1, 1, dtos);
	}
}