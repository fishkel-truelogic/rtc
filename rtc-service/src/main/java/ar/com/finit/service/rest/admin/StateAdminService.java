package ar.com.finit.service.rest.admin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.annotation.security.RolesAllowed;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Variant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import ar.com.finit.core.model.State;
import ar.com.finit.core.model.dao.StateDao;
import ar.com.finit.dto.model.StateDTO;
import ar.com.finit.dto.model.factory.StateDTOFactory;
import ar.com.finit.dto.violation.ViolationDTO;
import ar.com.finit.service.model.converter.StateConverter;
import ar.com.finit.service.model.converter.violation.StateViolationConverter;
import ar.com.finit.service.rest.utils.EntityPath;

/**
 * @author leo
 */
@Configurable
@Path(EntityPath.ADMIN + EntityPath.STATES)
public class StateAdminService {
	private Logger logger = (Logger) LoggerFactory.getLogger(StateAdminService.class);

	@Autowired
	private StateDao stateDao;
	@Autowired
	private Validator validator;
	@Autowired
	private StateViolationConverter stateViolationConverter;
	@Autowired
	private StateDTOFactory stateDTOFactory;
	@Autowired
	private StateConverter stateConverter;

	@GET
	@Path(value = "/")
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed("admin")
	public Response list() {
		List<State> states = this.stateDao.findAll();
		return Response.ok(stateConverter.toDTO(states)).build();
	}

	@POST
	@Path("/")
	@RolesAllowed("admin")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(String json) {
		logger.info("*** guardando " + json + " ***");
		StateDTO stateDto = stateDTOFactory.makeDTO(json);
		State state = stateConverter.toEntity(stateDto);
		Response response = this.validate(state);
		if (response != null)
			return response;
		stateDao.save(state);
		return Response.status(Status.CREATED).entity(state.getId()).type(MediaType.APPLICATION_JSON_TYPE).build();
	}

	private Response validate(State state) {
		Response response = null;
		Set<ConstraintViolation<State>> violations = validator.validate(state);
		if (!violations.isEmpty()) {
			Collection<ViolationDTO> dtos = stateViolationConverter.toDTO(violations);
			List<Variant> variants = new ArrayList<Variant>();
			variants.add(new Variant(MediaType.APPLICATION_JSON_TYPE, Locale.forLanguageTag("es"), "UTF-8"));
			response = Response.notAcceptable(variants).entity(dtos).build();
		}
		return response;
	}

}