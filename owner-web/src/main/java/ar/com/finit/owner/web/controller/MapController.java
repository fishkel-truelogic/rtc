package ar.com.finit.owner.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.jboss.resteasy.client.ClientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ar.com.finit.dto.model.MapMarkerDTO;
import ar.com.finit.dto.model.factory.MapMarkerDTOFactory;
import ar.com.finit.owner.web.rest.client.HttpProtocolHelper;
import ar.com.finit.owner.web.rest.client.RestElement;

/**
 * @author leo
 */

@Controller
@RequestMapping("/map")
public class MapController {
	
	@Autowired
	private HttpProtocolHelper httpProtocolHelper;
	@Autowired
	private MapMarkerDTOFactory mapMarkerDTOFactory;

	@RequestMapping({"embedSmall/", "embedSmall"})
	public ModelAndView smallMap(@RequestParam(value = "lat", required = true) String lat,
							@RequestParam(value = "lng", required = true) String lng) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("lat", lat);
		params.put("lng", lng);
		
		return new ModelAndView("embedSmallMap", params);
	}

	@RequestMapping("/edit/{mapMarker}")
	public ModelAndView editMap(@PathVariable("mapMarker") String mapMarker, 
			@RequestParam(value = "lat", required = false) String lat,
			@RequestParam(value = "lng", required = false) String lng,
			@RequestParam(value = "entity", required = true) String entity) {
		
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("mmId", mapMarker);
		if (lat != null && lng != null) {
			params.put("lat", lat);
			params.put("lng", lng);
		}
		params.put("entity", entity);
		
		return new ModelAndView("editMap", params);
	}

	@RequestMapping(value = "/" , method = RequestMethod.POST)
	public @ResponseBody String post(@RequestParam(value = "lat", required = true) String lat,
			@RequestParam(value = "lng", required = true) String lng,
			@RequestParam(value = "mmId", required = true) String mmId,
		    @RequestParam(value = "entity", required = true) String entity) {
		
		ClientResponse<String> response;
		String url;
		
		url = httpProtocolHelper.getServiceUrl(RestElement.PRIV, RestElement.MAP_MARKER) + mmId;
		response = httpProtocolHelper.getJsonRequest(url);
		
		MapMarkerDTO mapMarker = mapMarkerDTOFactory.makeDTO(response.getEntity());
		mapMarker.setLat(new Double(lat));
		mapMarker.setLng(new Double(lng));
		mapMarker.setEntity(entity);

		url = httpProtocolHelper.getServiceUrl(RestElement.PRIV, RestElement.MAP_MARKER) + mmId;
		response = httpProtocolHelper.postJsonRequest(url, mapMarker);
		
		return "ok";
	}
	
	@RequestMapping(value = "/{mapMarker}", method = RequestMethod.GET)
	public ModelAndView map(@PathVariable String mapMarker,
							@RequestParam(value = "entity", required = true) String entity) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		ClientResponse<String> response;
		String url;
		
		url = httpProtocolHelper.getServiceUrl(RestElement.PRIV, RestElement.MAP_MARKER) + mapMarker;
		response = httpProtocolHelper.getJsonRequest(url);
		
		MapMarkerDTO mapMarkerDTO = mapMarkerDTOFactory.makeDTO(response.getEntity());
		
		if (mapMarkerDTO.getLat() != null && mapMarkerDTO.getLng() != null) {
			params.put("lat", mapMarkerDTO.getLat());
			params.put("lng", mapMarkerDTO.getLng());
		}
		params.put("mapMarker", mapMarker);
		params.put("entity", entity);
		
		return new ModelAndView("dialog/mapEditDialog", params);
	}

}