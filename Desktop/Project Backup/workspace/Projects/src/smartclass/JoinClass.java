package smartclass;

import javax.swing.JFrame;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class JoinClass {

	private JFrame frame;
	private JTextField classCodetf;

	

	/**
	 * 
	 * Create the application.
	 */
	public JoinClass() {
		initialize();
		
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(51, 51, 102));
		frame.getContentPane().setForeground(new Color(255, 255, 255));
		frame.getContentPane().setFont(new Font("Segoe UI", Font.PLAIN, 12));
		frame.getContentPane().setLayout(null);
		
		JLabel lblClassCode = new JLabel("Class Code");
		lblClassCode.setForeground(new Color(255, 255, 255));
		lblClassCode.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblClassCode.setHorizontalAlignment(SwingConstants.RIGHT);
		lblClassCode.setBounds(70, 137, 59, 14);
		frame.getContentPane().add(lblClassCode);
		
		classCodetf = new JTextField();
		classCodetf.setBorder(null);
		classCodetf.setBounds(139, 135, 216, 20);
		frame.getContentPane().add(classCodetf);
		classCodetf.setColumns(10);
		
		JLabel lblAskYourTeacher = new JLabel("Ask your teacher for the class code, than enter it here");
		lblAskYourTeacher.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblAskYourTeacher.setForeground(new Color(204, 204, 204));
		lblAskYourTeacher.setBounds(70, 76, 285, 14);
		frame.getContentPane().add(lblAskYourTeacher);
		
		JButton btnJoin = new JButton("Join");
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!isAlreadyJoined()){
					if(isValidCode()){
					JOptionPane.showMessageDialog(null, "Class joined successfully!");
					frame.dispose();
					}else{
					JOptionPane.showMessageDialog(null, "Class Code is not valid!");
					}
				}else{
					JOptionPane.showMessageDialog(null, "Class already joined!");
				}
			}
		});
		btnJoin.setForeground(new Color(204, 204, 204));
		btnJoin.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnJoin.setBackground(new Color(51, 153, 204));
		btnJoin.setBounds(285, 181, 70, 23);
		frame.getContentPane().add(btnJoin);
		
		JButton button = new JButton("Cancel");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		button.setForeground(new Color(204, 204, 204));
		button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		button.setBackground(new Color(51, 51, 102));
		button.setBounds(198, 182, 77, 23);
		frame.getContentPane().add(button);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public boolean isValidCode(){
		try{
			ResultSet rs = getClassesResultSet();
			HomePage.showClassNameOnMenu(rs);
			while(rs.next()){
				
				if(rs.getString("classcode").equals(classCodetf.getText())){
					if(rs.getString("username").equals(Login.getUser().getUserName())){
						return false;
					}
					Create_Class.insertValues(rs.getString("classcode"), Login.getUser().getUserName(), 
							rs.getString("classname"), rs.getString("subject"), rs.getString("section"));
					
					Login.getUser().getClasses().add(new MyClass(rs.getString("classcode"),Login.getUser().getUserName(), 
							rs.getString("classname"), rs.getString("subject"),rs.getString("section")));
					
					return true;
				}
			
			}
		}catch(Exception e){System.out.println(e);}
		return false;
	}

	public boolean isAlreadyJoined(){
		boolean isAlreadyJoined = false;
		try{
			ResultSet rs = getClassesResultSet();
			
			while(rs.next()){
				if(rs.getString("classcode").equals(classCodetf.getText())){
					if(rs.getString("username").equals(Login.getUser().getUserName())){
						isAlreadyJoined = true;
					}
				}
			}
		}catch(Exception e){
			System.out.println(e);
		}
		return isAlreadyJoined;
	}
	
	private ResultSet getClassesResultSet(){
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Create_Class.createTableIfNotExists();
			Connection conn = SignUp.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM classes");
			return rs;
		}catch(Exception e){
			System.out.println(e);
		}
		return null; 
	}
}
