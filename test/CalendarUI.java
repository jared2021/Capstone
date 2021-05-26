package test;
import javax.swing.*;

import java.awt.event.*;
import java.sql.SQLException;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.*;
import java.util.ArrayList;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;

public class CalendarUI implements ActionListener{

	private Calendar calendar;
	private JFrame frame;
	private JPanel panel;
	private int userID;
	
	private Optimize optimize;
	
	private NewEventUI newEventUI;
	
	private String stringCurrentMonth;
	
	//used to get current month and year for calendar
	private LocalDate currentDate = LocalDate.now();
	private Month currentMonth = currentDate.getMonth();
	private String currentYear = Integer.toString(currentDate.getYear());
	
	private LocalDate previousMonthDate;
	private LocalDate previousMonthDateSunday;
	private LocalDate previousMonthDateMonday;
	private LocalDate previousMonthDateTuesday;
	private LocalDate previousMonthDateWednesday;
	private LocalDate previousMonthDateThursday;
	private LocalDate previousMonthDateFriday;
	private LocalDate previousMonthDateSaturday;
	
	private LocalDate currentMonthDate;
	private LocalDate currentMonthDateSunday;
	private LocalDate currentMonthDateMonday;
	private LocalDate currentMonthDateTuesday;
	private LocalDate currentMonthDateWednesday;
	private LocalDate currentMonthDateThursday;
	private LocalDate currentMonthDateFriday;
	private LocalDate currentMonthDateSaturday;
	
	private LocalDate nextMonthDate;
	private LocalDate nextMonthDateSunday;
	private LocalDate nextMonthDateMonday;
	private LocalDate nextMonthDateTuesday;
	private LocalDate nextMonthDateWednesday;
	private LocalDate nextMonthDateThursday;
	private LocalDate nextMonthDateFriday;
	private LocalDate nextMonthDateSaturday;
	
	//constant variables to check what month the calendar currently holds
	private final String JANUARY = "1";
	private final String FEBRUARY = "2";
	private final String MARCH = "3";
	private final String APRIL = "4";
	private final String MAY = "5";
	private final String JUNE = "6";
	private final String JULY = "7";
	private final String AUGUST = "8";
	private final String SEPTEMBER = "9";
	private final String OCTOBER = "10";
	private final String NOVEMBER = "11";
	private final String DECEMBER = "12";
	
	//labels to show current month/year on calendar and days
	private JLabel month;
	private JLabel year;
	private JLabel sunday;
	private JLabel monday;
	private JLabel tuesday;
	private JLabel wednesday;
	private JLabel thursday;
	private JLabel friday;
	private JLabel saturday;
	
	private ArrayList<Event> events = new ArrayList<Event>();
	
	//variables to hold the screen size
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int width = (int)screenSize.getWidth();
	private int height = (int)screenSize.getHeight();
	
	//labels to build calendar dates
	private JLabel boxOne = new JLabel();
	private JLabel boxTwo = new JLabel();
	private JLabel boxThree = new JLabel();
	private JLabel boxFour = new JLabel();
	private JLabel boxFive = new JLabel();
	private JLabel boxSix = new JLabel();
	private JLabel boxSeven = new JLabel();
	private JLabel boxEight = new JLabel();
	private JLabel boxNine = new JLabel();
	private JLabel boxTen = new JLabel();
	private JLabel boxEleven = new JLabel();
	private JLabel boxTwelve = new JLabel();
	private JLabel boxThirteen = new JLabel();
	private JLabel boxFourteen = new JLabel();
	private JLabel boxFifteen = new JLabel();
	private JLabel boxSixteen = new JLabel();
	private JLabel boxSeventeen = new JLabel();
	private JLabel boxEighteen = new JLabel();
	private JLabel boxNinteen = new JLabel();
	private JLabel boxTwenty = new JLabel();
	private JLabel boxTwentyOne = new JLabel();
	private JLabel boxTwentyTwo = new JLabel();
	private JLabel boxTwentyThree = new JLabel();
	private JLabel boxTwentyFour = new JLabel();
	private JLabel boxTwentyFive = new JLabel();
	private JLabel boxTwentySix = new JLabel();
	private JLabel boxTwentySeven = new JLabel();
	private JLabel boxTwentyEight = new JLabel();
	private JLabel boxTwentyNine = new JLabel();
	private JLabel boxThirty = new JLabel();
	private JLabel boxThirtyOne = new JLabel();
	private JLabel boxThirtyTwo = new JLabel();
	private JLabel boxThirtyThree = new JLabel();
	private JLabel boxThirtyFour = new JLabel();
	private JLabel boxThirtyFive = new JLabel();
	private JLabel boxThirtySix = new JLabel();
	private JLabel boxThirtySeven = new JLabel();
	private JLabel boxThirtyEight = new JLabel();
	private JLabel boxThirtyNine = new JLabel();
	private JLabel boxFourty = new JLabel();
	private JLabel boxFourtyOne = new JLabel();
	private JLabel boxFourtyTwo = new JLabel();
	
