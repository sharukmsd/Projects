/**
 * 
 */
package smartclass;

import javax.management.loading.PrivateClassLoader;

/**
 * @author Shahrukh
 *
 */
public class MyClass {
	private String className;
	private String subject;
	private String section;
	private String classCode;
	private String userName;
	
	public MyClass(){
		className = "";
		subject = "";
		section = "";
		classCode = "";
	}
	
	
	
	/**
	 * @param className
	 * @param subject
	 * @param section
	 */
	public MyClass(String classcode, String userName, String className, String subject, String section) {
		this.className = className;
		this.subject = subject;
		this.section = section;
		this.classCode = classcode;
		this.userName = userName;
		
		
	}
	
	
	
	
	
	/**
	 * @return the classCode
	 */
	public String getClassCode() {
		return classCode;
	}



	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}



	/**
	 * @param classCode the classCode to set
	 */
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}



	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}



	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @return the section
	 */
	public String getSection() {
		return section;
	}
	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @param section the section to set
	 */
	public void setSection(String section) {
		this.section = section;
	}
	/*void showInformation(User user){
		System.out.println(" Working");
		user.printUserInfo();
		firstTf.setText(user.getFirstName());
		System.out.println(" Working");
		lastTf.setText(user.getLastName());
		userNametf.setText(user.getUserName());
		emailTf.setText(user.getEmail());
		phNotf.setText(user.getPhNo());
		genderTf.setText(user.getGender());
	}*/
	
	
}
