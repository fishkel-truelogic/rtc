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
import ar.com.finit.dto.model.DistrictDTO;
import ar.com.finit.dto.model.StateDTO;
import ar.com.finit.dto.model.factory.DistrictDTOFactory;
import ar.com.finit.dto.model.factory.StateDTOFactory;

/**
 * @author leo
 */

@Controller
@RequestMapping("/districts")
public class DistrictController {
	
	@Autowired
	private HttpProtocolHelper httpProtocolHelper;
	@Autowired
	private UserSessionHelper userSessionHelper;
	@Autowired
	private StateDTOFactory stateDTOFactory;
	@Autowired
	private DistrictDTOFactory districtDTOFactory;
	
	@RequestMapping({"/", ""})
	public ModelAndView districts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		userSessionHelper.getUser(request, response);
		ClientResponse<String> clientResponse;
		String url;
	
		url = httpProtocolHelper.getServiceUrl(RestElement.ADMIN, RestElement.DISTRICT);
		clientResponse = httpProtocolHelper.getJsonRequest(url);

		if (clientResponse.getStatus() == Status.OK.getStatusCode()) {
			List<DistrictDTO> districts = (List<DistrictDTO>) districtDTOFactory.makeDTOs(clientResponse.getEntity());
			params.put("districts", districts);
		}
		return new ModelAndView("districts", params);
	}
	
	@RequestMapping(value = {"", "/"} , method = RequestMethod.POST)
	public ModelAndView createOrUpdate(@ModelAttribute("district") DistrictDTO district) throws ServletException, IOException{
		if (district.getId() == null) {
			if (district.getName() == null) {
				if (district.getState().getId() == null) {
					return this.createState(district.getState());
				} else {
					return this.updateState(district.getState());
				}
			} else {
				return this.createDistrict(district);
			}
		} else {
			return this.updateDistrictt(district);
		}
	}

	private ModelAndView createDistrict(DistrictDTO district) throws ServletException, IOException {
		ClientResponse<String> response;
		String url;
	
		url = httpProtocolHelper.getServiceUrl(RestElement.ADMIN, RestElement.DISTRICT);
		response = httpProtocolHelper.postJsonRequest(url, district);

		if (response.getStatus() == Status.OK.getStatusCode()) {
			return this.districts(null, null);
		}
		return this.districts(null, null);
	}

	private ModelAndView updateState(StateDTO state) {
		// TODO Auto-generated method stub
		return null;
	}

	private ModelAndView createState(StateDTO state) throws ServletException, IOException {
		ClientResponse<String> response;
		String url;
	
		url = httpProtocolHelper.getServiceUrl(RestElement.ADMIN, RestElement.STATE);
		response = httpProtocolHelper.postJsonRequest(url, state);

		if (response.getStatus() == Status.OK.getStatusCode()) {
			return this.districts(null, null);
		}
		return this.districts(null, null);
	}

	private ModelAndView updateDistrictt(DistrictDTO district) {
		// TODO Auto-generated method stub
		return null;
	}

	@RequestMapping("/new/state")
	public ModelAndView newState() {
		Map<String, Object> params = new HashMap<String, Object>();
		return new ModelAndView("create/state", params);
	}
	
	@RequestMapping("/new/district")
	public ModelAndView newDistrict() {
		Map<String, Object> params = new HashMap<String, Object>();
		ClientResponse<String> response;
		String url;
	
		url = httpProtocolHelper.getServiceUrl(RestElement.ADMIN, RestElement.STATE);
		response = httpProtocolHelper.getJsonRequest(url);

		if (response.getStatus() == Status.OK.getStatusCode()) {
			List<StateDTO> states = (List<StateDTO>) stateDTOFactory.makeDTOs(response.getEntity());
			params.put("states", states);
		}
		return new ModelAndView("create/district", params);
	}

}