package ar.com.finit.service.rest.priv;

import java.util.Collection;
import java.util.Set;

import javax.annotation.security.RolesAllowed;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import ar.com.finit.core.model.Album;
import ar.com.finit.core.model.dao.AlbumDao;
import ar.com.finit.dto.model.AlbumDTO;
import ar.com.finit.dto.model.factory.AlbumDTOFactory;
import ar.com.finit.dto.violation.ViolationDTO;
import ar.com.finit.service.model.converter.AlbumConverter;
import ar.com.finit.service.model.converter.violation.AlbumViolationConverter;
import ar.com.finit.service.rest.utils.EntityPath;
import ar.com.finit.service.rest.utils.VariantsFactory;

/**
 * @author leo
 */
@Configurable
@Path(EntityPath.PRIVATE + EntityPath.ALBUMS)
public class AlbumPrivateService {

	@Autowired
	public AlbumDao albumDao;
	@Autowired
	private Validator validator;
	@Autowired
	private AlbumViolationConverter albumViolationConverter;
	@Autowired
	private AlbumDTOFactory albumDTOFactory;
	@Autowired
	public AlbumConverter albumConverter;

	
	@POST
	@Path("/{id:(([0-9a-fA-F]){8}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){12}){1}}")
	@RolesAllowed({ "owner", "admin" })
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") String id, String json) {

		AlbumDTO albumDTO = albumDTOFactory.makeDTO(json);
		Album album = albumConverter.toEntity(albumDTO);

		Response response = validate(album);
		if (response != null)
			return response;

		albumDao.update(album);
		return Response.ok(albumConverter.toDTO(album, false)).build();

	}
	
	private Response validate(Album album) {
		Set<ConstraintViolation<Album>> violations = validator.validate(album);
		if (!violations.isEmpty()) {
			Collection<ViolationDTO> dtos = albumViolationConverter.toDTO(violations);
			return Response.notAcceptable(VariantsFactory.variants()).entity(dtos).build();
		}
		return null;
	}
}
