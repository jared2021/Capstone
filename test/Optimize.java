package test;
import java.util.ArrayList;

public class Optimize {

	private ArrayList<Event> events;
	
	private ArrayList<Integer> startTimeHour = new ArrayList<Integer>();
	private ArrayList<Integer> startTimeMinute = new ArrayList<Integer>();
	
	private ArrayList<Integer> endTimeHour = new ArrayList<Integer>();
	private ArrayList<Integer> endTimeMinute = new ArrayList<Integer>();
	
	private ArrayList<Integer> isCompatible = new ArrayList<Integer>();
	private ArrayList<Integer> trueCompatible = new ArrayList<Integer>();
	
	private final int militaryTime = 12;
	
	int cost = 0;
	int potentialCost = 0;
	
	public Optimize (ArrayList<Event> events)
	{
		this.events = events;
		this.getEndTime();
	}
	
	public void getEndTime()
	{
		for(int i=0; i<events.size(); i++)
		{
			if(events.get(i).getEndTimePeriod().equals("PM") && !events.get(i).getEndTimeHour().replaceAll("\\s", "").equals("12"))
			{
				String removeSpaces = events.get(i).getEndTimeHour().replaceAll("\\s", "");
				int temp = Integer.parseInt(removeSpaces);
				endTimeHour.add(temp + militaryTime);
				
				removeSpaces = events.get(i).getEndTimeMinute().replaceAll("\\s", "");
				temp = Integer.parseInt(removeSpaces);
				endTimeMinute.add(temp);
			}
			else if(events.get(i).getEndTimePeriod().equals("AM") && events.get(i).getEndTimeHour().replaceAll("\\s", "").equals("12"))
			{
				endTimeHour.add(0);
				String removeSpaces = events.get(i).getEndTimeMinute().replaceAll("\\s", "");
				int temp = Integer.parseInt(removeSpaces);
				endTimeMinute.add(temp);
			}
			else
			{
				String removeSpaces = events.get(i).getEndTimeHour().replaceAll("\\s", "");
				int temp = Integer.parseInt(removeSpaces);
				endTimeHour.add(temp);
				
				removeSpaces = events.get(i).getEndTimeMinute().replaceAll("\\s", "");
				temp = Integer.parseInt(removeSpaces);
				endTimeMinute.add(temp);
			}
		}
		this.getStartTime();
		
	}
	
	public void getStartTime()
	{
		for(int i =0;i<events.size();i++)
		{
			if(events.get(i).getStartTimePeriod().equals("PM") && !events.get(i).getStartTimeHour().replaceAll("\\s", "").equals("12"))
			{
				String removeSpaces = events.get(i).getStartTimeHour().replaceAll("\\s", "");
				int temp = Integer.parseInt(removeSpaces);
				startTimeHour.add(temp + militaryTime);
				
				removeSpaces = events.get(i).getStartTimeMinute().replaceAll("\\s", "");
				temp = Integer.parseInt(removeSpaces);
				startTimeMinute.add(temp);
			}
			else if(events.get(i).getStartTimePeriod().equals("AM") && events.get(i).getStartTimeHour().replaceAll("\\s", "").equals("12"))
			{
				startTimeHour.add(0);
				String removeSpaces = events.get(i).getStartTimeMinute().replaceAll("\\s", "");
				int temp = Integer.parseInt(removeSpaces);
				startTimeMinute.add(temp);
			}
			else
			{
				String removeSpaces = events.get(i).getStartTimeHour().replaceAll("\\s", "");
				int temp = Integer.parseInt(removeSpaces);
				startTimeHour.add(temp);
				
				removeSpaces = events.get(i).getStartTimeMinute().replaceAll("\\s","");
				temp = Integer.parseInt(removeSpaces);
				startTimeMinute.add(temp);
			}
		}
		this.organizeEvents();
		this.organizeEventsCost();
	}
	
