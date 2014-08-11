package ar.com.finit.core.model.dao.impl;

import java.io.Serializable;

import org.hibernate.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.finit.core.dao.impl.AbstractDao;
import ar.com.finit.core.model.Player;
import ar.com.finit.core.model.dao.PlayerDao;

/**
 * @author leo
 */
public class PlayerDaoImpl extends AbstractDao<Serializable, Player> implements PlayerDao {

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Player findByFbId(Serializable id) {
		String hql = String.format("select p from %s as p ", super.getPersistentType().getName());
		hql += " where p.fbId = :id ";
		Query q = super.getSession().createQuery(hql);
		q.setParameter("id", id);
		return (Player) q.uniqueResult();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Player login(String login, String password) {
		String hql = String.format("select p from %s as p ", super.getPersistentType().getName());
		hql += " where (p.username = :login or p.email = :login) and password = :pass ";
		Query q = super.getSession().createQuery(hql);
		q.setParameter("login", login);
		q.setParameter("pass", password);
		return (Player) q.uniqueResult();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean existsUsername(String username) {
		String hql = String.format("select p from %s as p ", super.getPersistentType().getName());
		hql += " where p.username = :username ";
		Query q = super.getSession().createQuery(hql);
		q.setParameter("username", username);
		return q.list().size() != 0;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Player findByEmail(String email) {
		String hql = String.format("select p from %s as p ", super.getPersistentType().getName());
		hql += " where p.email = :email ";
		Query q = super.getSession().createQuery(hql);
		q.setParameter("email", email);
		return (Player) q.uniqueResult();
	}

}