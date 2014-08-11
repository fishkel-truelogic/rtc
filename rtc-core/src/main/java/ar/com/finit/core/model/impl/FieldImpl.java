package ar.com.finit.core.model.impl;

import java.util.List;

import ar.com.finit.core.model.Album;
import ar.com.finit.core.model.Field;
import ar.com.finit.core.model.Rate;
import ar.com.finit.core.model.Sport;

/**
 * @author leo
 */
public class FieldImpl implements Field {

	private String id;

	private String name;

	private String userId;

	private String price;

	private boolean ceiling;

	private boolean lights;

	private Album album;

	private String ground;

	private List<Sport> sports;
	
	private Rate rate;

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public boolean isLights() {
		return lights;
	}

	public void setLights(boolean lights) {
		this.lights = lights;
	}

	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	@Override
	public List<Sport> getSports() {
		return sports;
	}

	public void setSports(List<Sport> sports) {
		this.sports = sports;
	}

	@Override
	public String getGround() {
		return ground;
	}

	public void setGround(String ground) {
		this.ground = ground;
	}

	@Override
	public boolean isCeiling() {
		return ceiling;
	}

	public void setCeiling(boolean ceiling) {
		this.ceiling = ceiling;
	}

	@Override
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public Rate getRate() {
		return rate;
	}

	public void setRate(Rate rate) {
		this.rate = rate;
	}
	
	

}
