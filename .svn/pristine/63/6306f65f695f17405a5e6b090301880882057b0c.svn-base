package ar.com.finit.core.model.dao.impl;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.finit.core.dao.impl.AbstractDao;
import ar.com.finit.core.model.Event;
import ar.com.finit.core.model.dao.EventDao;
import ar.com.finit.core.model.impl.EventImpl;

/**
 * @author leo
 */
public class EventDaoImpl extends AbstractDao<Serializable, Event> implements EventDao {

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Event> findBy(String userId, String entity, String start, String end, String status) throws ParseException {
		Criteria criteria = getSession().createCriteria(getPersistentType(), "e");
		if (entity != null && !entity.isEmpty()) {
			criteria.createAlias("e.field", "f"); 
			criteria.add(Restrictions.eq("f.id", entity));
		}
		if (userId != null && !userId.isEmpty()) {
			criteria.createAlias("e.user", "u"); 
			criteria.add(Restrictions.eq("u.id", userId));
		}
		if (status != null) {
			if (status.equals("0")) {
				criteria.add(Restrictions.disjunction()
				        .add(Restrictions.eq("status", 1))
				        .add(Restrictions.eq("status", 5))
				        .add(Restrictions.eq("status", 6)));
			} else {
				criteria.add(Restrictions.disjunction()
				        .add(Restrictions.eq("status", 0))
				        .add(Restrictions.eq("status", 2))
				        .add(Restrictions.eq("status", 3))
				        .add(Restrictions.eq("status", 4)));
			}
		}
		if (start != null && end != null) {
			String pattern  = "dd/MM/yyyy HH:mm";
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			criteria.add(Restrictions.between("start_date", format.parse(start), format.parse(end)));
		}
		criteria.addOrder(Order.desc("start_date"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Event> findBy(String entity, String start, String end) throws ParseException {
		Criteria criteria = getSession().createCriteria(getPersistentType(), "e");
		if (entity != null && !entity.isEmpty()) {
			criteria.createAlias("e.field", "f"); 
			criteria.add(Restrictions.eq("f.id", entity));
		}
		if (start != null && end != null) {
			String pattern  = "dd/MM/yyyy HH:mm";
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			criteria.add(Restrictions.between("start_date", format.parse(start), format.parse(end)));
		}
		criteria.addOrder(Order.desc("start_date"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Event> findPlayer(String id, int status) {
		Criteria c = getSession().createCriteria(getPersistentType(), "e");
		c.createAlias("e.player", "p"); 
		c.add(Restrictions.eq("e.status", status));
		c.add(Restrictions.eq("p.id", id));
		return c.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Event> findByStatus(int status) {
		Criteria c = getSession().createCriteria(EventImpl.class, "e");
		c.add(Restrictions.eq("e.status", status));
		return c.list();
	}

}
