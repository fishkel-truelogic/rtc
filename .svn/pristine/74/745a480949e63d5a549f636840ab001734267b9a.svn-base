package ar.com.finit.service.rest.pub;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import ar.com.finit.core.model.State;
import ar.com.finit.core.model.dao.StateDao;
import ar.com.finit.dto.model.StateDTO;
import ar.com.finit.service.model.converter.StateConverter;
import ar.com.finit.service.rest.utils.EntityPath;

/**
 * @author leo
 */
@Configurable
@Path(EntityPath.PUBLIC + EntityPath.STATES)
public class StatePublicService {

	@Autowired
	public StateDao stateDao;
	@Autowired
	public StateConverter stateConverter;

	@GET
	@Path(value = "/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<StateDTO> getAll() {
		List<State> states = this.stateDao.findAll();
		return stateConverter.toDTO(states);
	}

}
