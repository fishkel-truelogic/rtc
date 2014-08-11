package ar.com.finit.service.model.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.finit.core.model.District;
import ar.com.finit.core.model.impl.DistrictImpl;
import ar.com.finit.dto.model.DistrictDTO;

/**
 * @author leo
 */
public class DistrictConverter {
	
	@Autowired
	private StateConverter stateConverter;

	public List<District> toEntity(Collection<DistrictDTO> disticts) {
		List<District> entities = new ArrayList<District>();
		for (DistrictDTO distictDTO : disticts) {
			entities.add(toEntity(distictDTO));
		}
		return entities;
	}

	public List<DistrictDTO> toDTO(Collection<District> disticts) {
		List<DistrictDTO> dtos = new ArrayList<DistrictDTO>();
		for (District distict : disticts) {
			dtos.add(toDTO(distict));
		}
		return dtos;
	}

	public DistrictDTO toDTO(District distict) {
		if (distict == null) {
			return null;
		}
		DistrictDTO dto = new DistrictDTO();
		dto.setId(distict.getId());
		dto.setName(distict.getName());
		dto.setState(stateConverter.toDTO(distict.getState()));
		return dto;
	}

	public District toEntity(DistrictDTO dto) {
		if (dto == null) {
			return null;
		}
		DistrictImpl distict = new DistrictImpl();
		distict.setId(dto.getId());
		distict.setName(dto.getName());
		distict.setState(stateConverter.toEntity(dto.getState()));
		return distict;
	}
}