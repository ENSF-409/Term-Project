package controller;

import model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;

// Talk to server through sockets (sends command first then object/string)
// TODO Documentation

public class ComController {
	
	private Socket socket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	//private ObjectOutputStream objectOutputStream;

	public ComController() {
		try {
			socket = new Socket("localhost", 9090);
			socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream())); // assuming client only receives string messages from server over socket
			//objectOutputStream = new ObjectOutputStream (socket.getOutputStream()); // client sends serialized student object to server
			socketOut = new PrintWriter (socket.getOutputStream(), true); // client sends strings to server
		} catch (IOException e) { 
			e.printStackTrace();
		}catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public String searchCourse(String courseName, String courseNum) {
		socketOut.println("1"); // switch case 1: Search for course in course catalog
		if (verifyReceivedCommand(receiveMessageFromServer())) { // check if command was received by server
			socketOut.println(courseName + " " + courseNum);
			return receiveMessageFromServer(); // either returns course information or message saying that course was not found 
		}else
			return "Error!"; // server couldn't receive/perform command
	}
	
	public String addCourse(String studentName, int studentId, String courseName, String courseNum, String secNum) {
		socketOut.println("2");  // switch case 2: add course to student courses
		if (verifyReceivedCommand(receiveMessageFromServer())) { // check if command was received by server
			socketOut.println(studentName + " " + studentId + " " + courseName + " " + courseNum + " " + secNum);
			return receiveMessageFromServer(); // return message of whether course was added successfully or not
		}
		
		return "Error!"; // server couldn't receive/perform command
	}
	
		public String removeCourse(String studentName, int studentId, String courseName, String courseNum, String secNum) {
		socketOut.println("3");  // switch case 3: remove course from student courses
		if (verifyReceivedCommand(receiveMessageFromServer())) { // check if command was received by server
			socketOut.println(studentName + " " + studentId + " " + courseName + " " + courseNum + " " + secNum);
			return receiveMessageFromServer(); // return message of whether course was removed successfully or not
		}
		return "Error!"; // server couldn't receive/perform command
	}
	
	public String getCourseCatalogue() {
		socketOut.println("4"); // switch case 4: View all courses (i.e. get course catalog from server)
		String courses = receiveMultipleLineMessageFromServer(); // receive multiple message from server
		if (!courses.isBlank())  // check if command was received by server
			return courses;
		else
			return "Error!"; // Server could not send course catalog through socket
	}
	
	public String getStudentCourses(Student theStudent) {
		socketOut.println("5"); // switch case 4: View all student courses (i.e. registration list of student object sent as string)
		if (verifyReceivedCommand(receiveMessageFromServer())) { // check if command was received by server
		//	try {
				//objectOutputStream.writeObject(theStudent); // send serialized object of Student through socket
				socketOut.println(theStudent.getStudentName() + " " + theStudent.getStudentId());
				return receiveMessageFromServer(); // returns string of student's courses
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}
		return "Error!"; // server couldn't receive/perform command
	}
	
	public String searchStudent(Student theStudent) {
		socketOut.println("6"); // switch case 6 (new case to be added): Search for student in student list
		
		if (verifyReceivedCommand(receiveMessageFromServer())) { // check if command was received by server
//			try {
				//objectOutputStream.writeUnshared(theStudent); // send serialized object of Student through socket
				socketOut.println(theStudent.getStudentName() + " " + theStudent.getStudentId());
				return receiveMessageFromServer(); // returns string message of either "valid" or "invalid"
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}
		return "Error!"; // server couldn't receive/perform command
	}

	// Receives String from server through socket
	public String receiveMessageFromServer() {
		
		String messageFromServer = " ";		
		
		try {
			messageFromServer = socketIn.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return messageFromServer;
	}

	// Receives multiline String from server through socket
	public String receiveMultipleLineMessageFromServer() {
		String line;
		String messageFromServer = " ";	
		try {
			while((line = socketIn.readLine()) != null) {
				messageFromServer += line + "\n";
				if(line.contains("\0")) {
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return messageFromServer;
	}
	
	private boolean verifyReceivedCommand(String recieved) {
		if (recieved.equalsIgnoreCase("valid"))
			return true;
		else
			return false;
	}
}
