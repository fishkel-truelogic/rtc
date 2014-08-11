package ar.com.finit.service.rest.pub;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import ar.com.finit.core.model.Stablishment;
import ar.com.finit.core.model.dao.StablishmentDao;
import ar.com.finit.dto.model.StablishmentDTO;
import ar.com.finit.dto.model.page.StablishmentCollectionPage;
import ar.com.finit.service.model.converter.StablishmentConverter;
import ar.com.finit.service.rest.utils.EntityPath;
import ar.com.finit.service.rest.utils.MathUtil;

/**
 * @author leo
 */

@Configurable
@Path(EntityPath.PUBLIC + EntityPath.STABLISHMENTS)
public class StablishmentPublicService {
	
	@Autowired
	private StablishmentDao stablishmentDao;
	@Autowired
	private StablishmentConverter stablishmentConverter;
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public StablishmentCollectionPage serch(@QueryParam("page") 
										    @DefaultValue(value = "1") 
										    int page,
										    @QueryParam("pageSize") 
										    @DefaultValue(value = "10") 
										    int pageSize,  
										    @QueryParam("state") 
										    String state,
										    @QueryParam("district") 
										    String district,
										    @QueryParam("sport") 
										    String sport,
										    @QueryParam("ground") 
										    String ground,
										    @QueryParam("day") 
										    String day,
										    @QueryParam("hour") 
										    String hour) {
		
		
		int first = (page - 1) * pageSize;

		long rowCount = stablishmentDao.rowCountBy(state, district, sport, ground, day, hour);
		List<Stablishment> stablishments = stablishmentDao.findBy(first, pageSize, state, district, sport, ground, day, hour);
			
		int pageCant = MathUtil.roundUp(new Double(rowCount)/new Double(pageSize));
		return new StablishmentCollectionPage(rowCount, pageSize, pageCant, page, stablishmentConverter.toDTO(stablishments, true));
	}
	
	@GET
	@Path("/count")
	@Produces(MediaType.APPLICATION_JSON)
	public StablishmentCollectionPage serchCount(@QueryParam("page") 
						    @DefaultValue(value = "1") 
						    int page,
						    @QueryParam("pageSize") 
						    @DefaultValue(value = "10") 
						    int pageSize,  
						    @QueryParam("state") 
						    String state,
						    @QueryParam("district") 
						    String district,
						    @QueryParam("sport") 
						    String sport,
						    @QueryParam("ground") 
						    String ground,
						    @QueryParam("day") 
						    String day,
						    @QueryParam("hour") 
						    String hour) {
		
		
		long rowCount = stablishmentDao.rowCountBy(state, district, sport, ground, day, hour);
		int pageCant = MathUtil.roundUp(new Double(rowCount)/new Double(pageSize));
		return new StablishmentCollectionPage(rowCount, pageSize, pageCant, page, null);
	}
	
	@GET
	@Path("/{id:(([0-9a-fA-F]){8}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){12}){1}}")
	@Produces(MediaType.APPLICATION_JSON)
	public StablishmentDTO read(@PathParam("id") String id, @QueryParam("lazy")@DefaultValue("true") Boolean lazy) {
		Stablishment stablishment = stablishmentDao.findById(id);
		return stablishmentConverter.toDTO(stablishment, lazy);
	}

	@GET
	@Path("/owner/{id:(([0-9a-fA-F]){8}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){12}){1}}")
	@Produces(MediaType.APPLICATION_JSON)
	public StablishmentDTO readOwner(@PathParam("id") String id, @QueryParam("lazy")@DefaultValue("true") Boolean lazy) {
		Stablishment stablishment = stablishmentDao.findByUserId(id);
		return stablishmentConverter.toDTO(stablishment, lazy);
	}
}
