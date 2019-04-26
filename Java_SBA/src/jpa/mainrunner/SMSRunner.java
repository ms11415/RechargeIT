package jpa.mainrunner;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import jpa.entitymodels.*;
import jpa.service.*;

public class SMSRunner {
	
	// exit method so that I can avoid nested if-statements for the menus
	public static void sayGoodbye() {
		System.out.println("Program terminated. Goodbye!");
		System.exit(1);
	}

	public static void main(String[] args) {
		// create connection to database
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SMS");
		EntityManager em = emf.createEntityManager();
		
		// instantiate course and student services for later use
		CourseService courseService = new CourseService();
		StudentService studentService = new StudentService();
		
		// instantiate Student object for interacting with menus
		Student studentUser = new Student();
		
		// instantiate Scanner object for user input
		Scanner input = new Scanner(System.in);
		
		// present initial menu to user
		System.out.println("Welcome to the Student Management Service. Please enter 1 or 2:");
		System.out.println("[1] Student Login");
		System.out.println("[2] Exit System");
		String userinput = input.nextLine();
		if (userinput.equals("2")) {
			// exit method
			sayGoodbye();
		} else if (userinput.equals("1")) {
			System.out.println("Please enter your email address:");
			// take input and set the email value in studentUser
			studentUser.setsEmail(input.nextLine());
			System.out.println("Please enter your password:");
			// take input and set the password value in studentUser
			studentUser.setsPass(input.nextLine());
		} else {
			// inform user of invalid choice
			System.out.println("Invalid option.");
			// exit method
			sayGoodbye();
		}
		
		// validate user by using getters and passing as arguments
		// if valid, exit program
		if (!studentService.validateStudent(em, studentUser.getsEmail(), studentUser.getsPass())) {
			sayGoodbye();
		}
		
		// get student course list and then use setter to associate them with studentUser
		studentUser.setsCourses(studentService.getStudentCourses(em, studentUser.getsEmail()));
		// if course list is empty, inform user; otherwise, print course list
		if (studentUser.getsCourses().isEmpty()) {
			System.out.println("*** You are not registered for any courses. *** \n");
		} else {
			System.out.println("Your courses:");
			courseService.printCourseList(studentUser.getsCourses());
		}

		// next menu, register or exit
		System.out.println("\nWould you like to register? Please enter 1 or 2:");
		System.out.println("[1] Register to Class");
		System.out.println("[2] Logout");
	
		userinput = input.nextLine();
		if (userinput.equals("2")) {
			System.out.println("\nYou are logged out.");
			// exit method
			sayGoodbye();
		} else if (userinput.equals("1")) {
			// do nothing, continue with program execution to avoid nested if-statements
		} else {
			// inform user of invalid choice
			System.out.println("Invalid option.");
			// exit method
			sayGoodbye();
		}
		
		System.out.println("This is the list of available of courses:");
		// print out all courses
		courseService.printCourseList(courseService.getAllCourses(em));
		// prompt user for course choice
		System.out.println("\nEnter the course ID number to register:");
		// initialize courseId so that Java stops complaining on line 110
		int courseId = 0;
		// try-catch in case user enters non-integer value
		try {
			courseId = input.nextInt();
		} catch (Exception e) {
			// inform user of invalid entry
			System.out.println("Invalid entry.");
			// exit method
			sayGoodbye();
		}
		// register user
		studentService.registerStudentToCourse(em, studentUser.getsEmail(), courseId);
		// reprint student course list after registration
		System.out.println("\nYour courses:");
		courseService.printCourseList(studentService.getStudentCourses(em, studentUser.getsEmail()));
		// inform user of sign-out
		System.out.println("\nRegistration complete. You have been signed out.");
		// close Scanner object to avoid memory leaks
		input.close();

		// close database connection
		em.close();
		emf.close();
	}
}