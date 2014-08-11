package ar.com.finit.core.model.dao;

import java.io.Serializable;
import java.util.List;

import ar.com.finit.core.dao.Dao;
import ar.com.finit.core.model.User;

/**
 * @author leo
 */
public interface UserDao extends Dao<Serializable, User> {

	public List<User> findBy(String username, int first, int maxResult);

	public long countWithFilter(String username);

	public User initialize(User user);

	public User findByUsername(String username);
}
