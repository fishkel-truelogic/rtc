package ar.com.finit.service.rest.pub.qd;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import ar.com.finit.core.model.qd.Lugar;
import ar.com.finit.core.model.qd.dao.LugarDao;
import ar.com.finit.dto.model.qd.LugarDTO;
import ar.com.finit.service.model.converter.qd.LugarConverter;
import ar.com.finit.service.rest.utils.EntityPath;

/**
 * @author leo
 */
@Configurable
@Path(EntityPath.PUBLIC + EntityPath.LUGARES)
public class LugarService {

	@Autowired
	public LugarDao lugarDao;
	@Autowired
	public LugarConverter lugarConverter;

	@GET
	@Path(value = "/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<LugarDTO> findAll() {
		List<Lugar> lugares = this.lugarDao.findAll();
		return lugarConverter.toDTO(lugares);
	}
	
}
