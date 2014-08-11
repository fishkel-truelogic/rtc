package ar.com.finit.service.rest.pub;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import ar.com.finit.core.model.Ground;
import ar.com.finit.core.model.dao.GroundDao;
import ar.com.finit.dto.model.GroundDTO;
import ar.com.finit.service.model.converter.GroundConverter;
import ar.com.finit.service.rest.utils.EntityPath;

/**
 * @author leo
 */

@Configurable
@Path(EntityPath.PUBLIC + EntityPath.GROUNDS)
public class GroundPublicService {
	
	@Autowired
	private GroundDao groundDao;
	@Autowired
	private GroundConverter groundConverter;

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response all() {
		List<Ground> grounds = groundDao.findAll();
		List<GroundDTO> dtos = groundConverter.toDTO(grounds);
		return Response.ok(dtos).build();
	}
}
