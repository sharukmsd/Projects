package smartclass;




import javax.swing.JFrame;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.awt.Color;

import javax.swing.JButton;
//import javax.xml.transform.Templates;



//import com.mysql.cj.x.protobuf.MysqlxSql.StmtExecute;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Create_Class {

	private JFrame frame;
	private JTextField classNametf;
	private JTextField sectiontf;
	private JTextField subjectTf;
	private String classcode;
	
	/**
	 * Create the application.
	 */
	public Create_Class() {
		initialize();
		classcode = genrateClassCode();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.getContentPane().setForeground(new Color(255, 255, 255));
		frame.getContentPane().setBackground(new Color(51, 51, 102));
		frame.getContentPane().setFont(new Font("Segoe UI", Font.PLAIN, 12));
		frame.setBounds(100, 100, 450, 441);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblClassName = new JLabel("Class Name");
		lblClassName.setForeground(new Color(255, 255, 255));
		lblClassName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblClassName.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblClassName.setBounds(73, 101, 62, 14);
		frame.getContentPane().add(lblClassName);
		
		JLabel lblSection = new JLabel("Section");
		lblSection.setForeground(new Color(255, 255, 255));
		lblSection.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSection.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblSection.setBounds(73, 166, 62, 14);
		frame.getContentPane().add(lblSection);
		
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setForeground(new Color(255, 255, 255));
		lblSubject.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSubject.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblSubject.setBounds(73, 234, 62, 14);
		frame.getContentPane().add(lblSubject);
		
		classNametf = new JTextField();
		classNametf.setBounds(159, 99, 203, 20);
		frame.getContentPane().add(classNametf);
		classNametf.setColumns(10);
		
		sectiontf = new JTextField();
		sectiontf.setColumns(10);
		sectiontf.setBounds(159, 164, 203, 20);
		frame.getContentPane().add(sectiontf);
		
		subjectTf = new JTextField();
		subjectTf.setColumns(10);
		subjectTf.setBounds(159, 232, 203, 20);
		frame.getContentPane().add(subjectTf);
		
		JLabel lblCreateClass = new JLabel("Create Class");
		lblCreateClass.setForeground(new Color(204, 204, 204));
		lblCreateClass.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		lblCreateClass.setBounds(73, 11, 131, 41);
		frame.getContentPane().add(lblCreateClass);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(IsAvalibleClassCode()){
					try {
						insertValues(classcode, Login.getUser().getUserName(), classNametf.getText(),
								subjectTf.getText(), sectiontf.getText());
						JOptionPane.showMessageDialog(null, "Class created successfully!");
					} catch (Exception e) {
						System.out.println(e);
					}
				}else{
					JOptionPane.showMessageDialog(null, "else");
				}
				
				frame.dispose();
				new HomePage();
			}
		});
		btnCreate.setForeground(new Color(204, 204, 204));
		btnCreate.setBackground(new Color(51, 153, 204));
		btnCreate.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnCreate.setBounds(285, 298, 77, 23);
		frame.getContentPane().add(btnCreate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				new HomePage();
			}
		});
		btnCancel.setForeground(new Color(204, 204, 204));
		btnCancel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnCancel.setBackground(new Color(51, 51, 102));
		btnCancel.setBounds(198, 299, 77, 23);
		frame.getContentPane().add(btnCancel);
	}
	
	
	public static void insertValues(String c_code, String userName, String className, String sub, String sec)throws Exception{
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			createTableIfNotExists();
			Connection conn = SignUp.getConnection();
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO classes" 
			+ "(classcode, username, classname, subject ,section)"
			+"VALUES (?, ?, ?, ?, ?)");
			
			stmt.setString(1, c_code);
			stmt.setString(2, userName);
			stmt.setString(3, className);
			stmt.setString(4, sub);
			stmt.setString(5, sec);
			stmt.executeUpdate();
			/**
			 * The first user in the database of a classes 
			 * with a unique class code is the class creator
			 */
			Login.getUser().getClasses().add(new MyClass(c_code, userName, 
					className, sub, sec));
			
			
			
			
			
			
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	
	
	
	
	
	
	public static void createTableIfNotExists()throws Exception{
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");	
			SignUp.createDataBaseIfNotExists();	
			Connection conn = SignUp.getConnection();
			Statement stmt = conn.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS classes(classcode varchar(8) NOT NULL,username varchar(255) NOT NULL,"
					+ "classname varchar(255) NOT NULL, subject varchar(255) NOT NULL,  section varchar(255) NOT NULL )";
			stmt.executeUpdate(sql);
			System.out.println("Classes Table Created!");
		}catch(Exception e){
			System.out.println(e);
		}
		
		
	}
	
	public boolean IsAvalibleClassCode(){
		String temp = "";
		try{
			createTableIfNotExists();
			Connection conn =SignUp.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM classes");
			
			while(rs.next()){
				//System.out.println(rs.getString("classcode"));
				temp = genrateClassCode();
				while(rs.getString("classcode").equals(temp)){
					temp = genrateClassCode();
					classcode = temp;
				}
			}
			}catch(Exception e){
				System.out.println(e);
			}
		
		return true;
	}
	public String genrateClassCode(){
		return String.valueOf((int)(Math.random()*200000));
	}
	
	
}
