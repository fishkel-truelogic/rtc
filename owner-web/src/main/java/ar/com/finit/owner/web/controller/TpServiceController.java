package ar.com.finit.owner.web.controller;

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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.com.finit.dto.model.TpServiceDTO;
import ar.com.finit.dto.model.UserDTO;
import ar.com.finit.dto.model.factory.TpServiceDTOFactory;
import ar.com.finit.owner.web.rest.client.HttpProtocolHelper;
import ar.com.finit.owner.web.rest.client.RestElement;
import ar.com.finit.owner.web.session.UserSessionHelper;

/**
 * @author leo
 */

@Controller
@Scope("request")
@RequestMapping("/services")
public class TpServiceController {

	@Autowired
	private UserSessionHelper userSessionHelper;
	@Autowired
	private HttpProtocolHelper httpProtocolHelper;
	@Autowired
	private TpServiceDTOFactory tpServiceDTOFactory;
	
	@RequestMapping({"/" , ""})
	public ModelAndView services(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		ClientResponse<String> clientResponse;
		String url;

		UserDTO user = userSessionHelper.getUser(request, response);

		url = httpProtocolHelper.getServiceUrl(RestElement.PRIV, RestElement.TP_SERVICE) + user.getId();
		clientResponse = httpProtocolHelper.getJsonRequest(url);

		if (clientResponse.getStatus() == Status.OK.getStatusCode()) {
			List<TpServiceDTO> tpServices = (List<TpServiceDTO>) tpServiceDTOFactory.makeDTOs(clientResponse.getEntity());
			params.put("tpServices", tpServices);
			return new ModelAndView("tpServices", params);
		}
		return new ModelAndView("tpServices", params);
	}

	@RequestMapping(value = {"/" , ""}, method = RequestMethod.POST)
	public ModelAndView createOrUpdate(@ModelAttribute("tpService") TpServiceDTO tpService) throws ServletException, IOException{
		if (tpService.getId() == null) return this.create(tpService);
		return this.update(tpService);
	}
	
	@RequestMapping("/new")
	public ModelAndView tpService() {
		Map<String, Object> params = new HashMap<String, Object>();
		return new ModelAndView("create/tpService", params);
	}

	@RequestMapping("/edit")
	public ModelAndView editDialog(@RequestParam(value = "id", required = true) String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		ClientResponse<String> response;
		String url;

		url = httpProtocolHelper.getServiceUrl(RestElement.PUB, RestElement.TP_SERVICE) + id;
		response = httpProtocolHelper.getJsonRequest(url);

		if (response.getStatus() == Status.OK.getStatusCode()) {
			TpServiceDTO tpService = tpServiceDTOFactory.makeDTO(response.getEntity());
			params.put("tpService", tpService);
		}
		
		return new ModelAndView("edit/tpService", params);
	}

	@RequestMapping("/delete")
	public ModelAndView deleteConfirmation(@RequestParam(value = "id", required = true) String id, @RequestParam(value = "name", required = true) String name) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("name", name);
		return new ModelAndView("dialog/deleteTpService", params);
	}

	@RequestMapping(value = "/delete/{tpService}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String tpService) {
		ClientResponse<String> response;
		String url;

		url = httpProtocolHelper.getServiceUrl(RestElement.PRIV, RestElement.TP_SERVICE) + tpService;
		response = httpProtocolHelper.deleteJsonRequest(url);
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		
	}
	
	private ModelAndView update(TpServiceDTO tpService) throws ServletException, IOException {
		ClientResponse<String> response;
		String url;

		url = httpProtocolHelper.getServiceUrl(RestElement.PRIV, RestElement.TP_SERVICE) + tpService.getId();
		response = httpProtocolHelper.postJsonRequest(url, tpService);
		
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return this.services(null, null);
		}
		
		return this.services(null, null);
	}
	
	private ModelAndView create(TpServiceDTO tpService) throws ServletException, IOException {
		ClientResponse<String> response;
		String url;
		
		String userId = this.userSessionHelper.getUser(null, null).getId();
		tpService.setUserId(userId);
		
		url = httpProtocolHelper.getServiceUrl(RestElement.PRIV, RestElement.TP_SERVICE);
		response = httpProtocolHelper.postJsonRequest(url, tpService);

		if (response.getStatus() == Status.OK.getStatusCode()) {
			return this.services(null, null);
		}
		return this.services(null, null);
	}

}