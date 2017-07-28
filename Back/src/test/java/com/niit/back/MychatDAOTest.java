/*package com.niit.back;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.back.dao.MychatDAO;
import com.niit.back.model.Mychat;

public class MychatDAOTest {
	
	@Autowired
	static AnnotationConfigApplicationContext context;

	@Autowired
	static MychatDAO mychatDAO;

	@Autowired
	static Mychat mychat;

	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.back");
		context.refresh();

		// get the mychatDAO from context
		mychatDAO = (MychatDAO) context.getBean("MychatDAO");

		mychat = (Mychat) context.getBean("mychat");
	}

	@Test
	public void createMychatDAOTest() {

		mychat.setMessage("hai");
		
		mychatDAO.saveOrUpdate(mychat);

	}


}
*/