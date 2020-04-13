package controller;

import view.*;
import model.*;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;

// TODO Documentation

public class GUIController {
	
	private LoginFrame loginFrame;
	private MainFrame mainFrame;
	private SearchCourseFrame searchCourseFrame;
	private ViewAllCoursesFrame viewAllCoursesFrame;
	private ViewStudentCoursesFrame viewStudentCoursesFrame;
	private CourseDisplayFrame courseDisplayFrame;
	private Student theStudent;
	private ComController comController;
	
	public GUIController(){
		init();
		loginFrame = new LoginFrame();
		loginFrame.setVisible(true);
		loginFrameEventHandler(loginFrame.getLoginButton());
		mainFrame = new MainFrame();
		mainFrameEventHandler(mainFrame.getViewAllCoursesButton(), mainFrame.getViewStudentCoursesButton());
	}

	private void init() {
		comController = new ComController();
		loginFrame = new LoginFrame();
		mainFrame = new MainFrame();
		searchCourseFrame = new SearchCourseFrame();
		viewAllCoursesFrame = new ViewAllCoursesFrame();
		viewStudentCoursesFrame = new ViewStudentCoursesFrame();
		courseDisplayFrame = new CourseDisplayFrame();
	}
	
	private void setVisibleFrame(JFrame visibleFrame, JFrame invisibleFrame) {
		visibleFrame.setVisible(true);
		invisibleFrame.setVisible(false);
	}
	
	private void switchToMainFrame(JFrame disposeFrame) {
		disposeFrame.dispose();
		mainFrame.setVisible(true);
	}
	
	// TODO Complete event handlers
	
	private void loginFrameEventHandler(JButton login) {
		login.addActionListener((ActionEvent e) ->{
			System.out.println("login!");
			
			setStudent(loginFrame.getName(), loginFrame.getId());
			System.out.println(theStudent.toString());
			
			while(!verifyAction(searchStudent())){// I changed from verifyStudent to verifyAction for now since they'll be doing the same thing
				loginFrame.displayErrorMessage("Invalid!");//  I changed from displayMessage displayErrorMessage since thats the name of the method in LoginFrame
				setStudent(loginFrame.getName(), loginFrame.getId());
				System.out.println(theStudent.toString());
			}
		
			mainFrame.setInstructionMessage(theStudent.getStudentName());
			switchToMainFrame(loginFrame);
		});	
	}

	private void mainFrameEventHandler(JButton viewAllCourses, JButton viewStudentCourses) {
				
		viewAllCourses.addActionListener((ActionEvent e) ->{
			System.out.println("View all courses!");
			viewAllCoursesFrame = new ViewAllCoursesFrame();
			String courses = getCourseCatalogue(); // assuming that we get String of all courses here
			viewAllCoursesFrame.setTextArea(courses); // pass String here
			//viewAllCoursesFrame.setTextArea("...Courses...");
			setVisibleFrame(viewAllCoursesFrame, mainFrame);
			viewAllCoursesEventHandler(viewAllCoursesFrame.getReturnToMainButton(), viewAllCoursesFrame.getSearchCourse());
		});
				
		viewStudentCourses.addActionListener((ActionEvent e) ->{
			System.out.println("View student courses!");
			String studentCourses = getStudentCourses(theStudent); // assuming that we get String of student's courses here
			//if there are no courses in the students registration, studentCourses = "";
			viewStudentCoursesFrame = new ViewStudentCoursesFrame();
			
			if(!stringNotBlank(studentCourses)) 
				studentCourses = theStudent.getStudentName() + " is not enrolled in any courses";
			
			viewStudentCoursesFrame.setTextArea(studentCourses); // pass String here
			// TODO check error below
			//viewStudentCoursesFrame.setTextArea("...Student Courses...");
			setVisibleFrame(viewStudentCoursesFrame, mainFrame);
			viewStudentCoursesEventHandler(viewStudentCoursesFrame.getReturnToMainButton());
		});
	}
	
	private void searchCourseFrameEventHandler(JButton returnToMainButton, JButton okButton) {
		returnToMainButton.addActionListener((ActionEvent e) ->{
			setVisibleFrame(viewAllCoursesFrame, searchCourseFrame);
		});
				
		okButton.addActionListener((ActionEvent e) ->{
			while(!verifyCourse(searchCourse(searchCourseFrame.getCourseName(), searchCourseFrame.getCourseNum()))) {
				searchCourseFrame.displayMessage("Course does not exist!");
				return;
			}
			courseDisplayFrame = new CourseDisplayFrame();
			System.out.println(searchCourseFrame.getCourseName() + " " + searchCourseFrame.getCourseNum());
			courseDisplayFrame.setCourseTextArea(searchCourseFrame.getCourseName(), searchCourseFrame.getCourseNum());
			setVisibleFrame(courseDisplayFrame, searchCourseFrame);
			courseDisplayEventHandler(courseDisplayFrame.getReturnToSearchCourseButton(), courseDisplayFrame.getAddCourseButton(), courseDisplayFrame.getRemoveCourseButton());
		});
	}

