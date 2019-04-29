package smartclass;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class ViewProfile {

	private JFrame frame;
	private JTextField firstTf;
	private JTextField lastTf;
	private JTextField userNametf;
	private JTextField emailTf;
	private JTextField phNotf;
	private JTextField genderTf;
	
	private JButton button;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public ViewProfile() {
		initialize();
		//System.out.println(Login.getUser());
		
		setValues();
		frame.setVisible(true);
		
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(51, 51, 102));
		frame.setBounds(100, 100, 752, 452);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setForeground(new Color(255, 255, 255));
		lblFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFirstName.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblFirstName.setBounds(77, 76, 77, 14);
		frame.getContentPane().add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setForeground(new Color(255, 255, 255));
		lblLastName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastName.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblLastName.setBounds(77, 121, 77, 14);
		frame.getContentPane().add(lblLastName);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(new Color(255, 255, 255));
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblUsername.setBounds(77, 168, 77, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblEmail = new JLabel("E-Mail");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblEmail.setBounds(77, 216, 77, 14);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblPhNo = new JLabel("Ph. No");
		lblPhNo.setForeground(new Color(255, 255, 255));
		lblPhNo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhNo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblPhNo.setBounds(77, 264, 77, 14);
		frame.getContentPane().add(lblPhNo);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setForeground(new Color(255, 255, 255));
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGender.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblGender.setBounds(77, 312, 77, 14);
		frame.getContentPane().add(lblGender);
		
		firstTf = new JTextField();
		firstTf.setEditable(false);
		firstTf.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		firstTf.setBorder(null);
		firstTf.setBounds(164, 74, 261, 20);
		frame.getContentPane().add(firstTf);
		firstTf.setColumns(10);
		
		lastTf = new JTextField();
		lastTf.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lastTf.setEditable(false);
		lastTf.setColumns(10);
		lastTf.setBorder(null);
		lastTf.setBounds(164, 119, 261, 20);
		frame.getContentPane().add(lastTf);
		
		userNametf = new JTextField();
		userNametf.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		userNametf.setEditable(false);
		userNametf.setColumns(10);
		userNametf.setBorder(null);
		userNametf.setBounds(164, 166, 261, 20);
		frame.getContentPane().add(userNametf);
		
		emailTf = new JTextField();
		emailTf.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		emailTf.setEditable(false);
		emailTf.setColumns(10);
		emailTf.setBorder(null);
		emailTf.setBounds(164, 214, 261, 20);
		frame.getContentPane().add(emailTf);
		
		phNotf = new JTextField();
		phNotf.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		phNotf.setEditable(false);
		phNotf.setColumns(10);
		phNotf.setBorder(null);
		phNotf.setBounds(164, 262, 261, 20);
		frame.getContentPane().add(phNotf);
		
		genderTf = new JTextField();
		genderTf.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		genderTf.setEditable(false);
		genderTf.setColumns(10);
		genderTf.setBorder(null);
		genderTf.setBounds(164, 310, 261, 20);
		frame.getContentPane().add(genderTf);
		
		button = new JButton("<Back");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		button.setBackground(new Color(51, 102, 204));
		button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		button.setBounds(0, 0, 77, 23);
		frame.getContentPane().add(button);
		
		JTextArea classesTa = new JTextArea();
		classesTa.setBounds(488, 76, 196, 254);
		frame.getContentPane().add(classesTa);
	}
	
	public void setValues(){
		//System.out.println("sdgsgdfgdf");
		User user = Login.getUser();
		firstTf.setText(user.getFirstName());
		
		lastTf.setText(user.getLastName());
		userNametf.setText(user.getUserName());
		emailTf.setText(user.getEmail());
		phNotf.setText(user.getPhNo());
		genderTf.setText(user.getGender());
	
	}
}
