package com.niit.back;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.back.dao.ForumDAO;
import com.niit.back.model.Forum;

public class ForumDAOTest {
	
	@Autowired
	static AnnotationConfigApplicationContext context;

	@Autowired
	static ForumDAO forumDAO;

	@Autowired
	static Forum forum;

	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.back");
		context.refresh();

		// get the forumDAO from context
		forumDAO = (ForumDAO) context.getBean("ForumDAO");

		forum = (Forum) context.getBean("forum");
	}

	@Test
	public void createForumDAOTest() {

		forum.setForumcontent("placement");
		forum.setForumname("jobs");
		forum.setStatus("n");
		forum.setUserId(5);
		
		forumDAO.saveOrUpdate(forum);

	}
	


}
