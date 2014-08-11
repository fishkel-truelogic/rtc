package ar.com.finit.service.model.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ar.com.finit.core.model.State;
import ar.com.finit.core.model.impl.StateImpl;
import ar.com.finit.dto.model.StateDTO;

/**
 * @author leo
 */
public class StateConverter {

	public List<State> toEntity(Collection<StateDTO> disticts) {
		List<State> entities = new ArrayList<State>();
		for (StateDTO distictDTO : disticts) {
			entities.add(toEntity(distictDTO));
		}
		return entities;
	}

	public List<StateDTO> toDTO(Collection<State> states) {
		List<StateDTO> dtos = new ArrayList<StateDTO>();
		for (State state : states) {
			dtos.add(toDTO(state));
		}
		return dtos;
	}

	public StateDTO toDTO(State state) {
		if (state == null) {
			return null;
		}
		StateDTO dto = new StateDTO();
		dto.setId(state.getId());
		dto.setName(state.getName());
		return dto;
	}

	public State toEntity(StateDTO dto) {
		if (dto == null) {
			return null;
		}
		StateImpl distict = new StateImpl();
		distict.setId(dto.getId());
		distict.setName(dto.getName());
		return distict;
	}
}