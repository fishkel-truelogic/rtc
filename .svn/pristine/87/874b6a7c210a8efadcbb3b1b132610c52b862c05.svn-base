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

import ar.com.finit.core.model.District;
import ar.com.finit.core.model.dao.DistrictDao;
import ar.com.finit.dto.model.DistrictDTO;
import ar.com.finit.dto.model.factory.DistrictDTOFactory;
import ar.com.finit.dto.violation.ViolationDTO;
import ar.com.finit.service.model.converter.DistrictConverter;
import ar.com.finit.service.model.converter.violation.DistrictViolationConverter;
import ar.com.finit.service.rest.utils.EntityPath;

/**
 * @author leo
 */
@Configurable
@Path(EntityPath.ADMIN + EntityPath.DISTRICTS)
public class DistrictAdminService {
	private Logger logger = (Logger) LoggerFactory.getLogger(DistrictAdminService.class);

	@Autowired
	private DistrictDao districtDao;
	@Autowired
	private Validator validator;
	@Autowired
	private DistrictViolationConverter districtViolationConverter;
	@Autowired
	private DistrictDTOFactory districtDTOFactory;
	@Autowired
	private DistrictConverter districtConverter;

	@GET
	@Path(value = "/")
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed("admin")
	public Response list() {
		List<District> districts = this.districtDao.findAll();
		return Response.ok(districtConverter.toDTO(districts)).build();
	}

	@POST
	@Path("/")
	@RolesAllowed("admin")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(String json) {
		logger.info("*** guardando " + json + " ***");
		DistrictDTO districtDto = districtDTOFactory.makeDTO(json);
		District district = districtConverter.toEntity(districtDto);
		Response response = this.validate(district);
		if (response != null)
			return response;
		districtDao.save(district);
		return Response.status(Status.CREATED).entity(district.getId()).type(MediaType.APPLICATION_JSON_TYPE).build();
	}

	private Response validate(District district) {
		Response response = null;
		Set<ConstraintViolation<District>> violations = validator.validate(district);
		if (!violations.isEmpty()) {
			Collection<ViolationDTO> dtos = districtViolationConverter.toDTO(violations);
			List<Variant> variants = new ArrayList<Variant>();
			variants.add(new Variant(MediaType.APPLICATION_JSON_TYPE, Locale.forLanguageTag("es"), "UTF-8"));
			response = Response.notAcceptable(variants).entity(dtos).build();
		}
		return response;
	}

}