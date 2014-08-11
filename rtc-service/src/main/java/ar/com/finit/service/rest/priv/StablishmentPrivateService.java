package ar.com.finit.service.rest.priv;

import java.util.Collection;
import java.util.Set;

import javax.annotation.security.RolesAllowed;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
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

import ar.com.finit.core.model.Stablishment;
import ar.com.finit.core.model.dao.AlbumDao;
import ar.com.finit.core.model.dao.FieldDao;
import ar.com.finit.core.model.dao.MapMarkerDao;
import ar.com.finit.core.model.dao.RateDao;
import ar.com.finit.core.model.dao.StablishmentDao;
import ar.com.finit.core.model.dao.TpServiceDao;
import ar.com.finit.core.model.impl.RateImpl;
import ar.com.finit.core.model.impl.StablishmentImpl;
import ar.com.finit.dto.model.StablishmentDTO;
import ar.com.finit.dto.model.factory.StablishmentDTOFactory;
import ar.com.finit.dto.violation.ViolationDTO;
import ar.com.finit.service.model.converter.AlbumConverter;
import ar.com.finit.service.model.converter.FieldConverter;
import ar.com.finit.service.model.converter.RateConverter;
import ar.com.finit.service.model.converter.StablishmentConverter;
import ar.com.finit.service.model.converter.TpServiceConverter;
import ar.com.finit.service.model.converter.violation.StablishmentViolationConverter;
import ar.com.finit.service.rest.utils.EntityPath;
import ar.com.finit.service.rest.utils.VariantsFactory;

/**
 * @author leo
 */

@Configurable
@Path(EntityPath.PRIVATE + EntityPath.STABLISHMENTS)
public class StablishmentPrivateService {

	@Autowired
	private StablishmentDao stablishmentDao;
	@Autowired
	private AlbumDao albumDao;
	@Autowired
	private AlbumConverter albumConverter;
	@Autowired
	private RateDao rateDao;
	@Autowired
	private RateConverter rateConverter;
	@Autowired
	private FieldDao fieldDao;
	@Autowired
	private MapMarkerDao mapMarkerDao;
	@Autowired
	private TpServiceDao tpServiceDao;
	@Autowired
	private StablishmentConverter stablishmentConverter;
	@Autowired
	private StablishmentDTOFactory stablishmentDTOFactory;
	@Autowired
	private Validator validator;
	@Autowired
	private StablishmentViolationConverter stablishmentViolationConverter;
	@Autowired
	private FieldConverter fieldConverter;
	@Autowired
	private TpServiceConverter tpServiceConverter;

	@GET
	@Path("/{userId:(([0-9a-fA-F]){8}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){12}){1}}")
	@RolesAllowed({ "owner", "admin" })
	@Produces(MediaType.APPLICATION_JSON)
	public Response read(@PathParam("userId") String userId, @QueryParam("lazy") @DefaultValue("true") boolean lazy) {
		Stablishment stablishment = this.stablishmentDao.findByUserId(userId);
		return Response.ok(stablishmentConverter.toDTO(stablishment, lazy)).build();
	}

	@POST
	@Path("/{id:(([0-9a-fA-F]){8}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){12}){1}}")
	@RolesAllowed({ "owner", "admin" })
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") String id, String json) {
		StablishmentDTO stablishmentDTO = stablishmentDTOFactory.makeDTO(json);
		StablishmentImpl stablishment = (StablishmentImpl) stablishmentConverter.toEntity(stablishmentDTO);
		stablishment.setMapMarker(mapMarkerDao.findById(stablishment.getMapMarker().getId()));
		stablishment.setAlbum(albumConverter.toEntity(albumConverter.toDTO(albumDao.findById(stablishment.getAlbum().getId()), false)));
		stablishment.setFields(fieldConverter.toEntity(fieldConverter.toDTO(fieldDao.findByUserId(stablishment.getUserId()), false)));
		stablishment.setTpServices(tpServiceConverter.toEntity(tpServiceConverter.toDTO(tpServiceDao.findByUserId(stablishment.getUserId()), false)));
		stablishment.setRate(rateConverter.toEntity(rateConverter.toDTO(rateDao.findById(stablishment.getRate().getId()))));
		Response response = validate(stablishment);
		if (response != null) return response;
		stablishmentDao.update(stablishment);
		return Response.ok().build();

	}

	@POST
	@Path("/")
	@RolesAllowed({ "owner", "admin" })
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(String json) {
		StablishmentDTO stablishmentDTO = stablishmentDTOFactory.makeDTO(json);
		Stablishment stablishment = stablishmentConverter.toEntity(stablishmentDTO);
		Response response = validate(stablishment);
		if (response != null) return response;
		stablishmentDao.save(stablishment);
		RateImpl rate = (RateImpl)stablishment.getRate();
		rate.setEntity(stablishment.getId());
		return Response.ok().entity(stablishment.getId()).build();
	}

	private Response validate(Stablishment stablishment) {
		Set<ConstraintViolation<Stablishment>> violations = validator.validate(stablishment);
		if (!violations.isEmpty()) {
			Collection<ViolationDTO> dtos = stablishmentViolationConverter.toDTO(violations);
			return Response.notAcceptable(VariantsFactory.variants()).entity(dtos).build();
		}
		return null;
	}

}