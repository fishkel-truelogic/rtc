package ar.com.finit.owner.web.controller;

import java.io.IOException;
import java.util.ArrayList;
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

import ar.com.finit.dto.model.DistrictDTO;
import ar.com.finit.dto.model.StablishmentDTO;
import ar.com.finit.dto.model.StateDTO;
import ar.com.finit.dto.model.UserDTO;
import ar.com.finit.dto.model.factory.DistrictDTOFactory;
import ar.com.finit.dto.model.factory.StablishmentDTOFactory;
import ar.com.finit.dto.model.factory.StateDTOFactory;
import ar.com.finit.dto.model.qd.LugarDTO;
import ar.com.finit.dto.model.qd.factory.LugarDTOFactory;
import ar.com.finit.owner.web.rest.client.HttpProtocolHelper;
import ar.com.finit.owner.web.rest.client.RestElement;
import ar.com.finit.owner.web.session.UserSessionHelper;

/**
 * @author leo
 */

@Controller
@RequestMapping("/stablishment")
public class StablishmentController {

	@Autowired
	private UserSessionHelper userSessionHelper;
	@Autowired
	private HttpProtocolHelper httpProtocolHelper;
	@Autowired
	private StablishmentDTOFactory stablishmentDTOFactory;
	@Autowired
	private StateDTOFactory stateDTOFactory;
	@Autowired
	private LugarDTOFactory lugarDTOFactory;
	@Autowired
	private DistrictDTOFactory districtDTOFactory;

	@RequestMapping({ "/", "" })
	public ModelAndView stablishment(HttpServletRequest request, HttpServletResponse response2) throws ServletException, IOException {

		Map<String, Object> params = new HashMap<String, Object>();
		ClientResponse<String> response;
		String url;

		UserDTO user = userSessionHelper.getUser(request, response2);

		url = httpProtocolHelper.getServiceUrl(RestElement.PRIV, RestElement.STABLISHMENT) + user.getId();
		response = httpProtocolHelper.getJsonRequest(url);

		if (response.getStatus() == Status.OK.getStatusCode()) {
			StablishmentDTO stablishment = stablishmentDTOFactory.makeDTO(response.getEntity());
			if (stablishment != null) {
				params.put("stablishment", stablishment);
				return new ModelAndView("stablishment", params);
			} 
			return newStablishment();
			
		} else {
			params.put("error", response.getStatus());
		}
		return new ModelAndView("stablishment", params);
	}
	
	@RequestMapping("/edit")
	public ModelAndView stablishmentEdit() throws ServletException, IOException {
		
		Map<String, Object> params = new HashMap<String, Object>();
		ClientResponse<String> response;
		String url;
		
		UserDTO user = userSessionHelper.getUser(null, null);
		
		url = httpProtocolHelper.getServiceUrl(RestElement.PRIV, RestElement.STABLISHMENT) + user.getId();
		response = httpProtocolHelper.getJsonRequest(url);
		
		if (response.getStatus() == Status.OK.getStatusCode()) {
			
			StablishmentDTO stablishment = stablishmentDTOFactory.makeDTO(response.getEntity());
			if (stablishment != null) {
				params.put("stablishment", stablishment);
				this.addDistrictParams(params);
				return new ModelAndView("edit/stablishment", params);
			} 
			return newStablishment();
			
		} else {
			params.put("error", response.getStatus());
		}
		
		return new ModelAndView("stablishment", params);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView createOrUpdate(@ModelAttribute("stablishment")StablishmentDTO stablishment) throws ServletException, IOException{
		if (stablishment.getId() == null) return this.create(stablishment);
		return this.update(stablishment);
	}
	
	private ModelAndView newStablishment() {
		Map<String, Object> params = new HashMap<String, Object>();
		this.addDistrictParams(params);
//		this.addLugares(params);
		params.put("stablishment", new StablishmentDTO());
		return new ModelAndView("create/stablishment", params);
	}

	private void addLugares(Map<String, Object> params) {
		ClientResponse<String> response;
		String url;
		url = httpProtocolHelper.getServiceUrl(RestElement.PUB, RestElement.LUGAR);
		response = httpProtocolHelper.getJsonRequest(url);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			List<LugarDTO> lugares = (List<LugarDTO>) lugarDTOFactory.makeDTOs(response.getEntity());
			params.put("lugares", lugares);
		}
		
	}

	private void addDistrictParams(Map<String, Object> params) {
		ClientResponse<String> response;
		String url;
		url = httpProtocolHelper.getServiceUrl(RestElement.PUB, RestElement.STATE);
		response = httpProtocolHelper.getJsonRequest(url);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			List<StateDTO> states = (List<StateDTO>) stateDTOFactory.makeDTOs(response.getEntity());
			params.put("states", states);
			url = httpProtocolHelper.getServiceUrl(RestElement.PUB, RestElement.DISTRICT) + "?state=" + states.iterator().next().getName();
			response = httpProtocolHelper.getJsonRequest(url);
			if (response.getStatus() == Status.OK.getStatusCode()) {
				List<DistrictDTO> districts = new ArrayList<DistrictDTO>();
				districts.addAll(districtDTOFactory.makeDTOs(response.getEntity()));
				params.put("districts", districts);
			}
		}
	}
	
	private ModelAndView create(StablishmentDTO stablishment) throws ServletException, IOException {
		ClientResponse<String> response;
		String url;
		
		String userId = this.userSessionHelper.getUser(null, null).getId();
		stablishment.setUserId(userId);
		
		url = httpProtocolHelper.getServiceUrl(RestElement.PRIV, RestElement.STABLISHMENT);
		response = httpProtocolHelper.postJsonRequest(url, stablishment);

		if (response.getStatus() == Status.OK.getStatusCode()) {
			return this.stablishment(null, null);
		}
		
		return newStablishment();
	}

	private ModelAndView update(StablishmentDTO stablishment) throws ServletException, IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		ClientResponse<String> response;
		String url;

		url = httpProtocolHelper.getServiceUrl(RestElement.PRIV, RestElement.STABLISHMENT) + stablishment.getId();
		response = httpProtocolHelper.postJsonRequest(url, stablishment);
		
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return this.stablishment(null, null);
		}
		
		this.addDistrictParams(params);
		params.put("stablishment", stablishment);
		return new ModelAndView("edit/stablishment", params);
	}

}