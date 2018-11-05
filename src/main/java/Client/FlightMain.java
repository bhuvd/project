package Client;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Pattern;

import com.ntl.srs.bean.CredentialsBean;
import com.ntl.srs.bean.PassengerBean;
import com.ntl.srs.bean.PaymentBean;
import com.ntl.srs.bean.ProfileBean;
import com.ntl.srs.bean.ReservationBean;
import com.ntl.srs.bean.RouteBean;
import com.ntl.srs.bean.ScheduleBean;
import com.ntl.srs.bean.FlightBean;
import com.ntl.srs.daoImpl.ReservationBeanDaoImpl;
import com.ntl.srs.daoImpl.RouteBeanDaoImpl;
import com.ntl.srs.daoImpl.ScheduleBeanDaoImpl;
import com.ntl.srs.service.Administrator;
import com.ntl.srs.serviceImpl.AdministratorImpl;
import com.ntl.srs.serviceImpl.CustomerImpl;
import com.ntl.srs.utilImpl.LoggedIn;
import com.ntl.srs.utilImpl.PaymentImpl;
import com.ntl.srs.utilImpl.SignedUp;




public class FlightMain {
	static String reserveId;
	static Scanner sc=new Scanner(System.in);
	LoggedIn log=new LoggedIn();
	static double tfare=0.0;
	CredentialsBean sign=new CredentialsBean();
	ProfileBean profile=new ProfileBean();
	SignedUp signup=new SignedUp();
	AdministratorImpl admin=new AdministratorImpl();
	CustomerImpl cust=new CustomerImpl();
	static CredentialsBean credit=null;
	PaymentImpl pay=new PaymentImpl();
	RouteBeanDaoImpl rt=new RouteBeanDaoImpl();
	ScheduleBeanDaoImpl st=new ScheduleBeanDaoImpl();
	
	ReservationBeanDaoImpl re=new ReservationBeanDaoImpl();
	
	public static void main(String z[]) throws ClassNotFoundException, IOException, SQLException
	{
		System.out.println("hello");
		boolean val=true;
		while(val) {
		FlightMain userid=new FlightMain();
		val=userid.getData();
		}
	}
	
