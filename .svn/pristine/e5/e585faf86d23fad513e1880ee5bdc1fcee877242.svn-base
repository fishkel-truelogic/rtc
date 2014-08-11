package ar.com.finit.service.model.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ar.com.finit.core.model.Sport;
import ar.com.finit.core.model.impl.SportImpl;
import ar.com.finit.dto.model.SportDTO;

/**
 * @author leo
 */
public class SportConverter {

	public List<SportDTO> toDTO(Collection<Sport> sports) {
		List<SportDTO> dtos = new ArrayList<SportDTO>();
		for (Sport sport : sports) {
			dtos.add(toDTO(sport));
		}
		return dtos;
	}

	public List<Sport> toEntity(List<SportDTO> sports) {
		List<Sport> entities = new ArrayList<Sport>();
		for (SportDTO sportDTO : sports) {
			entities.add(toEntity(sportDTO));
		}
		return entities;
	}

	public SportDTO toDTO(Sport sport) {
		if (sport == null) {
			return null;
		}
		SportDTO dto = new SportDTO();
		dto.setId(sport.getId());
		dto.setName(sport.getName());
		return dto;
	}

	public Sport toEntity(SportDTO dto) {
		if (dto == null) {
			return null;
		}
		SportImpl sport = new SportImpl();
		sport.setId(dto.getId());
		sport.setName(dto.getName());
		return sport;
	}
}