	public void organizeEvents()
	{	
		//sorts the events by their end times, earliest to latest
		for(int i=0; i<events.size(); i++)
		{
			for(int j=i+1; j<events.size(); j++)
			{	
				if(endTimeHour.get(i) > endTimeHour.get(j) && events.get(i).getMonth() == events.get(j).getMonth() && events.get(i).getDay() == events.get(j).getDay() && events.get(i).getYear() == events.get(j).getYear()
				|| endTimeHour.get(i) == endTimeHour.get(j) && endTimeMinute.get(i) > endTimeMinute.get(j) && events.get(i).getMonth() == events.get(j).getMonth() && events.get(i).getDay() == events.get(j).getDay() && events.get(i).getYear() == events.get(j).getYear()
				|| events.get(i).getMonth() > events.get(j).getMonth() && events.get(i).getYear() == events.get(j).getYear()
				|| events.get(i).getDay() > events.get(j).getDay() && events.get(i).getMonth() == events.get(j).getMonth() && events.get(i).getYear() == events.get(j).getYear()
				|| events.get(i).getYear() > events.get(j).getYear())
				{
					Event tempEvent = events.get(i);
					events.set(i, events.get(j));
					events.set(j, tempEvent);
					
					int temp = endTimeHour.get(i);
					endTimeHour.set(i, endTimeHour.get(j));
					endTimeHour.set(j, temp);
					
					temp = endTimeMinute.get(i);
					endTimeMinute.set(i, endTimeMinute.get(j));
					endTimeMinute.set(j, temp);
					
					temp = startTimeHour.get(i);
					startTimeHour.set(i, startTimeHour.get(j));
					startTimeHour.set(j, temp);
					
					temp = startTimeMinute.get(i);
					startTimeMinute.set(i, startTimeMinute.get(j));
					startTimeMinute.set(j, temp);
				}
			}
		}
		
		System.out.println("The order of events from earliest end time to latest end time is shown below.");
		for(int i =0; i<events.size();i++)
		{
			System.out.println(events.get(i).getMonth() + " " + events.get(i).getDay() + " " + events.get(i).getYear());
			System.out.println(events.get(i).getStartTimeHour() + ":" + events.get(i).getStartTimeMinute() + " " +events.get(i).getStartTimePeriod());
			System.out.println(events.get(i).getEndTimeHour() + ":" + events.get(i).getEndTimeMinute() + " " +events.get(i).getEndTimePeriod());
			System.out.println(events.get(i).getCost());
			System.out.println("------------------------------------");
		}
		System.out.println("-----------------------------------------------");
		System.out.println("-----------------------------------------------");
		
		this.optimize();
		System.out.println("The total cost for normal optimization is " + cost + " which is equal to");
		for(int i = 0;i<trueCompatible.size();i++)
		{
			System.out.println(events.get(trueCompatible.get(i)).getMonth() + " " + events.get(trueCompatible.get(i)).getDay() + " " + events.get(trueCompatible.get(i)).getYear());
			System.out.println(events.get(trueCompatible.get(i)).getStartTimeHour() + ":" + events.get(trueCompatible.get(i)).getStartTimeMinute() + " " + events.get(trueCompatible.get(i)).getStartTimePeriod());
			System.out.println(events.get(trueCompatible.get(i)).getEndTimeHour() + ":" + events.get(trueCompatible.get(i)).getEndTimeMinute() + " " + events.get(trueCompatible.get(i)).getEndTimePeriod());
			System.out.println(events.get(trueCompatible.get(i)).getCost());
			System.out.println("-------------------------------------------");
		}
		System.out.println("-----------------------------------------------");
		System.out.println("-----------------------------------------------");
		trueCompatible.clear();

		this.optimizeEqualCost();
		System.out.println("The total cost for same cost optimization is " + trueCompatible.size() + " which is equal to");
		for(int i = 0;i<trueCompatible.size();i++)
		{
			System.out.println(events.get(trueCompatible.get(i)).getMonth() + " " + events.get(trueCompatible.get(i)).getDay() + " " + events.get(trueCompatible.get(i)).getYear());
			System.out.println(events.get(trueCompatible.get(i)).getStartTimeHour() + ":" + events.get(trueCompatible.get(i)).getStartTimeMinute() + " " + events.get(trueCompatible.get(i)).getStartTimePeriod());
			System.out.println(events.get(trueCompatible.get(i)).getEndTimeHour() + ":" + events.get(trueCompatible.get(i)).getEndTimeMinute() + " " + events.get(trueCompatible.get(i)).getEndTimePeriod());
			System.out.println(events.get(trueCompatible.get(i)).getCost());
			System.out.println("-------------------------------------------");
		}
		System.out.println("-----------------------------------------------");
		System.out.println("-----------------------------------------------");
		trueCompatible.clear();
		
		this.optimizeMostEvents();
		System.out.println("The total cost for most events optimization is " + cost + " which is equal to");
		for(int i = 0;i<trueCompatible.size();i++)
		{
			System.out.println(events.get(trueCompatible.get(i)).getMonth() + " " + events.get(trueCompatible.get(i)).getDay() + " " + events.get(trueCompatible.get(i)).getYear());
			System.out.println(events.get(trueCompatible.get(i)).getStartTimeHour() + ":" + events.get(trueCompatible.get(i)).getStartTimeMinute() + " " + events.get(trueCompatible.get(i)).getStartTimePeriod());
			System.out.println(events.get(trueCompatible.get(i)).getEndTimeHour() + ":" + events.get(trueCompatible.get(i)).getEndTimeMinute() + " " + events.get(trueCompatible.get(i)).getEndTimePeriod());
			System.out.println(events.get(trueCompatible.get(i)).getCost());
			System.out.println("-------------------------------------------");
		}
		System.out.println("-----------------------------------------------");
		System.out.println("-----------------------------------------------");
	}
	
