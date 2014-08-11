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
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Variant;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import ar.com.finit.core.model.Image;
import ar.com.finit.core.model.dao.ImageDao;
import ar.com.finit.dto.model.ImageDTO;
import ar.com.finit.dto.model.factory.ImageDTOFactory;
import ar.com.finit.dto.violation.ViolationDTO;
import ar.com.finit.service.model.converter.ImageConverter;
import ar.com.finit.service.model.converter.violation.ImageViolationConverter;
import ar.com.finit.service.rest.utils.EntityPath;

/**
 * @author leo
 */
@Configurable
@Path(EntityPath.PRIVATE + EntityPath.IMAGES)
public class ImagePrivateService {

	private static final Logger logger = Logger.getLogger(ImagePrivateService.class);
	@Autowired
	public ImageDao imageDao;
	@Autowired
	private Validator validator;
	@Autowired
	private ImageViolationConverter imageViolationConverter;
	@Autowired
	private ImageDTOFactory imageDTOFactory;
	@Autowired
	public ImageConverter imageConverter;


	@POST
	@RolesAllowed(value = { "owner" })
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(String json) {
		logger.info("*** Realizado POST para el " + json);
		ImageDTO dto = imageDTOFactory.makeDTO(json);
		Image image = imageConverter.toEntity(dto);
		Response response = this.validate(image);
		if (response != null) {
			return response;
		}
		this.imageDao.save(image);
		return Response.status(Status.CREATED).entity(image.getId()).type(MediaType.APPLICATION_JSON_TYPE).build();
	}

	@POST
	@RolesAllowed(value = { "owner" })
	@Path("/{id:(([0-9a-fA-F]){8}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){12}){1}}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") String id, String json) {
		ImageDTO dto = imageDTOFactory.makeDTO(json);
		if (!id.equals(dto.getId())) {
			ViolationDTO vdto = new ViolationDTO();
			vdto.setInvalidValue(dto.getId());
			vdto.setMessage("El id del objeto a actualizar no coincide con el pasado por parámetros");
			vdto.setMessageTemplate("{ar.com.avanzit.constraints.idsDontMatch}");
			vdto.setProperty("code");
			List<Variant> variants = new ArrayList<Variant>();
			variants.add(new Variant(MediaType.APPLICATION_JSON_TYPE, Locale.forLanguageTag("es"), "UTF-8"));
			return Response.notAcceptable(variants).entity(vdto).build();
		}
		Image image = imageConverter.toEntity(dto);
		Response response = this.validate(image);
		if (response != null){
			return response;
		}
		this.imageDao.update(image);
		return Response.ok(image, MediaType.APPLICATION_JSON_TYPE).build();
	}

	@DELETE
	@RolesAllowed(value = { "admin", "owner" })
	@Path("/{id:(([0-9a-fA-F]){8}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){12}){1}}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") String id) {
		Image image = this.imageDao.findById(id);
		if (image != null) {
			this.imageDao.remove(image);
		}
	}

	private Response validate(Image image) {
		Response response = null;
		Set<ConstraintViolation<Image>> violations = validator.validate(image);
		if (!violations.isEmpty()) {
			Collection<ViolationDTO> dtos = imageViolationConverter.toDTO(violations);
			List<Variant> variants = new ArrayList<Variant>();
			variants.add(new Variant(MediaType.APPLICATION_JSON_TYPE, Locale.forLanguageTag("es"), "UTF-8"));
			response = Response.notAcceptable(variants).entity(dtos).build();
		}
		return response;
	}
}
