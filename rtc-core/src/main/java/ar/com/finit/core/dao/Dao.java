package ar.com.finit.core.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

/**
 * @author leo
 * 
 * @param <K>
 * @param <T>
 */
public interface Dao<K extends Serializable, T> {

	public T findById(Serializable id);

	public List<T> findAll();

	public void save(T object);

	public void remove(T object);

	public Session getSession();

	public void update(T object);
	
}