package ar.com.finit.owner.web.model;

import java.util.List;

import ar.com.finit.dto.model.EventDTO;

/**
 * @author leo
 */
public class CalendarModel {

	private List<EventDTO> data;
	
	public List<EventDTO> getData() {
		return data;
	}

	public void setData(List<EventDTO> data) {
		this.data = data;
	}

}
