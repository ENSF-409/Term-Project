package controller;

import model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class BackEnd implements Runnable{
	
	private RegistrationApp regApp;
	private Socket aSocket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	//private ObjectInputStream objectInputStream;
	//private ObjectOutputStream objectOutputStream;
	
	public BackEnd(Socket s) {
		regApp = new RegistrationApp(); //if you follow the trail, loads from database for each new instance of backend
		aSocket = s;
		
		try {
			socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			socketOut = new PrintWriter(aSocket.getOutputStream());
			//objectInputStream = new ObjectInputStream(aSocket.getInputStream());
			//objectOutputStream = new ObjectOutputStream(aSocket.getOutputStream()); 
		}catch(IOException e) {
			System.out.println(e);
		}
	}

	public void menu() {
		
		Student theStudent = null;
		
		while (true) {
			String choiceFromServer = readFromSocket();
			if(choiceFromServer.compareTo( "4" ) != 0 && choiceFromServer != null) sendString("valid");
			
//			if(choiceFromServer.compareTo( "5" ) == 0 || choiceFromServer.compareTo( "6" ) == 0 ){//if choice 5 or 6 are chosen deserialize Student object
//				try {
//					theStudent = (Student) objectInputStream.readObject();
//				} catch (ClassNotFoundException e) {
//					e.printStackTrace();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
			
			switch (choiceFromServer) {
			case "1":
				searchCatCourses(); // I commented out st += listAllStudents(); in toString() of CourseOffering.java
				break;
			case "2":
				addCourse(); //Adds course to Student object in Student arrayList in database class
				break;
			case "3":
				removeStudentCourse(); // Removes courses from Student object
				break;
			case "4":
				listCatCourses(); // Lists all courses, doesn't list students in courses see comment in case "1"
				break;
			case "5":
				//listStudentCourses(theStudent); //Note that this sends a string of courses a student is enrolled in, if the student
												//is not enrolled in any course, this sends an empty string of s = "";
				listStudentCourses();
				
				break;		      
						      
			case "6":
				//searchStudent(theStudent);
				searchStudent();
				break;
			default:
				System.out.println("\nInvalid selection. Please try again");
				break;
			}
		}
	}
	
	//case "1"
	private void searchCatCourses() {
		String str = readFromSocket();
		String searchCatCoursesParameters[] = str.split(" ");
		String name = searchCatCoursesParameters[0];
		int num = Integer.parseInt(searchCatCoursesParameters[1]);
		sendString(regApp.searchCatCourses(name, num) + "\0");	
	}
	
	//case "2"
	private void addCourse() {
		String str = readFromSocket();
		String addCourseParameters[] = str.split(" ");
		String studentName = addCourseParameters[0];
		int studentId = Integer.parseInt(addCourseParameters[1]);
		String courseName = addCourseParameters[2];
		int courseNum = Integer.parseInt(addCourseParameters[3]);
		int secNum = Integer.parseInt(addCourseParameters[4]);
		sendString(regApp.addCourse(studentName, studentId, courseName, courseNum, secNum));
	}
	
	//case "3"
	private void removeStudentCourse() {
		String str = readFromSocket();
		String removeCourseParameters[] = str.split(" ");
		String studentName = removeCourseParameters[0];
		int studentId = Integer.parseInt(removeCourseParameters[1]);
		String courseName = removeCourseParameters[2];
		int courseNum = Integer.parseInt(removeCourseParameters[3]);
		int secNum = Integer.parseInt(removeCourseParameters[4]);
		sendString(regApp.removeStudentCourse(studentName, studentId, courseName, courseNum, secNum));
	}
	
	// case "4"
	private void listCatCourses() {
		sendString(regApp.listAllCourses() + "\0");
	}
	
	// case "5"
//	private void listStudentCourses(Student theStudent) {
//		sendString(regApp.listStudentCourses(theStudent.getStudentName(), theStudent.getStudentId()));
//		//Note that this sends a string of courses a student is enrolled in, if the student
//		//is not enrolled in any course, this sends an empty string of s = "";
//	}
	
	private void listStudentCourses() {
		String str = readFromSocket();
		String student [] = str.split(" ");
		sendString(regApp.listStudentCourses(student[0], Integer.parseInt(student[1])) + "\0");
	}
	
	
	// case "6"
//	private void searchStudent(Student theStudent){
//		int checker = 0;
//		for(Student s: regApp.getDataBase().getStudentListFromDataBase()){// Using the database field of regApp to search for student
//			if(validatStudentNameandId(s, theStudent)){
//				checker++;
//				break;
//			}
//		}
//		if(checker == 0) sendString("invalid");
//		else sendString("valid");
//	}
	
	
	private void searchStudent() {
		int checker = 0;
		String str = readFromSocket();
		String student [] = str.split(" ");
		
		for (Student s: regApp.getDataBase().getStudentListFromDataBase()) {
			System.out.println("Compare with " + s.getStudentName());
			if (validateStudent(student[0], Integer.parseInt(student[1]), s)) {
				checker++;
				break;
			}
		}
		if (checker == 0)
			sendString("invalid");
		else
			sendString("valid");
	}
	
	private String readFromSocket() {
		String message = " ";
		try {
			message = socketIn.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return message;
	}
	
	private boolean validateStudent(String name, int id, Student theStudent) {
		System.out.println("Comparing " + theStudent.getStudentName() + " from database with " + name);
		if (theStudent.getStudentName().equalsIgnoreCase(name) && theStudent.getStudentId() == id)
			return true;
		else
			return false;
	}
	
	
//	private boolean validatStudentNameandId(Student s, Student theStudent) {
//		if(theStudent.getStudentName().equals(s.getStudentName()) && theStudent.getStudentId() == s.getStudentId())
//			return true;
//		else return false;
//	}
//	
	public void sendString(String toSend) {
		socketOut.println(toSend);
		socketOut.flush();
	}
	
	@Override
	public void run() {
		try {
			menu();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//public static void main (String [] args) {
		//BackEnd end = new BackEnd();
		//end.menu();
	//}


}
