package com.niit.back;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.back.dao.CommentDAO;
import com.niit.back.model.Comment;

public class CommentDAOTest {
	
	@Autowired
	static AnnotationConfigApplicationContext context;

	@Autowired
	static CommentDAO commentDAO;

	@Autowired
	static Comment comment;

	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.back");
		context.refresh();

		// get the commentDAO from context
		commentDAO = (CommentDAO) context.getBean("CommentDAO");

		comment = (Comment) context.getBean("comment");
	}

	@Test
	public void createCommentDAOTest() {

		comment.setBlogid(8);
		
		comment.setForumid(5);
		comment.setUsercomment("hai");
		comment.setUserid(5);
		comment.setUsername("sathish");
		commentDAO.saveOrUpdate(comment);

	}
	


}
