package ar.com.finit.service.model.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ar.com.finit.core.model.Rate;
import ar.com.finit.core.model.impl.RateImpl;
import ar.com.finit.dto.model.RateDTO;

/**
 * @author leo
 */
public class RateConverter {

	public List<RateDTO> toDTO(Collection<Rate> rates) {
		List<RateDTO> dtos = new ArrayList<RateDTO>();
		for (Rate rate : rates) {
			dtos.add(toDTO(rate));
		}
		return dtos;
	}

	public List<Rate> toEntity(List<RateDTO> rates) {
		List<Rate> entities = new ArrayList<Rate>();
		for (RateDTO rateDTO : rates) {
			entities.add(toEntity(rateDTO));
		}
		return entities;
	}

	public RateDTO toDTO(Rate rate) {
		if (rate == null) return null;
		RateDTO dto = new RateDTO();
		dto.setId(rate.getId());
		dto.setAverage(rate.getAverage());
		dto.setVotes(rate.getVotes());
		dto.setEntity(rate.getEntity());
		return dto;
	}

	public Rate toEntity(RateDTO dto) {
		RateImpl rate = new RateImpl();
		rate.setId(dto.getId());
		rate.setAverage(dto.getAverage());
		rate.setVotes(dto.getVotes());
		rate.setEntity(dto.getEntity());
		return rate;
	}
}