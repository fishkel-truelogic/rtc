package ar.com.finit.core.model.dao.impl;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.finit.core.dao.impl.AbstractDao;
import ar.com.finit.core.model.MapMarker;
import ar.com.finit.core.model.dao.MapMarkerDao;
import ar.com.finit.core.model.dao.StablishmentDao;

/**
 * @author leo
 */
public class MapMarkerDaoImpl extends AbstractDao<Serializable, MapMarker> implements MapMarkerDao {

	@Autowired
	private StablishmentDao stablishmentDao;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<MapMarker> findBy(String state, String district, String sport, String ground, String day, String hour) {
		String hql = String.format("select m from %s as m ", this.getPersistentType().getName());
		hql += " where m.entity in (select distinct s.id from ar.com.finit.core.model.impl.StablishmentImpl as s ";
		hql += " left join s.district as d ";
		hql += " left join d.state as st ";
		hql += " left join s.fields as f ";
		hql += " left join f.sports as sp ";
		hql += this.addParameter(state, district, sport, ground, day, hour) + ")";
		Query q = this.getSession().createQuery(hql);
		this.setParameter(q, state, district, sport, ground, day, hour);
		return q.list();
	}
	
	private String addParameter(String state, String district, String sport, String ground, String day, String hour) {
		String hql = "";
		hql = " where m.entity = s.id  ";
		if (district != null && !district.isEmpty()) hql += " and d.name = :district ";
		if (state != null && !state.isEmpty()) hql += " and st.name = :state ";
		if (sport != null && !sport.isEmpty()) hql += " and sp.name = :sport ";
		if (ground != null && !ground.isEmpty()) hql += " and f.ground = :ground ";
		if (day != null && !day.isEmpty() && hour != null && !hour.isEmpty()) {
			hql += " and f.id not in (select distinct e.entity from ar.com.finit.core.model.impl.EventImpl as e where :date >= e.startTime and :date < e.endTime)";
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
					q.setParameter("date",format.parse(day + " " + hour));
				} catch (HibernateException e) {
					throw new RuntimeException(e);
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

}