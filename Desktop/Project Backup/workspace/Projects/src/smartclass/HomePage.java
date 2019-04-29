package smartclass;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JMenuBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import java.awt.Component;

public class HomePage {

	private JFrame frame;
	private static JPanel menuPanel;
	private static JPanel classcodepnl;
	private static JLabel codelbl;
	private static JLabel classcodelabel;
	private JPanel parentPanel;
	private static JScrollPane scrollPane;
	private static JPanel view;
	/**
	 * Create the application.
	 */
	public HomePage() {
		initialize();
		
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 868, 554);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel lblClasses = new JLabel("Classes");
		lblClasses.setFont(new Font("Segoe UI", Font.BOLD, 18));
		Border border = BorderFactory.createLineBorder(Color.BLACK);
	    lblClasses.setBorder(border);
	    
		
		view = new JPanel();
		//view.add(lblClasses);
		view.setLayout(new BoxLayout(view, BoxLayout.Y_AXIS));
		
		scrollPane = new JScrollPane(view);
		
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(100, 500));
		scrollPane.setColumnHeaderView(lblClasses);
		
		
		menuPanel = new JPanel();
		frame.getContentPane().add(menuPanel, BorderLayout.WEST);
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
		
		
		parentPanel = new JPanel();
		frame.getContentPane().add(parentPanel, BorderLayout.CENTER);
		parentPanel.setLayout(new BorderLayout(0, 0));
		
		classcodepnl = new JPanel();
		parentPanel.add(classcodepnl, BorderLayout.EAST);
		classcodepnl.setLayout(new BoxLayout(classcodepnl, BoxLayout.Y_AXIS));
		
		classcodelabel = new JLabel("Class Code");
		
		
		codelbl = new JLabel("");
		classcodepnl.add(codelbl);
		
		
		
		
		
		
		
		//for(int i = 0; i < 5; i++){
		
		/*for(MyClass classs: Login.getUser().getClasses()){
			JLabel classsNamelbl = new JLabel(classs.getClassName());
			classsNamelbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
			menuPanel.add(classsNamelbl);
		 //System.out.println(classs.getClassName());
		 //System.out.println("classs.getClassName()");
		}*/
		if(!isInAClass()){
			JLabel c_Namelbl = new JLabel("No classes");
			menuPanel.add(c_Namelbl);
		}
		
		//}
		
		
		
		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(51, 51, 102));
		
		frame.setJMenuBar(menuBar);
		
		JLabel lblSmartClass = new JLabel("Smart Class");
		lblSmartClass.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblSmartClass.setHorizontalAlignment(SwingConstants.LEFT);
		lblSmartClass.setForeground(Color.WHITE);
		lblSmartClass.setFont(new Font("Segoe UI", Font.PLAIN, 36));
		menuBar.add(lblSmartClass);
		
		menuBar.add(Box.createHorizontalGlue());
		
		JMenu mnProfile = new JMenu("Profile");
		mnProfile.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnProfile.setHorizontalAlignment(SwingConstants.RIGHT);
		mnProfile.setHorizontalTextPosition(SwingConstants.RIGHT);
		mnProfile.setForeground(Color.LIGHT_GRAY);
		
		
		JMenuItem mntmViewProfile = new JMenuItem("View Profile");
		mntmViewProfile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ViewProfile();
			}
		});
		mnProfile.add(mntmViewProfile);
		
		JMenuItem mntmEditProfile = new JMenuItem("Edit Profile");
		mnProfile.add(mntmEditProfile);
		
		JMenuItem mntmLogOut = new JMenuItem("Log Out");
		mntmLogOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				new Login();
			}
		});
		mnProfile.add(mntmLogOut);
		
		JMenu menu = new JMenu("+");
		menu.setHorizontalTextPosition(SwingConstants.CENTER);
		menu.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menu.setForeground(Color.LIGHT_GRAY);
		menu.setBackground(Color.DARK_GRAY);
		menu.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(menu);
		menuBar.add(mnProfile);
		
		JMenuItem mntmCreateClass = new JMenuItem("Create Class");
		mntmCreateClass.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				new Create_Class();
			}
		});
		menu.add(mntmCreateClass);
		
		JMenuItem mntmJoinClass = new JMenuItem("Join Class");
		mntmJoinClass.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new JoinClass();
				
			}
		});
		menu.add(mntmJoinClass);
		
		
	}
	
	public boolean isInAClass(){
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Create_Class.createTableIfNotExists();
			Connection conn =SignUp.getConnection();
			Statement stmt = conn.createStatement();
			final ResultSet rs = stmt.executeQuery("SELECT * FROM classes");
			
			showClassNameOnMenu(rs);
			
			return true;
			}catch(Exception e){
				System.out.println(e);
			}
	return false;
	}

	public static void showClassNameOnMenu(ResultSet rs) throws SQLException{
		
		while(rs.next()){
			
			
			if(rs.getString("username").equals(Login.getUser().getUserName())){
				final JLabel c_Namelbl = new JLabel(rs.getString("classname"));
				codelbl.setText("");
				final String classcodelbl = rs.getString("classcode"); 
				c_Namelbl.addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mousePressed(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseExited(MouseEvent arg0) {
						// TODO Auto-generated method stub
						c_Namelbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
						
					}
					
					@Override
					public void mouseEntered(MouseEvent arg0) {
						// TODO Auto-generated method stub
						c_Namelbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
						
						
					}
					
					@Override
					public void mouseClicked(MouseEvent arg0) {
						
						
						codelbl.setText(classcodelbl);
						classcodepnl.add(classcodelabel);
						classcodepnl.add(codelbl);
						
					}
				});
				c_Namelbl.setForeground(Color.DARK_GRAY);
				c_Namelbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				c_Namelbl.setHorizontalAlignment(JLabel.CENTER);
				
				view.add(c_Namelbl);
				
				
				Login.getUser().getClasses().add(new MyClass(rs.getString("classcode"),Login.getUser().getUserName(), 
						rs.getString("classname"), rs.getString("subject"),rs.getString("section")));
				
				
			}
			
		}
		
		menuPanel.add(scrollPane);
	}
	
	
}
