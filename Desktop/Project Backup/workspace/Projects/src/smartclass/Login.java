package smartclass;

import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.JButton;

import java.awt.Point;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;
	private JTextField userNametf;
	private JPasswordField passWordf;
	private static User user;
	

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setLocation(new Point(400, 400));
		frame.getContentPane().setLocation(new Point(400, 400));
		frame.getContentPane().setForeground(new Color(204, 204, 204));
		frame.getContentPane().setFont(new Font("Segoe UI", Font.PLAIN, 14));
		frame.getContentPane().setBackground(new Color(51, 51, 102));
		frame.setBounds(100, 100, 586, 399);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel userNamelbl = new JLabel("Username");
		userNamelbl.setForeground(new Color(204, 204, 204));
		userNamelbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		userNamelbl.setBounds(143, 129, 64, 14);
		frame.getContentPane().add(userNamelbl);
		
		userNametf = new JTextField();
		userNametf.setBorder(UIManager.getBorder("ComboBox.border"));
		userNametf.setHorizontalAlignment(SwingConstants.LEFT);
		userNametf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		userNametf.setBounds(235, 128, 171, 20);
		frame.getContentPane().add(userNametf);
		userNametf.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblPassword.setForeground(new Color(204, 204, 204));
		lblPassword.setBounds(143, 185, 64, 14);
		frame.getContentPane().add(lblPassword);
		
		passWordf = new JPasswordField();
		passWordf.setBorder(UIManager.getBorder("ComboBox.border"));
		passWordf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		passWordf.setBounds(235, 184, 171, 20);
		frame.getContentPane().add(passWordf);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					SignUp.createTableIfNotExists();
				} catch (Exception e) {
					System.out.println(e);
				}
				if(isUser(userNametf.getText(), getPassWordString())){
					//JOptionPane.showMessageDialog(null, "Successfully Loged In!");
					new HomePage();
					frame.dispose();
				}else{
					JOptionPane.showMessageDialog(null, "Incorrect username or password!");
				}
			}
		});
		btnLogin.setForeground(new Color(204, 204, 204));
		btnLogin.setBackground(new Color(51, 153, 204));
		btnLogin.setBorder(null);
		btnLogin.setBounds(342, 248, 64, 23);
		frame.getContentPane().add(btnLogin);
		
		JLabel lblNewMember = new JLabel("New member?");
		lblNewMember.setForeground(new Color(153, 153, 153));
		lblNewMember.setFont(new Font("Urdu Typesetting", Font.PLAIN, 14));
		lblNewMember.setBounds(392, 19, 92, 20);
		frame.getContentPane().add(lblNewMember);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				new SignUp();
			}
		});
		btnSignUp.setForeground(new Color(204, 204, 204));
		btnSignUp.setBorder(null);
		btnSignUp.setBackground(new Color(51, 153, 204));
		btnSignUp.setBounds(484, 19, 64, 23);
		frame.getContentPane().add(btnSignUp);
		user = new User();
	}
	
	public boolean isUser(String username, String password){
		try{
			Connection conn = SignUp.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM user" );
			while(rs.next()){
				if(rs.getString("username").equals(username) && rs.getString("password").equals(password)){
					user = new User(rs.getString("first"), rs.getString("last"), rs.getString("username"),
							rs.getString("password"), rs.getString("email"), rs.getString("phno"), rs.getString("gender"));
					
					
					
					
					
					
					return true;
				}
			}
			
		}catch(Exception e){
			
		}
		return false;
	}
	
	private String getPassWordString(){
		char passArr[] = passWordf.getPassword();
		String passString = new String();
		for(char i: passArr){
			passString += i;
		}
		return passString;
	}

	/**
	 * @return the user
	 */
	public static User getUser(){
		return user;
	}

}
