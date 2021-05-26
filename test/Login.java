package test;
import javax.swing.*;

import java.awt.event.*;
import java.sql.SQLException;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.*;
import java.util.ArrayList;

public class Login implements ActionListener{

	private Calendar calendar;
	private JFrame frame;
	private JPanel text;
	private JButton login;
	private JButton createUser;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int width = (int)screenSize.getWidth();
	private int height = (int)screenSize.getHeight();
	private JTextField userName;
	private JLabel user;
	private JPasswordField password;
	private JLabel code;
	private CreateUserUI newUserUI;
	
	private int[] checkUser;
	private ArrayList<Event> events = new ArrayList<Event>();
	
	public Login(Calendar calendar) {
		
		//System.out.println("Width: " + width/2 + " Height: " + height/2);
		
		this.calendar = calendar;
		
		frame = new JFrame();
		frame.setSize(width, height);
	
		text = new JPanel();
		text.setLayout(null);
		
		userName = new JTextField();
		userName.setSize(100,25);
		userName.setLocation(width/2, height/2 - 25);
		
		user = new JLabel("UserName");
		user.setSize(100,25);
		user.setLocation(userName.getX() - 75, userName.getY());
		
		password = new JPasswordField();
		password.setSize(100,25);
		password.setLocation(width/2, height/2 + 25);
		
		code = new JLabel("Password");
		code.setSize(100,25);
		code.setLocation(password.getX() - 75, password.getY());
		
		login = new JButton("Login");
		login.setSize(100,25);
		login.setLocation(width/2, height/2 + 75);
		login.addActionListener(this);
		
		createUser = new JButton("New User?");
		createUser.setSize(100,25);
		createUser.setLocation(width/2, height/2 + 125);
		createUser.addActionListener(this);
		
		text.add(userName, BorderLayout.CENTER);
		text.add(password, BorderLayout.CENTER);
		text.add(user);
		text.add(code);
		text.add(login);
		text.add(createUser);
		
		frame.add(text, null);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Login");
		frame.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == login)
		{
			String testUser = userName.getText();
			String testPassword = password.getText();
			try {
				checkUser = calendar.testCredentials(testUser, testPassword);
				if(checkUser[0] == 1)
				{
					events = calendar.getEvents(checkUser[1]);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				}
			}catch (SQLException f){
				System.out.println("SQL exception occured" + f);
			}
		}
		else if(e.getSource() == createUser)
		{
			newUserUI = new CreateUserUI(calendar);
		}
		
	}

}
