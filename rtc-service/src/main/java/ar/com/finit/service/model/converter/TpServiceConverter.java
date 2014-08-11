package ar.com.finit.service.model.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.finit.core.model.TpService;
import ar.com.finit.core.model.impl.TpServiceImpl;
import ar.com.finit.dto.model.TpServiceDTO;

/**
 * @author leo
 */
public class TpServiceConverter {

	@Autowired
	private AlbumConverter albumConverter;

	public List<TpServiceDTO> toDTO(Collection<TpService> disticts, boolean lazy) {
		List<TpServiceDTO> dtos = new ArrayList<TpServiceDTO>();
		for (TpService distict : disticts) {
			dtos.add(toDTO(distict, lazy));
		}
		return dtos;
	}

	public List<TpService> toEntity(List<TpServiceDTO> disticts) {
		List<TpService> entities = new ArrayList<TpService>();
		for (TpServiceDTO distictDTO : disticts) {
			entities.add(toEntity(distictDTO));
		}
		return entities;
	}

	public TpServiceDTO toDTO(TpService tpService, boolean lazy) {
		if (tpService == null) {
			return null;
		}
		TpServiceDTO dto = new TpServiceDTO();
		dto.setId(tpService.getId());
		dto.setName(tpService.getName());
		dto.setUserId(tpService.getUserId());
		dto.setAlbum(albumConverter.toDTO(tpService.getAlbum(), lazy));
		return dto;
	}

	public TpService toEntity(TpServiceDTO dto) {
		if (dto == null) {
			return null;
		}
		TpServiceImpl tpService = new TpServiceImpl();
		tpService.setId(dto.getId());
		tpService.setName(dto.getName());
		tpService.setUserId(dto.getUserId());
		tpService.setAlbum(albumConverter.toEntity(dto.getAlbum()));
		return tpService;
	}
}