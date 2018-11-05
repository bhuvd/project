package com.ntl.srs.serviceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import com.ntl.srs.bean.PassengerBean;
import com.ntl.srs.bean.RouteBean;
import com.ntl.srs.bean.ScheduleBean;
import com.ntl.srs.bean.FlightBean;
import com.ntl.srs.daoImpl.PassengerBeanDaoImpl;
import com.ntl.srs.daoImpl.RouteBeanDaoImpl;
import com.ntl.srs.daoImpl.ScheduleBeanDaoImpl;
import com.ntl.srs.daoImpl.FlightBeanDaoImpl;
import com.ntl.srs.service.Administrator;

public class AdministratorImpl implements Administrator{

	FlightBeanDaoImpl Shipsbean=new FlightBeanDaoImpl();
	ScheduleBeanDaoImpl Schedulebean=new ScheduleBeanDaoImpl();
	RouteBeanDaoImpl rbean=new RouteBeanDaoImpl();
	PassengerBeanDaoImpl passenger=new PassengerBeanDaoImpl();
	
	
	
	
	
	
	/**
	 * 
	 */
	public AdministratorImpl() {
		super();
	}
	
	public AdministratorImpl(PassengerBeanDaoImpl passimpl) {
		super();
		passenger=passimpl;
		
	}
	
	public AdministratorImpl(FlightBeanDaoImpl shipimpl) {
		super();
		Shipsbean=shipimpl;
		
	}

	public AdministratorImpl(RouteBeanDaoImpl roueimpl) {
		super();
		rbean=roueimpl;
		
	}

	public AdministratorImpl(ScheduleBeanDaoImpl schimpl) {
		super();
		Schedulebean=schimpl;
		
	}

	public String addFlight(FlightBean shipbean) throws SQLException {
		
//		Random rand = new Random();
//		shipbean.setShipID(shipbean.getShipName().substring(0, 2)+String.format("%04d", rand.nextInt(10000)));
//		System.out.println("please NOTE UR UNIQUE ID: "+shipbean.getShipID());
//		
		String status=Shipsbean.createFlightBean(shipbean);
		
		if(status!=null)
		return "success";
		
		return null;
	}

	
	public boolean modifyFlight(FlightBean Shipbean) throws SQLException {
		// TODO Auto-generated method stub
	
		if(Shipsbean.updateFlightBean(Shipbean))
		{
			return true;
		}
		
		return false;
	}

	
	public String addSchedule(ScheduleBean schedulebean) throws SQLException {
		// TODO Auto-generated method stub
		
//		AdministratorImpl ad=new AdministratorImpl();
//		Random rand = new Random();
//		RouteBean rot=ad.viewByRouteId(schedulebean.getRouteID());
//		schedulebean.setScheduleID(rot.getSource().substring(0, 2)+rot.getDestination().substring(0, 2)+String.format("%04d", rand.nextInt(10000)));
//		System.out.println("please NOTE UR UNIQUE ID: "+schedulebean.getScheduleID());
//	
		String status=Schedulebean.createScheduleBean(schedulebean);
		
		return status;
	}

	
	public boolean modifySchedule(ScheduleBean schedulebean) throws SQLException {
		// TODO Auto-generated method stub
		if(Schedulebean.updateScheduleBean(schedulebean))
		{
			return true;
		}
		return false;
	}

	
	public int removeSchedule(ArrayList<String> scheduleid) {
		// TODO Auto-generated method stub
		int result=Schedulebean.deleteScheduleBean(scheduleid);
		
		return result;
	}

	
	public String addRoute(RouteBean routebean) throws SQLException {

//		Random rand = new Random();
//		routebean.setRouteID(routebean.getSource().substring(0, 2)+routebean.getDestination().substring(0, 2)+String.format("%04d", rand.nextInt(10000)));
//		System.out.println("please NOTE UR UNIQUE ID: "+routebean.getRouteID());
//		
		
		String status=rbean.createRouteBean(routebean);
		
		return status;
	}

	
	public boolean modifyRoute(RouteBean routebean) throws SQLException {
		if(rbean.updateRouteBean(routebean))
		{
			return true;
		}
		return false;
	}

	
	public int removeRoute(String routeid) throws SQLException {
		ArrayList<String> al=new ArrayList<String>();
		String z[]=routeid.split(" ");
		for(String i:z)
		{
			al.add(i);
		}
int result=rbean.deleteRouteBean(al);
		System.out.println(result);
		return result;
	}

	
	public FlightBean viewByFlightId(String ShipId) throws SQLException {
		FlightBean sp=Shipsbean.findByID(ShipId);
		if(sp!=null)
		return sp;
		else {
			return null;
		}
	}

	
	public RouteBean viewByRouteId(String routeid) throws SQLException {
		RouteBean rb=rbean.findByID(routeid);
		if(rb!=null)
		return rb;
		else {
			return null;
		}
	}

	
	public ArrayList<FlightBean> viewByAllFlights() throws SQLException {
		ArrayList<FlightBean> al=new ArrayList<FlightBean>();
		al=Shipsbean.findAll();
		if(al!=null)
		{
			return al;
		}
		return null;
	}

	
	public ArrayList<RouteBean> viewByAllRoute() throws SQLException {
		ArrayList<RouteBean> al=new ArrayList<RouteBean>();
		al=rbean.findAll();
		if(al!=null)
		{
			return al;
		}
		return null;
	}

	
	public ArrayList<ScheduleBean> viewByAllSchedule() throws SQLException {
		ArrayList<ScheduleBean> al=new ArrayList<ScheduleBean>();
		al=Schedulebean.findAll();
		if(al!=null)
		{
			return al;
		}
		return null;
	}

	
	public ScheduleBean viewByScheduleId(String scheduleid) throws SQLException {
		ScheduleBean sb=Schedulebean.findByID(scheduleid);
		if(sb!=null)
		return sb;
		else {
			return null;
		}
	}

	
	public ArrayList<PassengerBean> viewPasengersByFlight(String shipid) throws SQLException {
		ArrayList<PassengerBean> apass=new ArrayList<PassengerBean>();
		apass=passenger.findByFlight(shipid);
		if(apass!=null)
		{
			return apass;
		}
		return null;
	}

}
