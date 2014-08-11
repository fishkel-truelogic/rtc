package ar.com.finit.core.model.dao;

import java.io.Serializable;
import java.util.List;

import ar.com.finit.core.dao.Dao;
import ar.com.finit.core.model.TpService;

/**
 * @author leo
 */
public interface TpServiceDao extends Dao<Serializable, TpService> {

	public List<TpService> findByUserId(String userId);

}
