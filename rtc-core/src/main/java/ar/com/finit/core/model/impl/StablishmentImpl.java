package ar.com.finit.core.model.impl;

import java.util.List;

import ar.com.finit.core.model.Album;
import ar.com.finit.core.model.District;
import ar.com.finit.core.model.Field;
import ar.com.finit.core.model.MapMarker;
import ar.com.finit.core.model.Rate;
import ar.com.finit.core.model.Sport;
import ar.com.finit.core.model.Stablishment;
import ar.com.finit.core.model.TpService;

/**
 * @author leo
 */
public class StablishmentImpl implements Stablishment {

	private String id;

	private String userId;

	private String email;
	
	private String name;

	private String address;

	private String web;

	private String openDay;

	private String openHour;

	private String closeDay;

	private String closeHour;

	private District district;

	private Album album;

	private MapMarker mapMarker;

	private List<Sport> sports;

	private List<Field> fields;

	private List<TpService> tpServices;

	private Rate rate;
	
	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	@Override
	public List<Sport> getSports() {
		return sports;
	}

	public void setSports(List<Sport> sports) {
		this.sports = sports;
	}

	@Override
	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	@Override
	public List<TpService> getTpServices() {
		return tpServices;
	}

	public void setTpServices(List<TpService> tpServices) {
		this.tpServices = tpServices;
	}

	@Override
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	@Override
	public String getOpenHour() {
		return openHour;
	}

	public void setOpenHour(String openHour) {
		this.openHour = openHour;
	}

	@Override
	public String getCloseHour() {
		return closeHour;
	}

	public void setCloseHour(String closeHour) {
		this.closeHour = closeHour;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String getOpenDay() {
		return openDay;
	}

	public void setOpenDay(String openDay) {
		this.openDay = openDay;
	}

	@Override
	public String getCloseDay() {
		return closeDay;
	}

	public void setCloseDay(String closeDay) {
		this.closeDay = closeDay;
	}

	@Override
	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	@Override
	public MapMarker getMapMarker() {
		return mapMarker;
	}

	public void setMapMarker(MapMarker mapMarker) {
		this.mapMarker = mapMarker;
	}

	public Rate getRate() {
		return rate;
	}

	public void setRate(Rate rate) {
		this.rate = rate;
	}

}
