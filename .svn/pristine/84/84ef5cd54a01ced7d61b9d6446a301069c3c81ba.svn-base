package ar.com.finit.core.model.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.finit.core.dao.impl.AbstractDao;
import ar.com.finit.core.model.TpService;
import ar.com.finit.core.model.dao.AlbumDao;
import ar.com.finit.core.model.dao.TpServiceDao;

/**
 * @author leo
 */
public class TpServiceDaoImpl extends AbstractDao<Serializable, TpService> implements TpServiceDao {


	@Autowired
	private AlbumDao albumDao;
	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<TpService> findByUserId(String userId) {
		String hql = String.format("select t from %s as t where 1 = 1 ", this.getPersistentType().getName());
		hql += " and t.userId = :userId";
		Query q = this.getSession().createQuery(hql);
		q.setParameter("userId", userId);
		return q.list();
	}
}