	public boolean getData() throws IOException, ClassNotFoundException, SQLException
	{
		FlightMain user=new FlightMain();
		System.out.println("Are you an existing User? (yes/no/out)");
		String choose=sc.nextLine();
		if(choose.equalsIgnoreCase("yes"))
		{
			return user.getLogin();
			
		}
		else if(choose.equalsIgnoreCase("no")) {
			return user.getSignup();
			
		}
		else {
			
			System.out.println("Program will close!! you really wanna proceed?");
			String fin=sc.nextLine();
			if(fin.equalsIgnoreCase("yes"))
			System.exit(0);
			else {
				return true;
			}
		}
		
		return false;
	}
	
	
	public boolean getLogin() throws ClassNotFoundException, IOException, SQLException
	{
		FlightMain use=new FlightMain();
		
		System.out.println("enter Id");
		String lid=sc.nextLine();
	
		System.out.println("enter password");
		String pass=sc.nextLine();
	
		credit=new CredentialsBean(lid,pass);
		
		String result=signup.login(credit);
		//System.out.println(result);
		if(result.equals("A"))
		{
			System.out.println("hello admin");
			boolean admn=true;
			String activity="";
			while(admn)
			{
				System.out.println("want to do any activity?(yes/no/out/Change)");
				 activity=sc.nextLine();
				if(activity.equalsIgnoreCase("yes"))
				{
					
					
					System.out.println("enter the field you wanna Alter (Flight/Route/Schedule)");
					String field=sc.nextLine();
					if(field.equalsIgnoreCase("flight"))
					{
						ArrayList<FlightBean> alShip=new ArrayList<FlightBean>();
						alShip=admin.viewByAllFlights();
						for(FlightBean shi:alShip)
						{
							System.out.println(shi);
						}
						System.out.println("\n");
						System.out.println("enter the specific task(add/modify)");
						String taskship=sc.nextLine();
						if(taskship.equalsIgnoreCase("add"))
						{
							if(use.shipAdd())
								System.out.println("added successfully");
							admn=true;
						}
						else if(taskship.equalsIgnoreCase("modify")) {
							if(use.shipModify())
								System.out.println("modified successfully");
							else {
								System.out.println("can't modify");
							}
							admn=true;
						}
						else {
							System.out.println("something wrong input");
							admn=true;
						}
						
					}
					else if(field.equalsIgnoreCase("Route")){
						
						ArrayList<RouteBean> alroute=new ArrayList<RouteBean>();
						alroute=admin.viewByAllRoute();
						for(RouteBean shi:alroute)
						{
							System.out.println(shi);
						}
						System.out.println("\n");
						
						
						System.out.println("enter the specific task(add/modify/delete)");
						String taskship=sc.nextLine();
						if(taskship.equalsIgnoreCase("add"))
						{
							if(use.routeAdd())
							{
								System.out.println("added successfully");
							}
							else {
								System.out.println("something wrong");
							}
							admn=true;
						}
						else if(taskship.equalsIgnoreCase("modify")) {
							if(use.routeModify())
								System.out.println("modified successfully");
							else {
								System.out.println("can't modify");
							}
							admn=true;
						}
						else if(taskship.equalsIgnoreCase("delete")) {
							if(use.routeDelete())
								System.out.println("deleted successfully");
							else {
								System.out.println("something wrong");
							}
							admn=true;
						}
						else {
							System.out.println("something wrong input");
							admn=true;
						}
						
					}
					else if(field.equalsIgnoreCase("Schedule")){
						ArrayList<ScheduleBean> alSchedule=new ArrayList<ScheduleBean>();
						alSchedule=admin.viewByAllSchedule();
						for(ScheduleBean shi:alSchedule)
						{
							System.out.println(shi);
						}
						System.out.println("\n");
						System.out.println("enter the specific task(add/modify/delete)");
						String taskship=sc.nextLine();
						if(taskship.equalsIgnoreCase("add"))
						{
							if(use.scheduleAdd())
							{
								System.out.println("added successfully");
							}
							else {
								System.out.println("something wrong");
							}
							admn=true;
						}
						else if(taskship.equalsIgnoreCase("modify")) {
							if(use.scheduleModify())
								System.out.println("modified successfully");
							
							else {
								System.out.println("something wrong");
								
							}
							admn=true;
						}
						else if(taskship.equalsIgnoreCase("delete")) {
							if(use.scheduleDelete())
							{
								System.out.println("deleted successfully");
								
							}
							else {
								System.out.println("something wrong");
								
							}
							admn=true;
						}
						else {
							System.out.println("something wrong input");
							admn=true;
						}
						
					}
				else{
					System.out.println("something wrong input");
					admn=true;
				}
				}
				else if(activity.equalsIgnoreCase("no"))  {
					System.out.println("Thankyou, hope to see you soon!");
					admn=false;
					signup.logout(credit.getUserID());
				}
				else if(activity.equalsIgnoreCase("Change"))
				{
					use.changingPassword(credit);
					admn=true;
				}
				else if(activity.equalsIgnoreCase("out"))
				{
					admn=false;
					signup.logout(credit.getUserID());
				}
				else {
					admn=true;
				}
			}
		}
		else if(result.equals("C"))
		{
			System.out.println("hello customer");
			boolean custm=true;
			while(custm) {
			System.out.println("ticket functionality (reserve/cancel/view/payment/out)");
			String ticket=sc.nextLine();
			if(ticket.equalsIgnoreCase("reserve"))
			{
				reserveId=use.reserveTicket();
				custm=true;
			}
			else if(ticket.equalsIgnoreCase("cancel"))
			{
				System.out.println("enter reservation Id");
				String rrid=sc.nextLine();
				
				cust.cancelTicket(rrid);
				custm=true;
			}
			else if(ticket.equalsIgnoreCase("payment"))
			{
				System.out.println("enter the reservation Id");
				String reId=sc.nextLine();
				
				if(use.paymentCredit())
				{
					ReservationBean reser=new ReservationBean();
					reser=re.findByID(reId);
					cust.changeBookingStatus(reser);
					ArrayList<PassengerBean> ans=new ArrayList<PassengerBean>();
					 ans=use.passengerInput(reId,reser.getNoOfSeats());
					if(ans!=null)
					{
					cust.addingPassengers(ans);
					}
				}
				custm=true;
			}
			else if(ticket.equalsIgnoreCase("view"))
			{
				System.out.println("enter reservation Id");
				String rrid=sc.nextLine();
				
				Map<ReservationBean,PassengerBean> map = new HashMap<ReservationBean,PassengerBean>();
				map=cust.viewTicket(rrid);
				
				 for ( ReservationBean key : map.keySet() )
			        {
					 for(PassengerBean val:map.values())
					 {
						 System.out.println(key.getReservationID()+" "+key.getTotalFare()+" "+key.getJourneyDate()+" "+val.getName());
					 }
					 
			            //System.out.println( map.get( key ) );
			        }
				 custm=true;
			}
			else if(ticket.equalsIgnoreCase("out"))
			{
				custm=false;
				signup.logout(credit.getUserID());
			}
			else {
				custm=true;
			}
			}
		}
		else if(result.equals("fail"))
		{
			System.out.println(" Already logged in!");
		}
		else {
			System.out.println("no such user exists");
		}
		return true;
		
	}
		/*if(log.authenticate(credit))
		{
			String userType=log.authorize(lid);
			 log.changeLoginStatus(credit,credit.getLoginStatus());
			 
			if(userType.equals("A"))
			{
				System.out.println("hello admin");
			}
			else if(userType.equals("C")) {
				System.out.println("hello customer");
			}
			else {
			
			return true;
			}
		
		}
		else {
			System.out.println("something wrong with password or already logged in!");
			return true;
		}
		return true;
	}
	*/
	public boolean getSignup() throws IOException, ClassNotFoundException
	{
		System.out.println("Enter information in standard format");
		
		System.out.println("enter Firstname");
		String fname=sc.nextLine();
		//profile.setFirstName(fname);
		
		System.out.println("enter Lastname");
		String lname=sc.nextLine();
		//profile.setPassword(lname);
		
		boolean toCheckDate=true;
		String dobirth=null;
		while(toCheckDate) {
		System.out.println("Date of Birth in format (dd/mm/yyyy)");
		 dobirth=sc.nextLine();
		 if(Pattern.matches("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)",dobirth))
		 {
			 toCheckDate=false;
		 }
		}
		String dateOfBirth[]=dobirth.split("/");
		LocalDate dob=LocalDate.of(Integer.parseInt(dateOfBirth[2]),Integer.parseInt(dateOfBirth[1]), Integer.parseInt(dateOfBirth[0]));
		//profile.setDateOfBirth(dob);
		
		System.out.println("enter gender");
		String gender=sc.nextLine();
		//profile.setGender(gender);
		
		System.out.println("enter Street");
		String street=sc.nextLine();
	//	profile.setStreet(street);
		
		System.out.println("enter Location");
		String location=sc.nextLine();
//		profile.setLocation(location);
		
		System.out.println("enter city");
		String city=sc.nextLine();
	//	profile.setCity(city);
		
		System.out.println("enter state");
		String state=sc.nextLine();
	//	profile.setState(state);
		
		String pincode=null;
		boolean toCheckPincode=true;
		while(toCheckPincode) {
		System.out.println("enter Pincode");
		 pincode=sc.nextLine();
		 if(Pattern.matches("[0-9]{6}", pincode)) {
			 toCheckPincode=false;
		 }
		}
	//	profile.setPincode(pincode);
		
		String mobile=null;
		boolean toCheckMobile=true;
		while(toCheckMobile) {
		System.out.println("enter MobileNo");
		mobile=sc.nextLine();
		if(Pattern.matches("[7-9][0-9]{9}", mobile)) {
			toCheckMobile=false;
			}
		}
	//	profile.setMobileNo(mobile);
		String email=null;
		boolean toCheckEmail=true;
		 String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$"; 
		while(toCheckEmail) {
		System.out.println("enter email-ID");
		 email=sc.nextLine();
		
		 if(Pattern.matches(emailRegex,email)) {
			 toCheckEmail=false;
		 }
		}
	//	profile.setEmailID(email);
		String pass=null;
		boolean toCheckPassword=true;
		while(toCheckPassword) {
		System.out.println("enter Password (correct format)");
		 pass=sc.nextLine();
		 if(Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", pass)) {
			 toCheckPassword=false;
		 }
		}
		//profile.setPassword(pass);
		
		
		Random rand = new Random();
			String regid=fname.substring(0, 2)+String.format("%04d", rand.nextInt(10000));
			System.out.println("please NOTE UR UNIQUE ID: "+regid);
		
		ProfileBean profileBean=new ProfileBean(regid,fname,lname,dob,gender,street,location,city,state,pincode,mobile,email,pass);
		
		
		String registration=signup.register(profileBean) ;
		if(registration!=null)
		{
			System.out.println("registration Successfull");
		}
		else {
			System.out.println("something is wrong");
		}
			
	return true;
	
	}
	
