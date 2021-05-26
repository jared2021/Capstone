package test;
import javax.swing.*;

import java.awt.event.*;
import java.sql.SQLException;
import java.sql.Time;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.*;
import java.util.ArrayList;

public class NewEventUI implements ActionListener{

	private JFrame frame;
	private JPanel panel;
	
	private ArrayList<Event> events;
	private Calendar calendar;
	private int userID;
	
	private JTextField description;
	private JTextField location;
	private JTextField month;
	private JTextField day;
	private JTextField year;
	
	private JLabel descriptionLabel;
	private JLabel locationLabel;
	private JLabel monthLabel;
	private JLabel dayLabel;
	private JLabel yearLabel;
	private JLabel startTimeLabel;
	private JLabel startTimeColon;
	private JLabel endTimeLabel;
	private JLabel endTimeColon;
	//private JLabel repeatLabel;
	//private JLabel alertLabel;
	private JLabel message;
	private JLabel costLabel;
	
	private JComboBox startTimeHour;
	private JComboBox startTimeMinute;
	private JComboBox startTimePeriod;
	private JComboBox endTimeHour;
	private JComboBox endTimeMinute;
	private JComboBox endTimePeriod;
	private JComboBox cost;
	//private JComboBox repeat;
	//private JComboBox alert;
	
	private JButton createEvent;
	
