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
public class UserTest {

	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	
	
	@Test
	public void persistence() {
		UserImpl user = new UserImpl();
		user.setUsername("tomcat");
		user.setPassword("tomcat");
		
		RoleImpl role = new RoleImpl();
		role.setName("user");
		roleDao.save(role);
		
		user.addRole(role);
		
		userDao.save(user);
		Assert.assertNotNull(user.getId());
		
		List<User> users = userDao.findAll();
		Assert.assertEquals(users.get(0).getUsername(), "tomcat");
		
		user.setPassword("jboss");
		userDao.update(user);
		Assert.assertEquals(userDao.findById(user.getId()).getPassword(), "jboss");
		
	}
	
	@Test
	public void lazyEager() {
		List<User> users = userDao.findAll();
		if (users.isEmpty()) {
			this.persistence();
			users = userDao.findAll();
		}
		
		Assert.assertEquals(users.get(0).getRoles() instanceof PersistentSet, true);
		
		userDao.initialize(users.get(0));
		Assert.assertEquals(users.get(0).getRoles() instanceof Set<?>, true);
		
	}
	
	@Test
	public void delete() {
		List<User> users = userDao.findAll();
		if (users.isEmpty()) {
			this.persistence();
			users = userDao.findAll();
		}
		
		for (User user : users) {
			userDao.remove(user);
		}
		
		Assert.assertEquals(0, userDao.findAll().size());
		
	}
	
	@Test
	public void pagination() {
		List<User> users = userDao.findAll();
		if (!(users.isEmpty())) {
			this.delete();
		} 
		
		for (char i = 'a'; i<='z'; i++) {
			UserImpl user = new UserImpl();
			switch (i) {
			case 'b':
				user.setUsername("" + i + 'a');
				user.setPassword("" + i);
				break;
			case 'o':
				user.setUsername("" + i + 'a');
				user.setPassword("" + i);
				break;
			default:
				user.setUsername("" + i);
				user.setPassword("" + i);
				break;
			}
			userDao.save(user);
		}
		
		users = userDao.findAll();
		Assert.assertEquals(26, userDao.countWithFilter(null));
		Assert.assertEquals(3, userDao.countWithFilter("a"));
		Assert.assertEquals(1, userDao.countWithFilter("oa"));
		
		Assert.assertEquals(10, userDao.findBy(null, 0, 10).size());
		Assert.assertEquals(2, userDao.findBy("a", 0, 2).size());
	}

}
