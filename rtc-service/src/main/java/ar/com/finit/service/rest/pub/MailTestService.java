package ar.com.finit.service.rest.pub;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import ar.com.finit.service.mail.MailService;
import ar.com.finit.service.rest.utils.EntityPath;

/**
 * @author leo
 */
@Configurable
@Path(EntityPath.PUBLIC + "mail")
public class MailTestService {
	@Autowired
	private MailService mailService;
	
	

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public String mailTest() {
		mailService.send("leofishkel@hotmail.com", "Test de envío de email.", "Prueba del envío de correo electrónico.");  
		return "ok";
	}
}
