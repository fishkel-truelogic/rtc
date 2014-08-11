package ar.com.finit.dto.page;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.finit.dto.RoleJsonTest;
import ar.com.finit.dto.model.RoleDTO;
import ar.com.finit.dto.model.factory.RoleCollectionPageFactory;
import ar.com.finit.dto.model.page.RoleCollectionPage;

/**
 * @author leo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/rtc-dto-config.xml" })
public class RolePageJsonTest {
	private static final String ONE_PAGE = "{\"" + "rowCount\":2,\"" + "pageSize\":5,\"" + "pageNumber\":1,\"" + "pageElements\":[{\"" + "id\":1,\"" + "name\":\"Role name 1\""
			+ "},{\"" + "id\":2,\"" + "name\":\"Role name 2\"" + "}]}";
	private static final RoleCollectionPage roleCollectionPage = makeZCP();

	@Autowired
	private RoleCollectionPageFactory roleCollectionPageFactory;

	@Test
	public void toJson() {
		assertEquals(ONE_PAGE, roleCollectionPageFactory.toJson(roleCollectionPage));
	}

	@Test
	public void fromJson() {
		assertEquals(roleCollectionPage, roleCollectionPageFactory.makeDTO(ONE_PAGE));
	}

	private static RoleCollectionPage makeZCP() {
		Collection<RoleDTO> dtos = new ArrayList<RoleDTO>();
		dtos.add(RoleJsonTest.role1);
		dtos.add(RoleJsonTest.role2);
		return new RoleCollectionPage(2, 5, 1, 1, dtos);
	}
}