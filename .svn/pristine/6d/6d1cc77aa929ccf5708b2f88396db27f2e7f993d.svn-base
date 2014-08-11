package ar.com.finit.service.rest.pub;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import ar.com.finit.core.model.MapMarker;
import ar.com.finit.core.model.dao.MapMarkerDao;
import ar.com.finit.service.model.converter.MapMarkerConverter;
import ar.com.finit.service.rest.utils.EntityPath;

/**
 * @author leo
 */

@Configurable
@Path(EntityPath.PUBLIC + EntityPath.MAP_MARKERS)
public class MapMarkerPublicService {

	@Autowired
	private MapMarkerDao mapMarkerDao;
	@Autowired
	private MapMarkerConverter mapMarkerConverter;

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response serch(@QueryParam("state") 
					     String state,
					     @QueryParam("district") 
					     String district,
					     @QueryParam("sport") 
					     String sport,
					     @QueryParam("ground") 
					     String ground,
					     @QueryParam("day") 
					     String day,
					     @QueryParam("hour") 
					     String hour) {
		
		
		List<MapMarker> mapMarkers = mapMarkerDao.findBy(state, district, sport, ground, day, hour);
			
		return Response.ok(mapMarkerConverter.toDTO(mapMarkers)).build();
	}

}