	private ArrayList<JLabel> calendarMaterial = new ArrayList<JLabel>();
	
	private JButton newEvent = new JButton();
	private JButton optimalEvents = new JButton();
	private JButton randomCost = new JButton();
	private JButton addMonth = new JButton();
	private JButton subtractMonth = new JButton();
	
	public CalendarUI(int userID, Calendar calendar, ArrayList<Event> events)
	{
		this.userID = userID;
		this.calendar = calendar;
		this.events = events;
		
		frame = new JFrame();
		frame.setSize(width, height);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		month = new JLabel();
		month.setSize(100,25);
		month.setText(this.setMonthText());
		month.setLocation(width/2 - 20, height/8 - 100);
		
		if(Integer.parseInt(stringCurrentMonth) - 1 < 1)
		{
			previousMonthDate = LocalDate.of(Integer.parseInt(currentYear) - 1, 12, 1);
		}
		else
		{
			previousMonthDate = LocalDate.of(Integer.parseInt(currentYear), Integer.parseInt(stringCurrentMonth) - 1, 1);
		}
		previousMonthDateSunday = previousMonthDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.SUNDAY));
		previousMonthDateMonday = previousMonthDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
		previousMonthDateTuesday = previousMonthDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.TUESDAY));
		previousMonthDateWednesday = previousMonthDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.WEDNESDAY));
		previousMonthDateThursday = previousMonthDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.THURSDAY));
		previousMonthDateFriday = previousMonthDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.FRIDAY));
		previousMonthDateSaturday = previousMonthDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.SATURDAY));
		
		currentMonthDate = LocalDate.of(Integer.parseInt(currentYear), Integer.parseInt(stringCurrentMonth), 1);
		currentMonthDateSunday = currentMonthDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.SUNDAY));
		currentMonthDateMonday = currentMonthDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
		currentMonthDateTuesday = currentMonthDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.TUESDAY));
		currentMonthDateWednesday = currentMonthDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.WEDNESDAY));
		currentMonthDateThursday = currentMonthDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.THURSDAY));
		currentMonthDateFriday = currentMonthDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.FRIDAY));
		currentMonthDateSaturday = currentMonthDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.SATURDAY));
		
		if(Integer.parseInt(stringCurrentMonth) + 1 > 12)
		{
			nextMonthDate = LocalDate.of(Integer.parseInt(currentYear) + 1, 1, 1);
		}
		else
		{
			nextMonthDate = LocalDate.of(Integer.parseInt(currentYear), Integer.parseInt(stringCurrentMonth) + 1, 1);
		}
		nextMonthDateSunday = nextMonthDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.SUNDAY));
		nextMonthDateMonday = nextMonthDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
		nextMonthDateTuesday = nextMonthDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.TUESDAY));
		nextMonthDateWednesday = nextMonthDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.WEDNESDAY));
		nextMonthDateThursday = nextMonthDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.THURSDAY));
		nextMonthDateFriday = nextMonthDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.FRIDAY));
		nextMonthDateSaturday = nextMonthDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.SATURDAY));
		
		subtractMonth.setSize(50,25);
		subtractMonth.setText("<");
		subtractMonth.setLocation(month.getX() - 50, month.getY());
		subtractMonth.addActionListener(this);
		
		year = new JLabel();
		year.setSize(100,25);
		year.setText(currentYear);
		year.setLocation(month.getX() + 70, month.getY());
		
		addMonth.setSize(50,25);
		addMonth.setText(">");
		addMonth.setLocation(year.getX() + 30, year.getY());
		addMonth.addActionListener(this);
		
		sunday = new JLabel();
		sunday.setSize(100, 25);
		sunday.setText("Sun");
		sunday.setLocation(width/8, height/12);
		
		monday = new JLabel();
		monday.setSize(100,25);
		monday.setText("Mon");
		monday.setLocation(2*width/8, height/12);
		
		tuesday = new JLabel();
		tuesday.setSize(100,25);
		tuesday.setText("Tue");
		tuesday.setLocation(3*width/8, height/12);
		
		wednesday = new JLabel();
		wednesday.setSize(100,25);
		wednesday.setText("Wed");
		wednesday.setLocation(4*width/8, height/12);
		
		thursday = new JLabel();
		thursday.setSize(100,25);
		thursday.setText("Thur");
		thursday.setLocation(5*width/8, height/12);
		
		friday = new JLabel();
		friday.setSize(100,25);
		friday.setText("Fri");
		friday.setLocation(6*width/8, height/12);
		
		saturday = new JLabel();
		saturday.setSize(100,25);
		saturday.setText("Sat");
		saturday.setLocation(7*width/8, height/12);
		
		newEvent.setSize(150,25);
		newEvent.setText("Create new event");
		newEvent.setLocation(0,0);
		newEvent.addActionListener(this);

		optimalEvents.setSize(150,25);
		optimalEvents.setText("Give optimal events");
		optimalEvents.setLocation(width - 150, 0);
		optimalEvents.addActionListener(this);
		
		randomCost.setSize(150,25);
		randomCost.setText("Randomize costs");
		randomCost.setLocation(optimalEvents.getX() - 150, optimalEvents.getY());
		randomCost.addActionListener(this);
		
		panel.add(sunday);
		panel.add(monday);
		panel.add(tuesday);
		panel.add(wednesday);
		panel.add(thursday);
		panel.add(friday);
		panel.add(saturday);
		panel.add(month);
		panel.add(year);
		panel.add(newEvent);
		panel.add(addMonth);
		panel.add(subtractMonth);
		panel.add(optimalEvents);
		panel.add(randomCost);
		
		this.setUpLabels();
		this.changeDays();
		
		frame.add(panel,null);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Calendar");
		frame.setVisible(true);
	}
	
	public String setMonthText()
	{
		if(currentMonth == Month.JANUARY)
		{
			stringCurrentMonth = "1";
			return "January";
		}
		else if(currentMonth == Month.FEBRUARY)
		{
			stringCurrentMonth = "2";
			return "February";
		}
		else if(currentMonth == Month.MARCH)
		{
			stringCurrentMonth = "3";
			return "March";
		}
		else if(currentMonth == Month.APRIL)
		{
			stringCurrentMonth = "4";
			return "April";
		}
		else if(currentMonth == Month.MAY)
		{
			stringCurrentMonth = "5";
			return "May";
		}
		else if(currentMonth == Month.JUNE)
		{
			stringCurrentMonth = "6";
			return "June";
		}
		else if(currentMonth == Month.JULY)
		{
			stringCurrentMonth = "7";
			return "July";
		}
		else if(currentMonth == Month.AUGUST)
		{
			stringCurrentMonth = "8";
			return "August";
		}
		else if(currentMonth == Month.SEPTEMBER)
		{
			stringCurrentMonth = "9";
			return "September";
		}
		else if(currentMonth == Month.OCTOBER)
		{
			stringCurrentMonth = "10";
			return "October";
		}
		else if(currentMonth == Month.NOVEMBER)
		{
			stringCurrentMonth = "11";
			return "November";
		}
		else if(currentMonth == Month.DECEMBER)
		{
			stringCurrentMonth = "12";
			return "December";
		}
		else
		{
			return "Error";
		}
	}
	
	public void setUpLabels()
	{
		calendarMaterial.add(boxOne);
		calendarMaterial.add(boxTwo);
		calendarMaterial.add(boxThree);
		calendarMaterial.add(boxFour);
		calendarMaterial.add(boxFive);
		calendarMaterial.add(boxSix);
		calendarMaterial.add(boxSeven);
		calendarMaterial.add(boxEight);
		calendarMaterial.add(boxNine);
		calendarMaterial.add(boxTen);
		calendarMaterial.add(boxEleven);
		calendarMaterial.add(boxTwelve);
		calendarMaterial.add(boxThirteen);
		calendarMaterial.add(boxFourteen);
		calendarMaterial.add(boxFifteen);
		calendarMaterial.add(boxSixteen);
		calendarMaterial.add(boxSeventeen);
		calendarMaterial.add(boxEighteen);
		calendarMaterial.add(boxNinteen);
		calendarMaterial.add(boxTwenty);
		calendarMaterial.add(boxTwentyOne);
		calendarMaterial.add(boxTwentyTwo);
		calendarMaterial.add(boxTwentyThree);
		calendarMaterial.add(boxTwentyFour);
		calendarMaterial.add(boxTwentyFive);
		calendarMaterial.add(boxTwentySix);
		calendarMaterial.add(boxTwentySeven);
		calendarMaterial.add(boxTwentyEight);
		calendarMaterial.add(boxTwentyNine);
		calendarMaterial.add(boxThirty);
		calendarMaterial.add(boxThirtyOne);
		calendarMaterial.add(boxThirtyTwo);
		calendarMaterial.add(boxThirtyThree);
		calendarMaterial.add(boxThirtyFour);
		calendarMaterial.add(boxThirtyFive);
		calendarMaterial.add(boxThirtySix);
		calendarMaterial.add(boxThirtySeven);
		calendarMaterial.add(boxThirtyEight);
		calendarMaterial.add(boxThirtyNine);
		calendarMaterial.add(boxFourty);
		calendarMaterial.add(boxFourtyOne);
		calendarMaterial.add(boxFourtyTwo);
		
		this.setLocation();
		
		for(int i=0; i<calendarMaterial.size(); i++)
		{
			calendarMaterial.get(i).setSize(width/8,height/8);
			calendarMaterial.get(i).setBackground(Color.WHITE);
			calendarMaterial.get(i).setOpaque(true);
			String temp = String.valueOf(i + 1);
			calendarMaterial.get(i).setText(temp);
			panel.add(calendarMaterial.get(i));
		}
	}
	
	public void setLocation()
	{
		calendarMaterial.get(0).setLocation(width/8,height/8);
		calendarMaterial.get(1).setLocation(2*width/8, height/8);
		calendarMaterial.get(2).setLocation(3*width/8, height/8);
		calendarMaterial.get(3).setLocation(4*width/8, height/8);
		calendarMaterial.get(4).setLocation(5*width/8, height/8);
		calendarMaterial.get(5).setLocation(6*width/8, height/8);
		calendarMaterial.get(6).setLocation(7*width/8, height/8);
		calendarMaterial.get(7).setLocation(width/8, 2*height/8);
		calendarMaterial.get(8).setLocation(2*width/8, 2*height/8);
		calendarMaterial.get(9).setLocation(3*width/8, 2*height/8);
		calendarMaterial.get(10).setLocation(4*width/8, 2*height/8);
		calendarMaterial.get(11).setLocation(5*width/8, 2*height/8);
		calendarMaterial.get(12).setLocation(6*width/8, 2*height/8);
		calendarMaterial.get(13).setLocation(7*width/8, 2*height/8);
		calendarMaterial.get(14).setLocation(width/8, 3*height/8);
		calendarMaterial.get(15).setLocation(2*width/8, 3*height/8);
		calendarMaterial.get(16).setLocation(3*width/8, 3*height/8);
		calendarMaterial.get(17).setLocation(4*width/8, 3*height/8);
		calendarMaterial.get(18).setLocation(5*width/8, 3*height/8);
		calendarMaterial.get(19).setLocation(6*width/8, 3*height/8);
		calendarMaterial.get(20).setLocation(7*width/8, 3*height/8);
		calendarMaterial.get(21).setLocation(width/8, 4*height/8);
		calendarMaterial.get(22).setLocation(2*width/8, 4*height/8);
		calendarMaterial.get(23).setLocation(3*width/8, 4*height/8);
		calendarMaterial.get(24).setLocation(4*width/8, 4*height/8);
		calendarMaterial.get(25).setLocation(5*width/8, 4*height/8);
		calendarMaterial.get(26).setLocation(6*width/8, 4*height/8);
		calendarMaterial.get(27).setLocation(7*width/8, 4*height/8);
		calendarMaterial.get(28).setLocation(width/8, 5*height/8);
		calendarMaterial.get(29).setLocation(2*width/8, 5*height/8);
		calendarMaterial.get(30).setLocation(3*width/8, 5*height/8);
		calendarMaterial.get(31).setLocation(4*width/8, 5*height/8);
		calendarMaterial.get(32).setLocation(5*width/8, 5*height/8);
		calendarMaterial.get(33).setLocation(6*width/8, 5*height/8);
		calendarMaterial.get(34).setLocation(7*width/8, 5*height/8);
		calendarMaterial.get(35).setLocation(width/8, 6*height/8);
		calendarMaterial.get(36).setLocation(2*width/8, 6*height/8);
		calendarMaterial.get(37).setLocation(3*width/8, 6*height/8);
		calendarMaterial.get(38).setLocation(4*width/8, 6*height/8);
		calendarMaterial.get(39).setLocation(5*width/8, 6*height/8);
		calendarMaterial.get(40).setLocation(6*width/8, 6*height/8);
		calendarMaterial.get(41).setLocation(7*width/8, 6*height/8);

	}
	
	public void changeMonth()
	{
		if(stringCurrentMonth.equals(JANUARY))
		{
			month.setText("January");
		}
		else if(stringCurrentMonth.equals(FEBRUARY))
		{
			month.setText("February");
		}
		else if(stringCurrentMonth.equals(MARCH))
		{
			month.setText("March");
		}
		else if(stringCurrentMonth.equals(APRIL))
		{
			month.setText("April");
		}
		else if(stringCurrentMonth.equals(MAY))
		{
			month.setText("May");
		}
		else if(stringCurrentMonth.equals(JUNE))
		{
			month.setText("June");
		}
		else if(stringCurrentMonth.equals(JULY))
		{
			month.setText("July");
		}
		else if(stringCurrentMonth.equals(AUGUST))
		{
			month.setText("August");
		}
		else if(stringCurrentMonth.equals(SEPTEMBER))
		{
			month.setText("September");
		}
		else if(stringCurrentMonth.equals(OCTOBER))
		{
			month.setText("October");
		}
		else if(stringCurrentMonth.equals(NOVEMBER))
		{
			month.setText("November");
		}
		else if(stringCurrentMonth.equals(DECEMBER))
		{
			month.setText("December");
		}
		else
		{
			System.out.println("Error");
		}
	}
	
	public void changeDays()
	{
		if(currentMonthDate == currentMonthDateSunday)
		{
			int start = 0;
			this.setLabelText(start);
		}
		else if(currentMonthDate == currentMonthDateMonday)
		{
			int start = 1;
			this.setLabelText(start);
		}
		else if(currentMonthDate == currentMonthDateTuesday)
		{
			int start = 2;
			this.setLabelText(start);
		}
		else if(currentMonthDate == currentMonthDateWednesday)
		{
			int start = 3;
			this.setLabelText(start);
		}
		else if(currentMonthDate == currentMonthDateThursday)
		{
			int start = 4;
			this.setLabelText(start);
		}
		else if(currentMonthDate == currentMonthDateFriday)
		{
			int start = 5;
			this.setLabelText(start);
		}
		else if(currentMonthDate == currentMonthDateSaturday)
		{
			int start = 6;
			this.setLabelText(start);
		}
	}
	
	public void setLabelText(int start)
	{
		for(int i=0;i<start;i++)
		{
			calendarMaterial.get(i).setText("");
		}
		int day = 1;
		if(stringCurrentMonth.equals(JANUARY) 
		|| stringCurrentMonth.equals(MARCH) 
		|| stringCurrentMonth.equals(MAY)
		|| stringCurrentMonth.equals(JULY)
		|| stringCurrentMonth.equals(AUGUST)
		|| stringCurrentMonth.equals(OCTOBER)
		|| stringCurrentMonth.equals(DECEMBER))
		{
			for(int i=start;i<31+start;i++)
			{
				calendarMaterial.get(i).setText(String.valueOf(day));
				day++;
			}
			for(int i=31+start;i<calendarMaterial.size();i++)
			{
				calendarMaterial.get(i).setText(" ");
			}
		}
		else if(stringCurrentMonth.equals(APRIL)
		|| stringCurrentMonth.equals(JUNE)
		|| stringCurrentMonth.equals(SEPTEMBER)
		|| stringCurrentMonth.equals(NOVEMBER))
		{
			for(int i=start;i<30+start;i++)
			{
				calendarMaterial.get(i).setText(String.valueOf(day));
				day++;
			}
			for(int i=30+start;i<calendarMaterial.size();i++)
			{
				calendarMaterial.get(i).setText(" ");
			}
		}
		else if(stringCurrentMonth.equals(FEBRUARY) && Integer.parseInt(currentYear) % 4 != 0)
		{
			for(int i=start;i<28+start;i++)
			{
				calendarMaterial.get(i).setText(String.valueOf(day));
				day++;
			}
			for(int i=28+start;i<calendarMaterial.size();i++)
			{
				calendarMaterial.get(i).setText(" ");
			}
		}
		else
		{
			for(int i=start;i<29+start;i++)
			{
				calendarMaterial.get(i).setText(String.valueOf(day));
				day++;
			}
			for(int i=29;i<calendarMaterial.size();i++)
			{
				calendarMaterial.get(i).setText(" ");
			}
		}
	}
	
	public void actionPerformed (ActionEvent e)
	{
		if(e.getSource() == newEvent)
		{
			newEventUI = new NewEventUI(userID, events, calendar);
		}
		if(e.getSource() == optimalEvents)
		{
			try
			{
				optimize = new Optimize(calendar.checkEvents(userID));
			}
			catch(Exception f)
			{
				System.out.println("Something went wrong when trying to retrieve events");
				f.printStackTrace();
			}
		}
		if(e.getSource() == addMonth)
		{
			int temp = Integer.parseInt(stringCurrentMonth) + 1;
			if(temp > 12)
			{
				temp = 1;
				int yearTemp = Integer.parseInt(currentYear) + 1;
				currentYear = String.valueOf(yearTemp);
				year.setText(currentYear);
			}
			stringCurrentMonth = String.valueOf(temp);
			this.changeMonth();
			
			previousMonthDate = currentMonthDate;
			previousMonthDateSunday = currentMonthDateSunday;
			previousMonthDateMonday = currentMonthDateMonday;
			previousMonthDateTuesday = currentMonthDateTuesday;
			previousMonthDateWednesday = currentMonthDateWednesday;
			previousMonthDateThursday = currentMonthDateThursday;
			previousMonthDateFriday = currentMonthDateFriday;
			previousMonthDateSaturday = currentMonthDateSaturday;
			
			currentMonthDate = nextMonthDate;
			currentMonthDateSunday = nextMonthDateSunday;
			currentMonthDateMonday = nextMonthDateMonday;
			currentMonthDateTuesday = nextMonthDateTuesday;
			currentMonthDateWednesday = nextMonthDateWednesday;
			currentMonthDateThursday = nextMonthDateThursday;
			currentMonthDateFriday = nextMonthDateFriday;
			currentMonthDateSaturday = nextMonthDateSaturday;
			
			this.changeDays();
			
			if(Integer.parseInt(stringCurrentMonth) + 1 > 12)
			{
				nextMonthDate = LocalDate.of(Integer.parseInt(currentYear) + 1, 1, 1);
			}
			else
			{
				nextMonthDate = LocalDate.of(Integer.parseInt(currentYear), Integer.parseInt(stringCurrentMonth) + 1, 1);
			}
			nextMonthDateSunday = nextMonthDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.SUNDAY));
			nextMonthDateMonday = nextMonthDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
			nextMonthDateTuesday = nextMonthDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.TUESDAY));
			nextMonthDateWednesday = nextMonthDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.WEDNESDAY));
			nextMonthDateThursday = nextMonthDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.THURSDAY));
			nextMonthDateFriday = nextMonthDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.FRIDAY));
			nextMonthDateSaturday = nextMonthDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.SATURDAY));

		}
		else if(e.getSource() == subtractMonth)
		{
			int temp = Integer.parseInt(stringCurrentMonth) - 1;
			if(temp < 1)
			{
				temp = 12;
				int yearTemp = Integer.parseInt(currentYear) - 1;
				currentYear = String.valueOf(yearTemp);
				year.setText(currentYear);
			}
			stringCurrentMonth = String.valueOf(temp);
			this.changeMonth();
			
			nextMonthDate = currentMonthDate;
			nextMonthDateMonday = currentMonthDateMonday;
			nextMonthDateTuesday = currentMonthDateTuesday;
			nextMonthDateWednesday = currentMonthDateWednesday;
			nextMonthDateThursday = currentMonthDateThursday;
			nextMonthDateFriday = currentMonthDateFriday;
			nextMonthDateSaturday = currentMonthDateSaturday;
			
			currentMonthDate = previousMonthDate;
			currentMonthDateMonday = previousMonthDateMonday;
			currentMonthDateTuesday = previousMonthDateTuesday;
			currentMonthDateWednesday = previousMonthDateWednesday;
			currentMonthDateThursday = previousMonthDateThursday;
			currentMonthDateFriday = previousMonthDateFriday;
			currentMonthDateSaturday = previousMonthDateSaturday;
			
			this.changeDays();
			
			if(Integer.parseInt(stringCurrentMonth) - 1 < 1)
			{
				previousMonthDate = LocalDate.of(Integer.parseInt(currentYear) - 1, 12, 1);
			}
			else
			{
				previousMonthDate = LocalDate.of(Integer.parseInt(currentYear), Integer.parseInt(stringCurrentMonth) - 1, 1);
			}
			previousMonthDateSunday = previousMonthDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.SUNDAY));
			previousMonthDateMonday = previousMonthDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
			previousMonthDateTuesday = previousMonthDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.TUESDAY));
			previousMonthDateWednesday = previousMonthDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.WEDNESDAY));
			previousMonthDateThursday = previousMonthDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.THURSDAY));
			previousMonthDateFriday = previousMonthDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.FRIDAY));
			previousMonthDateSaturday = previousMonthDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.SATURDAY));
		}
		else if(e.getSource() == randomCost)
		{
			calendar.randomizeCost(userID);
		}
	}
}
