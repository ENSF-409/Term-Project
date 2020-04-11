package view;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

// TODO Documentation

public class LoginFrame extends JFrame{
	
	private Container c;
	private JTextField name, id;
	private JButton login;
	private JTextArea welcomeMessage;
	private JPanel pNorth, pSouth;
	
	public LoginFrame() {
		// Login Frame
		super("Student Login");
		setBounds(400, 350, 700, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c = getContentPane();
		c.setLayout(new BorderLayout());
		
		// North Panel
		pNorth = new JPanel();
		pNorth.setLayout(new GridLayout(1, 0));
		welcomeMessage = new JTextArea();
		welcomeMessage.setEditable(false);
		welcomeMessage.setFont(new Font("Arial", Font.BOLD, 15));
		welcomeMessage.setLineWrap(true);
		String welcome = " Welcome!\n\n We're excited to have you try our Student Course" + 
								" Resgistration System! \n This tool is for planning and registration purposes" + 
								" to generate course list options. \n\n Please login with your first name and Student Id:\n";
		welcomeMessage.setText(welcome);
		pNorth.add(welcomeMessage);
		c.add("North", pNorth);
		
		// South Panel
		pSouth = new JPanel();
		pSouth.setLayout(new FlowLayout());
		name = new JTextField(15);
		id = new JTextField(15);
		login = new JButton("Login");
		pSouth.add(new JLabel("Name: "));
		pSouth.add(name);
		pSouth.add(new JLabel("Student Id: "));
		pSouth.add(id);
		pSouth.add(Box.createRigidArea(new Dimension(200, 0)));
		pSouth.add(login);
		
		c.add("South", pSouth);
		pack();
	}
	
	public void displayErrorMessage(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage);
	}
	
	public JButton getLoginButton() {
		return login;
	}
	
	public String getName() {
		return name.getText();
	}
	
	public int getId() {
		return Integer.parseInt(id.getText());
	}
}
