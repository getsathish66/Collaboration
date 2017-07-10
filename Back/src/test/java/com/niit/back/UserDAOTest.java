package com.niit.back;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.back.dao.UserDAO;
import com.niit.back.model.User;

public class UserDAOTest {

	@Autowired
	static AnnotationConfigApplicationContext context;

	@Autowired
	static UserDAO userDAO;

	@Autowired
	static User user;

	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.back");
		context.refresh();

		// get the userDAO from context
		userDAO = (UserDAO) context.getBean("UserDAO");

		user = (User) context.getBean("user");
	}

	@Test
	public void createUserDAOTest() {

		user.setUsername("Sathish");
		user.setPassword("1111");
		user.setContact("8807761502");
		user.setEmail("get@gmail.com");
		user.setAddress("Tirupur");
		user.setRole("admin");
		userDAO.saveOrUpdate(user);

	}
	
}
