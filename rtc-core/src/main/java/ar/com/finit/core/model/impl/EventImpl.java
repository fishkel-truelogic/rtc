package ar.com.finit.core.model.impl;

import java.util.Date;

import ar.com.finit.core.model.Event;
import ar.com.finit.core.model.Field;
import ar.com.finit.core.model.Player;
import ar.com.finit.core.model.User;

/**
 * @author leo
 */
public class EventImpl implements Event {
	
	public static final int NO_PREPAYMENT_YET = 0 ;
	public static final int CANCELED_PREPAYMENT = 1;
	public static final int NO_PLAYED_YET = 2;
	public static final int NO_RATED_YET = 3;
	public static final int RATED = 4;
	public static final int CANCELED_PLAYER = 5;
	public static final int CANCELED_OWNER = 6;

	private Integer id;
	
	private User user;
	
	private Field field;
	
	private Date start_date;
	
	private Date end_date;
	
	private Player player;

	private Date prepayment;

	private int status;
	
	private String notPlayer;
	
	private boolean rtc;
	
	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	@Override
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	@Override
	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	@Override
	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public Date getPrepayment() {
		return prepayment;
	}

	public void setPrepayment(Date prepayment) {
		this.prepayment = prepayment;
	}

	@Override
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String getNotPlayer() {
		return notPlayer;
	}

	public void setNotPlayer(String notPlayer) {
		this.notPlayer = notPlayer;
	}

	@Override
	public boolean isRtc() {
		return rtc;
	}

	public void setRtc(boolean rtc) {
		this.rtc = rtc;
	}
	
	
	
}
