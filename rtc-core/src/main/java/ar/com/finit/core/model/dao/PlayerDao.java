package ar.com.finit.core.model.dao;

import java.io.Serializable;

import ar.com.finit.core.dao.Dao;
import ar.com.finit.core.model.Player;

/**
 * @author leo
 */
public interface PlayerDao extends Dao<Serializable, Player> {

	public Player findByFbId(Serializable id);

	public Player login(String login, String password);

	public boolean existsUsername(String username);

	public Player findByEmail(String email);

}
