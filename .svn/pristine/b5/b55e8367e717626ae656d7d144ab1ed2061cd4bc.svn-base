package ar.com.finit.core.model.dao.impl;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.finit.core.dao.impl.AbstractDao;
import ar.com.finit.core.model.Field;
import ar.com.finit.core.model.Rate;
import ar.com.finit.core.model.Stablishment;
import ar.com.finit.core.model.dao.StablishmentDao;
import ar.com.finit.core.model.impl.FieldImpl;

/**
 * @author leo
 */
public class StablishmentDaoImpl extends AbstractDao<Serializable, Stablishment> implements StablishmentDao {

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Stablishment findByUserId(String userId) {
		String hql = String.format("select s from %s as s where 1 = 1 ", this.getPersistentType().getName());
		hql += " and s.userId = :userId";
		Query q = this.getSession().createQuery(hql);
		q.setParameter("userId", userId);
		return (Stablishment) q.uniqueResult();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public long rowCountBy(String state, String district, String sport, String ground, String day, String hour) {
		String hql = String.format("select count(distinct s) from %s as s ", this.getPersistentType().getName());
		hql += " left join s.district as d ";
		hql += " left join d.state as st ";
		hql += " left join s.fields as f ";
		hql += " left join f.sports as sp ";
		hql += this.addParameter(state, district, sport, ground, day, hour);
		Query q = this.getSession().createQuery(hql);
		this.setParameter(q, state, district, sport, ground, day, hour);
		return (Long) q.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Stablishment> findBy(int first, int pageSize, String state, String district, String sport, String ground, String day, String hour) {
		String hql = String.format("select distinct s from %s as s ", this.getPersistentType().getName());
		hql += " left join s.district as d ";
		hql += " left join d.state as st ";
		hql += " left join s.fields as f ";
		hql += " left join f.sports as sp ";
		hql += this.addParameter(state, district, sport, ground, day, hour);
		Query q = this.getSession().createQuery(hql);
		this.setParameter(q, state, district, sport, ground, day, hour);
		if (first != -1) q.setFirstResult(first).setMaxResults(pageSize);
		return q.list();
	}
	

	private String addParameter(String state, String district, String sport, String ground, String day, String hour) {
		String hql = "";
		hql = " where 1 = 1 ";
		if (district != null && !district.isEmpty()) hql += " and d.name = :district ";
		if (state != null && !state.isEmpty()) hql += " and st.name = :state ";
		if (sport != null && !sport.isEmpty()) hql += " and sp.name = :sport ";
		if (ground != null && !ground.isEmpty()) hql += " and f.ground = :ground ";
		if (day != null && !day.isEmpty() && hour != null && !hour.isEmpty()) {
			hql += " and f.id not in (select distinct e.entity from ar.com.finit.core.model.impl.EventImpl as e where :date >= e.start_date and :date < e.end_date)";
		}
		return hql;
	}

	private void setParameter(Query q, String state, String district, String sport, String ground, String day, String hour) {
		if (district != null && !district.isEmpty()) q.setParameter("district", district);
		if (state != null && !state.isEmpty()) q.setParameter("state", state);
		if (sport != null && !sport.isEmpty()) q.setParameter("sport", sport);
		if (ground != null && !ground.isEmpty()) q.setParameter("ground", ground);
		if (day != null && !day.isEmpty()) {
			if (hour != null && !hour.isEmpty()) {
				String pattern = "dd/MM/yyyy HH:mm";
				SimpleDateFormat format = new SimpleDateFormat(pattern);
				try {
					q.setParameter("date", format.parse(day + " " + hour));
				} catch (HibernateException e) {
					throw new RuntimeException(e);
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}
			}
		}
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Rate findRateByEntity(String entity) {
		String hql = String.format("select s.rate from %s as s ", this.getPersistentType().getName());
		hql += " left join s.fields as f ";
		hql += " left join s.rate as r ";
		hql += " where r.entity = :entity and f.id = r.entity and s.userId = f.userId ";
		Query q = this.getSession().createQuery(hql);
		q.setParameter("entity", entity);
		return (Rate) q.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Field> findFeaturedBy(String state, String sport) {
		Criteria criteria = getSession().createCriteria(FieldImpl.class);
		if (sport != null && !sport.isEmpty()) {
			criteria.createAlias("sports", "sports");
			criteria.add(Restrictions.eq("sports.name", sport));
		}
		criteria.createAlias("rate", "rate");
		criteria.addOrder(Order.desc("rate.average")).addOrder(Order.desc("rate.votes"));
		criteria.setMaxResults(3);
		return criteria.list();
	}

}
