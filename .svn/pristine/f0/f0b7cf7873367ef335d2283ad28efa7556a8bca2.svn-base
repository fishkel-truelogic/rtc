package ar.com.finit.service.model.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ar.com.finit.core.model.MapMarker;
import ar.com.finit.core.model.impl.MapMarkerImpl;
import ar.com.finit.dto.model.MapMarkerDTO;

/**
 * @author leo
 */
public class MapMarkerConverter {

	public List<MapMarkerDTO> toDTO(Collection<MapMarker> mapMarkers) {
		List<MapMarkerDTO> mapMarkersDTO = new ArrayList<MapMarkerDTO>();
		for (MapMarker mapMarker : mapMarkers) {
			mapMarkersDTO.add(this.toDTO(mapMarker));
		}
		return mapMarkersDTO;
	}

	public List<MapMarker> toEntity(Collection<MapMarkerDTO> mapMarkersDTO) {
		List<MapMarker> mapMarkers = new ArrayList<MapMarker>();
		for (MapMarkerDTO mapMarker : mapMarkersDTO) {
			mapMarkers.add(this.toEntity(mapMarker));
		}
		return mapMarkers;
	}

	public MapMarkerDTO toDTO(MapMarker mapMarker) {
		if (mapMarker == null) {
			return null;
		}
		MapMarkerDTO dto = new MapMarkerDTO();
		dto.setId(mapMarker.getId());
		dto.setEntity(mapMarker.getEntity());
		dto.setLat(mapMarker.getLat());
		dto.setLng(mapMarker.getLng());
		return dto;
	}

	public MapMarker toEntity(MapMarkerDTO dto) {
		if (dto == null) {
			return null;
		}
		MapMarkerImpl mapMarker = new MapMarkerImpl();
		mapMarker.setId(dto.getId());
		mapMarker.setEntity(dto.getEntity());
		mapMarker.setLat(dto.getLat());
		mapMarker.setLng(dto.getLng());
		return mapMarker;
	}

}
