package com.ntl.srs.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import com.ntl.srs.bean.ScheduleBean;
import com.ntl.srs.bean.FlightBean;
import com.ntl.srs.dao.ScheduleBeanDao;
import com.ntl.srs.utilImpl.DBUtilImpl;

public class ScheduleBeanDaoImpl implements ScheduleBeanDao{

	
	Connection con=DBUtilImpl.getDBConnection("jdbc");
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	
	public String createScheduleBean(ScheduleBean scheduleBean) throws SQLException {
		// TODO Auto-generated method stub
		try {
		ps=con.prepareStatement("insert into SRS_TBL_Schedule values(?,?,?,?)");
		ps.setString(1, scheduleBean.getScheduleID());
		ps.setString(2, scheduleBean.getFlightID());
		ps.setString(3, scheduleBean.getRouteID());
		ps.setDate(4, Date.valueOf(scheduleBean.getStartDate()));
		}catch(SQLException sq)
		{
			sq.printStackTrace();
			}
		int add=ps.executeUpdate();
		if(add>0)
		return "success";
		else {
			return null;
		}
		
	
	}

	
	public int deleteScheduleBean(ArrayList<String> al) {
		int flag=1;
		for(String i:al) {
		try {
				ps=con.prepareStatement("delete from srs_tbl_schedule where scheduleId='"+i+"'");
				int del=ps.executeUpdate();
				if(del==0)
				{
					flag=0;
					return 0;
				}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		}
		return flag;
		
	}

	
	public boolean updateScheduleBean(ScheduleBean scheduleBean) throws SQLException {
		// TODO Auto-generated method stub
		
		ps=con.prepareStatement("update srs_tbl_schedule set FlightId='"+scheduleBean.getFlightID()+"', RouteId='"+scheduleBean.getRouteID()+"',startDate='"+Date.valueOf(scheduleBean.getStartDate())+"' where ScheduleId='"+scheduleBean.getScheduleID()+"'");
		int modify=ps.executeUpdate();
		if(modify>0)
		{
			return true;
		}
		return false;
	}

	
	public ScheduleBean findByID(String id) throws SQLException {

		
		
		ps=con.prepareStatement("select * from srs_tbl_schedule where scheduleId='"+id+"'");
		rs=ps.executeQuery();
		while(rs.next()) {
			Date today = rs.getDate(4);
			Instant instant = Instant.ofEpochMilli(today.getTime());
			LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()); 
			LocalDate localDate = localDateTime.toLocalDate();
		ScheduleBean sbean=new ScheduleBean(rs.getString(1),rs.getString(3),rs.getString(2),localDate);
		
			return sbean;
		}
		return null;
	}

	
	public ArrayList<ScheduleBean> findAll() throws SQLException {
		ArrayList<ScheduleBean> sbean=new ArrayList<ScheduleBean>();
		ps=con.prepareStatement("select * from srs_tbl_schedule ");
		rs=ps.executeQuery();
		while(rs.next())
		{
			
			Date today = rs.getDate(4);
			Instant instant = Instant.ofEpochMilli(today.getTime());
			LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()); 
			LocalDate localDate = localDateTime.toLocalDate();

			
			ScheduleBean sche=new ScheduleBean(rs.getString(1),rs.getString(3),rs.getString(2),localDate);
			sbean.add(sche);
		}
		if(rs.first())
		{
			return sbean;
		}
		return null;
	}

}
