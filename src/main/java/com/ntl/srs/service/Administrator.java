package com.ntl.srs.service;



import java.sql.SQLException;
import java.util.ArrayList;

import com.ntl.srs.bean.PassengerBean;
import com.ntl.srs.bean.RouteBean;
import com.ntl.srs.bean.ScheduleBean;
import com.ntl.srs.bean.FlightBean;

public interface Administrator {


		String  addFlight(FlightBean shipbean) throws SQLException;
		boolean modifyFlight(FlightBean Shipbean) throws SQLException;
		String  addSchedule(ScheduleBean schedulebean) throws SQLException ;
		boolean  modifySchedule(ScheduleBean schedulebean) throws SQLException ;
		int  removeSchedule(ArrayList<String>  scheduleid) ;
		String  addRoute(RouteBean routebean) throws SQLException;
		boolean  modifyRoute(RouteBean routebean) throws SQLException;
		int  removeRoute(String routeid) throws SQLException ;
		FlightBean  viewByFlightId(String FlightId) throws SQLException ;
		RouteBean  viewByRouteId(String routeid) throws SQLException ;
		ArrayList<FlightBean>  viewByAllFlights() throws SQLException;
		ArrayList<RouteBean>  viewByAllRoute() throws SQLException;
		ArrayList<ScheduleBean>  viewByAllSchedule() throws SQLException;
		ScheduleBean  viewByScheduleId(String scheduleid) throws SQLException;
		ArrayList<PassengerBean> viewPasengersByFlight(String scheduleid) throws SQLException;

	}

	

