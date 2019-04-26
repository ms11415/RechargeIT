package jpa.dao;

import java.util.List;
import jpa.entitymodels.Course;

import javax.persistence.EntityManager;

public interface CourseDAO {
	
	public List<Course> getAllCourses(EntityManager em);

}
