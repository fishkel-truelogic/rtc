package ar.com.finit.core.model.dao;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

import ar.com.finit.core.dao.Dao;
import ar.com.finit.core.model.Event;

/**
 * @author leo
 */
public interface EventDao extends Dao<Serializable, Event> {

	public List<Event> findBy(String userId, String entity, String start, String end, String status) throws ParseException;

	public List<Event> findBy(String entity, String start, String end) throws ParseException;

	public List<Event> findPlayer(String id, int status);

	public List<Event> findByStatus(int status);

}
