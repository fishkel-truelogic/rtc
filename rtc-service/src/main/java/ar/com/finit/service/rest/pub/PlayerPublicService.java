package ar.com.finit.service.rest.pub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Variant;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import ar.com.finit.core.model.Player;
import ar.com.finit.core.model.dao.PlayerDao;
import ar.com.finit.core.model.impl.PlayerImpl;
import ar.com.finit.dto.model.PlayerDTO;
import ar.com.finit.dto.model.factory.PlayerDTOFactory;
import ar.com.finit.dto.violation.ViolationDTO;
import ar.com.finit.service.mail.MailService;
import ar.com.finit.service.model.converter.PlayerConverter;
import ar.com.finit.service.model.converter.violation.PlayerViolationConverter;
import ar.com.finit.service.rest.utils.EntityPath;

/**
 * @author leo
 */

@Configurable
@Path(EntityPath.PUBLIC + EntityPath.PLAYERS)
public class PlayerPublicService {
	private Logger logger = Logger.getLogger(PlayerPublicService.class);

	@Autowired
	private PlayerDTOFactory playerDTOFactory;
	@Autowired
	private PlayerConverter playerConverter;
	@Autowired
	private Validator validator;
	@Autowired
	private PlayerViolationConverter playerViolationConverter;
	@Autowired
	private PlayerDao playerDao;
	@Autowired
	private MailService mailService;

	@GET
	@Path(value = "/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@QueryParam("fb") String fb, @QueryParam("login") String login, @QueryParam("password") String password) {
		PlayerDTO player = new PlayerDTO();
		if (fb == null) {
			player = playerConverter.toDTO(playerDao.login(login, password));
		} else {
			player = playerConverter.toDTO(playerDao.findByFbId(fb));
		}
		if (player == null) {
			logger.info("Nombre de usuario o contraseña incorrecta");
			ViolationDTO vdto = new ViolationDTO();
			vdto.setMessage("Nombre de usuario o Contraseña incorrecta");
			vdto.setStatus(Status.NOT_ACCEPTABLE.getStatusCode());
			List<Variant> variants = new ArrayList<Variant>();
			variants.add(new Variant(MediaType.APPLICATION_JSON_TYPE, Locale.forLanguageTag("es"), "UTF-8"));
			return Response.notAcceptable(variants).entity(vdto).build();
		} 
		
		if (!player.isEnabled()) {
			logger.info("El usuario está deshabilitado");
			ViolationDTO vdto = new ViolationDTO();
			vdto.setMessage("El usuario se encuentra deshabilitado por el sistema");
			vdto.setStatus(Status.NOT_ACCEPTABLE.getStatusCode());
			List<Variant> variants = new ArrayList<Variant>();
			variants.add(new Variant(MediaType.APPLICATION_JSON_TYPE, Locale.forLanguageTag("es"), "UTF-8"));
			return Response.notAcceptable(variants).entity(vdto).build();
		}
		
		return Response.ok(player).build();
		
	}
	
	@GET
	@Path("/{id:(([0-9a-fA-F]){8}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){12}){1}}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") String id) {
		Player player = playerDao.findById(id);
		if (player == null) {
			return Response.noContent().build();
		} else {
			return Response.ok(playerConverter.toDTO(player)).build();
		}
	}
	
	@GET
	@Path("/sendmail")
	@Produces(MediaType.APPLICATION_JSON)
	public Response sendMail(@QueryParam("email") String email, @QueryParam("username") String username, @QueryParam("id") String id) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("username", username);
		param.put("email", email);
		param.put("id", id);
		mailService.send(email, "Confirmación de email.", mailService.getTemplateAsString("templates/mail/emailConfirmation.html", param));  
		return Response.ok("ok").build();
	}
	
	@POST
	@Path("/{id:(([0-9a-fA-F]){8}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){12}){1}}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") String id, String json) {
		PlayerDTO playerDto = playerDTOFactory.makeDTO(json);
		PlayerImpl player = (PlayerImpl) playerDao.findById(playerDto.getId());
		Player playerSameEmail = playerDao.findByEmail(playerDto.getEmail());
		player.setEmail(playerDto.getEmail());
		if (playerSameEmail != null) {
			logger.info("Ya existe un usuario con ese email");
			ViolationDTO vdto = new ViolationDTO();
			vdto.setInvalidValue(playerDto.getUsername());
			vdto.setMessage("Correo electrónico existente");
			vdto.setStatus(Status.NOT_ACCEPTABLE.getStatusCode());
			List<Variant> variants = new ArrayList<Variant>();
			variants.add(new Variant(MediaType.APPLICATION_JSON_TYPE, Locale.forLanguageTag("es"), "UTF-8"));
			return Response.notAcceptable(variants).entity(vdto).build();
		} else {
			playerDao.update(player);
			return Response.ok().build();
		}
	}
	
	@GET
	@Path("/{id:([0-9])+}")
	@Produces(MediaType.APPLICATION_JSON)
	public PlayerDTO getFb(@PathParam("id") String id) {
		Player player = playerDao.findByFbId(id);
		if (player == null) {
			return new PlayerDTO();
		} else {
			return playerConverter.toDTO(player);
		}
	}
	

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(String json) {
		PlayerDTO playerDto = playerDTOFactory.makeDTO(json);
		Player player = playerConverter.toEntity(playerDto);
		Player playerSameEmail = playerDao.findByEmail(playerDto.getEmail());
		
		if (playerDto.getFbId() == null) {
			logger.info("No es usuario de facebook");
			if (playerDao.existsUsername(playerDto.getUsername())) {
				logger.info("Ya existe un usuario con ese username");
				ViolationDTO vdto = new ViolationDTO();
				vdto.setInvalidValue(playerDto.getUsername());
				vdto.setMessage("Nombre de usuario existente");
				vdto.setStatus(Status.NOT_ACCEPTABLE.getStatusCode());
				List<Variant> variants = new ArrayList<Variant>();
				variants.add(new Variant(MediaType.APPLICATION_JSON_TYPE, Locale.forLanguageTag("es"), "UTF-8"));
				return Response.notAcceptable(variants).entity(vdto).build();
			} else if (playerSameEmail != null) {
				logger.info("Ya existe un usuario con ese email");
				ViolationDTO vdto = new ViolationDTO();
				vdto.setInvalidValue(playerDto.getUsername());
				vdto.setMessage("Correo electrónico existente");
				vdto.setStatus(Status.NOT_ACCEPTABLE.getStatusCode());
				List<Variant> variants = new ArrayList<Variant>();
				variants.add(new Variant(MediaType.APPLICATION_JSON_TYPE, Locale.forLanguageTag("es"), "UTF-8"));
				return Response.notAcceptable(variants).entity(vdto).build();
			}
		}
		playerDao.save(player);
		return Response.ok(playerConverter.toDTO(player)).build();
	}
	
}
