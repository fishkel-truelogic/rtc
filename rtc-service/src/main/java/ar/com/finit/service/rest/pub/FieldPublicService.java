package ar.com.finit.service.rest.pub;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import ar.com.finit.core.model.Field;
import ar.com.finit.core.model.Stablishment;
import ar.com.finit.core.model.dao.FieldDao;
import ar.com.finit.core.model.dao.StablishmentDao;
import ar.com.finit.dto.model.StablishmentDTO;
import ar.com.finit.service.model.converter.FieldConverter;
import ar.com.finit.service.model.converter.StablishmentConverter;
import ar.com.finit.service.rest.utils.EntityPath;

/**
 * @author leo
 */

@Configurable
@Path(EntityPath.PUBLIC + EntityPath.FIELDS)
public class FieldPublicService {

	@Autowired
	private FieldDao fieldDao;
	@Autowired
	private FieldConverter fieldConverter;
	@Autowired
	private StablishmentConverter stablishmentConverter;
	@Autowired
	private StablishmentDao stablishmentDao;

	@GET
	@Path("/{id:(([0-9a-fA-F]){8}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){12}){1}}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response read(@PathParam("id") String id, @QueryParam("lazy") @DefaultValue("true") boolean lazy) {
		Field field = this.fieldDao.findById(id);
		return Response.ok(fieldConverter.toDTO(field, lazy)).build();
	}

	@GET
	@Path("/featured")
	@Produces(MediaType.APPLICATION_JSON)
	public Response read(@QueryParam("sport") String sport, @QueryParam("state") String state) {
		List<Field> fields = this.stablishmentDao.findFeaturedBy(state, sport);
		List<Stablishment> stablishments = new ArrayList<Stablishment>();
		for (Field field : fields) {
			Stablishment stablishment = stablishmentDao.findByUserId(field.getUserId());
			stablishments.add(stablishment);
		}
		List<StablishmentDTO> stablishmentsDTO = stablishmentConverter.toDTO(stablishments, true);
		int i = 0;
		for (StablishmentDTO stablishment : stablishmentsDTO) {
			stablishment.addField(fieldConverter.toDTO(fields.get(i), true));
			i ++;
		}
		return Response.ok(stablishmentsDTO).build();
	}

}
