package ar.com.finit.service.quartz;

import java.util.Date;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import ar.com.finit.core.model.Event;
import ar.com.finit.core.model.dao.EventDao;
import ar.com.finit.core.model.impl.EventImpl;

/**
 * @author leo
 */
public class PrepaymentJob extends QuartzJobBean {

	private Logger logger = (Logger)LoggerFactory.getLogger(PrepaymentJob.class);

	private EventDao eventDao;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		logger.info("Executing prepayment validation");
		Date now = new Date();
		List<Event> events = eventDao.findByStatus(EventImpl.NO_PREPAYMENT_YET);
		for (Event event : events) {
			if (event.getPrepayment().before(now)) {
				EventImpl eventImpl = (EventImpl) event;
				eventImpl.setStatus(EventImpl.CANCELED_PREPAYMENT);
				eventDao.update(eventImpl);
				//TODO mandar mail notificando la baja de la reserva
			}
		}
		logger.info("Executing played validation");
		events = eventDao.findByStatus(EventImpl.NO_PLAYED_YET);
		for (Event event : events) {
			if (event.getEnd_date().before(now)) {
				EventImpl eventImpl = (EventImpl) event;
				eventImpl.setStatus(EventImpl.NO_RATED_YET);
				eventDao.update(eventImpl);
				//TODO mandar mail notificando la disponibilidad de calificacion de cancha
			}
		}
	}

	public EventDao getEventDao() {
		return eventDao;
	}

	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}
	
}
