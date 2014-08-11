package ar.com.finit.core.model.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.finit.core.dao.impl.AbstractDao;
import ar.com.finit.core.model.Image;
import ar.com.finit.core.model.dao.ImageDao;

/**
 * @author leo
 */
public class ImageDaoImpl extends AbstractDao<Serializable, Image> implements ImageDao {

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Image> findByEntity(String entity) {
		String hql = String.format("select i from %s as i ", this.getPersistentType().getName());
		hql += " where i.entity = :entity and i.cover = false";
		Query q = this.getSession().createQuery(hql);
		q.setParameter("entity", entity);
		return q.list();
	}


}