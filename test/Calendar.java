package test;
import java.sql.*;

import java.util.ArrayList;

import java.util.Random;

public class Calendar {
	
	Connection con;
	int[] isUser = {0,-1};
	int userID;
	ArrayList<Event> events = new ArrayList<Event>();
	
	Random random = new Random();
	
	Calendar()throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
		String connectionUrl = "jdbc:sqlserver://capstone-project.database.windows.net:1433;database=Capstone;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";  
		con = DriverManager.getConnection(connectionUrl,"jascho","Windmaster52631");
	}
	
	public int[] testCredentials(String testUserName, String testPassword) throws SQLException { 
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM USERS");
		while(rs.next())
		{
			int userID = rs.getInt("Userid");
			String userNameData = rs.getString("UserName");
			String passwordData = rs.getString("Password");
			if(userNameData.equals(testUserName) && passwordData.equals(testPassword))
			{
				isUser[0] = 1;
				isUser[1] = userID;
			}
		}
		return isUser;
	}
	
	public boolean createCredentials(String userName, String testUserName, String password, String testPassword) throws SQLException {
		boolean doesExist = false;
		if(!userName.equals(testUserName) || !password.equals(testPassword))
		{
			doesExist = true;
			return doesExist;
		}
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT UserName FROM Users");
		while(rs.next())
		{
			String testName = rs.getString("UserName");
			if(testName.equals(userName))
			{
				doesExist = true;
				return doesExist;
			}
		}
		if(!doesExist)
		{
			stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO Users VALUES ('" + userName + "','" + password + "')");
		}
		return doesExist;
	}
	
	public ArrayList<Event> getEvents(int UserID) throws SQLException
	{
		userID = UserID; 
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM Events WHERE Userid = " + userID);
		while(rs.next())
		{
			int eventId = rs.getInt("EventID");
			String description = rs.getString("Description");
			String location = rs.getString("Location");
			int month = rs.getInt("Month");
			int day = rs.getInt("Day");
			int year = rs.getInt("Year");
			String startTimeHour = rs.getString("StartTimeHour");
			String startTimeMinute = rs.getString("StartTimeMinute");
			String startTimePeriod = rs.getString("StartTimePeriod");
			String endTimeHour = rs.getString("EndTimeHour");
			String endTimeMinute = rs.getString("EndTimeMinute");
			String endTimePeriod = rs.getString("EndTimePeriod");
			int cost = rs.getInt("Cost");
			boolean deleted = rs.getBoolean("Deleted");
			Event event = new Event(eventId, userID, description, location, month, day, year, startTimeHour, startTimeMinute, startTimePeriod, endTimeHour, endTimeMinute, endTimePeriod, cost, deleted);
			events.add(event);
		}
		this.createCalendarUI(userID,events);
		return events;
	}
	
	public ArrayList<Event> checkEvents(int UserID) throws SQLException
	{
		userID = UserID; 
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM Events WHERE Userid = " + userID);
		events.clear();
		while(rs.next())
		{
			int eventId = rs.getInt("EventID");
			String description = rs.getString("Description");
			String location = rs.getString("Location");
			int month = rs.getInt("Month");
			int day = rs.getInt("Day");
			int year = rs.getInt("Year");
			String startTimeHour = rs.getString("StartTimeHour").replaceAll("\\s", "");
			String startTimeMinute = rs.getString("StartTimeMinute").replaceAll("\\s", "");
			String startTimePeriod = rs.getString("StartTimePeriod").replaceAll("\\s", "");
			String endTimeHour = rs.getString("EndTimeHour").replaceAll("\\s", "");
			String endTimeMinute = rs.getString("EndTimeMinute").replaceAll("\\s", "");
			String endTimePeriod = rs.getString("EndTimePeriod").replaceAll("\\s", "");
			int cost = rs.getInt("Cost");
			boolean deleted = rs.getBoolean("Deleted");
			Event event = new Event(eventId, userID, description, location, month, day, year, startTimeHour, startTimeMinute, startTimePeriod, endTimeHour, endTimeMinute, endTimePeriod, cost, deleted);
			events.add(event);
		}
		return events;
	}
	
	public void createCalendarUI(int userID, ArrayList<Event> events)
	{
		CalendarUI calendarUI = new CalendarUI(userID, this, events);
	}
	
	public void createNewEvent(int userID, String description, String location, String month, String day, String year, String startTimeHour, String startTimeMinute, String startTimePeriod, String endTimeHour, String endTimeMinute, String endTimePeriod, String cost) throws SQLException 
	{
		int newUserID = userID;
		String newDescription = description;
		String newLocation = location;
		int newMonth = Integer.parseInt(month);
		int newDay = Integer.parseInt(day);
		int newYear = Integer.parseInt(year);
		String newStartTimeHour = startTimeHour;
		String newStartTimeMinute = startTimeMinute;
		newStartTimeMinute = newStartTimeMinute.replaceAll("\\s", "");
		String newStartTimePeriod = startTimePeriod;
		String newEndTimeHour = endTimeHour;
		String newEndTimeMinute = endTimeMinute;
		newEndTimeMinute = newEndTimeMinute.replaceAll("\\s", "");
		String newEndTimePeriod = endTimePeriod;
		int newCost = Integer.parseInt(cost);
		boolean deleted = false;
		System.out.println(startTimeMinute);
		System.out.println(startTimeMinute.length());
		Statement stmt = con.createStatement();
		String statement = "'" + userID + "',' " + description + "',' " + location + "',' " + newMonth + "',' " + newDay + "',' " + newYear + "',' " + newStartTimeHour + "',' " + newStartTimeMinute + "',' " + newStartTimePeriod + "',' " + newEndTimeHour + "',' " + newEndTimeMinute + "',' " + newEndTimePeriod + "',' " + cost + "',' " + deleted + "'";
		stmt.executeUpdate("INSERT INTO Events VALUES (" + statement +")");
	}
	
	public boolean randomizeCost(int userID)
	{
		try {
			int totalEvents = 0;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM Events");
			rs.next();
			totalEvents = rs.getInt(1);
			System.out.println(totalEvents);
			for(int i=1;i<totalEvents;i++)
			{
				int randomCost = random.nextInt(10) + 1;
				Statement statement = con.createStatement();
				statement.executeUpdate("UPDATE Events SET Cost = " + randomCost + " WHERE Eventid = " + i);
			}
			return true;
		}
		catch(SQLException e)
		{
			System.out.println("Something went wrong when trying to randomize the cost");
			e.printStackTrace();
		}
		return false;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		Calendar calendar = new Calendar();
		Login loginScreen = new Login(calendar);
	}

}
