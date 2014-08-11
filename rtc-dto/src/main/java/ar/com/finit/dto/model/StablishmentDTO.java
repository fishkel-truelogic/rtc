package ar.com.finit.dto.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leo
 */
public class StablishmentDTO {

	private String id;

	private String name;

	private String userId;

	private String email;

	private String web;

	private String openDay;

	private String closeDay;

	private String openHour;

	private String closeHour;
	
	private String address;

	private DistrictDTO district;

	private AlbumDTO album;

	private MapMarkerDTO mapMarker;

	private List<SportDTO> sports;

	private List<FieldDTO> fields;

	private List<TpServiceDTO> tpServices;
	
	private RateDTO rate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getOpenDay() {
		return openDay;
	}

	public void setOpenDay(String openDay) {
		this.openDay = openDay;
	}

	public String getCloseDay() {
		return closeDay;
	}

	public void setCloseDay(String closeDay) {
		this.closeDay = closeDay;
	}

	public String getOpenHour() {
		return openHour;
	}

	public void setOpenHour(String openHour) {
		this.openHour = openHour;
	}

	public String getCloseHour() {
		return closeHour;
	}

	public void setCloseHour(String closeHour) {
		this.closeHour = closeHour;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public DistrictDTO getDistrict() {
		return district;
	}

	public void setDistrict(DistrictDTO district) {
		this.district = district;
	}

	public AlbumDTO getAlbum() {
		if (album == null) album = new AlbumDTO();
		return album;
	}

	public void setAlbum(AlbumDTO album) {
		this.album = album;
	}

	public MapMarkerDTO getMapMarker() {
		if (mapMarker == null) mapMarker = new MapMarkerDTO();
		return mapMarker;
	}

	public void setMapMarker(MapMarkerDTO mapMarker) {
		this.mapMarker = mapMarker;
	}

	public List<SportDTO> getSports() {
		if (this.sports == null) this.sports = new ArrayList<SportDTO>();
		return sports;
	}

	public void setSports(List<SportDTO> sports) {
		this.sports = sports;
	}
	
	public void addSport(SportDTO sport) {
		this.getSports().add(sport);
	}

	public List<FieldDTO> getFields() {
		if (this.fields == null) this.fields = new ArrayList<FieldDTO>();
		return fields;
	}

	public void setFields(List<FieldDTO> fields) {
		this.fields = fields;
	}
	
	public void addField(FieldDTO field) {
		this.getFields().add(field);
	}

	public List<TpServiceDTO> getTpServices() {
		if (this.tpServices == null) this.tpServices = new ArrayList<TpServiceDTO>();
		return tpServices;
	}

	public void setTpServices(List<TpServiceDTO> tpServices) {
		this.tpServices = tpServices;
	}
	
	public void addTpService(TpServiceDTO tpService) {
		this.getTpServices().add(tpService);
	}

	public RateDTO getRate() {
		if (rate == null) rate = new RateDTO();
		return rate;
	}

	public void setRate(RateDTO rate) {
		this.rate = rate;
	}

}
