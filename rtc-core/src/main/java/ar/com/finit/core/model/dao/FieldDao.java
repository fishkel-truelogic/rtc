package ar.com.finit.core.model.dao;

import java.io.Serializable;
import java.util.List;

import ar.com.finit.core.dao.Dao;
import ar.com.finit.core.model.Field;

/**
 * @author leo
 */
public interface FieldDao extends Dao<Serializable, Field> {

	public List<Field> findByUserId(String userId);

}
