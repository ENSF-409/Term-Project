package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

// TODO Documentation

public class ViewAllCoursesFrame extends JFrame{
	private Container c;
	private JPanel pCenter, pSouth;
	private JTextArea textArea;
	private JButton returnToMain, searchCourse;
	private JScrollPane scrollPanel;
	
	public ViewAllCoursesFrame() {
		super("View All Courses");
		setBounds(350, 200, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c = getContentPane();
		c.setLayout(new BorderLayout());
		
		// South Panel
		pSouth = new JPanel(new FlowLayout(FlowLayout.CENTER)); // TODO check if works with JPanel default constructor
		returnToMain = new JButton("Return");
		searchCourse = new JButton("Search Course");
		pSouth.add(returnToMain);
		pSouth.add(searchCourse);
		c.add("South", pSouth);
	}

	public void setTextArea(String courses) {
		// Center Panel
		pCenter = new JPanel();
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setText(courses);
		textArea.setLineWrap(false); // may not need
		scrollPanel = new JScrollPane(textArea);
		pCenter.add(textArea);
		c.add(scrollPanel, BorderLayout.CENTER);
		pack();
	}
	
	public JButton getReturnToMainButton() {
		return returnToMain;
	}
	
	public JButton getSearchCourse() {
		return searchCourse;
	}
	
}
