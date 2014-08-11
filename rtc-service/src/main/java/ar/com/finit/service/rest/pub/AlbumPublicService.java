package ar.com.finit.service.rest.pub;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import ar.com.finit.core.model.Album;
import ar.com.finit.core.model.dao.AlbumDao;
import ar.com.finit.service.model.converter.AlbumConverter;
import ar.com.finit.service.rest.utils.EntityPath;

/**
 * @author leo
 */

@Configurable
@Path(EntityPath.PUBLIC + EntityPath.ALBUMS)
public class AlbumPublicService {

	@Autowired
	public AlbumDao albumDao;
	@Autowired
	public AlbumConverter albumConverter;


	@GET
	@Path("/{id:(([0-9a-fA-F]){8}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){12}){1}}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response read(@PathParam("id") String id) {
		Album album = this.albumDao.findById(id);
		return Response.ok(albumConverter.toDTO(album, false)).build();
	}

}
