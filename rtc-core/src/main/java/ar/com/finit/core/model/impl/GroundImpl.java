package ar.com.finit.core.model.impl;

import ar.com.finit.core.model.Ground;

/**
 * @author leo
 */
public class GroundImpl implements Ground {

	private String id;

	private String name;

	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String text) {
		this.name = text;
	}

}