	public ArrayList<Integer> compatibleList(int i)
	{
		int position = i - 1;
		i = position - 1;
		while(i>=0)
		{
			if(events.get(position).getMonth() != events.get(i).getMonth()
			|| events.get(position).getDay() != events.get(i).getDay()
			|| events.get(position).getYear() != events.get(i).getYear())
			{
				isCompatible.add(i);
				position = i;
				i = position - 1;
			}
			else if(startTimeHour.get(position) > endTimeHour.get(i)
			|| startTimeHour.get(position) == endTimeHour.get(i) && startTimeMinute.get(position) > endTimeMinute.get(i)
			|| startTimeHour.get(position) == endTimeHour.get(i) && startTimeMinute.get(position) == endTimeMinute.get(i))
			{
				isCompatible.add(i);
				position = i;
				i=position - 1;
			}
			else
			{
				i--;
			}
		}
		return isCompatible;
	}
	
	public void compatibleCost(int i)
	{
		int position = i - 1;
		isCompatible.add(position);
		position = position - 1;
		boolean compatible = true;
		while(position>=0)
		{
			for(int j = 0;j<isCompatible.size();j++)
			{
				if(startTimeHour.get(position) >= startTimeHour.get(isCompatible.get(j))
				&& startTimeHour.get(position) < endTimeHour.get(isCompatible.get(j))
				&& events.get(position).getMonth() == events.get(isCompatible.get(j)).getMonth()
				&& events.get(position).getDay() == events.get(isCompatible.get(j)).getDay()
				&& events.get(position).getYear() == events.get(isCompatible.get(j)).getYear())
				{
					compatible = false;
				}
				else if(endTimeHour.get(position) >= startTimeHour.get(isCompatible.get(j))
				&& endTimeHour.get(position) < endTimeHour.get(isCompatible.get(j))
				&& events.get(position).getMonth() == events.get(isCompatible.get(j)).getMonth()
				&& events.get(position).getDay() == events.get(isCompatible.get(j)).getDay()
				&& events.get(position).getYear() == events.get(isCompatible.get(j)).getYear())
				{
					compatible = false;
				}
				else if(startTimeHour.get(position) <= startTimeHour.get(isCompatible.get(j))
				&& endTimeHour.get(position) >= endTimeHour.get(isCompatible.get(j))
				&& events.get(position).getMonth() == events.get(isCompatible.get(j)).getMonth()
				&& events.get(position).getDay() == events.get(isCompatible.get(j)).getDay()
				&& events.get(position).getYear() == events.get(isCompatible.get(j)).getYear())
				{
					compatible = false;
				}
			}
			if(compatible)
			{
				isCompatible.add(position);
			}
			compatible = true;
			position--;
		}
	}
	
	public ArrayList<Integer> optimize()
	{
		for(int i = 1; i<events.size() + 1; i++)
		{
			this.compatibleList(i);
			for(int j = 0;j<isCompatible.size(); j++)
			{
				potentialCost = potentialCost + events.get(isCompatible.get(j)).getCost();
			}
			potentialCost = potentialCost + events.get(i - 1).getCost();
			if(cost < potentialCost)
			{
				cost = potentialCost;
				trueCompatible.clear();
				for(int k=0; k<isCompatible.size();k++)
				{
					trueCompatible.add(isCompatible.get(k));
				}
				trueCompatible.add(i - 1);
			}
			potentialCost = 0;
			isCompatible.clear();
		}
		return trueCompatible;
	}
	
