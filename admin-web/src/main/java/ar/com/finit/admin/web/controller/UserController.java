package ar.com.finit.admin.web.controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.client.ClientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.com.finit.admin.web.converter.UserConverter;
import ar.com.finit.admin.web.model.UserModel;
import ar.com.finit.admin.web.rest.client.HttpProtocolHelper;
import ar.com.finit.admin.web.rest.client.RestElement;
import ar.com.finit.admin.web.session.UserSessionHelper;
import ar.com.finit.dto.model.RoleDTO;
import ar.com.finit.dto.model.StablishmentDTO;
import ar.com.finit.dto.model.UserDTO;
import ar.com.finit.dto.model.factory.RoleDTOFactory;
import ar.com.finit.dto.model.factory.StablishmentDTOFactory;
import ar.com.finit.dto.model.factory.UserDTOFactory;

/**
 * @author leo
 */

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private HttpProtocolHelper httpProtocolHelper;
	@Autowired
	private UserSessionHelper userSessionHelper;
	@Autowired
	private UserDTOFactory userDTOFactory;
	@Autowired
	private StablishmentDTOFactory stablishmentDTOFactory;
	@Autowired
	private RoleDTOFactory roleDTOFactory;
	@Autowired
	private UserConverter userConverter;

	@RequestMapping({ "/", "" })
	public ModelAndView users(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		userSessionHelper.getUser(request, response);
		ClientResponse<String> clientResponse;
		String url;

		url = httpProtocolHelper.getServiceUrl(RestElement.ADMIN, RestElement.USER);
		clientResponse = httpProtocolHelper.getJsonRequest(url);

		if (clientResponse.getStatus() == Status.OK.getStatusCode()) {
			List<UserDTO> users = (List<UserDTO>) userDTOFactory.makeDTOs(clientResponse.getEntity());
			params.put("users", users);
		}
		return new ModelAndView("users", params);
	}

	@RequestMapping("/details/{id}")
	public ModelAndView userDetails(@PathVariable String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		ClientResponse<String> clientResponse;
		String url;
		
		url = httpProtocolHelper.getServiceUrl(RestElement.PRIV, RestElement.STABLISHMENT) + id + "?lazy=false";
		clientResponse = httpProtocolHelper.getJsonRequest(url);
		
		if (clientResponse.getStatus() == Status.OK.getStatusCode()) {
			StablishmentDTO stablishment =  stablishmentDTOFactory.makeDTO(clientResponse.getEntity());
			params.put("stablishment", stablishment);
		}
		return new ModelAndView("userDetails", params);
	}

	@RequestMapping("/new")
	public ModelAndView newUser() {
		Map<String, Object> params = new HashMap<String, Object>();
		ClientResponse<String> response;
		String url;

		url = httpProtocolHelper.getServiceUrl(RestElement.ADMIN, RestElement.ROLE);
		response = httpProtocolHelper.getJsonRequest(url);

		if (response.getStatus() == Status.OK.getStatusCode()) {
			List<RoleDTO> roles = (List<RoleDTO>) roleDTOFactory.makeDTOs(response.getEntity());
			params.put("roles", roles);
		}
		return new ModelAndView("create/user", params);
	}
	
	@RequestMapping(value = {"", "/"} , method = RequestMethod.POST)
	public ModelAndView createOrUpdate(@ModelAttribute("user") UserModel user) throws ServletException, IOException{
		if (user.getId() == null) {
			return this.createUser(user);
		} else {
			return this.updateUser(user);
		}
	}

	private ModelAndView updateUser(UserModel user) {
		UserDTO userDto = userConverter.toDTO(user);
		return null;
	}

	private ModelAndView createUser(UserModel user) throws ServletException, IOException {
		UserDTO userDto = userConverter.toDTO(user);
		ClientResponse<String> response;
		String url;
	
		url = httpProtocolHelper.getServiceUrl(RestElement.ADMIN, RestElement.USER);
		response = httpProtocolHelper.postJsonRequest(url, userDto);

		if (response.getStatus() == Status.OK.getStatusCode()) {
			return this.users(null, null);
		}
		return this.users(null, null);
	}

}