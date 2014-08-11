package ar.com.finit.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.finit.core.dao.Dao;

/**
 * @author leo
 * 
 * @param <K>
 * @param <T>
 */
public class AbstractDao<K extends Serializable, T> implements Dao<K, T> {

	private SessionFactory sessionFactory;
	private Class<T> persistentType;

	@SuppressWarnings("unchecked")
	public AbstractDao() {
		ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.persistentType = (Class<T>) parameterizedType.getActualTypeArguments()[1];
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public T findById(Serializable id) {
		Query q = this.getSession().createQuery(String.format("select pe from %s as pe where pe.id = :id", persistentType.getName()));
		q.setParameter("id", id);
		return (T) q.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<T> findAll() {
		return this.getSession().createCriteria(this.persistentType).list();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(T object) {
		this.getSession().persist(object);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(T object) {
		this.getSession().update(object);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(T object) {
		this.getSession().delete(object);
	}

	@Override
	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	protected Class<T> getPersistentType() {
		return this.persistentType;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * @param obj
	 * @param prefix
	 * @return Devuelve un String que corresponde a la parte where del hql en
	 *         una consulta por filtros ejemplo:
	 *         "and d.name like :name and d.description". Este método se hizo en
	 *         base a la funcionalidad existente en los daos dónde se armaban
	 *         hql con like y %%. El String del ejemplo es resultado de ingresar
	 *         en el párametro prefix "d." (correspondiente al alias indicado en
	 *         la parte select del hql) y en el parámetro obj una instancia de
	 *         DestinationImpl con los campos name y descritpion no nulos. Por
	 *         reflection el proceso toma los campos de tipo String no nulos y
	 *         los agrega a la query. Cuando en la consulta intervienen mas de
	 *         una tabla (join) se debe llamar este método por cada una de ellas
	 *         para agregar los campos correspondientes a cada objeto
	 *         relacionado
	 * 
	 *         TODO: Mejorar la abstracción del metodo en casos futuros para que
	 *         arme una query pudiendo ponerse otros operadores (between, =,
	 *         etc).
	 * 
	 */
	protected String addParameterFilter(Object obj, String prefix) {
		String queryParam = "";
		for (Field field : obj.getClass().getDeclaredFields()) {
			try {
				field.setAccessible(true);
				Object value = field.get(obj);
				if (value != null && value instanceof String) {
					queryParam += " and ";
					queryParam += prefix + field.getName() + " like :" + field.getName();
				}
			} catch (IllegalArgumentException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
		return queryParam;
	}

	/**
	 * @param objects
	 * @param hql
	 * @return Devuelve un org.hibernate.Query creado a partir de un String hql
	 *         agregandole los valores a las variables indicadas como :variable
	 *         en la parte "where" de la query. En el parametro objects se debe
	 *         mandar una collection conteniendo los objetos cargados con los
	 *         campos no nulos que fueron ingresados en la query al ser llamado
	 *         el metodo addParameterFilter.
	 */
	protected Query createQuery(Collection<Object> objects, String hql) {
		Query q = this.getSession().createQuery(hql);
		for (Object obj : objects) {
			for (int i = 0; i < obj.getClass().getDeclaredFields().length; i++) {
				try {
					Field field = obj.getClass().getDeclaredFields()[i];
					if (field != null) {
						field.setAccessible(true);
						Object value = field.get(obj);
						if (value != null && value instanceof String) {
							q.setParameter(field.getName(), "%" + value + "%");
						}
					}
				} catch (IllegalArgumentException e) {
					throw new RuntimeException(e);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}

			}
		}
		return q;
	}

}