package com.niit.back;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.back.dao.BlogDAO;
import com.niit.back.model.Blog;

public class BlogDAOTest {
	
	@Autowired
	static AnnotationConfigApplicationContext context;

	@Autowired
	static BlogDAO blogDAO;

	@Autowired
	static Blog blog;

	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.back");
		context.refresh();

		// get the blogDAO from context
		blogDAO = (BlogDAO) context.getBean("BlogDAO");

		blog = (Blog) context.getBean("blog");
	}

	@Test
	public void createBlogDAOTest() {

		blog.setBlogname("sathish");
		blog.setDescription("haa");
		blog.setLikes(4);
		blog.setStatus("nul");
		blog.setTitle("title");
		blog.setUserid(5);
		
		blogDAO.saveOrUpdate(blog);

	}
	

}
