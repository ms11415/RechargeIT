package jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

public interface StudentDAO {

	public List<Student> getAllStudents(EntityManager em);
	
	public Student getStudentByEmail(EntityManager em, String sEmail);
	
	public boolean validateStudent(EntityManager em, String sEmail, String sPassword);
	
	public void registerStudentToCourse(EntityManager em, String sEmail, int cId);
	
	public List<Course> getStudentCourses(EntityManager em, String sEmail);
}
