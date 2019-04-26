package jpa.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

public class StudentService implements StudentDAO {

	@Override
	public List<Student> getAllStudents(EntityManager em) {
		// create query to get all rows from Student table
		Query query = em.createQuery("Select s from Student s");
		// get result list and assign to list variable to return
		List<Student> allStudents = query.getResultList();
		return allStudents;
	}

	@Override
	public Student getStudentByEmail(EntityManager em, String sEmail) {
		Student foundStudent = em.find(Student.class, sEmail);
		return foundStudent;
	}

	@Override
	public boolean validateStudent(EntityManager em, String sEmail, String sPassword) {
		// get student by email
		Student foundStudent = getStudentByEmail(em, sEmail);
		// first check to see whether user is found; if not, return false
		if (foundStudent == null) {
			System.out.println("User not found.");
			return false;
		// otherwise use getter and check whether password matches
		// must use equals() for string comparison
		} else if (foundStudent.getsPass().equals(sPassword)) {
			System.out.println("Password matches, you are now logged in.\n");
			return true;
		} else {
			System.out.println("Password does not match.");
			return false;
		}
	}

	@Override
	public void registerStudentToCourse(EntityManager em, String sEmail, int cId) {
		// find student by email
		Student foundStudent = getStudentByEmail(em, sEmail);
		// then use getter to return the student's course list
		List<Course> studentCourses = foundStudent.getsCourses();
		// find course by ID
		Course foundCourse = CourseService.getCourseById(em, cId);
		// first check whether the course is valid
		if (foundCourse == null) {
			System.out.println("\n *** That course does not exist. *** \n");
		// if exists, check to see if student is already registered in the course
		} else if (studentCourses.contains(foundCourse)) {
			System.out.println("\n *** You are already registered for that course. *** \n");
		} else {
			// add the course to student's course list
			studentCourses.add(foundCourse);
			// commit the data
			em.getTransaction().begin();
			em.persist(foundStudent);
			em.getTransaction().commit();
		}
	}

	@Override
	public List<Course> getStudentCourses(EntityManager em, String sEmail) {
		// find student by email
		Student foundStudent = getStudentByEmail(em, sEmail);
		// then use getter to return the student's course list
		List<Course> studentCourses = foundStudent.getsCourses();
		return studentCourses;
	}
	
	public void printStudentList(List<Student> studentList) {
		// added helper method to output student list details
		// TODO use printf to format nicely
		for (Student s:studentList) {
			System.out.println(s.toString());
		}
	}
}