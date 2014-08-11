package ar.com.finit.core.model.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import ar.com.finit.core.dao.Dao;
import ar.com.finit.core.model.Role;
import ar.com.finit.core.model.User;

/**
 * @author leo
 */
public interface RoleDao extends Dao<Serializable, Role> {

	public Set<Role> findByUser(User user);

	public long countWithFilter(String name);

	public Collection<Role> findBy(String name, int first, int maxResult);

}
