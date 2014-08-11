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

import ar.com.finit.core.model.Role;
import ar.com.finit.core.model.dao.RoleDao;
import ar.com.finit.dto.model.RoleDTO;
import ar.com.finit.dto.model.factory.RoleDTOFactory;
import ar.com.finit.dto.violation.ViolationDTO;
import ar.com.finit.service.model.converter.RoleConverter;
import ar.com.finit.service.model.converter.violation.RoleViolationConverter;
import ar.com.finit.service.rest.utils.EntityPath;

/**
 * @author leo
 */
@Configurable
@Path(EntityPath.ADMIN + EntityPath.ROLES)
public class RoleAdminService {
	private Logger logger = (Logger)LoggerFactory.getLogger(RoleAdminService.class);
	
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private Validator validator;
	@Autowired
	private RoleViolationConverter roleViolationConverter;
	@Autowired
	private RoleDTOFactory roleDTOFactory;
	@Autowired
	private RoleConverter roleConverter;
	
	@GET
	@Path(value="/")
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed("admin")
	public Response list() {
		List<Role> roles = this.roleDao.findAll();
		return Response.ok(roleConverter.toDTO(roles)).build();
	}
	
    @GET
    @Path("/{id:[\\d]+}")
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
	public RoleDTO read(@PathParam("id") Long id){
    	Role role = this.roleDao.findById(id);
		return roleConverter.toDTO(role);
	}
    
    @POST
    @Path("/")
    @RolesAllowed("admin")
    @Consumes(MediaType.APPLICATION_JSON)
	public Response create(String json){
    	logger.info("*** Realizado PUT para el " + json);
    	RoleDTO roleDto = roleDTOFactory.makeDTO(json);
    	Role role = roleConverter.toEntity(roleDto);
		Response response = this.validate(role);
		if (response != null) return response;
		roleDao.save(role);
		return Response.status(Status.CREATED).entity(role.getId()).type(MediaType.APPLICATION_JSON_TYPE).build();
	}
    
	@POST
	@Path("/{id:[\\d]+}")
	@RolesAllowed("admin")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") Long id, String json) {
		RoleDTO dto = roleDTOFactory.makeDTO(json);
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
		Role role = roleConverter.toEntity(dto);
		Response response = this.validate(role);
		if (response != null) {
			return response;
		}
		this.roleDao.update(role);
		return Response.ok(role, MediaType.APPLICATION_JSON_TYPE).build();
	}
    

	private Response validate(Role role) {
		Response response = null;
		Set<ConstraintViolation<Role>> violations = validator.validate(role);
		if (!violations.isEmpty()) {
			Collection<ViolationDTO> dtos = roleViolationConverter.toDTO(violations);
			List<Variant> variants = new ArrayList<Variant>();
			variants.add(new Variant(MediaType.APPLICATION_JSON_TYPE, Locale.forLanguageTag("es"), "UTF-8"));
			response = Response.notAcceptable(variants).entity(dtos).build();
		}
		return response;
	}
}