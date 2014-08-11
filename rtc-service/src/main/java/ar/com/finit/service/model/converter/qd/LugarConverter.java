package ar.com.finit.service.model.converter.qd;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ar.com.finit.core.model.qd.Lugar;
import ar.com.finit.core.model.qd.impl.LugarImpl;
import ar.com.finit.dto.model.qd.LugarDTO;

/**
 * @author leo
 */
public class LugarConverter {

	public List<Lugar> toEntity(Collection<LugarDTO> lugares) {
		List<Lugar> entities = new ArrayList<Lugar>();
		for (LugarDTO lugarDTO : lugares) {
			entities.add(toEntity(lugarDTO));
		}
		return entities;
	}

	public List<LugarDTO> toDTO(Collection<Lugar> lugares) {
		List<LugarDTO> dtos = new ArrayList<LugarDTO>();
		for (Lugar lugar : lugares) {
			dtos.add(toDTO(lugar));
		}
		return dtos;
	}

	public LugarDTO toDTO(Lugar lugar) {
		if (lugar == null) {
			return null;
		}
		LugarDTO dto = new LugarDTO();
		dto.setId(lugar.getId());
		dto.setIdBarrio(lugar.getIdBarrio());
		dto.setIdDeporte(lugar.getIdDeporte());
		dto.setIdProvincia(lugar.getIdProvincia());
		dto.setComentario(lugar.getComentario());
		dto.setDestacada(lugar.getDestacada());
		dto.setDomicilio(lugar.getDomicilio());
		dto.setDescripcion(lugar.getDescripcion());
		dto.setTamano(lugar.getTamano());
		dto.setTelefono(lugar.getTelefono());
		dto.setMostrar(lugar.getMostrar());
		return dto;
	}

	public Lugar toEntity(LugarDTO dto) {
		if (dto == null) {
			return null;
		}
		LugarImpl impl = new LugarImpl();
		impl.setId(dto.getId());
		impl.setIdBarrio(dto.getIdBarrio());
		impl.setIdDeporte(dto.getIdDeporte());
		impl.setIdProvincia(dto.getIdProvincia());
		impl.setComentario(dto.getComentario());
		impl.setDestacada(dto.getDestacada());
		impl.setDomicilio(dto.getDomicilio());
		impl.setDescripcion(dto.getDescripcion());
		impl.setTamano(dto.getTamano());
		impl.setTelefono(dto.getTelefono());
		impl.setMostrar(dto.getMostrar());
		return impl;
	}
}
