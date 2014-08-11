package ar.com.finit.core.test.model;

import java.util.List;
import java.util.Set;

import org.hibernate.collection.internal.PersistentSet;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.finit.core.model.Role;
import ar.com.finit.core.model.User;
import ar.com.finit.core.model.dao.RoleDao;
import ar.com.finit.core.model.dao.UserDao;
import ar.com.finit.core.model.impl.RoleImpl;
import ar.com.finit.core.model.impl.UserImpl;
/**
 * @author leo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:rtc-core-config-test.xml" })
public class RoleTest {

	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	
	
	@Test
	public void persistence() {
		
		RoleImpl role = new RoleImpl();
		role.setName("admin");
		roleDao.save(role);
		
		Assert.assertTrue(role.getId() != 0);
		
		role.setName("owner");
		roleDao.update(role);
		Assert.assertEquals(roleDao.findById(role.getId()).getName(), "owner");
		
	}
	
	@Test
	public void lazyEager() {
		List<User> users = userDao.findAll();
		if (users.isEmpty()) {
			UserImpl user = new UserImpl();
			user.setUsername("tomcat");
			user.setPassword("tomcat");
			
			RoleImpl role = new RoleImpl();
			role.setName("user");
			roleDao.save(role);
			user.addRole(role);
			userDao.save(user);
			users = userDao.findAll();
		}
		
		Assert.assertEquals(users.get(0).getRoles() instanceof PersistentSet, true);
		((UserImpl) users.get(0)).setRoles(roleDao.findByUser(users.get(0)));
		Assert.assertEquals(users.get(0).getRoles() instanceof Set<?>, true);
		
		
	}
	
	@Test
	public void delete() {
		RoleImpl role = new RoleImpl();
		role.setName("asdasd");
		roleDao.save(role);
		
		Assert.assertNotNull(role.getId());
		Long id = role.getId();
		roleDao.remove(role);
		
		Role role2 = roleDao.findById(id);
		Assert.assertNull(role2);
		
	}
	
	@Test
	public void pagination() {
		List<Role> roles = roleDao.findAll();
		if (!(roles.isEmpty())) {
			this.delete();
		} 
		
		for (char i = 'a'; i<='z'; i++) {
			RoleImpl role = new RoleImpl();
			switch (i) {
			case 'b':
				role.setName("" + i + 'a');
				break;
			case 'o':
				role.setName("" + i + 'a');
				break;
			default:
				role.setName("" + i);
				break;
			}
			roleDao.save(role);
		}
		
		roles = roleDao.findAll();
		Assert.assertEquals(3, roleDao.countWithFilter("a"));
		Assert.assertEquals(1, roleDao.countWithFilter("oa"));
		
		Assert.assertEquals(10, roleDao.findBy(null, 0, 10).size());
		Assert.assertEquals(2, roleDao.findBy("a", 0, 2).size());
	}
}
