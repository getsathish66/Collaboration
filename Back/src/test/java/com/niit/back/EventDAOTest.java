package com.niit.back;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.back.dao.EventDAO;
import com.niit.back.model.Event;

public class EventDAOTest {
	
	@Autowired
	static AnnotationConfigApplicationContext context;

	@Autowired
	static EventDAO eventDAO;

	@Autowired
	static Event event;

	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.back");
		context.refresh();

		// get the eventDAO from context
		eventDAO = (EventDAO) context.getBean("EventDAO");

		event = (Event) context.getBean("event");
	}

	@Test
	public void createEventDAOTest() {

		event.setEventcategory("jobs");
		event.setEventdatails("placement");
		event.setEventname("campus");
		
		eventDAO.saveOrUpdate(event);

	}
	


}