	public boolean changingPassword(CredentialsBean cb) throws IOException, ClassNotFoundException
	{
		System.out.println("enter old password");
		String passwd=sc.nextLine();
		//profile.setGender(gender);
		
		String pass=null;
		boolean toCheckPass=true;
		while(toCheckPass) {
		System.out.println("enter new password in correct format");
		 pass=sc.nextLine();
		 if(Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", pass)) {
			 toCheckPass=false;
		 }
		}
	//	profile.setStreet(street);
		
		System.out.println("confirm password");
		String cnfpass=sc.nextLine();
//		profile.setLocation(location);
		
		if(cb.getPassword().equals(passwd) && pass.equals(cnfpass))
		{
			cb.setPassword(pass);
			String str=signup.changePassword(cb,pass);
			
			if(str!=null)
			{
				System.out.println("password changed for user"+str);
			}
			else {
				System.out.println("Unsuccessful");
			}
		}
		else {
			System.out.println("something is not correct");
		}
		return true;
	}
	
	public boolean shipAdd() throws SQLException
	{
		
		
		System.out.println("enter Ship Name");
		String shipName=sc.nextLine();
	//	profile.setStreet(street);
		
		System.out.println("enter Seating Capacity");
		int seatCap=sc.nextInt();
//		profile.setLocation(location);
		
		System.out.println("enter Reservation Capacity");
		int resCap=sc.nextInt();
	//	profile.setCity(city);
		
		
		
		
		FlightBean ship=new FlightBean(shipName,seatCap,resCap);
		Random rand = new Random();
		ship.setFlightID(ship.getFlightName().substring(0, 2)+String.format("%04d", rand.nextInt(10000)));
		System.out.println("please NOTE UR UNIQUE ID: "+ship.getFlightID());
		
		if(admin.addFlight(ship)!=null) {
			
			return true;
		}
		return false;
	}

