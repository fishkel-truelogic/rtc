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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.com.finit.dto.model.FieldDTO;
import ar.com.finit.dto.model.GroundDTO;
import ar.com.finit.dto.model.SportDTO;
import ar.com.finit.dto.model.UserDTO;
import ar.com.finit.dto.model.factory.FieldDTOFactory;
import ar.com.finit.dto.model.factory.GroundDTOFactory;
import ar.com.finit.dto.model.factory.SportDTOFactory;
import ar.com.finit.owner.web.converter.FieldConverter;
import ar.com.finit.owner.web.model.FieldModel;
import ar.com.finit.owner.web.rest.client.HttpProtocolHelper;
import ar.com.finit.owner.web.rest.client.RestElement;
import ar.com.finit.owner.web.session.UserSessionHelper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author leo
 */

@Controller
@RequestMapping("/fields")
public class FieldController {

	@Autowired
	private UserSessionHelper userSessionHelper;
	@Autowired
	private HttpProtocolHelper httpProtocolHelper;
	@Autowired
	private SportDTOFactory sportDTOFactory;
	@Autowired
	private FieldDTOFactory fieldDTOFactory;
	@Autowired
	private GroundDTOFactory groundDTOFactory;
	@Autowired
	private FieldConverter fieldConverter;
	
	
	@RequestMapping({"/" , ""})
	public ModelAndView fields(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		ClientResponse<String> clientResponse;
		String url;
		
		UserDTO user = userSessionHelper.getUser(request, response);
		
		url = httpProtocolHelper.getServiceUrl(RestElement.PRIV, RestElement.FIELD) + user.getId();
		clientResponse = httpProtocolHelper.getJsonRequest(url);
		

		if (clientResponse.getStatus() == Status.OK.getStatusCode()) {
			List<FieldDTO> fields = (List<FieldDTO>) fieldDTOFactory.makeDTOs(clientResponse.getEntity());
			params.put("fields", fields);
			return new ModelAndView("fields", params);
		}
		return new ModelAndView("fields", params);
	}

	@RequestMapping(value = {"/" , ""}, method = RequestMethod.POST)
	public ModelAndView createOrUpdate(@ModelAttribute("field") FieldModel field) throws ServletException, IOException{
		if (field.getId() == null) return this.create(field);
		return this.update(field);
	}
	

	@RequestMapping("/new")
	public ModelAndView field() {
		Map<String, Object> params = new HashMap<String, Object>();
		ClientResponse<String> response;
		String url;

		url = httpProtocolHelper.getServiceUrl(RestElement.PUB, RestElement.GROUND);
		response = httpProtocolHelper.getJsonRequest(url);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			List<GroundDTO> grounds = (List<GroundDTO>) groundDTOFactory.makeDTOs(response.getEntity());
			params.put("grounds", grounds);
		}
		url = httpProtocolHelper.getServiceUrl(RestElement.PUB, RestElement.SPORT);
		response = httpProtocolHelper.getJsonRequest(url);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			List<SportDTO> sports = new ArrayList<SportDTO>();
			sports.addAll(sportDTOFactory.makeDTOs(response.getEntity()));
			params.put("sports", sports);
		}
		
		return new ModelAndView("create/field", params);
	}
	
	@RequestMapping("/edit")
	public ModelAndView editDialog(@RequestParam(value = "id", required = true) String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		ClientResponse<String> response;
		String url;

		url = httpProtocolHelper.getServiceUrl(RestElement.PUB, RestElement.FIELD) + id;
		response = httpProtocolHelper.getJsonRequest(url);

		if (response.getStatus() == Status.OK.getStatusCode()) {
			FieldDTO field = fieldDTOFactory.makeDTO(response.getEntity());
			FieldModel fieldModel = this.fieldConverter.toModel(field);
			params.put("field", fieldModel);
		}
		
		url = httpProtocolHelper.getServiceUrl(RestElement.PUB, RestElement.GROUND);
		response = httpProtocolHelper.getJsonRequest(url);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			Gson gson = new Gson();
			List<String> grounds = gson.fromJson(response.getEntity(), new TypeToken<List<String>>(){}.getType());
			params.put("grounds", grounds);
		}
		url = httpProtocolHelper.getServiceUrl(RestElement.PUB, RestElement.SPORT);
		response = httpProtocolHelper.getJsonRequest(url);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			List<SportDTO> sports = new ArrayList<SportDTO>();
			sports.addAll(sportDTOFactory.makeDTOs(response.getEntity()));
			params.put("sports", sports);
		}
		
		return new ModelAndView("edit/field", params);
	}
	
	@RequestMapping("/delete")
	public ModelAndView deleteConfirmation(@RequestParam(value = "id", required = true) String id, @RequestParam(value = "name", required = true) String name) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("name", name);
		return new ModelAndView("dialog/deleteField", params);
	}
	
	@RequestMapping(value = "/delete/{field}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String field) {
		ClientResponse<String> response;
		String url;

		url = httpProtocolHelper.getServiceUrl(RestElement.PRIV, RestElement.FIELD) + field;
		response = httpProtocolHelper.deleteJsonRequest(url);
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		
	}

	private ModelAndView update(FieldModel field) throws ServletException, IOException {

		ClientResponse<String> response;
		String url;

		FieldDTO fieldDTO = fieldConverter.toDTO(field);
		
		String userId = this.userSessionHelper.getUser(null, null).getId();
		fieldDTO.setUserId(userId);
		
		url = httpProtocolHelper.getServiceUrl(RestElement.PRIV, RestElement.FIELD) + field.getId();
		response = httpProtocolHelper.postJsonRequest(url, fieldDTO);

		if (response.getStatus() == Status.OK.getStatusCode()) {
			return this.fields(null, null);
		}
		return this.fields(null, null);
	}
	
	private ModelAndView create(FieldModel field) throws ServletException, IOException {
		ClientResponse<String> response;
		String url;

		FieldDTO fieldDTO = fieldConverter.toDTO(field);
		
		String userId = this.userSessionHelper.getUser(null, null).getId();
		fieldDTO.setUserId(userId);
		
		url = httpProtocolHelper.getServiceUrl(RestElement.PRIV, RestElement.FIELD);
		response = httpProtocolHelper.postJsonRequest(url, fieldDTO);

		if (response.getStatus() == Status.OK.getStatusCode()) {
			return this.fields(null, null);
		}
		return this.fields(null, null);
	}

}