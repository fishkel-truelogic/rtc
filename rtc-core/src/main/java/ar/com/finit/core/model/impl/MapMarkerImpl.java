package ar.com.finit.core.model.impl;

import ar.com.finit.core.model.MapMarker;

/**
 * @author leo
 */
public class MapMarkerImpl implements MapMarker {

	private String id;

	private Double lat;

	private Double lng;

	private String entity;

	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	@Override
	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	@Override
	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

}
