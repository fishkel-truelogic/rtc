package ar.com.finit.service.model.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ar.com.finit.core.model.Ground;
import ar.com.finit.core.model.impl.GroundImpl;
import ar.com.finit.dto.model.GroundDTO;

/**
 * @author leo
 */
public class GroundConverter {

	public List<GroundDTO> toDTO(Collection<Ground> grounds) {
		List<GroundDTO> dtos = new ArrayList<GroundDTO>();
		for (Ground ground : grounds) {
			dtos.add(toDTO(ground));
		}
		return dtos;
	}

	public List<Ground> toEntity(List<GroundDTO> grounds) {
		List<Ground> entities = new ArrayList<Ground>();
		for (GroundDTO groundDTO : grounds) {
			entities.add(toEntity(groundDTO));
		}
		return entities;
	}

	public GroundDTO toDTO(Ground ground) {
		if (ground == null) {
			return null;
		}
		GroundDTO dto = new GroundDTO();
		dto.setId(ground.getId());
		dto.setName(ground.getName());
		return dto;
	}

	public Ground toEntity(GroundDTO dto) {
		if (dto == null) {
			return null;
		}
		GroundImpl ground = new GroundImpl();
		ground.setId(dto.getId());
		ground.setName(dto.getName());
		return ground;
	}

}
