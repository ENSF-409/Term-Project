package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

// TODO Documentation

public class MainFrame extends JFrame{
	private Container c;
	private JPanel pCenter, pSouth;
	private JButton viewAllCourses, viewStudentCourses;
	private JLabel labelCenter;
	private JTextArea instructions;
	
	public MainFrame() {
		// Main Frame
		super("Student Course Registration System");
		setBounds(350, 200, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c = getContentPane();
		c.setLayout(new BorderLayout());
		
		// South Panel
		pSouth = new JPanel();
		pSouth.setLayout(new FlowLayout());
		viewAllCourses = new JButton("View All Courses");
		viewStudentCourses = new JButton("View My Courses");
		pSouth.add(viewAllCourses);
		pSouth.add(viewStudentCourses);
		c.add("South", pSouth);
		
		// Center Panel
		pCenter = new JPanel();
		pCenter.setLayout(new GridLayout(2, 0));
	}
	
	public void setInstructionMessage(String student) {
		// Center Panel Continued
		labelCenter = new JLabel("Welcome " + student + "!");
		labelCenter.setFont(new Font("Arial", Font.BOLD, 30));
		labelCenter.setHorizontalAlignment(SwingConstants.CENTER);
		pCenter.add(labelCenter);
		instructions = new JTextArea();
		instructions.setEditable(false);
		instructions.setFont(new Font("Arial", Font.PLAIN, 20));
		instructions.setLineWrap(true);
		String welcome = "To view all available courses, click on View All Courses.\n"
				+ "To view all courses you are registered in, click on View My Courses.";
		instructions.setText(welcome);
		pCenter.add(instructions);
		c.add("Center", pCenter);
	}
	
	public void displayMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	public JButton getViewAllCoursesButton() {
		return viewAllCourses;
	}
	
	public JButton getViewStudentCoursesButton() {
		return viewStudentCourses;
	}

}