	public void optimizeEqualCost()
	{
		for (int i = 1; i<events.size() + 1; i++)
		{
			this.compatibleList(i);
			if(trueCompatible.size() <= isCompatible.size())
			{
				trueCompatible.clear();
				for(int j=0; j<isCompatible.size();j++)
				{
					trueCompatible.add(isCompatible.get(j));
				}
				trueCompatible.add(i - 1);
			}
			isCompatible.clear();
		}
	}
	
	//optimize through taking highest cost events
	
	public void organizeEventsCost()
	{
		for(int i=0;i<events.size();i++)
		{
			for(int j=i+1;j<events.size();j++)
			{
				if(events.get(i).getCost() > events.get(j).getCost())
				{
					Event tempEvent = events.get(i);
					events.set(i, events.get(j));
					events.set(j, tempEvent);
					
					int temp = endTimeHour.get(i);
					endTimeHour.set(i, endTimeHour.get(j));
					endTimeHour.set(j, temp);
					
					temp = endTimeMinute.get(i);
					endTimeMinute.set(i, endTimeMinute.get(j));
					endTimeMinute.set(j, temp);
					
					temp = startTimeHour.get(i);
					startTimeHour.set(i, startTimeHour.get(j));
					startTimeHour.set(j, temp);
					
					temp = startTimeMinute.get(i);
					startTimeMinute.set(i, startTimeMinute.get(j));
					startTimeMinute.set(j, temp);
				}
			}
			
		}
		System.out.println("The order of events from highest cost to lowest cost is shown below.");
		for(int i =0; i<events.size();i++)
		{
			System.out.println(events.get(i).getMonth() + " " + events.get(i).getDay() + " " + events.get(i).getYear());
			System.out.println(events.get(i).getStartTimeHour() + ":" + events.get(i).getStartTimeMinute() + " " +events.get(i).getStartTimePeriod() + "  " + startTimeHour.get(i));
			System.out.println(events.get(i).getEndTimeHour() + ":" + events.get(i).getEndTimeMinute() + " " +events.get(i).getEndTimePeriod() + "  " + endTimeHour.get(i));
			System.out.println(events.get(i).getCost());
			System.out.println("------------------------------------");
		}
		System.out.println("-----------------------------------------------");
		System.out.println("-----------------------------------------------");
		
		this.optimizeCost();
		System.out.println("The total cost for largest cost optimization is " + cost + " which is equal to");
		for(int i = 0;i<trueCompatible.size();i++)
		{
			System.out.println(events.get(trueCompatible.get(i)).getMonth() + " " + events.get(trueCompatible.get(i)).getDay() + " " + events.get(trueCompatible.get(i)).getYear());
			System.out.println(events.get(trueCompatible.get(i)).getStartTimeHour() + ":" + events.get(trueCompatible.get(i)).getStartTimeMinute() + " " + events.get(trueCompatible.get(i)).getStartTimePeriod());
			System.out.println(events.get(trueCompatible.get(i)).getEndTimeHour() + ":" + events.get(trueCompatible.get(i)).getEndTimeMinute() + " " + events.get(trueCompatible.get(i)).getEndTimePeriod());
			System.out.println(events.get(trueCompatible.get(i)).getCost());
			System.out.println("-------------------------------------------");
		}
		System.out.println("-----------------------------------------------");
		System.out.println("-----------------------------------------------");
	}
	
	public void optimizeCost()
	{
		for(int i=1;i<events.size() + 1 ;i++)
		{
			this.compatibleCost(i);
			for(int j=0;j<isCompatible.size();j++)
			{
				potentialCost = potentialCost + events.get(isCompatible.get(j)).getCost();
			}
			if(cost < potentialCost)
			{
				cost = potentialCost;
			}
			trueCompatible.clear();
			for(int k=0; k<isCompatible.size();k++)
			{
				trueCompatible.add(isCompatible.get(k));
			}
			potentialCost = 0;
			isCompatible.clear();
		}
	}
	
	public void optimizeMostEvents()
	{
		for(int i=1;i<events.size() + 1;i++)
		{
			this.compatibleList(i);
			for(int j = 0;j<isCompatible.size(); j++)
			{
				potentialCost = potentialCost + events.get(isCompatible.get(j)).getCost();
			}
			potentialCost = potentialCost + events.get(i - 1).getCost();
			if(isCompatible.size() >= trueCompatible.size())
			{
				cost = potentialCost;
				trueCompatible.clear();
				for(int k=0; k<isCompatible.size();k++)
				{
					trueCompatible.add(isCompatible.get(k));
				}
				trueCompatible.add(i - 1);
			}
			potentialCost = 0;
			isCompatible.clear();
		}
	}
}
