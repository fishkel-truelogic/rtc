package ar.com.finit.owner.web.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ar.com.finit.dto.model.EventDTO;
import ar.com.finit.dto.model.FieldDTO;
import ar.com.finit.dto.model.UserDTO;
import ar.com.finit.dto.model.factory.EventDTOFactory;
import ar.com.finit.dto.model.factory.FieldDTOFactory;
import ar.com.finit.owner.web.converter.EventConverter;
import ar.com.finit.owner.web.model.CalendarModel;
import ar.com.finit.owner.web.model.EventModel;
import ar.com.finit.owner.web.rest.client.HttpProtocolHelper;
import ar.com.finit.owner.web.rest.client.RestElement;
import ar.com.finit.owner.web.session.UserSessionHelper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author leo
 */

@Controller
@RequestMapping("/calendar")
public class CalendarController {

	@Autowired
	private UserSessionHelper userSessionHelper;
	@Autowired
	private HttpProtocolHelper httpProtocolHelper;
	@Autowired
	private EventDTOFactory eventDTOFactory;
	@Autowired
	private EventConverter eventConverter;
	@Autowired
	private FieldDTOFactory fieldDTOFactory;

	@RequestMapping({ "/", "" })
	public ModelAndView calendarPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		ClientResponse<String> clientResponse;
		String url;

		UserDTO user = userSessionHelper.getUser(request, response);

		url = httpProtocolHelper.getServiceUrl(RestElement.PRIV, RestElement.FIELD) + user.getId();
		clientResponse = httpProtocolHelper.getJsonRequest(url);

		if (clientResponse.getStatus() == Status.OK.getStatusCode()) {
			List<FieldDTO> fields = (List<FieldDTO>) fieldDTOFactory.makeDTOs(clientResponse.getEntity());
			params.put("fields", fields);
		}

		params.put("entity", 0);

		return new ModelAndView("calendar", params);
	}

	@RequestMapping("/{status}")
	public ModelAndView calendarPage(@PathVariable int status, @RequestParam(value = "entity", required = false) String entity) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("entity", entity);
		params.put("status", status);
		return new ModelAndView("calendarEmbed", params);
	}

	@RequestMapping("/new")
	public ModelAndView newEvent() throws ServletException, IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		List<String> dates = new ArrayList<String>();
		for (int i = 0; i < 30; i++) {
			dates.add(format.format(calendar.getTime()));
			calendar.add(Calendar.DAY_OF_YEAR, 1);
		}
		params.put("dates", dates);
		
		ClientResponse<String> clientResponse;
		String url;

		UserDTO user = userSessionHelper.getUser(null, null);

		url = httpProtocolHelper.getServiceUrl(RestElement.PRIV, RestElement.FIELD) + user.getId();
		clientResponse = httpProtocolHelper.getJsonRequest(url);

		if (clientResponse.getStatus() == Status.OK.getStatusCode()) {
			List<FieldDTO> fields = (List<FieldDTO>) fieldDTOFactory.makeDTOs(clientResponse.getEntity());
			params.put("fields", fields);
		}
		return new ModelAndView("create/event", params);
	}

	@RequestMapping(value = "/events/{status}", method = RequestMethod.GET)
	public @ResponseBody String events(@PathVariable("status") String status, @RequestParam(value = "entity", required = false) String entity) throws ServletException, IOException {
		CalendarModel calendar = new CalendarModel();
		
		ClientResponse<String> response;
		String url;
		
		UserDTO user = userSessionHelper.getUser(null, null);
		
		String pathArgs = "?status=" + status;
		
		if (!"0".equals(entity)) {
			pathArgs += "&entity=" + entity;
		}
		
		url = httpProtocolHelper.getServiceUrl(RestElement.PRIV, RestElement.EVENT) + user.getId() + pathArgs;
		response = httpProtocolHelper.getJsonRequest(url);
		
		if (response.getStatus() == Status.OK.getStatusCode()) {
			List<EventDTO> events = (List<EventDTO>) eventDTOFactory.makeDTOs(response.getEntity());
			calendar.setData(events);
		}
		
		Gson gson = new Gson();
		return gson.toJson(calendar, new TypeToken<CalendarModel>() {}.getType());
	}

	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	public @ResponseBody String cancel(@RequestParam(value = "id", required = true) String id) throws ServletException, IOException {
		
		ClientResponse<String> response;
		String url;
		
		url = httpProtocolHelper.getServiceUrl(RestElement.PRIV, RestElement.EVENT) + "cancel/" + id;
		response = httpProtocolHelper.deleteJsonRequest(url);
		
		if (response.getStatus() == Status.OK.getStatusCode()) {
		}

		return "ok";
	}

	@RequestMapping(value = "/prepayment", method = RequestMethod.GET)
	public @ResponseBody String prepayment(@RequestParam(value = "id", required = true) String id) {
		
		ClientResponse<String> response;
		String url;
		
		url = httpProtocolHelper.getServiceUrl(RestElement.PRIV, RestElement.EVENT) + id;
		response = httpProtocolHelper.getJsonRequest(url);
		
		if (response.getStatus() == Status.OK.getStatusCode()) {
			EventDTO event = eventDTOFactory.makeDTO(response.getEntity());
			event.setStatus(EventDTO.NO_PLAYED_YET);
			response = httpProtocolHelper.postJsonRequest(url, event);
		}
		
		return "ok";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody String save(@ModelAttribute("event") EventModel event) throws ServletException, IOException {
		
		ClientResponse<String> response;
		String url;
		
		UserDTO user = userSessionHelper.getUser(null, null);
		
		EventDTO dto = eventConverter.toDTO(event);
		dto.setUserId(user.getId());
		
		url = httpProtocolHelper.getServiceUrl(RestElement.PRIV, RestElement.EVENT);
		response = httpProtocolHelper.postJsonRequest(url, dto);
//		
		if (response.getStatus() == Status.OK.getStatusCode()) {
//			EventDTO eventDto = eventDTOFactory.makeDTO(response.getEntity());
//			eventDto.setStatus(EventDTO.NO_PLAYED_YET);
//			response = httpProtocolHelper.postJsonRequest(url, eventDto);
		}
//		
		return "ok";
	}

	

}