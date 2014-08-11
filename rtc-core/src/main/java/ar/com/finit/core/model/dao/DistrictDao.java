package ar.com.finit.core.model.dao;

import java.io.Serializable;
import java.util.List;

import ar.com.finit.core.dao.Dao;
import ar.com.finit.core.model.District;

/**
 * @author leo
 */
public interface DistrictDao extends Dao<Serializable, District> {
	
	public List<District> findByState(String state);
	
}