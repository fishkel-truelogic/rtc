package ar.com.finit.owner.web.converter;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.client.ClientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ar.com.finit.dto.model.FieldDTO;
import ar.com.finit.dto.model.SportDTO;
import ar.com.finit.dto.model.factory.SportDTOFactory;
import ar.com.finit.owner.web.model.FieldModel;
import ar.com.finit.owner.web.rest.client.HttpProtocolHelper;
import ar.com.finit.owner.web.rest.client.RestElement;

/**
 * @author leo
 */

@Component
@Scope("application")
public class FieldConverter {

	@Autowired
	private HttpProtocolHelper httpProtocolHelper;
	@Autowired 
	private SportDTOFactory sportDTOFactory;
	
	public FieldDTO toDTO(FieldModel fieldModel) {
		String url;
		ClientResponse<String> response;
		
		FieldDTO field = new FieldDTO();
		field.setId(fieldModel.getId());
		field.setCeiling(fieldModel.isCeiling());
		field.getRate().setId(fieldModel.getRateId());
		field.setGround(fieldModel.getGround());
		field.setLights(fieldModel.isLights());
		field.setName(fieldModel.getName());
		field.setUserId(fieldModel.getUserId());
		field.getAlbum().setId(fieldModel.getAlbumId());
		for(String sport : fieldModel.getSports()) {
			if (sport != null) {
				url = httpProtocolHelper.getServiceUrl(RestElement.PUB, RestElement.SPORT) + sport;
				response = this.httpProtocolHelper.getJsonRequest(url);
				if (response.getStatus() == Status.OK.getStatusCode()) {
					field.addSport(sportDTOFactory.makeDTO(response.getEntity()));
				}
			}
		}
		return field;

	}

	public FieldModel toModel(FieldDTO field) {
		FieldModel fieldModel = new FieldModel();
		fieldModel.setId(field.getId());
		fieldModel.setCeiling(field.isCeiling());
		fieldModel.setGround(field.getGround());
		fieldModel.setLights(field.isLights());
		fieldModel.setName(field.getName());
		fieldModel.setUserId(field.getUserId());
		fieldModel.setAlbumId(field.getAlbum().getId());
		fieldModel.setRateId(field.getRate().getId());
		List<String> sports = new ArrayList<String>();
		for (SportDTO sport : field.getSports()) {
			sports.add(sport.getId());
		}
		fieldModel.setSports(sports);
		return fieldModel;
	}
}
