package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ViewStudentCoursesFrame extends JFrame {
	private Container c;
	private JTextArea textArea;
	private JPanel pSouth;
	private JButton returnToMain;
	private JScrollPane scrollPanel;
	
	public ViewStudentCoursesFrame() {
		super("View My Courses");
		setBounds(400, 350, 700, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c = getContentPane();
		c.setLayout(new BorderLayout());
		
		// South Panel
		pSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		returnToMain = new JButton("Return");
		pSouth.add(returnToMain);
		c.add("South", pSouth);
	}

	public void setTextArea(String studentCourses) {
		// Center Text Area
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPanel = new JScrollPane(textArea);
		textArea.setText(studentCourses);
		textArea.setLineWrap(false); // may not need
		c.add("Center", textArea);
		c.add("Center", scrollPanel);
		pack();
	}
	
	public JButton getReturnToMainButton() {
		return returnToMain;
	}
}
