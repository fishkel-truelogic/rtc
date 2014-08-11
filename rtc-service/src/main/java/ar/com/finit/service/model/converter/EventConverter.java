package ar.com.finit.service.model.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.finit.core.model.Event;
import ar.com.finit.core.model.dao.FieldDao;
import ar.com.finit.core.model.dao.PlayerDao;
import ar.com.finit.core.model.dao.UserDao;
import ar.com.finit.core.model.impl.EventImpl;
import ar.com.finit.dto.model.EventDTO;

/**
 * @author leo
 */
public class EventConverter {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private FieldDao fieldDao;
	@Autowired
	private PlayerDao playerDao;

	public List<EventDTO> toDTO(Collection<Event> events) {
		List<EventDTO> eventsDTO = new ArrayList<EventDTO>();
		for (Event event : events) {
			eventsDTO.add(this.toDTO(event));
		}
		return eventsDTO;
	}

	public List<Event> toEntity(Collection<EventDTO> eventsDTO) throws ParseException {
		List<Event> events = new ArrayList<Event>();
		for (EventDTO event : eventsDTO) {
			events.add(this.toEntity(event));
		}
		return events;
	}

	public EventDTO toDTO(Event event) {
		if (event == null) return null;
		EventDTO dto = new EventDTO();
		dto.setId(event.getId().toString());
		dto.setUserId(event.getUser().getId());
		dto.setEntity(event.getField().getId());
		dto.setEntityName(event.getField().getName());
		String pattern = "dd/MM/yyyy HH:mm";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		dto.setStart_date(format.format(event.getStart_date()));
		dto.setEnd_date(format.format(event.getEnd_date()));
		dto.setStatus(event.getStatus());
		dto.setRtc(event.isRtc());
		if (dto.isRtc()){
			dto.setPlayerId(event.getPlayer().getId());
		}
		if (event.getPrepayment() != null) {
			dto.setPrepayment(format.format(event.getPrepayment()));
		}
		dto.setRtc(event.isRtc());
		this.setTextDetails(dto, event);
		return dto;
		
	}

	private void setTextDetails(EventDTO dto, Event event) {
		if (event.isRtc()) {
			dto.setDetails(event.getPlayer().getUsername() + " + " + event.getPlayer().getEmail());
		} else {
			dto.setDetails(event.getNotPlayer());
		}
		switch(event.getStatus()) {
		case EventDTO.CANCELED_OWNER: dto.setColor(EventDTO.CANCELED_OWNER_COLOR); dto.setText(EventDTO.CANCELED_OWNER_TEXT); dto.setReadonly(true); break;
		case EventDTO.CANCELED_PLAYER: dto.setColor(EventDTO.CANCELED_PLAYER_COLOR); dto.setText(EventDTO.CANCELED_PLAYER_TEXT); dto.setReadonly(true); break;
		case EventDTO.CANCELED_PREPAYMENT: dto.setColor(EventDTO.CANCELED_PREPAYMENT_COLOR); dto.setText(EventDTO.CANCELED_PREPAYMENT_TEXT); dto.setReadonly(true); break;
		case EventDTO.NO_PLAYED_YET: dto.setColor(EventDTO.NO_PLAYED_YET_COLOR); dto.setText(EventDTO.NO_PLAYED_YET_TEXT); dto.setReadonly(false); break;
		case EventDTO.NO_PREPAYMENT_YET: dto.setColor(EventDTO.NO_PREPAYMENT_YET_COLOR); dto.setText(EventDTO.NO_PREPAYMENT_YET_TEXT + dto.getPrepayment()); dto.setReadonly(false); break;
		case EventDTO.NO_RATED_YET: dto.setColor(EventDTO.NO_RATED_YET_COLOR); dto.setText(EventDTO.NO_RATED_YET_TEXT); dto.setReadonly(false); break;
		case EventDTO.RATED: dto.setColor(EventDTO.RATED_COLOR); dto.setText(EventDTO.RATED_TEXT); dto.setReadonly(true); break;
		
		}
		
	}

	public Event toEntity(EventDTO dto) throws ParseException {
		if (dto == null) return null;
		EventImpl event = new EventImpl();
		if (dto.getId() != null) event.setId(Integer.parseInt(dto.getId()));
		event.setUser(userDao.findById(dto.getUserId()));
		event.setField(fieldDao.findById(dto.getEntity()));
		String pattern = "dd/MM/yyyy HH:mm";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		event.setStart_date(format.parse(dto.getStart_date()));
		event.setPrepayment(this.parsePrepayment(dto.getPrepayment()));
		event.setEnd_date(format.parse(dto.getEnd_date()));
		event.setRtc(dto.isRtc());
		if (dto.isRtc()) {
			event.setPlayer(playerDao.findById(dto.getPlayerId()));
		} else {
			event.setNotPlayer(dto.getDetails());
		}
		event.setStatus(dto.getStatus());
		return event;
	}

	private Date parsePrepayment(String prepayment) throws ParseException {
		if (prepayment != null) {
			String pattern = "dd/MM/yyyy HH:mm";
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			return format.parse(prepayment);
		}
		return null;
		
	}

}