	public boolean shipModify() throws SQLException
	{
		System.out.println("enter Ship ID you wanna modify");
		String shipId=sc.nextLine();
		FlightBean ship=admin.viewByFlightId(shipId);
		
		if(ship!=null) {
			
		System.out.println("enter Ship Name");
		String shipName=sc.nextLine();
		ship.setFlightName(shipName);
		
		System.out.println("enter Seating Capacity");
		int seatCap=sc.nextInt();
		ship.setSeatingCapacity(seatCap);
		
		System.out.println("enter Reservation Capacity");
		int resCap=sc.nextInt();
		ship.setReservationCapacity(resCap);
		
		if(admin.modifyFlight(ship)) {
			
			return true;
			}
		
		}
		else {
			System.out.println("no such id exists!");
			
		}
		return false;
	}
	
	public boolean scheduleAdd() throws SQLException
	{
		
		
		System.out.println("enter ShipId");
		String ShipId=sc.nextLine();
	
		
		System.out.println("enter RouteId");
		String RouteId=sc.nextLine();

		
		
		System.out.println("Starting Date in format (dd/mm/yyyy)");
		String stdate=sc.nextLine();
		String startd[]=stdate.split("/");
		LocalDate startDate=LocalDate.of(Integer.parseInt(startd[2]),Integer.parseInt(startd[1]), Integer.parseInt(startd[0]));
	
	
		
		ScheduleBean sched=new ScheduleBean(RouteId,ShipId,startDate);
		
		AdministratorImpl ad=new AdministratorImpl();
		Random rand = new Random();
		RouteBean rot=ad.viewByRouteId(sched.getRouteID());
		sched.setScheduleID(rot.getSource().substring(0, 2)+rot.getDestination().substring(0, 2)+String.format("%04d", rand.nextInt(10000)));
		System.out.println("please NOTE UR UNIQUE ID: "+sched.getScheduleID());
	
		if(admin.addSchedule(sched)!=null) {
			return true;
		}
		return false;
	}
	
