package controller;
import model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class BackEnd implements Runnable{
	
	private RegistrationApp regApp;
	private Socket aSocket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;
	
	public BackEnd(Socket s) {
		regApp = new RegistrationApp(); //if you follow the trail, loads from database for each new instance of backend
		aSocket = s;
		
		try {
			socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			socketOut = new PrintWriter(aSocket.getOutputStream());
			objectInputStream = new ObjectInputStream(aSocket.getInputStream());
			objectOutputStream = new ObjectOutputStream(aSocket.getOutputStream()); 
		}catch(IOException e) {
			System.out.println(e);
		}
	}
	
	// public void printMenuChoices() {
		// System.out.println("Please choose from one of the following options: ");
		// System.out.println("1. Search catalogue courses.");
		// System.out.println("2. Add course to student courses.");
		// System.out.println("3. Remove course from student courses.");
		// System.out.println("4. View all courses in catalogue");
		// System.out.println("5. View all courses taken by student.");
		// System.out.println("6. Quit.");
		// System.out.println("\nPlease enter your selection: ");
	// }
	
	public void menu() {
		
		Student theStudent = null;
		
		while (true) {
			String choiceFromServer = readFromSocket();
			if(choiceFromServer.compareTo( "4" ) != 0 && choiceFromServer != null) sendString("valid");
			
			if(choiceFromServer.compareTo( "5" ) == 0 || choiceFromServer.compareTo( "6" ) == 0 ){//if choice 5 or 6 are chosen deserialize Student object
				try {
					theStudent = (Student)objectInputStream.readUnshared();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			switch (choiceFromServer) {
			case "1":
				searchCatCourses(); // I commented out st += listAllStudents(); in toString() of CourseOffering.java
				break;
			case "2":
				addCourse(); // This creates a new Student object with the parameters passed in through the socket
				break;
			case "3":
				removeStudentCourse(); // Removes courses from previously created Student object from case "2"
				break;
			case "4":
				listCatCourses(); // Lists all courses, doesn't list students in courses see comment in case "1"
				break;
			case "5":
				listStudentCourses(theStudent); // Talk to Niyousha: theStudent created newly in line 58 of GUIController is different
				break;		      // from the theStudent created by case "2", even if they have the same name, adding a course
						      // to one of them does not add a course to the other, same goes for removing a course
						      // A solution to this is, using the info from the loginFrame.getName() ...etc., to initially 
						      // have theStudent to be only constructed on the server side and then serialized and sent to the client side
			case "6":
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
		sendString(regApp.searchCatCourses(name, num));	
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
		
		//Student sendStudent = regApp.addCourse(studentName, studentId, courseName, courseNum, secNum);
		//objectOutputStream.writeUnshared(sendStudent); //WHICH MEANS Niyousha may have to 
		//CHANGE LINE 50 OF ComController to return (Student)objectInputStream.readUnshared();
		//CHANGE LINE 173 OF GUIController to return type of Student
		//CHANGE LINE 123 OF GUIController to type of Student
		
		sendString(regApp.addCourse(studentName, studentId, courseName, courseNum, secNum));//Remove, see comment on line 69
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
		sendString(regApp.listAllCourses());
	}
	
	// case "5"
	private void listStudentCourses(Student theStudent) {
		regApp.listStudentCourses(theStudent.getStudentName(), theStudent.getStudentId());
	}
	
	// case "6"
	private void searchStudent(){
		int checker = 0;
		for(Student s: regApp.getDataBase().sendStudentList()){// Using the database field of regApp to search for student
			if(theStudent.equals(s)){
				sendString("valid");
				checker++;
				break;
			}
		}
		if(checker == 0) sendString("invalid");
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
	
	
	public void sendString(String toSend) {
		socketOut.println(toSend);
		socketOut.flush();
	}
	
	@Override
	public void run() {
		try {
			menu();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main (String [] args) {
		FrontEnd end = new FrontEnd();
		end.menu();
	}
}
