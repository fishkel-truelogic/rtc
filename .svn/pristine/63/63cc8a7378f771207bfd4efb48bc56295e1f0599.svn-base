package ar.com.finit.core.model.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.finit.core.dao.impl.AbstractDao;
import ar.com.finit.core.model.Role;
import ar.com.finit.core.model.User;
import ar.com.finit.core.model.dao.RoleDao;
import ar.com.finit.core.model.dao.UserDao;
import ar.com.finit.core.model.impl.RoleImpl;

/**
 * @author leo
 */
public class RoleDaoImpl extends AbstractDao<Serializable, Role> implements RoleDao {

	@Autowired
	private UserDao userDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Set<Role> findByUser(User user) {
		Set<Role> roles = new HashSet<Role>();
		for (Role role : userDao.findById(user.getId()).getRoles()) {
			roles.add(role);
		}
		return roles;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public long countWithFilter(String name) {
		RoleImpl role = new RoleImpl();
		role.setName(name);

		String hql = String.format("select count(*) from %s as r ", this.getPersistentType().getName().toString());
		hql += " where 1 = 1 " + this.addParameterFilter(role, "r.");
		
		Collection<Object> objects = new HashSet<Object>();
		objects.add(role);
		
		return (Long) this.createQuery(objects, hql).uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public Collection<Role> findBy(String name, int first, int maxResult) {
		RoleImpl role = new RoleImpl();
		role.setName(name);

		String hql = String.format("select r from %s as r ", this.getPersistentType().getName().toString());
		hql += " where 1 = 1 " + this.addParameterFilter(role, "r.");
		
		Collection<Object> objects = new HashSet<Object>();
		objects.add(role);
		
		Query q = this.createQuery(objects, hql);
		if (first != -1) {
			q.setFirstResult(first);
			q.setMaxResults(maxResult);
		}
		return q.list();
	}

}
