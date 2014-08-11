package ar.com.finit.service.rest.pub;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import ar.com.finit.core.model.District;
import ar.com.finit.core.model.dao.DistrictDao;
import ar.com.finit.dto.model.DistrictDTO;
import ar.com.finit.service.model.converter.DistrictConverter;
import ar.com.finit.service.rest.utils.EntityPath;

/**
 * @author leo
 */
@Configurable
@Path(EntityPath.PUBLIC + EntityPath.DISTRICTS)
public class DistrictPublicService {

	@Autowired
	public DistrictDao districtDao;
	@Autowired
	public DistrictConverter districtConverter;

	@GET
	@Path(value = "/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DistrictDTO> byState(@QueryParam("state") String state) {
		List<District> districts = this.districtDao.findByState(state);
		return districtConverter.toDTO(districts);
	}
	
}