	public boolean scheduleModify() throws SQLException
	{
		System.out.println("enter Schedule ID you wanna modify");
		String scheduleId=sc.nextLine();
		ScheduleBean sched=admin.viewByScheduleId(scheduleId);
		
		if(sched!=null) {
		System.out.println("enter ShipId");
		String shipId=sc.nextLine();
		FlightBean sbean=admin.viewByFlightId(shipId);
		if(sbean!=null)
		sched.setFlightID(shipId);
		else {
			System.out.println("this shipId doesnot match");
			return true;
		}
		
		System.out.println("enter Route Id");
		String routeId=sc.nextLine();
		RouteBean rbean=admin.viewByRouteId(routeId);
		if(rbean!=null)
		{
			sched.setRouteID(routeId);
		}
		else {
			System.out.println("this routeId doesnot match");
			return true;
		}
		
		
		System.out.println("Starting Date in format (dd/mm/yyyy)");
		String stdate=sc.nextLine();
		String startd[]=stdate.split("/");
		LocalDate startDate=LocalDate.of(Integer.parseInt(startd[2]),Integer.parseInt(startd[1]), Integer.parseInt(startd[0]));
		sched.setStartDate(startDate);
		
		if(admin.modifySchedule(sched)) {
			
			return true;
		}}
		else {
			System.out.println("no such id exists");
		}
		return false;
	}
	
	public boolean scheduleDelete() throws SQLException
	{
		ArrayList<String> al=new ArrayList<String>();
		System.out.println("wanna delete any schedule?");
		String reply=sc.nextLine();
		while(reply.equalsIgnoreCase("yes"))
		{
			System.out.println("enter ScheduleId");
			String schedId=sc.nextLine();
			if(st.findByID(schedId)==null)
			{
				System.out.println("no such id exists!");
				return false;
			}
			al.add(schedId);
			System.out.println("wanna delete more?");
			reply=sc.nextLine();
		}
	
		if(al!=null) {
		
		if(admin.removeSchedule(al)!=0) {
			return true;
		}
	
	}
	else {
		System.out.println("no item selected");
	}
		return false;
	}
	public boolean routeAdd() throws SQLException
	{
		
		
		System.out.println("enter source");
		String source=sc.nextLine();
	
		
		System.out.println("enter destination");
		String dest=sc.nextLine();

		
		System.out.println("enter TravelDistance");
		String travelDist=sc.nextLine();
	
		System.out.println("enter Fare");
		int fare=sc.nextInt();
		
		RouteBean rot=new RouteBean(source,dest,travelDist,fare);
		Random rand = new Random();
		rot.setRouteID(rot.getSource().substring(0, 2)+rot.getDestination().substring(0, 2)+String.format("%04d", rand.nextInt(10000)));
		System.out.println("please NOTE UR UNIQUE ID: "+rot.getRouteID());
		
		if(admin.addRoute(rot)!=null) {
			
			return true;
		}
		return false;
	}
	
