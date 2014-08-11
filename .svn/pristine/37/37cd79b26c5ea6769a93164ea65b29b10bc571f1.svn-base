package ar.com.finit.service.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import ar.com.finit.service.rest.utils.MapServices;

@Configurable
@ApplicationPath("/")
public class JaxRsActivator extends Application {
	
	@Autowired
	private MapServices mapServices;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public MapServices home(){
		return mapServices;
	}
}