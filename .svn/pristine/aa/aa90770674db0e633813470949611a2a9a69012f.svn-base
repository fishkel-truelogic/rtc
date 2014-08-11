package ar.com.finit.owner.web.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ar.com.finit.dto.model.EventDTO;
import ar.com.finit.owner.web.model.EventModel;

/**
 * @author leo
 */
@Component
@Scope("application")
public class EventConverter {

	
	public EventDTO toDTO(EventModel model) {
		EventDTO dto = new EventDTO();
		dto.setDetails(model.getUsername() + " - " + model.getEmail());
		dto.setRtc(false);
		dto.setEntity(model.getField());
		dto.setStart_date(model.getDay() + " " + model.getHour());
		Calendar calendar = Calendar.getInstance();
		String pattern = "dd/MM/yyyy HH:mm";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			Date date = format.parse(dto.getStart_date());
			calendar.setTime(date);
			calendar.add(Calendar.HOUR_OF_DAY, 1);
			dto.setEnd_date(format.format(calendar.getTime()));
			if (model.isPrepayment()) {
				dto.setStatus(EventDTO.NO_PREPAYMENT_YET);
				if (this.calculatePrepayment(date, format) != null) {
					dto.setPrepayment(this.calculatePrepayment(date, format));
				} else {
					dto.setStatus(EventDTO.NO_PLAYED_YET);
				}
			} else {
				dto.setStatus(EventDTO.NO_PLAYED_YET);
			}
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return dto;
	}

	private String calculatePrepayment(Date date, SimpleDateFormat format) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		DateTime dateTime = new DateTime(date);
		Period p = new Period(new DateTime(), dateTime);
		if(p.getHours() >= 336) {
			calendar.add(Calendar.DAY_OF_YEAR, -12);
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.MINUTE, 0);
			return format.format(calendar.getTime());
		} else if(p.getHours() >= 168) {
			calendar.add(Calendar.DAY_OF_YEAR, -5);
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.MINUTE, 0);
			return format.format(calendar.getTime());
		} else if(p.getHours() >= 96) {
			calendar.add(Calendar.DAY_OF_YEAR, -4);
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.MINUTE, 0);
			return format.format(calendar.getTime());
		} else if(p.getHours() >= 48) {
			calendar.add(Calendar.HOUR_OF_DAY, -24);
			return format.format(calendar.getTime());
		} else if(p.getHours() >= 24) {
			calendar.add(Calendar.HOUR_OF_DAY, -6);
			return format.format(calendar.getTime());
		} else if(p.getHours() >= 12) {
			calendar.add(Calendar.HOUR_OF_DAY, -3);
			return format.format(calendar.getTime());
		} else if(p.getHours() >= 6) {
			calendar.add(Calendar.HOUR_OF_DAY, -2);
			return format.format(calendar.getTime());
		} else {
			return null;
		}
	}
}
