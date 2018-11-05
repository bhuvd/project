package com.ntl.srs.bean;

import java.time.LocalDate;

public class ScheduleBean {

	private String scheduleID;
	private String routeID;
	private String flightID;
	private LocalDate startDate;
	
	
	
	
	public ScheduleBean(String routeID, String flightID, LocalDate startDate) {
		super();
		this.routeID = routeID;
		this.flightID = flightID;
		this.startDate = startDate;
	}



	/**
	 * @param scheduleID
	 * @param routeID
	 * @param flightID
	 */
	public ScheduleBean(String scheduleID, String routeID, String flightID) {
		super();
		this.scheduleID = scheduleID;
		this.routeID = routeID;
		this.flightID = flightID;
	}



	public ScheduleBean() {
		super();
	}
	
	
	
	public ScheduleBean(String scheduleID, String routeID, String flightID, LocalDate startDate) {
		super();
		this.scheduleID = scheduleID;
		this.routeID = routeID;
		this.flightID = flightID;
		this.startDate = startDate;
	}



	public String getScheduleID() {
		return scheduleID;
	}
	public void setScheduleID(String scheduleID) {
		this.scheduleID = scheduleID;
	}
	public String getRouteID() {
		return routeID;
	}
	public void setRouteID(String routeID) {
		this.routeID = routeID;
	}
	public String getFlightID() {
		return flightID;
	}
	public void setFlightID(String flightID) {
		this.flightID = flightID;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ScheduleBean [scheduleID=" + scheduleID + ", routeID=" + routeID + ", flightID=" + flightID + ", startDate="
				+ startDate + "]";
	}
	
	
	
	
}
