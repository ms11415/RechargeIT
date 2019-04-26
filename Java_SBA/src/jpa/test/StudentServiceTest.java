package jpa.test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import jpa.entitymodels.*;
import jpa.service.*;

import org.junit.Test;

public class StudentServiceTest {

	@Test
	public final void testGetStudentByEmail() {
		// create database connection
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SMS");
		EntityManager em = emf.createEntityManager();
		
		// create StudentService to access non-static method
		StudentService testService = new StudentService();
		
		// get student using unknown email value
		Student nullStudent = testService.getStudentByEmail(em, "fake@email.com");
		// get student using known email value
		Student notNullStudent = testService.getStudentByEmail(em, "hluckham0@google.ru");
		// close database connection
		em.close();
		emf.close();
		
		// ensure that the method returns null and not null objects
		assertNull(nullStudent);
		assertNotNull(notNullStudent);
	}
}
