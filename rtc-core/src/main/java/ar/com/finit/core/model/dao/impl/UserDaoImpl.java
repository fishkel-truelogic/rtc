package ar.com.finit.core.model.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.finit.core.dao.impl.AbstractDao;
import ar.com.finit.core.model.Role;
import ar.com.finit.core.model.User;
import ar.com.finit.core.model.dao.UserDao;
import ar.com.finit.core.model.impl.UserImpl;

/**
 * @author leo
 */
public class UserDaoImpl extends AbstractDao<Serializable, User> implements UserDao {

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<User> findBy(String username, int first, int pageSize) {
		UserImpl user = new UserImpl();
		user.setUsername(username);

		String hql = String.format("select u from %s as u ", this.getPersistentType().getName().toString());
		hql += " where 1 = 1 " + this.addParameterFilter(user, "u.");
		
		Collection<Object> objects = new HashSet<Object>();
		objects.add(user);
		
		Query q = this.createQuery(objects, hql);
		if (first != -1) {
			q.setFirstResult(first);
			q.setMaxResults(pageSize);
		}
		
		return q.list();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public long countWithFilter(String username) {
		UserImpl user = new UserImpl();
		user.setUsername(username);

		String hql = String.format("select count(*) from %s as u ", this.getPersistentType().getName().toString());
		hql += " where 1 = 1 " + this.addParameterFilter(user, "u.");
		
		Collection<Object> objects = new HashSet<Object>();
		objects.add(user);
		
		return (Long) this.createQuery(objects, hql).uniqueResult();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public User initialize(User user) {
		UserImpl userImpl = (UserImpl) this.findById(user.getId());
		Set<Role> roles = new HashSet<Role>();
		for (Role role : userImpl.getRoles()) {
			roles.add(role);
		}
		userImpl.setRoles(roles);
		return userImpl;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public User findByUsername(String username) {
		String hql = String.format("select u from %s as u ", this.getPersistentType().getName().toString());
		hql += " where 1 = 1 and u.username = :username ";
		Query q = this.getSession().createQuery(hql);
		q.setParameter("username", username);
		return (User) q.uniqueResult();
	}

}