	public boolean routeModify() throws SQLException
	{
		System.out.println("enter route ID you wanna modify");
		String routeId=sc.nextLine();
		RouteBean rot=admin.viewByRouteId(routeId);
		
		if(rot!=null) {
		System.out.println("enter source");
		String source=sc.nextLine();
		rot.setSource(source);
		
		System.out.println("enter destination");
		String dest=sc.nextLine();
		rot.setDestination(dest);

		System.out.println("enter travel Duration");
		String tduration=sc.nextLine();
		rot.setTravelDuration(tduration);
	
		System.out.println("enter fare");
		int fare=sc.nextInt();
		rot.setFare(fare);
		
		if(admin.modifyRoute(rot)) {
			
			return true;
		}
		
		}
		else {
			System.out.println("id wrong");
		}
		return false;
	}
	
	public boolean routeDelete() throws SQLException
	{
		String id="";
		System.out.println("wanna delete any schedule?");
		String reply=sc.nextLine();
		while(reply.equalsIgnoreCase("yes"))
		{
			System.out.println("enter routeId");
			String routeId=sc.nextLine();
			if(rt.findByID(routeId)==null)
			{
				System.out.println("no such id exists!");
				return false;
			}
			id+=routeId;
			System.out.println("wanna delete more?");
			reply=sc.nextLine();
		}
	
		if(id!="") 
		{
			
			if(admin.removeRoute(id)!=0) 
			{
				return true;
				
			}
			
		}
		else {
			System.out.println("no item selected");
		}
		return false;
	}
	
	
	public boolean allShips() throws SQLException
	{
		ArrayList<FlightBean> al=admin.viewByAllFlights();
		for(FlightBean i:al)
		{
			System.out.println(i.getFlightID()+" "+i.getFlightName()+" "+i.getSeatingCapacity()+" "+i.getReservationCapacity());
		}
		return true;
	}
	
	public boolean allRoutes() throws SQLException
	{
		ArrayList<RouteBean> al=admin.viewByAllRoute();
		for(RouteBean i:al)
		{
			System.out.println(i.getRouteID()+" "+i.getSource()+" "+i.getDestination()+" "+i.getTravelDuration()+" "+i.getFare());
		}
		return true;
	}
	
	public boolean allSchedule() throws SQLException
	{
		ArrayList<ScheduleBean> al=admin.viewByAllSchedule();
		for(ScheduleBean i:al)
		{
			System.out.println(i.getScheduleID()+" "+i.getFlightID()+" "+i.getRouteID()+" "+i.getStartDate());
		}
		return true;
	}
	
