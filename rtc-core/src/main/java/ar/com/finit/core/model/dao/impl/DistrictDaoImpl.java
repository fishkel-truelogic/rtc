package ar.com.finit.core.model.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.finit.core.dao.impl.AbstractDao;
import ar.com.finit.core.model.District;
import ar.com.finit.core.model.dao.DistrictDao;

/**
 * @author leo
 */
public class DistrictDaoImpl extends AbstractDao<Serializable, District> implements DistrictDao {

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public List<District> findByState(String state) {
		if (state != null && !state.isEmpty()) {
			Criteria criteria = this.getSession().createCriteria(this.getPersistentType(), "d");
			criteria.createAlias("d.state", "s");
			criteria.add(Restrictions.eq("s.name", state));
			criteria.addOrder(Order.asc("d.name"));
			return criteria.list();
		}else {
			return null;
		}
	}
}
