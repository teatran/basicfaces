package onthi.oop;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class Gregorian
{

	public static void main(String[] args)
	{
		GregorianCalendar now = new GregorianCalendar();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH);
		int day = now.get(Calendar.DAY_OF_MONTH);
		System.out.println(day + "/" + (month+1) + "/" + year);
		
		Gregorian test = new Gregorian();
		test.demoHashMap();
		
	}
	
	public void demoHashMap()
	{
		// use HashMap
		Map<String, Employee> staff = new HashMap<>();
		staff.put("1", new Employee("Amy Lee"));
		staff.put("2", new Employee("Harry Hacker"));
		staff.put("3", new Employee("Gary Cooper"));
		staff.put("4", new Employee("test"));
		
		// print all entries
		System.out.println(staff);
		
		// remove an entry
		staff.remove("4");
		
		// replace an entry
		staff.put("3", new Employee("David Copper"));
		
		// get a value
		System.out.println(staff.get("2"));
		
		// iterate through all entries
		for (Map.Entry<String, Employee> entry : staff.entrySet()) {
			String key = entry.getKey();
			Employee value = entry.getValue();
			System.out.println("key=" + key + ", value=" + value);
		}
		
	}
	
	private class Employee 
	{
		private String name;
		
		public Employee(String aName)
		{
			name = aName;
		}
		
		@Override
		public String toString()
		{ 
			return "<Employee>"+ name + "</Employee>";
		}
	}

}
