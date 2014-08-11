package ar.com.finit.core.model.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.finit.core.dao.impl.AbstractDao;
import ar.com.finit.core.model.Field;
import ar.com.finit.core.model.dao.AlbumDao;
import ar.com.finit.core.model.dao.FieldDao;

/**
 * @author leo
 */
public class FieldDaoImpl extends AbstractDao<Serializable, Field> implements FieldDao {

	@Autowired
	private AlbumDao albumDao;
	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Field> findByUserId(String userId) {
		String hql = String.format("select f from %s as f where 1 = 1 ", this.getPersistentType().getName());
		hql += " and f.userId = :userId";
		Query q = this.getSession().createQuery(hql);
		q.setParameter("userId", userId);
		return q.list();
	}


}