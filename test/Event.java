package test;
import java.sql.Time;

public class Event {

	private int eventId;
	private int userId;
	private String description;
	private String location;
	private int month;
	private int day;
	private int year;
	private String startTimeHour;
	private String startTimeMinute;
	private String startTimePeriod;
	private String endTimeHour;
	private String endTimeMinute;
	private String endTimePeriod;
	private int cost;
	private boolean deleted;
	public Event(int Eventid, int Userid, String description, String location, int month, int day, int year, String startTimeHour, String startTimeMinute, String startTimePeriod, String endTimeHour, String endTimeMinute, String endTimePeriod, int cost, boolean deleted) {
		eventId = Eventid;
		userId = Userid;
		this.description = description;
		this.location = location;
		this.day = day;
		this.month = month;
		this.year =year;
		this.startTimeHour = startTimeHour;
		this.startTimeMinute = startTimeMinute;
		this.startTimePeriod = startTimePeriod;
		this.endTimeHour = endTimeHour;
		this.endTimeMinute = endTimeMinute;
		this.endTimePeriod = endTimePeriod;
		this.cost = cost;
		this.deleted = deleted;
	}
	
	public int getEventId() {
		return eventId;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public int getDay() {
		return day;
	}
	
	public void setDay(int day) {
		this.day = day;
	}
	
	public int getMonth() {
		return month;
	}
	
	public void setMonth(int month) {
		this.month = month;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public String getStartTimeHour() {
		return startTimeHour;
	}
	
	public void setStartTimeHour(String startTimeHour) {
		this.startTimeHour = startTimeHour;
	}
	
	public String getStartTimeMinute() {
		return startTimeMinute;
	}
	
	public void setStartTimeMinute(String startTimeMinute) {
		this.startTimeMinute = startTimeMinute;
	}
	
	public String getStartTimePeriod() {
		return startTimePeriod;
	}
	
	public void setStartTimePeriod(String starTimePeriod) {
		this.startTimePeriod = startTimePeriod;
	}
	
	public String getEndTimeHour() {
		return endTimeHour;
	}
	
	public void setEndTimeHour(String endTimeHour) {
		this.endTimeHour = endTimeHour;
	}
	
	public String getEndTimeMinute() {
		return endTimeMinute;
	}
	
	public void setEndTimeMinute(String endTimeMinute) {
		this.endTimeMinute = endTimeMinute;
	}
	
	public String getEndTimePeriod() {
		return endTimePeriod;
	}
	
	public void setEndTimePeriod(String endTimePeriod) {
		this.endTimePeriod = endTimePeriod;
	}
	
	
	public int getCost() {
		return cost;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public boolean getDeleted() {
		return deleted;
	}
	
	public void setDeleted(boolean deleted)
	{
		this.deleted = deleted;
	}
	
}
