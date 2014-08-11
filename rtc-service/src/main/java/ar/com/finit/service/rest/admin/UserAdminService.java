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

import ar.com.finit.core.model.User;
import ar.com.finit.core.model.dao.UserDao;
import ar.com.finit.dto.model.UserDTO;
import ar.com.finit.dto.model.factory.UserDTOFactory;
import ar.com.finit.dto.violation.ViolationDTO;
import ar.com.finit.service.model.converter.UserConverter;
import ar.com.finit.service.model.converter.violation.UserViolationConverter;
import ar.com.finit.service.rest.utils.EntityPath;

/**
 * @author leo
 */
@Configurable
@Path(EntityPath.ADMIN + EntityPath.USERS)
public class UserAdminService {
	private Logger logger = (Logger)LoggerFactory.getLogger(UserAdminService.class);
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private Validator validator;
	@Autowired
	private UserViolationConverter userViolationConverter;
	@Autowired
	private UserDTOFactory userDTOFactory;
	@Autowired
	private UserConverter userConverter;
	
	@GET
	@Path(value="/")
	@RolesAllowed({"admin"})
	@Produces(MediaType.APPLICATION_JSON)
	public Response list() {
		List<User> users = this.userDao.findAll();
		return Response.ok(userConverter.toDTO(users)).build();
	}
	
    @POST
    @Path("/")
    @RolesAllowed("admin")
    @Consumes(MediaType.APPLICATION_JSON)
	public Response create(String json){
    	logger.info("*** Realizado PUT para el " + json);
    	UserDTO userDto = userDTOFactory.makeDTO(json);
    	User user = userConverter.toEntity(userDto);
		Response response = this.validate(user);
		if (response != null) return response;
		try {
			userDao.save(user);
		} catch(DataIntegrityViolationException e) {
			logger.error(e.getMessage(), e);
			ViolationDTO violation = new ViolationDTO();
			violation.setProperty("username");
			violation.setMessage("Nombre de usuario existente");
			violation.setMessageTemplate("{ar.com.avanzit.constraints.uniqueUser}");
			Set<ViolationDTO> violations = new HashSet<ViolationDTO>();
			violations.add(violation);
			List<Variant> variants = new ArrayList<Variant>();
			variants.add(new Variant(MediaType.APPLICATION_JSON_TYPE, Locale.forLanguageTag("es"), "UTF-8"));
			return Response.notAcceptable(variants).entity(violations).build();
		}
		return Response.status(Status.CREATED).entity(user.getId()).type(MediaType.APPLICATION_JSON_TYPE).build();
	}
    
	@POST
	@Path("/{id:(([0-9a-fA-F]){8}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){12}){1}}")
	@RolesAllowed("admin")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") String id, String json) {
		UserDTO dto = userDTOFactory.makeDTO(json);
		if (!id.equals(dto.getId())) {
			ViolationDTO vdto = new ViolationDTO();
			vdto.setInvalidValue(dto.getId().toString());
			// TODO: Utilizar I18n para este mensaje 
			vdto.setMessage("El ID del objeto a actualizar no coincide con el pasado por parámetros");
			vdto.setMessageTemplate("{ar.com.avanzit.constraints.idsDontMatch}");
			vdto.setProperty("id");
			List<Variant> variants = new ArrayList<Variant>();
			variants.add(new Variant(MediaType.APPLICATION_JSON_TYPE, Locale.forLanguageTag("es"), "UTF-8"));
			return Response.notAcceptable(variants).entity(vdto).build();
		}
		User user = userConverter.toEntity(dto);
		Response response = this.validate(user);
		if (response != null) return response;
		try {
			this.userDao.update(user);
		} catch(HibernateSystemException e) {
			logger.error(e.getMessage(), e);
			ViolationDTO violation = new ViolationDTO();
			violation.setProperty("username");
			violation.setMessage("Nombre de usuario existente");
			violation.setMessageTemplate("{ar.com.avanzit.constraints.uniqueUser}");
			Set<ViolationDTO> violations = new HashSet<ViolationDTO>();
			violations.add(violation);
			List<Variant> variants = new ArrayList<Variant>();
			variants.add(new Variant(MediaType.APPLICATION_JSON_TYPE, Locale.forLanguageTag("es"), "UTF-8"));
			return Response.notAcceptable(variants).entity(violations).build();
		}
		return Response.ok(user, MediaType.APPLICATION_JSON_TYPE).build();
	}
    

	private Response validate(User user) {
		Response response = null;
		Set<ConstraintViolation<User>> violations = validator.validate(user);
		if (!violations.isEmpty()) {
			Collection<ViolationDTO> dtos = userViolationConverter.toDTO(violations);
			List<Variant> variants = new ArrayList<Variant>();
			variants.add(new Variant(MediaType.APPLICATION_JSON_TYPE, Locale.forLanguageTag("es"), "UTF-8"));
			response = Response.notAcceptable(variants).entity(dtos).build();
		}
		return response;
	}
}