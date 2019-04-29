package smartclass;

import java.util.ArrayList;

public class User {
	private String firstName;
	private String lastName;
	private String userName;
	private String passWord;
	private String email;
	private String phNo;
	private String gender;
	//private static ArrayList<User> users = new ArrayList<>();
	private ArrayList<MyClass> classes =  new ArrayList<>();
	
	
	public User() {
		firstName = "";
		lastName = "";
		userName = "";
		passWord = "";
		email = "";
		phNo = "";
		gender = "";
		
	}
	
	public User(String firstName, String lastName, String userName, String passWord,
			String email, String phNo, String gender){
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.passWord =passWord;
		this.email = email;
		this.phNo = phNo;
		this.gender = gender;
	}
	/*public static void main (String[] args)  {
		User user = new User("Ali","Ahmad", "ali", "***", "email", "654151", "Male");
		users.add(user);
		user.printUserInfo();
		
	}*/


	//////////////////////Getters & Setters////////////////////////////////////
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public String getEmail() {
		return email;
	}

	public String getPhNo() {
		return phNo;
	}

	public String getGender() {
		return gender;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhNo(String phNo) {
		this.phNo = phNo;
	}

	public void setGender(String gender) {
		this.gender = gender;
		
	}

	
	/**
	 * @return the classes
	 */
	public  ArrayList<MyClass> getClasses() {
		return classes;
	}

	/**
	 * @param classes the classes to set
	 */
	public void setClasses(ArrayList<MyClass> classes) {
		this.classes = classes;
	}

	
	@Override
	public String toString(){  
  		return firstName+" "+lastName+" "+userName+" "+passWord+" "+email+" "+phNo+" "+gender;  
 	}
 	  
	
}
