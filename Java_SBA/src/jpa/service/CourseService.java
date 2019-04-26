package jpa.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;

public class CourseService implements CourseDAO {

	@Override
	public List<Course> getAllCourses(EntityManager em) {
		// create query to get all rows from Course table
		Query query = em.createQuery("Select c from Course c");
		// get result list and assign to list variable to return
		List<Course> allCourses = query.getResultList();
		return allCourses;
	}
	
	public static Course getCourseById(EntityManager em, int cId) {
		// added helper method to find course by ID
		Course foundCourse = em.find(Course.class, cId);
		return foundCourse;
		
	}
	
	public void printCourseList(List<Course> courseList) {
		// added helper method to output course list details
		// TODO use printf to format nicely
		System.out.printf("%-5s %-28s %s \n", "ID", "COURSE","INSTRUCTOR");
		for (Course c:courseList) {
			System.out.printf("%-5s %-28s %s \n", c.getcId(), c.getcName(), c.getcInstructorName());
		}
	}

}