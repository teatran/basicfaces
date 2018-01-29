package io.tea;

import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;


@ManagedBean(name="form")
@SessionScoped
public class RegisterFrom implements Serializable {

	private static final long serialVersionUID = -815388089726819976L;

	public enum Education { HIGH_SCHOOL, BACHELOR, MASTER, DOCTOR };
	
	// ===========================================================================
	// value - in select component
	// Here we use instance fields since each session get a new instance of bean.
	// ===========================================================================
	
	private String email;
	private boolean contactMe;
	private int[] bestDaysToContact;
	private Integer yearOfBirth;
	private String[] colors;
	private Set<String> languages = new TreeSet<String>();
	private Education education = Education.BACHELOR;
	
	public String getEmail() { return email; }
	public void setEmail(String newValue) { email = newValue; }
	
	public boolean getContactMe() { return contactMe; }
	public void setContactMe(boolean newValue) { contactMe = newValue; }
	
	public int[] getBestDaysToContact() { return bestDaysToContact; }
	public void setBestDaysToContact(int[] newValue) { bestDaysToContact = newValue; }
	
	public Integer getYearOfBirth() { return yearOfBirth; }
	public void setYearOfBirth(Integer newValue) { yearOfBirth = newValue; }
	
	public String[] getColors() { return colors; }
	public void setColors(String[] newValue) { colors = newValue; }
	
	public String getColorsConcat() {
		StringBuilder result = new StringBuilder();
		for (String color : colors) {
			int index = Integer.parseInt(color);
			result.append(colorItems[index].getLabel() + ", ");
		}
		return result.toString();
	}
	
	public Set<String> getLanguages() { return languages; }
	public void setLanguages(Set<String> newValue) { languages = newValue; }
	
	public Education getEducation() { return education; }
	public void setEducation(Education newValue) { education = newValue; }
	
	// =========================================================================================
	// items - in select component
	// Here we use static fields since they are application-wide and independent from sessions.
	// =========================================================================================
	
	public static class Weekday {
		private int dayOfWeek;
		
		public Weekday(int dow) {
			dayOfWeek = dow;
		}
		
		public String getDayName() {
			DateFormatSymbols symbols = new DateFormatSymbols();
			String[] weekdays = symbols.getWeekdays();
			return weekdays[dayOfWeek];
		}
		
		public int getDayNumber() {
			return dayOfWeek;
		}
	}
	
	public Collection<SelectItem> getYearItems() { return yearItems; }
	
	public Weekday[] getDaysOfTheWeek() { return daysOfTheWeek; }
	
	public SelectItem[] getLanguageItems() { return languageItems; }
	
	public SelectItem[] getColorItems() { return colorItems; }
	
	public Map<String, Education> getEducationItems() { return educationItems; }
	
	private static Collection<SelectItem> yearItems;
	static {
		yearItems = new ArrayList<SelectItem>();
		// The first item is the "no selection" item
		yearItems.add(new SelectItem(null, "Choose a year", "No selection here", false, false, true));
		for (int i = 1960; i < 2020; i++)
			yearItems.add(new SelectItem(i));
	}
	
	private static Weekday[] daysOfTheWeek;
	static {
		daysOfTheWeek = new Weekday[7];
		for (int i = Calendar.SUNDAY; i <= Calendar.SATURDAY; i++) {
			daysOfTheWeek[i - Calendar.SUNDAY] = new Weekday(i);
		}
	}
	
	private static SelectItem[] languageItems = {
		new SelectItem("English"),
		new SelectItem("French"),
		new SelectItem("Japanese"),
		new SelectItem("Vietnamese"),
		new SelectItem("Italian")	
	};
	
	private static SelectItem[] colorItems = {
		new SelectItem("1", "Blue"),
		new SelectItem("2", "Black"),
		new SelectItem("3", "White"),
		new SelectItem("4", "Gray"),
		new SelectItem("5", "Cyan")
	};
	
	private static Map<String, Education> educationItems;
	static {
		educationItems = new LinkedHashMap<String, Education>();
		educationItems.put("High School", Education.HIGH_SCHOOL);
		educationItems.put("BACHELOR", Education.BACHELOR);
		educationItems.put("MASTER", Education.MASTER);
		educationItems.put("DOCTORATE", Education.DOCTOR);
	}
}
