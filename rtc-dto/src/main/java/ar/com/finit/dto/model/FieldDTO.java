package ar.com.finit.dto.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leo
 */
public class FieldDTO {

	private String id;

	private String name;

	private String userId;

	private String price;

	private boolean ceiling;

	private boolean lights;

	private String ground;

	private AlbumDTO album;

	private List<SportDTO> sports;

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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public boolean isCeiling() {
		return ceiling;
	}

	public void setCeiling(boolean ceiling) {
		this.ceiling = ceiling;
	}

	public boolean isLights() {
		return lights;
	}

	public void setLights(boolean lights) {
		this.lights = lights;
	}

	public String getGround() {
		return ground;
	}

	public void setGround(String ground) {
		this.ground = ground;
	}

	public AlbumDTO getAlbum() {
		if (album == null) album = new AlbumDTO();
		return album;
	}

	public void setAlbum(AlbumDTO album) {
		this.album = album;
	}

	public List<SportDTO> getSports() {
		if (sports == null) sports = new ArrayList<SportDTO>();
		return sports;
	}

	public void setSports(List<SportDTO> sports) {
		this.sports = sports;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void addSport(SportDTO sport) {
		this.getSports().add(sport);		
	}

	public RateDTO getRate() {
		if (rate == null) rate = new RateDTO();
		return rate;
	}

	public void setRate(RateDTO rate) {
		this.rate = rate;
	}

	
}
