package io.tea;

import java.util.ArrayList;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.annotation.PostConstruct;

import org.jsf.db.DatabaseOperation;


@ManagedBean
@RequestScoped
public class StudentsListBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private static ArrayList<Student> allStudents;
	
	@PostConstruct   // automatically called just after bean construction
	private void init() {
		allStudents = DatabaseOperation.selectAllStudents();
		System.out.println("*** " + allStudents.size() + "students");   // ugly test 
	}
	
	public ArrayList<Student> getAllStudents() {
		return allStudents;
	}
	
	public String deleteOneStudent(String studentId) {
		DatabaseOperation.deleteStudent(studentId);
		// show list of all students again
		return "all?faces-redirect=true";
	}
	
	// We run this as a commandline test
	public static void main(String[] args) {
		StudentsListBean mybean = new StudentsListBean();
		ArrayList<Student> mylist = mybean.getAllStudents();
		System.out.println("There are " + mylist.size() + " students.");
	}
}
