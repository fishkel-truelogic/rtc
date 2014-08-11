package ar.com.finit.owner.web.controller;

import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.client.ClientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.finit.owner.web.rest.client.HttpProtocolHelper;
import ar.com.finit.owner.web.rest.client.RestElement;

/**
 * @author leo
 */
@Controller
@RequestMapping("/district")
public class DistrictController {
	
	@Autowired
	private HttpProtocolHelper httpProtocolHelper;

	@RequestMapping(value = "/{state}", method = RequestMethod.GET)
	public @ResponseBody String images(@PathVariable String state) {
		ClientResponse<String> response;
		String url;
		
		url = httpProtocolHelper.getServiceUrl(RestElement.PUB, RestElement.DISTRICT) + "?state=" + state;
		response = httpProtocolHelper.getJsonRequest(url);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return response.getEntity();
		}
		return null;
	}
}
