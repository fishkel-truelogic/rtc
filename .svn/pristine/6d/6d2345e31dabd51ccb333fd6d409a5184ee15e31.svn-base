package ar.com.finit.service.rest.admin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
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
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Variant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate4.HibernateSystemException;

import ar.com.finit.core.model.Ground;
import ar.com.finit.core.model.dao.GroundDao;
import ar.com.finit.dto.model.GroundDTO;
import ar.com.finit.dto.model.factory.GroundDTOFactory;
import ar.com.finit.dto.violation.ViolationDTO;
import ar.com.finit.service.model.converter.GroundConverter;
import ar.com.finit.service.model.converter.violation.GroundViolationConverter;
import ar.com.finit.service.rest.utils.EntityPath;

/**
 * @author leo
 */
@Configurable
@Path(EntityPath.ADMIN + EntityPath.GROUNDS)
public class GroundAdminService {
	private Logger logger = (Logger)LoggerFactory.getLogger(GroundAdminService.class);
	
	@Autowired
	private GroundDao groundDao;
	@Autowired
	private Validator validator;
	@Autowired
	private GroundViolationConverter groundViolationConverter;
	@Autowired
	private GroundDTOFactory groundDTOFactory;
	@Autowired
	private GroundConverter groundConverter;
	
	@GET
	@Path(value="/")
	@RolesAllowed({"admin"})
	@Produces(MediaType.APPLICATION_JSON)
	public Response list() {
		List<Ground> grounds = this.groundDao.findAll();
		return Response.ok(groundConverter.toDTO(grounds)).build();
	}
	
    @POST
    @Path("/")
    @RolesAllowed("admin")
    @Consumes(MediaType.APPLICATION_JSON)
	public Response create(String json){
    	logger.info("*** Realizado PUT para el " + json);
    	GroundDTO groundDto = groundDTOFactory.makeDTO(json);
    	Ground ground = groundConverter.toEntity(groundDto);
		Response response = this.validate(ground);
		if (response != null) return response;
		try {
			groundDao.save(ground);
		} catch(DataIntegrityViolationException e) {
			logger.error(e.getMessage(), e);
			ViolationDTO violation = new ViolationDTO();
			violation.setProperty("name");
			violation.setMessage("Nombre de deporte existente");
			violation.setMessageTemplate("{ar.com.avanzit.constraints.uniqueGround}");
			Set<ViolationDTO> violations = new HashSet<ViolationDTO>();
			violations.add(violation);
			List<Variant> variants = new ArrayList<Variant>();
			variants.add(new Variant(MediaType.APPLICATION_JSON_TYPE, Locale.forLanguageTag("es"), "UTF-8"));
			return Response.notAcceptable(variants).entity(violations).build();
		}
		return Response.status(Status.CREATED).entity(ground.getId()).type(MediaType.APPLICATION_JSON_TYPE).build();
	}
    
	@POST
	@Path("/{id:(([0-9a-fA-F]){8}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){12}){1}}")
	@RolesAllowed("admin")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") String id, String json) {
		GroundDTO dto = groundDTOFactory.makeDTO(json);
		Ground ground = groundConverter.toEntity(dto);
		Response response = this.validate(ground);
		if (response != null) return response;
		try {
			this.groundDao.update(ground);
		} catch(HibernateSystemException e) {
			logger.error(e.getMessage(), e);
			ViolationDTO violation = new ViolationDTO();
			violation.setProperty("name");
			violation.setMessage("Nombre de deporte existente");
			violation.setMessageTemplate("{ar.com.avanzit.constraints.uniqueGround}");
			Set<ViolationDTO> violations = new HashSet<ViolationDTO>();
			violations.add(violation);
			List<Variant> variants = new ArrayList<Variant>();
			variants.add(new Variant(MediaType.APPLICATION_JSON_TYPE, Locale.forLanguageTag("es"), "UTF-8"));
			return Response.notAcceptable(variants).entity(violations).build();
		}
		return Response.ok(ground, MediaType.APPLICATION_JSON_TYPE).build();
	}
    

	private Response validate(Ground ground) {
		Response response = null;
		Set<ConstraintViolation<Ground>> violations = validator.validate(ground);
		if (!violations.isEmpty()) {
			Collection<ViolationDTO> dtos = groundViolationConverter.toDTO(violations);
			List<Variant> variants = new ArrayList<Variant>();
			variants.add(new Variant(MediaType.APPLICATION_JSON_TYPE, Locale.forLanguageTag("es"), "UTF-8"));
			response = Response.notAcceptable(variants).entity(dtos).build();
		}
		return response;
	}
}