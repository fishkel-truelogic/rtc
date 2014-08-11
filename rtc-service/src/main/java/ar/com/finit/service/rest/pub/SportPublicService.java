package ar.com.finit.service.rest.pub;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import ar.com.finit.core.model.Sport;
import ar.com.finit.core.model.dao.SportDao;
import ar.com.finit.dto.model.SportDTO;
import ar.com.finit.service.model.converter.SportConverter;
import ar.com.finit.service.rest.utils.EntityPath;

/**
 * @author leo
 */

@Configurable
@Path(EntityPath.PUBLIC + EntityPath.SPORTS)
public class SportPublicService {
	
	@Autowired
	private SportDao sportDao;
	@Autowired
	private SportConverter sportConverter;
	
	@GET
	@Path(value = "/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SportDTO> sports () {
		return sportConverter.toDTO(sportDao.findAll());
	}
	
	@GET
	@Path("/{id:(([0-9a-fA-F]){8}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){12}){1}}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") String id) {
		Sport sport = sportDao.findById(id);
		return Response.ok(sportConverter.toDTO(sport)).build();
	}

}
