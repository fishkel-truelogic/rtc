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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.com.finit.admin.web.rest.client.HttpProtocolHelper;
import ar.com.finit.admin.web.rest.client.RestElement;
import ar.com.finit.admin.web.session.UserSessionHelper;
import ar.com.finit.dto.model.GroundDTO;
import ar.com.finit.dto.model.factory.GroundDTOFactory;
import ar.com.finit.dto.model.factory.SportDTOFactory;

/**
 * @author leo
 */

@Controller
@RequestMapping("/grounds")
public class GroundController {
	
	@Autowired
	private HttpProtocolHelper httpProtocolHelper;
	@Autowired
	private UserSessionHelper userSessionHelper;
	@Autowired
	private SportDTOFactory sportDTOFactory;
	@Autowired
	private GroundDTOFactory groundDTOFactory;
	
	@RequestMapping({"/", ""})
	public ModelAndView grounds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		userSessionHelper.getUser(request, response);
		ClientResponse<String> clientResponse;
		String url;
	
		url = httpProtocolHelper.getServiceUrl(RestElement.ADMIN, RestElement.GROUND);
		clientResponse = httpProtocolHelper.getJsonRequest(url);

		if (clientResponse.getStatus() == Status.OK.getStatusCode()) {
			List<GroundDTO> grounds = (List<GroundDTO>) groundDTOFactory.makeDTOs(clientResponse.getEntity());
			params.put("grounds", grounds);
		}
		return new ModelAndView("grounds", params);
	}
	
	@RequestMapping(value = {"", "/"} , method = RequestMethod.POST)
	public ModelAndView createOrUpdate(@ModelAttribute("ground") GroundDTO ground) throws ServletException, IOException{
		if (ground.getId() == null) {
			return this.createGround(ground);
		} else {
			return this.updateGround(ground);
		}
	}

	private ModelAndView updateGround(GroundDTO ground) {
		// TODO Auto-generated method stub
		return null;
	}

	private ModelAndView createGround(GroundDTO ground) throws ServletException, IOException {
		ClientResponse<String> response;
		String url;
	
		url = httpProtocolHelper.getServiceUrl(RestElement.ADMIN, RestElement.GROUND);
		response = httpProtocolHelper.postJsonRequest(url, ground);

		if (response.getStatus() == Status.OK.getStatusCode()) {
			return this.grounds(null, null);
		}
		return this.grounds(null, null);
	}

	
	@RequestMapping("/new")
	public ModelAndView newGround() {
		Map<String, Object> params = new HashMap<String, Object>();
		return new ModelAndView("create/ground", params);
	}

}