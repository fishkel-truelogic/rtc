package ar.com.finit.service.rest.pub;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import ar.com.finit.core.model.Image;
import ar.com.finit.core.model.dao.ImageDao;
import ar.com.finit.service.model.converter.ImageConverter;
import ar.com.finit.service.rest.utils.EntityPath;

/**
 * @author leo
 */

@Configurable
@Path(EntityPath.PUBLIC + EntityPath.IMAGES)
public class ImagePublicService {

	@Autowired
	public ImageDao imageDao;
	@Autowired
	public ImageConverter imageConverter;


	@GET
	@Path("/{id:(([0-9a-fA-F]){8}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){12}){1}}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response read(@PathParam("id") String id) {
		Image image = this.imageDao.findById(id);
		return Response.ok(imageConverter.toDTO(image)).build();
	}

}
