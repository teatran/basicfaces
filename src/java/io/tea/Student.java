package io.tea;

import java.util.Date;
import java.util.ArrayList;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

import org.jsf.db.DatabaseOperation;


@ManagedBean
@RequestScoped
public class Student implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String studentId; 
	private String firstName;
	private String lastName;
	private String classId;
	private String studyClass;
	private String subject;
	private Double mark;
	private Date checkDate;
	
	private static ArrayList<SelectItem> classList;
	
	public ArrayList<SelectItem> getClassList() { 
		classList = DatabaseOperation.selectAllClasses();
		return classList; 
	}
	
	public String getStudentId() { return studentId; }
	public void setStudentId(String newValue) { studentId = newValue; }
	
	public String getFirstName() { return firstName; }
	public void setFirstName(String newValue) { firstName = newValue; }
	
	public String getLastName() { return lastName; }
	public void setLastName(String newValue) { lastName = newValue; }
	
	public String getClassId() { return classId; }
	public void setClassId(String newValue) { classId = newValue; }
	
	public String getStudyClass() { return studyClass; }
	public void setStudyClass(String newValue) { studyClass = newValue; }
	
	public String getSubject() { return subject; }
	public void setSubject(String newValue) { subject = newValue; }
	
	public Double getMark() { return mark; }
	public void setMark(Double newValue) { mark = newValue; }
	
	public Date getCheckDate() { return checkDate; }
	public void setCheckDate(Date newValue) { checkDate = newValue; }
	
	public String addToDatabase() {
		int updateCount = DatabaseOperation.insertStudent(this);
		if (updateCount == 0)
			return "create?faces-redirect=true";
		else
			return "all?faces-redirect=true";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
