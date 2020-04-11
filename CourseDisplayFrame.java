package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CourseDisplayFrame extends JFrame{
	private Container c;
	private JPanel pNorth, pCenter, pSouth;
	private JButton returnToSearchCourse, addCourse, removeCourse;
	private String courseName, courseNum;
	private JTextField secNum;
	private JLabel courseText;
	
	public CourseDisplayFrame() {
		super("Course Display");
		setBounds(400, 400, 600, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c = getContentPane();
		c.setLayout(new BorderLayout());
		
		// Center Panel
		pCenter = new JPanel();
		pCenter.add(new JLabel("Section number: "));
		secNum = new JTextField(15);
		pCenter.add(secNum);
		c.add("Center", pCenter);
		
		// South Panel
		pSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		returnToSearchCourse = new JButton("Return");
		addCourse = new JButton("Add Course");
		removeCourse = new JButton ("Remove Course");
		pSouth.add(returnToSearchCourse);
		pSouth.add(addCourse);
		pSouth.add(removeCourse);
		c.add("South", pSouth);
	}
	
	public void setCourseTextArea(String courseName, String courseNum) {
		// North Panel
		this.courseName = courseName;
		this.courseNum = courseNum;
		pNorth = new JPanel();
		courseText = new JLabel("Course: " + courseName + " " + courseNum);
		pNorth.add(courseText);
		c.add("North", pNorth);
		pack();
	}
	
	public void displayMessage(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage);
	}
	
	public JButton getAddCourseButton() {
		return addCourse;
	}
	
	public JButton getRemoveCourseButton() {
		return removeCourse;
	}
	
	public JButton getReturnToSearchCourseButton() {
		return returnToSearchCourse;
	}
	
	public String getSecNum() {
		return secNum.getText();
	}

	public String getCourseName() {
		return courseName;
	}

	public String getCourseNum() {
		return courseNum;
	}
}
