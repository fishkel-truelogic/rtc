package ar.com.finit.service.rest.priv;

import java.text.ParseException;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import ar.com.finit.core.model.Event;
import ar.com.finit.core.model.dao.EventDao;
import ar.com.finit.core.model.impl.EventImpl;
import ar.com.finit.dto.model.EventDTO;
import ar.com.finit.dto.model.factory.EventDTOFactory;
import ar.com.finit.service.model.converter.EventConverter;
import ar.com.finit.service.rest.utils.EntityPath;

/**
 * @author leo
 */

@Configurable
@Path(EntityPath.PRIVATE + EntityPath.EVENTS)
public class EventPrivateService {

	@Autowired
	private EventDao eventDao;
	@Autowired
	private EventDTOFactory eventDTOFactory;
	@Autowired
	private EventConverter eventConverter;
	
	@GET
	@Path("/{userId:(([0-9a-fA-F]){8}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){12}){1}}")
	@RolesAllowed({ "owner", "admin" })
	@Produces(MediaType.APPLICATION_JSON)
	public Response read(@PathParam("userId") String userId, @QueryParam("entity") String entity, @QueryParam("status") String status, @QueryParam("start") String start, @QueryParam("end") String end) throws ParseException {
		List<Event> events = this.eventDao.findBy(userId, entity, start, end, status);
		return Response.ok(eventConverter.toDTO(events)).build();
	}

	@GET
	@Path("/{id:([0-9])+}")
	@RolesAllowed({ "owner", "admin" })
	@Produces(MediaType.APPLICATION_JSON)
	public Response read(@PathParam("id") int id) {
		Event event = this.eventDao.findById(id);
		return Response.ok(eventConverter.toDTO(event)).build();
	}
	
	@POST
	@Path("/")
	@RolesAllowed({ "owner", "admin" })
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addDetails(String json) throws ParseException {
		EventDTO eventDto = eventDTOFactory.makeDTO(json);
		Event event = eventConverter.toEntity(eventDto);
		eventDao.save(event);
		return Response.ok(event.getId()).build();
	}

	@POST
	@Path("/{id:([0-9])+}")
	@RolesAllowed({ "owner", "admin" })
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addDetails(@PathParam("id") String id, String json) throws ParseException {
		EventDTO eventDto = eventDTOFactory.makeDTO(json);
		Event event = eventConverter.toEntity(eventDto);
		eventDao.update(event);
		return Response.ok(event.getId()).build();
	}

	@DELETE
	@Path("/cancel/{id:([0-9])+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
		EventImpl event = (EventImpl)this.eventDao.findById(id);
		if (event != null) {
			event.setStatus(EventImpl.CANCELED_OWNER);
			this.eventDao.update(event);
			return Response.ok().build();
		}
		return Response.noContent().build();
	}
	


}
