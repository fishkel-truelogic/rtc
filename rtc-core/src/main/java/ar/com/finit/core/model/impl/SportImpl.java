package ar.com.finit.core.model.impl;

import ar.com.finit.core.model.Sport;

/**
 * @author leo
 */
public class SportImpl implements Sport {

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

	public void setName(String name) {
		this.name = name;
	}

}
