package test;
import javax.swing.*;

import java.awt.event.*;
import java.sql.SQLException;
import java.awt.*;

public class CreateUserUI implements ActionListener{

	private Calendar calendar;
	private JFrame frame;
	private JPanel panel;
	
	private JTextField userName;
	private JTextField checkUserName;
	private JPasswordField password;
	private JPasswordField checkPassword;
	
	private JLabel user;
	private JLabel checkUser;
	private JLabel code;
	private JLabel checkCode;
	private JLabel results;
	
	private JButton submitCredentials;
	
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int width = (int)screenSize.getWidth();
	private int height = (int)screenSize.getHeight();
	public CreateUserUI(Calendar calendar) {
		this.calendar = calendar;
		frame = new JFrame();
		frame.setSize(width/4, height/2);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		userName = new JTextField();
		userName.setSize(100,25);
		userName.setLocation(frame.getWidth()/2, frame.getHeight()/2 - 50);
		
		user = new JLabel("UserName");
		user.setSize(100,25);
		user.setLocation(userName.getX() - 75, userName.getY());
		
		checkUserName = new JTextField();
		checkUserName.setSize(100,25);
		checkUserName.setLocation(frame.getWidth()/2, frame.getHeight()/2);
		
		checkUser = new JLabel("Re-Type UserName");
		checkUser.setSize(150,25);
		checkUser.setLocation(checkUserName.getX() - 124, checkUserName.getY());
		
		password = new JPasswordField();
		password.setSize(100,25);
		password.setLocation(frame.getWidth()/2, frame.getHeight()/2 + 50);
		
		code = new JLabel("Password");
		code.setSize(100,25);
		code.setLocation(password.getX() - 75, password.getY());
		
		checkPassword = new JPasswordField();
		checkPassword.setSize(100,25);
		checkPassword.setLocation(frame.getWidth()/2, frame.getHeight()/2 + 100);
		
		checkCode = new JLabel("Re-Type Password");
		checkCode.setSize(150, 25);
		checkCode.setLocation(checkPassword.getX() - 124, checkPassword.getY());
		
		submitCredentials = new JButton("Submit");
		submitCredentials.setSize(100,25);
		submitCredentials.setLocation(frame.getWidth()/2, frame.getHeight()/2 + 150);
		submitCredentials.addActionListener(this);
		
		results = new JLabel("");
		results.setSize(150,25);
		results.setLocation(frame.getWidth()/2, frame.getHeight()/2 + 200);
		
		panel.add(userName);
		panel.add(user);
		panel.add(checkUserName);
		panel.add(checkUser);
		panel.add(password);
		panel.add(code);
		panel.add(checkPassword);
		panel.add(checkCode);
		panel.add(submitCredentials);
		panel.add(results);
		
		frame.add(panel, null);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("New User");
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("Checking your potential credentials");
		String testUser = userName.getText();
		String testUserCheck = checkUserName.getText();
		String testPassword = password.getText();
		String testPasswordCheck = checkPassword.getText();
		try {
			if(!calendar.createCredentials(testUser, testUserCheck, testPassword, testPasswordCheck))
				{
					results.setText("New user created");
					frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				}
		}catch (SQLException f){
			System.out.println("SQL exception occured" + f);
		}
		
	}
}