	private String[] hour = {"1","2","3","4","5","6","7","8","9","10","11","12"};
	private String[] minute = {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59"};
	private String[] period = {"AM","PM"};
	
	//private String[] repeatPeriod = {"every day", "once a week", "once a month", "once a year"};
	//private String[] alertPeriod = {"at time of event", "an hour before", "two hours before", "a day before", "a week before", "two weeks before"};
	private String[] costAmount = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
	
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int width = (int)screenSize.getWidth();
	private int height = (int)screenSize.getHeight();
	 
	
	public NewEventUI(int userID ,ArrayList<Event> events, Calendar calendar) {
		this.userID = userID;
		this.events = events;
		this.calendar = calendar;
		
		frame = new JFrame();
		frame.setSize(width/2, height/2);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		description = new JTextField();
		description.setSize(200,25);
		description.setLocation(width/8, height/8);
		
		descriptionLabel = new JLabel("Description:");
		descriptionLabel.setSize(150,25);
		descriptionLabel.setLocation(description.getX() - 100, description.getY());
		
		location = new JTextField();
		location.setSize(200,25);
		location.setLocation(width/8 + 400, height/8);
		
		locationLabel = new JLabel("Location:");
		locationLabel.setSize(150,25);
		locationLabel.setLocation(location.getX() - 100,location.getY());
		
		month = new JTextField();
		month.setSize(50,25);
		month.setLocation(width/8, height/6);
		
		monthLabel = new JLabel("Month:");
		monthLabel.setSize(50,25);
		monthLabel.setLocation(month.getX() - 50, month.getY());
		
		day = new JTextField();
		day.setSize(50,25);
		day.setLocation(month.getX() + 300, month.getY());
		
		dayLabel = new JLabel("Day:");
		dayLabel.setSize(50,25);
		dayLabel.setLocation(day.getX() - 50, day.getY());
		
		year = new JTextField();
		year.setSize(50,25);
		year.setLocation(day.getX() + 300, day.getY());
		
		yearLabel= new JLabel("Year:");
		yearLabel.setSize(50,25);
		yearLabel.setLocation(year.getX() - 50, year.getY());
		
		startTimeHour = new JComboBox(hour);
		startTimeHour.setSize(50,25);
		startTimeHour.setLocation(width/8, height/5);
		
		startTimeLabel = new JLabel("Start Time:");
		startTimeLabel.setSize(100,25);
		startTimeLabel.setLocation(startTimeHour.getX() - 75, startTimeHour.getY());
		
		startTimeColon = new JLabel(":");
		startTimeColon.setSize(10,25);
		startTimeColon.setLocation(startTimeHour.getX() + 60, startTimeHour.getY());
		
		startTimeMinute = new JComboBox(minute);
		startTimeMinute.setSize(50,25);
		startTimeMinute.setLocation(startTimeHour.getX() + 75, startTimeHour.getY());
		
		startTimePeriod = new JComboBox(period);
		startTimePeriod.setSize(50,25);
		startTimePeriod.setLocation(startTimeHour.getX() + 125, startTimeHour.getY());
		
		endTimeHour = new JComboBox(hour);
		endTimeHour.setSize(50,25);
		endTimeHour.setLocation(startTimeHour.getX() + 350, startTimeHour.getY());
		
		endTimeLabel = new JLabel("End Time:");
		endTimeLabel.setSize(100,25);
		endTimeLabel.setLocation(endTimeHour.getX() - 75, endTimeHour.getY());
		
		endTimeColon = new JLabel(":");
		endTimeColon.setSize(10,25);
		endTimeColon.setLocation(endTimeHour.getX() + 60, endTimeHour.getY());
		
		endTimeMinute = new JComboBox(minute);
		endTimeMinute.setSize(50,25);
		endTimeMinute.setLocation(endTimeHour.getX() + 75, endTimeHour.getY());
		
		endTimePeriod = new JComboBox(period);
		endTimePeriod.setSize(50,25);
		endTimePeriod.setLocation(endTimeHour.getX() + 125, endTimeHour.getY());
		
		cost = new JComboBox(costAmount);
		cost.setSize(100,25);
		cost.setLocation(width/8, height/4);
		
		costLabel = new JLabel("Cost");
		costLabel.setSize(100, 25);
		costLabel.setLocation(cost.getX() - 100, cost.getY());
		
		/*repeat = new JComboBox(repeatPeriod);
		repeat.setSize(100,25);
		repeat.setLocation(width/8, height/4);
		
		repeatLabel = new JLabel("Repeat Event:");
		repeatLabel.setSize(100,25);
		repeatLabel.setLocation(repeat.getX() - 100, repeat.getY());
		
		alert = new JComboBox(alertPeriod);
		alert.setSize(100,25);
		alert.setLocation(repeat.getX() + 500, repeat.getY());
		
		alertLabel = new JLabel("Alert you prior to event:");
		alertLabel.setSize(200,25);
		alertLabel.setLocation(alert.getX() - 150, alert.getY());*/
		
		createEvent = new JButton("Create Event");
		createEvent.setSize(150,25);
		createEvent.setLocation(width/4, height/3);
		createEvent.addActionListener(this);
		
		message = new JLabel("");
		message.setSize(200,25);
		message.setLocation(createEvent.getX(), createEvent.getY() + 200);
		
		panel.add(description);
		panel.add(location);
		panel.add(descriptionLabel);
		panel.add(locationLabel);
		panel.add(month);
		panel.add(monthLabel);
		panel.add(day);
		panel.add(dayLabel);
		panel.add(year);
		panel.add(yearLabel);
		panel.add(startTimeHour);
		panel.add(startTimeLabel);
		panel.add(startTimeMinute);
		panel.add(startTimeColon);
		panel.add(startTimePeriod);
		panel.add(endTimeHour);
		panel.add(endTimeLabel);
		panel.add(endTimeColon);
		panel.add(endTimeMinute);
		panel.add(endTimePeriod);
		panel.add(cost);
		panel.add(costLabel);
		/*panel.add(repeat);
		panel.add(repeatLabel);
		panel.add(alert);
		panel.add(alertLabel);*/
		panel.add(createEvent);
		panel.add(message);
		
		frame.add(panel,null);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("Create new event");
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == createEvent)
		{
			if(description.getText() == "" || month.getText() == "" || day.getText() == "" || year.getText() == "")
			{
				message.setText("Missing information");
			}
			else if(Integer.parseInt(month.getText()) < 0 || Integer.parseInt(month.getText()) > 12)
			{
				message.setText("Invalid month");
			}
			else
			{
				try {
					calendar.createNewEvent(userID,description.getText(), location.getText(), month.getText(), day.getText(), year.getText(), startTimeHour.getSelectedItem().toString(), startTimeMinute.getSelectedItem().toString(), startTimePeriod.getSelectedItem().toString(), endTimeHour.getSelectedItem().toString(), endTimeMinute.getSelectedItem().toString(), endTimePeriod.getSelectedItem().toString(), cost.getSelectedItem().toString());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
}
