package ar.com.finit.service.rest.pub;

import java.text.ParseException;
import java.util.List;

import javax.ws.rs.Consumes;
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
@Path(EntityPath.PUBLIC + EntityPath.EVENTS)
public class EventPublicService {

	@Autowired
	private EventDao eventDao;
	@Autowired
	private EventConverter eventConverter;
	@Autowired
	private EventDTOFactory eventDTOFactory;
	
	@GET
	@Path("/{entity:(([0-9a-fA-F]){8}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){12}){1}}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response read(@PathParam("entity") String entity, @QueryParam("start") String start, @QueryParam("end") String end) throws ParseException {
		List<Event> events = this.eventDao.findBy(entity, start, end);
		return Response.ok(eventConverter.toDTO(events)).build();
	}

	@GET
	@Path("/player/{id:(([0-9a-fA-F]){8}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){12}){1}}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response read(@PathParam("id") String id, @QueryParam("status") int status) throws ParseException {
		List<Event> events = this.eventDao.findPlayer(id, status);
		return Response.ok(eventConverter.toDTO(events)).build();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response booking(String json) throws ParseException {
		EventDTO eventDto = eventDTOFactory.makeDTO(json);
		Event event = eventConverter.toEntity(eventDto);
		eventDao.save(event);
		return Response.ok(event.getId()).build();
	}
	
	@POST
	@Path("/cancel/{id:([0-9])+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id, String json) {
		EventImpl event = (EventImpl)this.eventDao.findById(id);
		if (event != null) {
			event.setStatus(EventImpl.CANCELED_PLAYER);
			this.eventDao.update(event);
			return Response.ok().build();
		}
		return Response.noContent().build();
	}

	@POST
	@Path("/rated/{id:([0-9])+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response rated(@PathParam("id") int id, String json) {
		EventImpl event = (EventImpl)this.eventDao.findById(id);
		if (event != null) {
			event.setStatus(EventImpl.RATED);
			this.eventDao.update(event);
			return Response.ok().build();
		}
		return Response.noContent().build();
	}

}
