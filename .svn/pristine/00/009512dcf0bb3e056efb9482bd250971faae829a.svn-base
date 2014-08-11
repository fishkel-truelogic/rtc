package ar.com.finit.service.rest.priv;

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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Variant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import ar.com.finit.core.model.MapMarker;
import ar.com.finit.core.model.dao.MapMarkerDao;
import ar.com.finit.dto.model.MapMarkerDTO;
import ar.com.finit.dto.model.factory.MapMarkerDTOFactory;
import ar.com.finit.dto.violation.ViolationDTO;
import ar.com.finit.service.model.converter.MapMarkerConverter;
import ar.com.finit.service.model.converter.violation.MapMarkerViolationConverter;
import ar.com.finit.service.rest.utils.EntityPath;

/**
 * @author leo
 */

@Configurable
@Path(EntityPath.PRIVATE + EntityPath.MAP_MARKERS)
public class MapMarkerPrivateService {

	@Autowired
	private MapMarkerDao mapMarkerDao;
	@Autowired
	private MapMarkerConverter mapMarkerConverter;
	@Autowired
	private MapMarkerDTOFactory mapMarkerDTOFactory;
	@Autowired
	private Validator validator;
	@Autowired
	private MapMarkerViolationConverter mapMarkerViolationConverter;

	@GET
	@Path("/{id:(([0-9a-fA-F]){8}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){12}){1}}")
	@RolesAllowed({ "owner", "admin" })
	@Produces(MediaType.APPLICATION_JSON)
	public Response read(@PathParam("id") String id) {
		
		MapMarker mapMarker = this.mapMarkerDao.findById(id);
	
		return Response.ok(mapMarkerConverter.toDTO(mapMarker)).build();

	}

	@POST
	@Path("/{id:(([0-9a-fA-F]){8}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){12}){1}}")
	@RolesAllowed({ "owner", "admin" })
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") String id, String json) {

		MapMarkerDTO mapMarkerDTO = mapMarkerDTOFactory.makeDTO(json);
		MapMarker mapMarker = mapMarkerConverter.toEntity(mapMarkerDTO);

		Response response = validate(mapMarker);
		if (response != null)
			return response;

		mapMarkerDao.update(mapMarker);
		return Response.ok().build();

	}

	@POST
	@Path("/")
	@RolesAllowed({ "owner", "admin" })
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(String json) {
		
		MapMarkerDTO mapMarkerDTO = mapMarkerDTOFactory.makeDTO(json);
		MapMarker mapMarker = mapMarkerConverter.toEntity(mapMarkerDTO);
		
		Response response = validate(mapMarker);
		if (response != null)
			return response;
		
		mapMarkerDao.save(mapMarker);
		return Response.ok().entity(mapMarker.getId()).build();
		
	}

	private Response validate(MapMarker mapMarker) {
		
		List<Variant> variants = new ArrayList<Variant>();
		variants.add(new Variant(MediaType.APPLICATION_JSON_TYPE, Locale.forLanguageTag("es"), "UTF-8"));

		Set<ConstraintViolation<MapMarker>> violations = validator.validate(mapMarker);
		
		if (!violations.isEmpty()) {
			Collection<ViolationDTO> dtos = mapMarkerViolationConverter.toDTO(violations);
			return Response.notAcceptable(variants).entity(dtos).build();
		}
	
		return null;

	}

}
