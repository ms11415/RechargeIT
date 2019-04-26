package jpa.entitymodels;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student {
	@Id
	@Column(name = "email")
	private String sEmail;
	
	@Column(name="name")
	private String sName;
	
	@Column(name="password")
	private String sPass;
	
	@OneToMany
	List<Course> sCourses;

	public Student() {
		// no parameters, initialize members to default values
		
		sEmail = "default@live.com";
		sName = "Default Name";
		sPass = "Password";
	}

	public Student(String sEmail, String sName, String sPass, List<Course> sCourses) {
		this.sEmail = sEmail;
		this.sName = sName;
		this.sPass = sPass;
		this.sCourses = sCourses;
	}

	/**
	 * @return the sEmail
	 */
	public String getsEmail() {
		return sEmail;
	}

	/**
	 * @param sEmail the sEmail to set
	 */
	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}

	/**
	 * @return the sName
	 */
	public String getsName() {
		return sName;
	}

	/**
	 * @param sName the sName to set
	 */
	public void setsName(String sName) {
		this.sName = sName;
	}

	/**
	 * @return the sPass
	 */
	public String getsPass() {
		return sPass;
	}

	/**
	 * @param sPass the sPass to set
	 */
	public void setsPass(String sPass) {
		this.sPass = sPass;
	}

	/**
	 * @return the sCourses
	 */
	public List<Course> getsCourses() {
		return sCourses;
	}

	/**
	 * @param sCourses the sCourses to set
	 */
	public void setsCourses(List<Course> sCourses) {
		this.sCourses = sCourses;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Name: " + sName + ", Email: " + sEmail + ", Password: " + sPass + "]";
	}

}