	public String reserveTicket() throws SQLException 
	{
		FlightMain spp=new FlightMain();
		ArrayList<ScheduleBean> alsche=new ArrayList();
		int flag=0;
		int count=0;
		System.out.println("enter source");
		String src=sc.nextLine();
		
		System.out.println("enter destination");
		String dest=sc.nextLine();
		
		System.out.println("enter date");
		String date=sc.nextLine();
		String sdate[]=date.split("/");
		LocalDate startDate=LocalDate.of(Integer.parseInt(sdate[2]),Integer.parseInt(sdate[1]),Integer.parseInt(sdate[0]));
		
		alsche=cust.viewScheduleByRoute(src, dest, startDate);
		
		for(ScheduleBean sb:alsche)
		{
			System.out.println(sb.getScheduleID()+" route: "+sb.getRouteID()+" ship:"+sb.getFlightID());
		}
		
		System.out.println("enter the ship Id for details: ");
		String sid=sc.nextLine();
		
		for(ScheduleBean sb:alsche)
		{
			if(sb.getFlightID().equals(sid) )
			{
				flag=1;
				FlightBean shipb =new FlightBean();
				shipb=admin.viewByFlightId(sid);
				if( shipb!=null)
				{
					System.out.println("Name:"+shipb.getFlightName()+" seats:"+shipb.getSeatingCapacity()+" reservationCapacity:"+shipb.getReservationCapacity());
					break;
				}
				else{
					System.out.println("no such ship exits");	
				}
			}
			
		}
		if(flag==0)
		{
		
				System.out.println("This ship is not for desired route");
			
		}
		
		
		System.out.println("enter the route Id for details: ");
		String rid=sc.nextLine();
		
		for(ScheduleBean sb:alsche)
		{
			if(sb.getRouteID().equals(rid) )
			{
				count=1;
				RouteBean routeb =new RouteBean();
				routeb=admin.viewByRouteId(rid);
				if( routeb!=null)
				{
					System.out.println("Source:"+routeb.getSource()+" Destination:"+routeb.getDestination()+" travelDuration(in hrs):"+routeb.getTravelDuration()+" Fare(in INR):"+routeb.getFare());
					break;
				}
				else{
					System.out.println("no such route exits");
				}
				
			}
			
		}
		if(count==0)
		{
		
				System.out.println("This route is not for desired route");
			
		}
		
		ReservationBean reservebn=new ReservationBean();
		ArrayList<PassengerBean> ap=new ArrayList<PassengerBean>();
		
		
		System.out.println(" enter ScheduleId to reserve");
		String rsId=sc.nextLine();

		ScheduleBean schbean=new ScheduleBean();
		schbean=admin.viewByScheduleId(rsId);
		RouteBean rtbn=new RouteBean();
		rtbn=admin.viewByRouteId(schbean.getRouteID());
		if(schbean!=null)
		{
		System.out.println("no Of seats");
		int seats=sc.nextInt();
		
		Random rand = new Random();
		String reserid=rtbn.getSource().substring(0, 2)+rtbn.getDestination().substring(0, 2)+String.format("%04d", rand.nextInt(10000));
		System.out.println("please NOTE UR UNIQUE ID: "+reserid);
			
		if(seats>0) {
			
			
			
			 tfare=seats*rtbn.getFare();
			 reservebn=new ReservationBean(reserid,rsId,credit.getUserID(),LocalDate.now(),schbean.getStartDate(),seats,tfare,"pending");
			 
			 
			 
	FlightMain us=new FlightMain();
	ap=us.passengerInput(rsId,seats);
			 
	
			 if(spp.paymentCredit())
			 {
				 System.out.println("payment success!"+reservebn.getNoOfSeats());
				 cust.changeBookingStatus(reservebn);
			 }
			
			
		}
		else {
			System.out.println("No seats are reserved!");
		}
		
		}
		else {
			System.out.println("no such id exists");
		}
		
		cust.reserveTicket(reservebn, ap);
		return reservebn.getReservationID();
		
	}
	
	public boolean paymentCredit() throws SQLException
	{
		System.out.println("wanna pay?");
		String payes=sc.nextLine();
		if(payes.equalsIgnoreCase("yes"))
		{
			System.out.println("enter card");
			String card=sc.nextLine();
			
			if(pay.findByCardNumber(credit.getUserID(),card)) {
			
				System.out.println("thankyou for payment");
			}
			else {
				System.out.println("you need to enter details");
				
				
				System.out.println("card valid from");
				String validate=sc.nextLine();
				
				System.out.println("card valid to");
				String todate=sc.nextLine();
				
				System.out.println("balance payable");
				int bal=sc.nextInt();
				
				
				PaymentBean paymentb=new PaymentBean(card,validate,todate,bal,credit.getUserID());
				pay.process(paymentb);
				
			}
			return true;
			
		}
		else {
			System.out.println("your confirmation is still pending!");
			return false;
		}
	}

	public ArrayList<PassengerBean> passengerInput(String rsId,int seats)
	{
		ArrayList<PassengerBean> ap=new ArrayList<PassengerBean>();
		 for(int j=0;j<seats;j++)
			{
				System.out.println("enter details for passenger "+(j+1));
				
				System.out.println("enter name");
				sc.nextLine();
				String pname=sc.nextLine();
				
				System.out.println("enter age"); 
				int page=sc.nextInt();
				
				System.out.println("enter gender");
				sc.nextLine();
				String pgender=sc.nextLine();
				
				PassengerBean pb=new PassengerBean(rsId,pname,page,pgender);
				ap.add(pb);
				//System.out.println(rsId);
			}	
		// System.out.println(ap.size());
		 return ap;
		 
	}
	
}


