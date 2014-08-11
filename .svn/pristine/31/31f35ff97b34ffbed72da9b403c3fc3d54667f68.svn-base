package ar.com.finit.service.rest.priv;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.annotation.security.RolesAllowed;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

import ar.com.finit.core.model.Field;
import ar.com.finit.core.model.dao.AlbumDao;
import ar.com.finit.core.model.dao.FieldDao;
import ar.com.finit.core.model.dao.RateDao;
import ar.com.finit.core.model.dao.StablishmentDao;
import ar.com.finit.core.model.dao.TpServiceDao;
import ar.com.finit.core.model.impl.FieldImpl;
import ar.com.finit.core.model.impl.RateImpl;
import ar.com.finit.core.model.impl.StablishmentImpl;
import ar.com.finit.dto.model.FieldDTO;
import ar.com.finit.dto.model.factory.FieldDTOFactory;
import ar.com.finit.dto.violation.ViolationDTO;
import ar.com.finit.service.model.converter.AlbumConverter;
import ar.com.finit.service.model.converter.FieldConverter;
import ar.com.finit.service.model.converter.RateConverter;
import ar.com.finit.service.model.converter.StablishmentConverter;
import ar.com.finit.service.model.converter.TpServiceConverter;
import ar.com.finit.service.model.converter.violation.FieldViolationConverter;
import ar.com.finit.service.rest.utils.EntityPath;
import ar.com.finit.service.rest.utils.VariantsFactory;

/**
 * @author leo
 */

@Configurable
@Path(EntityPath.PRIVATE + EntityPath.FIELDS)
public class FieldPrivateService {

	@Autowired
	private Validator validator;
	@Autowired
	private StablishmentDao stablishmentDao;
	@Autowired
	private StablishmentConverter stablishmentConverter;
	@Autowired
	private AlbumDao albumDao;
	@Autowired
	private AlbumConverter albumConverter;
	@Autowired
	private RateDao rateDao;
	@Autowired
	private RateConverter rateConverter;
	@Autowired
	private TpServiceDao tpServiceDao;
	@Autowired
	private TpServiceConverter tpServiceConverter;
	@Autowired
	private FieldDao fieldDao;
	@Autowired
	private FieldConverter fieldConverter;
	@Autowired
	private FieldDTOFactory fieldDTOFactory;
	@Autowired
	private FieldViolationConverter fieldViolationConverter;

	@GET
	@Path("/{userId:(([0-9a-fA-F]){8}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){12}){1}}")
	@RolesAllowed({ "owner", "admin" })
	@Produces(MediaType.APPLICATION_JSON)
	public Response read(@PathParam("userId") String userId, @QueryParam("lazy") @DefaultValue("true") boolean lazy) {
		List<Field> fields = this.fieldDao.findByUserId(userId);
		return Response.ok(fieldConverter.toDTO(fields, lazy)).build();
	}

	@POST
	@Path("/{id:(([0-9a-fA-F]){8}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){12}){1}}")
	@RolesAllowed({ "owner", "admin" })
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") String id, String json) {
		FieldDTO fieldDTO = fieldDTOFactory.makeDTO(json);
		FieldImpl field = (FieldImpl) fieldConverter.toEntity(fieldDTO);
		field.setAlbum(albumConverter.toEntity(albumConverter.toDTO(albumDao.findById(field.getAlbum().getId()), false)));
		field.setRate(rateConverter.toEntity(rateConverter.toDTO(rateDao.findById(field.getRate().getId()))));
		Response response = validate(field);
		if (response != null) return response;
		fieldDao.update(field);
		return Response.ok().build();
	}

	@POST
	@Path("/")
	@RolesAllowed({ "owner", "admin" })
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(String json) {
		FieldDTO fieldDTO = fieldDTOFactory.makeDTO(json);
		Field field = fieldConverter.toEntity(fieldDTO);
		Response response = validate(field);
		if (response != null) return response;
		StablishmentImpl stablishment = (StablishmentImpl) stablishmentConverter.toEntity(stablishmentConverter.toDTO(stablishmentDao.findByUserId(field.getUserId()), false));
		stablishment.setAlbum(albumConverter.toEntity(albumConverter.toDTO(albumDao.findById(stablishment.getAlbum().getId()), false)));
		stablishment.setTpServices(tpServiceConverter.toEntity(tpServiceConverter.toDTO(tpServiceDao.findByUserId(stablishment.getUserId()), false)));
		stablishment.setFields(fieldConverter.toEntity(fieldConverter.toDTO(fieldDao.findByUserId(stablishment.getUserId()), false)));
		stablishment.setRate(rateConverter.toEntity(rateConverter.toDTO(rateDao.findById(stablishment.getRate().getId()))));
		stablishment.getFields().add(field);
		stablishmentDao.update(stablishment);
		RateImpl rate = (RateImpl) field.getRate();
		rate.setEntity(field.getId());
		rateDao.update(rate);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{id:(([0-9a-fA-F]){8}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){12}){1}}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") String id) {
		Field field = this.fieldDao.findById(id);
		if (field != null) {
			this.fieldDao.remove(field);
			return Response.ok().build();
		}
		return Response.noContent().build();
	}

	private Response validate(Field field) {
		Set<ConstraintViolation<Field>> violations = validator.validate(field);
		if (!violations.isEmpty()) {
			Collection<ViolationDTO> dtos = fieldViolationConverter.toDTO(violations);
			return Response.notAcceptable(VariantsFactory.variants()).entity(dtos).build();
		}
		return null;
	}

}
