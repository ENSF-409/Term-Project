package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

// TODO Documentation

public class SearchCourseFrame extends JFrame{
	private Container c;
	private JPanel pCenter, pSouth;
	private JButton returnToMain, ok;
	private JTextField courseName, courseNum;
	
	public SearchCourseFrame() {
		super("Select Courses");
		setBounds(400, 400, 600, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c = getContentPane();
		c.setLayout(new BorderLayout());
		
		// Center Panel
		pCenter = new JPanel();
		pCenter.setLayout(new FlowLayout());
		courseName = new JTextField(15);
		pCenter.add(new JLabel("Course Name: "));
		pCenter.add(courseName);
		courseNum = new JTextField(15);
		pCenter.add(new JLabel("Course Number: "));
		pCenter.add(courseNum);
		c.add("Center", pCenter);
		
		// South Panel
		pSouth = new JPanel();
		pSouth.setLayout(new FlowLayout(FlowLayout.RIGHT));
		returnToMain = new JButton("Return");
		ok = new JButton("Ok");
		pSouth.add(returnToMain);
		pSouth.add(ok);
		c.add("South", pSouth);

		pack();
	}

	public void displayMessage(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage);
	}

	public JButton getReturnToMainButton() {
		return returnToMain;
	}
	
	public JButton getOkButton() {
		return ok;
	}
	
	public String getCourseName() {
		return courseName.getText();
	}
	
	public String getCourseNum() {
		return courseNum.getText();
	}
}
