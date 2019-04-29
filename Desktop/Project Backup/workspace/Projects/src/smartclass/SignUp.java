package smartclass;
import java.sql.*;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Font;
import java.sql.DriverManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;








import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignUp {

	private JFrame frame;
	private JTextField firstNametf;
	private JTextField lastNametf;
	private JTextField userNametf;
	private JPasswordField passwordField;
	private JTextField emailTf;
	private JLabel lblPhNo;
	private String gender;
	private JTextField phNotf;
	private JComboBox genderCb;

	

	/**
	 * Create the application.
	 */
	public SignUp() {
		initialize();
		
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Segoe UI", Font.PLAIN, 14));
		frame.getContentPane().setBackground(new Color(51, 51, 102));
		frame.getContentPane().setForeground(new Color(204, 204, 204));
		frame.getContentPane().setLayout(null);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFirstName.setForeground(new Color(204, 204, 204));
		lblFirstName.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblFirstName.setBounds(110, 102, 69, 14);
		frame.getContentPane().add(lblFirstName);
		
		firstNametf = new JTextField();
		firstNametf.setBorder(null);
		firstNametf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		firstNametf.setBounds(189, 99, 156, 20);
		frame.getContentPane().add(firstNametf);
		firstNametf.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastName.setForeground(new Color(204, 204, 204));
		lblLastName.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblLastName.setBounds(110, 161, 69, 14);
		frame.getContentPane().add(lblLastName);
		
		lastNametf = new JTextField();
		lastNametf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lastNametf.setColumns(10);
		lastNametf.setBorder(null);
		lastNametf.setBounds(189, 158, 156, 20);
		frame.getContentPane().add(lastNametf);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setForeground(new Color(204, 204, 204));
		lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblUsername.setBounds(110, 222, 69, 14);
		frame.getContentPane().add(lblUsername);
		
		userNametf = new JTextField();
		userNametf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		userNametf.setColumns(10);
		userNametf.setBorder(null);
		userNametf.setBounds(189, 219, 156, 20);
		frame.getContentPane().add(userNametf);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setForeground(new Color(204, 204, 204));
		lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblPassword.setBounds(110, 281, 69, 14);
		frame.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBorder(null);
		passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		passwordField.setBounds(189, 278, 156, 20);
		frame.getContentPane().add(passwordField);
		
		JLabel lblEmail = new JLabel("E-Mail");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setForeground(new Color(204, 204, 204));
		lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblEmail.setBounds(110, 337, 69, 14);
		frame.getContentPane().add(lblEmail);
		
		emailTf = new JTextField();
		emailTf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		emailTf.setColumns(10);
		emailTf.setBorder(null);
		emailTf.setBounds(189, 334, 156, 20);
		frame.getContentPane().add(emailTf);
		
		lblPhNo = new JLabel("Phone Number");
		lblPhNo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhNo.setForeground(new Color(204, 204, 204));
		lblPhNo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblPhNo.setBounds(48, 393, 131, 14);
		
		frame.getContentPane().add(lblPhNo);
		
		
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGender.setForeground(new Color(204, 204, 204));
		lblGender.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblGender.setBounds(110, 442, 69, 14);
		frame.getContentPane().add(lblGender);
		
		gender = "";
		genderCb = new JComboBox();
		genderCb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gender = ""+genderCb.getItemAt(genderCb.getSelectedIndex());
				//System.out.println(gender);
			}
		});
		genderCb.setModel(new DefaultComboBoxModel(new String[] {"", "Male", "Female"}));
		genderCb.setBorder(null);
		genderCb.setName("");
		genderCb.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		genderCb.setBounds(189, 439, 156, 20);
		frame.getContentPane().add(genderCb);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setForeground(new Color(204, 204, 204));
		btnSignUp.setBackground(new Color(51, 153, 204));
		btnSignUp.setBorder(null);
		btnSignUp.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnSignUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					//////////////////////////////////////////
					if(isFullInformation()){
						if(isAvalibleUsername()){
							if(isvalidEmail()){
							insertValues();
							}else{
								JOptionPane.showMessageDialog(null, "Enter a valid email.");
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "Username already taken!");
							userNametf.setBackground(Color.RED);
							
						}
					}else{
						whichFeildIsEmpty();
					}
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}
		});
		btnSignUp.setBounds(261, 496, 85, 23);
		frame.getContentPane().add(btnSignUp);
		
		phNotf = new JTextField();
		phNotf.setBorder(null);
		phNotf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		phNotf.setBounds(189, 390, 156, 20);
		frame.getContentPane().add(phNotf);
		phNotf.setColumns(10);
		
		loginbtn = new JButton("Log In");
		loginbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				new Login();
			}
		});
		loginbtn.setForeground(new Color(204, 204, 204));
		loginbtn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		loginbtn.setBorder(null);
		loginbtn.setBackground(new Color(51, 153, 204));
		loginbtn.setBounds(388, 11, 69, 23);
		frame.getContentPane().add(loginbtn);
		frame.setBounds(100, 100, 483, 597);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	static final String USER = "root";
	static final String PASS = "";
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";   
	private JButton loginbtn;
	
	
	
	public void insertValues()throws Exception{
		//com.mysql.cj.jdbc.Driver
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			createTableIfNotExists();
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO user" + "(username, first, last ,password, email, phno, gender)"
			+"VALUES (?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, userNametf.getText());
			stmt.setString(2, firstNametf.getText());
			stmt.setString(3, lastNametf.getText());
			stmt.setString(4, getPassWordString());
			stmt.setString(5, emailTf.getText());
			stmt.setString(6, phNotf.getText());
			stmt.setString(7, gender);
			stmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Your account has been created successfully!");
			setFeildsEmpty();
			//new User().printUserInfo();
			
			
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	
	//
	public static void createTableIfNotExists()throws Exception{
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");	
			createDataBaseIfNotExists();	
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS user(username varchar(255) NOT NULL,"
					+ "first varchar(255) NOT NULL, last varchar(255) NOT NULL,  password varchar(255) NOT NULL, "
					+ "email varchar(255) NOT NULL, phno varchar(255) NOT NULL,	gender varchar(6) NOT NULL )";
			stmt.executeUpdate(sql);
			System.out.println("Table Created!");
		}catch(Exception e){
			System.out.println(e);
		}
		
		
	}
	
	
	public static void createDataBaseIfNotExists()throws Exception{
		String DB_URL = "jdbc:mysql://localhost/";
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");	
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			String sql = "CREATE DATABASE IF NOT EXISTS users";
			stmt.executeUpdate(sql);
			System.out.println("Database Created!");
		}catch(Exception e){
			System.out.println(e);
		}
		
		
	}
	public static Connection getConnection()throws Exception{
		String DB_URL = "jdbc:mysql://localhost/users";
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Conn Established!");
			return conn;
		}catch(Exception e){
			System.out.println(e);
		}
		return null;
	}//////////////////////////////////////////////////////////////////////////////////
	private boolean isFullInformation(){
		boolean isValidInformation = false;
		if(firstNametf.getText().isEmpty() || lastNametf.getText().isEmpty() ||
				userNametf.getText().isEmpty()  ||
				getPassWordString().isEmpty() || emailTf.getText().isEmpty() ||
				phNotf.getText().isEmpty() ||gender.isEmpty())
		{
			isValidInformation = false;
		}else{
			isValidInformation = true;
		}
			
			
		return isValidInformation;
	}
	private boolean isAvalibleUsername() throws Exception{
		try{
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM user");
		while(rs.next()){
			//System.out.println(rs.getString("username"));
			if(rs.getString("username").equals(userNametf.getText())){
				return false;
			}
		}
		}catch(Exception e){
			System.out.println(e);
		}
		return true;
	}
	private String getPassWordString(){
		char passArr[] = passwordField.getPassword();
		String passString = new String();
		for(char i: passArr){
			passString += i;
		}
		return passString;
	}
	
	private void whichFeildIsEmpty(){
		String message = "";
		if(firstNametf.getText().isEmpty()){
			message += "First name\n";
		}
		if(lastNametf.getText().isEmpty()){
			message += "Last name\n";
		}
		if(userNametf.getText().isEmpty()){
			message += "Username\n";
		}
		if(getPassWordString().isEmpty()){
			message += "Password\n";
		}
		if(emailTf.getText().isEmpty()){
			message += "Email\n";
		}
		if(phNotf.getText().isEmpty()){
			message += "Phone Number\n";
		}
		if(gender.isEmpty()){
			message += "Gender\n";
		}
		JOptionPane.showMessageDialog(null, "Follownig feild are must!\n"+message);
	}
	private void setFeildsEmpty(){
		firstNametf.setText("");
		lastNametf.setText("");
		userNametf.setText("");
		passwordField.setText("");
		emailTf.setText("");
		phNotf.setText("");
		genderCb.setSelectedIndex(0);
		userNametf.setBackground(Color.WHITE);
		
	}
	private boolean isvalidEmail(){
		if(emailTf.getText().contains("@")){
			return true;
		}
		return false;
	}
}