	public void courseDisplayEventHandler(JButton returnButton, JButton addCourseButton, JButton removeCourseButton) {
		
		returnButton.addActionListener((ActionEvent e) ->{
			setVisibleFrame(searchCourseFrame, courseDisplayFrame);
		});
		
		addCourseButton.addActionListener((ActionEvent e) ->{
			System.out.println("Course added!");
			courseDisplayFrame.displayMessage("Course added successfully!");
			String added = addCourse(courseDisplayFrame.getCourseName(), courseDisplayFrame.getCourseNum(), courseDisplayFrame.getSecNum());
			if(verifyAction(added))
				courseDisplayFrame.displayMessage("Course added successfully!");
			else {
				courseDisplayFrame.displayMessage(added); // error message from addCourse on server end sent as string
				setVisibleFrame(searchCourseFrame, courseDisplayFrame);
			}
			setVisibleFrame(viewAllCoursesFrame, courseDisplayFrame);
		});
		
		removeCourseButton.addActionListener((ActionEvent e) ->{
			System.out.println("Remove course!");
			courseDisplayFrame.displayMessage("Course removed successfully!");
			String removed = removeCourse(courseDisplayFrame.getCourseName(), courseDisplayFrame.getCourseNum(), courseDisplayFrame.getSecNum());
			if (verifyAction(removed))
				courseDisplayFrame.displayMessage("Course removed successfully!");
			else {
				courseDisplayFrame.displayMessage(removed); // error message from addCourse on server end sent as string
				setVisibleFrame(searchCourseFrame, courseDisplayFrame);
			}
			setVisibleFrame (viewAllCoursesFrame, courseDisplayFrame);
		});
	}

	public void viewAllCoursesEventHandler(JButton returnToMainButton, JButton searchCourseButton) {
		returnToMainButton.addActionListener((ActionEvent e) ->{
			switchToMainFrame(viewAllCoursesFrame);
		});
		
		searchCourseButton.addActionListener((ActionEvent e) ->{
			System.out.println("Search for courses!");
			searchCourseFrame = new SearchCourseFrame();
			setVisibleFrame(searchCourseFrame, viewAllCoursesFrame);
			searchCourseFrameEventHandler(searchCourseFrame.getReturnToMainButton(), searchCourseFrame.getOkButton());
		});

	}
	
	public void viewStudentCoursesEventHandler(JButton returnToMainButton) {
		returnToMainButton.addActionListener((ActionEvent e) ->{
			switchToMainFrame(viewStudentCoursesFrame);
		});
	}

	// Case 1
	private String searchCourse(String courseName, String courseNum) {
		return comController.searchCourse(courseName, courseNum);
	}

	// Case 2
	private String addCourse(String courseName, String courseNum, String secNum) {
		return comController.addCourse(theStudent.getStudentName(), theStudent.getStudentId(), courseName, courseNum, secNum);
	}
	
	// Case 3
	private String removeCourse(String courseName, String courseNum, String secNum) {
		return comController.removeCourse(theStudent.getStudentName(), theStudent.getStudentId(), courseName, courseNum, secNum);
	}
	
	// Case 4
	private String getCourseCatalogue() {
		return comController.getCourseCatalogue();
	}
	
	// Case 5
	private String getStudentCourses(Student theStudent) {
		return comController.getStudentCourses(theStudent);
	}
	
	// Case 6
	private String searchStudent() {
		return comController.searchStudent(theStudent);
	}

	private boolean verifyCourse(String searchCourse) {
		if (searchCourse.equalsIgnoreCase("valid"))
			return true;
		else
			return false;
	}
	
	private boolean stringNotBlank(String toTest) {
		if(toTest.compareTo("") == 0) {
			return false;
		}
		return true;
	}
	
	private boolean verifyAction(String valid) {
		if (valid.equalsIgnoreCase("valid"))
				return true;
		else
			return false;
	}
	
	private void setStudent(String name, int id) {
		theStudent = new Student(name, id);
	}
	
	public static void main(String []args) {
		GUIController GUI = new GUIController();
	}
}
