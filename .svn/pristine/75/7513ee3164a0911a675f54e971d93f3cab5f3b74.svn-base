package ar.com.finit.service.rest.pub;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import ar.com.finit.core.model.TpService;
import ar.com.finit.core.model.dao.TpServiceDao;
import ar.com.finit.service.model.converter.TpServiceConverter;
import ar.com.finit.service.rest.utils.EntityPath;

/**
 * @author leo
 */

@Configurable
@Path(EntityPath.PUBLIC + EntityPath.TP_SERVICES)
public class TpServicePublicService {

	@Autowired
	private TpServiceDao tpServiceDao;
	@Autowired
	private TpServiceConverter tpServiceConverter;

	@GET
	@Path("/{id:(([0-9a-fA-F]){8}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){12}){1}}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response read(@PathParam("id") String id, @QueryParam("lazy") @DefaultValue("true") boolean lazy) {
		TpService tpService = this.tpServiceDao.findById(id);
		return Response.ok(tpServiceConverter.toDTO(tpService, lazy)).build();
	}

}