package org.jsf.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.faces.model.SelectItem;

import io.tea.Student;

public class DatabaseOperation {
	private static Connection connection;
	private static Statement statement;
	private static PreparedStatement prepare;
	private static ResultSet resultSet;
	
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");   // load the connector class
			String dbUrl = "jdbc:mysql://localhost:3306/sinhvien";
			String dbUsername = "root";
			String dbPassword = "anhthe939**";
			connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		}
		catch  (Exception ex) {
			ex.printStackTrace();
		}
		return connection;
	}
	
	public static ArrayList<SelectItem> selectAllClasses() {
		ArrayList<SelectItem> allClasses = new ArrayList<SelectItem>();
		try {
			statement = getConnection().createStatement();
			resultSet = statement.executeQuery("SELECT malop, tenlop FROM lophoc;");
			while (resultSet.next()) {
				SelectItem item = new SelectItem(resultSet.getString("malop"),
								resultSet.getString("tenlop"));
				allClasses.add(item);
			}
			connection.close();
			System.out.println("***" + allClasses.size() + " classes");    // ugly test
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return allClasses;
	}
	
	public static ArrayList<Student> selectAllStudents() {
		ArrayList<Student> students = new ArrayList<Student>();
		try {
			statement = getConnection().createStatement();
			String selectQuery = "SELECT mssv, ho, ten, miengiam.malop, tenlop, mondanghoc, diemdanghoc, ngay "
					+ "FROM miengiam JOIN lophoc ON (miengiam.malop=lophoc.malop);";
			resultSet = statement.executeQuery(selectQuery);
			while (resultSet.next()) {
				Student student = new Student();
				student.setStudentId(resultSet.getString("mssv"));
				student.setFirstName(resultSet.getString("ho"));
				student.setLastName(resultSet.getString("ten"));
				student.setClassId(resultSet.getString("malop"));
				student.setStudyClass(resultSet.getString("tenlop"));
				student.setSubject(resultSet.getString("mondanghoc"));
				student.setMark(resultSet.getDouble("diemdanghoc"));
				// since resultSet.getDate returns a java.sql.Date object
				// we have to use getTimestamp
				Timestamp timestamp = resultSet.getTimestamp("ngay");
				student.setCheckDate(new java.util.Date(timestamp.getTime()));
				System.out.println(student.getCheckDate());
				students.add(student);
			}
			connection.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return students;
	}
	
	public static void deleteStudent(String studentId) {
		try {
			prepare = getConnection().prepareStatement("DELETE FROM miengiam WHERE mssv=?");
			prepare.setString(1, studentId);
			prepare.executeUpdate();
			connection.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static int insertStudent(Student student) {
		int updateCount = 0;
		try {
			prepare = getConnection().prepareStatement("INSERT INTO miengiam" +
                            "(mssv, ho, ten, malop, mondanghoc, diemdanghoc, ngay) VALUES (?, ?, ?, ?, ?, ?, ?)");
			prepare.setString(1, student.getStudentId());
			prepare.setString(2, student.getFirstName());
			prepare.setString(3, student.getLastName());
			prepare.setString(4, student.getClassId());
			prepare.setString(5, student.getSubject());
			prepare.setDouble(6, student.getMark());
			// convert java.util.Date object to MySql Date string
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateString = sdf.format(student.getCheckDate());
			System.out.println(dateString);
			prepare.setString(7, dateString);
			updateCount = prepare.executeUpdate();
			connection.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return updateCount;
	}
	
	
	// We run this as a unit test
	public static void main(String[] args) {
		ArrayList<Student> allStudents = selectAllStudents();
		System.out.println(allStudents.size());
	}
}
