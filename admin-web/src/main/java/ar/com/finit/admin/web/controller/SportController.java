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
import ar.com.finit.dto.model.SportDTO;
import ar.com.finit.dto.model.factory.SportDTOFactory;

/**
 * @author leo
 */

@Controller
@RequestMapping("/sports")
public class SportController {
	
	@Autowired
	private HttpProtocolHelper httpProtocolHelper;
	@Autowired
	private UserSessionHelper userSessionHelper;
	@Autowired
	private SportDTOFactory sportDTOFactory;
	
	@RequestMapping({"/", ""})
	public ModelAndView sports(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		userSessionHelper.getUser(request, response);
		ClientResponse<String> clientResponse;
		String url;
	
		url = httpProtocolHelper.getServiceUrl(RestElement.ADMIN, RestElement.SPORT);
		clientResponse = httpProtocolHelper.getJsonRequest(url);

		if (clientResponse.getStatus() == Status.OK.getStatusCode()) {
			List<SportDTO> sports = (List<SportDTO>) sportDTOFactory.makeDTOs(clientResponse.getEntity());
			params.put("sports", sports);
		}
		return new ModelAndView("sports", params);
	}
	
	@RequestMapping(value = {"", "/"} , method = RequestMethod.POST)
	public ModelAndView createOrUpdate(@ModelAttribute("sport") SportDTO sport) throws ServletException, IOException{
		if (sport.getId() == null) {
				return this.createSport(sport);
			} else {
				return this.updateSport(sport);
			}
	}

	private ModelAndView updateSport(SportDTO sport) {
		// TODO Auto-generated method stub
		return null;
	}

	private ModelAndView createSport(SportDTO sport) throws ServletException, IOException {
		ClientResponse<String> response;
		String url;
	
		url = httpProtocolHelper.getServiceUrl(RestElement.ADMIN, RestElement.SPORT);
		response = httpProtocolHelper.postJsonRequest(url, sport);

		if (response.getStatus() == Status.CREATED.getStatusCode()) {
			return this.sports(null, null);
		}
		return this.sports(null, null);
	}


	
	@RequestMapping("/new")
	public ModelAndView newSport() {
		Map<String, Object> params = new HashMap<String, Object>();
		return new ModelAndView("create/sport", params);
	}

}